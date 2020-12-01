----------------------------------------------------------------------------------------------
SELECT r.idcars,r.brand,c.firstname FROM carshop.cars AS r 
JOIN carshop.sales AS s ON r.idcars = s.idcars 
JOIN carshop.customers AS c ON s.idcustomers = c.idcustomers WHERE firstname = 'Leonardo'
----------------------------------------------------------------------------------------------
SELECT r.idcars,r.brand,c.firstname FROM carshop.cars AS r
JOIN carshop.sales AS s ON r.idcars = s.idcars 
JOIN carshop.customers AS c ON s.idcustomers = c.idcustomers  WHERE r.brand = 'Tesla'
----------------------------------------------------------------------------------------------
SELECT r.idcars,r.color,c.firstname FROM carshop.cars AS r
JOIN carshop.sales AS s ON r.idcars = s.idcars 
JOIN carshop.customers AS c ON s.idcustomers = c.idcustomers  WHERE r.color = 'Silver'
----------------------------------------------------------------------------------------------
SELECT r.idcars,r.brand,c.firstname,s.date FROM carshop.cars AS r
JOIN carshop.sales AS s ON r.idcars = s.idcars 
JOIN carshop.customers AS c ON s.idcustomers = c.idcustomers  WHERE s.date = '8/11/2020'
----------------------------------------------------------------------------------------------