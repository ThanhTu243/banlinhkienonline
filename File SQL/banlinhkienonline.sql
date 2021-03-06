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
    CONSTRAINT OR_activeORDER_CHK CHECK (status_order IN ('Ch??a duy???t','???? duy???t', '???? giao','???? h???y','???? x??a')),
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
VALUES("huynhphucadmin","$2y$12$n1Ea46fRM5OHjcldln9FCeDNKGWPlBTon6QSLWFiZQ70Jz7up77d.","Hu???nh", "Ng???c Ph??c","huynhphucadmin@gmail.com","ACTIVE","LOCAL","ADMIN","Qu???n 9","0322000568");
INSERT INTO accounts(username, passwords,firstname,lastname,gmail, active_account,provider, roles,address,phonenumber)
VALUES("thanhtuadmin","$2y$12$vlL2nNxUnnFgwPYZ3DwS2.DukpDpA3K7ZzPwnBzxlATAX.wHp48dO","Nguy???n", "Thanh T??","thanhtuadmin@gmail.com","ACTIVE","LOCAL","ADMIN","Qu???n 10","0322010568");
INSERT INTO accounts(username, passwords,firstname,lastname,gmail, active_account,provider, roles,address,phonenumber)
VALUES("ngoctinhadmin","$2y$12$E08DvJWGdIWEhAxFVoUVNu7MKtI2cCpCBRtPuV/cnkumAvrpIXHAW","Nguy???n","Ng???c T??nh","ngoctinhadmin@gmail.com","ACTIVE","LOCAL","ADMIN","Qu???n 11","0322100568");
INSERT INTO accounts(username, passwords,firstname,lastname,gmail, active_account,provider, roles,address,phonenumber)
VALUES("huynhngocphuc@gmail.com","$2y$12$0FFBQhnloHEL3Jkn/FdzTO9zEZJmMdXW4wMSubG/FwatiVm/XyWX6","Hu???nh","Ng???c Ph??c","huynhngocphuc@gmail.com","ACTIVE","LOCAL","CUSTOMER","Bu??n M?? Thu???t","0326000587");
INSERT INTO accounts(username, passwords,firstname,lastname,gmail, active_account,provider, roles,address,phonenumber)
VALUES("nguyenthanhtu@gmail.com","$2y$12$syXsrDwQo9qhYW6a9XZHkexaZDautIUFDyrxJC.NBRGl1smhkFVy6","Nguy???n","Thanh T??","nguyenthanhtu@gmail.com","ACTIVE","LOCAL","CUSTOMER","Qu???ng Nam","0326010587");
INSERT INTO accounts(username, passwords,firstname,lastname,gmail, active_account,provider, roles,address,phonenumber)
VALUES("ntthanhthao31@gmail.com","$2y$12$c3e35UG2lL4lhTM1iCcS7OaNQRimhgijW7Fn/NrTjXZu3YfxAFEvO","Nguy???n","Th??? Thanh Th???o","ntthanhthao31@gmail.com","ACTIVE","LOCAL","CUSTOMER","Qu???ng Nam","0867832447");
INSERT INTO accounts(username, passwords,firstname,lastname,gmail, active_account,provider, roles,address,phonenumber)
VALUES("chaugiakiet55533395@gmail.com","$2y$12$bbLhM88F0bKdOYGKg4vMKOZ20NLsllTDLKpK.FMxkKDkoH9/eI0Ma","Ch??u","Gia Ki???t","chaugiakiet55533395@gmail.com","ACTIVE","LOCAL","CUSTOMER","Tam Ky","032111587");
INSERT INTO accounts(username, passwords,firstname,lastname,gmail, active_account,provider, roles,address,phonenumber)
VALUES("halamhy@gmail.com","$2y$12$L6G1I3WH/99ZL0y5B0WYzOOxicSniPe1vy8/tC75ev8FeqKg00a5u","halamhy@gmail.com","H???","L??m Hy","ACTIVE","LOCAL","CUSTOMER","Tam Th??i","0333000698");
INSERT INTO accounts(username, passwords,firstname,lastname,gmail, active_account,provider, roles,address,phonenumber)
VALUES("trantrietvien@gmail.com","$2y$12$MyMaqVtRK9d39.aoJhKBS.pp0I7aoHCWmhPdA5Jr7P9eslPh6reGW","Tr???n","Tri???t Vi???n","trantrietvien@gmail.com","ACTIVE","LOCAL","CUSTOMER","B???c Li??u","0333100698");
INSERT INTO accounts(username, passwords,firstname,lastname,gmail, active_account,provider, roles,address,phonenumber)
VALUES("tumongkhiet@gmail.com","$2y$12$Wb9boDgvXQKuSLl0Gy3JN.qUJ24.b/iXGoF/QLrcwOxNuf0Zx1VjC","T???","M??ng Khi???t","tumongkhiet@gmail.com","ACTIVE","LOCAL","CUSTOMER","B???n Tre","0333110698");
INSERT INTO accounts(username, passwords,firstname,lastname,gmail, active_account,provider, roles,address,phonenumber)
VALUES("thanhlong@gmail.com","$2y$12$GD9bewiDJoSgISxzXIpZF.fJPsicaBHT4CKc5i2vHdRSEJMN.tPNe","Th??nh","Long","thanhlong@gmail.com","ACTIVE","LOCAL","CUSTOMER","B??nh D????ng ","0334000698");
INSERT INTO accounts(username, passwords,firstname,lastname,gmail, active_account,provider, roles,address,phonenumber)
VALUES("vuongtrachhien@gmail.com","$2y$12$P2.cuN6ZABetZgDIZfNY6uUWWlVM2k/Laq1RG.PUx53ub3DY9vedm","V????ng","Tr??ch Hi???n","vuongtrachhien@gmail.com","ACTIVE","LOCAL","CUSTOMER","H??? Ch?? Minh","0333200698");
INSERT INTO accounts(username, passwords,firstname,lastname,gmail, active_account,provider, roles,address,phonenumber)
VALUES("vuongnhatlam@gmail.com","$2y$12$v/3DMekr0H8LmtzpbzPVkuu2mHdjUVtop76Ep3fYtm/tygXvl7Inm","V????ng","Nh???t L??m","vuongnhatlam@gmail.com","ACTIVE","LOCAL","CUSTOMER","V??ng T??u","0343000698");
INSERT INTO accounts(username, passwords,firstname,lastname,gmail, active_account,provider, roles,address,phonenumber)
VALUES("vuonganvu@gmail.com","$2y$12$GY8rHWAFPwBkKvAko.NylOFoMQAuRkLT80vvWQ/dzaqk0fD8f.0GK","V????ng","An V??","vuonganvu@gmail.com","ACTIVE","LOCAL","CUSTOMER","Tam An","0333000608");
INSERT INTO accounts(username, passwords,firstname,lastname,gmail, active_account,provider, roles,address,phonenumber)
VALUES("huonghamchi@gmail.com","$2y$12$/CeNbvY00r3WlcoudUbiPeZpv31VAq5tI6sxMgoTuzLftVLjc0Lb6","H?????ng","H??m Chi","huonghamchi@gmail.com","ACTIVE","LOCAL","CUSTOMER","Tam Ph??","0333111698");


INSERT INTO customer(user_customer, firstname_customer,lastname_customer, address,gmail_customer,phonenumber_customer, is_delete)
VALUES("huynhngocphuc@gmail.com","Hu???nh","Ng???c Ph??c","Bu??n M?? Thu???t","huynhngocphuc@gmail.com","0326000587","NO");
INSERT INTO customer(user_customer, firstname_customer,lastname_customer, address,gmail_customer,phonenumber_customer, is_delete)
VALUES("nguyenthanhtu@gmail.com","Nguy???n","Thanh T??","Qu???ng Nam","nguyenthanhtu@gmail.com","0326010587","NO");
INSERT INTO customer(user_customer, firstname_customer,lastname_customer, address,gmail_customer,phonenumber_customer, is_delete)
VALUES("ntthanhthao31@gmail.com","Nguy???n","Th??? Thanh Th???o","Qu???ng Nam","ntthanhthao31@gmail.com","0867832447","NO");
INSERT INTO customer(user_customer,firstname_customer,lastname_customer, address,gmail_customer,phonenumber_customer, is_delete)
VALUES("chaugiakiet55533395@gmail.com","Ch??u","Gia Ki???t","Tam Ky","chaugiakiet55533395@gmail.com","032111587","NO");
INSERT INTO customer(user_customer, firstname_customer,lastname_customer, address,gmail_customer,phonenumber_customer, is_delete)
VALUES("halamhy@gmail.com","H???","L??m Hy","Tam Th??i","halamhy@gmail.com","0333000698","NO");
INSERT INTO customer(user_customer, firstname_customer,lastname_customer, address,gmail_customer,phonenumber_customer, is_delete)
VALUES("trantrietvien@gmail.com","Tr???n","Tri???t Vi???n","B???c Li??u","trantrietvien@gmail.com","0333100698","NO");
INSERT INTO customer(user_customer, firstname_customer,lastname_customer, address,gmail_customer,phonenumber_customer, is_delete)
VALUES("tumongkhiet@gmail.com","T???","M???ng Khi???t","B???n Tre","tumongkhiet@gmail.com","0333110698","NO");
INSERT INTO customer(user_customer, firstname_customer,lastname_customer, address,gmail_customer,phonenumber_customer, is_delete)
VALUES("thanhlong@gmail.com","Th??nh","Long","B??nh D????ng ","thanhlong@gmail.com","0334000698","NO");
INSERT INTO customer(user_customer, firstname_customer,lastname_customer, address,gmail_customer,phonenumber_customer, is_delete)
VALUES("vuongtrachhien@gmail.com","V????ng","Tr??ch Hi???n","H??? Ch?? Minh","vuongtrachhien@gmail.com","0333200698","NO");
INSERT INTO customer(user_customer, firstname_customer,lastname_customer, address,gmail_customer,phonenumber_customer, is_delete)
VALUES("vuongnhatlam@gmail.com","V????ng","Nh???t L??m","V??ng T??u","vuongnhatlam@gmail.com","0343000698","NO");
INSERT INTO customer(user_customer, firstname_customer,lastname_customer, address,gmail_customer,phonenumber_customer, is_delete)
VALUES("vuonganvu@gmail.com","V????ng","An V??","Tam An","vuonganvu@gmail.com","0333000608","NO");
INSERT INTO customer(user_customer, firstname_customer,lastname_customer, address,gmail_customer,phonenumber_customer, is_delete)
VALUES("huonghamchi@gmail.com","H?????ng","H??m Chi","Tam Ph??","huonghamchi@gmail.com","0333111698","NO");


INSERT INTO category (category_name,is_delete)
VALUES ('Laptop',"NO");
INSERT INTO category (category_name,is_delete)
VALUES ('Tai nghe',"NO");
INSERT INTO category (category_name,is_delete)
VALUES ('Chu???t',"NO");
INSERT INTO category(category_name,is_delete)
VALUES('??? c???ng SSD',"NO");
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
VALUES ( 1, 1, 'Laptop HP 14-DQ2055WM 39K15UA', 100, 10, 12490000, 'Ph??? nh??m b???n b???, kh??ng gian tr???i nghi???m ????? ?????y</br>Hi???u n??ng l???p ?????y nhu c???u ng?????i d??ng',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 1, 'Laptop HP 15S-FQ2602TU 4B6D3PA', 100, 0, 16390000, 'C???u h??nh ???n ?????nh v???i chip intel th??? h??? 11</br>K??ch th?????c m??n h??nh 15,6 inches v?? ????? ph??n gi???i Full HD',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 1, 'Laptop HP Pavilion x360 14-DW1018TU 2H3N6PA', 100, 6, 17990000, 'M??n h??nh 14 inch nh??? g???n, h??? tr??? g???p l??n ?????n 360 ?????</br>Chip Intel 5 th??? h??? 11, ??? c???ng SSD 512GB',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 1, 'Laptop HP 15-DY2045 2Q1H3UA', 100, 6, 17490000, 'Thi???t k??? c???ng c??p, tr???i nghi???m h??nh ???nh kh??ng gian l???n</br>Chip Intel th??? h??? 11 v?? RAM l???n cho s???c m???nh ???n t?????ng, pin th???i l?????ng c???c l??u',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 1, 'Laptop HP Probook 430 G8 2H0N6PA', 100, 0, 18590000, 'Laptop HP Probook 430 G8- Thi???t k??? m??u b???c sang tr???ng, g???n nh???</br>H??? tr??? ?????y ????? c??c c???ng k???t n???i</br>M??n h??nh r???ng full HD v?? vi???n m??n h??nh si??u m???ng</br>Dung l?????ng pin l???n - K???t n???i kh??ng d??y si??u t???c',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 1, 'Laptop HP Envy 13 AQ1022TU 8QN69PA', 100, 0, 22690000, '',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 1, 'Laptop HP Zbook Firefly 14 G8 1A2F1AV', 100, 10, 31490000, 'Thi???t k??? m???ng nh???, m??n h??nh 14 inch nh??? g???n, ????? s??ng cao</br>Hi???u n??ng ???n ?????nh v???i chip Intel th??? h??? 11, SSD 512GB',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 1, 'Laptop HP 340S G7 36A37PA', 100, 5, 20890000, 'Thi???t k??? g???n nh???, m??n h??nh 14 inch nh??? g???n, hi???n th??? s???c n??t</br>Hi???u n??ng si??u m???nh v???i CPU i7 th??? h??? 10, SSD dung l?????ng cao',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 1, 'Laptop HP 348 G7 9PH23PA', 100, 3, 22130000, 'B??? n??o Intel Core i7 10510U, Ram DDR4 8GB</br>Si??u b???n b??? ti??u chu???n qu??n ?????i, b???o m???t an to??n',"NO");
-- Dell Laptop
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 2, 'Laptop Dell Inspiron 3501 5580BLK', 100, 7, 18590000, 'Thi???t k??? g???n nh???, m??n h??nh k??ch th?????c l???n</br>Hi???u n??ng m?????t c??ng vi??n pin ????? d??ng',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 2, 'LAPTOP DELL INSPIRON 3505 i3505-A542BLK', 100, 12, 16690000, 'Thi???t k??? m???ng nh???, m??n h??nh k??ch th?????c l???n v???i vi???n si??u m???ng</br>Hi???u n??ng ???n ?????nh trong t???m gi?? v???i con chip AMD Ryzen 5 v?? SSD PCIE',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 2, 'Laptop Dell Inspiron 7306 N3I5202W', 100, 6, 26590000, 'M??n h??nh FHD 13.3 inch nh??? g???n, t???n s??? qu??t 60 Hz, g??c c???nh bo tr??n</br>Hi???u n??ng m???nh m??? v???i Intel Core i5-1135G7',"NO");

INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 2, 'Laptop Dell Vostro 3405 V4R53500U003W', 100, 8, 1699000, '',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 2, 'Laptop Dell Vostro 3500 P112F002ABL', 100, 3, 22190000, 'Thi???t k??? ????n gi???n v???i m??u ??en sang tr???ng, ch???t li???u c???ng c??p</br>Hi???u n??ng m???nh m??? v???i b??? vi x??? l?? kh???e, b??? nh??? SSD dung l?????ng cao',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 2, 'Laptop Dell Vostro 3400 70234073', 100, 4, 18590000, 'M???ng nh??? tinh t???, m??n h??nh ch???t l?????ng</br>Hi???u n??ng h??ng ?????u',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 2, 'Laptop Dell Vostro 3510 P112F002BBL', 100, 4, 21990000, 'Thi???t k??? g???n g??ng, m???ng nh???</br>C???u h??nh m???nh m???, ???n ?????nh</br>H??nh ???nh ?????c s???c, ??m thanh ???n t?????ng',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 2, 'Laptop Dell Vostro 5510 70253901', 100, 5, 21690000, 'Ki????u da??ng tinh t????, ma??n hi??nh r????ng l????n</br>Hi????u n??ng phu?? h????p c??ng vi????c ki??m ho??c t????p',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 2, 'Laptop Dell Vostro 5402 V5402A', 100, 5, 21790000, 'Laptop m???ng nh???, m??n h??nh 14 inch nh??? g???n</br>Hi???u n??ng m?????t m?? v???i chip Intel th??? h??? 11',"NO");

INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 2, 'Laptop Dell Gaming G5 5500 P89F003', 100, 3, 33610000, '',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 2, 'Laptop Dell Gaming G15 5511 P105F006BGR', 100, 7, 33990000, '',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 2, 'Laptop Dell Gaming G15 5515 P105F004DGR', 100, 5, 27190000, 'Thi???t k??? ?????m ch???t gaming, m??n h??nh 120Hz m?????t m??</br>Ho???t ?????ng ???n ?????nh v???i CPU AMD k???t h???p card ????? h???a chuy??n nghi???p',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 2, 'Laptop Dell Alienware M15 R6 70262923', 100, 7, 64090000, 'Thi???t k??? cao c???p v???i kh??? n??ng t???n nhi???t v?????t tr???i</br>M??n h??nh chu???n 2K s???c n??t, c??ng hi???u su???t v?????t tr???i',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 2, 'Laptop Dell Gaming G3 G3500CW', 100, 7, 2590000, 'Thi???t k??? g???n nh???, m??n h??nh k??ch th?????c l???n</br>Hi???u n??ng m?????t c??ng vi??n pin ????? d??ng',"NO");

-- ASUS VIVOBOOK
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 3, 'Laptop ASUS VivoBook 15 A515EA', 100, 0, 15360000, '',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 3, 'Laptop ASUS VivoBook X415MA-BV088T', 100, 0, 9390000, '',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 3, 'Laptop ASUS VivoBook Flip TM420IA-EC031T', 100, 0, 17190000, 'Thi???t k??? m???ng nh???, b???n l??? quay 360 ???n t?????ng',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 3, 'Laptop ASUS Vivobook R565EA-UH31T', 100, 16, 12490000, 'Thi???t k??? g???n nh???, m??n h??nh k??ch th?????c l???n</br>Hi???u n??ng m?????t c??ng vi??n pin ????? d??ng',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 3, 'Laptop ASUS VivoBook Flip 14 TM420IA-EC227T', 100, 4, 19190000, 'S??? d???ng CPU ?????n t??? AMD</br>M??n h??nh vi???n m???ng, g???n nh??? ???n t?????ng</br>M??u s???c c?? t??nh, sang tr???ng, tr???ng l?????ng nh??? d??? d??ng di chuy???n',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 3, 'Laptop ASUS VivoBook S433EA', 100, 0, 19490000, 'K??ch th?????c nh??? g???n, m??n h??nh NanoEdge 14 inch Full HD s???c n??t</br>H??? tr??? nhi???u c???ng k???t n???i, c??ng ngh??? ASUS SonicMaster s???ng ?????ng, pin 3 Cells 50WHrs',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 3, 'Laptop ASUS VivoBook M513UA-L1240T', 100, 7, 19690000, 'Thi???t k??? m???ng nh???, linh ho???t c??ng m??u ??nh kim thanh l???ch</br>Hi???u n??ng kh???e kho???n v???i chip AMD Ryzen, ??? c???ng SSD ?????c ghi d??? li???u t???c ????? cao',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 3, 'Laptop ASUS VivoBook 14 A415EA-EB360T', 100, 7, 18290000, 'Tr???ng l?????ng ch??? 1.4kg, m??n h??nh NanoEdge 14 inches</br>S??? h???u hi???u n??ng t???t: Intel Core-i5, VGA Iris Xe Graphics, 8GB RAM',"NO");

-- ASUS ZENBOOK
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 3, 'Laptop ASUS Zenbook UX425EA', 100, 7, 23390000, 'Ve?? ngoa??i hi????n ??a??i va?? c????ng ca??p v????i ma??n hi??nh 14 inch FHD</br>Vi x???? ly?? ma??nh me?? ke??m ti????n i??ch ph????n c????ng l????n ph????n m????m',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 3, 'Laptop ASUS ZenBook UX325EA-KG363T', 100, 4, 23290000, 'B??? vi x??? l?? v?????t tr???i gi??p n??ng cao hi???u su???t c??ng vi???c</br>Thi???t k??? g???n nh???, thanh l???ch',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 3, 'Laptop ASUS ZenBook Flip UX363EA', 100, 2, 26690000, 'Thi????t k???? c????ng ca??p & linh hoa??t v????i ma??n hi??nh xoay ng??????c 360 ??????</br>Vi x???? ly?? Intel Core i5-1135G7, RAM 8GB va?? 512GB SSD cho hi????u n??ng la??m vi????c t????t',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 3, 'Laptop ASUS Zenbook Duo 14 UX482EG', 100, 8, 32990000, 'Thi????t k???? ma??n hi??nh ke??p ScreenPad??? Plus v????i ?????? ph??n gia??i Full HD s????c ne??t</br>Vi x???? ly?? Intel Core i5-1135G7 cu??ng ?????? ho??a GeForce MX450 2GB v????n ha??nh ma??nh me??',"NO");

-- ASUS GAMING
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 3, 'Laptop ASUS Gaming ROG Strix G15 G513IH-HN015T', 100, 5, 22690000, 'Thi???t k??? sang tr???ng, logo Gaming ROG, vi???n m??n h??nh si??u m???ng</br>Ho???t ?????ng ???n ?????nh v???i chip H, h??? th???ng 2 loa ch???t l?????ng',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 3, 'Laptop ASUS Gaming ROG Zephyrus G14 GA401QH-HZ035T', 100, 4, 27590000, 'Thi???t k??? ?????c ????o, tr???ng l?????ng nh???</br>Hi???u n??ng m???nh m??? v???i chip x??? l?? nhanh ch??ng, dung l?????ng RAM v?? ROM l???n',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 3, 'Laptop ASUS Gaming FX506LH-HN002T', 100, 5, 20890000, 'Ki???u d??ng gaming sang tr???ng, m??n h??nh h??? tr??? 4K, t???n s??? qu??t 144 Hz</br>S???c m???nh ???n t?????ng b???i Core i5-10300H, GeForce GTX 1650, 8 GB RAM',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 3, 'Laptop Asus TUF Gaming F15 FX506HC-HN002T', 100, 7, 24390000, 'Hi???u n??ng ?????nh cao, t???n s??? qu??t 144Hz</br>B???n b??? v???i dung l?????ng pin d??i, t??nh n??ng t???n nhi???t hi???u qu???',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 3, 'Laptop ASUS TUF Gaming FA506IU-AL127T', 100, 3, 25990000, 'Thi???t k??? sang tr???ng ch???t ri??ng, ????? b???n ?????t chu???n qu??n ?????i M???</br>M??n h??nh r???ng 15.6 inch Full HD 144Hz, Card NVIDIA GeForce GTX 1660Ti 6GB ',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 3, 'Laptop ASUS TUF DASH F15 FX516PC-HN002T', 100, 7, 23890000, '',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 3, 'Laptop ASUS ExpertBook B9400CEA-KC0790T', 100, 3, 36990000, 'Thi???t k??? g???n nh???, m??n h??nh k??ch th?????c l???n</br>Hi???u n??ng m?????t c??ng vi??n pin ????? d??ng',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 3, 'Laptop Asus ExpertBook B5302FEA LF0749W', 100, 7, 25900000, '',"NO");

-- ACER
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 4, 'Laptop Acer Aspire 3 A315-56-37DV', 100, 7, 11490000, 'Thi???t k??? m???ng nh???, c???ng c??p</br>C???u h??nh ???n ?????nh v???i chip Intel Core i3-1005G1',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 4, 'Laptop Acer Aspire 5 A514-54-540F', 100, 2, 18090000, '',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 4, 'Laptop Acer Gaming Aspire 7 A715-42G-R4ST', 100, 7, 18690000, '',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 4, 'Laptop Acer Swift 3 SF314-43-R4X3', 100, 4, 19490000, 'Thi???t k??? m???ng nh???, hi???n ?????i c??ng m??n h??nh r?? n??t</br>Hi???u n??ng ???n ?????nh v???i chipset AMD Ryzen 5, RAM 16GB',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 4, 'Laptop Acer Swift 3X SF314-510G-57MR', 100, 10, 20390000, 'Thi???t k??? nguy??n kh???i ??? m??n h??nh tr??n vi???n</br>Hi???u n??ng m???nh v???i chip Intel th??? h??? 11, card vga r???i Intel XE Max',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 4, 'Laptop Acer Swift 5 SF514-55T-51NZ', 100, 7, 22690000, 'Thi???t k??? g???n nh???, m??n h??nh k??ch th?????c l???n</br>Dung l?????ng pin s???n s??ng cho ng??y d??i, b???o m???t v??n tay an to??n tuy???t ?????i',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 4, 'Laptop Gaming Acer Nitro 5 Eagle AN515-57-74NU', 100, 7, 27590000, 'M??n h??nh FHD IPS r???ng l???n, t???c ????? l??m m???i 144Hz</br>B??? vi x??? l?? th??? h??? 11 - T???n nhi???t hi???u su???t ???n t?????ng',"NO");


-- MSI GAMING
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 5, 'Laptop MSI Gaming BRAVO 15 A4DCR-270VN', 100, 7, 18790000, 'M??n h??nh 15.6" h??? tr??? c??ng ngh??? Freesynce, t???n s??? qu??t m??n h??nh 144Hz</br>Vi???n m??n h??nh si??u m???ng, thi???t k??? v??? nh??m cao c???p',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 5, 'Laptop MSI Gaming GL75 Leopard 10SCSK 056VN', 100, 7, 21090000, 'Thi???t k??? nh??? g???n, m??n h??nh ?????n 17.3 inches</br>C???u h??nh m???nh m??? v???i Core i5-10200H, 8GB RAM, 512GB SSD, VGA GTX 1650 Ti',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 5, 'Laptop MSI Gaming GF65 THIN 10UE-286VN', 100, 12, 26490000, 'Thi????t k???? c????ng ca??p va?? h????m h????, ma??n hi??nh 15.6 inch 144 Hz cho hi??nh a??nh m??????t ma??</br>C??ng pha?? mo??i t????a game v????i CPU Intel Core i5-10500H va?? ?????? ho??a GeForce RTX 3060',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 5, 'Laptop MSI Gaming Alpha 15 A4DEK-027VN', 100, 20, 27590000, 'M??n h??nh kh???ng, t???n s??? qu??t 144 Hz, b??n ph??m t??y ch???nh s???c ?????</br>C???u h??nh m???nh m??? b???i AMD Ryzen 7, t???n nhi???t Cooler Boost 5',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 5, 'Laptop MSI Katana GF66 11UC-641VN', 100, 7, 28490000, 'Thi???t k??? s???c b??n, m??n h??nh s??ng b??ng nh?? l?????i g????m</br>Khai th??c to??n b??? ti???m n??ng c???a ki???m s??',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 5, 'Laptop MSI Creator M16 A11UD-694VN', 100, 7, 36490000, 'M??u s???c h??nh ???nh trung th???c, s???ng ?????ng</br>??m thanh s???ng ?????ng, dung l?????ng pin l???n',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 5, 'Laptop MSI Gaming GP76 Leopard 11UG-280VN', 100, 12, 46990000, 'B??? vi x??? l?? m???i nh???t, c??n m???i t???a game</br>Thi???t k??? ?????c ????o, t???c ????? l??m t????i ???n t?????ng',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 5, 'Laptop MSI Gaming GS66 Stealth 10UE-200VN', 100, 13, 51990000, 'Thi???t k??? m???nh m???, pin kh???ng, h??nh ???nh ??m thanh s???ng ?????ng</br>Chi???n game c???c m?????t v???i vi x??? l?? Intel Core i7, k???t n???i wifi 6 v?????t tr???i',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 5, 'Laptop MSI Gaming Pulse GL66 11UDK-255VN', 100, 7, 30990000, 'Ma??n hi??nh si??u m??????t, ta??n nhi????t si??u ma??t</br>Hi????u n??ng si??u ma??nh cho mo??i ta??c vu??',"NO");

-- LAPTOP LENOVO
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 6, 'Laptop Lenovo Ideapad 3 14ALC6', 100, 7, 14190000, 'Thi???t k??? m???ng nh???, v??? ?????p tinh t??? v???i m??n h??nh gi???i tr?? tuy???t v???i</br>T??ch h???p hi???u n??ng m???nh m??? v???i c??c c???ng k???t n???i ?????y ti???n ??ch',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 6, 'Laptop Lenovo Ideapad 5 15ITL05 82FG00M5VN', 100, 5, 18890000, 'Ngo???i h??nh sang tr???ng, h??nh ???nh s???c n??t</brChip Intel Core i5, Ram 8GB mang ?????n hi???u n??ng m???nh m???',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 6, 'Laptop Lenovo Ideapad Slim 3 15ARE05 81W4009FVN', 100, 0, 13290000, 'Thi???t k??? g???n nh???, m??n h??nh k??ch th?????c l???n</br>Hi???u n??ng m?????t c??ng vi??n pin ????? d??ng',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 6, 'Laptop Lenovo Ideapad S340-13IML', 100, 3, 11990000, 'Thi???t k??? g???n nh???, m??n h??nh k??ch th?????c l???n</br>Hi???u n??ng m?????t c??ng vi??n pin ????? d??ng',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 6, 'Laptop Lenovo Ideapad 5 Pro 14ACN6 82l7007XVN', 100, 3, 21990000, 'Thi???t k??? g???n nh???, m??n h??nh k??ch th?????c l???n</br>Hi???u n??ng m?????t c??ng vi??n pin ????? d??ng',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 6, 'Laptop Lenovo Ideapad 1 11IGL05', 100, 20, 8790000, 'Ki????u da??ng xa??ch tay nhe?? go??n, ma??n hi??nh b????t m????t</brHi????u n??ng ????n ??i??nh phu??c vu?? l??u da??i',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 6, 'Laptop Lenovo Thinkbook 15 G2 ITL', 100, 5, 20590000, 'Th????i th??????ng va?? mo??ng go??n, v????i ma??n hi??nh 15.6 inch ba??o v???? m????t hi????n ??a??i</br>La??m vi????c t????i ??u v????i chip Intel Core i7-1165G7, 512 GB SSD cu??ng Windows 10',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 6, 'Laptop Lenovo ThinkPad E14', 100, 15, 13490000, 'Dung l?????ng kh?? ???n t?????ng v???i RAM 8GB v?? ??? c???ng 1TB</br>M??n h??nh c?? k??ch th?????c 14 inch, s??? d???ng c??ng ngh??? ch???ng ch??i',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 6, 'Laptop Lenovo Thinkpad E490S 20NGS01K00', 100, 17, 15190000, 'Hi??nh da??ng thanh li??ch, thi????t k???? mo??ng go??n, ti??ch h????p nh????n da??ng v??n tay</br>Ma??n hi??nh 14 inch FHD thi????t k???? vi????n mo??ng giu??p quan sa??t tro??n ve??n hi??nh a??nh',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 6, 'Laptop Lenovo Thinkpad X13 GEN 2', 100, 8, 33590000, 'Nh??? g???n tinh t???, m??n h??nh ???n t?????ng</br>S???c m???nh b???t ph??, tr???i nghi???m ?????c bi???t',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 6, 'Laptop Lenovo Ideapad Gaming 3 15IMH05 81Y400Y8VN', 100, 8, 20590000, 'M??n h??nh k??ch th?????c l???n, t???n s??? qu??t 120Hz</br>C???u h??nh m???nh v???i chip Intel core i5 th??? h??? 10, SSD 512GB',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 6, 'Laptop Lenovo Gaming Legion 5 15ARH05 82B500GTVN', 100, 7, 24590000, 'Thi???t k??? g???n nh???, m??n h??nh k??ch th?????c l???n</br>Hi???u n??ng m?????t c??ng vi??n pin ????? d??ng',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 6, 'Laptop Lenovo Gaming L340-15IRH 81LK01J3VN', 100, 15, 16990000, 'Thi???t k??? g???n nh???, m??n h??nh k??ch th?????c l???n</br>Hi???u n??ng m?????t c??ng vi??n pin ????? d??ng',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 6, 'Laptop Lenovo Ideapad Gaming 3 15IMH05 81Y400Y9VN', 100, 14, 22290000, 'Thi???t k??? g???n nh???, m??n h??nh k??ch th?????c l???n</br>Hi???u n??ng m?????t c??ng vi??n pin ????? d??ng',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 1, 6, 'Laptop Lenovo Legion 7 16ACHG6 82N60039VN', 100, 7, 74990000, 'Con chip d??ng H mang l???i hi???u n??ng v?????t tr???i</br>T???n nhi???t ch???t l?????ng v???i Legion Coldfront 3.0',"NO");
-- TAI NGHE APPLE
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 2, 7, 'Tai nghe Bluetooth Apple AirPods 2', 100, 23, 3990000, 'Dung l?????ng pin l???n, s??? d???ng b???n b???</br>Thi???t k??? nh??? g???n, b???t m???t',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 2, 7, 'Tai nghe Bluetooth Apple AirPods Pro', 100, 37, 7999000, 'Airpod Pro s??? h???u thi???t k??? nh??? g???n, tr???ng l?????ng 5.4 gram</br>??m thanh tr??n Apple Airpods Pro r?? n??t v???i c??ng ngh??? kh??? ti???ng ???n ch??? ?????ng',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 2, 7, 'Tai nghe ch???p tai ch???ng ???n Apple AirPods Max', 100, 20, 13900000, 'Thi???t k??? ch???p tai s??? d???ng ??m ??i, ch???t li???u cao c???p n???i b???t</br>Ch???t l?????ng ??m thanh c???c k??? cao c???p, ch???ng ???n hi???u qu???',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 2, 7, 'Tai nghe Bluetooth Apple AirPods 3', 100, 0, 4990000, 'TThi???t k??? sang tr???ng, nhi???u thay ?????i so v???i th??? h??? tr?????c</br>Dung l?????ng pin ???????c c???i thi???n',"NO");

-- TAI NGHE SAM SUNG
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 2, 9, 'Tai nghe kh??ng d??y Samsung Galaxy Buds 2', 100, 30, 2990000, '3 micro c??ng c??ng ngh??? nh???n di???n gi???ng n??i</br>K???t n???i bluetooth chu???n v5.2',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 2, 9, 'Tai nghe Samsung Galaxy AKG EO-IG955', 100, 24, 250000, '',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 2, 9, 'Tai nghe nh??t tai Samsung IG935B', 100, 10, 300000, 'Tai nghe Samsung IG935B thi???t k??? nh??? g???n tr??? trung</br>C???m gi??c ??eo tho???i m??i c???a tai nghe samsung IG935B',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 2, 9, 'Tai nghe nh??t tai Samsung EG920B', 100, 10, 280000, 'Samsung EG920B - Thi???t k??? cao su b???n b??? d??i 1.2m, k??m theo c???p ?????m d???ng th?????ng v?? d???ng m??c</br>??m thanh trung th???c, ????? bass d??y, ???????ng k??nh 12mm tr??n Samsung EG920B ',"NO");

-- TAI NGHE JBL
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 2, 8, 'Tai nghe kh??ng d??y JBL Live Pro+', 100, 0, 2990000, 'Thi???t k??? nh??? g???n, h??? tr??? ch???ng n?????c IPX4</br>??m thanh JBL Signature Sound ch???t l?????ng',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 2, 8, 'Tai Nghe Kh??ng D??y JBL Tune 120 TWS', 100, 54, 2390000, 'Thi???t k??? ?????c ????o, g???n nh??? v???i driver ???????ng k??nh 5.8mm v?? m??u s???c th???i trang</br>JBL Tune 120Tws s??? d???ng chu???n k???t n???i bluetooth 4.2, k???t n???i tr??? l?? ???o ti???n l???i v?? nhanh ch??ng',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 2, 8, 'Tai nghe ch???p tai JBL Tune 510BT', 100, 28, 1390000, 'Thi????t k???? ??????m tai si??u ??m, ??i????u khi????n ??m thanh ti????n l????i</br>Chu????n ch????t ??m JBL Pure Bass ma??nh me?? v????i g????n 40 gi???? nghe li??n tu??c',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 2, 8, 'Tai nghe gaming c?? d??y JBL Quantum 50', 100, 17, 890000, 'Thi???t k??? ?????c ????o v?? th???i trang, nhi???u m??u s???c ????? l???a ch???n</br>Ch???t ??m JBL QuantumSOUND Signature ?????c quy???n, t????ng th??ch v???i nhi???u thi???t b???',"NO");
-- TAI NGHE SONY
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 2, 10, 'Tai nghe ch???p tai Sony WH-1000XM4', 100, 23, 8490000, 'Ch???c n??ng kh??? ti???ng ???n ch??? ?????ng, h???? tr??? ???Speak to Chat???</br>Kh??? n??ng ti??u th??? ??i????n n??ng th????p, pin s??? d???ng 40 gi???',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 2, 10, 'Tai nghe kh??ng d??y ch???ng ???n Sony WF-1000XM3', 100, 30, 5490000, 'Thi???t k??? ??m s??t kh??ng b??? tr?????t ra ngo??i, k??m ph??? ki???n eartip hybrid</br>C???m bi???n ti???ng ???n k??p, b??? x??? l?? ch???ng ???n HD QN1e ',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 2, 10, 'Tai nghe kh??ng d??y Sony WF-1000XM4', 100, 15, 6490000, 'Hi???u su???t ch???ng ???n ?????nh cao, m??ng loa 6 mm</br>T??ch h???p c??ng ngh??? hi???n ?????i: Edge-AI, DSEE ExtremeTM  t??ng c?????ng ??m thanh',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 2, 10, 'Tai nghe Ch???p Tai Kh??ng D??y Sony WH-CH510', 100, 16, 1190000, 'Sony WH-CH510 - Thi???t k??? g???n nh??? v???i tr???ng l?????ng ch??? 132g c??ng n??t ??i???u khi???n linh ho???t</br>Th???i l?????ng pin 35 gi??? c??ng ??m thanh ph??t tr???c ti???p th??ng qua Bluetooth 5.0',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 2, 10, 'Tai nghe Sony MDR-XB55AP', 100, 0, 890000, 'Thi???t k??? t??? nh???a v?? kim lo???i c??ng v???i housing g??c nghi??ng 45o cho c???m gi??c ??eo d??? ch???u, tho???i m??i</br>N??i kh??ng v???i t??nh tr???ng r???i d??y khi d??y c???a tai nghe ???????c thi???t d???ng d???t s???c g??n n???i',"NO");
-- CHU???T LOGITECH
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 3, 11, 'Chu???t kh??ng d??y Logitech M590', 100, 25, 800000, 'Thi???t k??? th??ng minh, nh??? g???n v?? hi???n ?????i</br>Ho???t ?????ng tuy???t v???i trong y??n l???ng, thao t??c nh??? nh??ng v?? ch??nh x??c',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 3, 11, 'Chu???t kh??ng d??y Logitech M331', 100, 0, 340000, 'Thi???t k??? bo tr??n ??m s??t l??ng b??n tay, tr???ng l?????ng ch??? 101g, ch???t li???u nh???a c???ng</br>Chu???t quang ????? ph??n gi???i l??n ?????n 1000dpi, k???t n???i kh??ng d??y v???i kho???ng c??ch 10m',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 3, 11, 'Chu???t kh??ng d??y Logitech M221', 100, 23, 319000, 'Thi???t k??? linh ho???t, t???c ????? kh??ng d??y 2.4 GHz</br>T????ng th??ch v???i h???u h???t h??? ??i???u h??nh, gi???m thi???u 90% ti???ng ???n',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 3, 11, 'Chu???t kh??ng d??y Logitech M238 Marvel Collection', 100, 48, 490000, '????? ph??n gi???i 1000 DPI, ph???m vi k???t n???i 10m</br>?????u t??n hi???u receiver nano, th???i gian s??? d???ng ?????n 12 th??ng',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 3, 11, 'Chu???t Gaming Logitech G102 LightSync', 100, 32, 599000, 'Thi????t k???? c???? ??i????n v????i 6 nu??t nh????n cu??ng h???? th????ng LED RGB</br>DPI l??n ??????n 8.000 v????i ca??m bi????n c????p ?????? ch??i game cho s???? thi??ch ri??ng',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 3, 11, 'Chu???t kh??ng d??y Logitech MX Anywhere 3', 100, 19, 1999000, 'Thi???t k??? kh??ng d??y v???i 2 c?? ch??? k???t n???i</br>Con cu???n Magspeed ??u vi???t, t????ng th??ch nhi???u h??? ??i???u h??nh',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 3, 11, 'Chu???t kh??ng d??y Logitech MX Master 2S', 100, 36, 2490000, '',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 3, 11, 'Chu???t ch??i game kh??ng d??y Logitech G502 Lightspeed', 100, 7, 2590000, 'Thi???t k??? m???nh m???, tr???ng l?????ng si??u nh???</br>C???m bi???n Hero ?????c ????o, ????? ch??nh x??c tuy???t ?????i',"NO");

-- SSD SAMSUNG
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 4, 9, '???? c????ng SSD Samsung 860 Evo 250GB M.2 2280 SATA 3 - MZ-N6E250BW', 100, 7, 1890000, 'T???i ??u h??a t???c ????? l??n m???c t???i ??a</br>C???i thi???n n??ng l?????ng hi???u qu???',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 4, 9, '???? c????ng SSD Samsung 860 Evo 500GB M.2 2280 SATA 3 - MZ-N6E500BW', 100, 7, 2490000, 'T???i ??u h??a t???c ????? l??n m???c t???i ??a</br>C???i thi???n n??ng l?????ng hi???u qu???',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 4, 9, '???? c????ng SSD Samsung 970 Pro 512GB M.2 2280 NVMe - MZ-V7P512BW', 100, 7, 5520000, 'T???i ??u h??a t???c ????? l??n m???c t???i ??a</br>C???i thi???n n??ng l?????ng hi???u qu???',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 4, 9, '???? c????ng SSD Samsung 970 Evo Plus 250GB M.2 NVMe - MZ-V7S250BW', 100, 7, 1790000, 'T???i ??u h??a t???c ????? l??n m???c t???i ??a</br>C???i thi???n n??ng l?????ng hi???u qu???',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 4, 9, '???? c????ng SSD Samsung 970 Evo Plus 500GB M.2 NVMe - MZ-V7S500BW', 100, 17, 2890000, 'T???i ??u h??a t???c ????? l??n m???c t???i ??a</br>C???i thi???n n??ng l?????ng hi???u qu???',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 4, 9, '??? c???ng SSD Samsung 980 PRO 500GB NVMe M.2 PCIe 4.0 (MZ-V8P500BW)', 100, 7, 3990000, 'T???i ??u h??a t???c ????? l??n m???c t???i ??a</br>C???i thi???n n??ng l?????ng hi???u qu???',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 4, 9, '??? c???ng SSD Samsung 870 EVO 250GB SATA III 2.5 inch (MZ-77E250BW)', 100, 7, 1890000, 'T???i ??u h??a t???c ????? l??n m???c t???i ??a</br>C???i thi???n n??ng l?????ng hi???u qu???',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 4, 9, '??? c???ng SSD Samsung 870 EVO 500GB SATA III 2.5 inch (MZ-77E500BW)', 100, 7, 2350000, 'T???i ??u h??a t???c ????? l??n m???c t???i ??a</br>C???i thi???n n??ng l?????ng hi???u qu???',"NO");
-- SSD KINGSTON
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 4, 12, '??? c???ng SSD Kingston A400 120GB Sata 3 (SA400S37/120G)', 100, 7, 790000, '',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 4, 12, '??? c???ng SSD Kingston A400 240GB Sata 3 (SA400S37/240G)', 100, 7, 1130000, '',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 4, 12, '??? c???ng SSD Kingston A400 480GB Sata 3 (SA400S37/480G)', 100, 7, 1610000, '',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 4, 12, '??? c???ng SSD Kingston KC600 512GB 2.5" SATA 3', 100, 7, 2190000, '',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 4, 12, '??? c???ng SSD Kingston KC600 256GB 2.5" SATA 3', 100, 7, 1390000, '',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 4, 12, '??? c???ng SSD Kingston A2000 250GB M.2 2280 NVMe PCIe', 100, 7, 1490000, '',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 4, 12, '??? c???ng SSD Kingston A2000 500GB M.2 2280 NVMe PCIe', 100, 7, 2200000, '',"NO");

-- RAM KINGMAX
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 5, 13, 'RAM desktop KINGMAX (1x4GB) DDR3 1600MHz', 100, 7, 790000, 'T??nh ???n ?????nh cao, ????? tin c???y v?? kh??? n??ng t????ng th??ch</br>???ng d???ng r???ng h??n v?? ti??u th??? ??i???n n??ng th???p h??n',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 5, 13, 'RAM desktop KINGMAX (1x8GB) DDR3 1600MHz', 100, 7, 1250000, 'T??nh ???n ?????nh cao, ????? tin c???y v?? kh??? n??ng t????ng th??ch</br>???ng d???ng r???ng h??n v?? ti??u th??? ??i???n n??ng th???p h??n',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 5, 13, 'RAM desktop KINGMAX (1x4GB) DDR4 2400MHz', 100, 7, 650000, 'T??nh ???n ?????nh cao, ????? tin c???y v?? kh??? n??ng t????ng th??ch</br>???ng d???ng r???ng h??n v?? ti??u th??? ??i???n n??ng th???p h??n',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 5, 13, 'RAM desktop KINGMAX (1x8GB) DDR4 2400MHz', 100, 7, 900000, 'T??nh ???n ?????nh cao, ????? tin c???y v?? kh??? n??ng t????ng th??ch</br>???ng d???ng r???ng h??n v?? ti??u th??? ??i???n n??ng th???p h??n',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 5, 13, 'RAM desktop KINGMAX HEATSINK (Zeus) (1 x 8GB) DDR4 3200MHz', 100, 7, 1400000, 'T??nh ???n ?????nh cao, ????? tin c???y v?? kh??? n??ng t????ng th??ch</br>???ng d???ng r???ng h??n v?? ti??u th??? ??i???n n??ng th???p h??n',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 5, 13, 'RAM desktop KINGMAX HEATSINK (Zeus) (1 x 16GB) DDR4 3200MHz', 100, 7, 2590000, 'T??nh ???n ?????nh cao, ????? tin c???y v?? kh??? n??ng t????ng th??ch</br>???ng d???ng r???ng h??n v?? ti??u th??? ??i???n n??ng th???p h??n',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 5, 13, 'RAM laptop KINGMAX Kingmax 16GB (1 x 16GB) DDR4 2666MHz', 100, 15, 2350000, 'T??nh ???n ?????nh cao, ????? tin c???y v?? kh??? n??ng t????ng th??ch</br>???ng d???ng r???ng h??n v?? ti??u th??? ??i???n n??ng th???p h??n',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 5, 13, 'RAM laptop KINGMAX Kingmax 8GB (3200) (1 x 8GB) DDR4 3200MHz', 100, 35, 1390000, 'T??nh ???n ?????nh cao, ????? tin c???y v?? kh??? n??ng t????ng th??ch</br>???ng d???ng r???ng h??n v?? ti??u th??? ??i???n n??ng th???p h??n',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 5, 13, 'RAM laptop KINGMAX Kingmax 16GB (3200) (1 x 16GB) DDR4 3200MHz', 100, 20, 2430000, 'T??nh ???n ?????nh cao, ????? tin c???y v?? kh??? n??ng t????ng th??ch</br>???ng d???ng r???ng h??n v?? ti??u th??? ??i???n n??ng th???p h??n',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 5, 13, 'RAM laptop KINGMAX Kingmax 32GB (3200) (1 x 32GB) DDR4 3200MHz', 100, 12, 4770000, 'T??nh ???n ?????nh cao, ????? tin c???y v?? kh??? n??ng t????ng th??ch</br>???ng d???ng r???ng h??n v?? ti??u th??? ??i???n n??ng th???p h??n',"NO");

-- RAM KINGSTON
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 5, 12, 'RAM desktop KINGSTON FURY Beast Black 32GB (2x16GB) DDR5 5200MHz (2 x 16GB) DDR5 5200MHz (KF552C40BBK2-32)', 100, 7, 7999000, 'T??nh ???n ?????nh cao, ????? tin c???y v?? kh??? n??ng t????ng th??ch</br>???ng d???ng r???ng h??n v?? ti??u th??? ??i???n n??ng th???p h??n',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 5, 12, 'RAM desktop KINGSTON Fury Beast 32GB (2 x 16GB) DDR4 3600MHz (KF436C18BBK2/32)', 100, 7, 5750000, 'T??nh ???n ?????nh cao, ????? tin c???y v?? kh??? n??ng t????ng th??ch</br>???ng d???ng r???ng h??n v?? ti??u th??? ??i???n n??ng th???p h??n',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 5, 12, 'RAM desktop KINGSTON Fury Beast RGB (2 x 16GB) DDR4 3600MHz (KF436C18BBAK2/32)', 100, 7, 5390000, 'T??nh ???n ?????nh cao, ????? tin c???y v?? kh??? n??ng t????ng th??ch</br>???ng d???ng r???ng h??n v?? ti??u th??? ??i???n n??ng th???p h??n',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 5, 12, 'RAM desktop KINGSTON Fury Renegade RGB 32GB (2 x 16GB) DDR4 3200MHz (KF432C16RB1AK2/32)', 100, 7, 2590000, 'T??nh ???n ?????nh cao, ????? tin c???y v?? kh??? n??ng t????ng th??ch</br>???ng d???ng r???ng h??n v?? ti??u th??? ??i???n n??ng th???p h??n',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 5, 12, 'RAM desktop KINGSTON Fury Beast RGB 32GB (2 x 16GB) DDR4 3200MHz (KF432C16BB1AK2/32)', 100, 7, 2590000, 'T??nh ???n ?????nh cao, ????? tin c???y v?? kh??? n??ng t????ng th??ch</br>???ng d???ng r???ng h??n v?? ti??u th??? ??i???n n??ng th???p h??n',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 5, 12, 'RAM laptop KINGSTON DDR4 Kingston 16GB (3200) (1 x 16GB) DDR4 3200MHz (KVR32S22D8/16)', 100, 7, 2090000, 'T??nh ???n ?????nh cao, ????? tin c???y v?? kh??? n??ng t????ng th??ch</br>???ng d???ng r???ng h??n v?? ti??u th??? ??i???n n??ng th???p h??n',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 5, 12, 'RAM laptop Kingston KVR16LS11/4 (1x4GB) DDR3L 1600MHz', 100, 7, 950000, 'T??nh ???n ?????nh cao, ????? tin c???y v?? kh??? n??ng t????ng th??ch</br>???ng d???ng r???ng h??n v?? ti??u th??? ??i???n n??ng th???p h??n',"NO");
INSERT INTO product (category_id,supplier_id, product_name, quantity,discount, unit_price, description_product,is_delete)
VALUES ( 5, 12, 'RAM laptop KINGSTON KCP432SS6/8 (1 x 8GB) DDR4 3200MHz', 100, 7, 1290000, 'T??nh ???n ?????nh cao, ????? tin c???y v?? kh??? n??ng t????ng th??ch</br>???ng d???ng r???ng h??n v?? ti??u th??? ??i???n n??ng th???p h??n',"NO");


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
VALUES ("Tam D??n","0326000692","2021-10-5",190000,"???? giao","","COD","Ch??a thanh to??n",1);
INSERT INTO orderdetail(order_id,product_id,quantity ,amount,is_delete)
VALUES (1,80,1,190000,"NO");

INSERT INTO orders(address,phone_number,create_date, total_amount, status_order,note,payment_method,payment_status, customer_id)
VALUES ("Tam Th??i","0326000693","2021-10-10",252000,"???? giao","","COD","???? thanh to??n",2);
INSERT INTO orderdetail(order_id,product_id,quantity ,amount,is_delete,is_review)
VALUES (2,82,1,252000,"NO","YES");

INSERT INTO orders(address,phone_number,create_date, total_amount, status_order,note,payment_method,payment_status, customer_id)
VALUES ("Tam L??nh","0326000694","2021-10-15",859300,"???? giao","","COD","???? thanh to??n",3);
INSERT INTO orderdetail(order_id,product_id,quantity ,amount,is_delete,is_review)
VALUES (3,95,1,254800,"NO","YES");
INSERT INTO orderdetail(order_id,product_id,quantity ,amount,is_delete,is_review)
VALUES (3,117,1,604500,"NO","YES");

INSERT INTO orders(address,phone_number,create_date, total_amount, status_order,note,payment_method,payment_status, customer_id)
VALUES ("Tam An","0326000695","2021-10-20",20088600,"???? giao","","COD","???? thanh to??n",4);
INSERT INTO orderdetail(order_id,product_id,quantity ,amount,is_delete,is_review)
VALUES (4,15,1,17846400,"NO","YES");
INSERT INTO orderdetail(order_id,product_id,quantity ,amount,is_delete,is_review)
VALUES (4,84,1,738700,"NO","YES");
INSERT INTO orderdetail(order_id,product_id,quantity ,amount,is_delete,is_review)
VALUES (4,92,1,600000,"NO","YES");
INSERT INTO orderdetail(order_id,product_id,quantity ,amount,is_delete,is_review)
VALUES (4,122,1,903500,"NO","YES");

INSERT INTO orders(address,phone_number,create_date, total_amount, status_order,note,payment_method,payment_status, customer_id)
VALUES ("Tam Th??nh","0326000696","2021-10-28",31062900,"???? giao","","COD","???? thanh to??n",1);
INSERT INTO orderdetail(order_id,product_id,quantity ,amount,is_delete,is_review)
VALUES (5,59,1,28820700,"NO","YES");
INSERT INTO orderdetail(order_id,product_id,quantity ,amount,is_delete,is_review)
VALUES (5,84,1,738700,"NO","YES");
INSERT INTO orderdetail(order_id,product_id,quantity ,amount,is_delete,is_review)
VALUES (5,92,1,600000,"NO","YES");
INSERT INTO orderdetail(order_id,product_id,quantity ,amount,is_delete,is_review)
VALUES (5,122,1,903500,"NO","YES");

INSERT INTO orders(address,phone_number,create_date, total_amount, status_order,note,payment_method,payment_status, customer_id)
VALUES ("Tam ?????i","0326000697","2021-11-7",15000,"???? giao","","COD","???? thanh to??n",2);
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
VALUES ("Ph?? Ninh","0326000698","2021-11-15",6080900,"???? giao","","COD","???? thanh to??n",3);
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
VALUES ("Duy Xuy??n","0326000699","2021-11-22",2019420,"???? giao","","COD","???? thanh to??n",4);
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
VALUES ("Th??ng B??nh","0326000670","2021-11-28",13270400,"???? giao","","COD","???? thanh to??n",1);
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
VALUES ("Qu???n 9","0326110670","2021-12-1",118081000,"???? giao","","COD","???? thanh to??n",2);
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
VALUES ("Qu???n 5","0326110670","2021-12-8",108141770,"???? giao","","COD","???? thanh to??n",2);
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
VALUES ("Qu???n T??n B??nh","0326110670","2021-12-15",3075230,"???? giao","","COD","???? thanh to??n",2);
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
VALUES ("Qu???n G?? V???p","0326110670","2021-12-24",101628500,"???? giao","","COD","???? thanh to??n",2);
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
VALUES ("Bu??n M?? Thu???t","0326110670","2021-12-27",91399700,"???? duy???t","","COD","Ch??a thanh to??n",2);
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
VALUES ("L?? V??n Vi???t, Qu???n 9","0326110670","2021-12-29",69740700,"???? duy???t","","COD","Ch??a thanh to??n",2);
INSERT INTO orderdetail(order_id,product_id,quantity ,amount,is_delete,is_review)
VALUES (15,74,1,69740700,"NO","YES");