insert into db_user(username, user_password, name, email) values('ggumi','1234','김꾸미','ggumi@gmail.com');
insert into db_user(username, user_password, name, email) values('ggumi2','1234','김꾸미2','ggumi2@gmail.com');
insert into db_user(username, user_password, name, email) values('ggumi3','1234','김꾸미3','ggumi2@gmail.com');
insert into db_user(username, user_password, name, email) values('ggumi4','1234','김꾸미4','ggumi2@gmail.com');
insert into db_user(username, user_password, name, email) values('ggumi5','1234','김꾸미5','ggumi2@gmail.com');
insert into db_user(username, user_password, name, email) values('ggumi6','1234','김꾸미6','ggumi2@gmail.com');
insert into db_user(username, user_password, name, email) values('ggumi7','1234','김꾸미7','ggumi2@gmail.com');
insert into db_user(username, user_password, name, email) values('ggumi8','1234','김꾸미8','ggumi2@gmail.com');
insert into db_user(username, user_password, name, email) values('ggumi9','1234','김꾸미9','ggumi2@gmail.com');
insert into db_user(username, user_password, name, email) values('ggumi10','1234','김꾸미10','ggumi2@gmail.com');
insert into db_user(username, user_password, name, email) values('ggumi11','1234','김꾸미11','ggumi2@gmail.com');


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

insert into follow (follow_check, follow_user_id, followee_user_id) values (true, 'ggumi', 1);
insert into follow (follow_check, follow_user_id, followee_user_id) values (true, 'ggumi', 1);
insert into follow (follow_check, follow_user_id, followee_user_id) values (true, 'ggumi', 1);
insert into follow (follow_check, follow_user_id, followee_user_id) values (true, 'ggumi', 1);
insert into follow (follow_check, follow_user_id, followee_user_id) values (true, 'ggumi', 1);
insert into follow (follow_check, follow_user_id, followee_user_id) values (true, 'ggumi', 2);
insert into follow (follow_check, follow_user_id, followee_user_id) values (true, 'ggumi', 2);
insert into follow (follow_check, follow_user_id, followee_user_id) values (true, 'ggumi', 2);
insert into follow (follow_check, follow_user_id, followee_user_id) values (true, 'ggumi', 2);
insert into follow (follow_check, follow_user_id, followee_user_id) values (true, 'ggumi', 3);
insert into follow (follow_check, follow_user_id, followee_user_id) values (true, 'ggumi', 3);
insert into follow (follow_check, follow_user_id, followee_user_id) values (true, 'ggumi', 3);
insert into follow (follow_check, follow_user_id, followee_user_id) values (true, 'ggumi', 4);
insert into follow (follow_check, follow_user_id, followee_user_id) values (true, 'ggumi', 4);
insert into follow (follow_check, follow_user_id, followee_user_id) values (true, 'ggumi', 5);