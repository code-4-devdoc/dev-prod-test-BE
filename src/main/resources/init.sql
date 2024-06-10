-- 데이터베이스 생성 및 설정
CREATE SCHEMA IF NOT EXISTS `testdb`;

-- resume 테이블 생성
CREATE TABLE IF NOT EXISTS app_user (
    id SERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS resume (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255),
    created_at TIMESTAMP
);

CREATE TABLE IF NOT EXISTS section (
    id SERIAL PRIMARY KEY,
    content TEXT
);