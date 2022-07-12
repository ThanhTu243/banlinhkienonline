DROP DATABASE banlinhkienonline;

CREATE DATABASE banlinhkienonline;

USE banlinhkienonline;

CREATE TABLE accounts (
                          account_id INT AUTO_INCREMENT,
                          username VARCHAR(250) UNIQUE,
                          passwords VARCHAR(150),
                          gmail varchar(250) UNIQUE,
                          firstname NVARCHAR(50),
                          lastname NVARCHAR(50),
                          address NVARCHAR(50),
                          phonenumber VARCHAR(50) UNIQUE,
                          activation_code VARCHAR(100),
                          passwordreset_code VARCHAR(50),
                          active_account VARCHAR(50),
                          provider VARCHAR(10),
                          roles VARCHAR(10),
                          CONSTRAINT OR_rolesACCOUNT_CHK CHECK (roles IN ('ADMIN','SHIPPER', 'CUSTOMER')),
                          CONSTRAINT OR_activeACCOUNT_CHK CHECK (active_account IN ('ACTIVE','NOT ACTIVE')),
                          CONSTRAINT AC_maACC_PK PRIMARY KEY(account_id)
);
CREATE TABLE customer(
                         customer_id INT AUTO_INCREMENT,
                         user_customer VARCHAR(250) UNIQUE,
                         firstname_customer NVARCHAR(50),
                         lastname_customer NVARCHAR(50),
                         image_customer NVARCHAR(500),
                         address NVARCHAR(50),
                         gmail_customer VARCHAR(50) UNIQUE,
                         phonenumber_customer VARCHAR(50) UNIQUE,
                         is_delete VARCHAR(20),
                         CONSTRAINT OR_isDeleteCUSTOMER_CHK CHECK (is_delete IN ('NO','YES')),
                         CONSTRAINT CUSTOMER_maCustomer_PK PRIMARY KEY(customer_id)
);
CREATE TABLE deliveryaddress (
                                 deliveryaddress_id INT AUTO_INCREMENT,
                                 fullname NVARCHAR(100),
                                 phone_number VARCHAR(12),
                                 deliveryaddress VARCHAR(500),
                                 is_delete VARCHAR(10),
                                 customer_id INT,
                                 CONSTRAINT DA_isDeleteIMAGE_CHK CHECK (is_delete IN ('NO','YES')),
                                 CONSTRAINT DA_maDELIVERYADDRESS_PK PRIMARY KEY(deliveryaddress_id),
                                 CONSTRAINT DA_maDELIVERYADDRESS_PK FOREIGN KEY (customer_id) REFERENCES customer(customer_id) ON DELETE CASCADE
);

CREATE TABLE category
(
    category_id INT AUTO_INCREMENT,
    category_name NVARCHAR(50),
    is_delete VARCHAR(20),
    CONSTRAINT OR_isDeleteCATEGORY_CHK CHECK (is_delete IN ('NO','YES')),
    CONSTRAINT CATE_maCategory_PK PRIMARY KEY(category_id)
);
CREATE TABLE supplier
(
    supplier_id INT AUTO_INCREMENT,
    supplier_name NVARCHAR(50),
    supplier_image VARCHAR(500),
    is_delete VARCHAR(20),
    CONSTRAINT OR_isDeleteSUPPLIER_CHK CHECK (is_delete IN ('NO','YES')),
    CONSTRAINT SUP_maSUPPLIER_PK PRIMARY KEY(supplier_id)
);

CREATE TABLE product
(
    product_id INT AUTO_INCREMENT,
    product_name NVARCHAR(150),
    quantity INT,
    discount INT,
    unit_price INT,
    description_product varchar(5000),
    category_id INT,
    supplier_id INT,
    is_delete VARCHAR(10),
    CONSTRAINT PR_isDeletePRODUCT_CHK CHECK (is_delete IN ('NO','YES')),
    CONSTRAINT PR_maPRODUCT_PK PRIMARY KEY(product_id),
    CONSTRAINT PR_maCATEGORY_FK FOREIGN KEY (category_id) REFERENCES category(category_id) ON DELETE CASCADE,
    CONSTRAINT PR_maSUPPLIER_FK FOREIGN KEY (supplier_id) REFERENCES supplier(supplier_id) ON DELETE CASCADE
);

CREATE TABLE image(
                      image_id INT AUTO_INCREMENT,
                      image VARCHAR(500),
                      is_delete VARCHAR(10),
                      product_id INT,
                      CONSTRAINT I_isDeleteIMAGE_CHK CHECK (is_delete IN ('NO','YES')),
                      CONSTRAINT I_maIMAGE_PK PRIMARY KEY(image_id),
                      CONSTRAINT I_maPRODUCT_FK FOREIGN KEY (product_id) REFERENCES product(product_id) ON DELETE CASCADE
);

CREATE TABLE cart
(
    cart_id INT AUTO_INCREMENT,
    quantity INT,
    is_delete VARCHAR(10),
    customer_id INT,
    product_id INT,
    CONSTRAINT OR_isDeleteCart_CHK CHECK (is_delete IN ('NO','YES')),
    CONSTRAINT PR_maCART_PK PRIMARY KEY(cart_id),
    CONSTRAINT CA_maPRODUCT_FK FOREIGN KEY (product_id) REFERENCES product(product_id) ON DELETE NO ACTION,
    CONSTRAINT CA_maCUSTOMER_FK FOREIGN KEY(customer_id) REFERENCES customer(customer_id) ON DELETE CASCADE
);

CREATE TABLE orders
(
    order_id INT AUTO_INCREMENT,
    address NVARCHAR(100),
    phone_number VARCHAR(15),
    create_date TIMESTAMP,
    total_amount LONG,
    note NVARCHAR(20),
    payment_method NVARCHAR(20),
    payment_status NVARCHAR(20),
    status_order NVARCHAR(10),
    customer_id INT,
    CONSTRAINT OR_activeORDER_CHK CHECK (status_order IN ('Chưa duyệt','Đã duyệt', 'Đã giao','Đã hủy')),
    CONSTRAINT OR_maOR_PK PRIMARY KEY(order_id),
    CONSTRAINT OR_maCUSTOMER_FK FOREIGN KEY(customer_id) REFERENCES customer(customer_id) ON DELETE CASCADE
);

CREATE TABLE orderdetail(
                            order_id INT,
                            product_id INT,
                            quantity INT(10),
                            amount LONG,
                            is_delete VARCHAR(10),
                            is_review VARCHAR(10),
                            CONSTRAINT OR_isReviewORDERDETAIL_CHK CHECK (is_review IN ('NO','YES')),
                            CONSTRAINT OR_isDeleteORDERDETAIL_CHK CHECK (is_delete IN ('NO','YES')),
                            CONSTRAINT OD_maORDERDETAIL_PK PRIMARY KEY(order_id, product_id),
                            CONSTRAINT OD_maORDER_FK FOREIGN KEY(order_id) REFERENCES orders(order_id) ON DELETE CASCADE,
                            CONSTRAINT OD_maPRODUCT_FK FOREIGN KEY(product_id) REFERENCES product (product_id) ON DELETE CASCADE
);

CREATE TABLE reviews(
                        order_id INT,
                        product_id INT,
                        customer_id INT,
                        comments NVARCHAR(255),
                        rating double,
                        CONSTRAINT RE_maREVIEWS_PK PRIMARY KEY(order_id, product_id,customer_id),
                        CONSTRAINT RE_maORDER_FK FOREIGN KEY(order_id) REFERENCES orders(order_id),
                        CONSTRAINT RE_maPRODUCT_FK FOREIGN KEY(product_id) REFERENCES product(product_id),
                        CONSTRAINT RE_maCUSTOMER_FK FOREIGN KEY(customer_id) REFERENCES customer(customer_id)
);

CREATE TABLE wishlist(
                         wishlist_id INT AUTO_INCREMENT,
                         product_id INT,
                         customer_id INT,
                         is_delete VARCHAR(10),
                         CONSTRAINT WL_isDeleteWishList_CHK CHECK (is_delete IN ('NO','YES')),
                         CONSTRAINT WL_maWISHLIST_PK PRIMARY KEY(wishlist_id),
                         CONSTRAINT WL_maPRODUCT_FK FOREIGN KEY(product_id) REFERENCES product(product_id),
                         CONSTRAINT WL_maCUSTOMER_FK FOREIGN KEY(customer_id) REFERENCES customer(customer_id)
);

INSERT INTO accounts(username, passwords,firstname,lastname,gmail, active_account,provider, roles,address,phonenumber)
VALUES("huynhphucadmin","$2y$12$n1Ea46fRM5OHjcldln9FCeDNKGWPlBTon6QSLWFiZQ70Jz7up77d.","Huỳnh", "Ngọc Phúc","huynhphucadmin@gmail.com","ACTIVE","LOCAL","ADMIN","Quận 9","0322000568");
INSERT INTO accounts(username, passwords,firstname,lastname,gmail, active_account,provider, roles,address,phonenumber)
VALUES("thanhtuadmin","$2y$12$vlL2nNxUnnFgwPYZ3DwS2.DukpDpA3K7ZzPwnBzxlATAX.wHp48dO","Nguyễn", "Thanh Tú","thanhtuadmin@gmail.com","ACTIVE","LOCAL","ADMIN","Quận 10","0322010568");
INSERT INTO accounts(username, passwords,firstname,lastname,gmail, active_account,provider, roles,address,phonenumber)
VALUES("ngoctinhadmin","$2y$12$E08DvJWGdIWEhAxFVoUVNu7MKtI2cCpCBRtPuV/cnkumAvrpIXHAW","Nguyễn","Ngọc Tình","ngoctinhadmin@gmail.com","ACTIVE","LOCAL","ADMIN","Quận 11","0322100568");
INSERT INTO accounts(username, passwords,firstname,lastname,gmail, active_account,provider, roles,address,phonenumber)
VALUES("huynhngocphuc@gmail.com","$2y$12$0FFBQhnloHEL3Jkn/FdzTO9zEZJmMdXW4wMSubG/FwatiVm/XyWX6","Huỳnh","Ngọc Phúc","huynhngocphuc@gmail.com","ACTIVE","LOCAL","CUSTOMER","Buôn Mê Thuột","0326000587");
INSERT INTO accounts(username, passwords,firstname,lastname,gmail, active_account,provider, roles,address,phonenumber)
VALUES("nguyenthanhtu@gmail.com","$2y$12$syXsrDwQo9qhYW6a9XZHkexaZDautIUFDyrxJC.NBRGl1smhkFVy6","Nguyễn","Thanh Tú","nguyenthanhtu@gmail.com","ACTIVE","LOCAL","CUSTOMER","Quảng Nam","0326010587");
INSERT INTO accounts(username, passwords,firstname,lastname,gmail, active_account,provider, roles,address,phonenumber)
VALUES("ntthanhthao31@gmail.com","$2y$12$c3e35UG2lL4lhTM1iCcS7OaNQRimhgijW7Fn/NrTjXZu3YfxAFEvO","Nguyễn","Thị Thanh Thảo","ntthanhthao31@gmail.com","ACTIVE","LOCAL","CUSTOMER","Quảng Nam","0867832447");
INSERT INTO accounts(username, passwords,firstname,lastname,gmail, active_account,provider, roles,address,phonenumber)
VALUES("chaugiakiet55533395@gmail.com","$2y$12$bbLhM88F0bKdOYGKg4vMKOZ20NLsllTDLKpK.FMxkKDkoH9/eI0Ma","Châu","Gia Kiệt","chaugiakiet55533395@gmail.com","ACTIVE","LOCAL","CUSTOMER","Tam Ky","032111587");
INSERT INTO accounts(username, passwords,firstname,lastname,gmail, active_account,provider, roles,address,phonenumber)
VALUES("halamhy@gmail.com","$2y$12$L6G1I3WH/99ZL0y5B0WYzOOxicSniPe1vy8/tC75ev8FeqKg00a5u","halamhy@gmail.com","Hạ","Lâm Hy","ACTIVE","LOCAL","CUSTOMER","Tam Thái","0333000698");
INSERT INTO accounts(username, passwords,firstname,lastname,gmail, active_account,provider, roles,address,phonenumber)
VALUES("trantrietvien@gmail.com","$2y$12$MyMaqVtRK9d39.aoJhKBS.pp0I7aoHCWmhPdA5Jr7P9eslPh6reGW","Trần","Triết Viễn","trantrietvien@gmail.com","ACTIVE","LOCAL","CUSTOMER","Bạc Liêu","0333100698");
INSERT INTO accounts(username, passwords,firstname,lastname,gmail, active_account,provider, roles,address,phonenumber)
VALUES("tumongkhiet@gmail.com","$2y$12$Wb9boDgvXQKuSLl0Gy3JN.qUJ24.b/iXGoF/QLrcwOxNuf0Zx1VjC","Từ","Mông Khiết","tumongkhiet@gmail.com","ACTIVE","LOCAL","CUSTOMER","Bến Tre","0333110698");
INSERT INTO accounts(username, passwords,firstname,lastname,gmail, active_account,provider, roles,address,phonenumber)
VALUES("thanhlong@gmail.com","$2y$12$GD9bewiDJoSgISxzXIpZF.fJPsicaBHT4CKc5i2vHdRSEJMN.tPNe","Thành","Long","thanhlong@gmail.com","ACTIVE","LOCAL","CUSTOMER","Bình Dương ","0334000698");
INSERT INTO accounts(username, passwords,firstname,lastname,gmail, active_account,provider, roles,address,phonenumber)
VALUES("vuongtrachhien@gmail.com","$2y$12$P2.cuN6ZABetZgDIZfNY6uUWWlVM2k/Laq1RG.PUx53ub3DY9vedm","Vương","Trách Hiện","vuongtrachhien@gmail.com","ACTIVE","LOCAL","CUSTOMER","Hồ Chí Minh","0333200698");
INSERT INTO accounts(username, passwords,firstname,lastname,gmail, active_account,provider, roles,address,phonenumber)
VALUES("vuongnhatlam@gmail.com","$2y$12$v/3DMekr0H8LmtzpbzPVkuu2mHdjUVtop76Ep3fYtm/tygXvl7Inm","Vương","Nhất Lâm","vuongnhatlam@gmail.com","ACTIVE","LOCAL","CUSTOMER","Vũng Tàu","0343000698");
INSERT INTO accounts(username, passwords,firstname,lastname,gmail, active_account,provider, roles,address,phonenumber)
VALUES("vuonganvu@gmail.com","$2y$12$GY8rHWAFPwBkKvAko.NylOFoMQAuRkLT80vvWQ/dzaqk0fD8f.0GK","Vương","An Vũ","vuonganvu@gmail.com","ACTIVE","LOCAL","CUSTOMER","Tam An","0333000608");
INSERT INTO accounts(username, passwords,firstname,lastname,gmail, active_account,provider, roles,address,phonenumber)
VALUES("huonghamchi@gmail.com","$2y$12$/CeNbvY00r3WlcoudUbiPeZpv31VAq5tI6sxMgoTuzLftVLjc0Lb6","Hướng","Hàm Chi","huonghamchi@gmail.com","ACTIVE","LOCAL","CUSTOMER","Tam Phú","0333111698");


INSERT INTO customer(user_customer, firstname_customer,lastname_customer, address,gmail_customer,phonenumber_customer, is_delete)
VALUES("huynhngocphuc@gmail.com","Huỳnh","Ngọc Phúc","Buôn Mê Thuột","huynhngocphuc@gmail.com","0326000587","NO");
INSERT INTO customer(user_customer, firstname_customer,lastname_customer, address,gmail_customer,phonenumber_customer, is_delete)
VALUES("nguyenthanhtu@gmail.com","Nguyễn","Thanh Tú","Quảng Nam","nguyenthanhtu@gmail.com","0326010587","NO");
INSERT INTO customer(user_customer, firstname_customer,lastname_customer, address,gmail_customer,phonenumber_customer, is_delete)
VALUES("ntthanhthao31@gmail.com","Nguyễn","Thị Thanh Thảo","Quảng Nam","ntthanhthao31@gmail.com","0867832447","NO");
INSERT INTO customer(user_customer,firstname_customer,lastname_customer, address,gmail_customer,phonenumber_customer, is_delete)
VALUES("chaugiakiet55533395@gmail.com","Châu","Gia Kiệt","Tam Ky","chaugiakiet55533395@gmail.com","032111587","NO");
INSERT INTO customer(user_customer, firstname_customer,lastname_customer, address,gmail_customer,phonenumber_customer, is_delete)
VALUES("halamhy@gmail.com","Hạ","Lâm Hy","Tam Thái","halamhy@gmail.com","0333000698","NO");
INSERT INTO customer(user_customer, firstname_customer,lastname_customer, address,gmail_customer,phonenumber_customer, is_delete)
VALUES("trantrietvien@gmail.com","Trần","Triết Viễn","Bạc Liêu","trantrietvien@gmail.com","0333100698","NO");
INSERT INTO customer(user_customer, firstname_customer,lastname_customer, address,gmail_customer,phonenumber_customer, is_delete)
VALUES("tumongkhiet@gmail.com","Từ","Mộng Khiết","Bến Tre","tumongkhiet@gmail.com","0333110698","NO");
INSERT INTO customer(user_customer, firstname_customer,lastname_customer, address,gmail_customer,phonenumber_customer, is_delete)
VALUES("thanhlong@gmail.com","Thành","Long","Bình Dương ","thanhlong@gmail.com","0334000698","NO");
INSERT INTO customer(user_customer, firstname_customer,lastname_customer, address,gmail_customer,phonenumber_customer, is_delete)
VALUES("vuongtrachhien@gmail.com","Vương","Trách Hiện","Hồ Chí Minh","vuongtrachhien@gmail.com","0333200698","NO");
INSERT INTO customer(user_customer, firstname_customer,lastname_customer, address,gmail_customer,phonenumber_customer, is_delete)
VALUES("vuongnhatlam@gmail.com","Vương","Nhất Lâm","Vũng Tàu","vuongnhatlam@gmail.com","0343000698","NO");
INSERT INTO customer(user_customer, firstname_customer,lastname_customer, address,gmail_customer,phonenumber_customer, is_delete)
VALUES("vuonganvu@gmail.com","Vương","An Vũ","Tam An","vuonganvu@gmail.com","0333000608","NO");
INSERT INTO customer(user_customer, firstname_customer,lastname_customer, address,gmail_customer,phonenumber_customer, is_delete)
VALUES("huonghamchi@gmail.com","Hướng","Hàm Chi","Tam Phú","huonghamchi@gmail.com","0333111698","NO");


INSERT INTO category (category_name,is_delete)
VALUES ('Laptop',"NO");
INSERT INTO category (category_name,is_delete)
VALUES ('Tai nghe',"NO");
INSERT INTO category (category_name,is_delete)
VALUES ('Chuột',"NO");
INSERT INTO category(category_name,is_delete)
VALUES('Ổ cứng SSD',"NO");
INSERT INTO category(category_name,is_delete)
VALUES('RAM',"NO");



INSERT INTO supplier (supplier_name,supplier_image,is_delete)
VALUES ('HP','https://bom.so/1LnqEL',"NO");
INSERT INTO supplier (supplier_name,supplier_image,is_delete)
VALUES ('Dell','https://bom.so/Zeic2l',"NO");
INSERT INTO supplier (supplier_name,supplier_image,is_delete)
VALUES ('Asus','https://bom.so/apjzXy',"NO");
INSERT INTO supplier (supplier_name,supplier_image,is_delete)
VALUES ('Acer','https://bom.so/8bzJXB',"NO");
INSERT INTO supplier (supplier_name,supplier_image,is_delete)
VALUES ('MSI','https://bom.so/gZCkrw',"NO");
INSERT INTO supplier (supplier_name,supplier_image,is_delete)
VALUES ('Lenovo','https://bom.so/Qq1tLx',"NO");
INSERT INTO supplier (supplier_name,supplier_image,is_delete)
VALUES ('Apple','https://bom.so/kFXgAL',"NO");
INSERT INTO supplier (supplier_name,supplier_image,is_delete)
VALUES ('JBL','https://bom.so/Mfjbx4',"NO");
INSERT INTO supplier (supplier_name,supplier_image,is_delete)
VALUES ('Samsung','https://bom.so/0AgolP',"NO");
INSERT INTO supplier (supplier_name,supplier_image,is_delete)
VALUES ('Sony','https://bom.so/i9Dn7l',"NO");
INSERT INTO supplier (supplier_name,supplier_image,is_delete)
VALUES ('Logitech','https://bom.so/Wpm82k',"NO");
INSERT INTO supplier (supplier_name,supplier_image,is_delete)
VALUES ('Kingston','https://bom.so/cMpx2Z',"NO");
INSERT INTO supplier (supplier_name,supplier_image,is_delete)
VALUES ('Kingmax','https://bom.so/UZwyyg',"NO");

-- LAPTOPHP
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 1, 'Laptop HP 14-DQ2055WM 39K15UA', 100, 10, 12490000, 'Phủ nhôm bền bỉ, không gian trải nghiệm đủ đầy</br>Hiệu năng lấp đầy nhu cầu người dùng',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 1, 'Laptop HP 15S-FQ2602TU 4B6D3PA', 100, 0, 16390000, 'Cấu hình ổn định với chip intel thế hệ 11</br>Kích thước màn hình 15,6 inches và độ phân giải Full HD',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 1, 'Laptop HP Pavilion x360 14-DW1018TU 2H3N6PA', 100, 6, 17990000, 'Màn hình 14 inch nhỏ gọn, hỗ trợ gập lên đến 360 độ</br>Chip Intel 5 thế hệ 11, ổ cứng SSD 512GB',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 1, 'Laptop HP 15-DY2045 2Q1H3UA', 100, 6, 17490000, 'Thiết kế cứng cáp, trải nghiệm hình ảnh không gian lớn</br>Chip Intel thế hệ 11 và RAM lớn cho sức mạnh ấn tượng, pin thời lượng cực lâu',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 1, 'Laptop HP Probook 430 G8 2H0N6PA', 100, 0, 18590000, 'Laptop HP Probook 430 G8- Thiết kế màu bạc sang trọng, gọn nhẹ</br>Hỗ trợ đầy đủ các cổng kết nối</br>Màn hình rộng full HD và viền màn hình siêu mỏng</br>Dung lượng pin lớn - Kết nối không dây siêu tốc',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 1, 'Laptop HP Envy 13 AQ1022TU 8QN69PA', 100, 0, 22690000, '',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 1, 'Laptop HP Zbook Firefly 14 G8 1A2F1AV', 100, 10, 31490000, 'Thiết kế mỏng nhẹ, màn hình 14 inch nhỏ gọn, độ sáng cao</br>Hiệu năng ổn định với chip Intel thế hệ 11, SSD 512GB',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 1, 'Laptop HP 340S G7 36A37PA', 100, 5, 20890000, 'Thiết kế gọn nhẹ, màn hình 14 inch nhỏ gọn, hiển thị sắc nét</br>Hiệu năng siêu mạnh với CPU i7 thế hệ 10, SSD dung lượng cao',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 1, 'Laptop HP 348 G7 9PH23PA', 100, 3, 22130000, 'Bộ não Intel Core i7 10510U, Ram DDR4 8GB</br>Siêu bền bỉ tiêu chuẩn quân đội, bảo mật an toàn',"NO");
-- Dell Laptop
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 2, 'Laptop Dell Inspiron 3501 5580BLK', 100, 7, 18590000, 'Thiết kế gọn nhẹ, màn hình kích thước lớn</br>Hiệu năng mượt cùng viên pin đủ dùng',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 2, 'LAPTOP DELL INSPIRON 3505 i3505-A542BLK', 100, 12, 16690000, 'Thiết kế mỏng nhẹ, màn hình kích thước lớn với viền siêu mỏng</br>Hiệu năng ổn định trong tầm giá với con chip AMD Ryzen 5 và SSD PCIE',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 2, 'Laptop Dell Inspiron 7306 N3I5202W', 100, 6, 26590000, 'Màn hình FHD 13.3 inch nhỏ gọn, tần số quét 60 Hz, góc cạnh bo tròn</br>Hiệu năng mạnh mẽ với Intel Core i5-1135G7',"NO");

INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 2, 'Laptop Dell Vostro 3405 V4R53500U003W', 100, 8, 1699000, '',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 2, 'Laptop Dell Vostro 3500 P112F002ABL', 100, 3, 22190000, 'Thiết kế đơn giản với màu đen sang trọng, chất liệu cứng cáp</br>Hiệu năng mạnh mẽ với bộ vi xử lý khỏe, bộ nhớ SSD dung lượng cao',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 2, 'Laptop Dell Vostro 3400 70234073', 100, 4, 18590000, 'Mỏng nhẹ tinh tế, màn hình chất lượng</br>Hiệu năng hàng đầu',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 2, 'Laptop Dell Vostro 3510 P112F002BBL', 100, 4, 21990000, 'Thiết kế gọn gàng, mỏng nhẹ</br>Cấu hình mạnh mẽ, ổn định</br>Hình ảnh đặc sắc, âm thanh ấn tượng',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 2, 'Laptop Dell Vostro 5510 70253901', 100, 5, 21690000, 'Kiểu dáng tinh tế, màn hình rộng lớn</br>Hiệu năng phù hợp công việc kiêm học tập',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 2, 'Laptop Dell Vostro 5402 V5402A', 100, 5, 21790000, 'Laptop mỏng nhẹ, màn hình 14 inch nhỏ gọn</br>Hiệu năng mượt mà với chip Intel thế hệ 11',"NO");

INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 2, 'Laptop Dell Gaming G5 5500 P89F003', 100, 3, 33610000, '',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 2, 'Laptop Dell Gaming G15 5511 P105F006BGR', 100, 7, 33990000, '',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 2, 'Laptop Dell Gaming G15 5515 P105F004DGR', 100, 5, 27190000, 'Thiết kế đậm chất gaming, màn hình 120Hz mượt mà</br>Hoạt động ổn định với CPU AMD kết hợp card đồ họa chuyên nghiệp',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 2, 'Laptop Dell Alienware M15 R6 70262923', 100, 7, 64090000, 'Thiết kế cao cấp với khả năng tản nhiệt vượt trội</br>Màn hình chuẩn 2K sắc nét, cùng hiệu suất vượt trội',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 2, 'Laptop Dell Gaming G3 G3500CW', 100, 7, 2590000, 'Thiết kế gọn nhẹ, màn hình kích thước lớn</br>Hiệu năng mượt cùng viên pin đủ dùng',"NO");

-- ASUS VIVOBOOK
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 3, 'Laptop ASUS VivoBook 15 A515EA', 100, 0, 15360000, '',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 3, 'Laptop ASUS VivoBook X415MA-BV088T', 100, 0, 9390000, '',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 3, 'Laptop ASUS VivoBook Flip TM420IA-EC031T', 100, 0, 17190000, 'Thiết kế mỏng nhẹ, bản lề quay 360 ấn tượng',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 3, 'Laptop ASUS Vivobook R565EA-UH31T', 100, 16, 12490000, 'Thiết kế gọn nhẹ, màn hình kích thước lớn</br>Hiệu năng mượt cùng viên pin đủ dùng',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 3, 'Laptop ASUS VivoBook Flip 14 TM420IA-EC227T', 100, 4, 19190000, 'Sử dụng CPU đến từ AMD</br>Màn hình viền mỏng, gọn nhẹ ấn tượng</br>Màu sắc cá tính, sang trọng, trọng lượng nhẹ dễ dàng di chuyển',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 3, 'Laptop ASUS VivoBook S433EA', 100, 0, 19490000, 'Kích thước nhỏ gọn, màn hình NanoEdge 14 inch Full HD sắc nét</br>Hỗ trợ nhiều cổng kết nối, công nghệ ASUS SonicMaster sống động, pin 3 Cells 50WHrs',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 3, 'Laptop ASUS VivoBook M513UA-L1240T', 100, 7, 19690000, 'Thiết kế mỏng nhẹ, linh hoạt cùng màu ánh kim thanh lịch</br>Hiệu năng khỏe khoắn với chip AMD Ryzen, ổ cứng SSD đọc ghi dữ liệu tốc độ cao',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 3, 'Laptop ASUS VivoBook 14 A415EA-EB360T', 100, 7, 18290000, 'Trọng lượng chỉ 1.4kg, màn hình NanoEdge 14 inches</br>Sở hữu hiệu năng tốt: Intel Core-i5, VGA Iris Xe Graphics, 8GB RAM',"NO");

-- ASUS ZENBOOK
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 3, 'Laptop ASUS Zenbook UX425EA', 100, 7, 23390000, 'Vẻ ngoài hiện đại và cứng cáp với màn hình 14 inch FHD</br>Vi xử lý mạnh mẽ kèm tiện ích phần cứng lẫn phần mềm',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 3, 'Laptop ASUS ZenBook UX325EA-KG363T', 100, 4, 23290000, 'Bộ vi xử lý vượt trội giúp nâng cao hiệu suất công việc</br>Thiết kế gọn nhẹ, thanh lịch',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 3, 'Laptop ASUS ZenBook Flip UX363EA', 100, 2, 26690000, 'Thiết kế cứng cáp & linh hoạt với màn hình xoay ngược 360 độ</br>Vi xử lý Intel Core i5-1135G7, RAM 8GB và 512GB SSD cho hiệu năng làm việc tốt',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 3, 'Laptop ASUS Zenbook Duo 14 UX482EG', 100, 8, 32990000, 'Thiết kế màn hình kép ScreenPad™ Plus với độ phân giải Full HD sắc nét</br>Vi xử lý Intel Core i5-1135G7 cùng đồ họa GeForce MX450 2GB vận hành mạnh mẽ',"NO");

-- ASUS GAMING
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 3, 'Laptop ASUS Gaming ROG Strix G15 G513IH-HN015T', 100, 5, 22690000, 'Thiết kế sang trọng, logo Gaming ROG, viền màn hình siêu mỏng</br>Hoạt động ổn định với chip H, hệ thống 2 loa chất lượng',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 3, 'Laptop ASUS Gaming ROG Zephyrus G14 GA401QH-HZ035T', 100, 4, 27590000, 'Thiết kế độc đáo, trọng lượng nhẹ</br>Hiệu năng mạnh mẽ với chip xử lý nhanh chóng, dung lượng RAM và ROM lớn',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 3, 'Laptop ASUS Gaming FX506LH-HN002T', 100, 5, 20890000, 'Kiểu dáng gaming sang trọng, màn hình hỗ trợ 4K, tần số quét 144 Hz</br>Sức mạnh ấn tượng bởi Core i5-10300H, GeForce GTX 1650, 8 GB RAM',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 3, 'Laptop Asus TUF Gaming F15 FX506HC-HN002T', 100, 7, 24390000, 'Hiệu năng đỉnh cao, tần số quét 144Hz</br>Bền bỉ với dung lượng pin dài, tính năng tản nhiệt hiệu quả',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 3, 'Laptop ASUS TUF Gaming FA506IU-AL127T', 100, 3, 25990000, 'Thiết kế sang trọng chất riêng, độ bền đạt chuẩn quân đội Mỹ</br>Màn hình rộng 15.6 inch Full HD 144Hz, Card NVIDIA GeForce GTX 1660Ti 6GB ',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 3, 'Laptop ASUS TUF DASH F15 FX516PC-HN002T', 100, 7, 23890000, '',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 3, 'Laptop ASUS ExpertBook B9400CEA-KC0790T', 100, 3, 36990000, 'Thiết kế gọn nhẹ, màn hình kích thước lớn</br>Hiệu năng mượt cùng viên pin đủ dùng',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 3, 'Laptop Asus ExpertBook B5302FEA LF0749W', 100, 7, 25900000, '',"NO");

-- ACER
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 4, 'Laptop Acer Aspire 3 A315-56-37DV', 100, 7, 11490000, 'Thiết kế mỏng nhẹ, cứng cáp</br>Cấu hình ổn định với chip Intel Core i3-1005G1',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 4, 'Laptop Acer Aspire 5 A514-54-540F', 100, 2, 18090000, '',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 4, 'Laptop Acer Gaming Aspire 7 A715-42G-R4ST', 100, 7, 18690000, '',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 4, 'Laptop Acer Swift 3 SF314-43-R4X3', 100, 4, 19490000, 'Thiết kế mỏng nhẹ, hiện đại cùng màn hình rõ nét</br>Hiệu năng ổn định với chipset AMD Ryzen 5, RAM 16GB',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 4, 'Laptop Acer Swift 3X SF314-510G-57MR', 100, 10, 20390000, 'Thiết kế nguyên khối – màn hình tràn viền</br>Hiệu năng mạnh với chip Intel thế hệ 11, card vga rời Intel XE Max',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 4, 'Laptop Acer Swift 5 SF514-55T-51NZ', 100, 7, 22690000, 'Thiết kế gọn nhẹ, màn hình kích thước lớn</br>Dung lượng pin sẵn sàng cho ngày dài, bảo mật vân tay an toàn tuyệt đối',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 4, 'Laptop Gaming Acer Nitro 5 Eagle AN515-57-74NU', 100, 7, 27590000, 'Màn hình FHD IPS rộng lớn, tốc độ làm mới 144Hz</br>Bộ vi xử lý thế hệ 11 - Tản nhiệt hiệu suất ấn tượng',"NO");


-- MSI GAMING
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 5, 'Laptop MSI Gaming BRAVO 15 A4DCR-270VN', 100, 7, 18790000, 'Màn hình 15.6" hỗ trợ công nghệ Freesynce, tần số quét màn hình 144Hz</br>Viền màn hình siêu mỏng, thiết kế vỏ nhôm cao cấp',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 5, 'Laptop MSI Gaming GL75 Leopard 10SCSK 056VN', 100, 7, 21090000, 'Thiết kế nhỏ gọn, màn hình đến 17.3 inches</br>Cấu hình mạnh mẽ với Core i5-10200H, 8GB RAM, 512GB SSD, VGA GTX 1650 Ti',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 5, 'Laptop MSI Gaming GF65 THIN 10UE-286VN', 100, 12, 26490000, 'Thiết kế cứng cáp và hầm hố, màn hình 15.6 inch 144 Hz cho hình ảnh mượt mà</br>Công phá mọi tựa game với CPU Intel Core i5-10500H và đồ họa GeForce RTX 3060',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 5, 'Laptop MSI Gaming Alpha 15 A4DEK-027VN', 100, 20, 27590000, 'Màn hình khủng, tần số quét 144 Hz, bàn phím tùy chỉnh sắc độ</br>Cấu hình mạnh mẽ bởi AMD Ryzen 7, tản nhiệt Cooler Boost 5',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 5, 'Laptop MSI Katana GF66 11UC-641VN', 100, 7, 28490000, 'Thiết kế sắc bén, màn hình sáng bóng như lưỡi gươm</br>Khai thác toàn bộ tiềm năng của kiếm sĩ',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 5, 'Laptop MSI Creator M16 A11UD-694VN', 100, 7, 36490000, 'Màu sắc hình ảnh trung thực, sống động</br>Âm thanh sống động, dung lượng pin lớn',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 5, 'Laptop MSI Gaming GP76 Leopard 11UG-280VN', 100, 12, 46990000, 'Bộ vi xử lý mới nhất, cân mọi tựa game</br>Thiết kế độc đáo, tốc độ làm tươi ấn tượng',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 5, 'Laptop MSI Gaming GS66 Stealth 10UE-200VN', 100, 13, 51990000, 'Thiết kế mạnh mẽ, pin khủng, hình ảnh âm thanh sống động</br>Chiến game cực mượt với vi xử lý Intel Core i7, kết nối wifi 6 vượt trội',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 5, 'Laptop MSI Gaming Pulse GL66 11UDK-255VN', 100, 7, 30990000, 'Màn hình siêu mượt, tản nhiệt siêu mát</br>Hiệu năng siêu mạnh cho mọi tác vụ',"NO");

-- LAPTOP LENOVO
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 6, 'Laptop Lenovo Ideapad 3 14ALC6', 100, 7, 14190000, 'Thiết kế mỏng nhẹ, vẻ đẹp tinh tế với màn hình giải trí tuyệt vời</br>Tích hợp hiệu năng mạnh mẽ với các cổng kết nối đầy tiện ích',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 6, 'Laptop Lenovo Ideapad 5 15ITL05 82FG00M5VN', 100, 5, 18890000, 'Ngoại hình sang trọng, hình ảnh sắc nét</brChip Intel Core i5, Ram 8GB mang đến hiệu năng mạnh mẽ',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 6, 'Laptop Lenovo Ideapad Slim 3 15ARE05 81W4009FVN', 100, 0, 13290000, 'Thiết kế gọn nhẹ, màn hình kích thước lớn</br>Hiệu năng mượt cùng viên pin đủ dùng',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 6, 'Laptop Lenovo Ideapad S340-13IML', 100, 3, 11990000, 'Thiết kế gọn nhẹ, màn hình kích thước lớn</br>Hiệu năng mượt cùng viên pin đủ dùng',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 6, 'Laptop Lenovo Ideapad 5 Pro 14ACN6 82l7007XVN', 100, 3, 21990000, 'Thiết kế gọn nhẹ, màn hình kích thước lớn</br>Hiệu năng mượt cùng viên pin đủ dùng',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 6, 'Laptop Lenovo Ideapad 1 11IGL05', 100, 20, 8790000, 'Kiểu dáng xách tay nhẹ gọn, màn hình bắt mắt</brHiệu năng ổn định phục vụ lâu dài',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 6, 'Laptop Lenovo Thinkbook 15 G2 ITL', 100, 5, 20590000, 'Thời thượng và mỏng gọn, với màn hình 15.6 inch bảo vệ mắt hiện đại</br>Làm việc tối ưu với chip Intel Core i7-1165G7, 512 GB SSD cùng Windows 10',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 6, 'Laptop Lenovo ThinkPad E14', 100, 15, 13490000, 'Dung lượng khá ấn tượng với RAM 8GB và ổ cứng 1TB</br>Màn hình có kích thước 14 inch, sử dụng công nghệ chống chói',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 6, 'Laptop Lenovo Thinkpad E490S 20NGS01K00', 100, 17, 15190000, 'Hình dáng thanh lịch, thiết kế mỏng gọn, tích hợp nhận dạng vân tay</br>Màn hình 14 inch FHD thiết kế viền mỏng giúp quan sát trọn vẹn hình ảnh',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 6, 'Laptop Lenovo Thinkpad X13 GEN 2', 100, 8, 33590000, 'Nhỏ gọn tinh tế, màn hình ấn tượng</br>Sức mạnh bứt phá, trải nghiệm đặc biệt',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 6, 'Laptop Lenovo Ideapad Gaming 3 15IMH05 81Y400Y8VN', 100, 8, 20590000, 'Màn hình kích thước lớn, tần số quét 120Hz</br>Cấu hình mạnh với chip Intel core i5 thế hệ 10, SSD 512GB',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 6, 'Laptop Lenovo Gaming Legion 5 15ARH05 82B500GTVN', 100, 7, 24590000, 'Thiết kế gọn nhẹ, màn hình kích thước lớn</br>Hiệu năng mượt cùng viên pin đủ dùng',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 6, 'Laptop Lenovo Gaming L340-15IRH 81LK01J3VN', 100, 15, 16990000, 'Thiết kế gọn nhẹ, màn hình kích thước lớn</br>Hiệu năng mượt cùng viên pin đủ dùng',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 6, 'Laptop Lenovo Ideapad Gaming 3 15IMH05 81Y400Y9VN', 100, 14, 22290000, 'Thiết kế gọn nhẹ, màn hình kích thước lớn</br>Hiệu năng mượt cùng viên pin đủ dùng',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 6, 'Laptop Lenovo Legion 7 16ACHG6 82N60039VN', 100, 7, 74990000, 'Con chip dòng H mang lại hiệu năng vượt trội</br>Tản nhiệt chất lượng với Legion Coldfront 3.0',"NO");
-- TAI NGHE APPLE
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 2, 7, 'Tai nghe Bluetooth Apple AirPods 2', 100, 23, 3990000, 'Dung lượng pin lớn, sử dụng bền bỉ</br>Thiết kế nhỏ gọn, bắt mắt',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 2, 7, 'Tai nghe Bluetooth Apple AirPods Pro', 100, 37, 7999000, 'Airpod Pro sở hữu thiết kế nhỏ gọn, trọng lượng 5.4 gram</br>Âm thanh trên Apple Airpods Pro rõ nét với công nghệ khử tiếng ồn chủ động',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 2, 7, 'Tai nghe chụp tai chống ồn Apple AirPods Max', 100, 20, 13900000, 'Thiết kế chụp tai sử dụng êm ái, chất liệu cao cấp nổi bật</br>Chất lượng âm thanh cực kỳ cao cấp, chống ồn hiệu quả',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 2, 7, 'Tai nghe Bluetooth Apple AirPods 3', 100, 0, 4990000, 'TThiết kế sang trọng, nhiều thay đổi so với thế hệ trước</br>Dung lượng pin được cải thiện',"NO");

-- TAI NGHE SAM SUNG
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 2, 9, 'Tai nghe không dây Samsung Galaxy Buds 2', 100, 30, 2990000, '3 micro cùng công nghệ nhận diện giọng nói</br>Kết nối bluetooth chuẩn v5.2',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 2, 9, 'Tai nghe Samsung Galaxy AKG EO-IG955', 100, 24, 250000, '',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 2, 9, 'Tai nghe nhét tai Samsung IG935B', 100, 10, 300000, 'Tai nghe Samsung IG935B thiết kế nhỏ gọn trẻ trung</br>Cảm giác đeo thoải mái của tai nghe samsung IG935B',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 2, 9, 'Tai nghe nhét tai Samsung EG920B', 100, 10, 280000, 'Samsung EG920B - Thiết kế cao su bền bỉ dài 1.2m, kèm theo cặp đệm dạng thường và dạng móc</br>Âm thanh trung thực, độ bass dày, đường kính 12mm trên Samsung EG920B ',"NO");

-- TAI NGHE JBL
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 2, 8, 'Tai nghe không dây JBL Live Pro+', 100, 0, 2990000, 'Thiết kế nhỏ gọn, hỗ trợ chống nước IPX4</br>Âm thanh JBL Signature Sound chất lượng',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 2, 8, 'Tai Nghe Không Dây JBL Tune 120 TWS', 100, 54, 2390000, 'Thiết kế độc đáo, gọn nhẹ với driver đường kính 5.8mm và màu sắc thời trang</br>JBL Tune 120Tws sử dụng chuẩn kết nối bluetooth 4.2, kết nối trợ lý ảo tiện lợi và nhanh chóng',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 2, 8, 'Tai nghe chụp tai JBL Tune 510BT', 100, 28, 1390000, 'Thiết kế đệm tai siêu êm, điều khiển âm thanh tiện lợi</br>Chuẩn chất âm JBL Pure Bass mạnh mẽ với gần 40 giờ nghe liên tục',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 2, 8, 'Tai nghe gaming có dây JBL Quantum 50', 100, 17, 890000, 'Thiết kế độc đáo và thời trang, nhiều màu sắc để lựa chọn</br>Chất âm JBL QuantumSOUND Signature độc quyền, tương thích với nhiều thiết bị',"NO");
-- TAI NGHE SONY
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 2, 10, 'Tai nghe chụp tai Sony WH-1000XM4', 100, 23, 8490000, 'Chức năng khử tiếng ồn chủ động, hỗ trợ “Speak to Chat”</br>Khả năng tiêu thụ điện năng thấp, pin sử dụng 40 giờ',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 2, 10, 'Tai nghe không dây chống ồn Sony WF-1000XM3', 100, 30, 5490000, 'Thiết kế ôm sát không bị trượt ra ngoài, kèm phụ kiện eartip hybrid</br>Cảm biến tiếng ồn kép, bộ xử lý chống ồn HD QN1e ',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 2, 10, 'Tai nghe không dây Sony WF-1000XM4', 100, 15, 6490000, 'Hiệu suất chống ồn đỉnh cao, màng loa 6 mm</br>Tích hợp công nghệ hiện đại: Edge-AI, DSEE ExtremeTM  tăng cường âm thanh',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 2, 10, 'Tai nghe Chụp Tai Không Dây Sony WH-CH510', 100, 16, 1190000, 'Sony WH-CH510 - Thiết kế gọn nhẹ với trọng lượng chỉ 132g cùng nút điều khiển linh hoạt</br>Thời lượng pin 35 giờ cùng âm thanh phát trực tiếp thông qua Bluetooth 5.0',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 2, 10, 'Tai nghe Sony MDR-XB55AP', 100, 0, 890000, 'Thiết kế từ nhựa và kim loại cùng với housing góc nghiêng 45o cho cảm giác đeo dễ chịu, thoải mái</br>Nói không với tình trạng rối dây khi dây của tai nghe được thiết dạng dẹt sọc gân nổi',"NO");
-- CHUỘT LOGITECH
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 3, 11, 'Chuột không dây Logitech M590', 100, 25, 800000, 'Thiết kế thông minh, nhỏ gọn và hiện đại</br>Hoạt động tuyệt vời trong yên lặng, thao tác nhẹ nhàng và chính xác',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 3, 11, 'Chuột không dây Logitech M331', 100, 0, 340000, 'Thiết kế bo tròn ôm sát lòng bàn tay, trọng lượng chỉ 101g, chất liệu nhựa cứng</br>Chuột quang độ phân giải lên đến 1000dpi, kết nối không dây với khoảng cách 10m',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 3, 11, 'Chuột không dây Logitech M221', 100, 23, 319000, 'Thiết kế linh hoạt, tốc độ không dây 2.4 GHz</br>Tương thích với hầu hết hệ điều hành, giảm thiểu 90% tiếng ồn',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 3, 11, 'Chuột không dây Logitech M238 Marvel Collection', 100, 48, 490000, 'Độ phân giải 1000 DPI, phạm vi kết nối 10m</br>Đầu tín hiệu receiver nano, thời gian sử dụng đến 12 tháng',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 3, 11, 'Chuột Gaming Logitech G102 LightSync', 100, 32, 599000, 'Thiết kế cổ điển với 6 nút nhấn cùng hệ thống LED RGB</br>DPI lên đến 8.000 với cảm biến cấp độ chơi game cho sở thích riêng',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 3, 11, 'Chuột không dây Logitech MX Anywhere 3', 100, 19, 1999000, 'Thiết kế không dây với 2 cơ chế kết nối</br>Con cuộn Magspeed ưu việt, tương thích nhiều hệ điều hành',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 3, 11, 'Chuột không dây Logitech MX Master 2S', 100, 36, 2490000, '',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 3, 11, 'Chuột chơi game không dây Logitech G502 Lightspeed', 100, 7, 2590000, 'Thiết kế mạnh mẽ, trọng lượng siêu nhẹ</br>Cảm biến Hero độc đáo, độ chính xác tuyệt đối',"NO");

-- SSD SAMSUNG
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 4, 9, 'Ổ cứng SSD Samsung 860 Evo 250GB M.2 2280 SATA 3 - MZ-N6E250BW', 100, 7, 1890000, 'Tối ưu hóa tốc độ lên mức tối đa</br>Cải thiện năng lượng hiệu quả',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 4, 9, 'Ổ cứng SSD Samsung 860 Evo 500GB M.2 2280 SATA 3 - MZ-N6E500BW', 100, 7, 2490000, 'Tối ưu hóa tốc độ lên mức tối đa</br>Cải thiện năng lượng hiệu quả',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 4, 9, 'Ổ cứng SSD Samsung 970 Pro 512GB M.2 2280 NVMe - MZ-V7P512BW', 100, 7, 5520000, 'Tối ưu hóa tốc độ lên mức tối đa</br>Cải thiện năng lượng hiệu quả',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 4, 9, 'Ổ cứng SSD Samsung 970 Evo Plus 250GB M.2 NVMe - MZ-V7S250BW', 100, 7, 1790000, 'Tối ưu hóa tốc độ lên mức tối đa</br>Cải thiện năng lượng hiệu quả',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 4, 9, 'Ổ cứng SSD Samsung 970 Evo Plus 500GB M.2 NVMe - MZ-V7S500BW', 100, 17, 2890000, 'Tối ưu hóa tốc độ lên mức tối đa</br>Cải thiện năng lượng hiệu quả',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 4, 9, 'Ổ cứng SSD Samsung 980 PRO 500GB NVMe M.2 PCIe 4.0 (MZ-V8P500BW)', 100, 7, 3990000, 'Tối ưu hóa tốc độ lên mức tối đa</br>Cải thiện năng lượng hiệu quả',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 4, 9, 'Ổ cứng SSD Samsung 870 EVO 250GB SATA III 2.5 inch (MZ-77E250BW)', 100, 7, 1890000, 'Tối ưu hóa tốc độ lên mức tối đa</br>Cải thiện năng lượng hiệu quả',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 4, 9, 'Ổ cứng SSD Samsung 870 EVO 500GB SATA III 2.5 inch (MZ-77E500BW)', 100, 7, 2350000, 'Tối ưu hóa tốc độ lên mức tối đa</br>Cải thiện năng lượng hiệu quả',"NO");
-- SSD KINGSTON
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 4, 12, 'Ổ cứng SSD Kingston A400 120GB Sata 3 (SA400S37/120G)', 100, 7, 790000, '',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 4, 12, 'Ổ cứng SSD Kingston A400 240GB Sata 3 (SA400S37/240G)', 100, 7, 1130000, '',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 4, 12, 'Ổ cứng SSD Kingston A400 480GB Sata 3 (SA400S37/480G)', 100, 7, 1610000, '',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 4, 12, 'Ổ cứng SSD Kingston KC600 512GB 2.5" SATA 3', 100, 7, 2190000, '',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 4, 12, 'Ổ cứng SSD Kingston KC600 256GB 2.5" SATA 3', 100, 7, 1390000, '',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 4, 12, 'Ổ cứng SSD Kingston A2000 250GB M.2 2280 NVMe PCIe', 100, 7, 1490000, '',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 4, 12, 'Ổ cứng SSD Kingston A2000 500GB M.2 2280 NVMe PCIe', 100, 7, 2200000, '',"NO");

-- RAM KINGMAX
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 5, 13, 'RAM desktop KINGMAX (1x4GB) DDR3 1600MHz', 100, 7, 790000, 'Tính ổn định cao, độ tin cậy và khả năng tương thích</br>Ứng dụng rộng hơn và tiêu thụ điện năng thấp hơn',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 5, 13, 'RAM desktop KINGMAX (1x8GB) DDR3 1600MHz', 100, 7, 1250000, 'Tính ổn định cao, độ tin cậy và khả năng tương thích</br>Ứng dụng rộng hơn và tiêu thụ điện năng thấp hơn',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 5, 13, 'RAM desktop KINGMAX (1x4GB) DDR4 2400MHz', 100, 7, 650000, 'Tính ổn định cao, độ tin cậy và khả năng tương thích</br>Ứng dụng rộng hơn và tiêu thụ điện năng thấp hơn',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 5, 13, 'RAM desktop KINGMAX (1x8GB) DDR4 2400MHz', 100, 7, 900000, 'Tính ổn định cao, độ tin cậy và khả năng tương thích</br>Ứng dụng rộng hơn và tiêu thụ điện năng thấp hơn',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 5, 13, 'RAM desktop KINGMAX HEATSINK (Zeus) (1 x 8GB) DDR4 3200MHz', 100, 7, 1400000, 'Tính ổn định cao, độ tin cậy và khả năng tương thích</br>Ứng dụng rộng hơn và tiêu thụ điện năng thấp hơn',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 5, 13, 'RAM desktop KINGMAX HEATSINK (Zeus) (1 x 16GB) DDR4 3200MHz', 100, 7, 2590000, 'Tính ổn định cao, độ tin cậy và khả năng tương thích</br>Ứng dụng rộng hơn và tiêu thụ điện năng thấp hơn',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 5, 13, 'RAM laptop KINGMAX Kingmax 16GB (1 x 16GB) DDR4 2666MHz', 100, 15, 2350000, 'Tính ổn định cao, độ tin cậy và khả năng tương thích</br>Ứng dụng rộng hơn và tiêu thụ điện năng thấp hơn',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 5, 13, 'RAM laptop KINGMAX Kingmax 8GB (3200) (1 x 8GB) DDR4 3200MHz', 100, 35, 1390000, 'Tính ổn định cao, độ tin cậy và khả năng tương thích</br>Ứng dụng rộng hơn và tiêu thụ điện năng thấp hơn',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 5, 13, 'RAM laptop KINGMAX Kingmax 16GB (3200) (1 x 16GB) DDR4 3200MHz', 100, 20, 2430000, 'Tính ổn định cao, độ tin cậy và khả năng tương thích</br>Ứng dụng rộng hơn và tiêu thụ điện năng thấp hơn',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 5, 13, 'RAM laptop KINGMAX Kingmax 32GB (3200) (1 x 32GB) DDR4 3200MHz', 100, 12, 4770000, 'Tính ổn định cao, độ tin cậy và khả năng tương thích</br>Ứng dụng rộng hơn và tiêu thụ điện năng thấp hơn',"NO");

-- RAM KINGSTON
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 5, 12, 'RAM desktop KINGSTON FURY Beast Black 32GB (2x16GB) DDR5 5200MHz (2 x 16GB) DDR5 5200MHz (KF552C40BBK2-32)', 100, 7, 7999000, 'Tính ổn định cao, độ tin cậy và khả năng tương thích</br>Ứng dụng rộng hơn và tiêu thụ điện năng thấp hơn',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 5, 12, 'RAM desktop KINGSTON Fury Beast 32GB (2 x 16GB) DDR4 3600MHz (KF436C18BBK2/32)', 100, 7, 5750000, 'Tính ổn định cao, độ tin cậy và khả năng tương thích</br>Ứng dụng rộng hơn và tiêu thụ điện năng thấp hơn',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 5, 12, 'RAM desktop KINGSTON Fury Beast RGB (2 x 16GB) DDR4 3600MHz (KF436C18BBAK2/32)', 100, 7, 5390000, 'Tính ổn định cao, độ tin cậy và khả năng tương thích</br>Ứng dụng rộng hơn và tiêu thụ điện năng thấp hơn',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 5, 12, 'RAM desktop KINGSTON Fury Renegade RGB 32GB (2 x 16GB) DDR4 3200MHz (KF432C16RB1AK2/32)', 100, 7, 2590000, 'Tính ổn định cao, độ tin cậy và khả năng tương thích</br>Ứng dụng rộng hơn và tiêu thụ điện năng thấp hơn',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 5, 12, 'RAM desktop KINGSTON Fury Beast RGB 32GB (2 x 16GB) DDR4 3200MHz (KF432C16BB1AK2/32)', 100, 7, 2590000, 'Tính ổn định cao, độ tin cậy và khả năng tương thích</br>Ứng dụng rộng hơn và tiêu thụ điện năng thấp hơn',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 5, 12, 'RAM laptop KINGSTON DDR4 Kingston 16GB (3200) (1 x 16GB) DDR4 3200MHz (KVR32S22D8/16)', 100, 7, 2090000, 'Tính ổn định cao, độ tin cậy và khả năng tương thích</br>Ứng dụng rộng hơn và tiêu thụ điện năng thấp hơn',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 5, 12, 'RAM laptop Kingston KVR16LS11/4 (1x4GB) DDR3L 1600MHz', 100, 7, 950000, 'Tính ổn định cao, độ tin cậy và khả năng tương thích</br>Ứng dụng rộng hơn và tiêu thụ điện năng thấp hơn',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 5, 12, 'RAM laptop KINGSTON KCP432SS6/8 (1 x 8GB) DDR4 3200MHz', 100, 7, 1290000, 'Tính ổn định cao, độ tin cậy và khả năng tương thích</br>Ứng dụng rộng hơn và tiêu thụ điện năng thấp hơn',"NO");


-- IMAGES

------------------------------------------
INSERT INTO image (image,is_delete,product_id)
VALUES ('https://bom.so/QrJEfJ','NO',1);
INSERT INTO image (image,is_delete,product_id)
VALUES ('https://bom.so/ejRBre','NO',1);
INSERT INTO image (image,is_delete,product_id)
VALUES ('https://bom.so/P6Bbhi','NO',2);
INSERT INTO image (image,is_delete,product_id)
VALUES ('https://bom.so/JCDxUm','NO',2);
INSERT INTO image (image,is_delete,product_id)
VALUES ('https://bom.so/6qJgSA','NO',3);
INSERT INTO image (image,is_delete,product_id)
VALUES ('https://bom.so/rL01II','NO',3);
INSERT INTO image (image,is_delete,product_id)
VALUES ('https://bom.so/pNK7gy','NO',4);
INSERT INTO image (image,is_delete,product_id)
VALUES ('https://bom.so/AbzTeF',"NO",4);
INSERT INTO image (image,is_delete,product_id)
VALUES ('https://bom.so/9lI02c','NO',5);
INSERT INTO image (image,is_delete,product_id)
VALUES ('https://bom.so/jcXXXb',"NO",5);
INSERT INTO image (image,is_delete,product_id)
VALUES ('https://bom.so/r8EdhU','NO',6);
INSERT INTO image (image,is_delete,product_id)
VALUES ('https://bom.so/q25XbL', "NO",6);
INSERT INTO image (image,is_delete,product_id)
VALUES ('https://bom.so/FVWYUk',"NO",7);
INSERT INTO image (image,is_delete,product_id)
VALUES ('https://bom.so/MM23wQ', "NO",7);
INSERT INTO image (image,is_delete,product_id)
VALUES ('https://bom.so/f3zD2o',"NO",8);
INSERT INTO image (image,is_delete,product_id)
VALUES ('https://bom.so/SptA1I',"NO",8);
INSERT INTO image (image,is_delete,product_id)
VALUES ('https://bom.so/fl7m7W',"NO",9);
INSERT INTO image (image,is_delete,product_id)
VALUES ('https://bom.so/YXcaSr',"NO",9);
INSERT INTO image (image,is_delete,product_id)
VALUES ('https://bom.so/PcOhwD',"NO",10);
INSERT INTO image (image,is_delete,product_id)
VALUES ('https://bom.so/w5pPd1',"NO",10);
INSERT INTO image (image,is_delete,product_id)
VALUES ('https://bom.so/p2tC9S',"NO",11);
INSERT INTO image (image,is_delete,product_id)
VALUES ('https://bom.so/yh8rEU', "NO",11);
INSERT INTO image (image,is_delete,product_id)
VALUES ('https://bom.so/C3arw5',"NO",12);
INSERT INTO image (image,is_delete,product_id)
VALUES ('https://bom.so/pm4mfH',"NO",12);

INSERT INTO image (image,is_delete,product_id)
VALUES ('https://bom.so/QJiuh0',"NO",13);
INSERT INTO image (image,is_delete,product_id)
VALUES ('https://bom.so/2DjKif',"NO",13);
INSERT INTO image (image,is_delete,product_id)
VALUES ('https://bom.so/YntcSX',"NO",14);
INSERT INTO image (image,is_delete,product_id)
VALUES ('https://bom.so/hLabbJ',"NO",14);
INSERT INTO image (image,is_delete,product_id)
VALUES ('https://bom.so/8kbYAD',"NO",15);
INSERT INTO image (image,is_delete,product_id)
VALUES ('https://bom.so/hI36wa', "NO",15);
INSERT INTO image (image,is_delete,product_id)
VALUES ('https://bom.so/dwldjN',"NO",16);
INSERT INTO image (image,is_delete,product_id)
VALUES ('https://bom.so/cFJJXs',"NO",16);
INSERT INTO image (image,is_delete,product_id)
VALUES ('https://bom.so/zCYSYw',"NO",17);
INSERT INTO image (image,is_delete,product_id)
VALUES ('https://bom.so/ltyR7v',"NO",17);
INSERT INTO image (image,is_delete,product_id)
VALUES ('https://bom.so/cvyjAU',"NO",18);
INSERT INTO image (image,is_delete,product_id)
VALUES ('https://bom.so/xa4Qey',"NO",18);

INSERT INTO image (image,is_delete,product_id)
VALUES ('https://bom.so/DnFgd9',"NO",19);
INSERT INTO image (image,is_delete,product_id)
VALUES ('https://bom.so/1AxVab',"NO",19);
INSERT INTO image (image,is_delete,product_id)
VALUES ('https://bom.so/mVN4iF',"NO",20);
INSERT INTO image (image,is_delete,product_id)
VALUES ('https://bom.so/3QlgcH',"NO",20);
INSERT INTO image (image,is_delete,product_id)
VALUES ('https://bom.so/oElsL8',"NO",21);
INSERT INTO image (image,is_delete,product_id)
VALUES ('https://bom.so/RIvLEo',"NO",21);
INSERT INTO image (image,is_delete,product_id)
VALUES ('https://bom.so/swU6bM',"NO",22);
INSERT INTO image (image,is_delete,product_id)
VALUES ('https://bom.so/jz0Xos',"NO",22);
INSERT INTO image (image,is_delete,product_id)
VALUES ('https://bom.so/bYX2AS',"NO",23);
INSERT INTO image (image,is_delete,product_id)
VALUES ('https://bom.so/xrD1ND',"NO",23);

INSERT INTO image (image,is_delete,product_id)
VALUES ('https://bom.so/2uhKty',"NO",24);
INSERT INTO image (image,is_delete,product_id)
VALUES ('https://bom.so/ACia2v',"NO",24);
INSERT INTO image (image,is_delete,product_id)
VALUES ('https://bom.so/3qPVXq',"NO",25);
INSERT INTO image (image,is_delete,product_id)
VALUES ('https://bom.so/73G95C',"NO",25);
INSERT INTO image (image,is_delete,product_id)
VALUES ('https://bom.so/jT7XSC',"NO",26);
INSERT INTO image (image,is_delete,product_id)
VALUES ('https://bom.so/5Tc3XB', "NO",26);
INSERT INTO image (image,is_delete,product_id)
VALUES ('https://bom.so/1BoTZT',"NO",27);
INSERT INTO image (image,is_delete,product_id)
VALUES ('https://bom.so/BRAPak', "NO",27);
INSERT INTO image (image,is_delete,product_id)
VALUES ('https://bom.so/qKDnhV',"NO",28);
INSERT INTO image (image,is_delete,product_id)
VALUES ('https://bom.so/Kw2k0K',"NO",28);
INSERT INTO image (image,is_delete,product_id)
VALUES ('https://bom.so/05UwtH',"NO",29);
INSERT INTO image (image,is_delete,product_id)
VALUES ('https://bom.so/tv7QDq', "NO",29);
INSERT INTO image (image,is_delete,product_id)
VALUES ('https://bom.so/niZw4Q',"NO",30);
INSERT INTO image (image,is_delete,product_id)
VALUES ('https://bom.so/uSMPhp',"NO",30);
INSERT INTO image (image,is_delete,product_id)
VALUES ('https://bom.so/DiuzJv',"NO",31);
INSERT INTO image (image,is_delete,product_id)
VALUES ('https://bom.so/vKrTDg',"NO",31);

INSERT INTO image (image,is_delete,product_id)
VALUES ('https://bom.so/WuFMe5',"NO",32);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/AqBYU6',"NO",32);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/pRsBmB',"NO",33);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/VNOYCJ', "NO",33);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/1wBZOd',"NO",34);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/jaMTo8', "NO",34);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/bD6Vr5',"NO",35);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/uPqI3Q', "NO",35);

INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/ty1hJ4',"NO",36);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/dDM10I', "NO",36);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/VoZoys',"NO",37);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/7fUJ64',"NO",37);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/g5EYe1',"NO",38);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/PYPJwl',"NO",38);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/Ef6NkX',"NO",39);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/spCcvC', "NO",39);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/lTp6jy',"NO",40);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/FcE1Ki',"NO",40);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/R06w33',"NO",41);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/cHH0HX', "NO",41);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/7ISsER',"NO",42);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/rOiZnz', "NO",42);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/oyEBsB',"NO",43);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/QSFFPV',"NO",43);

INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/r6JFhG',"NO",44);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/YbUsS6', "NO",44);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/EQ6ukW',"NO",45);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/85tteT',"NO",45);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/FvzSTk',"NO",46);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/D94s5G',"NO",46);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/xNU1gz',"NO",47);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/5HCSuB',"NO",47);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/Hsryil',"NO",48);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/R1dWSj',"NO",48);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/2Abjfk',"NO",49);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/UrbHzW',"NO",49);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/gaOgM5',"NO",50);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/zQHJmn', "NO",50);

INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/AM8PwN',"NO",51);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/sKZkjo',"NO",51);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/WZzxJW',"NO",52);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/dtQmMs',"NO",52);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/DGI5lg',"NO",53);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/3ple8B',"NO",53);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/GW7Qa3',"NO",54);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/vcvA2w',"NO",54);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/gis23h',"NO",55);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/RZ0F8X',"NO",55);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/NWWynG',"NO",56);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/CXrOGA',"NO",56);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/yRDbs8',"NO",57);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/QGQiwp',"NO",57);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/p5VaIC',"NO",58);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/c6GKmd',"NO",58);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/suCSvw',"NO",59);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/tK824r',"NO",59);


INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/Df35xb',"NO",60);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/M80qqJ',"NO",60);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/LZK2sm',"NO",61);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/cfXpYq',"NO",61);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/wNaIKw',"NO",62);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/F8N2It',"NO",62);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/Tjpgoy',"NO",63);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/tyJt3f',"NO",63);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/YXSv3d',"NO",64);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/QfBip7',"NO",64);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/yxWsZT',"NO",65);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/N2aB0C',"NO",65);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/Vy92ZK',"NO",66);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/K3XZEg',"NO",66);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/Bnb9Hz',"NO",67);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/XTkzaD',"NO",67);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/tVaa2i',"NO",68);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/EiyIO6',"NO",68);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/DZvRs5',"NO",69);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/sjS3kH',"NO",69);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/RdzhXN',"NO",70);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/tCmAti',"NO",70);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/K5ARbu',"NO",71);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/iaTIF7',"NO",71);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/7gopPs',"NO",72);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/9RX6Dd',"NO",72);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/HVwagD',"NO",73);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/XOSsQn',"NO",73);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/ZVgtXt',"NO",74);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/LUuYIA',"NO",74);


INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/WE6FaV',"NO",75);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/Ecp1E1',"NO",75);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/gCadB2',"NO",76);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/LnUVJV',"NO",76);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/MAtjQe',"NO",77);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/P4Pc2s',"NO",77);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/iQM8XL',"NO",78);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/KzWXio',"NO",78);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/lyXjwh',"NO",79);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/wIVnLe',"NO",79);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/GOfRCs',"NO",80);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/GOfRCs',"NO",80);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/Mhk7lJ',"NO",81);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/M1WBxq',"NO",81);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/mMQZ3l',"NO",82);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/k6Opdx',"NO",82);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/4mZy9u',"NO",83);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/5MeBmI',"NO",83);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/YpqqRT',"NO",84);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/1um9p9',"NO",84);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/pofSru',"NO",85);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/3tVX0j',"NO",85);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/9cc67K',"NO",86);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/VMSCcY',"NO",86);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/0AXsER',"NO",87);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/2A9cbP',"NO",87);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/o2zQHP',"NO",88);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/xR6npD',"NO",88);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/55qibj',"NO",89);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/tYhatI',"NO",89);

INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/ILIkKK',"NO",90);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/45LsmV',"NO",90);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/gmqLgs',"NO",91);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/ipIcSv',"NO",91);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/iQkuRs',"NO",92);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/WI35Pn',"NO",92);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/4PdrXV',"NO",93);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/CCbYAf',"NO",93);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/25qpct',"NO",94);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/QrWJtb',"NO",94);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/7QATbz',"NO",95);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/3fyylX',"NO",95);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/zFqPfo',"NO",96);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/DRgLpB',"NO",96);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/1bGtyf',"NO",97);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/iouZwp',"NO",97);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/nh4wBH',"NO",98);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/EPHj2p',"NO",98);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/PNEahF',"NO",99);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/PNEahF',"NO",99);


INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/b1NcAm',"NO",100);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/4gYbWQ',"NO",100);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/b1NcAm',"NO",101);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/4gYbWQ',"NO",101);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/4v1e7h',"NO",102);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/voaUiA',"NO",102);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/pQB48V',"NO",103);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/kXaq4M',"NO",103);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/YOiCzr',"NO",104);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/I8dF9R',"NO",104);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/uuV79B',"NO",105);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/6saHgN',"NO",105);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/C3WfWt',"NO",106);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/Fa0pTW',"NO",106);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/C3WfWt',"NO",107);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/Fa0pTW',"NO",107);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/H6eKl5',"NO",108);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/yetk2m',"NO",108);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/H6eKl5',"NO",109);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/yetk2m',"NO",109);

INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/H6eKl5',"NO",110);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/yetk2m',"NO",110);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/GYjxmh',"NO",111);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/QAD9Wf',"NO",111);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/GYjxmh',"NO",112);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/QAD9Wf',"NO",112);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/sL5wjy',"NO",113);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/BDKG1k',"NO",113);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/sL5wjy',"NO",114);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/BDKG1k',"NO",114);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/gVNC31',"NO",115);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/iI0ZAW',"NO",115);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/gVNC31',"NO",116);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/iI0ZAW',"NO",116);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/gVNC31',"NO",117);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/iI0ZAW',"NO",117);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/gVNC31',"NO",118);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/iI0ZAW',"NO",118);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/wUf5ki',"NO",119);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/MyaCar',"NO",119);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/wUf5ki',"NO",120);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/MyaCar',"NO",120);

INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/Szjt1D',"NO",121);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/5gmhB3',"NO",121);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/s0Cm59',"NO",122);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/5gmhB3',"NO",122);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/5gmhB3',"NO",123);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/5gmhB3',"NO",123);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/KmSpsd',"NO",124);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/5gmhB3',"NO",124);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/9RBHeq',"NO",125);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/XbfiXD',"NO",125);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/kRLoAu',"NO",126);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/TnpbE4', "NO",126);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/iSmvNc',"NO",127);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/A7BZ7d',"NO",127);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/eHEE5t',"NO",128);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/A7BZ7d',"NO",128);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/eHEE5t',"NO",129);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/A7BZ7d',"NO",129);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/DFtgJQ',"NO",130);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/IiPbb4',"NO",130);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/yBrmKH',"NO",131);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/IiPbb4', "NO",131);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/yBrmKH',"NO",132);
INSERT INTO image (image,is_delete, product_id)
VALUES ('https://bom.so/IiPbb4',"NO",132);



INSERT INTO orders(address,phone_number,create_date, total_amount, status_order,note,payment_method,payment_status, customer_id)
VALUES ("Tam Dân","0326000692","2021-10-5",190000,"Đã giao","","COD","Chưa thanh toán",1);
INSERT INTO orderdetail(order_id,product_id,quantity ,amount,is_delete)
VALUES (1,80,1,190000,"NO");

INSERT INTO orders(address,phone_number,create_date, total_amount, status_order,note,payment_method,payment_status, customer_id)
VALUES ("Tam Thái","0326000693","2021-10-10",252000,"Đã giao","","COD","Đã thanh toán",2);
INSERT INTO orderdetail(order_id,product_id,quantity ,amount,is_delete,is_review)
VALUES (2,82,1,252000,"NO","YES");

INSERT INTO orders(address,phone_number,create_date, total_amount, status_order,note,payment_method,payment_status, customer_id)
VALUES ("Tam Lãnh","0326000694","2021-10-15",859300,"Đã giao","","COD","Đã thanh toán",3);
INSERT INTO orderdetail(order_id,product_id,quantity ,amount,is_delete,is_review)
VALUES (3,95,1,254800,"NO","YES");
INSERT INTO orderdetail(order_id,product_id,quantity ,amount,is_delete,is_review)
VALUES (3,117,1,604500,"NO","YES");

INSERT INTO orders(address,phone_number,create_date, total_amount, status_order,note,payment_method,payment_status, customer_id)
VALUES ("Tam An","0326000695","2021-10-20",20088600,"Đã giao","","COD","Đã thanh toán",4);
INSERT INTO orderdetail(order_id,product_id,quantity ,amount,is_delete,is_review)
VALUES (4,15,1,17846400,"NO","YES");
INSERT INTO orderdetail(order_id,product_id,quantity ,amount,is_delete,is_review)
VALUES (4,84,1,738700,"NO","YES");
INSERT INTO orderdetail(order_id,product_id,quantity ,amount,is_delete,is_review)
VALUES (4,92,1,600000,"NO","YES");
INSERT INTO orderdetail(order_id,product_id,quantity ,amount,is_delete,is_review)
VALUES (4,122,1,903500,"NO","YES");

INSERT INTO orders(address,phone_number,create_date, total_amount, status_order,note,payment_method,payment_status, customer_id)
VALUES ("Tam Thành","0326000696","2021-10-28",31062900,"Đã giao","","COD","Đã thanh toán",1);
INSERT INTO orderdetail(order_id,product_id,quantity ,amount,is_delete,is_review)
VALUES (5,59,1,28820700,"NO","YES");
INSERT INTO orderdetail(order_id,product_id,quantity ,amount,is_delete,is_review)
VALUES (5,84,1,738700,"NO","YES");
INSERT INTO orderdetail(order_id,product_id,quantity ,amount,is_delete,is_review)
VALUES (5,92,1,600000,"NO","YES");
INSERT INTO orderdetail(order_id,product_id,quantity ,amount,is_delete,is_review)
VALUES (5,122,1,903500,"NO","YES");

INSERT INTO orders(address,phone_number,create_date, total_amount, status_order,note,payment_method,payment_status, customer_id)
VALUES ("Tam Đại","0326000697","2021-11-7",15000,"Đã giao","","COD","Đã thanh toán",2);
INSERT INTO orderdetail(order_id,product_id,quantity ,amount,is_delete,is_review)
VALUES (6,28,1,18422400,"NO","YES");
INSERT INTO orderdetail(order_id,product_id,quantity ,amount,is_delete,is_review)
VALUES (6,78,1,4990000,"NO","YES");
INSERT INTO orderdetail(order_id,product_id,quantity ,amount,is_delete,is_review)
VALUES (6,95,1,254800,"NO","YES");
INSERT INTO orderdetail(order_id,product_id,quantity ,amount,is_delete,is_review)
VALUES (6,121,1,1997500,"NO","YES");
INSERT INTO orderdetail(order_id,product_id,quantity ,amount,is_delete,is_review)
VALUES (6,107,1,2185500,"NO","YES");

INSERT INTO orders(address,phone_number,create_date, total_amount, status_order,note,payment_method,payment_status, customer_id)
VALUES ("Phú Ninh","0326000698","2021-11-15",6080900,"Đã giao","","COD","Đã thanh toán",3);
INSERT INTO orderdetail(order_id,product_id,quantity ,amount,is_delete,is_review)
VALUES (7,82,1,252000,"NO","YES");
INSERT INTO orderdetail(order_id,product_id,quantity ,amount,is_delete,is_review)
VALUES (7,83,1,2990000,"NO","YES");
INSERT INTO orderdetail(order_id,product_id,quantity ,amount,is_delete,is_review)
VALUES (7,84,1,1099400,"NO","YES");
INSERT INTO orderdetail(order_id,product_id,quantity ,amount,is_delete,is_review)
VALUES (7,85,1,1000800,"NO","YES");
INSERT INTO orderdetail(order_id,product_id,quantity ,amount,is_delete,is_review)
VALUES (7,86,1,738700,"NO","YES");

INSERT INTO orders(address,phone_number,create_date, total_amount, status_order,note,payment_method,payment_status, customer_id)
VALUES ("Duy Xuyên","0326000699","2021-11-22",2019420,"Đã giao","","COD","Đã thanh toán",4);
INSERT INTO orderdetail(order_id,product_id,quantity ,amount,is_delete,is_review)
VALUES (8,92,1,640000,"NO","YES");
INSERT INTO orderdetail(order_id,product_id,quantity ,amount,is_delete,is_review)
VALUES (8,93,1,340000,"NO","YES");
INSERT INTO orderdetail(order_id,product_id,quantity ,amount,is_delete,is_review)
VALUES (8,94,1,377300,"NO","YES");
INSERT INTO orderdetail(order_id,product_id,quantity ,amount,is_delete,is_review)
VALUES (8,95,1,254800,"NO","YES");
INSERT INTO orderdetail(order_id,product_id,quantity ,amount,is_delete,is_review)
VALUES (8,96,1,407320,"NO","YES");


INSERT INTO orders(address,phone_number,create_date, total_amount, status_order,note,payment_method,payment_status, customer_id)
VALUES ("Thăng Bình","0326000670","2021-11-28",13270400,"Đã giao","","COD","Đã thanh toán",1);
INSERT INTO orderdetail(order_id,product_id,quantity ,amount,is_delete,is_review)
VALUES (9,100,1,1757700,"NO","YES");
INSERT INTO orderdetail(order_id,product_id,quantity ,amount,is_delete,is_review)
VALUES (9,101,1,2315700,"NO","YES");
INSERT INTO orderdetail(order_id,product_id,quantity ,amount,is_delete,is_review)
VALUES (9,102,1,5133600,"NO","YES");
INSERT INTO orderdetail(order_id,product_id,quantity ,amount,is_delete,is_review)
VALUES (9,103,1,1664700,"NO","YES");
INSERT INTO orderdetail(order_id,product_id,quantity ,amount,is_delete,is_review)
VALUES (9,104,1,2398700,"NO","YES");

INSERT INTO orders(address,phone_number,create_date, total_amount, status_order,note,payment_method,payment_status, customer_id)
VALUES ("Quận 9","0326110670","2021-12-1",118081000,"Đã giao","","COD","Đã thanh toán",2);
INSERT INTO orderdetail(order_id,product_id,quantity ,amount,is_delete,is_review)
VALUES (10,40,1,25210300,"NO","YES");
INSERT INTO orderdetail(order_id,product_id,quantity ,amount,is_delete,is_review)
VALUES (10,41,1,22217700,"NO","YES");
INSERT INTO orderdetail(order_id,product_id,quantity ,amount,is_delete,is_review)
VALUES (10,42,1,35880300,"NO","YES");
INSERT INTO orderdetail(order_id,product_id,quantity ,amount,is_delete,is_review)
VALUES (10,43,1,24087000,"NO","YES");
INSERT INTO orderdetail(order_id,product_id,quantity ,amount,is_delete,is_review)
VALUES (10,44,1,10685700,"NO","YES");

INSERT INTO orders(address,phone_number,create_date, total_amount, status_order,note,payment_method,payment_status, customer_id)
VALUES ("Quận 5","0326110670","2021-12-8",108141770,"Đã giao","","COD","Đã thanh toán",2);
INSERT INTO orderdetail(order_id,product_id,quantity ,amount,is_delete,is_review)
VALUES (11,73,1,19169400,"NO","YES");
INSERT INTO orderdetail(order_id,product_id,quantity ,amount,is_delete,is_review)
VALUES (11,74,1,69740700,"NO","YES");
INSERT INTO orderdetail(order_id,product_id,quantity ,amount,is_delete,is_review)
VALUES (11,75,1,3072300,"NO","YES");
INSERT INTO orderdetail(order_id,product_id,quantity ,amount,is_delete,is_review)
VALUES (11,76,1,5039370,"NO","YES");
INSERT INTO orderdetail(order_id,product_id,quantity ,amount,is_delete,is_review)
VALUES (11,77,1,11120000,"NO","YES");


INSERT INTO orders(address,phone_number,create_date, total_amount, status_order,note,payment_method,payment_status, customer_id)
VALUES ("Quận Tân Bình","0326110670","2021-12-15",3075230,"Đã giao","","COD","Đã thanh toán",2);
INSERT INTO orderdetail(order_id,product_id,quantity ,amount,is_delete,is_review)
VALUES (12,90,1,999600,"NO","YES");
INSERT INTO orderdetail(order_id,product_id,quantity ,amount,is_delete,is_review)
VALUES (12,91,1,890000,"NO","YES");
INSERT INTO orderdetail(order_id,product_id,quantity ,amount,is_delete,is_review)
VALUES (12,92,1,600000,"NO","YES");
INSERT INTO orderdetail(order_id,product_id,quantity ,amount,is_delete,is_review)
VALUES (12,93,1,340000,"NO","YES");
INSERT INTO orderdetail(order_id,product_id,quantity ,amount,is_delete,is_review)
VALUES (12,94,1,245630,"NO","YES");

INSERT INTO orders(address,phone_number,create_date, total_amount, status_order,note,payment_method,payment_status, customer_id)
VALUES ("Quận Gò Vấp","0326110670","2021-12-24",101628500,"Đã giao","","COD","Đã thanh toán",2);
INSERT INTO orderdetail(order_id,product_id,quantity ,amount,is_delete,is_review)
VALUES (13,7,1,28341000,"NO","YES");
INSERT INTO orderdetail(order_id,product_id,quantity ,amount,is_delete,is_review)
VALUES (13,8,1,19845500,"NO","YES");
INSERT INTO orderdetail(order_id,product_id,quantity ,amount,is_delete,is_review)
VALUES (13,9,1,21466100,"NO","YES");
INSERT INTO orderdetail(order_id,product_id,quantity ,amount,is_delete,is_review)
VALUES (13,10,1,17288700,"NO","YES");
INSERT INTO orderdetail(order_id,product_id,quantity ,amount,is_delete,is_review)
VALUES (13,11,1,14687200,"NO","YES");

INSERT INTO orders(address,phone_number,create_date, total_amount, status_order,note,payment_method,payment_status, customer_id)
VALUES ("Buôn Mê Thuột","0326110670","2021-12-27",91399700,"Đã duyệt","","COD","Chưa thanh toán",2);
INSERT INTO orderdetail(order_id,product_id,quantity ,amount,is_delete,is_review)
VALUES (14,90,1,999600,"NO","YES");
INSERT INTO orderdetail(order_id,product_id,quantity ,amount,is_delete,is_review)
VALUES (14,91,1,890000,"NO","YES");
INSERT INTO orderdetail(order_id,product_id,quantity ,amount,is_delete,is_review)
VALUES (14,92,1,600000,"NO","YES");
INSERT INTO orderdetail(order_id,product_id,quantity ,amount,is_delete,is_review)
VALUES (14,73,1,19169400,"NO","YES");
INSERT INTO orderdetail(order_id,product_id,quantity ,amount,is_delete,is_review)
VALUES (14,74,1,69740700,"NO","YES");

INSERT INTO orders(address,phone_number,create_date, total_amount, status_order,note,payment_method,payment_status, customer_id)
VALUES ("Lê Văn Việt, Quận 9","0326110670","2021-12-29",69740700,"Đã duyệt","","COD","Chưa thanh toán",2);
INSERT INTO orderdetail(order_id,product_id,quantity ,amount,is_delete,is_review)
VALUES (15,74,1,69740700,"NO","YES");