-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : mar. 25 juin 2024 à 09:00
-- Version du serveur : 8.0.31
-- Version de PHP : 7.4.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `bibliotheque`
--

-- --------------------------------------------------------

--
-- Structure de la table `auteur`
--

DROP TABLE IF EXISTS `auteur`;
CREATE TABLE IF NOT EXISTS `auteur` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(30) NOT NULL,
  `email` varchar(30) NOT NULL,
  `sexe` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `specialites` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=MyISAM AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `auteur`
--

INSERT INTO `auteur` (`Id`, `nom`, `email`, `sexe`, `specialites`) VALUES
(5, 'HH', 'rebe@gmail.com', 'M', 'Informatique'),
(22, 'wdcfqyi', 'efugey', 'M', 'Genie_Logiciel'),
(18, 'boo', 'bubu@gmail.com', 'M', 'Informatique'),
(19, 'boo', 'bubu@gmail.com', 'M', 'Informatique'),
(20, 'boo', 'bubu@gmail.com', 'F', 'Genie_Logiciel');

-- --------------------------------------------------------

--
-- Structure de la table `livres`
--

DROP TABLE IF EXISTS `livres`;
CREATE TABLE IF NOT EXISTS `livres` (
  `id` int NOT NULL AUTO_INCREMENT,
  `titre` varchar(789) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `date_publication` date NOT NULL,
  `nbres_pages` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `livres`
--

INSERT INTO `livres` (`id`, `titre`, `date_publication`, `nbres_pages`) VALUES
(38, 'the iuytrsdf', '2024-06-21', 678),
(37, 'waiting for a day break', '2024-06-09', 678);

-- --------------------------------------------------------

--
-- Structure de la table `publication`
--

DROP TABLE IF EXISTS `publication`;
CREATE TABLE IF NOT EXISTS `publication` (
  `id` int NOT NULL AUTO_INCREMENT,
  `idAuteur` int NOT NULL,
  `idLivre` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `publication`
--

INSERT INTO `publication` (`id`, `idAuteur`, `idLivre`) VALUES
(1, 8, 37),
(8, 5, 42),
(7, 5, 42),
(9, 5, 42),
(10, 5, 42),
(11, 5, 42),
(12, 5, 42),
(13, 5, 42),
(14, 5, 42),
(15, 5, 42),
(16, 5, 42),
(17, 5, 42),
(18, 22, 38),
(19, 22, 38),
(20, 5, 38),
(21, 18, 38);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
