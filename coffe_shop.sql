-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3307
-- Generation Time: Dec 27, 2024 at 10:51 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.1.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `coffe_shop`
--

-- --------------------------------------------------------

--
-- Table structure for table `detailmenu`
--

CREATE TABLE `detailmenu` (
  `idDetMenu` int(20) NOT NULL,
  `namaMenu` varchar(50) NOT NULL,
  `harga` int(20) NOT NULL,
  `jumlah` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `detailmenu`
--

INSERT INTO `detailmenu` (`idDetMenu`, `namaMenu`, `harga`, `jumlah`) VALUES
(1, 'Expresso', 27000, 96),
(2, 'Latte', 27000, 199),
(3, 'Cappuccino', 28000, 110),
(4, 'Americano', 23000, 90);

-- --------------------------------------------------------

--
-- Table structure for table `laporantransaksi`
--

CREATE TABLE `laporantransaksi` (
  `idDetail` int(20) NOT NULL,
  `idMenu` int(20) NOT NULL,
  `namaMenu` varchar(50) NOT NULL,
  `harga` int(20) NOT NULL,
  `jumlah` int(10) NOT NULL,
  `totalHarga` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `laporantransaksi`
--

INSERT INTO `laporantransaksi` (`idDetail`, `idMenu`, `namaMenu`, `harga`, `jumlah`, `totalHarga`) VALUES
(1, 1, 'Expresso', 20000, 3, 60000),
(2, 2, 'Latte', 25000, 2, 50000),
(3, 3, 'Cappuccino', 25000, 3, 75000),
(4, 1, 'Expresso', 20000, 1, 20000),
(5, 2, 'Cappuccino', 25000, 3, 75000),
(6, 1, 'Expresso', 20000, 3, 60000),
(7, 2, 'Cappuccino', 25000, 1, 25000),
(8, 1, 'Expresso', 20000, 2, 40000),
(9, 1, 'Expresso', 20000, 2, 40000),
(10, 1, 'Latte', 27000, 1, 27000),
(11, 1, 'Expresso', 27000, 2, 54000),
(12, 1, 'Expresso', 27000, 2, 54000);

-- --------------------------------------------------------

--
-- Table structure for table `menu`
--

CREATE TABLE `menu` (
  `idMenu` int(20) NOT NULL,
  `namaMenu` varchar(50) NOT NULL,
  `harga` int(20) DEFAULT NULL,
  `jumlah` int(10) NOT NULL,
  `totalHarga` int(20) NOT NULL,
  `idDetMenu` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `pengguna`
--

CREATE TABLE `pengguna` (
  `id` int(11) NOT NULL,
  `namaPengguna` varchar(50) NOT NULL,
  `kataSandi` varchar(50) NOT NULL,
  `role` enum('admin','pelanggan') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pengguna`
--

INSERT INTO `pengguna` (`id`, `namaPengguna`, `kataSandi`, `role`) VALUES
(1, 'admin', 'admin123', 'admin'),
(2, 'awa', 'awa123', 'pelanggan'),
(3, 'AkmalZain', 'akmal123', 'pelanggan');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `detailmenu`
--
ALTER TABLE `detailmenu`
  ADD PRIMARY KEY (`idDetMenu`);

--
-- Indexes for table `laporantransaksi`
--
ALTER TABLE `laporantransaksi`
  ADD PRIMARY KEY (`idDetail`),
  ADD KEY `idMenu` (`idMenu`);

--
-- Indexes for table `menu`
--
ALTER TABLE `menu`
  ADD PRIMARY KEY (`idMenu`),
  ADD KEY `fk_detailMenu` (`idDetMenu`);

--
-- Indexes for table `pengguna`
--
ALTER TABLE `pengguna`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `namaPengguna` (`namaPengguna`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `detailmenu`
--
ALTER TABLE `detailmenu`
  MODIFY `idDetMenu` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `laporantransaksi`
--
ALTER TABLE `laporantransaksi`
  MODIFY `idDetail` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `menu`
--
ALTER TABLE `menu`
  MODIFY `idMenu` int(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `pengguna`
--
ALTER TABLE `pengguna`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `laporantransaksi`
--
ALTER TABLE `laporantransaksi`
  ADD CONSTRAINT `laporantransaksi_ibfk_1` FOREIGN KEY (`idMenu`) REFERENCES `menu` (`idMenu`);

--
-- Constraints for table `menu`
--
ALTER TABLE `menu`
  ADD CONSTRAINT `fk_detailMenu` FOREIGN KEY (`idDetMenu`) REFERENCES `detailmenu` (`idDetMenu`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
