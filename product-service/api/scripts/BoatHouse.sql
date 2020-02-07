create Database BoatHouse

create table FoodCategory
(
	Id int auto_increment,
	Name nvarchar(50) null,
	Description nvarchar(1000) null,
	constraint FoodCategory_pk
		primary key (Id)
);