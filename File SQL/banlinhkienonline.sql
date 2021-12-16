DROP DATABASE banlinhkienonline;

CREATE DATABASE banlinhkienonline;

USE banlinhkienonline;

     
CREATE TABLE ADMINS (
     admin_id INT AUTO_INCREMENT,
	 user_admin VARCHAR(10) UNIQUE,
	 password_admin VARCHAR(150),
	 fullname_admin NVARCHAR(40),
	 gmail_admin VARCHAR(50) UNIQUE,
     
	 CONSTRAINT AD_maAdmin_PK PRIMARY KEY(admin_id)
);

CREATE TABLE CUSTOMER(
	 customer_id INT AUTO_INCREMENT,
	 user_customer VARCHAR(20) UNIQUE,
	 password_customer VARCHAR(150),
	 fullname_customer NVARCHAR(50),
	 gmail_customer VARCHAR(50) UNIQUE,
     phonenumber_customer VARCHAR(10) UNIQUE,
	 CONSTRAINT CUSTOMER_maCustomer_PK PRIMARY KEY(customer_id)
);

CREATE TABLE SHIPPER(
     shipper_id INT AUTO_INCREMENT,
     user_shipper VARCHAR(20) UNIQUE,
     password_shipper VARCHAR(150),
     fullname_shipper NVARCHAR(50),
     gmail_shipper VARCHAR(50) UNIQUE,
     
     CONSTRAINT SHIPPER_maShipper_PK PRIMARY KEY(shipper_id)
);

CREATE TABLE CATEGORY
(
	 category_id INT AUTO_INCREMENT,
	 category_name NVARCHAR(50),

	 CONSTRAINT CATE_maCategory_PK PRIMARY KEY(category_id)
);
CREATE TABLE SUPPLIER
(
	supplier_id INT AUTO_INCREMENT,
    supplier_name NVARCHAR(50),
    CONSTRAINT SUP_maSUPPLIER_PK PRIMARY KEY(supplier_id)
);

CREATE TABLE PRODUCT
(
	 product_id INT AUTO_INCREMENT,
	 product_Name NVARCHAR(50),
	 quantity INT,
	 product_image VARCHAR(150),
     discount INT,
     unit_price INT,
     description_product varchar(5000),
     category_id INT,
     supplier_id INT,
	 
	 CONSTRAINT PR_maPRODUCT_PK PRIMARY KEY(product_id),
	 CONSTRAINT PR_maCATEGORY_FK FOREIGN KEY (category_id) REFERENCES CATEGORY(category_id) ON DELETE CASCADE,
     CONSTRAINT PR_maSUPPLIER_FK FOREIGN KEY (supplier_id) REFERENCES SUPPLIER(supplier_id) ON DELETE CASCADE
);

CREATE TABLE CART
(
	 customer_id INT,
	 product_id INT,
     quantity INT,
     
	 CONSTRAINT CA_maCART_PK PRIMARY KEY(customer_id, product_id),
	 CONSTRAINT CA_maPRODUCT_FK FOREIGN KEY (product_id) REFERENCES PRODUCT(product_id) ON DELETE NO ACTION,
     CONSTRAINT CA_maCUSTOMER_FK FOREIGN KEY(customer_id) REFERENCES CUSTOMER(customer_id) ON DELETE CASCADE
);

CREATE TABLE ORDERS
(
	 order_id INT AUTO_INCREMENT,
	 address NVARCHAR(100),
	 phone_number VARCHAR(15),
     create_date TIMESTAMP,
     total_amount INT,
	 status_order NVARCHAR(10),
     customer_id INT,
     CONSTRAINT OR_activeORDER_CHK CHECK (status_order IN ('Chưa duyệt','Đã duyệt', 'Đang giao', 'Đã giao')),
	 CONSTRAINT OR_maOR_PK PRIMARY KEY(order_id),
	 CONSTRAINT OR_maCUSTOMER_FK FOREIGN KEY(customer_id) REFERENCES CUSTOMER(customer_id) ON DELETE CASCADE
);

CREATE TABLE ORDERDETAIL(
	 order_id INT,
	 product_id INT,
	 quantity INT(10),
	 amount INT,

	 CONSTRAINT OD_maORDERDETAIL_PK PRIMARY KEY(order_id, product_id),
	 CONSTRAINT OD_maORDER_FK FOREIGN KEY(order_id) REFERENCES ORDERS(order_id) ON DELETE CASCADE,
	 CONSTRAINT OD_maPRODUCT_FK FOREIGN KEY(product_id) REFERENCES PRODUCT (product_id) ON DELETE CASCADE
);

CREATE TABLE DELIVERY(
     order_id INT,
	 shipper_id INT,

	 CONSTRAINT DE_maDELIVERY_PK PRIMARY KEY(order_id, shipper_id),
	 CONSTRAINT DE_maORDER_FK FOREIGN KEY(order_id) REFERENCES ORDERS(order_id) ON DELETE CASCADE,
	 CONSTRAINT DE_maSHIPPER_FK FOREIGN KEY(shipper_id) REFERENCES SHIPPER(shipper_id) ON DELETE NO ACTION
);

INSERT INTO ADMINS(user_admin, password_admin, fullname_admin, gmail_admin)
VALUES("thanhtu","$2y$12$.wETVbrO9pOlsgdD5gnDFe3nSxvAtANiWgE7dws8OlswTexeMEgf6","helo","helo@gmail.com");

INSERT INTO CUSTOMER(user_customer,password_customer,fullname_customer, gmail_customer, phonenumber_customer)
VALUES ("nguoidung","123","Tài Khoản","ahihi@gmail.com","0126459789");

INSERT INTO ORDERS(address,phone_number,create_date, total_amount, status_order, customer_id)
VALUES ("Tam Dan","345","2021-10-15",15000,"Chưa duyệt",1);
INSERT INTO ORDERS(address,phone_number,create_date, total_amount, status_order, customer_id)
VALUES ("Tam Dan","567","2021-10-15",15000,"Chưa duyệt",1);
INSERT INTO ORDERS(address,phone_number,create_date, total_amount, status_order, customer_id)
VALUES ("Tam Dan","897","2021-10-15",15000,"Đã duyệt",1);

INSERT INTO CATEGORY (category_name)
VALUES ('Máy tính');
INSERT INTO CATEGORY (category_name)
VALUES ('Tai nghe');
INSERT INTO CATEGORY (category_name)
VALUES ('Chuột');

INSERT INTO SUPPLIER (supplier_name)
VALUES ('Dell');
INSERT INTO SUPPLIER (supplier_name)
VALUES ('Asus');
INSERT INTO SUPPLIER (supplier_name)
VALUES ('Sony');
INSERT INTO SUPPLIER (supplier_name)
VALUES ('Logitech');

INSERT INTO PRODUCT (category_id,supplier_id, product_name, quantity, product_image,discount, unit_price, description_product)
VALUES ( 1, 1, 'Máy tính Dell Inspiron I3501-5075BLK', 1,'maytinhdelli3501.png', 1, 18290000, '');
INSERT INTO PRODUCT (category_id,supplier_id, product_name, quantity, product_image,discount, unit_price, description_product)
VALUES ( '1', '2', 'Máy tính ASUS E210MA-GJ353T', '20','maytinhasuse210.png', '8', '7790000', '');
INSERT INTO PRODUCT (category_id,supplier_id, product_name, quantity, product_image,discount, unit_price, description_product)
VALUES ( '2', '3', 'Tai nghe chụp tai Sony WH-1000XM4', '20','tainghsonywh1000.png', '8', '6490000', '');
INSERT INTO PRODUCT (category_id,supplier_id, product_name, quantity, product_image,discount, unit_price, description_product)
VALUES ( '3', '4', 'Chuột Gaming Logitech G502 Hero', '20','chuotlogitechg502.png', '8', '1080000', '');





