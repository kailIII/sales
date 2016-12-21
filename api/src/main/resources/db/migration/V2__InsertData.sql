INSERT INTO issuer(
	active, 
	document_number, 
	email, 
	name, 
	registration_date, 
	uuid
) VALUES (
	true, '1234567890', 'test@test.com', 'test issuer', now(), 'fc36d396-b920-46b5-b22a-c76fb0786a41'
), (
	true, '0987654321', 'test2@test.com', 'test 2 issuer', now(), 'fc36d396-b920-46b5-b22a-c76fb0786a42'
);

INSERT INTO product(
	active, 
	last_update_date, 
	name, 
	price, 
	registration_date, 
	version,
	uuid
) VALUES (
	true, now(), 'Product 1', 5.25, now(), 1, '5a350db8-c7c3-11e6-9d9d-cec0c932ce01'
), (
	true, now(), 'Product 2', 7.30, now(), 1, '5a350db8-c7c3-11e6-9d9d-cec0c932ce02'
), (
	true, now(), 'Product 3', 10.10, now(), 1, '5a350db8-c7c3-11e6-9d9d-cec0c932ce03'
), (
	true, now(), 'Product 4', 12.75, now(), 1, '5a350db8-c7c3-11e6-9d9d-cec0c932ce04'
), (
	true, now(), 'Product 5', 15.00, now(), 1, '5a350db8-c7c3-11e6-9d9d-cec0c932ce05'
);
