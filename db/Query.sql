-- ---------------------------
-- ROLE
-- ---------------------------
DROP ROLE IF EXISTS order_app;
DROP ROLE IF EXISTS order_viewer;

CREATE ROLE IF NOT EXISTS order_app;
CREATE ROLE IF NOT EXISTS order_viewer;
-- ---------------------------
-- USER
-- ---------------------------
DROP USER IF EXISTS 'app'@'localhost';
DROP USER IF EXISTS 'viewer'@'localhost';

CREATE USER IF NOT EXISTS 'app'@'localhost' IDENTIFIED BY 'app';
CREATE USER IF NOT EXISTS 'viewer'@'localhost' IDENTIFIED BY 'viewer';

-- ---------------------------
-- Add user roles
-- ---------------------------	
GRANT 'order_app' TO 'app'@'localhost';
GRANT 'order_viewer' TO 'viewer'@'localhost';

-- ---------------------------
-- Schema
-- ---------------------------
DROP SCHEMA IF EXISTS order_schema;
CREATE SCHEMA IF NOT EXISTS order_schema;
USE order_schema;

-- ---------------------------
-- Table - Group
-- ---------------------------
CREATE TABLE IF NOT EXISTS order_schema.group (
	group_id INT NOT NULL,
    group_name VARCHAR(100) NOT NULL UNIQUE,
    PRIMARY KEY (group_id)
);


-- ---------------------------
-- Table - Order
-- ---------------------------
CREATE TABLE order_schema.order (
	order_id INT NOT NULL DEFAULT 1,
    group_id INT NOT NULL,
    order_date DATE NOT NULL,
    description VARCHAR(200) NOT NULL,
    status ENUM ('active','deleted') NOT NULL DEFAULT 'active',
    PRIMARY KEY (order_id, group_id),
    FOREIGN KEY (group_id) REFERENCES order_schema.group(group_id)
);

-- ---------------------------
-- Functions - get count of group id
-- ---------------------------
DROP FUNCTION IF EXISTS order_schema.group_id_count;
DELIMITER $$
CREATE FUNCTION order_schema.group_id_count(group_id INT)
RETURNS INT READS SQL DATA
BEGIN
	DECLARE group_count INT;
    SET group_count = (SELECT MAX(order_id) FROM order_schema.order WHERE order_schema.order.group_id = group_id);
    IF (group_count IS NULL) THEN SET group_count = 0; END IF;
	RETURN (group_count);
END$$
DELIMITER ;

-- ---------------------------
-- Functions - get group id
-- ---------------------------
DROP FUNCTION IF EXISTS order_schema.get_group_id;
DELIMITER $$
CREATE FUNCTION order_schema.group_id_lookup(group_name VARCHAR(100))
RETURNS INT READS SQL DATA
BEGIN
	DECLARE group_id INT;
    SET group_id = (SELECT g.group_id FROM order_schema.group g WHERE g.group_name = group_name);
	RETURN (group_id);
END$$
DELIMITER ;

-- ---------------------------
-- Functions - get new group id
-- ---------------------------
DROP FUNCTION IF EXISTS order_schema.get_new_group_id;
DELIMITER $$
CREATE FUNCTION order_schema.get_new_group_id()
RETURNS INT READS SQL DATA
BEGIN
	DECLARE group_id INT;
    SET group_id = (SELECT COUNT(g.group_id) + 1 FROM order_schema.group g);
	RETURN (group_id);
END$$
DELIMITER ;

-- ---------------------------
-- Create Trigger - Id generation
-- ---------------------------
DROP TRIGGER IF EXISTS order_schema.id_generation;
DELIMITER $$
CREATE TRIGGER order_schema.id_generation BEFORE INSERT ON order_schema.order
FOR EACH ROW
BEGIN
SET NEW.order_id = order_schema.group_id_count(NEW.group_id) + 1;
END $$
DELIMITER ;

-- ---------------------------
-- Create View - All Order
-- ---------------------------
DROP VIEW IF EXISTS order_schema.detailed_view;
CREATE VIEW order_schema.detailed_view
AS
SELECT o.order_id, o.group_id, g.group_name, o.order_date, o.description, o.status   
FROM order_schema.order o
LEFT JOIN order_schema.group g on g.group_id = o.group_id;

SELECT * FROM order_schema.detailed_view;

-- ---------------------------
-- Create View - CountView
-- ---------------------------
DROP VIEW IF EXISTS order_schema.count_view;
CREATE VIEW order_schema.count_view
AS
SELECT o.group_id, max(o.order_id), o.group_name  
FROM order_schema.detailed_view o
GROUP BY o.group_id;

SELECT * FROM order_schema.count_view;


-- ---------------------------
-- Store procedure
-- ---------------------------
DROP PROCEDURE IF EXISTS order_schema.add_order;
DELIMITER $$
CREATE PROCEDURE order_schema.add_order(
	IN group_name VARCHAR(100),
    IN order_date DATE,
    IN description VARCHAR(200),
    IN status ENUM('active','deleted')
)
BEGIN
	DECLARE group_id INT;
    SET group_id = order_schema.group_id_lookup(group_name);
    IF (group_id IS NULL) THEN
		SET group_id = order_schema.get_new_group_id();
        INSERT INTO order_schema.group(group_id, group_name)
		VALUES(group_id, group_name);
    END IF;
    INSERT INTO order_schema.order(group_id, order_date, description, status)
    VALUES(group_id, order_date, description, status);
END $$
DELIMITER ;

GRANT EXECUTE ON PROCEDURE order_schema.add_order TO order_app;

DROP PROCEDURE IF EXISTS order_schema.get_order;
DELIMITER $$
CREATE PROCEDURE order_schema.get_order(
	IN id INT,
    IN group_name VARCHAR(100)
)
BEGIN
	SELECT d.order_id, d.group_name, d.order_date, d.description, d.status FROM order_schema.detailed_view d WHERE d.order_id = id AND d.group_name = group_name;
END $$
DELIMITER ;

GRANT EXECUTE ON PROCEDURE order_schema.get_order TO order_app;
GRANT EXECUTE ON PROCEDURE order_schema.get_order TO 'app'@'localhost';

DROP PROCEDURE IF EXISTS order_schema.get_orders;
DELIMITER $$
CREATE PROCEDURE order_schema.get_orders()
BEGIN
	SELECT d.order_id, d.group_name, d.order_date, d.description, d.status FROM order_schema.detailed_view d;
END $$
DELIMITER ;

GRANT EXECUTE ON PROCEDURE order_schema.get_orders TO order_app;

DROP PROCEDURE IF EXISTS order_schema.update_order;
DELIMITER $$
CREATE PROCEDURE order_schema.update_order(
	IN order_id INT,
    IN group_name VARCHAR(100),
    IN order_date DATE,
    IN description VARCHAR(200),
    IN status ENUM('active','deleted')
)
BEGIN
	DECLARE group_id INT;
    SET group_id = order_schema.group_id_lookup(group_name);
	IF (group_id IS NOT NULL) THEN
		UPDATE order_schema.order o SET o.order_date = order_date, o.description = description, o.status = status WHERE o.order_id = order_id AND o.group_id = group_id;
    END IF;
END $$
DELIMITER ;

GRANT EXECUTE ON PROCEDURE order_schema.update_order TO order_app;

DROP PROCEDURE IF EXISTS order_schema.delete_order;
DELIMITER $$
CREATE PROCEDURE order_schema.delete_order(
	IN id INT,
    IN group_name VARCHAR(100)
)
BEGIN
	DECLARE group_id INT;
	SET group_id = order_schema.group_id_lookup(group_name);
	IF (group_id IS NOT NULL) THEN
		UPDATE order_schema.order o SET o.status = 'deleted' WHERE o.order_ID = id AND o.group_id = group_id;
	END IF;
END $$
DELIMITER ;

GRANT EXECUTE ON PROCEDURE order_schema.get_orders TO order_app;
-- ---------------------------
-- Test data - group
-- ---------------------------
INSERT INTO order_schema.group(group_id, group_name)
VALUES(1, 'Name 1'); 
INSERT INTO order_schema.group(group_id, group_name)
VALUES( 2, 'Name 2');

-- ---------------------------
-- Test data - order
-- ---------------------------
INSERT INTO order_schema.order(group_id, order_date, description)
VALUES( 1, CURDATE(), '1,1'); 
INSERT INTO order_schema.order(group_id, order_date, description)
VALUES( 2, CURDATE(), '2,1');
INSERT INTO order_schema.order(group_id, order_date, description)
VALUES( 1, CURDATE(), '1,2'); 
INSERT INTO order_schema.order(group_id, order_date, description)
VALUES( 2, CURDATE(), '2,2');

-- ---------------------------
-- Test procedures
-- ---------------------------
CALL order_schema.add_order('Name 3','2020-12-24', 'David 24', 'ACTIVE');
CALL order_schema.get_orders();
CALL order_schema.delete_order(1, 'Name 1');
CALL order_schema.update_order(1, 'Name 1', '2019-03-24', 'David 1,1', 'ACTIVE');



-- GRANT ALL PRIVILEGES ON order_schema.* TO 'app'@'localhost';