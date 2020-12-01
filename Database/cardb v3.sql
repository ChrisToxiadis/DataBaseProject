﻿CREATE SCHEMA carshop;

CREATE table carshop.cars(
  idcars INT PRIMARY KEY,
  brand VARCHAR(45) NOT NULL,
  model VARCHAR(45) NOT NULL,
  color varchar(45) NOT NULL
  constraint c_color check (color in ('Silver','Black','White','Red','Blue')),
  price numeric CONSTRAINT positive_price CHECK (price > 0),
  condition VARCHAR(45) NOT NULL,
  constraint c_condition check (condition in ('New','Used'))
  );

CREATE TABLE carshop.customers (
  idcustomers INT NOT NULL,
  firstname VARCHAR(45) NOT NULL,
  lastname VARCHAR(45) NOT NULL,
  phone VARCHAR(45) NOT NULL,
  country VARCHAR(45) NOT NULL,
  constraint c_country check (country in ('GR','CY','ES','IT')),
  gender VARCHAR(45) NOT NULL,
  constraint g_gender check (gender in ('Female','Male')),
  PRIMARY KEY (idcustomers),
  UNIQUE (phone)
  );

CREATE TABLE carshop.sales (
  date VARCHAR(45) NOT NULL,
  idcars INT NOT NULL,
  idcustomers INT NOT NULL,
  
  CONSTRAINT fk_idcar
  FOREIGN KEY(idcars)
  REFERENCES carshop.cars(idcars)ON DELETE CASCADE,

  CONSTRAINT fk_idcustomers
  FOREIGN KEY(idcustomers)
  REFERENCES carshop.customers(idcustomers) ON DELETE CASCADE,
  CONSTRAINT p_key PRIMARY KEY(idcars,idcustomers)
  );
