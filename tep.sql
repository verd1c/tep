-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 06, 2021 at 02:46 PM
-- Server version: 10.4.17-MariaDB
-- PHP Version: 8.0.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tep`
--

-- --------------------------------------------------------

--
-- Table structure for table `diagnoses`
--

CREATE TABLE `diagnoses` (
  `diagnoses_id` int(20) NOT NULL,
  `visit_id` int(20) NOT NULL,
  `name` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `diagnoses`
--

INSERT INTO `diagnoses` (`diagnoses_id`, `visit_id`, `name`) VALUES
(1, 15, '\"yes\"'),
(2, 25, 'heart disease'),
(4, 17, 'covid'),
(5, 24, 'heart disease'),
(6, 18, 'immune system dysfunction'),
(7, 20, 'immune system dysfunction'),
(8, 27, 'covid'),
(9, 23, 'immune system dysfunction'),
(13, 28, 'heart disease'),
(14, 29, 'arthritis');

-- --------------------------------------------------------

--
-- Table structure for table `diagnosis`
--

CREATE TABLE `diagnosis` (
  `diagnosis_id` int(20) NOT NULL,
  `name` varchar(40) NOT NULL,
  `target` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `diagnosis`
--

INSERT INTO `diagnosis` (`diagnosis_id`, `name`, `target`) VALUES
(2, 'immune system dysfunction', 'eczema'),
(3, 'covid', 'fever'),
(4, 'heart disease', 'heartache'),
(5, 'alergy', 'inflammation'),
(6, 'overweight', 'kidneystone'),
(7, 'arthritis', 'pain');

-- --------------------------------------------------------

--
-- Table structure for table `doctors`
--

CREATE TABLE `doctors` (
  `user_id` int(20) NOT NULL,
  `first_name` varchar(30) NOT NULL,
  `last_name` varchar(30) NOT NULL,
  `specialization` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `doctors`
--

INSERT INTO `doctors` (`user_id`, `first_name`, `last_name`, `specialization`) VALUES
(14, 'Johnny', 'Sins', 'Cardiologist'),
(17, 'Bob', 'Marley', 'Surgeon'),
(18, 'Nick', 'Takeo', 'Surgeon'),
(19, 'Nikolas', 'Cage', 'General'),
(20, 'John', 'Doestar', 'Dermatologist'),
(21, 'Julia', 'Majch', 'Allergist'),
(26, 'Maria', 'Dong', 'Anesthesiologist');

-- --------------------------------------------------------

--
-- Table structure for table `drugs`
--

CREATE TABLE `drugs` (
  `drug_id` int(11) NOT NULL,
  `name` varchar(40) NOT NULL,
  `type` varchar(40) NOT NULL,
  `density` int(40) NOT NULL,
  `target` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `drugs`
--

INSERT INTO `drugs` (`drug_id`, `name`, `type`, `density`, `target`) VALUES
(2, 'Azathioprine', 'oral', 50, 'eczema'),
(3, 'Cyclosporine', 'oral', 100, 'eczema'),
(4, 'Dupixent', 'injectable', 300, 'eczema'),
(5, 'Nemolizumab', 'injectable', 250, 'eczema'),
(6, 'Paracetamol', 'oral', 151, 'fever'),
(7, 'Tylenol', 'oral', 151, 'fever'),
(8, 'Ibuprofen', 'oral', 21, 'fever'),
(9, 'Motrin', 'oral', 180, 'fever'),
(10, 'Aspirin', 'oral', 180, 'heartache'),
(11, 'Blood Thinner', 'injectable', 400, 'heartache'),
(12, 'Xanax', 'oral', 250, 'heartache'),
(13, 'Relaxers', 'oral', 100, 'heartache'),
(14, 'Bayer', 'oral', 200, 'inflammation'),
(15, 'Naprosyn', 'oral', 230, 'inflammation'),
(16, 'Midol', 'oral', 211, 'inflammation'),
(17, 'Advil', 'oral', 206, 'inflammation'),
(18, 'Aleve', 'oral', 200, 'kidneystone'),
(19, 'Thiazide', 'oral', 180, 'kidneystone'),
(20, 'Flomax', 'oral', 300, 'kidneystone'),
(21, 'Jalyn', 'oral', 230, 'kidneystone'),
(22, 'Bupivacaine', 'injectable', 123, 'pain'),
(23, 'Lidocaine', 'injectable', 400, 'pain'),
(24, 'Gabapentin', 'oral', 200, 'pain'),
(25, 'Naproxen', 'oral', 151, 'pain');

-- --------------------------------------------------------

--
-- Table structure for table `employees`
--

CREATE TABLE `employees` (
  `user_id` int(20) NOT NULL,
  `first_name` varchar(30) NOT NULL,
  `last_name` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `employees`
--

INSERT INTO `employees` (`user_id`, `first_name`, `last_name`) VALUES
(33, 'Josh', 'Wa');

-- --------------------------------------------------------

--
-- Table structure for table `examinations`
--

CREATE TABLE `examinations` (
  `examination_id` int(20) NOT NULL,
  `visit_id` int(20) NOT NULL,
  `amka` int(20) NOT NULL,
  `doctor_id` int(20) NOT NULL,
  `diagnosis` varchar(40) NOT NULL,
  `hospitalized` varchar(40) NOT NULL,
  `note` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `examinations`
--

INSERT INTO `examinations` (`examination_id`, `visit_id`, `amka`, `doctor_id`, `diagnosis`, `hospitalized`, `note`) VALUES
(31, 26, 312, 14, 'Good', 'false', 'Good state'),
(35, 14, 732, 21, 'aa', 'false', 'none'),
(36, 15, 120, 14, 'aa', 'true', 'Drugs given'),
(37, 25, 312, 14, 'aa', 'false', 'none'),
(38, 17, 100, 19, 'aa', 'false', 'none'),
(39, 24, 312, 14, 'aa', 'true', 'none'),
(40, 18, 100, 20, 'aa', 'true', 'Has covid'),
(41, 20, 100, 20, 'none', 'true', 'Good state'),
(42, 27, 152, 19, 'none', 'false', 'none'),
(43, 28, 999, 14, 'none', 'false', 'Too many big macs'),
(44, 29, 99, 26, 'none', 'true', 'Was fine');

-- --------------------------------------------------------

--
-- Table structure for table `illness`
--

CREATE TABLE `illness` (
  `name` varchar(40) NOT NULL,
  `specialization` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `illness`
--

INSERT INTO `illness` (`name`, `specialization`) VALUES
('eczema', 'Dermatologist'),
('fever', 'General'),
('heartache', 'Cardiologist'),
('inflammation', 'Allergist'),
('kidneystone', 'Surgeon'),
('pain', 'Anesthesiologist');

-- --------------------------------------------------------

--
-- Table structure for table `medicaltests`
--

CREATE TABLE `medicaltests` (
  `medicaltest_id` int(11) NOT NULL,
  `visit_id` int(20) NOT NULL,
  `type` varchar(40) NOT NULL,
  `completed` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `medicaltests`
--

INSERT INTO `medicaltests` (`medicaltest_id`, `visit_id`, `type`, `completed`) VALUES
(5, 14, 'none', 'false'),
(6, 15, 'none', 'true'),
(7, 25, 'electrocardiogram', 'true'),
(10, 17, 'covid test', 'true'),
(11, 24, 'electrocardiogram', 'true'),
(12, 18, 'skin biopsy', 'true'),
(13, 18, 'skin biopsy', 'true'),
(14, 18, 'skin biopsy', 'true'),
(15, 18, 'skin biopsy', 'true'),
(16, 20, 'skin biopsy', 'true'),
(17, 27, 'covid test', 'true'),
(18, 23, 'skin biopsy', 'false'),
(21, 28, 'electrocardiogram', 'true'),
(22, 28, 'electrocardiogram', 'true'),
(23, 29, 'xray', 'true');

-- --------------------------------------------------------

--
-- Table structure for table `nurses`
--

CREATE TABLE `nurses` (
  `user_id` int(20) NOT NULL,
  `first_name` varchar(30) NOT NULL,
  `last_name` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `nurses`
--

INSERT INTO `nurses` (`user_id`, `first_name`, `last_name`) VALUES
(29, 'Astrid', 'Mueler');

-- --------------------------------------------------------

--
-- Table structure for table `patients`
--

CREATE TABLE `patients` (
  `user_id` int(20) NOT NULL,
  `amka` int(20) NOT NULL,
  `first_name` varchar(20) NOT NULL,
  `last_name` varchar(20) NOT NULL,
  `address` varchar(40) NOT NULL,
  `institution` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `patients`
--

INSERT INTO `patients` (`user_id`, `amka`, `first_name`, `last_name`, `address`, `institution`) VALUES
(15, 100, 'Aimee', 'Marchand', 'Deutschland', 'Ayaya'),
(22, 169, 'Jason', 'Derulo', 'Desesaas', 'Aaaaa'),
(23, 3211, 'Polymorph', 'Forest', 'Axaxaaxaxaxaxsxaasdds', 'Idk lol'),
(24, 732, 'Jules', 'Uwu', 'Waw', 'Wow'),
(27, 120, 'Bobby', 'Shmurda', 'Black', 'Ahah'),
(28, 312, 'Bolber', 'Mohamms', 'Hallel', 'Usjan'),
(30, 152, 'Jareed', 'Keren', 'Halum', 'Salem'),
(31, 999, 'Big', 'Mac', 'Narnia', 'Lol'),
(34, 99, 'Jason', 'Manthos', 'Heraklion', 'Poggies');

-- --------------------------------------------------------

--
-- Table structure for table `prescriptions`
--

CREATE TABLE `prescriptions` (
  `prescription_id` int(11) NOT NULL,
  `visit_id` int(20) NOT NULL,
  `drug` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `prescriptions`
--

INSERT INTO `prescriptions` (`prescription_id`, `visit_id`, `drug`) VALUES
(1, 26, 'Tylenol'),
(2, 26, 'Ibuprofen'),
(3, 26, 'Ibuprofen'),
(4, 25, 'Aspirin'),
(6, 17, 'Paracetamol'),
(7, 24, 'Aspirin'),
(8, 24, 'Blood Thinner'),
(9, 18, 'Cyclosporine'),
(10, 18, 'Nemolizumab'),
(11, 20, 'Cyclosporine'),
(12, 27, 'Paracetamol'),
(13, 27, 'Tylenol'),
(14, 23, 'Azathioprine'),
(17, 28, 'Aspirin'),
(18, 29, 'Lidocaine'),
(19, 29, 'Naproxen');

-- --------------------------------------------------------

--
-- Table structure for table `shift`
--

CREATE TABLE `shift` (
  `shift_id` int(20) NOT NULL,
  `since` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `shift`
--

INSERT INTO `shift` (`shift_id`, `since`) VALUES
(1, '2021-01-03 12:09:35.431'),
(2, '2021-01-03 12:10:21.822'),
(3, '2021-01-03 12:12:28.683'),
(4, '2021-01-03 12:54:43.913'),
(5, '2021-01-05 11:14:07.801'),
(6, '2021-01-05 11:17:36.309'),
(7, '2021-01-05 11:24:48.133'),
(8, '2021-01-05 14:36:10.393'),
(9, '2021-01-05 14:42:42.923');

-- --------------------------------------------------------

--
-- Table structure for table `shift_attendee`
--

CREATE TABLE `shift_attendee` (
  `shift_id` int(20) NOT NULL,
  `user_id` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `shift_attendee`
--

INSERT INTO `shift_attendee` (`shift_id`, `user_id`) VALUES
(1, 14),
(1, 17),
(1, 18),
(1, 19),
(2, 14),
(2, 17),
(2, 21),
(2, 19),
(3, 14),
(3, 17),
(3, 21),
(3, 19),
(4, 14),
(4, 17),
(4, 21),
(4, 18),
(4, 19),
(4, 20),
(4, 26),
(5, 14),
(5, 17),
(5, 21),
(5, 18),
(5, 19),
(5, 20),
(5, 26),
(5, 29),
(6, 14),
(6, 17),
(6, 21),
(6, 18),
(6, 19),
(6, 20),
(6, 26),
(6, 29),
(7, 14),
(7, 17),
(7, 21),
(7, 18),
(7, 19),
(7, 20),
(7, 26),
(7, 29),
(8, 14),
(8, 17),
(8, 21),
(8, 18),
(8, 19),
(8, 20),
(8, 26),
(8, 29),
(8, 33),
(9, 14),
(9, 17),
(9, 21),
(9, 18),
(9, 19),
(9, 20),
(9, 26),
(9, 29),
(9, 33);

-- --------------------------------------------------------

--
-- Table structure for table `tests`
--

CREATE TABLE `tests` (
  `test_id` int(20) NOT NULL,
  `name` varchar(40) NOT NULL,
  `target` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tests`
--

INSERT INTO `tests` (`test_id`, `name`, `target`) VALUES
(1, 'skin biopsy', 'eczema'),
(2, 'covid test', 'fever'),
(3, 'electrocardiogram', 'heartache'),
(4, 'crp', 'inflammation'),
(5, 'tomography', 'kidneystone'),
(6, 'xray', 'pain');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int(20) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `job` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `username`, `password`, `job`) VALUES
(14, 'johnnysins', 'psins', 'doctor'),
(15, '100', 'p100', 'patient'),
(17, 'bobmarley', 'pmarley', 'doctor'),
(18, 'nicktakeo', 'ptakeo', 'doctor'),
(19, 'nikolascage', 'pcage', 'doctor'),
(20, 'JohnDoe', 'pDoe', 'doctor'),
(21, 'JuliaMajch', 'pMajch', 'doctor'),
(22, '169', 'p169', 'patient'),
(23, '3211', 'p3211', 'patient'),
(24, '732', 'p732', 'patient'),
(26, 'MariaDong', 'pDong', 'doctor'),
(27, '120', 'p120', 'patient'),
(28, '312', 'p312', 'patient'),
(29, 'AstridMueler', 'pMueler', 'nurse'),
(30, '152', 'p152', 'patient'),
(31, '999', 'p999', 'patient'),
(33, 'JoshWa', 'pWa', 'employee'),
(34, '99', 'p99', 'patient');

-- --------------------------------------------------------

--
-- Table structure for table `visits`
--

CREATE TABLE `visits` (
  `visit_id` int(40) NOT NULL,
  `amka` int(40) NOT NULL,
  `doctor` int(20) NOT NULL,
  `illness` varchar(40) NOT NULL,
  `date` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `visits`
--

INSERT INTO `visits` (`visit_id`, `amka`, `doctor`, `illness`, `date`) VALUES
(15, 120, 14, 'heartache', '2021-01-03 12:55:46.294'),
(16, 100, 19, 'fever', '2021-01-03 12:56:01.76'),
(17, 100, 19, 'fever', '2021-01-03 12:56:03.476'),
(18, 100, 20, 'eczema', '2021-01-03 12:56:05.009'),
(19, 100, 21, 'inflammation', '2021-01-03 12:56:06.477'),
(20, 100, 20, 'eczema', '2021-01-03 12:57:43.344'),
(21, 100, 17, 'kidneystone', '2021-01-03 13:00:04.889'),
(22, 100, 26, 'pain', '2021-01-03 13:00:07.365'),
(23, 100, 20, 'eczema', '2021-01-03 13:00:11.071'),
(24, 312, 14, 'heartache', '2021-01-03 13:46:07.674'),
(25, 312, 14, 'heartache', '2021-01-03 13:46:08.338'),
(27, 152, 19, 'fever', '2021-01-05 07:54:54.964'),
(28, 999, 14, 'heartache', '2021-01-05 10:49:24.366'),
(29, 99, 26, 'pain', '2021-01-05 14:44:42.849');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `diagnoses`
--
ALTER TABLE `diagnoses`
  ADD PRIMARY KEY (`diagnoses_id`),
  ADD UNIQUE KEY `visit_id` (`visit_id`);

--
-- Indexes for table `diagnosis`
--
ALTER TABLE `diagnosis`
  ADD PRIMARY KEY (`diagnosis_id`);

--
-- Indexes for table `doctors`
--
ALTER TABLE `doctors`
  ADD PRIMARY KEY (`user_id`) USING BTREE;

--
-- Indexes for table `drugs`
--
ALTER TABLE `drugs`
  ADD PRIMARY KEY (`drug_id`);

--
-- Indexes for table `employees`
--
ALTER TABLE `employees`
  ADD PRIMARY KEY (`user_id`);

--
-- Indexes for table `examinations`
--
ALTER TABLE `examinations`
  ADD PRIMARY KEY (`examination_id`),
  ADD UNIQUE KEY `visit_id` (`visit_id`);

--
-- Indexes for table `illness`
--
ALTER TABLE `illness`
  ADD PRIMARY KEY (`name`);

--
-- Indexes for table `medicaltests`
--
ALTER TABLE `medicaltests`
  ADD PRIMARY KEY (`medicaltest_id`);

--
-- Indexes for table `nurses`
--
ALTER TABLE `nurses`
  ADD PRIMARY KEY (`user_id`);

--
-- Indexes for table `patients`
--
ALTER TABLE `patients`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `amka` (`amka`);

--
-- Indexes for table `prescriptions`
--
ALTER TABLE `prescriptions`
  ADD PRIMARY KEY (`prescription_id`);

--
-- Indexes for table `shift`
--
ALTER TABLE `shift`
  ADD PRIMARY KEY (`shift_id`);

--
-- Indexes for table `tests`
--
ALTER TABLE `tests`
  ADD PRIMARY KEY (`test_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indexes for table `visits`
--
ALTER TABLE `visits`
  ADD PRIMARY KEY (`visit_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `diagnoses`
--
ALTER TABLE `diagnoses`
  MODIFY `diagnoses_id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `diagnosis`
--
ALTER TABLE `diagnosis`
  MODIFY `diagnosis_id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `drugs`
--
ALTER TABLE `drugs`
  MODIFY `drug_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT for table `examinations`
--
ALTER TABLE `examinations`
  MODIFY `examination_id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45;

--
-- AUTO_INCREMENT for table `medicaltests`
--
ALTER TABLE `medicaltests`
  MODIFY `medicaltest_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `prescriptions`
--
ALTER TABLE `prescriptions`
  MODIFY `prescription_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `shift`
--
ALTER TABLE `shift`
  MODIFY `shift_id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `tests`
--
ALTER TABLE `tests`
  MODIFY `test_id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT for table `visits`
--
ALTER TABLE `visits`
  MODIFY `visit_id` int(40) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `doctors`
--
ALTER TABLE `doctors`
  ADD CONSTRAINT `doctors_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `employees`
--
ALTER TABLE `employees`
  ADD CONSTRAINT `employees_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `nurses`
--
ALTER TABLE `nurses`
  ADD CONSTRAINT `nurses_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `patients`
--
ALTER TABLE `patients`
  ADD CONSTRAINT `patients_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
