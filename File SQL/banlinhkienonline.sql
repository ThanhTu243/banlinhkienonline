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
    supplier_image VARCHAR(500),
    is_delete VARCHAR(20),
	CONSTRAINT OR_isDeleteSUPPLIER_CHK CHECK (is_delete IN ('NO','YES')),
    CONSTRAINT SUP_maSUPPLIER_PK PRIMARY KEY(supplier_id)
);

CREATE TABLE PRODUCT
(
	 product_id INT AUTO_INCREMENT,
	 product_name NVARCHAR(50),
	 quantity INT,
	 product_image VARCHAR(500),
     productimage_description VARCHAR(500),
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
     total_amount LONG,
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
	 amount LONG,
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
VALUES ("Tam Dan","345","2021-10-15",92233,"Chưa duyệt","Đã thanh toán",1);
INSERT INTO ORDERS(address,phone_number,create_date, total_amount, status_order,note, customer_id)
VALUES ("Tam Dan","567","2021-10-15",15000,"Chưa duyệt","Đã thanh toán",1);
INSERT INTO ORDERS(address,phone_number,create_date, total_amount, status_order,note, customer_id)
VALUES ("Tam Dan","897","2021-10-15",15000,"Đã duyệt","Đã thanh toán",1);

INSERT INTO CATEGORY (category_name,is_delete)
VALUES ('Laptop',"NO");
INSERT INTO CATEGORY (category_name,is_delete)
VALUES ('Tai nghe',"NO");
INSERT INTO CATEGORY (category_name,is_delete)
VALUES ('Chuột',"NO");
INSERT INTO CATEGORY(category_name,is_delete)
VALUES('Ổ cứng',"NO");


INSERT INTO SUPPLIER (supplier_name,supplier_image,is_delete)
VALUES ('HP','https://bom.so/1LnqEL',"NO");
INSERT INTO SUPPLIER (supplier_name,supplier_image,is_delete)
VALUES ('Dell','https://bom.so/Zeic2l',"NO");
INSERT INTO SUPPLIER (supplier_name,supplier_image,is_delete)
VALUES ('Asus','https://bom.so/apjzXy',"NO");
INSERT INTO SUPPLIER (supplier_name,supplier_image,is_delete)
VALUES ('Asus','https://bom.so/apjzXy',"NO");

-- HP LAPTOP
INSERT INTO PRODUCT (category_id,supplier_id, product_name, quantity, product_image,productimage_description,discount, unit_price, description_product,is_delete)
VALUES ( 1, 1, 'Laptop HP 14-DQ2055WM 39K15UA', 100,'https://bom.so/QrJEfJ','https://bom.so/ejRBre', 10, 12490000, 'Phủ nhôm bền bỉ, không gian trải nghiệm đủ đầy</br>Hiệu năng lấp đầy nhu cầu người dùng',"NO");
INSERT INTO PRODUCT (category_id,supplier_id, product_name, quantity, product_image,productimage_description,discount, unit_price, description_product,is_delete)
VALUES ( 1, 1, 'Laptop HP 15S-FQ2602TU 4B6D3PA', 100,'https://bom.so/P6Bbhi','https://bom.so/JCDxUm', 0, 16390000, 'Cấu hình ổn định với chip intel thế hệ 11</br>Kích thước màn hình 15,6 inches và độ phân giải Full HD',"NO");
INSERT INTO PRODUCT (category_id,supplier_id, product_name, quantity, product_image,productimage_description,discount, unit_price, description_product,is_delete)
VALUES ( 1, 1, 'Laptop HP Pavilion x360 14-DW1018TU 2H3N6PA', 100,'https://bom.so/6qJgSA','https://bom.so/rL01II', 6, 17990000, 'Màn hình 14 inch nhỏ gọn, hỗ trợ gập lên đến 360 độ</br>Chip Intel 5 thế hệ 11, ổ cứng SSD 512GB',"NO");
INSERT INTO PRODUCT (category_id,supplier_id, product_name, quantity, product_image,productimage_description,discount, unit_price, description_product,is_delete)
VALUES ( 1, 1, 'Laptop HP 15-DY2045 2Q1H3UA', 100,'https://bom.so/pNK7gy','https://bom.so/AbzTeF', 6, 17490000, 'Thiết kế cứng cáp, trải nghiệm hình ảnh không gian lớn</br>Chip Intel thế hệ 11 và RAM lớn cho sức mạnh ấn tượng, pin thời lượng cực lâu',"NO");
INSERT INTO PRODUCT (category_id,supplier_id, product_name, quantity, product_image,productimage_description,discount, unit_price, description_product,is_delete)
VALUES ( 1, 1, 'Laptop HP Probook 430 G8 2H0N6PA', 100,'https://bom.so/9lI02c','https://bom.so/jcXXXb', 0, 18590000, 'Laptop HP Probook 430 G8- Thiết kế màu bạc sang trọng, gọn nhẹ</br>Hỗ trợ đầy đủ các cổng kết nối</br>Màn hình rộng full HD và viền màn hình siêu mỏng</br>Dung lượng pin lớn - Kết nối không dây siêu tốc',"NO");
INSERT INTO PRODUCT (category_id,supplier_id, product_name, quantity, product_image,productimage_description,discount, unit_price, description_product,is_delete)
VALUES ( 1, 1, 'Laptop HP Envy 13 AQ1022TU 8QN69PA', 100,'https://bom.so/r8EdhU','https://bom.so/q25XbL', 0, 22690000, '',"NO");
INSERT INTO PRODUCT (category_id,supplier_id, product_name, quantity, product_image,productimage_description,discount, unit_price, description_product,is_delete)
VALUES ( 1, 1, 'Laptop HP Zbook Firefly 14 G8 1A2F1AV', 100,'https://bom.so/FVWYUk','https://bom.so/MM23wQ', 10, 31490000, 'Thiết kế mỏng nhẹ, màn hình 14 inch nhỏ gọn, độ sáng cao</br>Hiệu năng ổn định với chip Intel thế hệ 11, SSD 512GB',"NO");
INSERT INTO PRODUCT (category_id,supplier_id, product_name, quantity, product_image,productimage_description,discount, unit_price, description_product,is_delete)
VALUES ( 1, 1, 'Laptop HP 340S G7 36A37PA', 100,'https://bom.so/f3zD2o','https://bom.so/SptA1I', 5, 20890000, 'Thiết kế gọn nhẹ, màn hình 14 inch nhỏ gọn, hiển thị sắc nét</br>Hiệu năng siêu mạnh với CPU i7 thế hệ 10, SSD dung lượng cao',"NO");
INSERT INTO PRODUCT (category_id,supplier_id, product_name, quantity, product_image,productimage_description,discount, unit_price, description_product,is_delete)
VALUES ( 1, 1, 'Laptop HP 348 G7 9PH23PA', 100,'https://bom.so/fl7m7W','https://bom.so/YXcaSr', 3, 22130000, 'Bộ não Intel Core i7 10510U, Ram DDR4 8GB</br>Siêu bền bỉ tiêu chuẩn quân đội, bảo mật an toàn',"NO");
-- Dell Laptop
INSERT INTO PRODUCT (category_id,supplier_id, product_name, quantity, product_image,productimage_description,discount, unit_price, description_product,is_delete)
VALUES ( 1, 2, 'Laptop Dell Inspiron 3501 5580BLK', 100,'https://bom.so/PcOhwD','https://bom.so/w5pPd1', 7, 18590000, 'Thiết kế gọn nhẹ, màn hình kích thước lớn</br>Hiệu năng mượt cùng viên pin đủ dùng',"NO");
INSERT INTO PRODUCT (category_id,supplier_id, product_name, quantity, product_image,productimage_description,discount, unit_price, description_product,is_delete)
VALUES ( 1, 2, 'LAPTOP DELL INSPIRON 3505 i3505-A542BLK', 100,'https://bom.so/p2tC9S','https://bom.so/yh8rEU', 12, 16690000, 'Thiết kế mỏng nhẹ, màn hình kích thước lớn với viền siêu mỏng</br>Hiệu năng ổn định trong tầm giá với con chip AMD Ryzen 5 và SSD PCIE',"NO");
INSERT INTO PRODUCT (category_id,supplier_id, product_name, quantity, product_image,productimage_description,discount, unit_price, description_product,is_delete)
VALUES ( 1, 2, 'Laptop Dell Inspiron 7306 N3I5202W', 100,'https://bom.so/C3arw5','https://bom.so/pm4mfH', 6, 26590000, 'Màn hình FHD 13.3 inch nhỏ gọn, tần số quét 60 Hz, góc cạnh bo tròn</br>Hiệu năng mạnh mẽ với Intel Core i5-1135G7',"NO");
INSERT INTO PRODUCT (category_id,supplier_id, product_name, quantity, product_image,productimage_description,discount, unit_price, description_product,is_delete)
VALUES ( 1, 2, 'Laptop Dell Inspirion 15 3000 L3511 7125BLK', 100,'Laptop_Dell_Inspirion_15_3000_L3511_7125BLK_1.jpg','Laptop_Dell_Inspirion_15_3000_L3511_7125BLK_2.jpg', 8, 21990000, 'Thiết kế nhỏ gọn, màn hình kích thước lớn, khung viền siêu mỏng</br>Hiệu năng mạnh mẽ với chip Intel i7, ổ cứng dung lượng lớn',"NO");

INSERT INTO PRODUCT (category_id,supplier_id, product_name, quantity, product_image,productimage_description,discount, unit_price, description_product,is_delete)
VALUES ( 1, 2, 'Laptop Dell Vostro 3405 V4R53500U003W', 100,'https://bom.so/QJiuh0','https://bom.so/2DjKif', 8, 1699000, '',"NO");
INSERT INTO PRODUCT (category_id,supplier_id, product_name, quantity, product_image,productimage_description,discount, unit_price, description_product,is_delete)
VALUES ( 1, 2, 'Laptop Dell Vostro 3500 P112F002ABL', 100,'https://bom.so/YntcSX','https://bom.so/hLabbJ', 3, 22190000, 'Thiết kế đơn giản với màu đen sang trọng, chất liệu cứng cáp</br>Hiệu năng mạnh mẽ với bộ vi xử lý khỏe, bộ nhớ SSD dung lượng cao',"NO");
INSERT INTO PRODUCT (category_id,supplier_id, product_name, quantity, product_image,productimage_description,discount, unit_price, description_product,is_delete)
VALUES ( 1, 2, 'Laptop Dell Vostro 3400 70234073', 100,'https://bom.so/8kbYAD','https://bom.so/hI36wa', 4, 18590000, 'Mỏng nhẹ tinh tế, màn hình chất lượng</br>Hiệu năng hàng đầu',"NO");
INSERT INTO PRODUCT (category_id,supplier_id, product_name, quantity, product_image,productimage_description,discount, unit_price, description_product,is_delete)
VALUES ( 1, 2, 'Laptop Dell Vostro 3510 P112F002BBL', 100,'https://bom.so/dwldjN','https://bom.so/cFJJXs', 4, 21990000, 'Thiết kế gọn gàng, mỏng nhẹ</br>Cấu hình mạnh mẽ, ổn định</br>Hình ảnh đặc sắc, âm thanh ấn tượng',"NO");
INSERT INTO PRODUCT (category_id,supplier_id, product_name, quantity, product_image,productimage_description,discount, unit_price, description_product,is_delete)
VALUES ( 1, 2, 'Laptop Dell Vostro 5510 70253901', 100,'https://bom.so/zCYSYw','https://bom.so/ltyR7v', 5, 21690000, 'Kiểu dáng tinh tế, màn hình rộng lớn</br>Hiệu năng phù hợp công việc kiêm học tập',"NO");
INSERT INTO PRODUCT (category_id,supplier_id, product_name, quantity, product_image,productimage_description,discount, unit_price, description_product,is_delete)
VALUES ( 1, 2, 'Laptop Dell Vostro 5402 V5402A', 100,'https://bom.so/cvyjAU','https://bom.so/xa4Qey', 5, 21790000, 'Laptop mỏng nhẹ, màn hình 14 inch nhỏ gọn</br>Hiệu năng mượt mà với chip Intel thế hệ 11',"NO");

INSERT INTO PRODUCT (category_id,supplier_id, product_name, quantity, product_image,productimage_description,discount, unit_price, description_product,is_delete)
VALUES ( 1, 2, 'Laptop Dell Gaming G5 5500 P89F003', 100,'https://bom.so/DnFgd9','https://bom.so/1AxVab', 3, 33610000, '',"NO");
INSERT INTO PRODUCT (category_id,supplier_id, product_name, quantity, product_image,productimage_description,discount, unit_price, description_product,is_delete)
VALUES ( 1, 2, 'Laptop Dell Gaming G15 5511 P105F006BGR', 100,'https://bom.so/mVN4iF','https://bom.so/3QlgcH', 7, 33990000, '',"NO");
INSERT INTO PRODUCT (category_id,supplier_id, product_name, quantity, product_image,productimage_description,discount, unit_price, description_product,is_delete)
VALUES ( 1, 2, 'Laptop Dell Gaming G15 5515 P105F004DGR', 100,'https://bom.so/oElsL8','https://bom.so/RIvLEo', 5, 27190000, 'Thiết kế đậm chất gaming, màn hình 120Hz mượt mà</br>Hoạt động ổn định với CPU AMD kết hợp card đồ họa chuyên nghiệp',"NO");
INSERT INTO PRODUCT (category_id,supplier_id, product_name, quantity, product_image,productimage_description,discount, unit_price, description_product,is_delete)
VALUES ( 1, 2, 'Laptop Dell Alienware M15 R6 70262923', 100,'https://bom.so/swU6bM','https://bom.so/jz0Xos', 7, 64090000, 'Thiết kế cao cấp với khả năng tản nhiệt vượt trội</br>Màn hình chuẩn 2K sắc nét, cùng hiệu suất vượt trội',"NO");
INSERT INTO PRODUCT (category_id,supplier_id, product_name, quantity, product_image,productimage_description,discount, unit_price, description_product,is_delete)
VALUES ( 1, 2, 'Laptop Dell Gaming G3 G3500CW', 100,'https://bom.so/bYX2AS','https://bom.so/xrD1ND', 7, 2590000, 'Thiết kế gọn nhẹ, màn hình kích thước lớn</br>Hiệu năng mượt cùng viên pin đủ dùng',"NO");

-- ASUS VIVOBOOK
INSERT INTO PRODUCT (category_id,supplier_id, product_name, quantity, product_image,productimage_description,discount, unit_price, description_product,is_delete)
VALUES ( 1, 3, 'Laptop ASUS VivoBook 15 A515EA', 100,'https://bom.so/2uhKty','https://bom.so/ACia2v', 0, 15360000, '',"NO");
INSERT INTO PRODUCT (category_id,supplier_id, product_name, quantity, product_image,productimage_description,discount, unit_price, description_product,is_delete)
VALUES ( 1, 3, 'Laptop ASUS VivoBook X415MA-BV088T', 100,'https://bom.so/3qPVXq','https://bom.so/73G95C', 0, 9390000, '',"NO");
INSERT INTO PRODUCT (category_id,supplier_id, product_name, quantity, product_image,productimage_description,discount, unit_price, description_product,is_delete)
VALUES ( 1, 3, 'Laptop ASUS VivoBook Flip TM420IA-EC031T', 100,'https://bom.so/jT7XSC','https://bom.so/5Tc3XB', 0, 17190000, 'Thiết kế mỏng nhẹ, bản lề quay 360 ấn tượng',"NO");
INSERT INTO PRODUCT (category_id,supplier_id, product_name, quantity, product_image,productimage_description,discount, unit_price, description_product,is_delete)
VALUES ( 1, 3, 'Laptop ASUS Vivobook R565EA-UH31T', 100,'https://bom.so/1BoTZT','https://bom.so/BRAPak', 16, 12490000, 'Thiết kế gọn nhẹ, màn hình kích thước lớn</br>Hiệu năng mượt cùng viên pin đủ dùng',"NO");
INSERT INTO PRODUCT (category_id,supplier_id, product_name, quantity, product_image,productimage_description,discount, unit_price, description_product,is_delete)
VALUES ( 1, 3, 'Laptop ASUS VivoBook Flip 14 TM420IA-EC227T', 100,'https://bom.so/qKDnhV','https://bom.so/Kw2k0K', 4, 19190000, 'Sử dụng CPU đến từ AMD</br>Màn hình viền mỏng, gọn nhẹ ấn tượng</br>Màu sắc cá tính, sang trọng, trọng lượng nhẹ dễ dàng di chuyển',"NO");
INSERT INTO PRODUCT (category_id,supplier_id, product_name, quantity, product_image,productimage_description,discount, unit_price, description_product,is_delete)
VALUES ( 1, 3, 'Laptop ASUS VivoBook S433EA', 100,'https://bom.so/05UwtH','https://bom.so/tv7QDq', 0, 19490000, 'Kích thước nhỏ gọn, màn hình NanoEdge 14 inch Full HD sắc nét</br>Hỗ trợ nhiều cổng kết nối, công nghệ ASUS SonicMaster sống động, pin 3 Cells 50WHrs',"NO");
INSERT INTO PRODUCT (category_id,supplier_id, product_name, quantity, product_image,productimage_description,discount, unit_price, description_product,is_delete)
VALUES ( 1, 3, 'Laptop ASUS VivoBook M513UA-L1240T', 100,'https://bom.so/niZw4Q','https://bom.so/uSMPhp', 7, 19690000, 'Thiết kế mỏng nhẹ, linh hoạt cùng màu ánh kim thanh lịch</br>Hiệu năng khỏe khoắn với chip AMD Ryzen, ổ cứng SSD đọc ghi dữ liệu tốc độ cao',"NO");
INSERT INTO PRODUCT (category_id,supplier_id, product_name, quantity, product_image,productimage_description,discount, unit_price, description_product,is_delete)
VALUES ( 1, 3, 'Laptop ASUS VivoBook 14 A415EA-EB360T', 100,'https://bom.so/DiuzJv','https://bom.so/vKrTDg', 7, 18290000, 'Trọng lượng chỉ 1.4kg, màn hình NanoEdge 14 inches</br>Sở hữu hiệu năng tốt: Intel Core-i5, VGA Iris Xe Graphics, 8GB RAM',"NO");

-- ASUS ZENBOOK
INSERT INTO PRODUCT (category_id,supplier_id, product_name, quantity, product_image,productimage_description,discount, unit_price, description_product,is_delete)
VALUES ( 1, 3, 'Laptop ASUS Zenbook UX425EA', 100,'https://bom.so/WuFMe5','https://bom.so/AqBYU6', 7, 23390000, 'Vẻ ngoài hiện đại và cứng cáp với màn hình 14 inch FHD</br>Vi xử lý mạnh mẽ kèm tiện ích phần cứng lẫn phần mềm',"NO");
INSERT INTO PRODUCT (category_id,supplier_id, product_name, quantity, product_image,productimage_description,discount, unit_price, description_product,is_delete)
VALUES ( 1, 3, 'Laptop ASUS ZenBook UX325EA-KG363T', 100,'https://bom.so/pRsBmB','https://bom.so/VNOYCJ', 4, 23290000, 'Bộ vi xử lý vượt trội giúp nâng cao hiệu suất công việc</br>Thiết kế gọn nhẹ, thanh lịch',"NO");
INSERT INTO PRODUCT (category_id,supplier_id, product_name, quantity, product_image,productimage_description,discount, unit_price, description_product,is_delete)
VALUES ( 1, 3, 'Laptop ASUS ZenBook Flip UX363EA', 100,'https://bom.so/1wBZOd','https://bom.so/jaMTo8', 2, 26690000, 'Thiết kế cứng cáp & linh hoạt với màn hình xoay ngược 360 độ</br>Vi xử lý Intel Core i5-1135G7, RAM 8GB và 512GB SSD cho hiệu năng làm việc tốt',"NO");
INSERT INTO PRODUCT (category_id,supplier_id, product_name, quantity, product_image,productimage_description,discount, unit_price, description_product,is_delete)
VALUES ( 1, 3, 'Laptop ASUS Zenbook Duo 14 UX482EG', 100,'https://bom.so/bD6Vr5','https://bom.so/uPqI3Q', 8, 32990000, 'Thiết kế màn hình kép ScreenPad™ Plus với độ phân giải Full HD sắc nét</br>Vi xử lý Intel Core i5-1135G7 cùng đồ họa GeForce MX450 2GB vận hành mạnh mẽ',"NO");

-- ASUS GAMING 
INSERT INTO PRODUCT (category_id,supplier_id, product_name, quantity, product_image,productimage_description,discount, unit_price, description_product,is_delete)
VALUES ( 1, 3, 'Laptop ASUS Gaming ROG Strix G15 G513IH-HN015T', 100,'https://bom.so/ty1hJ4','https://bom.so/dDM10I', 5, 22690000, 'Thiết kế sang trọng, logo Gaming ROG, viền màn hình siêu mỏng</br>Hoạt động ổn định với chip H, hệ thống 2 loa chất lượng',"NO");
INSERT INTO PRODUCT (category_id,supplier_id, product_name, quantity, product_image,productimage_description,discount, unit_price, description_product,is_delete)
VALUES ( 1, 3, 'Laptop ASUS Gaming ROG Zephyrus G14 GA401QH-HZ035T', 100,'https://bom.so/VoZoys','https://bom.so/7fUJ64', 4, 27590000, 'Thiết kế độc đáo, trọng lượng nhẹ</br>Hiệu năng mạnh mẽ với chip xử lý nhanh chóng, dung lượng RAM và ROM lớn',"NO");
INSERT INTO PRODUCT (category_id,supplier_id, product_name, quantity, product_image,productimage_description,discount, unit_price, description_product,is_delete)
VALUES ( 1, 3, 'Laptop ASUS Gaming FX506LH-HN002T', 100,'https://bom.so/g5EYe1','https://bom.so/PYPJwl', 5, 20890000, 'Kiểu dáng gaming sang trọng, màn hình hỗ trợ 4K, tần số quét 144 Hz</br>Sức mạnh ấn tượng bởi Core i5-10300H, GeForce GTX 1650, 8 GB RAM',"NO");
INSERT INTO PRODUCT (category_id,supplier_id, product_name, quantity, product_image,productimage_description,discount, unit_price, description_product,is_delete)
VALUES ( 1, 3, 'Laptop Asus TUF Gaming F15 FX506HC-HN002T', 100,'https://bom.so/Ef6NkX','https://bom.so/spCcvC', 7, 24390000, 'Hiệu năng đỉnh cao, tần số quét 144Hz</br>Bền bỉ với dung lượng pin dài, tính năng tản nhiệt hiệu quả',"NO");
INSERT INTO PRODUCT (category_id,supplier_id, product_name, quantity, product_image,productimage_description,discount, unit_price, description_product,is_delete)
VALUES ( 1, 3, 'Laptop ASUS TUF Gaming FA506IU-AL127T', 100,'https://bom.so/lTp6jy','https://bom.so/FcE1Ki', 3, 25990000, 'Thiết kế sang trọng chất riêng, độ bền đạt chuẩn quân đội Mỹ</br>Màn hình rộng 15.6 inch Full HD 144Hz, Card NVIDIA GeForce GTX 1660Ti 6GB ',"NO");
INSERT INTO PRODUCT (category_id,supplier_id, product_name, quantity, product_image,productimage_description,discount, unit_price, description_product,is_delete)
VALUES ( 1, 3, 'Laptop ASUS TUF DASH F15 FX516PC-HN002T', 100,'https://bom.so/R06w33','https://bom.so/cHH0HX', 7, 23890000, '',"NO");
INSERT INTO PRODUCT (category_id,supplier_id, product_name, quantity, product_image,productimage_description,discount, unit_price, description_product,is_delete)
VALUES ( 1, 3, 'Laptop ASUS ExpertBook B9400CEA-KC0790T', 100,'https://bom.so/7ISsER','https://bom.so/rOiZnz', 3, 36990000, 'Thiết kế gọn nhẹ, màn hình kích thước lớn</br>Hiệu năng mượt cùng viên pin đủ dùng',"NO");
INSERT INTO PRODUCT (category_id,supplier_id, product_name, quantity, product_image,productimage_description,discount, unit_price, description_product,is_delete)
VALUES ( 1, 3, 'Laptop Asus ExpertBook B5302FEA LF0749W', 100,'https://bom.so/oyEBsB','https://bom.so/QSFFPV', 7, 2590000, '',"NO");


INSERT INTO PRODUCT (category_id,supplier_id, product_name, quantity, product_image,productimage_description,discount, unit_price, description_product,is_delete)
VALUES ( 1, 2, 'Laptop Dell Gaming G3 G3500CW', 100,'hinh1','hinh2', 7, 2590000, 'Thiết kế gọn nhẹ, màn hình kích thước lớn</br>Hiệu năng mượt cùng viên pin đủ dùng',"NO");





