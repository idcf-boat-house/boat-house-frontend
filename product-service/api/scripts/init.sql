create Database BoatHouse;
use BoatHouse;
create table FoodCategory ( Id int auto_increment, Name nvarchar(50) null, Description nvarchar(1000) null, constraint FoodCategory_pk primary key (Id) );
CREATE TABLE Food (
	Id INT auto_increment,
	CategoryId INT NOT NULL,
	Name nvarchar ( 50 ) NOT NULL,
	Price DECIMAL ( 5, 2 ) NOT NULL DEFAULT 0,
	Description nvarchar ( 1000 ) NULL,
	Picture longblob NULL,
CONSTRAINT food_pk PRIMARY KEY ( Id ),
CONSTRAINT food_category_fk FOREIGN KEY ( CategoryId ) REFERENCES FoodCategory ( Id ));