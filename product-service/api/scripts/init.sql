create Database BoatHouse;
use BoatHouse;
DROP TABLE IF EXISTS `FoodCategory`;
create table FoodCategory ( Id int auto_increment, Name nvarchar(50) null, Description nvarchar(1000) null, constraint FoodCategory_pk primary key (Id) );

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