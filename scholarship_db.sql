-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 20, 2026 at 11:36 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `scholarship_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `administration`
--

CREATE TABLE `administration` (
  `admin_id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL,
  `designation` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `administration`
--

INSERT INTO `administration` (`admin_id`, `name`, `email`, `password`, `designation`) VALUES
(1, 'System Admin', 'admin@gmail.com', '1234', 'Administrator'),
(2, 'Jude Fernandes', 'jude@university.edu', '1234', 'Scholarship Officer'),
(3, 'Mary Johnson', 'mary@university.edu', '1234', 'Registrar');

-- --------------------------------------------------------

--
-- Table structure for table `admins`
--

CREATE TABLE `admins` (
  `admin_id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `admins`
--

INSERT INTO `admins` (`admin_id`, `username`, `password`) VALUES
(1, 'admin1', 'admin2'),
(2, 'admin', 'admin123');

-- --------------------------------------------------------

--
-- Table structure for table `scholarships`
--

CREATE TABLE `scholarships` (
  `scholarship_id` int(11) NOT NULL,
  `scholarship_name` varchar(100) NOT NULL,
  `minimum_cgpa` decimal(3,2) NOT NULL,
  `amount` decimal(10,2) NOT NULL,
  `deadline` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `scholarships`
--

INSERT INTO `scholarships` (`scholarship_id`, `scholarship_name`, `minimum_cgpa`, `amount`, `deadline`) VALUES
(1, 'Chairman Scholarship', 3.75, 50000.00, '2026-08-31'),
(2, 'Need-Based Scholarship', 3.00, 30000.00, '2026-09-15'),
(3, 'Women in STEM Scholarship', 3.20, 40000.00, '2026-10-01'),
(4, 'International Exchange Scholarship', 3.60, 80000.00, '2026-11-30'),
(5, 'Innovation & Entrepreneurship Grant', 3.40, 45000.00, '2026-10-15'),
(6, 'Community Leadership Scholarship', 3.20, 35000.00, '2026-12-10'),
(7, 'Financial Assistance Scholarship', 2.80, 25000.00, '2026-09-30'),
(8, 'Dean\'s Excellence Scholarship', 3.90, 100000.00, '2026-08-15');

-- --------------------------------------------------------

--
-- Table structure for table `scholarship_applications`
--

CREATE TABLE `scholarship_applications` (
  `application_id` int(11) NOT NULL,
  `student_id` int(11) NOT NULL,
  `scholarship_id` int(11) NOT NULL,
  `application_date` date NOT NULL,
  `status` enum('Pending','Approved','Rejected') DEFAULT 'Pending',
  `reviewed_by` int(11) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `scholarship_applications`
--

INSERT INTO `scholarship_applications` (`application_id`, `student_id`, `scholarship_id`, `application_date`, `status`, `reviewed_by`, `remarks`) VALUES
(21, 1, 1, '2026-07-01', 'Approved', 1, 'Excellent academic performance'),
(22, 2, 1, '2026-07-02', 'Pending', NULL, ''),
(23, 3, 2, '2026-07-03', 'Rejected', 1, 'CGPA below requirement'),
(24, 4, 4, '2026-07-04', 'Approved', 1, 'Outstanding research work'),
(25, 5, 5, '2026-07-05', 'Pending', NULL, ''),
(26, 6, 1, '2026-07-06', 'Approved', 1, 'Top performer'),
(27, 7, 2, '2026-07-07', 'Rejected', 1, 'Incomplete documents'),
(28, 8, 3, '2026-07-08', 'Pending', NULL, '');

-- --------------------------------------------------------

--
-- Table structure for table `students`
--

CREATE TABLE `students` (
  `student_id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `department` varchar(50) NOT NULL,
  `semester` int(11) NOT NULL,
  `cgpa` decimal(3,2) NOT NULL,
  `date_of_birth` date DEFAULT NULL,
  `phone` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `students`
--

INSERT INTO `students` (`student_id`, `name`, `email`, `password`, `department`, `semester`, `cgpa`, `date_of_birth`, `phone`) VALUES
(1, 'Mostafa Morshed Rafat', 'mostafamorshedrafat@gmail.com', 'hosob89', 'CSE', 6, 3.59, '2003-02-21', '01646866801'),
(2, 'Md Salman', 'salman799@gmail.com', 'huston89', 'EEE', 5, 3.00, NULL, NULL),
(3, 'Sourov Chandra Shil', 'sourovshil@gmail.com', 'lightbeckick21', 'BBA', 4, 3.50, NULL, NULL),
(4, 'Rahim Uddin', 'rahim@gmail.com', 'rahim123', 'CSE', 5, 3.75, '2003-08-15', '01712345678'),
(5, 'John Smith', 'john@gmail.com', '1234', 'Computer Science', 5, 3.85, '2003-04-12', '01711111111'),
(6, 'Emily Johnson', 'emily@gmail.com', '1234', 'Computer Science', 7, 3.72, '2002-11-08', '01722222222'),
(7, 'Michael Brown', 'michael@gmail.com', '1234', 'Electrical Engineering', 6, 3.40, '2003-01-15', '01733333333'),
(8, 'Sarah Davis', 'sarah@gmail.com', '1234', 'Business Administration', 8, 3.91, '2002-06-25', '01744444444'),
(9, 'David Wilson', 'david@gmail.com', '1234', 'Civil Engineering', 4, 3.25, '2004-02-19', '01755555555'),
(10, 'Sophia Miller', 'sophia@gmail.com', '1234', 'Computer Science', 3, 3.96, '2004-09-10', '01766666666'),
(11, 'James Anderson', 'james@gmail.com', '1234', 'Mechanical Engineering', 5, 3.58, '2003-07-03', '01777777777'),
(12, 'Olivia Thomas', 'olivia@gmail.com', '1234', 'Computer Science', 2, 3.88, '2005-01-20', '01788888888');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `administration`
--
ALTER TABLE `administration`
  ADD PRIMARY KEY (`admin_id`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `unique_admin_email` (`email`);

--
-- Indexes for table `admins`
--
ALTER TABLE `admins`
  ADD PRIMARY KEY (`admin_id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indexes for table `scholarships`
--
ALTER TABLE `scholarships`
  ADD PRIMARY KEY (`scholarship_id`);

--
-- Indexes for table `scholarship_applications`
--
ALTER TABLE `scholarship_applications`
  ADD PRIMARY KEY (`application_id`),
  ADD UNIQUE KEY `unique_student_scholarship` (`student_id`,`scholarship_id`),
  ADD KEY `scholarship_id` (`scholarship_id`),
  ADD KEY `fk_reviewed_by` (`reviewed_by`);

--
-- Indexes for table `students`
--
ALTER TABLE `students`
  ADD PRIMARY KEY (`student_id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `administration`
--
ALTER TABLE `administration`
  MODIFY `admin_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `admins`
--
ALTER TABLE `admins`
  MODIFY `admin_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `scholarships`
--
ALTER TABLE `scholarships`
  MODIFY `scholarship_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `scholarship_applications`
--
ALTER TABLE `scholarship_applications`
  MODIFY `application_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT for table `students`
--
ALTER TABLE `students`
  MODIFY `student_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `scholarship_applications`
--
ALTER TABLE `scholarship_applications`
  ADD CONSTRAINT `fk_reviewed_by` FOREIGN KEY (`reviewed_by`) REFERENCES `administration` (`admin_id`),
  ADD CONSTRAINT `scholarship_applications_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `students` (`student_id`),
  ADD CONSTRAINT `scholarship_applications_ibfk_2` FOREIGN KEY (`scholarship_id`) REFERENCES `scholarships` (`scholarship_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
