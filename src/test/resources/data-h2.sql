insert into users(username, user_password, name, email) values('ggumi','1234','김꾸미','ggumi@gmail.com');
insert into users(username, user_password, name, email) values('ggumi2','1234','김꾸미2','ggumi2@gmail.com');


insert into post(title, contents, post_type, is_pinned, user_id) values ('title','contents','NORMAL',false,1);
insert into post(title, contents, post_type, is_pinned, user_id) values ('title','contents','NORMAL',false,1);
insert into post(title, contents, post_type, is_pinned, user_id) values ('title','contents','NORMAL',false,1);

insert into post_Like(post_like, post_id, user_id) values (true,1,2);
insert into post_Like(post_like, post_id, user_id) values (true,2,2);
insert into post_Like(post_like, post_id, user_id) values (true,3,2);

insert into comment(comment, post_id, user_id) values ('comment',1,1);
insert into comment(comment, post_id, user_id) values ('comment',1,1);
insert into comment(comment, post_id, user_id) values ('comment',1,1);

insert into comment_like(comment_id, post_id, user_id, comment_like) values (1,1,2,true);
insert into comment_like(comment_id, post_id, user_id, comment_like) values (2,1,2,true);
insert into comment_like(comment_id, post_id, user_id, comment_like) values (3,1,2,true);