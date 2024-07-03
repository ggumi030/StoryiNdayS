create table users
(
    id            bigint auto_increment primary key,
    username      varchar(30)  not null,
    user_password      varchar(200) not null,
    introduction  varchar(200) null,
    name          varchar(30)  not null,
    user_state         enum ('ACTIVATION', 'DEACTIVATION', 'BLOCK') default 'ACTIVATION',
    refresh_token varchar(200) not null                        default '',
    user_auth          enum ('USER','ADMIN')                        default 'USER',
    email         varchar(100) not null,
    kakao_id      bigint       null
);

create table post
(
    id        bigint auto_increment primary key,
    title     varchar(200)             not null,
    contents  varchar(200)             not null,
    post_type enum ('NOTICE','NORMAL') not null,
    is_pinned boolean                  not null,
    user_id   bigint                   not null,

    constraint fk_tb_user_tb_post foreign key (user_id) references users (id)
);

create table post_like
(
    id        bigint auto_increment primary key,
    post_like boolean,
    post_id   bigint not null,
    user_id   bigint not null,


    constraint fk_tb_user_tb_postLike foreign key (user_id) references users (id),
    constraint fk_tb_post_tb_postLike foreign key (post_id) references post (id)
);
