-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: localhost    Database: store
-- ------------------------------------------------------
-- Server version	8.0.23

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
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `firstName` varchar(45) NOT NULL,
  `lastName` varchar(45) NOT NULL,
  `email` varchar(60) NOT NULL,
  `phone` char(12) NOT NULL,
  `address` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'Andrei','Amariei','andreiamariei@gmail.com','0745123456','Cluj-Napoca'),(3,'Cristian','Coman','cristiancoman@gmail.com','0788425681','Sebes'),(4,'Denis','Dumitru','denisdumitru@gmail.com','0715426895','Turda'),(6,'Florin','Filimon','florinfilimon@gmail.com','0754125632','Iasi'),(7,'Georgiana','Gherman','gerogianagherman@gmail.com','0719526456','Bucuresti'),(8,'Henry','Habuc','henryhabuc@gmail.com','0748125469','Constanta'),(10,'Jerry','Jackson','jerryjackson@gmail.com','0721321144','Blaj'),(12,'Lucian','Londroman','lucianlondroman@gmail.com','0716482333','Teius'),(14,'Nicolae','Nicoara','nicolaenicoara@gmail.com','0794632152','Sibiu'),(15,'Oana','Olanescu','oanaolanescu@gmail.com','0732132456','Medias'),(16,'Rodica','Rus','rodicarus@gmail.com','0745125456','Lancram');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `placedorder`
--

DROP TABLE IF EXISTS `placedorder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `placedorder` (
  `id` int NOT NULL AUTO_INCREMENT,
  `total` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `placedorder`
--

LOCK TABLES `placedorder` WRITE;
/*!40000 ALTER TABLE `placedorder` DISABLE KEYS */;
INSERT INTO `placedorder` VALUES (1,50),(2,39.2),(3,20.8),(4,57.5),(5,92.8),(6,12.8),(7,19.9),(8,89.4);
/*!40000 ALTER TABLE `placedorder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` int NOT NULL AUTO_INCREMENT,
  `productName` varchar(80) NOT NULL,
  `price` double NOT NULL,
  `quantity` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'caiet de matematica',4.5,30),(2,'pix',1.5,34),(4,'creion mecanic',21,14),(5,'bloc de desen',5.9,18),(6,'rezerva stilou',0.1,113),(7,'agenda 2021',47,5),(8,'calendar 2021',33,10),(9,'semn de carte',8.6,22),(12,'compas',14,9),(13,'raportor',2.7,15),(14,'radiera',5.5,21),(15,'capsator',17.4,14),(16,'creta',5.2,30);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `request`
--

DROP TABLE IF EXISTS `request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `request` (
  `id` int NOT NULL AUTO_INCREMENT,
  `idCustomer` int NOT NULL,
  `idProduct` int NOT NULL,
  `orderedQuantity` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `customer_request` (`idCustomer`),
  KEY `product_request` (`idProduct`),
  CONSTRAINT `customer_request` FOREIGN KEY (`idCustomer`) REFERENCES `customer` (`id`),
  CONSTRAINT `product_request` FOREIGN KEY (`idProduct`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `request`
--

LOCK TABLES `request` WRITE;
/*!40000 ALTER TABLE `request` DISABLE KEYS */;
INSERT INTO `request` VALUES (1,1,2,4),(2,14,8,1),(10,14,6,13),(11,14,9,2),(15,3,5,2),(16,3,6,10),(17,12,5,1),(18,12,12,1),(20,10,7,1);
/*!40000 ALTER TABLE `request` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-04-20 21:08:48
