insert into user_account (user_id, user_password, nickname, created_at, created_by, modified_at,
                          modified_by)
values ('yoon', '{noop}asdf1234', 'yoon', now(), 'yoon', now(), 'yoon');

insert into user_role_type (id,role_name)
values (1,'ROLE_USER');

insert into article (user_id, title, content, created_by, modified_by, created_at, modified_at)
values ('yoon', '타이틀','내용','yoon','yoon',now(),now());
