CREATE DATABASE jwt_db;

USE jwt_db;

CREATE user jwt IDENTIFIED BY 'jwt';

GRANT ALL PRIVILEGES ON *.* TO jwt@localhost IDENTIFIED BY 'jwt';