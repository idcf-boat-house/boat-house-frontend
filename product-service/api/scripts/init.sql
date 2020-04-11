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
INSERT INTO `intropage` VALUES ('intro', '船坞故事', '', '<p>18世纪90年代末的E国是一个战乱的国度，战争中，名为安德烈（Andre）的皇帝所带领的军队撤离到一个不知名的小镇，受伤的皇帝在这里遇上美丽典雅的牧场少女Aviva，Andre虽然受伤，但身为皇帝的Andre依然心系战事，希望能早日重返战场。</p><p>Aviva明白他的想法，除了细心帮他料理伤口，还每天用石头加热烹制菜肴给他吃，因为当地人认为用石头加热烹制食物可以调理身体，对伤口的复原有帮助。后来安德烈（Andre）获得了胜利，为了怀念，Aviva在小镇上开起了一家名为Boat House的小餐馆，里面的菜式全部都是以石头加热烹制的各式菜肴。从此这个小镇改名为南皇后渡口（South Queensferry）。而Boat House餐馆成为了南皇后渡口小镇的标志性建筑。</p>', '', '\0', null, '2020-04-11 11:55:37');

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
ALTER TABLE `BoatHouse`.`user` 
ADD UNIQUE INDEX `account_UNIQUE` (`account` ASC);
INSERT INTO `user` (`id`,`account`,`password`,`age`,`email`) VALUES (1,'admin','96e79218965eb72c92a549dd5a330112',NULL,NULL);
INSERT INTO `user` (`id`,`account`,`password`,`age`,`email`) VALUES (2,'test','96e79218965eb72c92a549dd5a330112',NULL,NULL);
INSERT INTO `user` (`id`,`account`,`password`,`age`,`email`) VALUES (3,'test2','96e79218965eb72c92a549dd5a330112',NULL,NULL);

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

DROP TABLE IF EXISTS `idcf_orders`;
create table `idcf_orders` (
  `id`                bigint        auto_increment
  comment 'primary key',
  `order_id`          varchar(32) CHARACTER SET utf8
  COLLATE utf8_general_ci    not null
  comment 'unique key for orders',
  `user_id`           bigint not null
  comment '用户id',
  `create_time`       datetime      default null
  comment '订单创建时间',
  `update_time`       datetime      default null
  comment '订单修改时间',
  `pay_type`          tinyint(2)    default 1
  comment '1: alipay, 2: wxpay, 3: unionpay',
  `total_amount`      decimal(6, 2) default 0.00
  comment 'total order price',
  `additional_amount` decimal(6, 2) default 0.00
  comment 'order additional price,for example Cost of tableware etc.',
  `order_status`      tinyint(2)    default 0
  comment '-2: expired, -1: rejected, 0: unpaid, 1: paid & order pending, 2: wait for delivery, 3: finished',
  `reason`            varchar(32) CHARACTER SET utf8
  COLLATE utf8_general_ci           default null
  comment '拒单理由',
  `note`              varchar(500) CHARACTER SET utf8
  COLLATE utf8_general_ci           default null
  comment '用户备注',
  primary key order_pk (`id`),
  unique order_unique (`order_id`),
  index order_user_idx (`user_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

DROP table if exists `idcf_order_items`;
create table `idcf_order_items` (
  `id`             bigint                                                                                                                                                                                                                                                                                                                                                                                                                                                                   auto_increment
  comment 'primary key',
  `order_id`       varchar(32) CHARACTER SET utf8
  COLLATE utf8_general_ci         not null
  comment 'unique key for orders',
  `food_id`        int    not null
  comment 'Food table id',
  `food_name`      nvarchar(50) NOT NULL,
  `food_price`     DECIMAL(5, 2) NOT NULL                                                                  DEFAULT 0.00
  comment 'food single price',
  `food_sub_total` decimal(5, 2) not null                                                                                                                                                                                                                                                                                                                                                                                                                                                   default 0.00
  comment 'food sub total price',
  `food_num`       int           not null
  comment 'food number',
  `food_picture`   nvarchar(1000) NULL
  comment 'food intro picture uri',
  primary key order_items_pk (`id`),
  index order_items_idx (`order_id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;