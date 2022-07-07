-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: banlinhkienonline
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `accounts`
--

DROP TABLE IF EXISTS `accounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `accounts` (
  `account_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(250) DEFAULT NULL,
  `passwords` varchar(150) DEFAULT NULL,
  `gmail` varchar(250) DEFAULT NULL,
  `firstname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `lastname` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `address` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `phonenumber` varchar(50) DEFAULT NULL,
  `activation_code` varchar(100) DEFAULT NULL,
  `passwordreset_code` varchar(50) DEFAULT NULL,
  `active_account` varchar(50) DEFAULT NULL,
  `provider` varchar(10) DEFAULT NULL,
  `roles` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`account_id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `gmail` (`gmail`),
  UNIQUE KEY `phonenumber` (`phonenumber`),
  CONSTRAINT `OR_activeACCOUNT_CHK` CHECK ((`active_account` in (_utf8mb4'ACTIVE',_utf8mb4'NOT ACTIVE'))),
  CONSTRAINT `OR_rolesACCOUNT_CHK` CHECK ((`roles` in (_utf8mb4'ADMIN',_utf8mb4'SHIPPER',_utf8mb4'CUSTOMER')))
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accounts`
--

LOCK TABLES `accounts` WRITE;
/*!40000 ALTER TABLE `accounts` DISABLE KEYS */;
INSERT INTO `accounts` VALUES (1,'huynhphucadmin','$2y$12$n1Ea46fRM5OHjcldln9FCeDNKGWPlBTon6QSLWFiZQ70Jz7up77d.','huynhphucadmin@gmail.com','Huỳnh','Ngọc Phúc','Quận 9','0322000568',NULL,NULL,'ACTIVE','LOCAL','ADMIN'),(2,'thanhtuadmin','$2y$12$vlL2nNxUnnFgwPYZ3DwS2.DukpDpA3K7ZzPwnBzxlATAX.wHp48dO','thanhtuadmin@gmail.com','Nguyễn','Thanh Tú','Quận 10','0322010568',NULL,NULL,'ACTIVE','LOCAL','ADMIN'),(3,'ngoctinhadmin','$2y$12$E08DvJWGdIWEhAxFVoUVNu7MKtI2cCpCBRtPuV/cnkumAvrpIXHAW','ngoctinhadmin@gmail.com','Nguyễn','Ngọc Tình','Quận 11','0322100568',NULL,NULL,'ACTIVE','LOCAL','ADMIN'),(4,'huynhngocphuc@gmail.com','$2y$12$0FFBQhnloHEL3Jkn/FdzTO9zEZJmMdXW4wMSubG/FwatiVm/XyWX6','huynhngocphuc@gmail.com','Huỳnh','Ngọc Phúc','Buôn Mê Thuột','0326000587',NULL,NULL,'ACTIVE','LOCAL','CUSTOMER'),(5,'nguyenthanhtu@gmail.com','$2y$12$syXsrDwQo9qhYW6a9XZHkexaZDautIUFDyrxJC.NBRGl1smhkFVy6','nguyenthanhtu@gmail.com','Nguyễn','Thanh Tú','Quảng Nam','0326010587',NULL,NULL,'ACTIVE','LOCAL','CUSTOMER'),(6,'ntthanhthao31@gmail.com','$2y$12$c3e35UG2lL4lhTM1iCcS7OaNQRimhgijW7Fn/NrTjXZu3YfxAFEvO','ntthanhthao31@gmail.com','Nguyễn','Thị Thanh Thảo','Quảng Nam','0867832447',NULL,NULL,'ACTIVE','LOCAL','CUSTOMER'),(7,'chaugiakiet55533395@gmail.com','$2y$12$bbLhM88F0bKdOYGKg4vMKOZ20NLsllTDLKpK.FMxkKDkoH9/eI0Ma','chaugiakiet55533395@gmail.com','Châu','Gia Kiệt','Tam Ky','032111587',NULL,NULL,'ACTIVE','LOCAL','CUSTOMER'),(8,'halamhy@gmail.com','$2y$12$L6G1I3WH/99ZL0y5B0WYzOOxicSniPe1vy8/tC75ev8FeqKg00a5u','Lâm Hy','halamhy@gmail.com','Hạ','Tam Thái','0333000698',NULL,NULL,'ACTIVE','LOCAL','CUSTOMER'),(9,'trantrietvien@gmail.com','$2y$12$MyMaqVtRK9d39.aoJhKBS.pp0I7aoHCWmhPdA5Jr7P9eslPh6reGW','trantrietvien@gmail.com','Trần','Triết Viễn','Bạc Liêu','0333100698',NULL,NULL,'ACTIVE','LOCAL','CUSTOMER'),(10,'tumongkhiet@gmail.com','$2y$12$Wb9boDgvXQKuSLl0Gy3JN.qUJ24.b/iXGoF/QLrcwOxNuf0Zx1VjC','tumongkhiet@gmail.com','Từ','Mông Khiết','Bến Tre','0333110698',NULL,NULL,'ACTIVE','LOCAL','CUSTOMER'),(11,'thanhlong@gmail.com','$2y$12$GD9bewiDJoSgISxzXIpZF.fJPsicaBHT4CKc5i2vHdRSEJMN.tPNe','thanhlong@gmail.com','Thành','Long','Bình Dương ','0334000698',NULL,NULL,'ACTIVE','LOCAL','CUSTOMER'),(12,'vuongtrachhien@gmail.com','$2y$12$P2.cuN6ZABetZgDIZfNY6uUWWlVM2k/Laq1RG.PUx53ub3DY9vedm','vuongtrachhien@gmail.com','Vương','Trách Hiện','Hồ Chí Minh','0333200698',NULL,NULL,'ACTIVE','LOCAL','CUSTOMER'),(13,'vuongnhatlam@gmail.com','$2y$12$v/3DMekr0H8LmtzpbzPVkuu2mHdjUVtop76Ep3fYtm/tygXvl7Inm','vuongnhatlam@gmail.com','Vương','Nhất Lâm','Vũng Tàu','0343000698',NULL,NULL,'ACTIVE','LOCAL','CUSTOMER'),(14,'vuonganvu@gmail.com','$2y$12$GY8rHWAFPwBkKvAko.NylOFoMQAuRkLT80vvWQ/dzaqk0fD8f.0GK','vuonganvu@gmail.com','Vương','An Vũ','Tam An','0333000608',NULL,NULL,'ACTIVE','LOCAL','CUSTOMER'),(15,'huonghamchi@gmail.com','$2y$12$/CeNbvY00r3WlcoudUbiPeZpv31VAq5tI6sxMgoTuzLftVLjc0Lb6','huonghamchi@gmail.com','Hướng','Hàm Chi','Tam Phú','0333111698',NULL,NULL,'ACTIVE','LOCAL','CUSTOMER');
/*!40000 ALTER TABLE `accounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `cart_id` int NOT NULL AUTO_INCREMENT,
  `quantity` int DEFAULT NULL,
  `is_delete` varchar(10) DEFAULT NULL,
  `customer_id` int DEFAULT NULL,
  `product_id` int DEFAULT NULL,
  PRIMARY KEY (`cart_id`),
  KEY `CA_maPRODUCT_FK` (`product_id`),
  KEY `CA_maCUSTOMER_FK` (`customer_id`),
  CONSTRAINT `CA_maCUSTOMER_FK` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`) ON DELETE CASCADE,
  CONSTRAINT `CA_maPRODUCT_FK` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`),
  CONSTRAINT `OR_isDeleteCart_CHK` CHECK ((`is_delete` in (_utf8mb4'NO',_utf8mb4'YES')))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `category_id` int NOT NULL AUTO_INCREMENT,
  `category_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `is_delete` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`category_id`),
  CONSTRAINT `OR_isDeleteCATEGORY_CHK` CHECK ((`is_delete` in (_utf8mb4'NO',_utf8mb4'YES')))
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Laptop','NO'),(2,'Tai nghe','NO'),(3,'Chuột','NO'),(4,'Ổ cứng SSD','NO'),(5,'RAM','NO');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `customer_id` int NOT NULL AUTO_INCREMENT,
  `user_customer` varchar(250) DEFAULT NULL,
  `firstname_customer` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `lastname_customer` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `image_customer` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `address` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `gmail_customer` varchar(50) DEFAULT NULL,
  `phonenumber_customer` varchar(50) DEFAULT NULL,
  `is_delete` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`customer_id`),
  UNIQUE KEY `user_customer` (`user_customer`),
  UNIQUE KEY `gmail_customer` (`gmail_customer`),
  UNIQUE KEY `phonenumber_customer` (`phonenumber_customer`),
  CONSTRAINT `OR_isDeleteCUSTOMER_CHK` CHECK ((`is_delete` in (_utf8mb4'NO',_utf8mb4'YES')))
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'huynhngocphuc@gmail.com','Huỳnh','Ngọc Phúc',NULL,'Buôn Mê Thuột','huynhngocphuc@gmail.com','0326000587','NO'),(2,'nguyenthanhtu@gmail.com','Nguyễn','Thanh Tú',NULL,'Quảng Nam','nguyenthanhtu@gmail.com','0326010587','NO'),(3,'ntthanhthao31@gmail.com','Nguyễn','Thị Thanh Thảo',NULL,'Quảng Nam','ntthanhthao31@gmail.com','0867832447','NO'),(4,'chaugiakiet55533395@gmail.com','Châu','Gia Kiệt',NULL,'Tam Ky','chaugiakiet55533395@gmail.com','032111587','NO'),(5,'halamhy@gmail.com','Hạ','Lâm Hy',NULL,'Tam Thái','halamhy@gmail.com','0333000698','NO'),(6,'trantrietvien@gmail.com','Trần','Triết Viễn',NULL,'Bạc Liêu','trantrietvien@gmail.com','0333100698','NO'),(7,'tumongkhiet@gmail.com','Từ','Mộng Khiết',NULL,'Bến Tre','tumongkhiet@gmail.com','0333110698','NO'),(8,'thanhlong@gmail.com','Thành','Long',NULL,'Bình Dương ','thanhlong@gmail.com','0334000698','NO'),(9,'vuongtrachhien@gmail.com','Vương','Trách Hiện',NULL,'Hồ Chí Minh','vuongtrachhien@gmail.com','0333200698','NO'),(10,'vuongnhatlam@gmail.com','Vương','Nhất Lâm',NULL,'Vũng Tàu','vuongnhatlam@gmail.com','0343000698','NO'),(11,'vuonganvu@gmail.com','Vương','An Vũ',NULL,'Tam An','vuonganvu@gmail.com','0333000608','NO'),(12,'huonghamchi@gmail.com','Hướng','Hàm Chi',NULL,'Tam Phú','huonghamchi@gmail.com','0333111698','NO');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `deliveryaddress`
--

DROP TABLE IF EXISTS `deliveryaddress`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `deliveryaddress` (
  `deliveryaddress_id` int NOT NULL AUTO_INCREMENT,
  `fullname` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `phone_number` varchar(12) DEFAULT NULL,
  `deliveryaddress` varchar(500) DEFAULT NULL,
  `is_delete` varchar(10) DEFAULT NULL,
  `customer_id` int DEFAULT NULL,
  PRIMARY KEY (`deliveryaddress_id`),
  KEY `DA_maDELIVERYADDRESS_PK` (`customer_id`),
  CONSTRAINT `DA_maDELIVERYADDRESS_PK` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`) ON DELETE CASCADE,
  CONSTRAINT `DA_isDeleteIMAGE_CHK` CHECK ((`is_delete` in (_utf8mb4'NO',_utf8mb4'YES')))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `deliveryaddress`
--

LOCK TABLES `deliveryaddress` WRITE;
/*!40000 ALTER TABLE `deliveryaddress` DISABLE KEYS */;
/*!40000 ALTER TABLE `deliveryaddress` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `image`
--

DROP TABLE IF EXISTS `image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `image` (
  `image_id` int NOT NULL AUTO_INCREMENT,
  `image` varchar(500) DEFAULT NULL,
  `is_delete` varchar(10) DEFAULT NULL,
  `product_id` int DEFAULT NULL,
  PRIMARY KEY (`image_id`),
  KEY `I_maPRODUCT_FK` (`product_id`),
  CONSTRAINT `I_maPRODUCT_FK` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE CASCADE,
  CONSTRAINT `I_isDeleteIMAGE_CHK` CHECK ((`is_delete` in (_utf8mb4'NO',_utf8mb4'YES')))
) ENGINE=InnoDB AUTO_INCREMENT=267 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `image`
--

LOCK TABLES `image` WRITE;
/*!40000 ALTER TABLE `image` DISABLE KEYS */;
INSERT INTO `image` VALUES (1,'https://bom.so/QrJEfJ','NO',1),(2,'https://bom.so/ejRBre','NO',1),(3,'https://bom.so/P6Bbhi','NO',2),(4,'https://bom.so/JCDxUm','NO',2),(5,'https://bom.so/6qJgSA','NO',3),(6,'https://bom.so/rL01II','NO',3),(7,'https://bom.so/pNK7gy','NO',4),(8,'https://bom.so/AbzTeF','NO',4),(9,'https://bom.so/9lI02c','NO',5),(10,'https://bom.so/jcXXXb','NO',5),(11,'https://bom.so/r8EdhU','NO',6),(12,'https://bom.so/q25XbL','NO',6),(13,'https://bom.so/FVWYUk','NO',7),(14,'https://bom.so/MM23wQ','NO',7),(15,'https://bom.so/f3zD2o','NO',8),(16,'https://bom.so/SptA1I','NO',8),(17,'https://bom.so/fl7m7W','NO',9),(18,'https://bom.so/YXcaSr','NO',9),(19,'https://bom.so/PcOhwD','NO',10),(20,'https://bom.so/w5pPd1','NO',10),(21,'https://bom.so/p2tC9S','NO',11),(22,'https://bom.so/yh8rEU','NO',11),(23,'https://bom.so/C3arw5','NO',12),(24,'https://bom.so/pm4mfH','NO',12),(25,'https://bom.so/QJiuh0','NO',13),(26,'https://bom.so/2DjKif','NO',13),(27,'https://bom.so/YntcSX','NO',14),(28,'https://bom.so/hLabbJ','NO',14),(29,'https://bom.so/8kbYAD','NO',15),(30,'https://bom.so/hI36wa','NO',15),(31,'https://bom.so/dwldjN','NO',16),(32,'https://bom.so/cFJJXs','NO',16),(33,'https://bom.so/zCYSYw','NO',17),(34,'https://bom.so/ltyR7v','NO',17),(35,'https://bom.so/cvyjAU','NO',18),(36,'https://bom.so/xa4Qey','NO',18),(37,'https://bom.so/DnFgd9','NO',19),(38,'https://bom.so/1AxVab','NO',19),(39,'https://bom.so/mVN4iF','NO',20),(40,'https://bom.so/3QlgcH','NO',20),(41,'https://bom.so/oElsL8','NO',21),(42,'https://bom.so/RIvLEo','NO',21),(43,'https://bom.so/swU6bM','NO',22),(44,'https://bom.so/jz0Xos','NO',22),(45,'https://bom.so/bYX2AS','NO',23),(46,'https://bom.so/xrD1ND','NO',23),(47,'https://bom.so/2uhKty','NO',24),(48,'https://bom.so/ACia2v','NO',24),(49,'https://bom.so/3qPVXq','NO',25),(50,'https://bom.so/73G95C','NO',25),(51,'https://bom.so/jT7XSC','NO',26),(52,'https://bom.so/5Tc3XB','NO',26),(53,'https://bom.so/1BoTZT','NO',27),(54,'https://bom.so/BRAPak','NO',27),(55,'https://bom.so/qKDnhV','NO',28),(56,'https://bom.so/Kw2k0K','NO',28),(57,'https://bom.so/05UwtH','NO',29),(58,'https://bom.so/tv7QDq','NO',29),(59,'https://bom.so/niZw4Q','NO',30),(60,'https://bom.so/uSMPhp','NO',30),(61,'https://bom.so/DiuzJv','NO',31),(62,'https://bom.so/vKrTDg','NO',31),(63,'https://bom.so/WuFMe5','NO',32),(64,'https://bom.so/AqBYU6','NO',32),(65,'https://bom.so/pRsBmB','NO',33),(66,'https://bom.so/VNOYCJ','NO',33),(67,'https://bom.so/1wBZOd','NO',34),(68,'https://bom.so/jaMTo8','NO',34),(69,'https://bom.so/bD6Vr5','NO',35),(70,'https://bom.so/uPqI3Q','NO',35),(71,'https://bom.so/ty1hJ4','NO',36),(72,'https://bom.so/dDM10I','NO',36),(73,'https://bom.so/VoZoys','NO',37),(74,'https://bom.so/7fUJ64','NO',37),(75,'https://bom.so/g5EYe1','NO',38),(76,'https://bom.so/PYPJwl','NO',38),(77,'https://bom.so/Ef6NkX','NO',39),(78,'https://bom.so/spCcvC','NO',39),(79,'https://bom.so/lTp6jy','NO',40),(80,'https://bom.so/FcE1Ki','NO',40),(81,'https://bom.so/R06w33','NO',41),(82,'https://bom.so/cHH0HX','NO',41),(83,'https://bom.so/7ISsER','NO',42),(84,'https://bom.so/rOiZnz','NO',42),(85,'https://bom.so/oyEBsB','NO',43),(86,'https://bom.so/QSFFPV','NO',43),(87,'https://bom.so/r6JFhG','NO',44),(88,'https://bom.so/YbUsS6','NO',44),(89,'https://bom.so/EQ6ukW','NO',45),(90,'https://bom.so/85tteT','NO',45),(91,'https://bom.so/FvzSTk','NO',46),(92,'https://bom.so/D94s5G','NO',46),(93,'https://bom.so/xNU1gz','NO',47),(94,'https://bom.so/5HCSuB','NO',47),(95,'https://bom.so/Hsryil','NO',48),(96,'https://bom.so/R1dWSj','NO',48),(97,'https://bom.so/2Abjfk','NO',49),(98,'https://bom.so/UrbHzW','NO',49),(99,'https://bom.so/gaOgM5','NO',50),(100,'https://bom.so/zQHJmn','NO',50),(101,'https://bom.so/AM8PwN','NO',51),(102,'https://bom.so/sKZkjo','NO',51),(103,'https://bom.so/WZzxJW','NO',52),(104,'https://bom.so/dtQmMs','NO',52),(105,'https://bom.so/DGI5lg','NO',53),(106,'https://bom.so/3ple8B','NO',53),(107,'https://bom.so/GW7Qa3','NO',54),(108,'https://bom.so/vcvA2w','NO',54),(109,'https://bom.so/gis23h','NO',55),(110,'https://bom.so/RZ0F8X','NO',55),(111,'https://bom.so/NWWynG','NO',56),(112,'https://bom.so/CXrOGA','NO',56),(113,'https://bom.so/yRDbs8','NO',57),(114,'https://bom.so/QGQiwp','NO',57),(115,'https://bom.so/p5VaIC','NO',58),(116,'https://bom.so/c6GKmd','NO',58),(117,'https://bom.so/suCSvw','NO',59),(118,'https://bom.so/tK824r','NO',59),(119,'https://bom.so/Df35xb','NO',60),(120,'https://bom.so/M80qqJ','NO',60),(121,'https://bom.so/LZK2sm','NO',61),(122,'https://bom.so/cfXpYq','NO',61),(123,'https://bom.so/wNaIKw','NO',62),(124,'https://bom.so/F8N2It','NO',62),(125,'https://bom.so/Tjpgoy','NO',63),(126,'https://bom.so/tyJt3f','NO',63),(127,'https://bom.so/YXSv3d','NO',64),(128,'https://bom.so/QfBip7','NO',64),(129,'https://bom.so/yxWsZT','NO',65),(130,'https://bom.so/N2aB0C','NO',65),(131,'https://bom.so/Vy92ZK','NO',66),(132,'https://bom.so/K3XZEg','NO',66),(133,'https://bom.so/Bnb9Hz','NO',67),(134,'https://bom.so/XTkzaD','NO',67),(135,'https://bom.so/tVaa2i','NO',68),(136,'https://bom.so/EiyIO6','NO',68),(137,'https://bom.so/DZvRs5','NO',69),(138,'https://bom.so/sjS3kH','NO',69),(139,'https://bom.so/RdzhXN','NO',70),(140,'https://bom.so/tCmAti','NO',70),(141,'https://bom.so/K5ARbu','NO',71),(142,'https://bom.so/iaTIF7','NO',71),(143,'https://bom.so/7gopPs','NO',72),(144,'https://bom.so/9RX6Dd','NO',72),(145,'https://bom.so/HVwagD','NO',73),(146,'https://bom.so/XOSsQn','NO',73),(147,'https://bom.so/ZVgtXt','NO',74),(148,'https://bom.so/LUuYIA','NO',74),(149,'https://bom.so/WE6FaV','NO',75),(150,'https://bom.so/Ecp1E1','NO',75),(151,'https://bom.so/gCadB2','NO',76),(152,'https://bom.so/LnUVJV','NO',76),(153,'https://bom.so/MAtjQe','NO',77),(154,'https://bom.so/P4Pc2s','NO',77),(155,'https://bom.so/iQM8XL','NO',78),(156,'https://bom.so/KzWXio','NO',78),(157,'https://bom.so/lyXjwh','NO',79),(158,'https://bom.so/wIVnLe','NO',79),(159,'https://bom.so/GOfRCs','NO',80),(160,'https://bom.so/GOfRCs','NO',80),(161,'https://bom.so/Mhk7lJ','NO',81),(162,'https://bom.so/M1WBxq','NO',81),(163,'https://bom.so/mMQZ3l','NO',82),(164,'https://bom.so/k6Opdx','NO',82),(165,'https://bom.so/4mZy9u','NO',83),(166,'https://bom.so/5MeBmI','NO',83),(167,'https://bom.so/YpqqRT','NO',84),(168,'https://bom.so/1um9p9','NO',84),(169,'https://bom.so/pofSru','NO',85),(170,'https://bom.so/3tVX0j','NO',85),(171,'https://bom.so/9cc67K','NO',86),(172,'https://bom.so/VMSCcY','NO',86),(173,'https://bom.so/0AXsER','NO',87),(174,'https://bom.so/2A9cbP','NO',87),(175,'https://bom.so/o2zQHP','NO',88),(176,'https://bom.so/xR6npD','NO',88),(177,'https://bom.so/55qibj','NO',89),(178,'https://bom.so/tYhatI','NO',89),(179,'https://bom.so/ILIkKK','NO',90),(180,'https://bom.so/45LsmV','NO',90),(181,'https://bom.so/gmqLgs','NO',91),(182,'https://bom.so/ipIcSv','NO',91),(183,'https://bom.so/iQkuRs','NO',92),(184,'https://bom.so/WI35Pn','NO',92),(185,'https://bom.so/4PdrXV','NO',93),(186,'https://bom.so/CCbYAf','NO',93),(187,'https://bom.so/25qpct','NO',94),(188,'https://bom.so/QrWJtb','NO',94),(189,'https://bom.so/7QATbz','NO',95),(190,'https://bom.so/3fyylX','NO',95),(191,'https://bom.so/zFqPfo','NO',96),(192,'https://bom.so/DRgLpB','NO',96),(193,'https://bom.so/1bGtyf','NO',97),(194,'https://bom.so/iouZwp','NO',97),(195,'https://bom.so/nh4wBH','NO',98),(196,'https://bom.so/EPHj2p','NO',98),(197,'https://bom.so/PNEahF','NO',99),(198,'https://bom.so/PNEahF','NO',99),(199,'https://bom.so/b1NcAm','NO',100),(200,'https://bom.so/4gYbWQ','NO',100),(201,'https://bom.so/b1NcAm','NO',101),(202,'https://bom.so/4gYbWQ','NO',101),(203,'https://bom.so/4v1e7h','NO',102),(204,'https://bom.so/voaUiA','NO',102),(205,'https://bom.so/pQB48V','NO',103),(206,'https://bom.so/kXaq4M','NO',103),(207,'https://bom.so/YOiCzr','NO',104),(208,'https://bom.so/I8dF9R','NO',104),(209,'https://bom.so/uuV79B','NO',105),(210,'https://bom.so/6saHgN','NO',105),(211,'https://bom.so/C3WfWt','NO',106),(212,'https://bom.so/Fa0pTW','NO',106),(213,'https://bom.so/C3WfWt','NO',107),(214,'https://bom.so/Fa0pTW','NO',107),(215,'https://bom.so/H6eKl5','NO',108),(216,'https://bom.so/yetk2m','NO',108),(217,'https://bom.so/H6eKl5','NO',109),(218,'https://bom.so/yetk2m','NO',109),(219,'https://bom.so/H6eKl5','NO',110),(220,'https://bom.so/yetk2m','NO',110),(221,'https://bom.so/GYjxmh','NO',111),(222,'https://bom.so/QAD9Wf','NO',111),(223,'https://bom.so/GYjxmh','NO',112),(224,'https://bom.so/QAD9Wf','NO',112),(225,'https://bom.so/sL5wjy','NO',113),(226,'https://bom.so/BDKG1k','NO',113),(227,'https://bom.so/sL5wjy','NO',114),(228,'https://bom.so/BDKG1k','NO',114),(229,'https://bom.so/gVNC31','NO',115),(230,'https://bom.so/iI0ZAW','NO',115),(231,'https://bom.so/gVNC31','NO',116),(232,'https://bom.so/iI0ZAW','NO',116),(233,'https://bom.so/gVNC31','NO',117),(234,'https://bom.so/iI0ZAW','NO',117),(235,'https://bom.so/gVNC31','NO',118),(236,'https://bom.so/iI0ZAW','NO',118),(237,'https://bom.so/wUf5ki','NO',119),(238,'https://bom.so/MyaCar','NO',119),(239,'https://bom.so/wUf5ki','NO',120),(240,'https://bom.so/MyaCar','NO',120),(241,'https://bom.so/Szjt1D','NO',121),(242,'https://bom.so/5gmhB3','NO',121),(243,'https://bom.so/s0Cm59','NO',122),(244,'https://bom.so/5gmhB3','NO',122),(245,'https://bom.so/5gmhB3','NO',123),(246,'https://bom.so/5gmhB3','NO',123),(247,'https://bom.so/KmSpsd','NO',124),(248,'https://bom.so/5gmhB3','NO',124),(249,'https://bom.so/9RBHeq','NO',125),(250,'https://bom.so/XbfiXD','NO',125),(251,'https://bom.so/kRLoAu','NO',126),(252,'https://bom.so/TnpbE4','NO',126),(253,'https://bom.so/iSmvNc','NO',127),(254,'https://bom.so/A7BZ7d','NO',127),(255,'https://bom.so/eHEE5t','NO',128),(256,'https://bom.so/A7BZ7d','NO',128),(257,'https://bom.so/eHEE5t','NO',129),(258,'https://bom.so/A7BZ7d','NO',129),(259,'https://bom.so/DFtgJQ','NO',130),(260,'https://bom.so/IiPbb4','NO',130),(261,'https://bom.so/yBrmKH','NO',131),(262,'https://bom.so/IiPbb4','NO',131),(263,'https://bom.so/yBrmKH','NO',132),(264,'https://bom.so/IiPbb4','NO',132),(265,'hinh test','YES',132),(266,'hinh','NO',132);
/*!40000 ALTER TABLE `image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderdetail`
--

DROP TABLE IF EXISTS `orderdetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orderdetail` (
  `order_id` int NOT NULL,
  `product_id` int NOT NULL,
  `quantity` int DEFAULT NULL,
  `amount` mediumtext,
  `is_delete` varchar(10) DEFAULT NULL,
  `is_review` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`order_id`,`product_id`),
  KEY `OD_maPRODUCT_FK` (`product_id`),
  CONSTRAINT `OD_maORDER_FK` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`) ON DELETE CASCADE,
  CONSTRAINT `OD_maPRODUCT_FK` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE CASCADE,
  CONSTRAINT `OR_isDeleteORDERDETAIL_CHK` CHECK ((`is_delete` in (_utf8mb4'NO',_utf8mb4'YES'))),
  CONSTRAINT `OR_isReviewORDERDETAIL_CHK` CHECK ((`is_review` in (_utf8mb4'NO',_utf8mb4'YES')))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderdetail`
--

LOCK TABLES `orderdetail` WRITE;
/*!40000 ALTER TABLE `orderdetail` DISABLE KEYS */;
INSERT INTO `orderdetail` VALUES (1,80,1,'190000','NO',NULL),(2,82,1,'252000','NO','YES'),(3,95,1,'254800','NO','YES'),(3,117,1,'604500','NO','YES'),(4,15,1,'17846400','NO','YES'),(4,84,1,'738700','NO','YES'),(4,92,1,'600000','NO','YES'),(4,122,1,'903500','NO','YES'),(5,59,1,'28820700','NO','YES'),(5,84,1,'738700','NO','YES'),(5,92,1,'600000','NO','YES'),(5,122,1,'903500','NO','YES'),(6,28,1,'18422400','NO','YES'),(6,78,1,'4990000','NO','YES'),(6,95,1,'254800','NO','YES'),(6,107,1,'2185500','NO','YES'),(6,121,1,'1997500','NO','YES'),(7,82,1,'252000','NO','YES'),(7,83,1,'2990000','NO','YES'),(7,84,1,'1099400','NO','YES'),(7,85,1,'1000800','NO','YES'),(7,86,1,'738700','NO','YES'),(8,92,1,'640000','NO','YES'),(8,93,1,'340000','NO','YES'),(8,94,1,'377300','NO','YES'),(8,95,1,'254800','NO','YES'),(8,96,1,'407320','NO','YES'),(9,100,1,'1757700','NO','YES'),(9,101,1,'2315700','NO','YES'),(9,102,1,'5133600','NO','YES'),(9,103,1,'1664700','NO','YES'),(9,104,1,'2398700','NO','YES'),(10,40,1,'25210300','NO','YES'),(10,41,1,'22217700','NO','YES'),(10,42,1,'35880300','NO','YES'),(10,43,1,'24087000','NO','YES'),(10,44,1,'10685700','NO','YES'),(11,73,1,'19169400','NO','YES'),(11,74,1,'69740700','NO','YES'),(11,75,1,'3072300','NO','YES'),(11,76,1,'5039370','NO','YES'),(11,77,1,'11120000','NO','YES'),(12,90,1,'999600','NO','YES'),(12,91,1,'890000','NO','YES'),(12,92,1,'600000','NO','YES'),(12,93,1,'340000','NO','YES'),(12,94,1,'245630','NO','YES'),(13,7,1,'28341000','NO','YES'),(13,8,1,'19845500','NO','YES'),(13,9,1,'21466100','NO','YES'),(13,10,1,'17288700','NO','YES'),(13,11,1,'14687200','NO','YES'),(14,73,1,'19169400','NO','YES'),(14,74,1,'69740700','NO','YES'),(14,90,1,'999600','NO','YES'),(14,91,1,'890000','NO','YES'),(14,92,1,'600000','NO','YES'),(15,74,1,'69740700','NO','YES');
/*!40000 ALTER TABLE `orderdetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `phone_number` varchar(15) DEFAULT NULL,
  `create_date` timestamp NULL DEFAULT NULL,
  `total_amount` mediumtext,
  `note` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `payment_method` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `payment_status` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `status_order` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `customer_id` int DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  KEY `OR_maCUSTOMER_FK` (`customer_id`),
  CONSTRAINT `OR_maCUSTOMER_FK` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`) ON DELETE CASCADE,
  CONSTRAINT `OR_activeORDER_CHK` CHECK ((`status_order` in (_utf8mb4'Chưa duyệt',_utf8mb4'Đã duyệt',_utf8mb4'Đã giao',_utf8mb4'Đã hủy')))
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,'Tam Dân','0326000692','2021-10-04 17:00:00','190000','','COD','Chưa thanh toán','Đã giao',1),(2,'Tam Thái','0326000693','2021-10-09 17:00:00','252000','','COD','Đã thanh toán','Đã giao',2),(3,'Tam Lãnh','0326000694','2021-10-14 17:00:00','859300','','COD','Đã thanh toán','Đã giao',3),(4,'Tam An','0326000695','2021-10-19 17:00:00','20088600','','COD','Đã thanh toán','Đã giao',4),(5,'Tam Thành','0326000696','2021-10-27 17:00:00','31062900','','COD','Đã thanh toán','Đã giao',1),(6,'Tam Đại','0326000697','2021-11-06 17:00:00','15000','','COD','Đã thanh toán','Đã giao',2),(7,'Phú Ninh','0326000698','2021-11-14 17:00:00','6080900','','COD','Đã thanh toán','Đã giao',3),(8,'Duy Xuyên','0326000699','2021-11-21 17:00:00','2019420','','COD','Đã thanh toán','Đã giao',4),(9,'Thăng Bình','0326000670','2021-11-27 17:00:00','13270400','','COD','Đã thanh toán','Đã giao',1),(10,'Quận 9','0326110670','2021-11-30 17:00:00','118081000','','COD','Đã thanh toán','Đã giao',2),(11,'Quận 5','0326110670','2021-12-07 17:00:00','108141770','','COD','Đã thanh toán','Đã giao',2),(12,'Quận Tân Bình','0326110670','2021-12-14 17:00:00','3075230','','COD','Đã thanh toán','Đã giao',2),(13,'Quận Gò Vấp','0326110670','2021-12-23 17:00:00','101628500','','COD','Đã thanh toán','Đã giao',2),(14,'Buôn Mê Thuột','0326110670','2021-12-26 17:00:00','91399700','','COD','Chưa thanh toán','Đã duyệt',2),(15,'Lê Văn Việt, Quận 9','0326110670','2021-12-28 17:00:00','69740700','','COD','Chưa thanh toán','Đã duyệt',2);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `product_id` int NOT NULL AUTO_INCREMENT,
  `product_name` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `discount` int DEFAULT NULL,
  `unit_price` int DEFAULT NULL,
  `description_product` varchar(5000) DEFAULT NULL,
  `category_id` int DEFAULT NULL,
  `supplier_id` int DEFAULT NULL,
  `is_delete` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`product_id`),
  KEY `PR_maCATEGORY_FK` (`category_id`),
  KEY `PR_maSUPPLIER_FK` (`supplier_id`),
  CONSTRAINT `PR_maCATEGORY_FK` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`) ON DELETE CASCADE,
  CONSTRAINT `PR_maSUPPLIER_FK` FOREIGN KEY (`supplier_id`) REFERENCES `supplier` (`supplier_id`) ON DELETE CASCADE,
  CONSTRAINT `PR_isDeletePRODUCT_CHK` CHECK ((`is_delete` in (_utf8mb4'NO',_utf8mb4'YES')))
) ENGINE=InnoDB AUTO_INCREMENT=133 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'Laptop HP 14-DQ2055WM 39K15UA',100,10,12490000,'Phủ nhôm bền bỉ, không gian trải nghiệm đủ đầy</br>Hiệu năng lấp đầy nhu cầu người dùng',1,1,'NO'),(2,'Laptop HP 15S-FQ2602TU 4B6D3PA',100,0,16390000,'Cấu hình ổn định với chip intel thế hệ 11</br>Kích thước màn hình 15,6 inches và độ phân giải Full HD',1,1,'NO'),(3,'Laptop HP Pavilion x360 14-DW1018TU 2H3N6PA',100,6,17990000,'Màn hình 14 inch nhỏ gọn, hỗ trợ gập lên đến 360 độ</br>Chip Intel 5 thế hệ 11, ổ cứng SSD 512GB',1,1,'NO'),(4,'Laptop HP 15-DY2045 2Q1H3UA',100,6,17490000,'Thiết kế cứng cáp, trải nghiệm hình ảnh không gian lớn</br>Chip Intel thế hệ 11 và RAM lớn cho sức mạnh ấn tượng, pin thời lượng cực lâu',1,1,'NO'),(5,'Laptop HP Probook 430 G8 2H0N6PA',100,0,18590000,'Laptop HP Probook 430 G8- Thiết kế màu bạc sang trọng, gọn nhẹ</br>Hỗ trợ đầy đủ các cổng kết nối</br>Màn hình rộng full HD và viền màn hình siêu mỏng</br>Dung lượng pin lớn - Kết nối không dây siêu tốc',1,1,'NO'),(6,'Laptop HP Envy 13 AQ1022TU 8QN69PA',100,0,22690000,'',1,1,'NO'),(7,'Laptop HP Zbook Firefly 14 G8 1A2F1AV',100,10,31490000,'Thiết kế mỏng nhẹ, màn hình 14 inch nhỏ gọn, độ sáng cao</br>Hiệu năng ổn định với chip Intel thế hệ 11, SSD 512GB',1,1,'NO'),(8,'Laptop HP 340S G7 36A37PA',100,5,20890000,'Thiết kế gọn nhẹ, màn hình 14 inch nhỏ gọn, hiển thị sắc nét</br>Hiệu năng siêu mạnh với CPU i7 thế hệ 10, SSD dung lượng cao',1,1,'NO'),(9,'Laptop HP 348 G7 9PH23PA',100,3,22130000,'Bộ não Intel Core i7 10510U, Ram DDR4 8GB</br>Siêu bền bỉ tiêu chuẩn quân đội, bảo mật an toàn',1,1,'NO'),(10,'Laptop Dell Inspiron 3501 5580BLK',100,7,18590000,'Thiết kế gọn nhẹ, màn hình kích thước lớn</br>Hiệu năng mượt cùng viên pin đủ dùng',1,2,'NO'),(11,'LAPTOP DELL INSPIRON 3505 i3505-A542BLK',100,12,16690000,'Thiết kế mỏng nhẹ, màn hình kích thước lớn với viền siêu mỏng</br>Hiệu năng ổn định trong tầm giá với con chip AMD Ryzen 5 và SSD PCIE',1,2,'NO'),(12,'Laptop Dell Inspiron 7306 N3I5202W',100,6,26590000,'Màn hình FHD 13.3 inch nhỏ gọn, tần số quét 60 Hz, góc cạnh bo tròn</br>Hiệu năng mạnh mẽ với Intel Core i5-1135G7',1,2,'NO'),(13,'Laptop Dell Vostro 3405 V4R53500U003W',100,8,1699000,'',1,2,'NO'),(14,'Laptop Dell Vostro 3500 P112F002ABL',100,3,22190000,'Thiết kế đơn giản với màu đen sang trọng, chất liệu cứng cáp</br>Hiệu năng mạnh mẽ với bộ vi xử lý khỏe, bộ nhớ SSD dung lượng cao',1,2,'NO'),(15,'Laptop Dell Vostro 3400 70234073',100,4,18590000,'Mỏng nhẹ tinh tế, màn hình chất lượng</br>Hiệu năng hàng đầu',1,2,'NO'),(16,'Laptop Dell Vostro 3510 P112F002BBL',100,4,21990000,'Thiết kế gọn gàng, mỏng nhẹ</br>Cấu hình mạnh mẽ, ổn định</br>Hình ảnh đặc sắc, âm thanh ấn tượng',1,2,'NO'),(17,'Laptop Dell Vostro 5510 70253901',100,5,21690000,'Kiểu dáng tinh tế, màn hình rộng lớn</br>Hiệu năng phù hợp công việc kiêm học tập',1,2,'NO'),(18,'Laptop Dell Vostro 5402 V5402A',100,5,21790000,'Laptop mỏng nhẹ, màn hình 14 inch nhỏ gọn</br>Hiệu năng mượt mà với chip Intel thế hệ 11',1,2,'NO'),(19,'Laptop Dell Gaming G5 5500 P89F003',100,3,33610000,'',1,2,'NO'),(20,'Laptop Dell Gaming G15 5511 P105F006BGR',100,7,33990000,'',1,2,'NO'),(21,'Laptop Dell Gaming G15 5515 P105F004DGR',100,5,27190000,'Thiết kế đậm chất gaming, màn hình 120Hz mượt mà</br>Hoạt động ổn định với CPU AMD kết hợp card đồ họa chuyên nghiệp',1,2,'NO'),(22,'Laptop Dell Alienware M15 R6 70262923',100,7,64090000,'Thiết kế cao cấp với khả năng tản nhiệt vượt trội</br>Màn hình chuẩn 2K sắc nét, cùng hiệu suất vượt trội',1,2,'NO'),(23,'Laptop Dell Gaming G3 G3500CW',100,7,2590000,'Thiết kế gọn nhẹ, màn hình kích thước lớn</br>Hiệu năng mượt cùng viên pin đủ dùng',1,2,'NO'),(24,'Laptop ASUS VivoBook 15 A515EA',100,0,15360000,'',1,3,'NO'),(25,'Laptop ASUS VivoBook X415MA-BV088T',100,0,9390000,'',1,3,'NO'),(26,'Laptop ASUS VivoBook Flip TM420IA-EC031T',100,0,17190000,'Thiết kế mỏng nhẹ, bản lề quay 360 ấn tượng',1,3,'NO'),(27,'Laptop ASUS Vivobook R565EA-UH31T',100,16,12490000,'Thiết kế gọn nhẹ, màn hình kích thước lớn</br>Hiệu năng mượt cùng viên pin đủ dùng',1,3,'NO'),(28,'Laptop ASUS VivoBook Flip 14 TM420IA-EC227T',100,4,19190000,'Sử dụng CPU đến từ AMD</br>Màn hình viền mỏng, gọn nhẹ ấn tượng</br>Màu sắc cá tính, sang trọng, trọng lượng nhẹ dễ dàng di chuyển',1,3,'NO'),(29,'Laptop ASUS VivoBook S433EA',100,0,19490000,'Kích thước nhỏ gọn, màn hình NanoEdge 14 inch Full HD sắc nét</br>Hỗ trợ nhiều cổng kết nối, công nghệ ASUS SonicMaster sống động, pin 3 Cells 50WHrs',1,3,'NO'),(30,'Laptop ASUS VivoBook M513UA-L1240T',100,7,19690000,'Thiết kế mỏng nhẹ, linh hoạt cùng màu ánh kim thanh lịch</br>Hiệu năng khỏe khoắn với chip AMD Ryzen, ổ cứng SSD đọc ghi dữ liệu tốc độ cao',1,3,'NO'),(31,'Laptop ASUS VivoBook 14 A415EA-EB360T',100,7,18290000,'Trọng lượng chỉ 1.4kg, màn hình NanoEdge 14 inches</br>Sở hữu hiệu năng tốt: Intel Core-i5, VGA Iris Xe Graphics, 8GB RAM',1,3,'NO'),(32,'Laptop ASUS Zenbook UX425EA',100,7,23390000,'Vẻ ngoài hiện đại và cứng cáp với màn hình 14 inch FHD</br>Vi xử lý mạnh mẽ kèm tiện ích phần cứng lẫn phần mềm',1,3,'NO'),(33,'Laptop ASUS ZenBook UX325EA-KG363T',100,4,23290000,'Bộ vi xử lý vượt trội giúp nâng cao hiệu suất công việc</br>Thiết kế gọn nhẹ, thanh lịch',1,3,'NO'),(34,'Laptop ASUS ZenBook Flip UX363EA',100,2,26690000,'Thiết kế cứng cáp & linh hoạt với màn hình xoay ngược 360 độ</br>Vi xử lý Intel Core i5-1135G7, RAM 8GB và 512GB SSD cho hiệu năng làm việc tốt',1,3,'NO'),(35,'Laptop ASUS Zenbook Duo 14 UX482EG',100,8,32990000,'Thiết kế màn hình kép ScreenPad™ Plus với độ phân giải Full HD sắc nét</br>Vi xử lý Intel Core i5-1135G7 cùng đồ họa GeForce MX450 2GB vận hành mạnh mẽ',1,3,'NO'),(36,'Laptop ASUS Gaming ROG Strix G15 G513IH-HN015T',100,5,22690000,'Thiết kế sang trọng, logo Gaming ROG, viền màn hình siêu mỏng</br>Hoạt động ổn định với chip H, hệ thống 2 loa chất lượng',1,3,'NO'),(37,'Laptop ASUS Gaming ROG Zephyrus G14 GA401QH-HZ035T',100,4,27590000,'Thiết kế độc đáo, trọng lượng nhẹ</br>Hiệu năng mạnh mẽ với chip xử lý nhanh chóng, dung lượng RAM và ROM lớn',1,3,'NO'),(38,'Laptop ASUS Gaming FX506LH-HN002T',100,5,20890000,'Kiểu dáng gaming sang trọng, màn hình hỗ trợ 4K, tần số quét 144 Hz</br>Sức mạnh ấn tượng bởi Core i5-10300H, GeForce GTX 1650, 8 GB RAM',1,3,'NO'),(39,'Laptop Asus TUF Gaming F15 FX506HC-HN002T',100,7,24390000,'Hiệu năng đỉnh cao, tần số quét 144Hz</br>Bền bỉ với dung lượng pin dài, tính năng tản nhiệt hiệu quả',1,3,'NO'),(40,'Laptop ASUS TUF Gaming FA506IU-AL127T',100,3,25990000,'Thiết kế sang trọng chất riêng, độ bền đạt chuẩn quân đội Mỹ</br>Màn hình rộng 15.6 inch Full HD 144Hz, Card NVIDIA GeForce GTX 1660Ti 6GB ',1,3,'NO'),(41,'Laptop ASUS TUF DASH F15 FX516PC-HN002T',100,7,23890000,'',1,3,'NO'),(42,'Laptop ASUS ExpertBook B9400CEA-KC0790T',100,3,36990000,'Thiết kế gọn nhẹ, màn hình kích thước lớn</br>Hiệu năng mượt cùng viên pin đủ dùng',1,3,'NO'),(43,'Laptop Asus ExpertBook B5302FEA LF0749W',100,7,25900000,'',1,3,'NO'),(44,'Laptop Acer Aspire 3 A315-56-37DV',100,7,11490000,'Thiết kế mỏng nhẹ, cứng cáp</br>Cấu hình ổn định với chip Intel Core i3-1005G1',1,4,'NO'),(45,'Laptop Acer Aspire 5 A514-54-540F',100,2,18090000,'',1,4,'NO'),(46,'Laptop Acer Gaming Aspire 7 A715-42G-R4ST',100,7,18690000,'',1,4,'NO'),(47,'Laptop Acer Swift 3 SF314-43-R4X3',100,4,19490000,'Thiết kế mỏng nhẹ, hiện đại cùng màn hình rõ nét</br>Hiệu năng ổn định với chipset AMD Ryzen 5, RAM 16GB',1,4,'NO'),(48,'Laptop Acer Swift 3X SF314-510G-57MR',100,10,20390000,'Thiết kế nguyên khối – màn hình tràn viền</br>Hiệu năng mạnh với chip Intel thế hệ 11, card vga rời Intel XE Max',1,4,'NO'),(49,'Laptop Acer Swift 5 SF514-55T-51NZ',100,7,22690000,'Thiết kế gọn nhẹ, màn hình kích thước lớn</br>Dung lượng pin sẵn sàng cho ngày dài, bảo mật vân tay an toàn tuyệt đối',1,4,'NO'),(50,'Laptop Gaming Acer Nitro 5 Eagle AN515-57-74NU',100,7,27590000,'Màn hình FHD IPS rộng lớn, tốc độ làm mới 144Hz</br>Bộ vi xử lý thế hệ 11 - Tản nhiệt hiệu suất ấn tượng',1,4,'NO'),(51,'Laptop MSI Gaming BRAVO 15 A4DCR-270VN',100,7,18790000,'Màn hình 15.6\" hỗ trợ công nghệ Freesynce, tần số quét màn hình 144Hz</br>Viền màn hình siêu mỏng, thiết kế vỏ nhôm cao cấp',1,5,'NO'),(52,'Laptop MSI Gaming GL75 Leopard 10SCSK 056VN',100,7,21090000,'Thiết kế nhỏ gọn, màn hình đến 17.3 inches</br>Cấu hình mạnh mẽ với Core i5-10200H, 8GB RAM, 512GB SSD, VGA GTX 1650 Ti',1,5,'NO'),(53,'Laptop MSI Gaming GF65 THIN 10UE-286VN',100,12,26490000,'Thiết kế cứng cáp và hầm hố, màn hình 15.6 inch 144 Hz cho hình ảnh mượt mà</br>Công phá mọi tựa game với CPU Intel Core i5-10500H và đồ họa GeForce RTX 3060',1,5,'NO'),(54,'Laptop MSI Gaming Alpha 15 A4DEK-027VN',100,20,27590000,'Màn hình khủng, tần số quét 144 Hz, bàn phím tùy chỉnh sắc độ</br>Cấu hình mạnh mẽ bởi AMD Ryzen 7, tản nhiệt Cooler Boost 5',1,5,'NO'),(55,'Laptop MSI Katana GF66 11UC-641VN',100,7,28490000,'Thiết kế sắc bén, màn hình sáng bóng như lưỡi gươm</br>Khai thác toàn bộ tiềm năng của kiếm sĩ',1,5,'NO'),(56,'Laptop MSI Creator M16 A11UD-694VN',100,7,36490000,'Màu sắc hình ảnh trung thực, sống động</br>Âm thanh sống động, dung lượng pin lớn',1,5,'NO'),(57,'Laptop MSI Gaming GP76 Leopard 11UG-280VN',100,12,46990000,'Bộ vi xử lý mới nhất, cân mọi tựa game</br>Thiết kế độc đáo, tốc độ làm tươi ấn tượng',1,5,'NO'),(58,'Laptop MSI Gaming GS66 Stealth 10UE-200VN',100,13,51990000,'Thiết kế mạnh mẽ, pin khủng, hình ảnh âm thanh sống động</br>Chiến game cực mượt với vi xử lý Intel Core i7, kết nối wifi 6 vượt trội',1,5,'NO'),(59,'Laptop MSI Gaming Pulse GL66 11UDK-255VN',100,7,30990000,'Màn hình siêu mượt, tản nhiệt siêu mát</br>Hiệu năng siêu mạnh cho mọi tác vụ',1,5,'NO'),(60,'Laptop Lenovo Ideapad 3 14ALC6',100,7,14190000,'Thiết kế mỏng nhẹ, vẻ đẹp tinh tế với màn hình giải trí tuyệt vời</br>Tích hợp hiệu năng mạnh mẽ với các cổng kết nối đầy tiện ích',1,6,'NO'),(61,'Laptop Lenovo Ideapad 5 15ITL05 82FG00M5VN',100,5,18890000,'Ngoại hình sang trọng, hình ảnh sắc nét</brChip Intel Core i5, Ram 8GB mang đến hiệu năng mạnh mẽ',1,6,'NO'),(62,'Laptop Lenovo Ideapad Slim 3 15ARE05 81W4009FVN',100,0,13290000,'Thiết kế gọn nhẹ, màn hình kích thước lớn</br>Hiệu năng mượt cùng viên pin đủ dùng',1,6,'NO'),(63,'Laptop Lenovo Ideapad S340-13IML',100,3,11990000,'Thiết kế gọn nhẹ, màn hình kích thước lớn</br>Hiệu năng mượt cùng viên pin đủ dùng',1,6,'NO'),(64,'Laptop Lenovo Ideapad 5 Pro 14ACN6 82l7007XVN',100,3,21990000,'Thiết kế gọn nhẹ, màn hình kích thước lớn</br>Hiệu năng mượt cùng viên pin đủ dùng',1,6,'NO'),(65,'Laptop Lenovo Ideapad 1 11IGL05',100,20,8790000,'Kiểu dáng xách tay nhẹ gọn, màn hình bắt mắt</brHiệu năng ổn định phục vụ lâu dài',1,6,'NO'),(66,'Laptop Lenovo Thinkbook 15 G2 ITL',100,5,20590000,'Thời thượng và mỏng gọn, với màn hình 15.6 inch bảo vệ mắt hiện đại</br>Làm việc tối ưu với chip Intel Core i7-1165G7, 512 GB SSD cùng Windows 10',1,6,'NO'),(67,'Laptop Lenovo ThinkPad E14',100,15,13490000,'Dung lượng khá ấn tượng với RAM 8GB và ổ cứng 1TB</br>Màn hình có kích thước 14 inch, sử dụng công nghệ chống chói',1,6,'NO'),(68,'Laptop Lenovo Thinkpad E490S 20NGS01K00',100,17,15190000,'Hình dáng thanh lịch, thiết kế mỏng gọn, tích hợp nhận dạng vân tay</br>Màn hình 14 inch FHD thiết kế viền mỏng giúp quan sát trọn vẹn hình ảnh',1,6,'NO'),(69,'Laptop Lenovo Thinkpad X13 GEN 2',100,8,33590000,'Nhỏ gọn tinh tế, màn hình ấn tượng</br>Sức mạnh bứt phá, trải nghiệm đặc biệt',1,6,'NO'),(70,'Laptop Lenovo Ideapad Gaming 3 15IMH05 81Y400Y8VN',100,8,20590000,'Màn hình kích thước lớn, tần số quét 120Hz</br>Cấu hình mạnh với chip Intel core i5 thế hệ 10, SSD 512GB',1,6,'NO'),(71,'Laptop Lenovo Gaming Legion 5 15ARH05 82B500GTVN',100,7,24590000,'Thiết kế gọn nhẹ, màn hình kích thước lớn</br>Hiệu năng mượt cùng viên pin đủ dùng',1,6,'NO'),(72,'Laptop Lenovo Gaming L340-15IRH 81LK01J3VN',100,15,16990000,'Thiết kế gọn nhẹ, màn hình kích thước lớn</br>Hiệu năng mượt cùng viên pin đủ dùng',1,6,'NO'),(73,'Laptop Lenovo Ideapad Gaming 3 15IMH05 81Y400Y9VN',100,14,22290000,'Thiết kế gọn nhẹ, màn hình kích thước lớn</br>Hiệu năng mượt cùng viên pin đủ dùng',1,6,'NO'),(74,'Laptop Lenovo Legion 7 16ACHG6 82N60039VN',100,7,74990000,'Con chip dòng H mang lại hiệu năng vượt trội</br>Tản nhiệt chất lượng với Legion Coldfront 3.0',1,6,'NO'),(75,'Tai nghe Bluetooth Apple AirPods 2',100,23,3990000,'Dung lượng pin lớn, sử dụng bền bỉ</br>Thiết kế nhỏ gọn, bắt mắt',2,7,'NO'),(76,'Tai nghe Bluetooth Apple AirPods Pro',100,37,7999000,'Airpod Pro sở hữu thiết kế nhỏ gọn, trọng lượng 5.4 gram</br>Âm thanh trên Apple Airpods Pro rõ nét với công nghệ khử tiếng ồn chủ động',2,7,'NO'),(77,'Tai nghe chụp tai chống ồn Apple AirPods Max',100,20,13900000,'Thiết kế chụp tai sử dụng êm ái, chất liệu cao cấp nổi bật</br>Chất lượng âm thanh cực kỳ cao cấp, chống ồn hiệu quả',2,7,'NO'),(78,'Tai nghe Bluetooth Apple AirPods 3',100,0,4990000,'TThiết kế sang trọng, nhiều thay đổi so với thế hệ trước</br>Dung lượng pin được cải thiện',2,7,'NO'),(79,'Tai nghe không dây Samsung Galaxy Buds 2',100,30,2990000,'3 micro cùng công nghệ nhận diện giọng nói</br>Kết nối bluetooth chuẩn v5.2',2,9,'NO'),(80,'Tai nghe Samsung Galaxy AKG EO-IG955',100,24,250000,'',2,9,'NO'),(81,'Tai nghe nhét tai Samsung IG935B',100,10,300000,'Tai nghe Samsung IG935B thiết kế nhỏ gọn trẻ trung</br>Cảm giác đeo thoải mái của tai nghe samsung IG935B',2,9,'NO'),(82,'Tai nghe nhét tai Samsung EG920B',100,10,280000,'Samsung EG920B - Thiết kế cao su bền bỉ dài 1.2m, kèm theo cặp đệm dạng thường và dạng móc</br>Âm thanh trung thực, độ bass dày, đường kính 12mm trên Samsung EG920B ',2,9,'NO'),(83,'Tai nghe không dây JBL Live Pro+',100,0,2990000,'Thiết kế nhỏ gọn, hỗ trợ chống nước IPX4</br>Âm thanh JBL Signature Sound chất lượng',2,8,'NO'),(84,'Tai Nghe Không Dây JBL Tune 120 TWS',100,54,2390000,'Thiết kế độc đáo, gọn nhẹ với driver đường kính 5.8mm và màu sắc thời trang</br>JBL Tune 120Tws sử dụng chuẩn kết nối bluetooth 4.2, kết nối trợ lý ảo tiện lợi và nhanh chóng',2,8,'NO'),(85,'Tai nghe chụp tai JBL Tune 510BT',100,28,1390000,'Thiết kế đệm tai siêu êm, điều khiển âm thanh tiện lợi</br>Chuẩn chất âm JBL Pure Bass mạnh mẽ với gần 40 giờ nghe liên tục',2,8,'NO'),(86,'Tai nghe gaming có dây JBL Quantum 50',100,17,890000,'Thiết kế độc đáo và thời trang, nhiều màu sắc để lựa chọn</br>Chất âm JBL QuantumSOUND Signature độc quyền, tương thích với nhiều thiết bị',2,8,'NO'),(87,'Tai nghe chụp tai Sony WH-1000XM4',100,23,8490000,'Chức năng khử tiếng ồn chủ động, hỗ trợ “Speak to Chat”</br>Khả năng tiêu thụ điện năng thấp, pin sử dụng 40 giờ',2,10,'NO'),(88,'Tai nghe không dây chống ồn Sony WF-1000XM3',100,30,5490000,'Thiết kế ôm sát không bị trượt ra ngoài, kèm phụ kiện eartip hybrid</br>Cảm biến tiếng ồn kép, bộ xử lý chống ồn HD QN1e ',2,10,'NO'),(89,'Tai nghe không dây Sony WF-1000XM4',100,15,6490000,'Hiệu suất chống ồn đỉnh cao, màng loa 6 mm</br>Tích hợp công nghệ hiện đại: Edge-AI, DSEE ExtremeTM  tăng cường âm thanh',2,10,'NO'),(90,'Tai nghe Chụp Tai Không Dây Sony WH-CH510',100,16,1190000,'Sony WH-CH510 - Thiết kế gọn nhẹ với trọng lượng chỉ 132g cùng nút điều khiển linh hoạt</br>Thời lượng pin 35 giờ cùng âm thanh phát trực tiếp thông qua Bluetooth 5.0',2,10,'NO'),(91,'Tai nghe Sony MDR-XB55AP',100,0,890000,'Thiết kế từ nhựa và kim loại cùng với housing góc nghiêng 45o cho cảm giác đeo dễ chịu, thoải mái</br>Nói không với tình trạng rối dây khi dây của tai nghe được thiết dạng dẹt sọc gân nổi',2,10,'NO'),(92,'Chuột không dây Logitech M590',100,25,800000,'Thiết kế thông minh, nhỏ gọn và hiện đại</br>Hoạt động tuyệt vời trong yên lặng, thao tác nhẹ nhàng và chính xác',3,11,'NO'),(93,'Chuột không dây Logitech M331',100,0,340000,'Thiết kế bo tròn ôm sát lòng bàn tay, trọng lượng chỉ 101g, chất liệu nhựa cứng</br>Chuột quang độ phân giải lên đến 1000dpi, kết nối không dây với khoảng cách 10m',3,11,'NO'),(94,'Chuột không dây Logitech M221',100,23,319000,'Thiết kế linh hoạt, tốc độ không dây 2.4 GHz</br>Tương thích với hầu hết hệ điều hành, giảm thiểu 90% tiếng ồn',3,11,'NO'),(95,'Chuột không dây Logitech M238 Marvel Collection',100,48,490000,'Độ phân giải 1000 DPI, phạm vi kết nối 10m</br>Đầu tín hiệu receiver nano, thời gian sử dụng đến 12 tháng',3,11,'NO'),(96,'Chuột Gaming Logitech G102 LightSync',100,32,599000,'Thiết kế cổ điển với 6 nút nhấn cùng hệ thống LED RGB</br>DPI lên đến 8.000 với cảm biến cấp độ chơi game cho sở thích riêng',3,11,'NO'),(97,'Chuột không dây Logitech MX Anywhere 3',100,19,1999000,'Thiết kế không dây với 2 cơ chế kết nối</br>Con cuộn Magspeed ưu việt, tương thích nhiều hệ điều hành',3,11,'NO'),(98,'Chuột không dây Logitech MX Master 2S',100,36,2490000,'',3,11,'NO'),(99,'Chuột chơi game không dây Logitech G502 Lightspeed',100,7,2590000,'Thiết kế mạnh mẽ, trọng lượng siêu nhẹ</br>Cảm biến Hero độc đáo, độ chính xác tuyệt đối',3,11,'NO'),(100,'Ổ cứng SSD Samsung 860 Evo 250GB M.2 2280 SATA 3 - MZ-N6E250BW',100,7,1890000,'Tối ưu hóa tốc độ lên mức tối đa</br>Cải thiện năng lượng hiệu quả',4,9,'NO'),(101,'Ổ cứng SSD Samsung 860 Evo 500GB M.2 2280 SATA 3 - MZ-N6E500BW',100,7,2490000,'Tối ưu hóa tốc độ lên mức tối đa</br>Cải thiện năng lượng hiệu quả',4,9,'NO'),(102,'Ổ cứng SSD Samsung 970 Pro 512GB M.2 2280 NVMe - MZ-V7P512BW',100,7,5520000,'Tối ưu hóa tốc độ lên mức tối đa</br>Cải thiện năng lượng hiệu quả',4,9,'NO'),(103,'Ổ cứng SSD Samsung 970 Evo Plus 250GB M.2 NVMe - MZ-V7S250BW',100,7,1790000,'Tối ưu hóa tốc độ lên mức tối đa</br>Cải thiện năng lượng hiệu quả',4,9,'NO'),(104,'Ổ cứng SSD Samsung 970 Evo Plus 500GB M.2 NVMe - MZ-V7S500BW',100,17,2890000,'Tối ưu hóa tốc độ lên mức tối đa</br>Cải thiện năng lượng hiệu quả',4,9,'NO'),(105,'Ổ cứng SSD Samsung 980 PRO 500GB NVMe M.2 PCIe 4.0 (MZ-V8P500BW)',100,7,3990000,'Tối ưu hóa tốc độ lên mức tối đa</br>Cải thiện năng lượng hiệu quả',4,9,'NO'),(106,'Ổ cứng SSD Samsung 870 EVO 250GB SATA III 2.5 inch (MZ-77E250BW)',100,7,1890000,'Tối ưu hóa tốc độ lên mức tối đa</br>Cải thiện năng lượng hiệu quả',4,9,'NO'),(107,'Ổ cứng SSD Samsung 870 EVO 500GB SATA III 2.5 inch (MZ-77E500BW)',100,7,2350000,'Tối ưu hóa tốc độ lên mức tối đa</br>Cải thiện năng lượng hiệu quả',4,9,'NO'),(108,'Ổ cứng SSD Kingston A400 120GB Sata 3 (SA400S37/120G)',100,7,790000,'',4,12,'NO'),(109,'Ổ cứng SSD Kingston A400 240GB Sata 3 (SA400S37/240G)',100,7,1130000,'',4,12,'NO'),(110,'Ổ cứng SSD Kingston A400 480GB Sata 3 (SA400S37/480G)',100,7,1610000,'',4,12,'NO'),(111,'Ổ cứng SSD Kingston KC600 512GB 2.5\" SATA 3',100,7,2190000,'',4,12,'NO'),(112,'Ổ cứng SSD Kingston KC600 256GB 2.5\" SATA 3',100,7,1390000,'',4,12,'NO'),(113,'Ổ cứng SSD Kingston A2000 250GB M.2 2280 NVMe PCIe',100,7,1490000,'',4,12,'NO'),(114,'Ổ cứng SSD Kingston A2000 500GB M.2 2280 NVMe PCIe',100,7,2200000,'',4,12,'NO'),(115,'RAM desktop KINGMAX (1x4GB) DDR3 1600MHz',100,7,790000,'Tính ổn định cao, độ tin cậy và khả năng tương thích</br>Ứng dụng rộng hơn và tiêu thụ điện năng thấp hơn',5,13,'NO'),(116,'RAM desktop KINGMAX (1x8GB) DDR3 1600MHz',100,7,1250000,'Tính ổn định cao, độ tin cậy và khả năng tương thích</br>Ứng dụng rộng hơn và tiêu thụ điện năng thấp hơn',5,13,'NO'),(117,'RAM desktop KINGMAX (1x4GB) DDR4 2400MHz',100,7,650000,'Tính ổn định cao, độ tin cậy và khả năng tương thích</br>Ứng dụng rộng hơn và tiêu thụ điện năng thấp hơn',5,13,'NO'),(118,'RAM desktop KINGMAX (1x8GB) DDR4 2400MHz',100,7,900000,'Tính ổn định cao, độ tin cậy và khả năng tương thích</br>Ứng dụng rộng hơn và tiêu thụ điện năng thấp hơn',5,13,'NO'),(119,'RAM desktop KINGMAX HEATSINK (Zeus) (1 x 8GB) DDR4 3200MHz',100,7,1400000,'Tính ổn định cao, độ tin cậy và khả năng tương thích</br>Ứng dụng rộng hơn và tiêu thụ điện năng thấp hơn',5,13,'NO'),(120,'RAM desktop KINGMAX HEATSINK (Zeus) (1 x 16GB) DDR4 3200MHz',100,7,2590000,'Tính ổn định cao, độ tin cậy và khả năng tương thích</br>Ứng dụng rộng hơn và tiêu thụ điện năng thấp hơn',5,13,'NO'),(121,'RAM laptop KINGMAX Kingmax 16GB (1 x 16GB) DDR4 2666MHz',100,15,2350000,'Tính ổn định cao, độ tin cậy và khả năng tương thích</br>Ứng dụng rộng hơn và tiêu thụ điện năng thấp hơn',5,13,'NO'),(122,'RAM laptop KINGMAX Kingmax 8GB (3200) (1 x 8GB) DDR4 3200MHz',100,35,1390000,'Tính ổn định cao, độ tin cậy và khả năng tương thích</br>Ứng dụng rộng hơn và tiêu thụ điện năng thấp hơn',5,13,'NO'),(123,'RAM laptop KINGMAX Kingmax 16GB (3200) (1 x 16GB) DDR4 3200MHz',100,20,2430000,'Tính ổn định cao, độ tin cậy và khả năng tương thích</br>Ứng dụng rộng hơn và tiêu thụ điện năng thấp hơn',5,13,'NO'),(124,'RAM laptop KINGMAX Kingmax 32GB (3200) (1 x 32GB) DDR4 3200MHz',100,12,4770000,'Tính ổn định cao, độ tin cậy và khả năng tương thích</br>Ứng dụng rộng hơn và tiêu thụ điện năng thấp hơn',5,13,'NO'),(125,'RAM desktop KINGSTON FURY Beast Black 32GB (2x16GB) DDR5 5200MHz (2 x 16GB) DDR5 5200MHz (KF552C40BBK2-32)',100,7,7999000,'Tính ổn định cao, độ tin cậy và khả năng tương thích</br>Ứng dụng rộng hơn và tiêu thụ điện năng thấp hơn',5,12,'NO'),(126,'RAM desktop KINGSTON Fury Beast 32GB (2 x 16GB) DDR4 3600MHz (KF436C18BBK2/32)',100,7,5750000,'Tính ổn định cao, độ tin cậy và khả năng tương thích</br>Ứng dụng rộng hơn và tiêu thụ điện năng thấp hơn',5,12,'NO'),(127,'RAM desktop KINGSTON Fury Beast RGB (2 x 16GB) DDR4 3600MHz (KF436C18BBAK2/32)',100,7,5390000,'Tính ổn định cao, độ tin cậy và khả năng tương thích</br>Ứng dụng rộng hơn và tiêu thụ điện năng thấp hơn',5,12,'NO'),(128,'RAM desktop KINGSTON Fury Renegade RGB 32GB (2 x 16GB) DDR4 3200MHz (KF432C16RB1AK2/32)',100,7,2590000,'Tính ổn định cao, độ tin cậy và khả năng tương thích</br>Ứng dụng rộng hơn và tiêu thụ điện năng thấp hơn',5,12,'NO'),(129,'RAM desktop KINGSTON Fury Beast RGB 32GB (2 x 16GB) DDR4 3200MHz (KF432C16BB1AK2/32)',100,7,2590000,'Tính ổn định cao, độ tin cậy và khả năng tương thích</br>Ứng dụng rộng hơn và tiêu thụ điện năng thấp hơn',5,12,'NO'),(130,'RAM laptop KINGSTON DDR4 Kingston 16GB (3200) (1 x 16GB) DDR4 3200MHz (KVR32S22D8/16)',100,7,2090000,'Tính ổn định cao, độ tin cậy và khả năng tương thích</br>Ứng dụng rộng hơn và tiêu thụ điện năng thấp hơn',5,12,'NO'),(131,'RAM laptop Kingston KVR16LS11/4 (1x4GB) DDR3L 1600MHz',100,7,950000,'Tính ổn định cao, độ tin cậy và khả năng tương thích</br>Ứng dụng rộng hơn và tiêu thụ điện năng thấp hơn',5,12,'NO'),(132,'RAM laptop KINGSTON KCP432SS6/8 (1 x 8GB) DDR4 3200MHz',100,7,1290000,'Tính ổn định cao, độ tin cậy và khả năng tương thích</br>Ứng dụng rộng hơn và tiêu thụ điện năng thấp hơn',5,12,'NO');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reviews`
--

DROP TABLE IF EXISTS `reviews`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reviews` (
  `order_id` int NOT NULL,
  `product_id` int NOT NULL,
  `customer_id` int NOT NULL,
  `comments` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `rating` double DEFAULT NULL,
  PRIMARY KEY (`order_id`,`product_id`,`customer_id`),
  KEY `RE_maPRODUCT_FK` (`product_id`),
  KEY `RE_maCUSTOMER_FK` (`customer_id`),
  CONSTRAINT `RE_maCUSTOMER_FK` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`),
  CONSTRAINT `RE_maORDER_FK` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`),
  CONSTRAINT `RE_maPRODUCT_FK` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reviews`
--

LOCK TABLES `reviews` WRITE;
/*!40000 ALTER TABLE `reviews` DISABLE KEYS */;
/*!40000 ALTER TABLE `reviews` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `supplier` (
  `supplier_id` int NOT NULL AUTO_INCREMENT,
  `supplier_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `supplier_image` varchar(500) DEFAULT NULL,
  `is_delete` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`supplier_id`),
  CONSTRAINT `OR_isDeleteSUPPLIER_CHK` CHECK ((`is_delete` in (_utf8mb4'NO',_utf8mb4'YES')))
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier`
--

LOCK TABLES `supplier` WRITE;
/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
INSERT INTO `supplier` VALUES (1,'HP','https://bom.so/1LnqEL','NO'),(2,'Dell','https://bom.so/Zeic2l','NO'),(3,'Asus','https://bom.so/apjzXy','NO'),(4,'Acer','https://bom.so/8bzJXB','NO'),(5,'MSI','https://bom.so/gZCkrw','NO'),(6,'Lenovo','https://bom.so/Qq1tLx','NO'),(7,'Apple','https://bom.so/kFXgAL','NO'),(8,'JBL','https://bom.so/Mfjbx4','NO'),(9,'Samsung','https://bom.so/0AgolP','NO'),(10,'Sony','https://bom.so/i9Dn7l','NO'),(11,'Logitech','https://bom.so/Wpm82k','NO'),(12,'Kingston','https://bom.so/cMpx2Z','NO'),(13,'Kingmax','https://bom.so/UZwyyg','NO');
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wishlist`
--

DROP TABLE IF EXISTS `wishlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `wishlist` (
  `wishlist_id` int NOT NULL AUTO_INCREMENT,
  `product_id` int DEFAULT NULL,
  `customer_id` int DEFAULT NULL,
  `is_delete` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`wishlist_id`),
  KEY `WL_maPRODUCT_FK` (`product_id`),
  KEY `WL_maCUSTOMER_FK` (`customer_id`),
  CONSTRAINT `WL_maCUSTOMER_FK` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`),
  CONSTRAINT `WL_maPRODUCT_FK` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`),
  CONSTRAINT `WL_isDeleteWishList_CHK` CHECK ((`is_delete` in (_utf8mb4'NO',_utf8mb4'YES')))
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wishlist`
--

LOCK TABLES `wishlist` WRITE;
/*!40000 ALTER TABLE `wishlist` DISABLE KEYS */;
INSERT INTO `wishlist` VALUES (1,132,5,'YES');
/*!40000 ALTER TABLE `wishlist` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-07-07 15:31:31
