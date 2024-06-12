-- 데이터베이스 생성 및 설정
CREATE SCHEMA IF NOT EXISTS `testdb`;

-- User 테이블 생성
CREATE TABLE IF NOT EXISTS UserUser (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

-- Resume 테이블 생성
CREATE TABLE IF NOT EXISTS Resume (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    title VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES UserUser(id) ON DELETE CASCADE
);

-- Skill 테이블 생성
CREATE TABLE IF NOT EXISTS Skill (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    resume_id BIGINT NOT NULL,
    title VARCHAR(255) NOT NULL,
    content TEXT,
    FOREIGN KEY (resume_id) REFERENCES Resume(id) ON DELETE CASCADE
);

-- Project 테이블 생성
CREATE TABLE IF NOT EXISTS Project (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    resume_id BIGINT NOT NULL,
    title VARCHAR(255) NOT NULL,
    content TEXT,
    period VARCHAR(100),
    is_current BOOLEAN,
    FOREIGN KEY (resume_id) REFERENCES Resume(id) ON DELETE CASCADE
);

-- Test용 User 추가
INSERT INTO UserUser (email, password) VALUES ('test@test.com', 'test');