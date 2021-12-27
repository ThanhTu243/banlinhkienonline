DROP DATABASE banlinhkienonline;

CREATE DATABASE banlinhkienonline;

USE banlinhkienonline;

CREATE TABLE ACCOUNTS (
     account_id INT AUTO_INCREMENT,
	 username VARCHAR(50) UNIQUE,
	 passwords VARCHAR(150),
     gmail varchar(50) UNIQUE,
     activation_code VARCHAR(100),
     passwordreset_code VARCHAR(50),
     active_account VARCHAR(50),
     provider VARCHAR(10),
     roles VARCHAR(10),
     CONSTRAINT OR_rolesACCOUNT_CHK CHECK (roles IN ('ADMIN','SHIPPER', 'CUSTOMER')),
     CONSTRAINT OR_activeACCOUNT_CHK CHECK (active_account IN ('ACTIVE','NOT ACTIVE')),
	 CONSTRAINT AC_maACC_PK PRIMARY KEY(account_id)
);
     
CREATE TABLE ADMINS (
     admin_id INT AUTO_INCREMENT,
	 user_admin VARCHAR(50) UNIQUE,
	 fullname_admin NVARCHAR(40),
	 gmail_admin VARCHAR(50) UNIQUE,
     is_delete VARCHAR(20),
     CONSTRAINT OR_isDeleteADMINS_CHK CHECK (is_delete IN ('NO','YES')),
	 CONSTRAINT AD_maAdmin_PK PRIMARY KEY(admin_id)
);

CREATE TABLE CUSTOMER(
	 customer_id INT AUTO_INCREMENT,
     user_customer VARCHAR(50) UNIQUE,
	 fullname_customer NVARCHAR(50),
     address NVARCHAR(50),
	 gmail_customer VARCHAR(50) UNIQUE,
     phonenumber_customer VARCHAR(50) UNIQUE,
     is_delete VARCHAR(20),
     CONSTRAINT OR_isDeleteCUSTOMER_CHK CHECK (is_delete IN ('NO','YES')),
	 CONSTRAINT CUSTOMER_maCustomer_PK PRIMARY KEY(customer_id)
);

CREATE TABLE SHIPPER(
     shipper_id INT AUTO_INCREMENT,
     user_shipper VARCHAR(50) UNIQUE,
     fullname_shipper NVARCHAR(50),
	 gmail_shipper VARCHAR(50) UNIQUE,
     is_delete VARCHAR(20),
     CONSTRAINT OR_isDeleteSHIPPER_CHK CHECK (is_delete IN ('NO','YES')),
     CONSTRAINT SHIPPER_maShipper_PK PRIMARY KEY(shipper_id)
);

CREATE TABLE CATEGORY
(
	 category_id INT AUTO_INCREMENT,
	 category_name NVARCHAR(50),
     is_delete VARCHAR(20),
	 CONSTRAINT OR_isDeleteCATEGORY_CHK CHECK (is_delete IN ('NO','YES')),
	 CONSTRAINT CATE_maCategory_PK PRIMARY KEY(category_id)
);
CREATE TABLE SUPPLIER
(
	supplier_id INT AUTO_INCREMENT,
    supplier_name NVARCHAR(50),
    is_delete VARCHAR(20),
	CONSTRAINT OR_isDeleteSUPPLIER_CHK CHECK (is_delete IN ('NO','YES')),
    CONSTRAINT SUP_maSUPPLIER_PK PRIMARY KEY(supplier_id)
);

CREATE TABLE PRODUCT
(
	 product_id INT AUTO_INCREMENT,
	 product_name NVARCHAR(50),
	 quantity INT,
	 product_image VARCHAR(150),
     discount INT,
     unit_price INT,
     description_product varchar(5000),
     category_id INT,
     supplier_id INT,
     is_delete VARCHAR(10),
	 CONSTRAINT OR_isDeletePRODUCT_CHK CHECK (is_delete IN ('NO','YES')),
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
     note NVARCHAR(20),
	 status_order NVARCHAR(10),
     customer_id INT,
     CONSTRAINT OR_activeORDER_CHK CHECK (status_order IN ('Chưa duyệt','Đã duyệt', 'Đang giao', 'Đã giao','Đã hủy')),
	 CONSTRAINT OR_maOR_PK PRIMARY KEY(order_id),
	 CONSTRAINT OR_maCUSTOMER_FK FOREIGN KEY(customer_id) REFERENCES CUSTOMER(customer_id) ON DELETE CASCADE
);

CREATE TABLE ORDERDETAIL(
	 order_id INT,
	 product_id INT,
	 quantity INT(10),
	 amount INT,
     is_delete VARCHAR(10),
	 CONSTRAINT OR_isDeleteORDERDETAIL_CHK CHECK (is_delete IN ('NO','YES')),
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

CREATE TABLE REVIEWS(
	  order_id INT,
      product_id INT,
      customer_id INT,
      comments NVARCHAR(255),
      rating double,
      CONSTRAINT RE_maREVIEWS_PK PRIMARY KEY(order_id, product_id,customer_id),
	  CONSTRAINT RE_maORDER_FK FOREIGN KEY(order_id) REFERENCES ORDERS(order_id),
	  CONSTRAINT RE_maPRODUCT_FK FOREIGN KEY(product_id) REFERENCES PRODUCT(product_id),
      CONSTRAINT RE_maCUSTOMER_FK FOREIGN KEY(customer_id) REFERENCES CUSTOMER(customer_id)
);

INSERT INTO ACCOUNTS(username, passwords,gmail,activation_code,passwordreset_code, active_account,provider, roles) 
VALUES("username1","$2y$12$EQbZdVS0EeEM8dbUIO70BOEUKYaLiTXn9ELFMh1GsyuOAluG2VTA2","username1@gmail.com","123","","ACTIVE","LOCAL","ADMIN");
INSERT INTO ACCOUNTS(username, passwords,gmail,activation_code,passwordreset_code, active_account,provider, roles)  
VALUES("username2","$2y$12$iFvzkWjqz2MT4lQXC9ywO.DA/r7tluPpp0O2qhTvx8irbIPZSPeG2","username2@gmail.com","123","","ACTIVE","LOCAL","SHIPPER");
INSERT INTO ACCOUNTS(username, passwords,gmail,activation_code,passwordreset_code, active_account,provider, roles) 
VALUES("username3","$2y$12$67uynXZwsJLYj1ndul/fD.h/4I.oyeKYjxl2nxik5RDELaxsj1jwu","username3@gmail.com","123","","ACTIVE","LOCAL","CUSTOMER");

INSERT INTO ADMINS(user_admin, fullname_admin,gmail_admin, is_delete)
VALUES("username1","tu","ahihi@gmail.com","NO");

INSERT INTO SHIPPER(user_shipper, fullname_shipper,gmail_shipper, is_delete)
VALUES("username1","tu","ahihi123@gmail.com","NO");

INSERT INTO CUSTOMER(user_customer, fullname_customer, address,gmail_customer,phonenumber_customer, is_delete)
VALUES("username1","tu","Tam Ky","ahihihihi@gmail.com","0123456789","NO");


INSERT INTO ORDERS(address,phone_number,create_date, total_amount, status_order,note, customer_id)
VALUES ("Tam Dan","345","2021-10-15",15000,"Chưa duyệt","Đã thanh toán",1);
INSERT INTO ORDERS(address,phone_number,create_date, total_amount, status_order,note, customer_id)
VALUES ("Tam Dan","567","2021-10-15",15000,"Chưa duyệt","Đã thanh toán",1);
INSERT INTO ORDERS(address,phone_number,create_date, total_amount, status_order,note, customer_id)
VALUES ("Tam Dan","897","2021-10-15",15000,"Đã duyệt","Đã thanh toán",1);

INSERT INTO CATEGORY (category_name,is_delete)
VALUES ('Máy tính',"NO");
INSERT INTO CATEGORY (category_name,is_delete)
VALUES ('Tai nghe',"NO");
INSERT INTO CATEGORY (category_name,is_delete)
VALUES ('Chuột',"NO");

INSERT INTO SUPPLIER (supplier_name,is_delete)
VALUES ('Dell',"NO");
INSERT INTO SUPPLIER (supplier_name,is_delete)
VALUES ('Asus',"NO");
INSERT INTO SUPPLIER (supplier_name,is_delete)
VALUES ('Sony',"NO");
INSERT INTO SUPPLIER (supplier_name,is_delete)
VALUES ('Logitech',"NO");

INSERT INTO PRODUCT (category_id,supplier_id, product_name, quantity, product_image,discount, unit_price, description_product,is_delete)
VALUES ( 1, 1, 'Máy tính Dell Inspiron I3501-5075BLK', 1,'maytinhdelli3501.png', 1, 18290000, '',"NO");
INSERT INTO PRODUCT (category_id,supplier_id, product_name, quantity, product_image,discount, unit_price, description_product,is_delete)
VALUES ( '1', '2', 'Máy tính ASUS E210MA-GJ353T', '20','maytinhasuse210.png', '8', '7790000', '',"NO");
INSERT INTO PRODUCT (category_id,supplier_id, product_name, quantity, product_image,discount, unit_price, description_product,is_delete)
VALUES ( '2', '3', 'Tai nghe chụp tai Sony WH-1000XM4', '20','tainghsonywh1000.png', '8', '6490000', '',"NO");
INSERT INTO PRODUCT (category_id,supplier_id, product_name, quantity, product_image,discount, unit_price, description_product,is_delete)
VALUES ( '3', '4', 'Chuột Gaming Logitech G502 Hero', '20','chuotlogitechg502.png', '8', '1080000', '',"NO");





