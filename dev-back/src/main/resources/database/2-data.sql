INSERT INTO users (user_id, user_password, user_name)
VALUES ('sampleId', '1234', 'Alice Johnson'),
       ('qwerty9803', 'test123123!', 'Bob Smith'),
       ('awesome9carol', 'poiuy098!', 'Carol Williams'),
       ('david198y', 'erty$5678', 'David Jones'),
       ('evetdcv2', 'xcv#@ljk', 'Eve Brown'),
       ('franksxtg2', 'hjk@@gfdjkl', 'Frank Davis');

INSERT INTO todo (user_no, todo_content, todo_complete_yn, todo_delete_yn)
VALUES ('1', 'JPA 공부하기', false, false),
       ('1', '운동하기', false, false),
       ('1', '세번째 할 일', false, false),
       ('1', '네번째 할 일', false, false),
       ('1', '다섯번째 할 일', false, false),
       ('1', '여섯번째 할 일', false, false),
       ('2', '스프링 공부하기', false, false),
       ('2', '고량주 야무지게 먹기', false, false),
       ('3', '정보처리기사 공부', false, false),
       ('4', 'AWS 공부', false, false),
       ('5', 'CI/CD 구축하기', true, false),
       ('6', '도커와 쿠버네티스 공부', true, false);

INSERT INTO todo_history (create_date, delete_date, complete_date, todo_no)
VALUES ('2024-04-30 08:00:00', '2024-04-30 08:30:00', '2024-04-30 08:15:00', 1),
       ('2024-04-30 08:30:00', '2024-04-30 09:00:00', '2024-04-30 08:45:00', 2),
       ('2024-04-30 10:00:00', '2024-04-30 10:30:00', '2024-04-30 10:15:00', 3),
       ('2024-04-30 11:00:00', '2024-04-30 11:30:00', '2024-04-30 11:15:00', 4),
       ('2024-04-30 12:00:00', '2024-04-30 12:30:00', '2024-04-30 12:15:00', 5),
       ('2024-04-30 13:00:00', '2024-04-30 13:30:00', '2024-04-30 13:15:00', 6);

