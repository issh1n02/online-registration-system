-- Create database
CREATE DATABASE IF NOT EXISTS registration_system
  DEFAULT CHARACTER SET utf8mb4
  DEFAULT COLLATE utf8mb4_unicode_ci;

USE registration_system;

-- Activity table
CREATE TABLE IF NOT EXISTS activity (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    start_time DATETIME,
    end_time DATETIME,
    location VARCHAR(200),
    max_participants INT,
    current_participants INT DEFAULT 0,
    status VARCHAR(50) DEFAULT 'ACTIVE',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Registration table
CREATE TABLE IF NOT EXISTS registration (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    activity_id BIGINT NOT NULL,
    user_name VARCHAR(50) NOT NULL,
    user_email VARCHAR(100),
    user_phone VARCHAR(20),
    remark VARCHAR(100),
    status VARCHAR(20) DEFAULT 'CONFIRMED',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (activity_id) REFERENCES activity(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Indexes
CREATE INDEX idx_activity_status ON activity(status);
CREATE INDEX idx_registration_activity ON registration(activity_id);
CREATE INDEX idx_registration_user ON registration(user_name);
