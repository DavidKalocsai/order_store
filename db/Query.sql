-- ---------------------------
-- USER
-- ---------------------------
DROP USER IF EXISTS 'app'@'localhost';
CREATE USER IF NOT EXISTS 'app'@'localhost' IDENTIFIED BY 'app';

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
    PRIMARY KEY (group_id)
);

-- ---------------------------
-- Table - Order
-- ---------------------------
CREATE TABLE order_schema.order_table (
	order_id INT NOT NULL DEFAULT 1,
    group_id INT NOT NULL,
    order_date DATE NOT NULL,
    order_desc VARCHAR(200) NOT NULL,
    order_status VARCHAR(50) NOT NULL,
    order_version INT NOT NULL DEFAULT 1,
    PRIMARY KEY (order_id, group_id),
    FOREIGN KEY (group_id) REFERENCES order_schema.group_table(group_id)
);

-- ---------------------------
-- Functions - get number of rows belonging to a given group_id 
-- ---------------------------
DROP FUNCTION IF EXISTS order_schema.group_count;
DELIMITER $$
CREATE FUNCTION order_schema.group_count(group_id INT)
RETURNS INT READS SQL DATA
BEGIN
	DECLARE group_count INT;
    SET group_count = (SELECT MAX(order_id) FROM order_schema.order_table WHERE order_schema.order_table.group_id = group_id);
    IF (group_count IS NULL) THEN SET group_count = 0; END IF;
	RETURN (group_count);
END$$
DELIMITER ;

-- SELECT order_schema.group_count('2');

-- ---------------------------
-- Functions - get group id belonging to given group name
-- ---------------------------
DROP FUNCTION IF EXISTS order_schema.get_group_id;
DELIMITER $$
CREATE FUNCTION order_schema.get_group_id(group_name VARCHAR(100))
RETURNS INT READS SQL DATA
BEGIN
	DECLARE group_id INT;
    SET group_id = (SELECT g.group_id FROM order_schema.group_table g WHERE g.group_name = group_name);
	RETURN (group_id);
END$$
DELIMITER ;

-- SELECT order_schema.get_group_id('Group 1')
-- ---------------------------
-- Functions - get next group id
-- ---------------------------
DROP FUNCTION IF EXISTS order_schema.get_next_group_id;
DELIMITER $$
CREATE FUNCTION order_schema.get_next_group_id()
RETURNS INT READS SQL DATA
BEGIN
	DECLARE l_group_id INT;
    SET l_group_id = (SELECT MAX(group_id) + 1 FROM order_schema.group_table g FOR UPDATE);
	RETURN (l_group_id);
END$$
DELIMITER ;

-- SELECT order_schema.get_next_group_id();
-- ---------------------------
-- Functions - version validatation
-- ---------------------------
DROP FUNCTION IF EXISTS order_schema.get_next_version_id;
DELIMITER $$
CREATE FUNCTION order_schema.get_next_version_id(
	i_order_id INT,
    i_group_id INT,
    i_version INT
)
RETURNS INT READS SQL DATA
BEGIN
	DECLARE l_version INT;
    DECLARE ret_valid INT;
    SET l_version = (SELECT order_version FROM order_schema.order_table o WHERE o.order_id = i_order_id AND o.group_id = i_group_id FOR UPDATE);
    if (i_version = l_version) THEN
		SET ret_valid = l_version + 1;
    ELSE
		SET ret_valid = -1; 
    END IF;
	RETURN (ret_valid);
END$$
DELIMITER ;

-- SELECT order_schema.get_next_version_id(1, 1, 1);

-- ---------------------------
-- Function - Calculate order id before insert
-- ---------------------------
DROP FUNCTION IF EXISTS order_schema.order_id_generation;
DELIMITER $$
CREATE FUNCTION order_schema.order_id_generation (
	group_id INT
)
RETURNS INT READS SQL DATA
BEGIN
	DECLARE l_order_id INT;
    SET l_order_id = order_schema.group_count(group_id) + 1;
    RETURN (l_order_id);
END $$
DELIMITER ;

-- ---------------------------
-- View - Merged Order - Group 
-- ---------------------------
DROP VIEW IF EXISTS order_schema.order_view;
CREATE VIEW order_schema.order_view
AS
	SELECT o.order_id, o.group_id, g.group_name, o.order_date, o.order_desc, o.order_status, o.order_version   
	FROM order_schema.order_table o
	LEFT JOIN order_schema.group_table g on g.group_id = o.group_id;

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
	DECLARE l_group_id INT;
	DECLARE l_new_order_id INT;
    SET l_group_id = order_schema.get_group_id(i_group_name);
    IF (l_group_id IS NULL) THEN
		SET l_group_id = order_schema.get_next_group_id();
		INSERT INTO order_schema.group_table(group_id, group_name)
		VALUES(l_group_id, i_group_name);
    END IF;   
    SET l_new_order_id = order_id_generation(l_group_id);
    INSERT INTO order_schema.order_table(order_id, group_id, order_date, order_desc, order_status)
    VALUES(l_new_order_id, l_group_id, i_order_date, i_order_desc, i_order_status);
    CALL order_schema.get_order(l_new_order_id, i_group_name);
END $$
DELIMITER ;

GRANT EXECUTE ON PROCEDURE order_schema.add_order TO 'app'@'localhost';

-- ---------------------------
-- Procedure - get order
-- ---------------------------
DROP PROCEDURE IF EXISTS order_schema.get_order;
DELIMITER $$
CREATE PROCEDURE order_schema.get_order(
	IN i_order_id INT,
    IN i_group_name VARCHAR(100)
)
BEGIN
	SELECT d.order_id, d.group_name, d.order_date, d.order_desc, d.order_status, d.order_version FROM order_schema.order_view d WHERE d.order_id = i_order_id AND d.group_name = i_group_name;
END $$
DELIMITER ;

GRANT EXECUTE ON PROCEDURE order_schema.get_order TO 'app'@'localhost';

-- ---------------------------
-- Procedure - get orders
-- ---------------------------
DROP PROCEDURE IF EXISTS order_schema.get_orders;
DELIMITER $$
CREATE PROCEDURE order_schema.get_orders()
BEGIN
	SELECT d.order_id, d.group_name, d.order_date, d.order_desc, d.order_status, d.order_version FROM order_schema.order_view d;
END $$
DELIMITER ;

GRANT EXECUTE ON PROCEDURE order_schema.get_orders TO 'app'@'localhost';

-- ---------------------------
-- Procedure - update order
-- --------------------------
DROP PROCEDURE IF EXISTS order_schema.update_order;
DELIMITER $$
CREATE PROCEDURE order_schema.update_order(
	IN i_order_id INT,
    IN i_group_name VARCHAR(100),
    IN i_order_date DATE,
    IN i_order_desc VARCHAR(200),
    IN i_order_status VARCHAR(50),
    IN i_order_version INT
)
BEGIN
	DECLARE l_group_id INT;
    DECLARE l_next_version_id INT;
    SET l_group_id = order_schema.get_group_id(i_group_name);
    SET l_next_version_id = order_schema.get_next_version_id(i_order_id, l_group_id, i_order_version);
    IF (l_group_id IS NOT NULL AND l_next_version_id > 0) THEN
		UPDATE order_schema.order_table o SET o.order_date = i_order_date, o.order_desc = i_order_desc, o.order_status = i_order_status, o.order_version = l_next_version_id WHERE o.order_id = i_order_id AND o.group_id = l_group_id;
        CALL order_schema.get_order(i_order_id, i_group_name);
    END IF;    
END $$
DELIMITER ;

GRANT EXECUTE ON PROCEDURE order_schema.update_order TO 'app'@'localhost';

-- ---------------------------
-- Procedure - delete order
-- --------------------------
DROP PROCEDURE IF EXISTS order_schema.delete_order;
DELIMITER $$
CREATE PROCEDURE order_schema.delete_order(
	IN i_order_id INT,
    IN i_group_name VARCHAR(100), 
    IN i_order_version INT
)
BEGIN
	DECLARE l_group_id INT;
    DECLARE l_next_version_id INT;
    SET l_group_id = order_schema.get_group_id(i_group_name);
    SET l_next_version_id = order_schema.get_next_version_id(i_order_id, l_group_id, i_order_version);
    IF (l_group_id IS NOT NULL AND l_next_version_id > 0) THEN
		UPDATE order_schema.order_table o SET o.order_status = 'deleted', o.order_version = l_next_version_id WHERE o.order_id = i_order_id AND o.group_id = l_group_id;
        CALL order_schema.get_order(i_order_id, i_group_name);
	END IF;
END $$
DELIMITER ;

GRANT EXECUTE ON PROCEDURE order_schema.delete_order TO 'app'@'localhost';

