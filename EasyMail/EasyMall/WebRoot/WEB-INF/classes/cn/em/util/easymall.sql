-- �������ݿ�
create database easymall;

-- ʹ�ø����ݿ�
use easymall;

-- ����user��
create table user(
id int primary key auto_increment,
username varchar(100),
password varchar(100),
nickname varchar(100),
email varchar(100)
);

-- ����2����������
insert into user values(null,'admin','123','��������Ա','123@163.com');
insert into user values(null,'�ŷ�','123','����Ա','123@163.com');

-- ������Ʒ�����
create table prod_category(
id int primary key auto_increment, -- ��Ʒ����id
cname varchar(100) -- ��Ʒ��������
);

-- ������Ʒ��
create table prod(
id int primary key auto_increment, -- ��Ʒid
name varchar(100), -- ��Ʒ����
price double, -- ��Ʒ�۸�
cid int, -- ��Ʒ�����id
pnum int, -- ��Ʒ����
imgurl varchar(200), -- ��ƷͼƬ��url
description varchar(200) -- ��Ʒ������
);


