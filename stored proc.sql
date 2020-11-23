------------------------------------------------------------------------------------------------
                                    carshop.cars
------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION carshop.car_brand(varchar(45)) 
RETURNS SETOF carshop.cars AS $$
SELECT * FROM carshop.cars WHERE brand = $1;
$$ LANGUAGE SQL

SELECT * FROM carshop.car_brand('BMW');


CREATE OR REPLACE FUNCTION carshop.car_model(varchar(45)) 
RETURNS SETOF carshop.cars AS $$
SELECT * FROM carshop.cars WHERE model = $1;
$$ LANGUAGE SQL

SELECT * FROM carshop.car_model('S');

CREATE OR REPLACE FUNCTION carshop.car_color(varchar(45)) 
RETURNS SETOF carshop.cars AS $$
SELECT * FROM carshop.cars WHERE color = $1;
$$ LANGUAGE SQL

SELECT * FROM carshop.car_color('Silver');
SELECT * FROM carshop.car_color('Silver') INTERSECT SELECT * FROM carshop.car_brand('Ford');


CREATE OR REPLACE FUNCTION carshop.car_price(numeric) 
RETURNS SETOF carshop.cars AS $$
SELECT * FROM carshop.cars WHERE price = $1;
$$ LANGUAGE SQL

SELECT * FROM carshop.car_price(13.500);


CREATE OR REPLACE FUNCTION carshop.car_condition(varchar(45)) 
RETURNS SETOF carshop.cars AS $$
SELECT * FROM carshop.cars WHERE condition = $1;
$$ LANGUAGE SQL

SELECT * FROM carshop.car_condition('Used');



------------------------------------------------------------------------------------------------
                                    carshop.customers
------------------------------------------------------------------------------------------------


CREATE OR REPLACE FUNCTION carshop.cus_firstname(varchar(45)) 
RETURNS SETOF carshop.customers AS $$
SELECT * FROM carshop.customers WHERE firstname = $1;
$$ LANGUAGE SQL

SELECT * FROM carshop.cus_firstname('Nick');


CREATE OR REPLACE FUNCTION carshop.cus_lastname(varchar(45)) 
RETURNS SETOF carshop.customers AS $$
SELECT * FROM carshop.customers WHERE lastname = $1;
$$ LANGUAGE SQL

SELECT * FROM carshop.cus_lastname('Papadopoulos'); 

CREATE OR REPLACE FUNCTION carshop.cus_country(varchar(45)) 
RETURNS SETOF carshop.customers AS $$
SELECT * FROM carshop.customers WHERE country = $1;
$$ LANGUAGE SQL

SELECT * FROM carshop.cus_country('GR')


CREATE OR REPLACE FUNCTION carshop.cus_gender(varchar(45)) 
RETURNS SETOF carshop.customers AS $$
SELECT * FROM carshop.customers WHERE gender = $1;
$$ LANGUAGE SQL

SELECT * FROM carshop.cus_gender('Other')

------------------------------------------------------------------------------------------------
                                    carshop.sales
------------------------------------------------------------------------------------------------


CREATE OR REPLACE FUNCTION carshop.sales_date(varchar(45)) 
RETURNS SETOF carshop.sales AS $$
SELECT * FROM carshop.sales WHERE date = $1; 
$$ LANGUAGE SQL

SELECT * FROM carshop.sales_date('9/10/2020') 