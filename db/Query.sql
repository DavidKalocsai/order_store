-- ---------------------------
-- USER
-- ---------------------------
DROP USER IF EXISTS 'app'@'localhost';
DROP USER IF EXISTS 'test'@'localhost';
CREATE USER IF NOT EXISTS 'app'@'localhost' IDENTIFIED BY 'app';
CREATE USER IF NOT EXISTS 'test'@'localhost' IDENTIFIED BY 'test';
-- ---------------------------
-- Schema
-- ---------------------------
DROP SCHEMA IF EXISTS order_schema;
CREATE SCHEMA IF NOT EXISTS order_schema;
USE order_schema;

-- ---------------------------
-- Table - Group
-- ---------------------------
CREATE TABLE IF NOT EXISTS order_schema.group_table (
	group_id INT NOT NULL,
    group_name VARCHAR(100) NOT NULL UNIQUE,
    group_member_count INT NOT NULL,
    PRIMARY KEY (group_id)
);

-- ---------------------------
-- Table - Order
-- ---------------------------
CREATE TABLE order_schema.order_table (
	order_id INT NOT NULL AUTO_INCREMENT,
	group_order_id INT NOT NULL DEFAULT 1,
    group_id INT NOT NULL,
    order_date DATE NOT NULL,
    order_desc VARCHAR(200) NOT NULL,
    order_status VARCHAR(50) NOT NULL,
    order_version INT NOT NULL DEFAULT 1,
    PRIMARY KEY (order_id),
    FOREIGN KEY (group_id) REFERENCES order_schema.group_table(group_id)
);

CREATE UNIQUE INDEX group_table_pk_index
ON order_schema.group_table (group_id);

CREATE UNIQUE INDEX group_table_u_index
ON order_schema.group_table (group_name);

CREATE UNIQUE INDEX order_table_pk_index
ON order_schema.order_table (group_order_id, group_id);

CREATE INDEX order_table_fk_index
ON order_schema.order_table (group_id);

-- SELECT order_schema.get_group_id('Test Group 1')
-- ---------------------------
-- Functions - get next group id
-- ---------------------------
DROP FUNCTION IF EXISTS order_schema.get_next_group_id;
DELIMITER $$
CREATE FUNCTION order_schema.get_next_group_id()
RETURNS INT READS SQL DATA
BEGIN
	DECLARE l_group_id INT;
    SET l_group_id = (SELECT MAX(group_id) FROM order_schema.group_table g FOR UPDATE);
    IF (l_group_id IS NULL) THEN SET l_group_id = 0; END IF;   
    RETURN (l_group_id + 1);
END$$
DELIMITER ;

-- SELECT order_schema.get_next_group_id();
-- ---------------------------
-- Function - group_order_id_generation
-- ---------------------------
DROP FUNCTION IF EXISTS order_schema.group_order_id_generation;
DELIMITER $$
CREATE FUNCTION order_schema.group_order_id_generation (
	group_id INT
)
RETURNS INT READS SQL DATA
BEGIN
	DECLARE l_group_order_id INT;
    SET l_group_order_id = (SELECT MAX(group_id) FROM order_schema.group_table g FOR UPDATE);
    RETURN (l_group_order_id);
END $$
DELIMITER ;

-- SELECT order_schema.group_order_id_generation('1001');
-- ---------------------------
-- View - Merged Order - Group 
-- ---------------------------
DROP VIEW IF EXISTS order_schema.order_view;
CREATE VIEW order_schema.order_view
AS
	SELECT o.order_id, o.group_order_id, o.group_id, g.group_name, o.order_date, o.order_desc, o.order_status, o.order_version   
	FROM order_schema.order_table o
	LEFT JOIN order_schema.group_table g on g.group_id = o.group_id;

-- SELECT * FROM order_schema.order_view
-- ---------------------------
-- Procedure - add order
-- ---------------------------
DROP PROCEDURE IF EXISTS order_schema.add_order;
DELIMITER $$
CREATE PROCEDURE order_schema.add_order(
	IN i_group_name VARCHAR(100),
    IN i_order_date DATE,
    IN i_order_desc VARCHAR(200),
    IN i_order_status VARCHAR(50)
)
BEGIN
	DECLARE l_id INT;
    DECLARE l_group_id INT;
    DECLARE l_member_count INT;
    SET l_group_id = (SELECT group_id FROM order_schema.group_table g WHERE g.group_name = i_group_name FOR UPDATE);
    IF (l_group_id IS NULL) THEN
		SET l_group_id = (SELECT MAX(group_id) FROM order_schema.group_table g FOR UPDATE);
        IF (l_group_id IS NULL) THEN SET l_group_id = 0; END IF;
        SET l_group_id = l_group_id + 1;
		INSERT INTO order_schema.group_table(group_id, group_name, group_member_count)
		VALUES(l_group_id, i_group_name, 0);
    END IF;
    SET l_member_count = (SELECT group_member_count FROM order_schema.group_table g WHERE g.group_id = l_group_id FOR UPDATE);
		INSERT INTO order_schema.order_table(group_order_id, group_id, order_date, order_desc, order_status)
		VALUES(l_member_count + 1, l_group_id, i_order_date, i_order_desc, i_order_status);
	UPDATE order_schema.group_table g SET g.group_member_count = l_member_count + 1 WHERE g.group_id = l_group_id;
    SET l_id = (SELECT v.order_id FROM order_schema.order_view v WHERE v.group_order_id = l_member_count + 1 AND v.group_name = i_group_name);
    CALL order_schema.get_order(l_id);
END $$
DELIMITER ;

-- CALL order_schema.add_order('Group 3', '2020-12-24', '1,3', 'active');
-- SELECT * FROM order_schema.order_view v;

GRANT EXECUTE ON PROCEDURE order_schema.add_order TO 'app'@'localhost';
GRANT EXECUTE ON PROCEDURE order_schema.add_order TO 'test'@'localhost';

-- ---------------------------
-- Procedure - get order
-- ---------------------------
DROP PROCEDURE IF EXISTS order_schema.get_order;
DELIMITER $$
CREATE PROCEDURE order_schema.get_order(
	IN i_order_id INT
)
BEGIN
	SELECT v.order_id, v.group_order_id, v.group_name, v.order_date, v.order_desc, v.order_status, v.order_version FROM order_schema.order_view v WHERE v.order_id = i_order_id;
END $$
DELIMITER ;

-- CALL order_schema.get_order(1);
-- SELECT * FROM order_schema.order_view v;

GRANT EXECUTE ON PROCEDURE order_schema.get_order TO 'app'@'localhost';
GRANT EXECUTE ON PROCEDURE order_schema.get_order TO 'test'@'localhost';

-- ---------------------------
-- Procedure - get orders
-- ---------------------------
DROP PROCEDURE IF EXISTS order_schema.get_orders;
DELIMITER $$
CREATE PROCEDURE order_schema.get_orders()
BEGIN
	SELECT v.order_id, v.group_order_id, v.group_name, v.order_date, v.order_desc, v.order_status, v.order_version FROM order_schema.order_view v;
END $$
DELIMITER ;

-- CALL order_schema.get_orders();
-- SELECT * FROM order_schema.order_view v;

GRANT EXECUTE ON PROCEDURE order_schema.get_orders TO 'app'@'localhost';
GRANT EXECUTE ON PROCEDURE order_schema.get_orders TO 'test'@'localhost';
-- ---------------------------
-- Procedure - update order
-- --------------------------
DROP PROCEDURE IF EXISTS order_schema.update_order;
DELIMITER $$
CREATE PROCEDURE order_schema.update_order(
	IN i_order_id INT,
	IN i_order_date DATE,
    IN i_order_desc VARCHAR(200),
    IN i_order_status VARCHAR(50),
    IN i_order_version INT
)
BEGIN
	UPDATE order_schema.order_table o SET o.order_date = i_order_date, o.order_desc = i_order_desc, o.order_status = i_order_status, o.order_version = i_order_version + 1 WHERE o.order_id = i_order_id AND o.order_version = i_order_version;
    CALL order_schema.get_order(i_order_id);
END $$
DELIMITER ;

-- CALL order_schema.update_order(1, '2019-11-11', 'Desc', 'active2', 1);
-- SELECT * FROM order_schema.order_view v;

GRANT EXECUTE ON PROCEDURE order_schema.update_order TO 'app'@'localhost';
GRANT EXECUTE ON PROCEDURE order_schema.update_order TO 'test'@'localhost';
-- ---------------------------
-- Procedure - delete order
-- --------------------------
DROP PROCEDURE IF EXISTS order_schema.delete_order;
DELIMITER $$
CREATE PROCEDURE order_schema.delete_order(
	IN i_order_id INT, 
    IN i_order_version INT
)
BEGIN
	DECLARE l_next_version_id INT;
    UPDATE order_schema.order_table o SET o.order_status = 'deleted', o.order_version = i_order_version + 1 WHERE o.order_id = i_order_id AND o.version_id = i_order_version;
    CALL order_schema.get_order(i_order_id);
END $$
DELIMITER ;

-- CALL order_schema.delete_order(1,2);


GRANT EXECUTE ON PROCEDURE order_schema.delete_order TO 'app'@'localhost';
GRANT EXECUTE ON PROCEDURE order_schema.delete_order TO 'test'@'localhost';

SELECT * FROM order_schema.order_view; 