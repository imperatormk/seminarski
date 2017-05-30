-- phpMyAdmin SQL Dump
-- version 4.6.6
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: May 30, 2017 at 10:53 PM
-- Server version: 5.7.17-log
-- PHP Version: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `testdb2`
--

-- --------------------------------------------------------

--
-- Table structure for table `subjects`
--

CREATE TABLE `subjects` (
  `ID` int(11) NOT NULL,
  `title` varchar(50) NOT NULL,
  `teacherID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `subjects`
--

INSERT INTO `subjects` (`ID`, `title`, `teacherID`) VALUES
(5, 'sub1', 3),
(6, 'sub2', 5);

-- --------------------------------------------------------

--
-- Table structure for table `tokens`
--

CREATE TABLE `tokens` (
  `ID` binary(16) NOT NULL,
  `userID` int(11) NOT NULL,
  `expiryDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tokens`
--

INSERT INTO `tokens` (`ID`, `userID`, `expiryDate`) VALUES
(0x4351e65d8e3e4df2aa2fefbd03e13c53, 1, '1970-01-01 01:00:01'),
(0x4cd86e87442542ac9ac36700b369ebcd, 1, '1970-01-01 01:00:01'),
(0xd1be64fc2fb7409283c25878f887a9cb, 3, '2017-06-02 00:42:29');

-- --------------------------------------------------------

--
-- Table structure for table `uploads`
--

CREATE TABLE `uploads` (
  `ID` int(11) NOT NULL,
  `title` varchar(50) NOT NULL,
  `studentID` int(11) NOT NULL,
  `workID` int(11) NOT NULL,
  `storeURL` varchar(255) NOT NULL,
  `subDate` datetime DEFAULT NULL,
  `state` tinyint(4) NOT NULL DEFAULT '0',
  `grade` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `uploads`
--

INSERT INTO `uploads` (`ID`, `title`, `studentID`, `workID`, `storeURL`, `subDate`, `state`, `grade`) VALUES
(1, 'realUpload9a', 4, 9, 'F:/DWUploads/1.png', '2017-05-30 23:48:40', 0, 0),
(5, 'testName', 1, 10, 'F:/DWUploads/5.png', '2017-05-31 00:17:57', 0, 0),
(6, 'realUpload9b', 1, 9, 'F:/DWUploads/3.png', '2017-05-31 00:41:00', 0, 0),
(7, 'testName2', 4, 10, 'F:/DWUploads/7.png', '2017-05-31 00:29:44', 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `ID` int(5) NOT NULL,
  `role` varchar(10) NOT NULL,
  `uName` varchar(20) NOT NULL,
  `pass` varchar(100) NOT NULL,
  `fName` varchar(20) NOT NULL,
  `lName` varchar(20) NOT NULL,
  `email` varchar(40) DEFAULT NULL,
  `stuNo` int(11) NOT NULL,
  `stuProg` varchar(20) NOT NULL,
  `title` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`ID`, `role`, `uName`, `pass`, `fName`, `lName`, `email`, `stuNo`, `stuProg`, `title`) VALUES
(1, 'student', 'tomskibt', 'pece123!', 'Tome', 'Kiriazi', 'tome@hotmail.com', 96, 'INKI', ''),
(3, 'teacher', 'imperatormk', 'pece123!', 'Darko', 'Simonovski', 'darko.simonovski@hotmail.com', 0, '', 'Dr.'),
(4, 'student', 'dengej', 'pece123!', 'Damjan', 'Simonovski', 'damjan.simonovski@hotmail.com', 150, 'INKI', ''),
(5, 'teacher', 'probfixer', 'pece123!', 'Prob', 'Fixer', 'prob@hotmail.com', 0, '', 'Mr.');

-- --------------------------------------------------------

--
-- Table structure for table `works`
--

CREATE TABLE `works` (
  `ID` int(11) NOT NULL,
  `title` varchar(100) NOT NULL,
  `description` text NOT NULL,
  `subjectID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `works`
--

INSERT INTO `works` (`ID`, `title`, `description`, `subjectID`) VALUES
(9, 'work1', 'work1', 5),
(10, 'work2', 'work2', 6);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `subjects`
--
ALTER TABLE `subjects`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `teacherID` (`teacherID`);

--
-- Indexes for table `tokens`
--
ALTER TABLE `tokens`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `userID` (`userID`);

--
-- Indexes for table `uploads`
--
ALTER TABLE `uploads`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `workID` (`workID`),
  ADD KEY `studentID` (`studentID`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `works`
--
ALTER TABLE `works`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `subjectID` (`subjectID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `subjects`
--
ALTER TABLE `subjects`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `uploads`
--
ALTER TABLE `uploads`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `ID` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `works`
--
ALTER TABLE `works`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `subjects`
--
ALTER TABLE `subjects`
  ADD CONSTRAINT `teacherID` FOREIGN KEY (`teacherID`) REFERENCES `users` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `tokens`
--
ALTER TABLE `tokens`
  ADD CONSTRAINT `userID` FOREIGN KEY (`userID`) REFERENCES `users` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `uploads`
--
ALTER TABLE `uploads`
  ADD CONSTRAINT `studentID` FOREIGN KEY (`studentID`) REFERENCES `users` (`ID`),
  ADD CONSTRAINT `workID` FOREIGN KEY (`workID`) REFERENCES `works` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `works`
--
ALTER TABLE `works`
  ADD CONSTRAINT `subjectID` FOREIGN KEY (`subjectID`) REFERENCES `subjects` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
