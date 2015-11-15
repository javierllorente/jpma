-- MySQL dump 10.13  Distrib 5.6.26, for Linux (x86_64)
--
-- Host: localhost    Database: pma
-- ------------------------------------------------------
-- Server version	5.6.26

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `localidad`
--

DROP TABLE IF EXISTS `localidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `localidad` (
  `id` int(5) unsigned zerofill NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `provincia` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  FULLTEXT KEY `nombre` (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `prediccion`
--

DROP TABLE IF EXISTS `prediccion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `prediccion` (
  `id_prediccion` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `id` int(5) unsigned zerofill NOT NULL,
  `fecha` varchar(255) NOT NULL,
  `prob_precipitacion` int(3) unsigned DEFAULT NULL,
  `cota_nieve_prov` int(4) unsigned DEFAULT NULL,
  `estado_cielo` varchar(255) NOT NULL,
  `viento_dir` varchar(2) NOT NULL,
  `viento_vel` int(3) DEFAULT NULL,
  `t_max` int(3) NOT NULL,
  `t_min` int(3) NOT NULL,
  `sens_termica_max` int(3) NOT NULL,
  `sens_termica_min` int(3) NOT NULL,
  `humedad_rel_max` int(3) NOT NULL,
  `humedad_rel_min` int(3) NOT NULL,
  PRIMARY KEY (`id_prediccion`),
  KEY `id` (`id`),
  CONSTRAINT `prediccion_ibfk_1` FOREIGN KEY (`id`) REFERENCES `localidad` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=25355 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-11-15 13:02:05
