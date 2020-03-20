create Database BoatHouse;
use BoatHouse;
DROP TABLE IF EXISTS `FoodCategory`;
create table FoodCategory ( Id int auto_increment, Name nvarchar(50) null, Description nvarchar(1000) null, constraint FoodCategory_pk primary key (Id) );
CREATE TABLE Food (
	Id INT auto_increment,
	CategoryId INT NOT NULL,
	Name nvarchar ( 50 ) NOT NULL,
	Price DECIMAL ( 5, 2 ) NOT NULL DEFAULT 0,
	Description nvarchar ( 1000 ) NULL,
	Picture nvarchar ( 1000 ) NULL,
CONSTRAINT food_pk PRIMARY KEY ( Id ),
CONSTRAINT food_category_fk FOREIGN KEY ( CategoryId ) REFERENCES FoodCategory ( Id ));

DROP TABLE IF EXISTS `join_us`;
CREATE TABLE `join_us`
(
    `id`       int(11) NOT NULL AUTO_INCREMENT,
    `name`     varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci   DEFAULT NULL,
    `telephone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci   DEFAULT NULL,
    `comment`  varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4; 

DROP TABLE IF EXISTS `intropage`;
CREATE TABLE `intropage` (
  `page_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `page_title` varchar(500) DEFAULT NULL,
  `page_api_url` varchar(500) DEFAULT NULL,
  `text` text,
  `image` text,
  `deleted` bit(1) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
   PRIMARY KEY (`page_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `intropage` VALUES ('intro', 'this is page title', 'http://www.baidu.com', '[\" <div>第一段  文字</div> \",\"<html> <p>第二段文字</p></html> \"]', '  [\"http://www.baidu.com\",\"https://www.cnblogs.com\"]', '\0', '2020-03-05 12:07:18', '2020-03-05 12:07:18');

DROP TABLE IF EXISTS user;
CREATE TABLE user
(
    id BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    account VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
    password VARCHAR(100) NULL DEFAULT NULL COMMENT '密码',
    age INT(11) NULL DEFAULT NULL COMMENT '年龄',
    email VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
    PRIMARY KEY (id)
);
DROP TABLE IF EXISTS shop_cart;
CREATE TABLE shop_cart (
    `id` VARCHAR(100) NOT NULL,
    `userid` INT(11) NOT NULL,
    `foodid` INT(11) NOT NULL,
    `num` INT(4) NOT NULL,
    `comment` VARCHAR(1000) DEFAULT NULL,
    PRIMARY KEY (id),
    unique key userid_foodid(`userid` ,`foodid`)
);

insert into BoatHouse.FoodCategory (Id,Name,Description)  values (1,'甜品','甜品');
insert into BoatHouse.FoodCategory (Id,Name,Description)  values (2,'主食','主食');
insert into BoatHouse.Food (Id,CategoryID,Name,Price,Description)  values (1,1,'黑森林',18,'黑森林蛋糕三角');
insert into BoatHouse.Food (Id,CategoryID,Name,Price,Description)  values (2,2,'米饭',2,'东北大米');
insert into BoatHouse.Food (Id,CategoryID,Name,Price,Description)  values (3,1,'酸奶',8,'自制无添加酸奶');
insert into BoatHouse.Food (Id,CategoryID,Name,Price,Description)  values (4,2,'意大利面',2,'意面');
