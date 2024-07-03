insert into users(username, user_password, name, email) values('ggumi','1234','김꾸미','ggumi@gmail.com');
insert into post(title, contents, post_type, is_pinned, user_id) values ('title','contents','NORMAL',false,1);
insert into post_Like(post_like, post_id, user_id) values (true,1,1);