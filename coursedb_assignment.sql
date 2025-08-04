-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: localhost    Database: coursedb
-- ------------------------------------------------------
-- Server version	8.0.42

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
-- Table structure for table `assignment`
--

DROP TABLE IF EXISTS `assignment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `assignment` (
  `assignment_id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) NOT NULL,
  `title` varchar(255) NOT NULL,
  `course_id` bigint DEFAULT NULL,
  PRIMARY KEY (`assignment_id`),
  KEY `FKrop26uwnbkstbtfha3ormxp85` (`course_id`),
  CONSTRAINT `FKrop26uwnbkstbtfha3ormxp85` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assignment`
--

LOCK TABLES `assignment` WRITE;
/*!40000 ALTER TABLE `assignment` DISABLE KEYS */;
INSERT INTO `assignment` VALUES (1,'Quiz on Java syntax and basic constructs.','Java Basics Quiz',1),(2,'Build a multi-threaded application in Java.','Advanced Java Project',1),(3,'Clean and transform datasets using pandas.','Python Data Wrangling',2),(4,'Build a CLI tool in Python.','Python Mini Project',2),(5,'Create a dynamic single-page app with React.','Frontend React Challenge',3),(6,'Design and implement REST APIs with Node.js.','Backend API Design',3),(7,'Evaluate models using cross-validation and metrics.','ML Model Evaluation',4),(8,'Create dashboards for insights.','Data Visualization Report',4),(9,'Design responsive layouts in Flutter.','Flutter UI Prototype',5),(10,'Implement state handling with Riverpod.','Flutter State Management',5),(11,'Quiz on Java syntax and basic constructs.','Java Basics Quiz',1),(12,'Build a multi-threaded application in Java.','Advanced Java Project',1),(13,'Clean and transform datasets using pandas.','Python Data Wrangling',2),(14,'Build a CLI tool in Python.','Python Mini Project',2),(15,'Create a dynamic single-page app with React.','Frontend React Challenge',3),(16,'Design and implement REST APIs with Node.js.','Backend API Design',3),(17,'Evaluate models using cross-validation and metrics.','ML Model Evaluation',4),(18,'Create dashboards for insights.','Data Visualization Report',4),(19,'Design responsive layouts in Flutter.','Flutter UI Prototype',5),(20,'Implement state handling with Riverpod.','Flutter State Management',5);
/*!40000 ALTER TABLE `assignment` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-08-05  0:16:35
