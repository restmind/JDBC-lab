SET FOREIGN_KEY_CHECKS = 0;

CREATE SCHEMA IF NOT EXISTS mydb;
USE mydb;

DROP TABLE IF EXISTS `order`;
DROP TABLE IF EXISTS `client`;
DROP TABLE IF EXISTS client_card;
DROP TABLE IF EXISTS card_type;
DROP TABLE IF EXISTS card_service;
DROP TABLE IF EXISTS service;
DROP TABLE IF EXISTS gym;


CREATE TABLE `order`
(
    id         INT NOT NULL AUTO_INCREMENT,
    client_id  INT NOT NULL,
    gym_id     INT NOT NULL,
    service_id INT NOT NULL,
    PRIMARY KEY (id),
    INDEX fk_order_gym1_idx (gym_id ASC) VISIBLE,
    INDEX fk_order_client1_idx (client_id ASC) VISIBLE,
    CONSTRAINT fk_order_gym1
        FOREIGN KEY (gym_id)
            REFERENCES gym (id),
    CONSTRAINT fk_order_client1
        FOREIGN KEY (client_id)
            REFERENCES `client` (id),
    CONSTRAINT fk_order_service1
        FOREIGN KEY (service_id)
            REFERENCES service (id)
)
    ENGINE = InnoDB;


CREATE TABLE `client`
(
    id      INT         NOT NULL AUTO_INCREMENT,
    name    VARCHAR(45) NOT NULL,
    surname VARCHAR(45) NULL,
    email   VARCHAR(45) NULL,
    PRIMARY KEY (id)
)
    ENGINE = InnoDB;


CREATE TABLE client_card
(
    client_id    INT NOT NULL,
    card_type_id INT NOT NULL,
    PRIMARY KEY (client_id, card_type_id),
    INDEX fk_client_card_card_type1_idx (card_type_id ASC) VISIBLE,
    CONSTRAINT fk_client_card_client1
        FOREIGN KEY (client_id)
            REFERENCES `client` (id),
    CONSTRAINT fk_client_card_card_type1
        FOREIGN KEY (card_type_id)
            REFERENCES card_type (id)
)
    ENGINE = InnoDB;


CREATE TABLE card_type
(
    id   INT         NOT NULL AUTO_INCREMENT,
    name VARCHAR(45) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE INDEX name_UNIQUE (name ASC) VISIBLE
)
    ENGINE = InnoDB;


CREATE TABLE card_service
(
    id           INT NOT NULL AUTO_INCREMENT,
    service_id   INT NOT NULL,
    card_type_id INT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_card_service_service1
        FOREIGN KEY (service_id)
            REFERENCES service (id),
    CONSTRAINT fk_card_service_card_type1
        FOREIGN KEY (card_type_id)
            REFERENCES card_type (id)
)
    ENGINE = InnoDB;


CREATE TABLE service
(
    id    INT           NOT NULL AUTO_INCREMENT,
    name  VARCHAR(45)   NOT NULL,
    price DECIMAL(8, 2) NULL,
    UNIQUE INDEX name_UNIQUE (name ASC) VISIBLE,
    PRIMARY KEY (id)
)
    ENGINE = InnoDB;


CREATE TABLE gym
(
    id       INT          NOT NULL AUTO_INCREMENT,
    location VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
)
    ENGINE = InnoDB;


SET FOREIGN_KEY_CHECKS = 1;


INSERT INTO `client`(name)
VALUES ('Orest'),
       ('Kate'),
       ('Mike');


INSERT INTO card_type(name)
VALUES ('Gold'),
       ('Premium'),
       ('Platinum');


INSERT INTO client_card(client_id, card_type_id)
VALUES (1, 1),
       (2, 2),
       (3, 3);


INSERT INTO gym(location)
VALUES ('Lviv'),
       ('Kyiv'),
       ('Dnipro');


INSERT INTO service(name)
VALUES ('Gym'),
       ('Pool'),
       ('SPA'),
       ('Massage');


INSERT INTO card_service(card_type_id, service_id)
VALUES (1, 1),
       (3, 1),
       (2, 1),
       (1, 2),
       (1, 3);

INSERT INTO `order` (client_id,
                     gym_id, service_id)
VALUES (1, 1, 1),
       (2, 3, 4),
       (3, 2, 1);

