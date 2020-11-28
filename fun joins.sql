CREATE OR REPLACE FUNCTION carshop.transaction_date(varchar(45)) 
RETURNS TABLE (date varchar,idcars int, brand varchar, model varchar, color varchar, price numeric, condition varchar, idcustomers int, firstname varchar, lastname varchar, country varchar) AS $$

SELECT s.date,r.idcars,r.brand,r.model,r.color,r.price,r.condition,c.idcustomers,c.firstname,c.lastname,c.country FROM carshop.cars AS r
JOIN carshop.sales AS s ON r.idcars = s.idcars 
JOIN carshop.customers AS c ON s.idcustomers = c.idcustomers  WHERE s.date = $1; 
$$ LANGUAGE SQL


SELECT * FROM carshop.transaction_date('8/11/2020') 

--------------------------------------------------------------------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION carshop.transaction_color(varchar(45))
RETURNS TABLE (date varchar,idcars int, brand varchar, model varchar, color varchar, price numeric, condition varchar, idcustomers int, firstname varchar, lastname varchar, country varchar) AS $$
SELECT s.date,r.idcars,r.brand,r.model,r.color,r.price,r.condition,c.idcustomers,c.firstname,c.lastname,c.country FROM carshop.cars AS r
JOIN carshop.sales AS s ON r.idcars = s.idcars 
JOIN carshop.customers AS c ON s.idcustomers = c.idcustomers  WHERE r.color = $1;
$$ LANGUAGE SQL

SELECT * FROM carshop.transaction_color('Red') 

--------------------------------------------------------------------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION carshop.transaction_phone(varchar(45))
RETURNS TABLE (date varchar,idcars int, brand varchar, model varchar, color varchar, price numeric, condition varchar, idcustomers int, firstname varchar, lastname varchar, country varchar) AS $$
SELECT s.date,r.idcars,r.brand,r.model,r.color,r.price,r.condition,c.idcustomers,c.firstname,c.lastname,c.country FROM carshop.cars AS r
JOIN carshop.sales AS s ON r.idcars = s.idcars 
JOIN carshop.customers AS c ON s.idcustomers = c.idcustomers  WHERE c.phone = $1;
$$ LANGUAGE SQL

SELECT * FROM carshop.transaction_phone('6940400404')

--------------------------------------------------------------------------------------------------------------------------------------------


CREATE OR REPLACE FUNCTION carshop.transaction_country(varchar(45))
RETURNS TABLE (date varchar,idcars int, brand varchar, model varchar, color varchar, price numeric, condition varchar, idcustomers int, firstname varchar, lastname varchar, country varchar) AS $$
SELECT s.date,r.idcars,r.brand,r.model,r.color,r.price,r.condition,c.idcustomers,c.firstname,c.lastname,c.country FROM carshop.cars AS r
JOIN carshop.sales AS s ON r.idcars = s.idcars 
JOIN carshop.customers AS c ON s.idcustomers = c.idcustomers  WHERE c.country = $1;
$$ LANGUAGE SQL

SELECT * FROM carshop.transaction_country('ES')

--------------------------------------------------------------------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION carshop.transaction_brand(varchar(45))
RETURNS TABLE (date varchar,idcars int, brand varchar, model varchar, color varchar, price numeric, condition varchar, idcustomers int, firstname varchar, lastname varchar, country varchar) AS $$
SELECT s.date,r.idcars,r.brand,r.model,r.color,r.price,r.condition,c.idcustomers,c.firstname,c.lastname,c.country FROM carshop.cars AS r
JOIN carshop.sales AS s ON r.idcars = s.idcars 
JOIN carshop.customers AS c ON s.idcustomers = c.idcustomers  WHERE r.brand = $1;
$$ LANGUAGE SQL

SELECT * FROM carshop.transaction_brand('Tesla')

--------------------------------------------------------------------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION carshop.transaction_gender(varchar(45))
RETURNS TABLE (date varchar,idcars int, brand varchar, model varchar, color varchar, price numeric, condition varchar, idcustomers int, firstname varchar, lastname varchar, country varchar, gender varchar) AS $$
SELECT s.date,r.idcars,r.brand,r.model,r.color,r.price,r.condition,c.idcustomers,c.firstname,c.lastname,c.country,c.gender FROM carshop.cars AS r
JOIN carshop.sales AS s ON r.idcars = s.idcars 
JOIN carshop.customers AS c ON s.idcustomers = c.idcustomers  WHERE c.gender = $1;
$$ LANGUAGE SQL

SELECT * FROM carshop.transaction_gender('Male')
