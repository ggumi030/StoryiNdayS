create table users
(
    id            bigint auto_increment primary key,
    username      varchar(30)  not null,
    user_password varchar(200) not null,
    introduction  varchar(200) null,
    name          varchar(30)  not null,
    user_state    enum ('ACTIVATION', 'DEACTIVATION', 'BLOCK') default 'ACTIVATION',
    refresh_token varchar(200) not null                        default '',
    user_auth     enum ('USER','ADMIN')                        default 'USER',
    email         varchar(100) not null,
    kakao_id      bigint       null
);

create table post
(
    id          bigint auto_increment primary key,
    title       varchar(200)             not null,
    contents    varchar(200)             not null,
    post_type   enum ('NOTICE','NORMAL') not null,
    is_pinned   boolean                  not null,
    user_id     bigint                   not null,
    created_at  timestamp default current_timestamp,
    modified_at timestamp default current_timestamp,

    constraint fk_tb_user_tb_post foreign key (user_id) references users (id)
);

create table post_like
(
    id        bigint auto_increment primary key,
    post_like boolean,
    post_id   bigint not null,
    user_id   bigint not null,


    constraint fk_users_post_like foreign key (user_id) references users (id),
    constraint fk_post_post_like foreign key (post_id) references post (id)
);

create table comment
(
    id          bigint auto_increment primary key,
    comment     varchar(200) not null,
    post_id     bigint       not null,
    user_id     bigint       not null,
    created_at  timestamp default current_timestamp,
    modified_at timestamp default current_timestamp,

    constraint fk_user_comment foreign key (user_id) references users (id),
    constraint fk_post_comment foreign key (post_id) references post (id)
);

create table comment_like
(
    id         bigint auto_increment primary key,
    comment_id bigint not null,
    post_id bigint not null,
    user_id bigint not null,
    comment_like boolean,

    constraint fk_comment_comment_like foreign key (comment_id) references comment (id),
    constraint fk_post_comment_like foreign key (post_id) references post (id),
    constraint fk_user_comment_like foreign key (user_id) references users (id)
);
