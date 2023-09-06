-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Ago 31, 2023 alle 17:15
-- Versione del server: 10.4.28-MariaDB
-- Versione PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `megliodellavisa`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `carta`
--

CREATE TABLE `carta` (
  `id` int(15) NOT NULL,
  `numeroCarta` varchar(15) NOT NULL,
  `attiva` tinyint(1) NOT NULL,
  `creditoResiduo` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `carta`
--

INSERT INTO `carta` (`id`, `numeroCarta`, `attiva`, `creditoResiduo`) VALUES
(1, '111111111', 0, 1163),
(2, '111111112', 1, 150),
(3, '111111113', 1, 10),
(4, '111111114', 1, 10),
(5, '111111115', 1, 10000);

-- --------------------------------------------------------

--
-- Struttura della tabella `transazione`
--

CREATE TABLE `transazione` (
  `id` int(10) NOT NULL,
  `utente` varchar(255) NOT NULL,
  `timestamp` double NOT NULL,
  `operazione` varchar(15) NOT NULL,
  `numerocarta` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `transazione`
--

INSERT INTO `transazione` (`id`, `utente`, `timestamp`, `operazione`, `numerocarta`) VALUES
(1, 'admin@admin.it', 1693401144846, '100', '111111111'),
(2, 'admin@admin.it', 1693401156477, '-100', '111111111'),
(3, 'admin@admin.it', 1693474043583, '-100', '111111111'),
(4, 'admin@admin.it', 1693474049449, '1000', '111111111'),
(5, 'admin@admin.it', 1693493561366, '-11', '111111111'),
(6, 'negozio@negozio.it', 1693494296677, '12', '111111111');

-- --------------------------------------------------------

--
-- Struttura della tabella `utente`
--

CREATE TABLE `utente` (
  `id` int(10) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `cognome` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `ruolo` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dump dei dati per la tabella `utente`
--

INSERT INTO `utente` (`id`, `nome`, `cognome`, `email`, `password`, `ruolo`) VALUES
(1, 'Admin', 'Admin', 'admin@admin.it', '5f4dcc3b5aa765d61d8327deb882cf99', 'admin'),
(2, 'negozio', 'negozio', 'negozio@negozio.it', '5f4dcc3b5aa765d61d8327deb882cf99', 'negoziante'),
(3, 'utente', 'utente', 'utente@utente.it', '5f4dcc3b5aa765d61d8327deb882cf99', 'utente');

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `carta`
--
ALTER TABLE `carta`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `transazione`
--
ALTER TABLE `transazione`
  ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `utente`
--
ALTER TABLE `utente`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `carta`
--
ALTER TABLE `carta`
  MODIFY `id` int(15) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT per la tabella `transazione`
--
ALTER TABLE `transazione`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT per la tabella `utente`
--
ALTER TABLE `utente`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
