USE registration_system;

-- Insert sample activities
INSERT INTO activity (name, description, start_time, end_time, location, max_participants, current_participants, status) VALUES
('Spring Boot Workshop', 'A hands-on workshop covering Spring Boot fundamentals and best practices', '2026-06-20 09:00:00', '2026-06-20 17:00:00', 'Room 301, Teaching Building', 50, 12, 'ACTIVE'),
('Microservices Architecture Seminar', 'Deep dive into microservices patterns: service discovery, circuit breaker, API gateway', '2026-07-01 14:00:00', '2026-07-01 17:00:00', 'Lecture Hall A', 200, 85, 'ACTIVE'),
('Docker & Kubernetes Training', 'Practical training on containerization and orchestration using Docker and K8s', '2026-07-15 09:00:00', '2026-07-16 17:00:00', 'Computer Lab 201', 30, 28, 'ACTIVE'),
('Summer Sports Meet 2026', 'Annual summer sports competition with track and field events', '2026-08-01 08:00:00', '2026-08-03 18:00:00', 'Main Sports Ground', 500, 120, 'ACTIVE'),
('AI & Machine Learning Forum', 'Forum discussing latest trends in AI, ML, and their applications in education', '2026-06-25 10:00:00', '2026-06-25 16:00:00', 'Conference Hall B', 150, 67, 'ACTIVE');

-- Insert sample registrations
INSERT INTO registration (activity_id, user_name, user_email, user_phone, status) VALUES
(1, 'Zhang Wei', 'zhangwei@example.com', '13800001001', 'CONFIRMED'),
(1, 'Li Na', 'lina@example.com', '13800001002', 'CONFIRMED'),
(1, 'Wang Fang', 'wangfang@example.com', '13800001003', 'CONFIRMED'),
(2, 'Zhao Lei', 'zhaolei@example.com', '13800002001', 'CONFIRMED'),
(2, 'Sun Ming', 'sunming@example.com', '13800002002', 'CONFIRMED'),
(3, 'Zhou Jie', 'zhoujie@example.com', '13800003001', 'CONFIRMED'),
(3, 'Wu Hao', 'wuhao@example.com', '13800003002', 'CONFIRMED'),
(4, 'Liu Yang', 'liuyang@example.com', '13800004001', 'CONFIRMED'),
(4, 'Chen Jing', 'chenjing@example.com', '13800004002', 'CONFIRMED'),
(5, 'Huang Peng', 'huangpeng@example.com', '13800005001', 'CONFIRMED');
