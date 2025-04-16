CREATE DATABASE IF NOT EXISTS db_insurance;

create user 'insurance_user01'@'%' identified by 'insur_user';
grant all privileges on db_insurance.* to 'insurance_user01'@'%';

CREATE TABLE IF NOT EXISTS db_insurance.tb_customers (
document_number VARCHAR(14) PRIMARY KEY NOT NULL,
name VARCHAR(255),
customer_type CHAR,
gender VARCHAR(9),
date_of_birth DATE,
email VARCHAR(255),
phone_number VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS db_insurance.tb_products (
product_id INT(20) PRIMARY KEY,
name VARCHAR(255),
created_at TIMESTAMP,
created_by VARCHAR(14),
active BOOLEAN
);

CREATE TABLE IF NOT EXISTS db_insurance.tb_coverages (
coverage_id INT(20) PRIMARY KEY,
type VARCHAR(255),
amount NUMERIC(17,2)
);

CREATE TABLE IF NOT EXISTS db_insurance.tb_assistances (
assistance_id INT(20) PRIMARY KEY,
type VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS db_insurance.tb_amounts (
amount_id INT(20) PRIMARY KEY,
max_amount NUMERIC(17,2),
min_amount NUMERIC(17,2),
suggested_amount NUMERIC(17,2)
);

CREATE TABLE IF NOT EXISTS db_insurance.tb_offers (
offer_id INT(20) PRIMARY KEY,
name VARCHAR(255),
created_at TIMESTAMP,
created_by VARCHAR(14),
active BOOLEAN,
product_id INT(20) REFERENCES tb_products(product_id),
coverage_id INT(20) REFERENCES tb_coverages(coverage_id),
coverage_id2 INT(20) REFERENCES tb_coverages(coverage_id),
coverage_id3 INT(20) REFERENCES tb_coverages(coverage_id),
coverage_id4 INT(20) REFERENCES tb_coverages(coverage_id),
assistance_id INT(20) REFERENCES tb_assistances(assistance_id),
assistance_id2 INT(20) REFERENCES tb_assistances(assistance_id),
amount_id INT(20) REFERENCES tb_amounts(amount_id)
);


CREATE TABLE IF NOT EXISTS db_insurance.tb_quotations (
quotation_id INT(20) PRIMARY KEY,
category VARCHAR(30),
created_at TIMESTAMP,
updated_at TIMESTAMP,
created_by VARCHAR(14),
total_monthly_premium_amount NUMERIC(17,2),
total_coverage_amount NUMERIC(17,2),
coverage_id INT(20) REFERENCES tb_coverages(coverage_id),
assistance_id INT(20) REFERENCES tb_assistances(assistance_id),
document_number VARCHAR(14)  REFERENCES tb_customers(document_number)
);


CREATE TABLE IF NOT EXISTS db_insurance.tb_policies (
insurance_policy_id INT(20) PRIMARY KEY,
created_at TIMESTAMP,
created_by VARCHAR(14),
quotation_id INT(20) REFERENCES tb_quotations(quotation_id)
);