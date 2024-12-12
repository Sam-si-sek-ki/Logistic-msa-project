-- 각 데이터베이스 및 사용자 생성 스크립트
CREATE DATABASE sparta_auth;
CREATE USER sparta_auth WITH PASSWORD 'sparta_auth';
GRANT ALL PRIVILEGES ON DATABASE sparta_auth TO sparta_auth;

CREATE DATABASE sparta_company;
CREATE USER sparta_company WITH PASSWORD 'sparta_company';
GRANT ALL PRIVILEGES ON DATABASE sparta_company TO sparta_company;

CREATE DATABASE sparta_delivery;
CREATE USER sparta_delivery WITH PASSWORD 'sparta_delivery';
GRANT CONNECT ON DATABASE sparta_delivery TO sparta_delivery;
GRANT ALL PRIVILEGES ON DATABASE sparta_delivery TO sparta_delivery;

CREATE DATABASE sparta_hub;
CREATE USER sparta_hub WITH PASSWORD 'sparta_hub';
GRANT CONNECT ON DATABASE sparta_hub TO sparta_hub;
GRANT ALL PRIVILEGES ON DATABASE sparta_hub TO sparta_hub;

CREATE DATABASE sparta_notification;
CREATE USER sparta_notification WITH PASSWORD 'sparta_notification';
GRANT CONNECT ON DATABASE sparta_notification TO sparta_notification;
GRANT ALL PRIVILEGES ON DATABASE sparta_notification TO sparta_notification;

CREATE DATABASE sparta_order;
CREATE USER sparta_order WITH PASSWORD 'sparta_order';
GRANT CONNECT ON DATABASE sparta_order TO sparta_order;
GRANT ALL PRIVILEGES ON DATABASE sparta_order TO sparta_order;

CREATE DATABASE sparta_product;
CREATE USER sparta_product WITH PASSWORD 'sparta_product';
GRANT CONNECT ON DATABASE sparta_product TO sparta_product;
GRANT ALL PRIVILEGES ON DATABASE sparta_product TO sparta_product;