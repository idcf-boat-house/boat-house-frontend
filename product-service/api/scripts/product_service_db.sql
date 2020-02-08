create Database product_service_db

use product_service_db

create table FoodCategory
(
	Id int auto_increment,
	Name nvarchar(50) null,
	Description nvarchar(1000) null,
	constraint FoodCategory_pk
		primary key (Id)
);