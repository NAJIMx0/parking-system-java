-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Jan 15, 2026 at 09:32 AM
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
) ENGINE=MyISAM AUTO_INCREMENT=152 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `car`
--

INSERT INTO `car` (`idCar`, `plateNumber`, `color`) VALUES
(90, 'PARK-STRESS-030', 'Color30'),
(89, 'PARK-STRESS-029', 'Color29'),
(88, 'PARK-STRESS-027', 'Color27'),
(87, 'PARK-STRESS-028', 'Color28'),
(86, 'PARK-STRESS-025', 'Color25'),
(85, 'PARK-STRESS-026', 'Color26'),
(84, 'PARK-STRESS-023', 'Color23'),
(83, 'PARK-STRESS-024', 'Color24'),
(82, 'PARK-STRESS-022', 'Color22'),
(81, 'PARK-STRESS-021', 'Color21'),
(80, 'PARK-STRESS-020', 'Color20'),
(79, 'PARK-STRESS-019', 'Color19'),
(78, 'PARK-STRESS-018', 'Color18'),
(77, 'PARK-STRESS-017', 'Color17'),
(76, 'PARK-STRESS-016', 'Color16'),
(75, 'PARK-STRESS-015', 'Color15'),
(74, 'PARK-STRESS-013', 'Color13'),
(73, 'PARK-STRESS-014', 'Color14'),
(72, 'PARK-STRESS-012', 'Color12'),
(71, 'PARK-STRESS-011', 'Color11'),
(70, 'PARK-STRESS-009', 'Color9'),
(69, 'PARK-STRESS-010', 'Color10'),
(68, 'PARK-STRESS-008', 'Color8'),
(67, 'PARK-STRESS-007', 'Color7'),
(66, 'PARK-STRESS-006', 'Color6'),
(65, 'PARK-STRESS-004', 'Color4'),
(64, 'PARK-STRESS-003', 'Color3'),
(63, 'PARK-STRESS-005', 'Color5'),
(62, 'PARK-STRESS-001', 'Color1'),
(61, 'PARK-STRESS-002', 'Color2'),
(91, 'PARK-STRESS-031', 'Color31'),
(92, 'PARK-STRESS-032', 'Color32'),
(93, 'PARK-STRESS-034', 'Color34'),
(94, 'PARK-STRESS-033', 'Color33'),
(95, 'PARK-STRESS-035', 'Color35'),
(96, 'PARK-STRESS-036', 'Color36'),
(97, 'PARK-STRESS-037', 'Color37'),
(98, 'PARK-STRESS-038', 'Color38'),
(99, 'PARK-STRESS-039', 'Color39'),
(100, 'PARK-STRESS-040', 'Color40'),
(101, 'PARK-STRESS-042', 'Color42'),
(102, 'PARK-STRESS-041', 'Color41'),
(103, 'PARK-STRESS-043', 'Color43'),
(104, 'PARK-STRESS-044', 'Color44'),
(105, 'PARK-STRESS-046', 'Color46'),
(106, 'PARK-STRESS-045', 'Color45'),
(107, 'PARK-STRESS-047', 'Color47'),
(108, 'PARK-STRESS-048', 'Color48'),
(109, 'PARK-STRESS-049', 'Color49'),
(110, 'PARK-STRESS-050', 'Color50'),
(111, 'PARK-STRESS-051', 'Color51'),
(112, 'PARK-STRESS-052', 'Color52'),
(113, 'PARK-STRESS-053', 'Color53'),
(114, 'PARK-STRESS-054', 'Color54'),
(115, 'PARK-STRESS-055', 'Color55'),
(116, 'PARK-STRESS-056', 'Color56'),
(117, 'PARK-STRESS-057', 'Color57'),
(118, 'PARK-STRESS-058', 'Color58'),
(119, 'PARK-STRESS-059', 'Color59'),
(120, 'PARK-STRESS-060', 'Color60'),
(121, 'PARK-STRESS-062', 'Color62'),
(122, 'PARK-STRESS-061', 'Color61'),
(123, 'PARK-STRESS-063', 'Color63'),
(124, 'PARK-STRESS-064', 'Color64'),
(125, 'PARK-STRESS-065', 'Color65'),
(126, 'PARK-STRESS-066', 'Color66'),
(127, 'PARK-STRESS-067', 'Color67'),
(128, 'PARK-STRESS-068', 'Color68'),
(129, 'PARK-STRESS-069', 'Color69'),
(130, 'PARK-STRESS-070', 'Color70'),
(131, 'PARK-STRESS-071', 'Color71'),
(132, 'PARK-STRESS-072', 'Color72'),
(133, 'PARK-STRESS-073', 'Color73'),
(134, 'PARK-STRESS-074', 'Color74'),
(135, 'PARK-STRESS-075', 'Color75'),
(136, 'PARK-STRESS-076', 'Color76'),
(137, 'PARK-STRESS-077', 'Color77'),
(138, 'abss23', 'blue'),
(139, 'abs21', 'red'),
(140, 'ENTRY-001', 'Color1'),
(141, 'ENTRY-002', 'Color2'),
(142, 'ENTRY-005', 'Color5'),
(143, 'ENTRY-003', 'Color3'),
(144, 'ENTRY-004', 'Color4'),
(145, 'ENTRY-006', 'Color6'),
(146, 'ENTRY-008', 'Color8'),
(147, 'ENTRY-007', 'Color7'),
(148, 'ENTRY-009', 'Color9'),
(149, 'ENTRY-010', 'Color10'),
(150, '44A', 'Red_White'),
(151, 'abs34', 'red');

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
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `floor`
--

INSERT INTO `floor` (`idFloor`, `floorNumber`, `idParking`) VALUES
(1, 1, 1),
(2, 2, 1);

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
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `parking`
--

INSERT INTO `parking` (`idParking`, `name`, `location`) VALUES
(1, 'Central Parking', 'Downtown Area');

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
) ENGINE=MyISAM AUTO_INCREMENT=78 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `payment`
--

INSERT INTO `payment` (`idPayment`, `amount`, `paymentTime`, `paymentMethod`, `idTicket`) VALUES
(1, 1.00, '2026-01-14 21:26:59', 'CARD', 4),
(2, 0.00, '2026-01-14 21:26:59', 'CASH', 15),
(3, 1.00, '2026-01-14 21:27:00', 'CARD', 2),
(4, 1.00, '2026-01-14 21:27:00', 'CASH', 5),
(5, 0.00, '2026-01-14 21:27:00', 'CARD', 10),
(6, 1.00, '2026-01-14 21:27:00', 'CASH', 3),
(7, 0.00, '2026-01-14 21:27:00', 'CASH', 11),
(8, 0.00, '2026-01-14 21:27:00', 'CARD', 12),
(9, 1.00, '2026-01-14 21:27:01', 'CARD', 6),
(10, 0.00, '2026-01-14 21:27:01', 'CARD', 14),
(11, 1.00, '2026-01-14 21:27:01', 'CASH', 7),
(12, 1.00, '2026-01-14 21:27:01', 'CARD', 8),
(13, 0.00, '2026-01-14 21:27:01', 'CASH', 9),
(14, 1.00, '2026-01-14 21:27:01', 'CASH', 1),
(15, 0.00, '2026-01-14 21:27:01', 'CASH', 13),
(16, 0.00, '2026-01-14 21:27:02', 'CARD', 16),
(17, 0.00, '2026-01-14 21:27:02', 'CASH', 17),
(18, 0.00, '2026-01-14 21:27:03', 'CARD', 18),
(19, 0.00, '2026-01-14 21:27:04', 'CASH', 19),
(20, 0.00, '2026-01-14 21:27:05', 'CARD', 20),
(21, 0.00, '2026-01-14 21:27:06', 'CASH', 21),
(22, 0.00, '2026-01-14 21:27:07', 'CARD', 22),
(23, 0.00, '2026-01-14 21:27:08', 'CASH', 23),
(24, 0.00, '2026-01-14 21:27:09', 'CARD', 24),
(25, 0.00, '2026-01-14 21:27:10', 'CASH', 25),
(26, 0.00, '2026-01-14 21:27:11', 'CARD', 26),
(27, 5.00, '2026-01-15 00:54:07', 'CARD', 2),
(28, 5.00, '2026-01-15 00:54:07', 'CASH', 3),
(29, 5.00, '2026-01-15 00:54:07', 'CASH', 1),
(30, 5.00, '2026-01-15 00:54:09', 'CARD', 4),
(31, 5.00, '2026-01-15 00:54:09', 'CASH', 5),
(32, 1.00, '2026-01-15 09:36:21', 'CASH', 72),
(33, 1.00, '2026-01-15 10:02:51', 'CARD', 77),
(34, 20.00, '2026-01-15 10:28:42', 'CASH', 8),
(35, 20.00, '2026-01-15 10:28:43', 'CARD', 9),
(36, 20.00, '2026-01-15 10:28:43', 'CASH', 3),
(37, 20.00, '2026-01-15 10:28:43', 'CASH', 27),
(38, 20.00, '2026-01-15 10:28:43', 'CARD', 4),
(39, 20.00, '2026-01-15 10:28:43', 'CARD', 15),
(40, 20.00, '2026-01-15 10:28:43', 'CASH', 1),
(41, 20.00, '2026-01-15 10:28:43', 'CARD', 30),
(42, 20.00, '2026-01-15 10:28:44', 'CARD', 7),
(43, 20.00, '2026-01-15 10:28:44', 'CASH', 29),
(44, 20.00, '2026-01-15 10:28:44', 'CARD', 28),
(45, 20.00, '2026-01-15 10:28:44', 'CASH', 31),
(46, 20.00, '2026-01-15 10:28:44', 'CASH', 12),
(47, 20.00, '2026-01-15 10:28:44', 'CARD', 2),
(48, 20.00, '2026-01-15 10:28:44', 'CASH', 6),
(49, 20.00, '2026-01-15 10:28:45', 'CARD', 32),
(50, 20.00, '2026-01-15 10:28:45', 'CASH', 33),
(51, 20.00, '2026-01-15 10:28:47', 'CARD', 34),
(52, 20.00, '2026-01-15 10:28:47', 'CASH', 35),
(53, 20.00, '2026-01-15 10:28:49', 'CARD', 36),
(54, 20.00, '2026-01-15 10:28:49', 'CASH', 37),
(55, 20.00, '2026-01-15 10:28:51', 'CARD', 38),
(56, 20.00, '2026-01-15 10:28:51', 'CASH', 39),
(57, 20.00, '2026-01-15 10:28:53', 'CARD', 40),
(58, 20.00, '2026-01-15 10:28:53', 'CASH', 41),
(59, 20.00, '2026-01-15 10:28:55', 'CARD', 42),
(60, 20.00, '2026-01-15 10:28:55', 'CASH', 43),
(61, 20.00, '2026-01-15 10:28:57', 'CARD', 44),
(62, 20.00, '2026-01-15 10:28:57', 'CASH', 45),
(63, 20.00, '2026-01-15 10:28:59', 'CARD', 46),
(64, 20.00, '2026-01-15 10:28:59', 'CASH', 47),
(65, 20.00, '2026-01-15 10:29:01', 'CARD', 48),
(66, 20.00, '2026-01-15 10:29:01', 'CASH', 49),
(67, 20.00, '2026-01-15 10:29:03', 'CARD', 50),
(68, 20.00, '2026-01-15 10:29:03', 'CASH', 51),
(69, 20.00, '2026-01-15 10:29:05', 'CARD', 52),
(70, 20.00, '2026-01-15 10:29:05', 'CASH', 53),
(71, 20.00, '2026-01-15 10:29:07', 'CARD', 54),
(72, 20.00, '2026-01-15 10:29:07', 'CASH', 55),
(73, 20.00, '2026-01-15 10:29:09', 'CARD', 56),
(74, 20.00, '2026-01-15 10:29:09', 'CASH', 57),
(75, 20.00, '2026-01-15 10:29:11', 'CARD', 58),
(76, 20.00, '2026-01-15 10:29:11', 'CASH', 59),
(77, 20.00, '2026-01-15 10:29:13', 'CARD', 60);

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
) ENGINE=MyISAM AUTO_INCREMENT=61 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `spot`
--

INSERT INTO `spot` (`idSpot`, `spotNumber`, `status`, `type`, `idFloor`) VALUES
(1, '1A01', 'FREE', 'REGULAR', 1),
(2, '1A02', 'FREE', 'REGULAR', 1),
(3, '1A03', 'FREE', 'REGULAR', 1),
(4, '1A04', 'FREE', 'REGULAR', 1),
(5, '1A05', 'FREE', 'REGULAR', 1),
(6, '1A06', 'FREE', 'REGULAR', 1),
(7, '1A07', 'FREE', 'REGULAR', 1),
(8, '1A08', 'FREE', 'REGULAR', 1),
(9, '1A09', 'FREE', 'REGULAR', 1),
(10, '1A10', 'FREE', 'REGULAR', 1),
(11, '1A11', 'FREE', 'REGULAR', 1),
(12, '1A12', 'FREE', 'REGULAR', 1),
(13, '1A13', 'FREE', 'REGULAR', 1),
(14, '1A14', 'FREE', 'REGULAR', 1),
(15, '1A15', 'FREE', 'REGULAR', 1),
(16, '1A16', 'FREE', 'REGULAR', 1),
(17, '1A17', 'FREE', 'REGULAR', 1),
(18, '1A18', 'FREE', 'REGULAR', 1),
(19, '1A19', 'FREE', 'REGULAR', 1),
(20, '1A20', 'FREE', 'REGULAR', 1),
(21, '1A21', 'FREE', 'REGULAR', 1),
(22, '1A22', 'FREE', 'REGULAR', 1),
(23, '1A23', 'FREE', 'REGULAR', 1),
(24, '1A24', 'FREE', 'REGULAR', 1),
(25, '1A25', 'FREE', 'REGULAR', 1),
(26, '1V01', 'FREE', 'VIP', 1),
(27, '1V02', 'FREE', 'VIP', 1),
(28, '1V03', 'FREE', 'VIP', 1),
(29, '1V04', 'FREE', 'VIP', 1),
(30, '1V05', 'FREE', 'VIP', 1),
(31, '2A01', 'FREE', 'REGULAR', 2),
(32, '2A02', 'FREE', 'REGULAR', 2),
(33, '2A03', 'FREE', 'REGULAR', 2),
(34, '2A04', 'FREE', 'REGULAR', 2),
(35, '2A05', 'FREE', 'REGULAR', 2),
(36, '2A06', 'FREE', 'REGULAR', 2),
(37, '2A07', 'FREE', 'REGULAR', 2),
(38, '2A08', 'FREE', 'REGULAR', 2),
(39, '2A09', 'FREE', 'REGULAR', 2),
(40, '2A10', 'FREE', 'REGULAR', 2),
(41, '2A11', 'FREE', 'REGULAR', 2),
(42, '2A12', 'FREE', 'REGULAR', 2),
(43, '2A13', 'FREE', 'REGULAR', 2),
(44, '2A14', 'FREE', 'REGULAR', 2),
(45, '2A15', 'FREE', 'REGULAR', 2),
(46, '2A16', 'FREE', 'REGULAR', 2),
(47, '2A17', 'FREE', 'REGULAR', 2),
(48, '2A18', 'FREE', 'REGULAR', 2),
(49, '2A19', 'FREE', 'REGULAR', 2),
(50, '2A20', 'FREE', 'REGULAR', 2),
(51, '2A21', 'FREE', 'REGULAR', 2),
(52, '2A22', 'FREE', 'REGULAR', 2),
(53, '2A23', 'FREE', 'REGULAR', 2),
(54, '2A24', 'FREE', 'REGULAR', 2),
(55, '2A25', 'FREE', 'REGULAR', 2),
(56, '2V01', 'FREE', 'VIP', 2),
(57, '2V02', 'FREE', 'VIP', 2),
(58, '2V03', 'FREE', 'VIP', 2),
(59, '2V04', 'FREE', 'VIP', 2),
(60, '2V05', 'FREE', 'VIP', 2);

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
) ENGINE=MyISAM AUTO_INCREMENT=78 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `ticket`
--

INSERT INTO `ticket` (`idTicket`, `entryTime`, `spotType`, `idCar`, `idSpot`) VALUES
(1, '2026-01-14 21:25:54', 'REGULAR', 61, 1),
(2, '2026-01-14 21:25:55', 'REGULAR', 62, 2),
(3, '2026-01-14 21:25:56', 'REGULAR', 65, 3),
(4, '2026-01-14 21:25:56', 'REGULAR', 64, 4),
(5, '2026-01-14 21:25:58', 'REGULAR', 67, 5),
(6, '2026-01-14 21:25:58', 'VIP', 69, 26),
(7, '2026-01-14 21:26:00', 'REGULAR', 66, 6),
(8, '2026-01-14 21:26:00', 'REGULAR', 68, 7),
(9, '2026-01-14 21:26:02', 'REGULAR', 74, 8),
(10, '2026-01-14 21:26:02', 'REGULAR', 71, 9),
(11, '2026-01-14 21:26:04', 'REGULAR', 73, 10),
(12, '2026-01-14 21:26:04', 'VIP', 63, 27),
(13, '2026-01-14 21:26:06', 'REGULAR', 70, 11),
(14, '2026-01-14 21:26:06', 'REGULAR', 72, 12),
(15, '2026-01-14 21:26:08', 'VIP', 75, 28),
(16, '2026-01-14 21:26:08', 'REGULAR', 76, 13),
(17, '2026-01-14 21:26:10', 'REGULAR', 78, 14),
(18, '2026-01-14 21:26:10', 'REGULAR', 77, 15),
(19, '2026-01-14 21:26:12', 'REGULAR', 79, 16),
(20, '2026-01-14 21:26:12', 'VIP', 80, 29),
(21, '2026-01-14 21:26:14', 'REGULAR', 81, 17),
(22, '2026-01-14 21:26:14', 'REGULAR', 82, 18),
(23, '2026-01-14 21:26:16', 'REGULAR', 84, 19),
(24, '2026-01-14 21:26:16', 'REGULAR', 83, 20),
(25, '2026-01-14 21:26:18', 'VIP', 86, 30),
(26, '2026-01-14 21:26:18', 'REGULAR', 85, 21),
(27, '2026-01-14 21:26:20', 'REGULAR', 88, 22),
(28, '2026-01-14 21:26:20', 'REGULAR', 87, 23),
(29, '2026-01-14 21:26:22', 'REGULAR', 89, 24),
(30, '2026-01-14 21:26:22', 'VIP', 90, 56),
(31, '2026-01-14 21:26:24', 'REGULAR', 91, 25),
(32, '2026-01-14 21:26:24', 'REGULAR', 92, 31),
(33, '2026-01-14 21:26:26', 'REGULAR', 94, 32),
(34, '2026-01-14 21:26:26', 'REGULAR', 93, 33),
(35, '2026-01-14 21:26:28', 'VIP', 95, 57),
(36, '2026-01-14 21:26:28', 'REGULAR', 96, 34),
(37, '2026-01-14 21:26:30', 'REGULAR', 98, 35),
(38, '2026-01-14 21:26:30', 'REGULAR', 97, 36),
(39, '2026-01-14 21:26:32', 'REGULAR', 99, 37),
(40, '2026-01-14 21:26:32', 'VIP', 100, 58),
(41, '2026-01-14 21:26:34', 'REGULAR', 102, 38),
(42, '2026-01-14 21:26:34', 'REGULAR', 101, 39),
(43, '2026-01-14 21:26:36', 'REGULAR', 103, 40),
(44, '2026-01-14 21:26:36', 'REGULAR', 104, 41),
(45, '2026-01-14 21:26:38', 'VIP', 106, 59),
(46, '2026-01-14 21:26:38', 'REGULAR', 105, 42),
(47, '2026-01-14 21:26:40', 'REGULAR', 107, 43),
(48, '2026-01-14 21:26:40', 'REGULAR', 108, 44),
(49, '2026-01-14 21:26:42', 'REGULAR', 109, 45),
(50, '2026-01-14 21:26:42', 'VIP', 110, 60),
(51, '2026-01-14 21:26:44', 'REGULAR', 111, 46),
(52, '2026-01-14 21:26:44', 'REGULAR', 112, 47),
(53, '2026-01-14 21:26:46', 'REGULAR', 113, 48),
(54, '2026-01-14 21:26:46', 'REGULAR', 114, 49),
(55, '2026-01-14 21:26:48', 'REGULAR', 116, 50),
(56, '2026-01-14 21:26:50', 'REGULAR', 117, 51),
(57, '2026-01-14 21:26:50', 'REGULAR', 118, 52),
(58, '2026-01-14 21:26:52', 'REGULAR', 119, 53),
(59, '2026-01-14 21:26:54', 'REGULAR', 122, 54),
(60, '2026-01-14 21:26:54', 'REGULAR', 121, 55),
(61, '2026-01-14 21:29:27', 'REGULAR', 139, 1),
(62, '2026-01-15 00:53:35', 'REGULAR', 141, 2),
(63, '2026-01-15 00:53:35', 'REGULAR', 140, 3),
(64, '2026-01-15 00:53:37', 'REGULAR', 142, 4),
(65, '2026-01-15 00:53:37', 'VIP', 143, 26),
(66, '2026-01-15 00:53:39', 'REGULAR', 144, 5),
(67, '2026-01-15 00:53:39', 'VIP', 145, 27),
(68, '2026-01-15 00:53:41', 'REGULAR', 146, 6),
(69, '2026-01-15 00:53:41', 'REGULAR', 147, 7),
(70, '2026-01-15 00:53:43', 'VIP', 148, 28),
(71, '2026-01-15 00:53:43', 'REGULAR', 149, 8),
(72, '2026-01-15 09:34:58', 'VIP', 150, 29),
(73, '2026-01-15 09:37:20', 'REGULAR', 140, 1),
(74, '2026-01-15 09:37:20', 'REGULAR', 141, 2),
(75, '2026-01-15 09:37:22', 'REGULAR', 144, 3),
(76, '2026-01-15 09:37:24', 'REGULAR', 142, 4),
(77, '2026-01-15 09:42:00', 'REGULAR', 151, 5);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
