-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Dec 31, 2025 at 10:07 AM
-- Server version: 8.3.0
-- PHP Version: 8.2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `parking_system`
--

-- --------------------------------------------------------

--
-- Table structure for table `car`
--

DROP TABLE IF EXISTS `car`;
CREATE TABLE IF NOT EXISTS `car` (
  `idCar` int NOT NULL AUTO_INCREMENT,
  `plateNumber` varchar(20) DEFAULT NULL,
  `color` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idCar`),
  UNIQUE KEY `plateNumber` (`plateNumber`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `car`
--

INSERT INTO `car` (`idCar`, `plateNumber`, `color`) VALUES
(2, 'ABC-124', 'Red'),
(3, 'ABC-127', 'Red'),
(5, 'ABC-199', 'Red'),
(6, 'BBB-199', 'Red'),
(7, 'BBBP-199', 'Red');

-- --------------------------------------------------------

--
-- Table structure for table `floor`
--

DROP TABLE IF EXISTS `floor`;
CREATE TABLE IF NOT EXISTS `floor` (
  `idFloor` int NOT NULL AUTO_INCREMENT,
  `floorNumber` int DEFAULT NULL,
  `idParking` int DEFAULT NULL,
  PRIMARY KEY (`idFloor`),
  KEY `idParking` (`idParking`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `parking`
--

DROP TABLE IF EXISTS `parking`;
CREATE TABLE IF NOT EXISTS `parking` (
  `idParking` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idParking`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
CREATE TABLE IF NOT EXISTS `payment` (
  `idPayment` int NOT NULL AUTO_INCREMENT,
  `amount` decimal(10,2) DEFAULT NULL,
  `paymentTime` datetime DEFAULT NULL,
  `paymentMethod` enum('CASH','CARD') DEFAULT NULL,
  `idTicket` int DEFAULT NULL,
  PRIMARY KEY (`idPayment`),
  KEY `idTicket` (`idTicket`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `spot`
--

DROP TABLE IF EXISTS `spot`;
CREATE TABLE IF NOT EXISTS `spot` (
  `idSpot` int NOT NULL AUTO_INCREMENT,
  `spotNumber` varchar(20) DEFAULT NULL,
  `status` enum('FREE','OCCUPIED') DEFAULT 'FREE',
  `type` enum('VIP','REGULAR') NOT NULL,
  `idFloor` int DEFAULT NULL,
  PRIMARY KEY (`idSpot`),
  KEY `idFloor` (`idFloor`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `ticket`
--

DROP TABLE IF EXISTS `ticket`;
CREATE TABLE IF NOT EXISTS `ticket` (
  `idTicket` int NOT NULL AUTO_INCREMENT,
  `entryTime` datetime NOT NULL,
  `spotType` enum('VIP','REGULAR') NOT NULL,
  `idCar` int DEFAULT NULL,
  `idSpot` int DEFAULT NULL,
  PRIMARY KEY (`idTicket`),
  KEY `idCar` (`idCar`),
  KEY `idSpot` (`idSpot`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;