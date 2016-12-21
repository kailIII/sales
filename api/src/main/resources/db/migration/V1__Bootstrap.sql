CREATE TABLE invoice (
	id  bigserial not null, 
	emission_date timestamp not null, 
	price numeric(19, 2) not null, 
	issuer_id int8, 
	primary key (id)
);

CREATE TABLE invoice_product (
	id  bigserial not null, 
	quantity int4 not null, 
	invoice_ide int8, 
	product_id int8, 
	primary key (id)
);

CREATE TABLE issuer (
	id  bigserial not null, 
	active boolean not null, 
	document_number varchar(255) not null, 
	email varchar(255) not null, 
	name varchar(255) not null, 
	registration_date timestamp not null, 
	uuid varchar(255) not null, 
	primary key (id)
);

CREATE TABLE product (
	id  bigserial not null, 
	active boolean not null, 
	last_update_date timestamp not null, 
	name varchar(255) not null, 
	price numeric(19, 2) not null, 
	registration_date timestamp not null, 
	uuid varchar(255) not null, 
	version int8, 
	primary key (id)
);

ALTER TABLE product ADD CONSTRAINT idx_uuid UNIQUE (uuid);

ALTER TABLE issuer ADD CONSTRAINT idx_doc_number UNIQUE (document_number);

ALTER TABLE invoice ADD CONSTRAINT FKlrj6qmc5kwbqfbwb8r4d6cnmp FOREIGN KEY (issuer_id) REFERENCES issuer;

ALTER TABLE invoice_product ADD CONSTRAINT FKpkdp86n9skc6bt9re86u0pkjd FOREIGN KEY (invoice_ide) REFERENCES invoice;

ALTER TABLE invoice_product ADD CONSTRAINT FK806bu27uepq9jw1gksvegoqkd FOREIGN KEY (product_id) REFERENCES product;
