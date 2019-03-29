-- ---------------------------
-- Create test table
-- ---------------------------
DROP TABLE IF EXISTS order_schema.test_data;
CREATE TABLE IF NOT EXISTS order_schema.test_data (
	test_name VARCHAR(100) NOT NULL UNIQUE,
    test_result INT
);

-- ---------------------------
-- Proceduer - add test result
-- ---------------------------
DROP PROCEDURE IF EXISTS order_schema.test_add_result;
DELIMITER $$
CREATE PROCEDURE order_schema.test_add_result(
	in i_test_name VARCHAR(100),
    in i_test_result INT
)
BEGIN
	INSERT INTO order_schema.test_data(test_name, test_result)
	VALUES(i_test_name, i_test_result); 
END $$
DELIMITER ;

-- ---------------------------
-- Proceduer - add test data to tables
-- ---------------------------
DROP PROCEDURE IF EXISTS order_schema.test_add_data;
DELIMITER $$
CREATE PROCEDURE order_schema.test_add_data()
BEGIN
	-- ---------------------------
	-- Test data - group
	-- ---------------------------
	INSERT INTO order_schema.group_table(group_id, group_name)
	VALUES(1001, 'Test Group 1'); 
	INSERT INTO order_schema.group_table(group_id, group_name)
	VALUES(1002, 'Test Group 2');

	-- ---------------------------
	-- Test data - order
	-- ---------------------------
	INSERT INTO order_schema.order_table(group_order_id, group_id, order_date, order_desc, order_status)
	VALUES( 1001, 1001, CURDATE(), '1,1 ', 'active'); 
	INSERT INTO order_schema.order_table(group_order_id, group_id, order_date, order_desc, order_status)
	VALUES( 1001, 1002, CURDATE(), '1,2 ',  'active');
	INSERT INTO order_schema.order_table(group_order_id, group_id, order_date, order_desc, order_status)
	VALUES( 1002, 1001, CURDATE(), '2,1 ',  'active'); 
	INSERT INTO order_schema.order_table(group_order_id, group_id, order_date, order_desc, order_status)
	VALUES( 1002, 1002, CURDATE(), '2,2 ',  'active');
END $$
DELIMITER ;
GRANT EXECUTE ON PROCEDURE order_schema.test_add_data TO 'test'@'localhost';
-- ---------------------------
-- Test procedures
-- ---------------------------
DROP PROCEDURE IF EXISTS order_schema.test_add;
DELIMITER $$
CREATE PROCEDURE order_schema.test_add()
BEGIN
	DECLARE result INT;
    SET result = 0;
	SET autocommit=0;
	START TRANSACTION;
		CALL order_schema.add_order('Group 3', '2020-12-24', '1,3', 'active');
        SET result = (SELECT COUNT(t.order_id) from (SELECT * FROM order_table o WHERE o.order_id = 1 AND o.group_id = 1) t);
    ROLLBACK;
    SET autocommit=1;
    CALL order_schema.test_add_result('Testing: add', result);
END $$
DELIMITER ;

DROP PROCEDURE IF EXISTS order_schema.test_delete;
DELIMITER $$
CREATE PROCEDURE order_schema.test_delete()
BEGIN
	DECLARE result INT;
    SET result = 0;
	SET autocommit=0;
	START TRANSACTION;
		CALL order_schema.test_add_data();
		CALL order_schema.delete_order(1, 1);
        SET result = (SELECT COUNT(t.order_id) from (SELECT * FROM order_table o WHERE o.order_id = 1 AND o.group_id = 1001 AND o.order_status = 'deleted' AND o.order_version = 2) t);
    ROLLBACK;
    SET autocommit=1;
    CALL order_schema.test_add_result('Testing: delete', result);
END $$
DELIMITER ;

DROP PROCEDURE IF EXISTS order_schema.test_update;
DELIMITER $$
CREATE PROCEDURE order_schema.test_update()
BEGIN
	DECLARE result INT; 
    SET result = 0;
	SET autocommit=0;
	START TRANSACTION;
	CALL order_schema.test_add_data();
    CALL order_schema.update_order(1, '2019-03-24', 'updated 1,3', 'updated', 1);
    SET result = (SELECT COUNT(t.order_id) from (SELECT * FROM order_table o WHERE o.group_order_id = 1 AND o.group_id = 2 AND o.order_status = 'updated' AND o.order_version = 2 AND o.order_date = '2019-03-24' AND o.order_desc = 'updated 1,3') t);
    ROLLBACK;
    SET autocommit=1;
    CALL order_schema.test_add_result('Testing: update', result);
END $$
DELIMITER ;

DROP PROCEDURE IF EXISTS order_schema.test_all;
DELIMITER $$
CREATE PROCEDURE order_schema.test_all()
BEGIN
	CALL order_schema.test_add();
	CALL order_schema.test_delete();
    CALL order_schema.test_update();
    SELECT * FROM order_schema.test_data;
END $$
DELIMITER ;

-- CALL order_schema.test_add();
-- CALL order_schema.test_delete();
-- CALL order_schema.add_order('Group 3', '2020-12-24', '1,3', 'active');
-- CALL order_schema.test_all();
-- SELECT * FROM order_view;