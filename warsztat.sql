-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Cze 15, 2025 at 09:23 PM
-- Wersja serwera: 10.4.32-MariaDB
-- Wersja PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `warsztat`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `body_types`
--

CREATE TABLE `body_types` (
  `body_type_id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `body_types`
--

INSERT INTO `body_types` (`body_type_id`, `name`) VALUES
(4, 'Coupe'),
(7, 'Crossover'),
(12, 'Fastback'),
(3, 'Hatchback'),
(5, 'Kabriolet'),
(2, 'Kombi'),
(11, 'Liftback'),
(10, 'Minivan'),
(9, 'Pick-up'),
(1, 'Sedan'),
(6, 'SUV'),
(8, 'Van');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `brands`
--

CREATE TABLE `brands` (
  `brand_id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `brands`
--

INSERT INTO `brands` (`brand_id`, `name`) VALUES
(1, 'Alfa Romeo'),
(2, 'Aston Martin'),
(3, 'Audi'),
(4, 'Bentley'),
(5, 'BMW'),
(6, 'Bugatti'),
(7, 'Cadillac'),
(8, 'Chevrolet'),
(9, 'Chrysler'),
(10, 'Citroën'),
(11, 'Dacia'),
(12, 'Daewoo'),
(13, 'Dodge'),
(14, 'Ferrari'),
(15, 'Fiat'),
(16, 'Ford'),
(17, 'Honda'),
(18, 'Hyundai'),
(19, 'Infiniti'),
(20, 'Jaguar'),
(21, 'Jeep'),
(22, 'Kia'),
(23, 'Lamborghini'),
(24, 'Land Rover'),
(25, 'Lexus'),
(26, 'Maserati'),
(27, 'Mazda'),
(28, 'McLaren'),
(29, 'Mercedes-Benz'),
(30, 'Mitsubishi'),
(31, 'Nissan'),
(32, 'Opel'),
(33, 'Peugeot'),
(34, 'Porsche'),
(35, 'Renault'),
(36, 'Rolls-Royce'),
(37, 'Rover'),
(38, 'Saab'),
(39, 'Seat'),
(40, 'Skoda'),
(41, 'Subaru'),
(42, 'Suzuki'),
(44, 'Toyota'),
(45, 'Volkswagen'),
(46, 'Volvo');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `client_types`
--

CREATE TABLE `client_types` (
  `client_type_id` int(11) NOT NULL,
  `client_type_name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `client_types`
--

INSERT INTO `client_types` (`client_type_id`, `client_type_name`) VALUES
(2, 'Firma'),
(1, 'Fizyczna osoba');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `customers`
--

CREATE TABLE `customers` (
  `customer_id` int(11) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `client_type_id` int(11) NOT NULL,
  `company_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `customers`
--

INSERT INTO `customers` (`customer_id`, `first_name`, `last_name`, `phone`, `email`, `client_type_id`, `company_name`) VALUES
(10, 'Filip', 'Lisowski', '324144255', '', 2, 'TESTOWA'),
(12, 'Marek', 'Niemiec', '567456321', '', 1, '');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `drive`
--

CREATE TABLE `drive` (
  `drive_id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `drive`
--

INSERT INTO `drive` (`drive_id`, `name`) VALUES
(1, 'FWD'),
(2, 'RWD'),
(3, 'AWD'),
(4, '4x4'),
(5, 'EV-FWD'),
(6, 'EV-RWD'),
(7, 'EV-AWD');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `employees`
--

CREATE TABLE `employees` (
  `employee_id` int(11) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `phone_number` varchar(15) NOT NULL,
  `position` varchar(50) NOT NULL,
  `role_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `employees`
--

INSERT INTO `employees` (`employee_id`, `first_name`, `last_name`, `phone_number`, `position`, `role_id`) VALUES
(1, 'Filip', 'Lisowski', '534222323', 'Właściciel', 1),
(2, 'Konrad', 'Zaremba', '507333444', 'Prezes zarządu', 1),
(3, 'Elżbieta', 'Leszczyńska', '507444555', 'Dyrektor generalna', 1),
(4, 'Ewelina', 'Bąk', '506111222', 'Kierownik warsztatu', 2),
(5, 'Tadeusz', 'Nowicki', '506222333', 'Zastępca kierownika', 2),
(6, 'Natalia', 'Grabowska', '506333444', 'Kierownik ds. jakości', 2),
(7, 'Andrzej', 'Kruk', '506444555', 'Kierownik zmiany', 2),
(8, 'Katarzyna', 'Mazur', '506555666', 'Recepcjonistka główna', 4),
(9, 'Barbara', 'Sadowska', '506666777', 'Specjalistka ds. obsługi klienta', 4),
(10, 'Michał', 'Dąbrowski', '555333444', 'Mechanik', 3),
(11, 'Łukasz', 'Zieliński', '504222333', 'Mechanik', 3),
(12, 'Tomasz', 'Majewski', '505111222', 'Diagnosta samochodowy', 3),
(13, 'Kamil', 'Szymański', '505222333', 'Blacharz', 3),
(14, 'Paweł', 'Wójcik', '505333444', 'Lakiernik', 3),
(15, 'Michał', 'Kaczmarek', '505444555', 'Elektryk samochodowy', 3),
(16, 'Piotr', 'Jankowski', '505555666', 'Mechanik silnikowy', 3),
(17, 'Artur', 'Zawadzki', '505666777', 'Specjalista ds. klimatyzacji', 3),
(18, 'Sebastian', 'Lis', '505777888', 'Mechanik ogólny', 3),
(19, 'Rafał', 'Pawlak', '505888999', 'Specjalista ds. układów hamulcowych', 3),
(20, 'Jakub', 'Baran', '505999000', 'Specjalista ds. zawieszenia', 3),
(21, 'Damian', 'Sikora', '506000111', 'Specjalista ds. napędów', 3),
(22, 'Grzegorz', 'Urban', '506888999', 'Magazynier części zamiennych', 5),
(23, 'Patryk', 'Rogowski', '506999000', 'Magazynier – odbiór dostaw', 5),
(24, 'Sylwia', 'Walczak', '507000111', 'Specjalistka ds. inwentaryzacji', 5),
(25, 'Dariusz', 'Michalak', '507111222', 'Koordynator magazynu', 5);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `engines`
--

CREATE TABLE `engines` (
  `engine_id` int(11) NOT NULL,
  `name` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `engines`
--

INSERT INTO `engines` (`engine_id`, `name`) VALUES
(1, 'I2'),
(2, 'I3'),
(3, 'I4'),
(4, 'I5'),
(5, 'I6'),
(6, 'V6'),
(7, 'V8'),
(8, 'V10'),
(9, 'V12'),
(10, 'W8'),
(11, 'W12'),
(12, 'W16'),
(13, 'B4'),
(14, 'B6'),
(15, 'R1'),
(16, 'R2'),
(17, 'R4'),
(18, 'EV');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `fuel_types`
--

CREATE TABLE `fuel_types` (
  `fuel_type_id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `fuel_types`
--

INSERT INTO `fuel_types` (`fuel_type_id`, `name`) VALUES
(5, 'CNG - Gaz ziemny'),
(6, 'E85 - Etanol (bioetanol)'),
(12, 'Elektryczny (EV)'),
(10, 'Hybryda (Diesel)'),
(9, 'Hybryda (PB95)'),
(4, 'LPG - Gaz płynny'),
(3, 'ON - Olej napędowy (Diesel)'),
(8, 'PB95 + CNG'),
(7, 'PB95 + LPG'),
(1, 'PB95 - Benzyna bezołowiowa 95'),
(2, 'PB98 - Benzyna bezołowiowa 98'),
(11, 'Plug-in Hybryda (PHEV)'),
(13, 'Wodór (H2)');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `logins`
--

CREATE TABLE `logins` (
  `login_id` int(11) NOT NULL,
  `employee_id` int(11) NOT NULL,
  `login` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `logins`
--

INSERT INTO `logins` (`login_id`, `employee_id`, `login`, `password`) VALUES
(1, 1, 'flisowski', '1234'),
(2, 2, 'kzaremba', '1234'),
(3, 3, 'eleszczynska', '1234'),
(4, 4, 'ebak', '1234'),
(5, 5, 'tnowicki', '1234'),
(6, 6, 'ngrabowska', '1234'),
(7, 7, 'akruk', '1234'),
(8, 8, 'kmazur', '1234'),
(9, 9, 'bsadowska', '1234'),
(10, 10, 'mdabrowski', '1234'),
(11, 11, 'lzielinski', '1234'),
(12, 12, 'tmajewski', '1234'),
(13, 13, 'kszymanski', '1234'),
(14, 14, 'pwojcik', '1234'),
(15, 15, 'mkaczmarek', '1234'),
(16, 16, 'pjankowski', '1234'),
(17, 17, 'azawadzki', '1234'),
(18, 18, 'slis', '1234'),
(19, 19, 'rpawlak', '1234'),
(20, 20, 'jbaran', '1234'),
(21, 21, 'dsikora', '1234'),
(22, 22, 'gurban', '1234'),
(23, 23, 'progowki', '1234'),
(24, 24, 'swalczak', '1234'),
(25, 25, 'dmichalak', '1234');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `models`
--

CREATE TABLE `models` (
  `model_id` int(11) NOT NULL,
  `brand_id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `models`
--

INSERT INTO `models` (`model_id`, `brand_id`, `name`) VALUES
(1, 1, '145'),
(2, 1, '146'),
(3, 1, '147'),
(4, 1, '155'),
(5, 1, '156'),
(6, 1, '159'),
(7, 1, '164'),
(8, 1, '166'),
(9, 1, '33'),
(10, 1, '4C'),
(11, 1, '75'),
(12, 1, '90'),
(13, 1, 'Alfasud'),
(14, 1, 'Alfetta'),
(15, 1, 'Brera'),
(16, 1, 'Crosswagon'),
(17, 1, 'Giulia'),
(18, 1, 'Giulietta'),
(19, 1, 'GT'),
(20, 1, 'GTV'),
(21, 1, 'Junior'),
(22, 1, 'Mito'),
(23, 1, 'RS'),
(24, 1, 'Spider'),
(25, 1, 'Sportwagon'),
(26, 1, 'Sprint'),
(27, 1, 'Stelvio'),
(28, 1, 'Tonale'),
(29, 2, 'Bulldog'),
(30, 2, 'Cygnet'),
(31, 2, 'DB'),
(32, 2, 'DB Mk III'),
(33, 2, 'DB1'),
(34, 2, 'DB11'),
(35, 2, 'DB12'),
(36, 2, 'DB2'),
(37, 2, 'DB4'),
(38, 2, 'DB5'),
(39, 2, 'DB6'),
(40, 2, 'DB7'),
(41, 2, 'DB9'),
(42, 2, 'DBS'),
(43, 2, 'DBS Superleggera'),
(44, 2, 'DBX'),
(45, 2, 'DBX707'),
(46, 2, 'Inny'),
(47, 2, 'Lagonda'),
(48, 2, 'One-77'),
(49, 2, 'Rapide'),
(50, 2, 'V12 Vanquish'),
(51, 2, 'V12 Vantage'),
(52, 2, 'V8'),
(53, 2, 'V8 Vantage'),
(54, 2, 'V8 Zagato'),
(55, 2, 'Vanquish'),
(56, 2, 'Vantage'),
(57, 2, 'Virage'),
(58, 2, 'Volatne'),
(59, 3, '100'),
(60, 3, '200'),
(61, 3, '80'),
(62, 3, '90'),
(63, 3, 'A1'),
(64, 3, 'A2'),
(65, 3, 'A3'),
(66, 3, 'A4'),
(67, 3, 'A4 Allroad'),
(68, 3, 'A5'),
(69, 3, 'A6'),
(70, 3, 'A6 Allroad'),
(71, 3, 'A6 e-tron'),
(72, 3, 'A7'),
(73, 3, 'A8'),
(74, 3, 'Cabriolet'),
(75, 3, 'Coupe'),
(76, 3, 'e-tron'),
(77, 3, 'e-tron GT'),
(78, 3, 'Inny'),
(79, 3, 'Q2'),
(80, 3, 'Q3'),
(81, 3, 'Q3 Sportback'),
(82, 3, 'Q4'),
(83, 3, 'Q4 Sportback'),
(84, 3, 'Q5'),
(85, 3, 'Q5 Sportback'),
(86, 3, 'Q6'),
(87, 3, 'Q7'),
(88, 3, 'Q8'),
(89, 3, 'Quattro'),
(90, 3, 'R8'),
(91, 3, 'RS e-tron GT'),
(92, 3, 'RS Q3'),
(93, 3, 'RS Q8'),
(94, 3, 'RS2'),
(95, 3, 'RS3'),
(96, 3, 'RS4'),
(97, 3, 'RS5'),
(98, 3, 'RS6'),
(99, 3, 'RS7'),
(100, 3, 'S1'),
(101, 3, 'S2'),
(102, 3, 'S3'),
(103, 3, 'S4'),
(104, 3, 'S5'),
(105, 3, 'S6'),
(106, 3, 'S7'),
(107, 3, 'S8'),
(108, 3, 'SQ2'),
(109, 3, 'SQ5'),
(110, 3, 'SQ6'),
(111, 3, 'SQ7'),
(112, 3, 'SQ8'),
(113, 3, 'TT'),
(114, 3, 'TT RS'),
(115, 3, 'TT S'),
(116, 3, 'V8'),
(117, 4, 'Arnage'),
(118, 4, 'Azure'),
(119, 4, 'Bentayga'),
(120, 4, 'Brooklands'),
(121, 4, 'Continental GT'),
(122, 4, 'Eight'),
(123, 4, 'Flying Spur'),
(124, 4, 'Inny'),
(125, 4, 'Mulsanne'),
(126, 4, 'Turbo R'),
(127, 4, 'Turbo RT'),
(128, 4, 'Turbo S'),
(129, 5, '1M'),
(130, 5, '3GT'),
(131, 5, '5GT'),
(132, 5, '6GT'),
(133, 5, 'i3'),
(134, 5, 'i4'),
(135, 5, 'i5'),
(136, 5, 'i7'),
(137, 5, 'i8'),
(138, 5, 'Inny'),
(139, 5, 'iX'),
(140, 5, 'iX1'),
(141, 5, 'iX2'),
(142, 5, 'iX3'),
(143, 5, 'M2'),
(144, 5, 'M3'),
(145, 5, 'M4'),
(146, 5, 'M5'),
(147, 5, 'M6'),
(148, 5, 'M8'),
(149, 5, 'Seria 1'),
(150, 5, 'Seria 2'),
(151, 5, 'Seria 3'),
(152, 5, 'Seria 4'),
(153, 5, 'Seria 5'),
(154, 5, 'Seria 6'),
(155, 5, 'Seria 7'),
(156, 5, 'Seria 8'),
(157, 5, 'X1'),
(158, 5, 'X2'),
(159, 5, 'X3'),
(160, 5, 'X3 M'),
(161, 5, 'X4'),
(162, 5, 'X4 M'),
(163, 5, 'X5'),
(164, 5, 'X5 M'),
(165, 5, 'X6'),
(166, 5, 'X6M'),
(167, 5, 'X7'),
(168, 5, 'XM'),
(169, 5, 'Z1'),
(170, 5, 'Z3'),
(171, 5, 'Z4'),
(172, 5, 'Z4 M'),
(173, 5, 'Z8'),
(174, 6, 'Chiron'),
(175, 6, 'Divo'),
(176, 6, 'EB 110'),
(177, 6, 'Inny'),
(178, 6, 'Veyron'),
(179, 7, 'Allante'),
(180, 7, 'ATS'),
(181, 7, 'BLS'),
(182, 7, 'Brougham'),
(183, 7, 'Catera'),
(184, 7, 'Cimarron'),
(185, 7, 'CT4'),
(186, 7, 'CT5'),
(187, 7, 'CT6'),
(188, 7, 'CTS'),
(189, 7, 'Deville'),
(190, 7, 'DTS'),
(191, 7, 'Eldorado'),
(192, 7, 'ELR'),
(193, 7, 'Escalade'),
(194, 7, 'Fleetwood'),
(195, 7, 'Inny'),
(196, 7, 'LYRIQ'),
(197, 7, 'Seville'),
(198, 7, 'SLS'),
(199, 7, 'SRX'),
(200, 7, 'STS'),
(201, 7, 'STS-V'),
(202, 7, 'XLR'),
(203, 7, 'XLR-V'),
(204, 7, 'XT4'),
(205, 7, 'XT5'),
(206, 7, 'XT6'),
(207, 7, 'XTS'),
(208, 8, '1500'),
(209, 8, '2500'),
(210, 8, '3500'),
(211, 8, 'Alero'),
(212, 8, 'Apache'),
(213, 8, 'Astro'),
(214, 8, 'Avalanche'),
(215, 8, 'Aveo'),
(216, 8, 'Bel Air'),
(217, 8, 'Beretta'),
(218, 8, 'Blazer'),
(219, 8, 'Bolt'),
(220, 8, 'C-10'),
(221, 8, 'C-20'),
(222, 8, 'C-30'),
(223, 8, 'Camaro'),
(224, 8, 'Caprice'),
(225, 8, 'Captiva'),
(226, 8, 'Cavalier'),
(227, 8, 'Celebrity'),
(228, 8, 'Chevelle'),
(229, 8, 'Chevy Van'),
(230, 8, 'Citation'),
(231, 8, 'Cobalt'),
(232, 8, 'Colorado'),
(233, 8, 'Corsica'),
(234, 8, 'Corvair'),
(235, 8, 'Corvette'),
(236, 8, 'Cruze'),
(237, 8, 'El Camino'),
(238, 8, 'Epica'),
(239, 8, 'Equinox'),
(240, 8, 'Evanda'),
(241, 8, 'Express'),
(242, 8, 'G'),
(243, 8, 'HHR'),
(244, 8, 'Impala'),
(245, 8, 'Inny'),
(246, 8, 'K1500'),
(247, 8, 'K30'),
(248, 8, 'Kalos'),
(249, 8, 'Lacetti'),
(250, 8, 'Lumina'),
(251, 8, 'Malibu'),
(252, 8, 'Matiz'),
(253, 8, 'Menlo'),
(254, 8, 'Monte Carlo'),
(255, 8, 'Monza'),
(256, 8, 'Nova'),
(257, 8, 'Nubira'),
(258, 8, 'Orlando'),
(259, 8, 'Rezzo'),
(260, 8, 'S-10'),
(261, 8, 'Silverado'),
(262, 8, 'Spark'),
(263, 8, 'Spectrum'),
(264, 8, 'SSR'),
(265, 8, 'Suburban'),
(266, 8, 'Tacuma'),
(267, 8, 'Tahoe'),
(268, 8, 'Trailblazer'),
(269, 8, 'Trans Sport'),
(270, 8, 'Traverse'),
(271, 8, 'Trax'),
(272, 8, 'Venture'),
(273, 8, 'Volt'),
(274, 9, '200'),
(275, 9, '300'),
(276, 9, '300C'),
(277, 9, '300M'),
(278, 9, '300s'),
(279, 9, 'Aspen'),
(280, 9, 'Caravan'),
(281, 9, 'Concorde'),
(282, 9, 'Crossfire'),
(283, 9, 'Daytona'),
(284, 9, 'ES'),
(285, 9, 'Grand Voyager'),
(286, 9, 'GS'),
(287, 9, 'GTS'),
(288, 9, 'Inny'),
(289, 9, 'Le Baron'),
(290, 9, 'LHS'),
(291, 9, 'Neon'),
(292, 9, 'New Yorker'),
(293, 9, 'Pacifica'),
(294, 9, 'Prowler'),
(295, 9, 'PT Cruiser'),
(296, 9, 'Saratoga'),
(297, 9, 'Sebring'),
(298, 9, 'Stratus'),
(299, 9, 'Town & Country'),
(300, 9, 'Valiant'),
(301, 9, 'Viper'),
(302, 9, 'Vision'),
(303, 9, 'Voyager'),
(304, 10, '2 CV'),
(305, 10, 'AX'),
(306, 10, 'Axel'),
(307, 10, 'Berlingo'),
(308, 10, 'BX'),
(309, 10, 'C-Crosser'),
(310, 10, 'C-Elysée'),
(311, 10, 'C-Zero'),
(312, 10, 'C1'),
(313, 10, 'C2'),
(314, 10, 'C3'),
(315, 10, 'C3 Aircross'),
(316, 10, 'C3 Picasso'),
(317, 10, 'C3 Pluriel'),
(318, 10, 'C4'),
(319, 10, 'C4 Aircross'),
(320, 10, 'C4 Cactus'),
(321, 10, 'C4 Grand Picasso'),
(322, 10, 'C4 Picasso'),
(323, 10, 'C4 SpaceTourer'),
(324, 10, 'C4X'),
(325, 10, 'C5'),
(326, 10, 'C5 Aircross'),
(327, 10, 'C5X'),
(328, 10, 'C6'),
(329, 10, 'C8'),
(330, 10, 'CX'),
(331, 10, 'DS'),
(332, 10, 'DS3'),
(333, 10, 'DS4'),
(334, 10, 'DS5'),
(335, 10, 'DS7'),
(336, 10, 'Evasion'),
(337, 10, 'GSA'),
(338, 10, 'Inny'),
(339, 10, 'Jumper'),
(340, 10, 'Jumpy Combi'),
(341, 10, 'Nemo'),
(342, 10, 'Saxo'),
(343, 10, 'SM'),
(344, 10, 'SpaceTourer'),
(345, 10, 'Visa'),
(346, 10, 'Xantia'),
(347, 10, 'XM'),
(348, 10, 'Xsara'),
(349, 10, 'Xsara Picasso'),
(350, 10, 'ZX'),
(351, 11, '1300'),
(352, 11, '1310'),
(353, 11, '1400'),
(354, 11, '1410'),
(355, 11, 'Bigster'),
(356, 11, 'Dokker'),
(357, 11, 'Dokker Van'),
(358, 11, 'Duster'),
(359, 11, 'Inny'),
(360, 11, 'Jogger'),
(361, 11, 'Lodgy'),
(362, 11, 'Logan'),
(363, 11, 'Logan Van'),
(364, 11, 'Nova'),
(365, 11, 'Pick Up'),
(366, 11, 'Sandero'),
(367, 11, 'Sandero Stepway'),
(368, 11, 'Solenza'),
(369, 11, 'Spring'),
(370, 11, 'Super Nova'),
(371, 12, 'Chairman'),
(372, 12, 'Cielo'),
(373, 12, 'Espero'),
(374, 12, 'Evanda'),
(375, 12, 'Inny'),
(376, 12, 'Kalos'),
(377, 12, 'Korando'),
(378, 12, 'Lacetti'),
(379, 12, 'Lanos'),
(380, 12, 'Leganza'),
(381, 12, 'Matiz'),
(382, 12, 'Musso'),
(383, 12, 'Nexia'),
(384, 12, 'Nubira'),
(385, 12, 'Racer'),
(386, 12, 'Rezzo'),
(387, 12, 'Tacuma'),
(388, 12, 'Tico'),
(389, 13, 'Avenger'),
(390, 13, 'Caliber'),
(391, 13, 'Caravan'),
(392, 13, 'Challenger'),
(393, 13, 'Charger'),
(394, 13, 'Dakota'),
(395, 13, 'Dart'),
(396, 13, 'Daytona'),
(397, 13, 'Diplomat'),
(398, 13, 'Durango'),
(399, 13, 'Dynasty'),
(400, 13, 'Grand Caravan'),
(401, 13, 'Hornet'),
(402, 13, 'Inny'),
(403, 13, 'Intrepid'),
(404, 13, 'Journey'),
(405, 13, 'Magnum'),
(406, 13, 'Monaco'),
(407, 13, 'Neon'),
(408, 13, 'Nitro'),
(409, 13, 'Omni'),
(410, 13, 'RAM'),
(411, 13, 'RAM TRX'),
(412, 13, 'Spirit'),
(413, 13, 'Stealth'),
(414, 13, 'Stratus'),
(415, 13, 'Viper'),
(416, 14, 'Daytona SP3'),
(417, 14, '12Cilindri'),
(418, 14, '208'),
(419, 14, '248'),
(420, 14, '250'),
(421, 14, '288'),
(422, 14, '296 GTB'),
(423, 14, '296 GTS'),
(424, 14, '308'),
(425, 14, '328'),
(426, 14, '330'),
(427, 14, '348'),
(428, 14, '360'),
(429, 14, '365'),
(430, 14, '400'),
(431, 14, '412'),
(432, 14, '456'),
(433, 14, '458 Italia'),
(434, 14, '458 Speciale'),
(435, 14, '488'),
(436, 14, '488 Pista'),
(437, 14, '488 Pista Spider'),
(438, 14, '512'),
(439, 14, '575'),
(440, 14, '599GTB'),
(441, 14, '612'),
(442, 14, '750'),
(443, 14, '812 GTS'),
(444, 14, '812 Superfast'),
(445, 14, 'California'),
(446, 14, 'California T'),
(447, 14, 'Daytona'),
(448, 14, 'Dino GT4'),
(449, 14, 'Enzo'),
(450, 14, 'F12berlinetta'),
(451, 14, 'F355'),
(452, 14, 'F360'),
(453, 14, 'F40'),
(454, 14, 'F430'),
(455, 14, 'F50'),
(456, 14, 'F550'),
(457, 14, 'F8 Spider'),
(458, 14, 'F8 Tributo'),
(459, 14, 'FF'),
(460, 14, 'GTC4Lusso'),
(461, 14, 'Inny'),
(462, 14, 'LaFerrari'),
(463, 14, 'Mondial'),
(464, 14, 'Portofino'),
(465, 14, 'Portofino M'),
(466, 14, 'Purosangue'),
(467, 14, 'Roma'),
(468, 14, 'Roma Spider'),
(469, 14, 'SF90 Spider'),
(470, 14, 'SF90 Stradale'),
(471, 14, 'Superamerica'),
(472, 14, 'Testarossa'),
(473, 15, '124'),
(474, 15, '124 Spider'),
(475, 15, '125p'),
(476, 15, '126'),
(477, 15, '127'),
(478, 15, '128'),
(479, 15, '130'),
(480, 15, '131'),
(481, 15, '132'),
(482, 15, '500'),
(483, 15, '500e'),
(484, 15, '500L'),
(485, 15, '500X'),
(486, 15, '600'),
(487, 15, '850'),
(488, 15, 'Albea'),
(489, 15, 'Barchetta'),
(490, 15, 'Brava'),
(491, 15, 'Bravo'),
(492, 15, 'Cinquecento'),
(493, 15, 'Coupe'),
(494, 15, 'Croma'),
(495, 15, 'Dino'),
(496, 15, 'Doblo'),
(497, 15, 'Ducato'),
(498, 15, 'Fiorino'),
(499, 15, 'Freemont'),
(500, 15, 'Fullback'),
(501, 15, 'Grande Panda'),
(502, 15, 'Grande Punto'),
(503, 15, 'Idea'),
(504, 15, 'Inny'),
(505, 15, 'Linea'),
(506, 15, 'Marea'),
(507, 15, 'Multipla'),
(508, 15, 'Palio'),
(509, 15, 'Panda'),
(510, 15, 'Punto'),
(511, 15, 'Punto 2012'),
(512, 15, 'Punto Evo'),
(513, 15, 'Qubo'),
(514, 15, 'Regata'),
(515, 15, 'Ritmo'),
(516, 15, 'Scudo'),
(517, 15, 'Sedici'),
(518, 15, 'Seicento'),
(519, 15, 'Siena'),
(520, 15, 'Spider Europa'),
(521, 15, 'Stilo'),
(522, 15, 'Strada'),
(523, 15, 'Talento'),
(524, 15, 'Tempra'),
(525, 15, 'Tipo'),
(526, 15, 'Ulysse'),
(527, 15, 'Uno'),
(528, 15, 'X 1'),
(529, 16, 'Aerostar'),
(530, 16, 'Aspire'),
(531, 16, 'B-MAX'),
(532, 16, 'Bronco'),
(533, 16, 'C-MAX'),
(534, 16, 'Capri'),
(535, 16, 'Contour'),
(536, 16, 'Cougar'),
(537, 16, 'Courier'),
(538, 16, 'Crown'),
(539, 16, 'Econoline'),
(540, 16, 'Econovan'),
(541, 16, 'EcoSport'),
(542, 16, 'Edge'),
(543, 16, 'Escape'),
(544, 16, 'Escort'),
(545, 16, 'Excursion'),
(546, 16, 'Expedition'),
(547, 16, 'Explorer'),
(548, 16, 'F150'),
(549, 16, 'F250'),
(550, 16, 'F350'),
(551, 16, 'Fairlane'),
(552, 16, 'Falcon'),
(553, 16, 'Festiva'),
(554, 16, 'Fiesta'),
(555, 16, 'FIVE HUNDRED'),
(556, 16, 'Flex'),
(557, 16, 'Focus'),
(558, 16, 'Focus C-Max'),
(559, 16, 'Freestar'),
(560, 16, 'Freestyle'),
(561, 16, 'Fusion'),
(562, 16, 'Galaxy'),
(563, 16, 'Granada'),
(564, 16, 'Grand C-MAX'),
(565, 16, 'GT'),
(566, 16, 'Inny'),
(567, 16, 'KA'),
(568, 16, 'Ka+'),
(569, 16, 'Kuga'),
(570, 16, 'Maverick'),
(571, 16, 'Mercury'),
(572, 16, 'Mondeo'),
(573, 16, 'Mustang'),
(574, 16, 'Mustang Mach-E'),
(575, 16, 'Orion'),
(576, 16, 'Probe'),
(577, 16, 'Puma'),
(578, 16, 'Ranchero'),
(579, 16, 'Ranger'),
(580, 16, 'Ranger Raptor'),
(581, 16, 'S-Max'),
(582, 16, 'Scorpio'),
(583, 16, 'Sierra'),
(584, 16, 'Streetka'),
(585, 16, 'Taunus'),
(586, 16, 'Taurus'),
(587, 16, 'Tempo'),
(588, 16, 'Thunderbird'),
(589, 16, 'Tourneo Connect'),
(590, 16, 'Tourneo Connect Grand'),
(591, 16, 'Tourneo Courier'),
(592, 16, 'Tourneo Custom'),
(593, 16, 'Transit'),
(594, 16, 'Transit Connect'),
(595, 16, 'Transit Courier'),
(596, 16, 'Transit Custom'),
(597, 16, 'Windstar'),
(598, 17, 'Accord'),
(599, 17, 'Aerodeck'),
(600, 17, 'City'),
(601, 17, 'Civic'),
(602, 17, 'Clarity'),
(603, 17, 'Concerto'),
(604, 17, 'CR-V'),
(605, 17, 'CR-Z'),
(606, 17, 'CRX'),
(607, 17, 'e'),
(608, 17, 'e:NP1'),
(609, 17, 'e:NSP1'),
(610, 17, 'e:NY1'),
(611, 17, 'Element'),
(612, 17, 'FR-V'),
(613, 17, 'HR-V'),
(614, 17, 'Inny'),
(615, 17, 'Insight'),
(616, 17, 'Integra'),
(617, 17, 'Jazz'),
(618, 17, 'Legend'),
(619, 17, 'Logo'),
(620, 17, 'NSX'),
(621, 17, 'Odyssey'),
(622, 17, 'Pilot'),
(623, 17, 'Prelude'),
(624, 17, 'Ridgeline'),
(625, 17, 'S 2000'),
(626, 17, 'Shuttle'),
(627, 17, 'Stream'),
(628, 17, 'ZR-V'),
(629, 18, 'Accent'),
(630, 18, 'Atos'),
(631, 18, 'Avante'),
(632, 18, 'Azera'),
(633, 18, 'Bayon'),
(634, 18, 'Coupe'),
(635, 18, 'Elantra'),
(636, 18, 'Equus'),
(637, 18, 'Excel'),
(638, 18, 'Galloper'),
(639, 18, 'Genesis'),
(640, 18, 'Genesis Coupe'),
(641, 18, 'Getz'),
(642, 18, 'Grand Santa Fe'),
(643, 18, 'Grandeur'),
(644, 18, 'H-1'),
(645, 18, 'H-1 Starex'),
(646, 18, 'H200'),
(647, 18, 'H350'),
(648, 18, 'i10'),
(649, 18, 'i20'),
(650, 18, 'i25'),
(651, 18, 'i30'),
(652, 18, 'i30 N'),
(653, 18, 'i40'),
(654, 18, 'Inny'),
(655, 18, 'Inster'),
(656, 18, 'IONIQ'),
(657, 18, 'IONIQ 5'),
(658, 18, 'IONIQ 6'),
(659, 18, 'IONIQ 7'),
(660, 18, 'IONIQ 9'),
(661, 18, 'ix20'),
(662, 18, 'ix35'),
(663, 18, 'ix55'),
(664, 18, 'Kona'),
(665, 18, 'Lantra'),
(666, 18, 'Matrix'),
(667, 18, 'NEXO'),
(668, 18, 'Palisade'),
(669, 18, 'Pony'),
(670, 18, 'S-Coupe'),
(671, 18, 'Santa Cruz'),
(672, 18, 'Santa Fe'),
(673, 18, 'Santamo'),
(674, 18, 'Sonata'),
(675, 18, 'Sonica'),
(676, 18, 'Staria'),
(677, 18, 'Terracan'),
(678, 18, 'Trajet'),
(679, 18, 'Tucson'),
(680, 18, 'Veloster'),
(681, 18, 'Veracruz'),
(682, 18, 'XG 30'),
(683, 18, 'XG 350'),
(684, 19, 'EX'),
(685, 19, 'FX'),
(686, 19, 'G'),
(687, 19, 'I30'),
(688, 19, 'I35'),
(689, 19, 'Inny'),
(690, 19, 'J30'),
(691, 19, 'M'),
(692, 19, 'Q30'),
(693, 19, 'Q45'),
(694, 19, 'Q50'),
(695, 19, 'Q60'),
(696, 19, 'Q70'),
(697, 19, 'QX'),
(698, 19, 'QX30'),
(699, 19, 'QX50'),
(700, 19, 'QX55'),
(701, 19, 'QX60'),
(702, 19, 'QX70'),
(703, 19, 'QX80'),
(704, 20, 'Daimler'),
(705, 20, 'E-Pace'),
(706, 20, 'E-Type'),
(707, 20, 'F-Pace'),
(708, 20, 'F-Type'),
(709, 20, 'I-Pace'),
(710, 20, 'Inny'),
(711, 20, 'MK II'),
(712, 20, 'S-Type'),
(713, 20, 'X-Type'),
(714, 20, 'XE'),
(715, 20, 'XF'),
(716, 20, 'XJ'),
(717, 20, 'XJR'),
(718, 20, 'XJS'),
(719, 20, 'XK'),
(720, 20, 'XK8'),
(721, 21, 'Avenger'),
(722, 21, 'Cherokee'),
(723, 21, 'CJ'),
(724, 21, 'Comanche'),
(725, 21, 'Commander'),
(726, 21, 'Compass'),
(727, 21, 'Gladiator'),
(728, 21, 'Grand Cherokee'),
(729, 21, 'Grand Wagoneer'),
(730, 21, 'Inny'),
(731, 21, 'Liberty'),
(732, 21, 'Patriot'),
(733, 21, 'Renegade'),
(734, 21, 'Wagoneer'),
(735, 21, 'Willys'),
(736, 21, 'Wrangler'),
(737, 22, 'Asia Rocsta'),
(738, 22, 'Besta'),
(739, 22, 'Cadenza'),
(740, 22, 'Carens'),
(741, 22, 'Carnival'),
(742, 22, 'Ceed'),
(743, 22, 'Cerato'),
(744, 22, 'Clarus'),
(745, 22, 'Elan'),
(746, 22, 'EV3'),
(747, 22, 'EV5'),
(748, 22, 'EV6'),
(749, 22, 'EV9'),
(750, 22, 'Inny'),
(751, 22, 'Joice'),
(752, 22, 'Leo'),
(753, 22, 'Magentis'),
(754, 22, 'Mentor'),
(755, 22, 'Niro'),
(756, 22, 'Opirus'),
(757, 22, 'Optima'),
(758, 22, 'Picanto'),
(759, 22, 'Pregio'),
(760, 22, 'Pride'),
(761, 22, 'ProCeed'),
(762, 22, 'PV5'),
(763, 22, 'Retona'),
(764, 22, 'Rio'),
(765, 22, 'Roadster'),
(766, 22, 'Rocsta'),
(767, 22, 'Sedona'),
(768, 22, 'Sephia'),
(769, 22, 'Shuma'),
(770, 22, 'Sorento'),
(771, 22, 'Soul'),
(772, 22, 'Spectra'),
(773, 22, 'Sportage'),
(774, 22, 'Stinger'),
(775, 22, 'Stonic'),
(776, 22, 'Telluride'),
(777, 22, 'Venga'),
(778, 22, 'XCeed'),
(779, 23, 'Aventador'),
(780, 23, 'Countach'),
(781, 23, 'Diablo'),
(782, 23, 'Espada'),
(783, 23, 'Gallardo'),
(784, 23, 'Huracan'),
(785, 23, 'Inny'),
(786, 23, 'Jalpa'),
(787, 23, 'LM'),
(788, 23, 'Miura'),
(789, 23, 'Murcielago'),
(790, 23, 'Urraco'),
(791, 23, 'Urus'),
(792, 24, 'Defender'),
(793, 24, 'Discovery'),
(794, 24, 'Discovery Sport'),
(795, 24, 'Freelander'),
(796, 24, 'Inny'),
(797, 24, 'Range Rover'),
(798, 24, 'Range Rover Evoque'),
(799, 24, 'Range Rover Sport'),
(800, 24, 'Range Rover Velar'),
(801, 25, 'CT'),
(802, 25, 'ES'),
(803, 25, 'GS'),
(804, 25, 'GX'),
(805, 25, 'HS 250h'),
(806, 25, 'Inny'),
(807, 25, 'IS'),
(808, 25, 'LBX'),
(809, 25, 'LC'),
(810, 25, 'LFA'),
(811, 25, 'LM'),
(812, 25, 'LS'),
(813, 25, 'LX'),
(814, 25, 'NX'),
(815, 25, 'RC'),
(816, 25, 'RX'),
(817, 25, 'RZ'),
(818, 25, 'SC'),
(819, 25, 'TX'),
(820, 25, 'UX'),
(821, 26, '224'),
(822, 26, '3200'),
(823, 26, 'Biturbo'),
(824, 26, 'Coupe'),
(825, 26, 'Ghibli'),
(826, 26, 'GranCabrio'),
(827, 26, 'Gransport'),
(828, 26, 'GranTurismo'),
(829, 26, 'Grecale'),
(830, 26, 'Inny'),
(831, 26, 'Levante'),
(832, 26, 'MC20'),
(833, 26, 'MC20 Cielo'),
(834, 26, 'Merak'),
(835, 26, 'Quattroporte'),
(836, 26, 'Shamal'),
(837, 26, 'Spyder'),
(838, 27, '121'),
(839, 27, '2'),
(840, 27, '2 Hybrid'),
(841, 27, '3'),
(842, 27, '323'),
(843, 27, '323F'),
(844, 27, '5'),
(845, 27, '6'),
(846, 27, '626'),
(847, 27, '929'),
(848, 27, 'Bongo'),
(849, 27, 'BT-50'),
(850, 27, 'CX-3'),
(851, 27, 'CX-30'),
(852, 27, 'CX-5'),
(853, 27, 'CX-50'),
(854, 27, 'CX-60'),
(855, 27, 'CX-7'),
(856, 27, 'CX-80'),
(857, 27, 'CX-9'),
(858, 27, 'CX-90'),
(859, 27, 'Demio'),
(860, 27, 'EZ-6'),
(861, 27, 'Inny'),
(862, 27, 'Millenia'),
(863, 27, 'MPV'),
(864, 27, 'MX-3'),
(865, 27, 'MX-30'),
(866, 27, 'MX-5'),
(867, 27, 'MX-6'),
(868, 27, 'Premacy'),
(869, 27, 'Protege'),
(870, 27, 'RX-6'),
(871, 27, 'RX-7'),
(872, 27, 'RX-8'),
(873, 27, 'Seria B'),
(874, 27, 'Seria E'),
(875, 27, 'Tribute'),
(876, 27, 'Xedos'),
(877, 28, '570 GT'),
(878, 28, '570S Coupe'),
(879, 28, '570S Spider'),
(880, 28, '600LT Coupe'),
(881, 28, '600LT Spider'),
(882, 28, '650S'),
(883, 28, '675Lt'),
(884, 28, '720S Coupe'),
(885, 28, '720S Spider'),
(886, 28, '765LT Coupe'),
(887, 28, '765LT Spider'),
(888, 28, 'Artura'),
(889, 28, 'GT'),
(890, 28, 'Inny'),
(891, 28, 'MP4-12C'),
(892, 29, '280'),
(893, 29, 'AMG GT'),
(894, 29, 'Citan'),
(895, 29, 'CL'),
(896, 29, 'CLA'),
(897, 29, 'CLC'),
(898, 29, 'CLE'),
(899, 29, 'CLK'),
(900, 29, 'CLS'),
(901, 29, 'EQA'),
(902, 29, 'EQB'),
(903, 29, 'EQC'),
(904, 29, 'EQE'),
(905, 29, 'EQS'),
(906, 29, 'EQS 680 SUV'),
(907, 29, 'EQT'),
(908, 29, 'EQV'),
(909, 29, 'GL'),
(910, 29, 'GLA'),
(911, 29, 'GLB'),
(912, 29, 'GLC'),
(913, 29, 'GLE'),
(914, 29, 'GLK'),
(915, 29, 'GLS'),
(916, 29, 'Inny'),
(917, 29, 'Klasa A'),
(918, 29, 'Klasa B'),
(919, 29, 'Klasa C'),
(920, 29, 'Klasa E'),
(921, 29, 'Klasa G'),
(922, 29, 'Klasa R'),
(923, 29, 'Klasa S'),
(924, 29, 'Klasa T'),
(925, 29, 'Klasa V'),
(926, 29, 'Klasa X'),
(927, 29, 'Maybach GLS'),
(928, 29, 'Maybach Klasa S'),
(929, 29, 'Maybach SL 680'),
(930, 29, 'MB 100'),
(931, 29, 'ML'),
(932, 29, 'Monarch'),
(933, 29, 'SL'),
(934, 29, 'SLC'),
(935, 29, 'SLK'),
(936, 29, 'SLR'),
(937, 29, 'SLS'),
(938, 29, 'Sprinter'),
(939, 29, 'Vaneo'),
(940, 29, 'Vario'),
(941, 29, 'Viano'),
(942, 29, 'Vito'),
(943, 29, 'W123'),
(944, 29, 'W124 (1984-1993)'),
(945, 29, 'W201 (190)'),
(946, 30, '3000GT'),
(947, 30, 'ASX'),
(948, 30, 'Canter'),
(949, 30, 'Carisma'),
(950, 30, 'Colt'),
(951, 30, 'Cordia'),
(952, 30, 'Cosmos'),
(953, 30, 'Diamante'),
(954, 30, 'Eclipse'),
(955, 30, 'Eclipse Cross'),
(956, 30, 'Endeavor'),
(957, 30, 'FTO'),
(958, 30, 'Galant'),
(959, 30, 'Galloper'),
(960, 30, 'Grandis'),
(961, 30, 'i-MiEV'),
(962, 30, 'Inny'),
(963, 30, 'L200'),
(964, 30, 'L300'),
(965, 30, 'L400'),
(966, 30, 'Lancer'),
(967, 30, 'Lancer Evolution'),
(968, 30, 'Montero'),
(969, 30, 'Outlander'),
(970, 30, 'Pajero'),
(971, 30, 'Pajero Pinin'),
(972, 30, 'Pajero Sport'),
(973, 30, 'Santamo'),
(974, 30, 'Sapporo'),
(975, 30, 'Sigma'),
(976, 30, 'Space Gear'),
(977, 30, 'Space Runner'),
(978, 30, 'Space Star'),
(979, 30, 'Space Wagon'),
(980, 30, 'Starion'),
(981, 30, 'Tredia'),
(982, 31, '100 NX'),
(983, 31, '200 SX'),
(984, 31, '240 SX'),
(985, 31, '280 ZX'),
(986, 31, '300 ZX'),
(987, 31, '350 Z'),
(988, 31, '370 Z'),
(989, 31, 'Almera'),
(990, 31, 'Almera Tino'),
(991, 31, 'Altima'),
(992, 31, 'Ariya'),
(993, 31, 'Armada'),
(994, 31, 'Bluebird'),
(995, 31, 'Cherry'),
(996, 31, 'Cube'),
(997, 31, 'Frontier'),
(998, 31, 'GT-R'),
(999, 31, 'Inny'),
(1000, 31, 'Interstar'),
(1001, 31, 'Juke'),
(1002, 31, 'Kikcs'),
(1003, 31, 'King Cab'),
(1004, 31, 'Kubistar'),
(1005, 31, 'Laurel'),
(1006, 31, 'Leaf'),
(1007, 31, 'Maxima'),
(1008, 31, 'Micra'),
(1009, 31, 'Murano'),
(1010, 31, 'Navara'),
(1011, 31, 'Note'),
(1012, 31, 'NP300 Pickup'),
(1013, 31, 'NV200'),
(1014, 31, 'NV300'),
(1015, 31, 'NV400'),
(1016, 31, 'Pathfinder'),
(1017, 31, 'Patrol'),
(1018, 31, 'Pickup'),
(1019, 31, 'Pixo'),
(1020, 31, 'Prairie'),
(1021, 31, 'Primastar'),
(1022, 31, 'Primera'),
(1023, 31, 'Pulsar'),
(1024, 31, 'Qashqai'),
(1025, 31, 'Qashqai+2'),
(1026, 31, 'Quest'),
(1027, 31, 'Rogue'),
(1028, 31, 'Sentra'),
(1029, 31, 'Serena'),
(1030, 31, 'Silvia'),
(1031, 31, 'Skyline'),
(1032, 31, 'Stanza'),
(1033, 31, 'Sunny'),
(1034, 31, 'Terrano'),
(1035, 31, 'Tiida'),
(1036, 31, 'Titan'),
(1037, 31, 'Townstar'),
(1038, 31, 'Trade'),
(1039, 31, 'Urvan'),
(1040, 31, 'Vanette'),
(1041, 31, 'X-Trail'),
(1042, 31, 'Xterra'),
(1043, 32, 'Adam'),
(1044, 32, 'Agila'),
(1045, 32, 'Ampera'),
(1046, 32, 'Antara'),
(1047, 32, 'Arena'),
(1048, 32, 'Ascona'),
(1049, 32, 'Astra'),
(1050, 32, 'Calibra'),
(1051, 32, 'Campo'),
(1052, 32, 'Cascada'),
(1053, 32, 'Combo'),
(1054, 32, 'Commodore'),
(1055, 32, 'Corsa'),
(1056, 32, 'Crossland'),
(1057, 32, 'Crossland X'),
(1058, 32, 'Diplomat'),
(1059, 32, 'Frontera'),
(1060, 32, 'Grandland'),
(1061, 32, 'Grandland X'),
(1062, 32, 'GT'),
(1063, 32, 'Inny'),
(1064, 32, 'Insignia'),
(1065, 32, 'Kadett'),
(1066, 32, 'Karl'),
(1067, 32, 'Manta'),
(1068, 32, 'Meriva'),
(1069, 32, 'Mokka'),
(1070, 32, 'Monterey'),
(1071, 32, 'Monza'),
(1072, 32, 'Movano'),
(1073, 32, 'Nova'),
(1074, 32, 'Omega'),
(1075, 32, 'Pick up Sportcap'),
(1076, 32, 'Rekord'),
(1077, 32, 'Senator'),
(1078, 32, 'Signum'),
(1079, 32, 'Sintra'),
(1080, 32, 'Speedster'),
(1081, 32, 'Tigra'),
(1082, 32, 'Vectra'),
(1083, 32, 'Vivaro'),
(1084, 32, 'Zafira'),
(1085, 33, '1007'),
(1086, 33, '104'),
(1087, 33, '106'),
(1088, 33, '107'),
(1089, 33, '108'),
(1090, 33, '2008'),
(1091, 33, '204'),
(1092, 33, '205'),
(1093, 33, '206'),
(1094, 33, '206 CC'),
(1095, 33, '206 plus'),
(1096, 33, '207'),
(1097, 33, '207 CC'),
(1098, 33, '208'),
(1099, 33, '3008'),
(1100, 33, '301'),
(1101, 33, '304'),
(1102, 33, '305'),
(1103, 33, '306'),
(1104, 33, '307'),
(1105, 33, '307 CC'),
(1106, 33, '308'),
(1107, 33, '308 CC'),
(1108, 33, '309'),
(1109, 33, '4007'),
(1110, 33, '4008'),
(1111, 33, '404'),
(1112, 33, '405'),
(1113, 33, '406'),
(1114, 33, '407'),
(1115, 33, '408'),
(1116, 33, '5008'),
(1117, 33, '504'),
(1118, 33, '505'),
(1119, 33, '508'),
(1120, 33, '604'),
(1121, 33, '605'),
(1122, 33, '607'),
(1123, 33, '806'),
(1124, 33, '807'),
(1125, 33, 'Bipper'),
(1126, 33, 'Boxer'),
(1127, 33, 'E-5008'),
(1128, 33, 'Expert'),
(1129, 33, 'Inny'),
(1130, 33, 'iOn'),
(1131, 33, 'Partner'),
(1132, 33, 'RCZ'),
(1133, 33, 'Rifter'),
(1134, 33, 'Traveller'),
(1135, 34, '356'),
(1136, 34, '718 Boxster'),
(1137, 34, '718 Cayman'),
(1138, 34, '718 Spyder'),
(1139, 34, '911'),
(1140, 34, '912'),
(1141, 34, '914'),
(1142, 34, '924'),
(1143, 34, '928'),
(1144, 34, '944'),
(1145, 34, '959'),
(1146, 34, '962'),
(1147, 34, '968'),
(1148, 34, 'Boxster'),
(1149, 34, 'Carrera GT'),
(1150, 34, 'Cayenne'),
(1151, 34, 'Cayman'),
(1152, 34, 'Inny'),
(1153, 34, 'Macan'),
(1154, 34, 'Panamera'),
(1155, 34, 'Taycan'),
(1156, 35, '10'),
(1157, 35, '11'),
(1158, 35, '12'),
(1159, 35, '14'),
(1160, 35, '18'),
(1161, 35, '19'),
(1162, 35, '20'),
(1163, 35, '21'),
(1164, 35, '25'),
(1165, 35, '30'),
(1166, 35, '4'),
(1167, 35, '5'),
(1168, 35, '8'),
(1169, 35, '9'),
(1170, 35, 'Alaskan'),
(1171, 35, 'Alpine A110'),
(1172, 35, 'Alpine A310'),
(1173, 35, 'Alpine V6'),
(1174, 35, 'Arkana'),
(1175, 35, 'Austral'),
(1176, 35, 'Avantime'),
(1177, 35, 'Captur'),
(1178, 35, 'Clio'),
(1179, 35, 'Coupe'),
(1180, 35, 'Espace'),
(1181, 35, 'Express'),
(1182, 35, 'Fluence'),
(1183, 35, 'Fuego'),
(1184, 35, 'Grand Espace'),
(1185, 35, 'Grand Scenic'),
(1186, 35, 'Inny'),
(1187, 35, 'Kadjar'),
(1188, 35, 'Kangoo'),
(1189, 35, 'Koleos'),
(1190, 35, 'Laguna'),
(1191, 35, 'Latitude'),
(1192, 35, 'Master'),
(1193, 35, 'Megane'),
(1194, 35, 'Modus'),
(1195, 35, 'Rafale'),
(1196, 35, 'Safrane'),
(1197, 35, 'Scenic'),
(1198, 35, 'Scenic Conquest'),
(1199, 35, 'Scenic RX4'),
(1200, 35, 'Symbioz'),
(1201, 35, 'Talisman'),
(1202, 35, 'Thalia'),
(1203, 35, 'Trafic'),
(1204, 35, 'Twingo'),
(1205, 35, 'Twizy'),
(1206, 35, 'Vel Satis'),
(1207, 35, 'Wind'),
(1208, 35, 'Zoe'),
(1209, 36, 'Corniche'),
(1210, 36, 'Cullinan'),
(1211, 36, 'Dawn'),
(1212, 36, 'Flying Spur'),
(1213, 36, 'Ghost'),
(1214, 36, 'Inny'),
(1215, 36, 'Park Ward'),
(1216, 36, 'Phantom'),
(1217, 36, 'Silver Cloud'),
(1218, 36, 'Silver Down'),
(1219, 36, 'Silver Seraph'),
(1220, 36, 'Silver Shadow'),
(1221, 36, 'Silver Spirit'),
(1222, 36, 'Silver Spur'),
(1223, 36, 'Spectre'),
(1224, 36, 'Touring Limousine'),
(1225, 36, 'Wraith'),
(1226, 37, '100'),
(1227, 37, '111'),
(1228, 37, '114'),
(1229, 37, '115'),
(1230, 37, '200'),
(1231, 37, '213'),
(1232, 37, '214'),
(1233, 37, '216'),
(1234, 37, '218'),
(1235, 37, '220'),
(1236, 37, '25'),
(1237, 37, '400'),
(1238, 37, '414'),
(1239, 37, '416'),
(1240, 37, '418'),
(1241, 37, '420'),
(1242, 37, '45'),
(1243, 37, '600'),
(1244, 37, '618'),
(1245, 37, '620'),
(1246, 37, '623'),
(1247, 37, '75'),
(1248, 37, '800'),
(1249, 37, '820'),
(1250, 37, '825'),
(1251, 37, '827'),
(1252, 37, 'City Rover'),
(1253, 37, 'Inny'),
(1254, 37, 'Metro'),
(1255, 37, 'MG'),
(1256, 37, 'Mini'),
(1257, 37, 'Montego'),
(1258, 37, 'SD'),
(1259, 37, 'Streetwise'),
(1260, 38, '9-2X'),
(1261, 38, '9-3'),
(1262, 38, '9-3X'),
(1263, 38, '9-5'),
(1264, 38, '9-7X'),
(1265, 38, '90'),
(1266, 38, '900'),
(1267, 38, '9000'),
(1268, 38, '96'),
(1269, 38, '99'),
(1270, 38, 'Inny'),
(1271, 39, 'Alhambra'),
(1272, 39, 'Altea'),
(1273, 39, 'Altea XL'),
(1274, 39, 'Arona'),
(1275, 39, 'Arosa'),
(1276, 39, 'Ateca'),
(1277, 39, 'Cordoba'),
(1278, 39, 'Exeo'),
(1279, 39, 'Ibiza'),
(1280, 39, 'Inca'),
(1281, 39, 'Inny'),
(1282, 39, 'Leon'),
(1283, 39, 'Malaga'),
(1284, 39, 'Marbella'),
(1285, 39, 'Mii'),
(1286, 39, 'Ronda'),
(1287, 39, 'Tarraco'),
(1288, 39, 'Terra'),
(1289, 39, 'Toledo'),
(1290, 40, '100'),
(1291, 40, '105'),
(1292, 40, '120'),
(1293, 40, '130'),
(1294, 40, '135'),
(1295, 40, 'Citigo'),
(1296, 40, 'Elroq'),
(1297, 40, 'Enyaq'),
(1298, 40, 'Fabia'),
(1299, 40, 'Favorit'),
(1300, 40, 'Felicia'),
(1301, 40, 'Forman'),
(1302, 40, 'Inny'),
(1303, 40, 'Kamiq'),
(1304, 40, 'Karoq'),
(1305, 40, 'Kodiaq'),
(1306, 40, 'Octavia'),
(1307, 40, 'Praktik'),
(1308, 40, 'RAPID'),
(1309, 40, 'Roomster'),
(1310, 40, 'Scala'),
(1311, 40, 'Superb'),
(1312, 40, 'Yeti'),
(1313, 41, '1800 Coupe'),
(1314, 41, 'Ascent'),
(1315, 41, 'B9 Tribeca'),
(1316, 41, 'Baja'),
(1317, 41, 'BRZ'),
(1318, 41, 'Crosstrek'),
(1319, 41, 'Forester'),
(1320, 41, 'G3X Justy'),
(1321, 41, 'Impreza'),
(1322, 41, 'Inny'),
(1323, 41, 'Justy'),
(1324, 41, 'Legacy'),
(1325, 41, 'Leone'),
(1326, 41, 'Levorg'),
(1327, 41, 'Outback'),
(1328, 41, 'Solterra'),
(1329, 41, 'SVX'),
(1330, 41, 'Trezia'),
(1331, 41, 'Tribeca'),
(1332, 41, 'Vivio'),
(1333, 41, 'WRX'),
(1334, 41, 'XT'),
(1335, 41, 'XV'),
(1336, 42, 'Across'),
(1337, 42, 'Alto'),
(1338, 42, 'Baleno'),
(1339, 42, 'Cappucino'),
(1340, 42, 'Carry'),
(1341, 42, 'Celerio'),
(1342, 42, 'Fronx'),
(1343, 42, 'Grand Vitara'),
(1344, 42, 'Ignis'),
(1345, 42, 'Inny'),
(1346, 42, 'Jimny'),
(1347, 42, 'Kizashi'),
(1348, 42, 'Liana'),
(1349, 42, 'LJ'),
(1350, 42, 'Reno'),
(1351, 42, 'Samurai'),
(1352, 42, 'SJ'),
(1353, 42, 'Splash'),
(1354, 42, 'Super-Carry'),
(1355, 42, 'Swace'),
(1356, 42, 'Swift'),
(1357, 42, 'SX4'),
(1358, 42, 'SX4 S-Cross'),
(1359, 42, 'Vitara'),
(1360, 42, 'Wagon R+'),
(1361, 42, 'X-90'),
(1362, 42, 'XL7'),
(1370, 44, '4-Runner'),
(1371, 44, 'Auris'),
(1372, 44, 'Avalon'),
(1373, 44, 'Avensis'),
(1374, 44, 'Avensis Verso'),
(1375, 44, 'Aygo'),
(1376, 44, 'Aygo X'),
(1377, 44, 'bZ4X'),
(1378, 44, 'C-HR'),
(1379, 44, 'Camry'),
(1380, 44, 'Camry Solara'),
(1381, 44, 'Carina'),
(1382, 44, 'Celica'),
(1383, 44, 'Corolla'),
(1384, 44, 'Corolla Cross'),
(1385, 44, 'Corolla Verso'),
(1386, 44, 'Cressida'),
(1387, 44, 'Crown'),
(1388, 44, 'Dyna'),
(1389, 44, 'FJ'),
(1390, 44, 'GR86'),
(1391, 44, 'Grand Highlander'),
(1392, 44, 'GT86'),
(1393, 44, 'Hiace'),
(1394, 44, 'Highlander'),
(1395, 44, 'Hilux'),
(1396, 44, 'Inny'),
(1397, 44, 'iQ'),
(1398, 44, 'Land Cruiser'),
(1399, 44, 'Lite-Ace'),
(1400, 44, 'Matrix'),
(1401, 44, 'Mirai'),
(1402, 44, 'MR2'),
(1403, 44, 'Paseo'),
(1404, 44, 'Picnic'),
(1405, 44, 'Previa'),
(1406, 44, 'Prius'),
(1407, 44, 'Prius+'),
(1408, 44, 'ProAce'),
(1409, 44, 'Proace City'),
(1410, 44, 'Proace City Verso'),
(1411, 44, 'Proace Verso'),
(1412, 44, 'RAV4'),
(1413, 44, 'Sequoia'),
(1414, 44, 'Sienna'),
(1415, 44, 'Starlet'),
(1416, 44, 'Supra'),
(1417, 44, 'Tacoma'),
(1418, 44, 'Tercel'),
(1419, 44, 'Tundra'),
(1420, 44, 'Urban Cruiser'),
(1421, 44, 'Venza'),
(1422, 44, 'Verso'),
(1423, 44, 'Verso S'),
(1424, 44, 'Yaris'),
(1425, 44, 'Yaris Cross'),
(1426, 44, 'Yaris Verso'),
(1427, 45, '181'),
(1428, 45, 'Amarok'),
(1429, 45, 'Arteon'),
(1430, 45, 'Atlas'),
(1431, 45, 'Beetle'),
(1432, 45, 'Bora'),
(1433, 45, 'Buggy'),
(1434, 45, 'Caddy'),
(1435, 45, 'California'),
(1436, 45, 'Caravelle'),
(1437, 45, 'CC'),
(1438, 45, 'Corrado'),
(1439, 45, 'Crafter'),
(1440, 45, 'Eos'),
(1441, 45, 'Fox'),
(1442, 45, 'Garbus'),
(1443, 45, 'Golf'),
(1444, 45, 'Golf Plus'),
(1445, 45, 'Golf Sportsvan'),
(1446, 45, 'ID. Buzz'),
(1447, 45, 'ID.3'),
(1448, 45, 'ID.4'),
(1449, 45, 'ID.5'),
(1450, 45, 'ID.6'),
(1451, 45, 'ID.7'),
(1452, 45, 'Iltis'),
(1453, 45, 'Inny'),
(1454, 45, 'Jetta'),
(1455, 45, 'Kafer'),
(1456, 45, 'Karmann Ghia'),
(1457, 45, 'LT'),
(1458, 45, 'Lupo'),
(1459, 45, 'Multivan'),
(1460, 45, 'New Beetle'),
(1461, 45, 'Passat'),
(1462, 45, 'Passat Alltrack'),
(1463, 45, 'Passat CC'),
(1464, 45, 'Phaeton'),
(1465, 45, 'Polo'),
(1466, 45, 'Polo Cross'),
(1467, 45, 'Routan'),
(1468, 45, 'Santana'),
(1469, 45, 'Scirocco'),
(1470, 45, 'Sharan'),
(1471, 45, 'T-Cross'),
(1472, 45, 'T-Roc'),
(1473, 45, 'Taigo'),
(1474, 45, 'Tayron'),
(1475, 45, 'Teramont'),
(1476, 45, 'Tiguan'),
(1477, 45, 'Tiguan Allspace'),
(1478, 45, 'Touareg'),
(1479, 45, 'Touran'),
(1480, 45, 'Transporter'),
(1481, 45, 'up!'),
(1482, 45, 'Vento'),
(1483, 46, '245'),
(1484, 46, '262'),
(1485, 46, '340'),
(1486, 46, '744'),
(1487, 46, '745'),
(1488, 46, '780'),
(1489, 46, '850'),
(1490, 46, '855'),
(1491, 46, '945'),
(1492, 46, '965'),
(1493, 46, 'C30'),
(1494, 46, 'C40'),
(1495, 46, 'C70'),
(1496, 46, 'EM90'),
(1497, 46, 'EX30'),
(1498, 46, 'EX90'),
(1499, 46, 'Inny'),
(1500, 46, 'MY23'),
(1501, 46, 'P1800'),
(1502, 46, 'Polar'),
(1503, 46, 'S40'),
(1504, 46, 'S60'),
(1505, 46, 'S70'),
(1506, 46, 'S80'),
(1507, 46, 'S90'),
(1508, 46, 'Seria 200'),
(1509, 46, 'Seria 300'),
(1510, 46, 'Seria 400'),
(1511, 46, 'Seria 700'),
(1512, 46, 'Seria 900'),
(1513, 46, 'V40'),
(1514, 46, 'V40 Cross Country'),
(1515, 46, 'V50'),
(1516, 46, 'V60'),
(1517, 46, 'V60 Cross Country'),
(1518, 46, 'V70'),
(1519, 46, 'V90'),
(1520, 46, 'V90 Cross Country'),
(1521, 46, 'XC 40'),
(1522, 46, 'XC 60'),
(1523, 46, 'XC 70'),
(1524, 46, 'XC 90');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `orders`
--

CREATE TABLE `orders` (
  `id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `vehicle_id` int(11) NOT NULL,
  `employee_id` int(11) NOT NULL,
  `service_id` int(11) NOT NULL,
  `part_id` int(11) NOT NULL,
  `deadline` date NOT NULL,
  `status` varchar(50) NOT NULL,
  `total_cost` decimal(10,2) NOT NULL,
  `description` text DEFAULT NULL,
  `used_part_quantity` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`id`, `customer_id`, `vehicle_id`, `employee_id`, `service_id`, `part_id`, `deadline`, `status`, `total_cost`, `description`, `used_part_quantity`) VALUES
(1, 10, 6, 10, 8, 11, '2025-06-19', 'Nowe', 690.00, 'wymiana klocków hamulcowych', 1),
(11, 10, 9, 1, 9, 11, '2025-06-11', 'Nowe', 1825.00, 'asd', 0),
(12, 12, 11, 5, 8, 8, '2025-06-29', 'W trakcie', 320.00, '123', 10);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `parts`
--

CREATE TABLE `parts` (
  `part_id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `quantity` int(11) NOT NULL CHECK (`quantity` >= 0),
  `unit_price` decimal(10,2) NOT NULL,
  `producer` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `parts`
--

INSERT INTO `parts` (`part_id`, `name`, `quantity`, `unit_price`, `producer`) VALUES
(8, 'Żarówka H9', 35, 8.00, 'Bosch'),
(11, 'Zacisk hamulcowy', 5, 450.00, 'Brembo'),
(12, 'Lakier samochodowy', 0, 40.00, 'AkzoNobel'),
(13, 'Wycieraczka', 56, 49.99, 'Bosch'),
(14, 'test', 9, 120.00, 'test');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `roles`
--

CREATE TABLE `roles` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`id`, `name`) VALUES
(1, 'admin'),
(2, 'manager'),
(3, 'mechanic'),
(4, 'receptionist'),
(5, 'warehouseman');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `services`
--

CREATE TABLE `services` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `price` decimal(10,2) NOT NULL CHECK (`price` >= 0),
  `duration` int(11) NOT NULL CHECK (`duration` > 0)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `services`
--

INSERT INTO `services` (`id`, `name`, `price`, `duration`) VALUES
(8, 'Wymiana klocków hamulcowych', 240.00, 2),
(9, 'Wymiana rozrządu', 475.00, 5),
(14, 'Lakierowanie', 2500.00, 12);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `vehicles`
--

CREATE TABLE `vehicles` (
  `vehicle_id` int(11) NOT NULL,
  `body_type_id` int(11) NOT NULL,
  `brand_id` int(11) NOT NULL,
  `model_id` int(11) NOT NULL,
  `fuel_type_id` int(11) NOT NULL,
  `engine_type_id` int(11) NOT NULL,
  `engine_capacity` int(11) NOT NULL,
  `drive_type_id` int(11) NOT NULL,
  `production_year` int(11) NOT NULL,
  `mileage` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `vehicles`
--

INSERT INTO `vehicles` (`vehicle_id`, `body_type_id`, `brand_id`, `model_id`, `fuel_type_id`, `engine_type_id`, `engine_capacity`, `drive_type_id`, `production_year`, `mileage`) VALUES
(6, 2, 33, 1119, 3, 3, 1988, 4, 2014, 158796),
(7, 4, 5, 144, 7, 7, 4378, 2, 1999, 58765),
(9, 6, 7, 193, 1, 7, 6478, 3, 2019, 56787),
(11, 3, 40, 1298, 1, 2, 1198, 1, 2009, 115678);

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `body_types`
--
ALTER TABLE `body_types`
  ADD PRIMARY KEY (`body_type_id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indeksy dla tabeli `brands`
--
ALTER TABLE `brands`
  ADD PRIMARY KEY (`brand_id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indeksy dla tabeli `client_types`
--
ALTER TABLE `client_types`
  ADD PRIMARY KEY (`client_type_id`),
  ADD UNIQUE KEY `type_name` (`client_type_name`);

--
-- Indeksy dla tabeli `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`customer_id`),
  ADD KEY `fk_customers_client_type` (`client_type_id`);

--
-- Indeksy dla tabeli `drive`
--
ALTER TABLE `drive`
  ADD PRIMARY KEY (`drive_id`);

--
-- Indeksy dla tabeli `employees`
--
ALTER TABLE `employees`
  ADD PRIMARY KEY (`employee_id`),
  ADD KEY `fk_employees_role` (`role_id`);

--
-- Indeksy dla tabeli `engines`
--
ALTER TABLE `engines`
  ADD PRIMARY KEY (`engine_id`);

--
-- Indeksy dla tabeli `fuel_types`
--
ALTER TABLE `fuel_types`
  ADD PRIMARY KEY (`fuel_type_id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indeksy dla tabeli `logins`
--
ALTER TABLE `logins`
  ADD PRIMARY KEY (`login_id`),
  ADD UNIQUE KEY `login` (`login`),
  ADD KEY `employee_id` (`employee_id`);

--
-- Indeksy dla tabeli `models`
--
ALTER TABLE `models`
  ADD PRIMARY KEY (`model_id`),
  ADD KEY `fk_models_brand` (`brand_id`);

--
-- Indeksy dla tabeli `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`),
  ADD KEY `customer_id` (`customer_id`),
  ADD KEY `vehicle_id` (`vehicle_id`),
  ADD KEY `employee_id` (`employee_id`),
  ADD KEY `service_id` (`service_id`),
  ADD KEY `part_id` (`part_id`);

--
-- Indeksy dla tabeli `parts`
--
ALTER TABLE `parts`
  ADD PRIMARY KEY (`part_id`);

--
-- Indeksy dla tabeli `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indeksy dla tabeli `services`
--
ALTER TABLE `services`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `vehicles`
--
ALTER TABLE `vehicles`
  ADD PRIMARY KEY (`vehicle_id`),
  ADD KEY `body_type_id` (`body_type_id`),
  ADD KEY `brand_id` (`brand_id`),
  ADD KEY `model_id` (`model_id`),
  ADD KEY `fuel_type_id` (`fuel_type_id`),
  ADD KEY `engine_type_id` (`engine_type_id`),
  ADD KEY `drive_type_id` (`drive_type_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `body_types`
--
ALTER TABLE `body_types`
  MODIFY `body_type_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `brands`
--
ALTER TABLE `brands`
  MODIFY `brand_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=47;

--
-- AUTO_INCREMENT for table `client_types`
--
ALTER TABLE `client_types`
  MODIFY `client_type_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `customers`
--
ALTER TABLE `customers`
  MODIFY `customer_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `drive`
--
ALTER TABLE `drive`
  MODIFY `drive_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `employees`
--
ALTER TABLE `employees`
  MODIFY `employee_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT for table `engines`
--
ALTER TABLE `engines`
  MODIFY `engine_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `fuel_types`
--
ALTER TABLE `fuel_types`
  MODIFY `fuel_type_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `logins`
--
ALTER TABLE `logins`
  MODIFY `login_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT for table `models`
--
ALTER TABLE `models`
  MODIFY `model_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1525;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `parts`
--
ALTER TABLE `parts`
  MODIFY `part_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `services`
--
ALTER TABLE `services`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `vehicles`
--
ALTER TABLE `vehicles`
  MODIFY `vehicle_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `customers`
--
ALTER TABLE `customers`
  ADD CONSTRAINT `fk_customers_client_type` FOREIGN KEY (`client_type_id`) REFERENCES `client_types` (`client_type_id`) ON UPDATE CASCADE;

--
-- Constraints for table `employees`
--
ALTER TABLE `employees`
  ADD CONSTRAINT `fk_employees_role` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`) ON UPDATE CASCADE;

--
-- Constraints for table `logins`
--
ALTER TABLE `logins`
  ADD CONSTRAINT `logins_ibfk_1` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`employee_id`);

--
-- Constraints for table `models`
--
ALTER TABLE `models`
  ADD CONSTRAINT `fk_models_brand` FOREIGN KEY (`brand_id`) REFERENCES `brands` (`brand_id`),
  ADD CONSTRAINT `models_ibfk_1` FOREIGN KEY (`brand_id`) REFERENCES `brands` (`brand_id`) ON DELETE CASCADE;

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`customer_id`),
  ADD CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`vehicle_id`) REFERENCES `vehicles` (`vehicle_id`),
  ADD CONSTRAINT `orders_ibfk_3` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`employee_id`),
  ADD CONSTRAINT `orders_ibfk_4` FOREIGN KEY (`service_id`) REFERENCES `services` (`id`),
  ADD CONSTRAINT `orders_ibfk_5` FOREIGN KEY (`part_id`) REFERENCES `parts` (`part_id`);

--
-- Constraints for table `vehicles`
--
ALTER TABLE `vehicles`
  ADD CONSTRAINT `vehicles_ibfk_1` FOREIGN KEY (`body_type_id`) REFERENCES `body_types` (`body_type_id`),
  ADD CONSTRAINT `vehicles_ibfk_2` FOREIGN KEY (`brand_id`) REFERENCES `brands` (`brand_id`),
  ADD CONSTRAINT `vehicles_ibfk_3` FOREIGN KEY (`model_id`) REFERENCES `models` (`model_id`),
  ADD CONSTRAINT `vehicles_ibfk_4` FOREIGN KEY (`fuel_type_id`) REFERENCES `fuel_types` (`fuel_type_id`),
  ADD CONSTRAINT `vehicles_ibfk_5` FOREIGN KEY (`engine_type_id`) REFERENCES `engines` (`engine_id`),
  ADD CONSTRAINT `vehicles_ibfk_6` FOREIGN KEY (`drive_type_id`) REFERENCES `drive` (`drive_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
