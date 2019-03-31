create TABLE IF NOT EXISTS  products
(
	uuid uuid not null,
	name varchar(255),
	category_uuid uuid
);