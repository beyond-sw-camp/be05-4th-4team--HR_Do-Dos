CREATE TABLE IF NOT EXISTS todolist.users
(
    user_no       BIGINT AUTO_INCREMENT,
    user_id       VARCHAR(50)  NOT NULL,
    user_password VARCHAR(100) NOT NULL,
    user_name     VARCHAR(50)  NOT NULL,
    PRIMARY KEY (user_no)
);

CREATE TABLE IF NOT EXISTS todolist.todo
(
    todo_no          BIGINT AUTO_INCREMENT,
    user_no          BIGINT        NOT NULL,
    todo_content     VARCHAR(1000) NOT NULL,
    todo_complete_yn BOOLEAN       NOT NULL,
    todo_delete_yn   BOOLEAN       NOT NULL,
    PRIMARY KEY (todo_no),
    FOREIGN KEY (user_no) REFERENCES users (user_no)
);

CREATE TABLE IF NOT EXISTS todolist.todo_history
(
    history_no    BIGINT AUTO_INCREMENT,
    create_date   DATETIME NOT NULL,
    delete_date   DATETIME,
    complete_date DATETIME,
    todo_no       BIGINT   NOT NULL,
    PRIMARY KEY (history_no),
    FOREIGN KEY (todo_no) REFERENCES todo (todo_no)
);