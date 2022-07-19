DROP DATABASE IF EXISTS phonesinventoryervice;
DROP USER IF EXISTS `phone_inventory_service`@`%`;
CREATE DATABASE IF NOT EXISTS phoneinventoryservice CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER IF NOT EXISTS `phone_inventory_service`@`%` IDENTIFIED WITH mysql_native_password BY 'password';
GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, REFERENCES, INDEX, ALTER, EXECUTE, CREATE VIEW, SHOW VIEW,
CREATE ROUTINE, ALTER ROUTINE, EVENT, TRIGGER ON `phoneinventoryservice`.* TO `phone_inventory_service`@`%`;
FLUSH PRIVILEGES;