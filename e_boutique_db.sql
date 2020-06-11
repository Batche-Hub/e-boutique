CREATE DATABASE  IF NOT EXISTS `e_boutique_db` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `e_boutique_db`;
-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: localhost    Database: e_boutique_db
-- ------------------------------------------------------
-- Server version	5.6.48-log

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
-- Table structure for table `adresse`
--

DROP TABLE IF EXISTS `adresse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `adresse` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `numero` int(3) NOT NULL,
  `rue` varchar(25) NOT NULL,
  `ville` varchar(25) NOT NULL,
  `code_postal` int(4) NOT NULL,
  `utilisateur_id` int(5) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `utilisateur_id_idx` (`utilisateur_id`),
  CONSTRAINT `utilisateur_id` FOREIGN KEY (`utilisateur_id`) REFERENCES `utilisateur` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `adresse`
--

LOCK TABLES `adresse` WRITE;
/*!40000 ALTER TABLE `adresse` DISABLE KEYS */;
INSERT INTO `adresse` VALUES (18,8,'Fustel de Coulanges','Paris',75005,42),(20,12,'Fustel de Coulanges','Paris',75005,44);
/*!40000 ALTER TABLE `adresse` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `article`
--

DROP TABLE IF EXISTS `article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `article` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `produit_article_id` int(5) NOT NULL,
  `quantite_produit_article` int(2) NOT NULL,
  `utilisateur_article_id` int(5) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `produit_article_id_idx` (`produit_article_id`),
  KEY `utilisateur_article_id_idx` (`utilisateur_article_id`),
  CONSTRAINT `produit_article_id` FOREIGN KEY (`produit_article_id`) REFERENCES `produit` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `utilisateur_article_id` FOREIGN KEY (`utilisateur_article_id`) REFERENCES `utilisateur` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article`
--

LOCK TABLES `article` WRITE;
/*!40000 ALTER TABLE `article` DISABLE KEYS */;
/*!40000 ALTER TABLE `article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `carte_paiement`
--

DROP TABLE IF EXISTS `carte_paiement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carte_paiement` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `date_validite` varchar(45) NOT NULL,
  `numero_carte` varchar(32) NOT NULL,
  `cryptogramme` varchar(3) NOT NULL,
  `utilisateur_carte_paiement_id` int(5) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `utilisateur_carte_paiement_id_idx` (`utilisateur_carte_paiement_id`),
  CONSTRAINT `utilisateur_carte_paiement_id` FOREIGN KEY (`utilisateur_carte_paiement_id`) REFERENCES `utilisateur` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carte_paiement`
--

LOCK TABLES `carte_paiement` WRITE;
/*!40000 ALTER TABLE `carte_paiement` DISABLE KEYS */;
INSERT INTO `carte_paiement` VALUES (5,'06/06/2020','12345678912345678','123',42);
/*!40000 ALTER TABLE `carte_paiement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `commande`
--

DROP TABLE IF EXISTS `commande`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `commande` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date_creation` varchar(45) NOT NULL,
  `date_livraison` varchar(45) NOT NULL,
  `prix_total` float NOT NULL,
  `utilisateur_commande_id` int(5) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `utilisateur_commande_id_idx` (`utilisateur_commande_id`),
  CONSTRAINT `utilisateur_commande_id` FOREIGN KEY (`utilisateur_commande_id`) REFERENCES `utilisateur` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `commande`
--

LOCK TABLES `commande` WRITE;
/*!40000 ALTER TABLE `commande` DISABLE KEYS */;
/*!40000 ALTER TABLE `commande` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ligne_commande`
--

DROP TABLE IF EXISTS `ligne_commande`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ligne_commande` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `produit_ligne_commande_id` int(5) NOT NULL,
  `quantite_produit` int(2) NOT NULL,
  `comande_ligne_commande_id` int(5) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `produit_ligne_commande_id_idx` (`produit_ligne_commande_id`),
  KEY `comande_ligne_commande_id_idx` (`comande_ligne_commande_id`),
  CONSTRAINT `comande_ligne_commande_id` FOREIGN KEY (`comande_ligne_commande_id`) REFERENCES `commande` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `produit_ligne_commande_id` FOREIGN KEY (`produit_ligne_commande_id`) REFERENCES `produit` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ligne_commande`
--

LOCK TABLES `ligne_commande` WRITE;
/*!40000 ALTER TABLE `ligne_commande` DISABLE KEYS */;
/*!40000 ALTER TABLE `ligne_commande` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produit`
--

DROP TABLE IF EXISTS `produit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `produit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(45) NOT NULL,
  `description` varchar(120) NOT NULL,
  `prix` float NOT NULL,
  `remise` int(2) DEFAULT '0',
  `categorie` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produit`
--

LOCK TABLES `produit` WRITE;
/*!40000 ALTER TABLE `produit` DISABLE KEYS */;
/*!40000 ALTER TABLE `produit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `utilisateur` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `nom` varchar(45) NOT NULL,
  `prenom` varchar(45) NOT NULL,
  `age` int(3) NOT NULL,
  `email` varchar(45) NOT NULL,
  `telephone` varchar(45) NOT NULL,
  `password` tinyblob NOT NULL,
  `role` varchar(45) NOT NULL,
  `cle` mediumblob NOT NULL,
  `is_active` tinyint(4) NOT NULL,
  `is_online` tinyint(4) NOT NULL,
  `photo_profil` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utilisateur`
--

LOCK TABLES `utilisateur` WRITE;
/*!40000 ALTER TABLE `utilisateur` DISABLE KEYS */;
INSERT INTO `utilisateur` VALUES (42,'Ch√©rif','BADAD',29,'chebadad@lilo.org','0658854621',_binary 'Jü<j\—Fìe','Client',_binary '¨\Ì\0sr\0java.security.KeyRepΩ˘O≥àö•C\0L\0	algorithmt\0Ljava/lang/String;[\0encodedt\0[BL\0formatq\0~\0L\0typet\0Ljava/security/KeyRep$Type;xpt\0DESur\0[B¨Û¯T\‡\0\0xp\0\0\0,»Öß˛It\0RAW~r\0java.security.KeyRep$Type\0\0\0\0\0\0\0\0\0\0xr\0java.lang.Enum\0\0\0\0\0\0\0\0\0\0xpt\0SECRET',1,0,NULL),(44,'Jeanne','Lamour',12,'jeanne','0781220459',_binary 'ié†ãù\"\ ','Client',_binary '¨\Ì\0sr\0java.security.KeyRepΩ˘O≥àö•C\0L\0	algorithmt\0Ljava/lang/String;[\0encodedt\0[BL\0formatq\0~\0L\0typet\0Ljava/security/KeyRep$Type;xpt\0DESur\0[B¨Û¯T\‡\0\0xp\0\0\0¯Äy\È\„ÆIdt\0RAW~r\0java.security.KeyRep$Type\0\0\0\0\0\0\0\0\0\0xr\0java.lang.Enum\0\0\0\0\0\0\0\0\0\0xpt\0SECRET',1,0,'C:\\Users\\Boule\\AppData\\LocalLow\\E-boutique\\UserPictures\\20190309175020869_0001.jpg');
/*!40000 ALTER TABLE `utilisateur` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'e_boutique_db'
--

--
-- Dumping routines for database 'e_boutique_db'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-11 12:18:00
