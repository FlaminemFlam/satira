-- phpMyAdmin SQL Dump
-- version 5.1.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Creato il: Mag 09, 2025 alle 13:00
-- Versione del server: 5.7.24
-- Versione PHP: 7.4.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `satira`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `admin`
--

CREATE TABLE `admin` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `nickname` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `img` longtext
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `admin`
--

INSERT INTO `admin` (`id`, `username`, `nickname`, `password`, `img`) VALUES
(1, 'lucap', 'LucaPower', 'password321', 'https://esempio.com/img/luca.jpg'),
(2, 'marior', 'MarioRock', 'supersecure', 'https://esempio.com/img/mario.jpg');

-- --------------------------------------------------------

--
-- Struttura della tabella `commenti`
--

CREATE TABLE `commenti` (
  `id` int(11) NOT NULL,
  `testo` varchar(255) DEFAULT NULL,
  `immagine_commento` longtext,
  `data_commento` datetime NOT NULL,
  `fk_id_post` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dump dei dati per la tabella `commenti`
--

INSERT INTO `commenti` (`id`, `testo`, `immagine_commento`, `data_commento`, `fk_id_post`) VALUES
(1, 'Questo articolo è semplicemente geniale! La parte sulla satira politica mi ha fatto piangere dal ridere. Continua così!', 'https://esempio.com/images/reaction_laugh.gif', '2024-06-10 10:15:00', 1),
(2, 'Analisi perfetta della situazione! Mi è piaciuta soprattutto la parte in cui confronti la realtà con la satira. Attendo il prossimo articolo!', NULL, '2024-06-10 16:30:00', 2);

-- --------------------------------------------------------

--
-- Struttura della tabella `post`
--

CREATE TABLE `post` (
  `id` int(11) NOT NULL,
  `immagine_post` longtext,
  `titolo` varchar(100) NOT NULL,
  `contenuto` text,
  `data_pubblicazione` datetime NOT NULL,
  `fk_id_admin` int(11) NOT NULL,
  `visible` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `post`
--

INSERT INTO `post` (`id`, `immagine_post`, `titolo`, `contenuto`, `data_pubblicazione`, `fk_id_admin`, `visible`) VALUES
(1, 'https://esempio.com/images/post_satirico1.jpg', 'La satira che non ti aspetti', 'Un\'analisi ironica e dissacrante dei luoghi comuni che tutti amiamo odiare. Il nostro personaggio questa volta ha davvero esagerato!', '2024-06-10 09:30:00', 1, 0),
(2, 'https://esempio.com/images/meme_satirico.png', 'Ridere per non piangere: guida alla sopravvivenza', 'Una raccolta delle situazioni più assurde che il nostro protagonista ha affrontato negli ultimi mesi. Con commenti esilaranti e riflessioni pungenti.', '2024-06-10 15:45:00', 2, 0);

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `nikname` (`nickname`);

--
-- Indici per le tabelle `commenti`
--
ALTER TABLE `commenti`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_id_post` (`fk_id_post`);

--
-- Indici per le tabelle `post`
--
ALTER TABLE `post`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_id_admin` (`fk_id_admin`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT per la tabella `commenti`
--
ALTER TABLE `commenti`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT per la tabella `post`
--
ALTER TABLE `post`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `commenti`
--
ALTER TABLE `commenti`
  ADD CONSTRAINT `commenti_ibfk_1` FOREIGN KEY (`fk_id_post`) REFERENCES `post` (`id`);

--
-- Limiti per la tabella `post`
--
ALTER TABLE `post`
  ADD CONSTRAINT `post_ibfk_1` FOREIGN KEY (`fk_id_admin`) REFERENCES `admin` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
