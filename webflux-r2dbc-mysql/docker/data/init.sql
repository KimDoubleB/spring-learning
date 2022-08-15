GRANT ALL PRIVILEGES ON *.* TO 'user'@'%';

DROP DATABASE IF EXISTS webflux;
CREATE DATABASE webflux CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

DROP TABLE IF EXISTS webflux.user;
DROP TABLE IF EXISTS webflux.post;

CREATE TABLE webflux.user (
                    id         bigint auto_increment primary key,
                    name       varchar(256) not null,
                    age        int(10) not null,
                    created_at timestamp default CURRENT_TIMESTAMP,
                    updated_at timestamp default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP
);

CREATE TABLE webflux.post (
                    id         bigint auto_increment primary key,
                    title    varchar(500),
                    content    varchar(500),
                    author_id  bigint not null,
                    created_at timestamp default CURRENT_TIMESTAMP,
                    updated_at timestamp default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP
);

ALTER TABLE webflux.post
    ADD FOREIGN KEY (`author_id`) REFERENCES user (`id`);
