-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versione server:              10.4.32-MariaDB - mariadb.org binary distribution
-- S.O. server:                  Win64
-- HeidiSQL Versione:            12.5.0.6677
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dump della struttura del database morracineseadvanced
DROP DATABASE IF EXISTS `morracineseadvanced`;
CREATE DATABASE IF NOT EXISTS `morracineseadvanced` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `morracineseadvanced`;

-- Dump della struttura di tabella morracineseadvanced.friendrequests
DROP TABLE IF EXISTS `friendrequests`;
CREATE TABLE IF NOT EXISTS `friendrequests` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `senderId` varchar(200) DEFAULT NULL,
  `receiverId` varchar(200) DEFAULT NULL,
  `status` enum('pending','accepted','rejected') DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_friendrequests_users` (`senderId`),
  KEY `FK_friendrequests_users_2` (`receiverId`),
  CONSTRAINT `FK_friendrequests_users` FOREIGN KEY (`senderId`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_friendrequests_users_2` FOREIGN KEY (`receiverId`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dump dei dati della tabella morracineseadvanced.friendrequests: ~0 rows (circa)
DELETE FROM `friendrequests`;

-- Dump della struttura di tabella morracineseadvanced.friends
DROP TABLE IF EXISTS `friends`;
CREATE TABLE IF NOT EXISTS `friends` (
  `userId` varchar(200) NOT NULL DEFAULT '',
  `friendId` varchar(200) NOT NULL DEFAULT '',
  PRIMARY KEY (`userId`,`friendId`) USING BTREE,
  KEY `FK_friends_users_2` (`friendId`) USING BTREE,
  CONSTRAINT `FK_friends_users` FOREIGN KEY (`userID`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_friends_users_2` FOREIGN KEY (`friendID`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dump dei dati della tabella morracineseadvanced.friends: ~0 rows (circa)
DELETE FROM `friends`;

-- Dump della struttura di tabella morracineseadvanced.matches
DROP TABLE IF EXISTS `matches`;
CREATE TABLE IF NOT EXISTS `matches` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `player1Id` varchar(200) DEFAULT NULL,
  `player2Id` varchar(200) DEFAULT NULL,
  `player1Move` varchar(50) DEFAULT NULL,
  `player2Move` varchar(50) DEFAULT NULL,
  `winnerId` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_matches_users` (`player1Id`),
  KEY `FK_matches_users_2` (`player2Id`),
  KEY `FK_matches_users_3` (`winnerId`),
  CONSTRAINT `FK_matches_users` FOREIGN KEY (`player1Id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_matches_users_2` FOREIGN KEY (`player2Id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_matches_users_3` FOREIGN KEY (`winnerId`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dump dei dati della tabella morracineseadvanced.matches: ~0 rows (circa)
DELETE FROM `matches`;

-- Dump della struttura di tabella morracineseadvanced.users
DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id` varchar(200) NOT NULL DEFAULT 'AUTO_INCREMENT',
  `username` varchar(50) DEFAULT NULL,
  `elo` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dump dei dati della tabella morracineseadvanced.users: ~0 rows (circa)
DELETE FROM `users`;

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
