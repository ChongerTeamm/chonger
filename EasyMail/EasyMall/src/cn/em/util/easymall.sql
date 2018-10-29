-- 创建数据库
create database easymall;

-- 使用该数据库
use easymall;

-- 创建user表
create table user(
id int primary key auto_increment,
username varchar(100),
password varchar(100),
nickname varchar(100),
email varchar(100)
);

-- 插入2条测试数据
insert into user values(null,'admin','123','炒鸡管理员','123@163.com');
insert into user values(null,'张飞','123','管理员','123@163.com');

-- 创建商品种类表
create table prod_category(
id int primary key auto_increment, -- 商品种类id
cname varchar(100) -- 商品种类名称
);

-- 创建商品表
create table prod(
id int primary key auto_increment, -- 商品id
name varchar(100), -- 商品名称
price double, -- 商品价格
cid int, -- 商品种类的id
pnum int, -- 商品数量
imgurl varchar(200), -- 商品图片的url
description varchar(200) -- 商品的描述
);


