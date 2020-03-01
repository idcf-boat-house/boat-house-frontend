
use BoatHouse;
DROP TABLE IF EXISTS `intropage`;
CREATE TABLE `intropage` (
  `page_id` varchar(500) DEFAULT NULL,
  `page_title` varchar(500) DEFAULT NULL,
  `page_api_url` varchar(500) DEFAULT NULL,
  `text` text,
  `image` text,
  `deleted` bit(1) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
