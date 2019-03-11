-- Spring Boot启动时会执行schema.slq和data.sql
-- 注意SQL格式千万不要写错。。。。。
CREATE TABLE if not EXISTS Ingredient(
  id VARCHAR(4) not null,
  name VARCHAR(25) not NULL,
  type VARCHAR(10) not null
);

CREATE TABLE if not EXISTS Taco(
  id IDENTITY ,
  name VARCHAR(50) not NULL,
  createdAt TIMESTAMP not null
);

CREATE TABLE if not EXISTS Taco_Ingredients(
  taco bigint not null ,
  ingredient VARCHAR(4) not NULL
);

alter table Taco_Ingredients
ADD FOREIGN KEY (taco) REFERENCES Taco(id);

alter table Taco_Ingredients
ADD FOREIGN KEY (ingredient) REFERENCES Ingredient(id);

CREATE TABLE if not EXISTS Taco_Order(
  id IDENTITY,
  Name VARCHAR(50) not null,
  Street VARCHAR(50) not null,
  City VARCHAR(50) not null,
  State VARCHAR(16) not null,
  Zip VARCHAR(10) not null,
  ccNumber VARCHAR(16) not null,
  ccExpiration VARCHAR(5) not null,
  ccCVV VARCHAR(3) not null,
  placedAt TIMESTAMP not null,
);

CREATE TABLE if not EXISTS Taco_Order_Tacos(
  tacoOrder bigint not NULL ,
  taco bigint not NULL
);

ALTER TABLE Taco_Order_Tacos
ADD FOREIGN KEY (tacoOrder) REFERENCES Taco_Order(id);

ALTER TABLE Taco_Order_Tacos
ADD FOREIGN KEY (taco) REFERENCES Taco(id);

