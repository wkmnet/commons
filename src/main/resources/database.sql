drop table if exists admin_user;
create table if not exists admin_user(
    `id` bigint primary key AUTO_INCREMENT comment '主健',
    `email` varchar(50) not null unique comment '用户名',
    `password` varchar(50)  comment '用户密码',
    `login_time` datetime comment '登录到期时间',
    `create_time` datetime comment '创建时间'
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
insert into admin_user(email,password,login_time,create_time) values('me@wukm.xin','acfa1278a0543413f2f5a20b98ad01ae','2017-07-29 20:10:00','2017-07-29 20:10:00');