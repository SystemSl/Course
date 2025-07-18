CREATE TYPE "kitchen_type_enum" AS ENUM (
  'ITALIAN',
  'CHINESE',
  'JAPANESE',
  'INDIAN',
  'FRENCH',
  'OTHER'
);

CREATE TABLE visitor_table (
id BIGSERIAL PRIMARY KEY,
name VARCHAR(60),
age DECIMAL(3) NOT NULL,
gender VARCHAR(1) NOT NULL
);

CREATE TABLE restaurant_table (
id BIGSERIAL PRIMARY KEY,
name VARCHAR(60) NOT NULL,
description TEXT,
kitchen_type kitchen_type_enum NOT NULL,
average_bill DECIMAL(10) NOT NULL,
rating DECIMAL(2, 1)
);

CREATE TABLE review_table (
visitor_id BIGSERIAL NOT NULL,
restaurant_id BIGSERIAL NOT NULL,
PRIMARY KEY (visitor_id, restaurant_id),
rating DECIMAL(1) NOT NULL,
comment TEXT
);

ALTER TABLE "review_table" ADD FOREIGN KEY ("visitor_id") REFERENCES "visitor_table" ("id") on delete cascade on update cascade;
ALTER TABLE "review_table" ADD FOREIGN KEY ("restaurant_id") REFERENCES "restaurant_table" ("id") on delete cascade on update cascade;