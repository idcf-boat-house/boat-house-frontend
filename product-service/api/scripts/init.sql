create Database BoatHouse;
use BoatHouse;
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
CREATE TABLE `join_us`
(
    `id`       int(11) NOT NULL AUTO_INCREMENT,
    `name`     varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci   DEFAULT NULL,
    `telephone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci   DEFAULT NULL,
    `comment`  varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;
