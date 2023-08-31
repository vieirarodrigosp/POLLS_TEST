CREATE TABLE IF NOT EXISTS sessionmanagerdb.assembly (
    assembly_id int NOT NULL AUTO_INCREMENT,
    agenda VARCHAR(100) NOT NULL,
    duration int NOT NULL,
    PRIMARY KEY (assembly_id)
) ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS sessionmanagerdb.poll (
    poll_id int NOT NULL AUTO_INCREMENT,
    assembly_id int NOT NULL,
    start_session TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    end_session TIMESTAMP,
    PRIMARY KEY (poll_id),
    FOREIGN KEY (assembly_id) REFERENCES assembly(assembly_id)
) ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS sessionmanagerdb.user (
    user_id int NOT NULL AUTO_INCREMENT,
    cpf VARCHAR(11) NOT NULL,
    PRIMARY KEY (user_id)
) ENGINE=INNODB;

CREATE TABLE IF NOT EXISTS sessionmanagerdb.user_vote (
    user_vote_id int NOT NULL AUTO_INCREMENT,
    poll_id int NOT NULL,
    user_id int NOT NULL,
    vote_value boolean NOT NULL,
    PRIMARY KEY (user_vote_id),
    FOREIGN KEY (poll_id) REFERENCES poll(poll_id),
    FOREIGN KEY (user_id) REFERENCES user(user_id)
);

INSERT INTO sessionmanagerdb.assembly(agenda, duration) VALUES ('agenda of the meeting test 001', 2);
INSERT INTO sessionmanagerdb.assembly(agenda, duration) VALUES ('agenda of the meeting test 002', 3);
INSERT INTO sessionmanagerdb.assembly(agenda, duration) VALUES ('agenda of the meeting test 003', 4);

INSERT INTO sessionmanagerdb.poll(assembly_id, start_session, end_session) VALUES (1, '2023-08-24 19:06:29', '2023-08-25 19:09:56');
INSERT INTO sessionmanagerdb.poll(assembly_id, start_session, end_session) VALUES (2, '2023-08-25 19:09:56', NULL);
INSERT INTO sessionmanagerdb.poll(assembly_id, start_session, end_session) VALUES (3, '2023-08-25 20:09:56', NULL);

INSERT INTO sessionmanagerdb.user(cpf) VALUES ('60732627010');
INSERT INTO sessionmanagerdb.user(cpf) VALUES ('40399332065');
INSERT INTO sessionmanagerdb.user(cpf) VALUES ('15050620040');

CREATE DATABASE userdb;

CREATE TABLE IF NOT EXISTS userdb.user (
    user_id int NOT NULL AUTO_INCREMENT,
    cpf VARCHAR(11) NOT NULL,
    status boolean NOT NULL,
    PRIMARY KEY (user_id),
    CONSTRAINT tb_uq UNIQUE (cpf)
) ENGINE=INNODB;

INSERT INTO userdb.user(cpf, status) VALUES ('60732627010', true);
INSERT INTO userdb.user(cpf, status) VALUES ('40399332065', false);
INSERT INTO userdb.user(cpf, status) VALUES ('15050620040', true);