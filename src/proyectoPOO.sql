-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Nov 15, 2022 at 08:24 PM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `proyectoPOO`
--
CREATE DATABASE IF NOT EXISTS `proyectoPOO` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `proyectoPOO`;

-- --------------------------------------------------------

--
-- Table structure for table `flashcard`
--

CREATE TABLE `flashcard` (
  `nombrePropietario` varchar(50) NOT NULL,
  `nombreLista` varchar(50) NOT NULL,
  `lado1` varchar(50) NOT NULL,
  `lado2` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `Libro`
--

CREATE TABLE `Libro` (
  `nombre` varchar(50) NOT NULL,
  `tema` varchar(50) NOT NULL,
  `paginas` int(11) NOT NULL,
  `idioma` varchar(50) NOT NULL,
  `nombrePropietario` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `listaFlashcards`
--

CREATE TABLE `listaFlashcards` (
  `tema` varchar(50) NOT NULL,
  `nombrePropietario` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `persona`
--

CREATE TABLE `persona` (
  `nombre` varchar(50) NOT NULL,
  `metas` varchar(1000) NOT NULL,
  `carnet` varchar(50) NOT NULL,
  `contrasenia` varchar(50) NOT NULL,
  `listasEstudiadas` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `flashcard`
--
ALTER TABLE `flashcard`
  ADD PRIMARY KEY (`lado1`);

--
-- Indexes for table `Libro`
--
ALTER TABLE `Libro`
  ADD PRIMARY KEY (`nombre`);

--
-- Indexes for table `listaFlashcards`
--
ALTER TABLE `listaFlashcards`
  ADD PRIMARY KEY (`tema`);

--
-- Indexes for table `persona`
--
ALTER TABLE `persona`
  ADD PRIMARY KEY (`nombre`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
