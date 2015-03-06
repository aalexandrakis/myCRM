-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Φιλοξενητής: localhost
-- Χρόνος δημιουργίας: 06 Μαρ 2015 στις 02:01:23
-- Έκδοση διακομιστή: 5.5.41-0ubuntu0.14.04.1
-- Έκδοση PHP: 5.5.9-1ubuntu4.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Βάση: `myCrm`
--
CREATE DATABASE IF NOT EXISTS `myCrm` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
USE `myCrm`;

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `companyInfo`
--

DROP TABLE IF EXISTS `companyInfo`;
CREATE TABLE IF NOT EXISTS `companyInfo` (
  `companyId` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `busDesc` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `address` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `afm` char(9) COLLATE utf8_unicode_ci NOT NULL,
  `doy` char(50) COLLATE utf8_unicode_ci NOT NULL,
  `mobilePhone` char(11) COLLATE utf8_unicode_ci NOT NULL,
  `workPhone` char(11) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`companyId`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='My info' AUTO_INCREMENT=8 ;

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `customers`
--

DROP TABLE IF EXISTS `customers`;
CREATE TABLE IF NOT EXISTS `customers` (
  `customerId` int(11) NOT NULL AUTO_INCREMENT,
  `customerName` char(50) COLLATE utf8_unicode_ci NOT NULL,
  `customerBusDesc` varchar(150) COLLATE utf8_unicode_ci NOT NULL,
  `customerDoy` char(50) COLLATE utf8_unicode_ci NOT NULL,
  `customerAddress` char(50) COLLATE utf8_unicode_ci NOT NULL,
  `customerAfm` char(9) COLLATE utf8_unicode_ci NOT NULL,
  `customerPhone` char(10) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`customerId`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Customers' AUTO_INCREMENT=3 ;

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `invoiceDetails`
--

DROP TABLE IF EXISTS `invoiceDetails`;
CREATE TABLE IF NOT EXISTS `invoiceDetails` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `invoiceId` bigint(20) NOT NULL,
  `description` varchar(300) COLLATE utf8_unicode_ci NOT NULL,
  `net` decimal(8,2) NOT NULL,
  `lineId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `invoiceId` (`invoiceId`,`lineId`),
  KEY `invoiceId_2` (`invoiceId`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Invoice Detail Records' AUTO_INCREMENT=14 ;

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `invoiceHeader`
--

DROP TABLE IF EXISTS `invoiceHeader`;
CREATE TABLE IF NOT EXISTS `invoiceHeader` (
  `invoiceId` bigint(20) NOT NULL AUTO_INCREMENT,
  `companyId` int(11) NOT NULL,
  `customerId` int(11) NOT NULL,
  `amount` decimal(8,2) NOT NULL,
  `fpa` decimal(5,2) NOT NULL,
  `taxis` decimal(8,2) NOT NULL,
  `gross` decimal(8,2) NOT NULL,
  `withHolding` decimal(5,2) NOT NULL,
  `fpaAmount` decimal(8,2) NOT NULL,
  `withHoldingAmount` decimal(8,2) NOT NULL,
  `receivedAmount` decimal(8,2) NOT NULL,
  `invoiceDate` date NOT NULL,
  `withHoldingString` char(200) COLLATE utf8_unicode_ci NOT NULL,
  `words` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`invoiceId`),
  KEY `companyId` (`companyId`,`customerId`),
  KEY `invoiceId` (`invoiceId`),
  KEY `customerId` (`customerId`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Invoices Header Records' AUTO_INCREMENT=32 ;

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `outcomeDetails`
--

DROP TABLE IF EXISTS `outcomeDetails`;
CREATE TABLE IF NOT EXISTS `outcomeDetails` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `outcomeId` bigint(20) NOT NULL,
  `description` varchar(300) COLLATE utf8_unicode_ci NOT NULL,
  `net` decimal(8,2) NOT NULL,
  `lineId` int(11) NOT NULL,
  `fpa` decimal(5,2) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `invoiceId` (`outcomeId`,`lineId`),
  KEY `invoiceId_2` (`outcomeId`),
  KEY `outcomeId` (`outcomeId`),
  KEY `lineId` (`lineId`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Invoice Detail Records' AUTO_INCREMENT=62 ;

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `outcomeHeader`
--

DROP TABLE IF EXISTS `outcomeHeader`;
CREATE TABLE IF NOT EXISTS `outcomeHeader` (
  `outcomeId` bigint(20) NOT NULL AUTO_INCREMENT,
  `companyId` int(11) NOT NULL,
  `supplierId` int(11) NOT NULL,
  `amount` decimal(8,2) NOT NULL,
  `gross` decimal(8,2) NOT NULL,
  `fpaAmount` decimal(8,2) NOT NULL,
  `outcomeDate` date NOT NULL,
  `outcomeNumber` char(15) COLLATE utf8_unicode_ci NOT NULL,
  `outcomeFile` mediumblob NOT NULL,
  `fileType` text COLLATE utf8_unicode_ci NOT NULL,
  `fileName` text COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`outcomeId`),
  KEY `companyId` (`companyId`,`supplierId`),
  KEY `invoiceId` (`outcomeId`),
  KEY `customerId` (`supplierId`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Invoices Header Records' AUTO_INCREMENT=39 ;

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `roles`
--

DROP TABLE IF EXISTS `roles`;
CREATE TABLE IF NOT EXISTS `roles` (
  `j_username` char(20) COLLATE utf8_unicode_ci NOT NULL,
  `role` char(10) COLLATE utf8_unicode_ci NOT NULL,
  KEY `j_username` (`j_username`),
  KEY `j_username_2` (`j_username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `suppliers`
--

DROP TABLE IF EXISTS `suppliers`;
CREATE TABLE IF NOT EXISTS `suppliers` (
  `supplierId` int(11) NOT NULL AUTO_INCREMENT,
  `supplierName` char(50) COLLATE utf8_unicode_ci NOT NULL,
  `supplierBusDesc` varchar(150) COLLATE utf8_unicode_ci NOT NULL,
  `supplierDoy` char(50) COLLATE utf8_unicode_ci NOT NULL,
  `supplierAddress` char(50) COLLATE utf8_unicode_ci NOT NULL,
  `supplierAfm` char(9) COLLATE utf8_unicode_ci NOT NULL,
  `supplierPhone` char(10) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`supplierId`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Customers' AUTO_INCREMENT=8 ;

-- --------------------------------------------------------

--
-- Δομή πίνακα για τον πίνακα `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `j_username` char(20) COLLATE utf8_unicode_ci NOT NULL,
  `userEmail` char(100) COLLATE utf8_unicode_ci NOT NULL,
  `j_password` char(150) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`j_username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Περιορισμοί για άχρηστους πίνακες
--

--
-- Περιορισμοί για πίνακα `invoiceDetails`
--
ALTER TABLE `invoiceDetails`
  ADD CONSTRAINT `invoiceDetails_ibfk_1` FOREIGN KEY (`invoiceId`) REFERENCES `invoiceHeader` (`invoiceId`);

--
-- Περιορισμοί για πίνακα `outcomeDetails`
--
ALTER TABLE `outcomeDetails`
  ADD CONSTRAINT `outcomeDetails_ibfk_1` FOREIGN KEY (`outcomeId`) REFERENCES `outcomeHeader` (`outcomeId`);

--
-- Περιορισμοί για πίνακα `roles`
--
ALTER TABLE `roles`
  ADD CONSTRAINT `roles_ibfk_1` FOREIGN KEY (`j_username`) REFERENCES `users` (`j_username`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
