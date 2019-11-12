-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 12-11-2019 a las 22:54:45
-- Versión del servidor: 10.4.8-MariaDB
-- Versión de PHP: 7.3.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `clubpadel`
--
CREATE DATABASE IF NOT EXISTS `clubpadel` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `clubpadel`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `calendario`
--

DROP TABLE IF EXISTS `calendario`;
CREATE TABLE IF NOT EXISTS `calendario` (
  `fecha` date NOT NULL,
  `pista` int(2) NOT NULL,
  `disponibilidad` tinyint(1) NOT NULL,
  `hora` varchar(5) NOT NULL,
  PRIMARY KEY (`fecha`,`pista`,`hora`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- RELACIONES PARA LA TABLA `calendario`:
--

--
-- Volcado de datos para la tabla `calendario`
--

INSERT INTO `calendario` (`fecha`, `pista`, `disponibilidad`, `hora`) VALUES
('2019-10-01', 1, 1, '14:00'),
('2019-10-01', 1, 1, '15:00'),
('2019-10-01', 1, 0, '20:00'),
('2019-10-01', 2, 0, '18:00'),
('2019-10-01', 2, 1, '19:00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `campeonato`
--

DROP TABLE IF EXISTS `campeonato`;
CREATE TABLE IF NOT EXISTS `campeonato` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `categoria` varchar(30) NOT NULL,
  `nivel` varchar(10) NOT NULL,
  `numParticipantes` int(5) NOT NULL,
  `precioInscripcion` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

--
-- RELACIONES PARA LA TABLA `campeonato`:
--

--
-- Volcado de datos para la tabla `campeonato`
--

INSERT INTO `campeonato` (`id`, `categoria`, `nivel`, `numParticipantes`, `precioInscripcion`) VALUES
(1, 'A', '1', 50, 5),
(2, 'B', '1', 20, 3),
(3, 'A', '2', 50, 4),
(4, 'B', '2', 10, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `campeonato2`
--

DROP TABLE IF EXISTS `campeonato2`;
CREATE TABLE IF NOT EXISTS `campeonato2` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `categoria` enum('Absoluto','Benjamin','Alevin','Juvenil','Veteranos','Leyendas') NOT NULL,
  `nivel` enum('Open','Basico','Medio','Alto','Profesional') NOT NULL,
  `limite_inscripcion` date DEFAULT NULL,
  `num_participantes` int(11) DEFAULT NULL,
  `precio_inscripcion` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

--
-- RELACIONES PARA LA TABLA `campeonato2`:
--

--
-- Volcado de datos para la tabla `campeonato2`
--

INSERT INTO `campeonato2` (`id`, `categoria`, `nivel`, `limite_inscripcion`, `num_participantes`, `precio_inscripcion`) VALUES
(1, 'Benjamin', 'Basico', '2019-11-23', 5, 3.25),
(2, 'Veteranos', 'Open', '2019-11-30', 10, 10.55),
(3, 'Absoluto', 'Open', '2019-11-25', 0, 2),
(4, 'Absoluto', 'Open', '2019-11-01', 0, 40),
(5, 'Absoluto', 'Open', '2019-11-30', 0, 555);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `enfrentamiento`
--

DROP TABLE IF EXISTS `enfrentamiento`;
CREATE TABLE IF NOT EXISTS `enfrentamiento` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `resultado` varchar(20) NOT NULL,
  `campeonatoId` int(10) NOT NULL,
  `reservaId` int(10) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_enfrentamiento_campeonatoId` (`campeonatoId`),
  KEY `fk_enfrentamiento_reservaId` (`reservaId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

--
-- RELACIONES PARA LA TABLA `enfrentamiento`:
--   `campeonatoId`
--       `campeonato` -> `id`
--   `reservaId`
--       `reserva` -> `id`
--

--
-- Volcado de datos para la tabla `enfrentamiento`
--

INSERT INTO `enfrentamiento` (`id`, `resultado`, `campeonatoId`, `reservaId`) VALUES
(1, '5-0', 1, 3),
(2, '3-2', 2, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `notificacion`
--

DROP TABLE IF EXISTS `notificacion`;
CREATE TABLE IF NOT EXISTS `notificacion` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `campeonatoId` int(10) NOT NULL,
  `partidoId` int(10) NOT NULL,
  `mensaje` text NOT NULL,
  `usuarioId` int(10) NOT NULL,
  `aceptado` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_notificacion_campeonatoId` (`campeonatoId`),
  KEY `fk_notificacion_partidoId` (`partidoId`),
  KEY `fk_notificacion_usuarioId` (`usuarioId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

--
-- RELACIONES PARA LA TABLA `notificacion`:
--   `campeonatoId`
--       `campeonato` -> `id`
--   `partidoId`
--       `enfrentamiento` -> `id`
--   `usuarioId`
--       `usuario` -> `id`
--

--
-- Volcado de datos para la tabla `notificacion`
--

INSERT INTO `notificacion` (`id`, `campeonatoId`, `partidoId`, `mensaje`, `usuarioId`, `aceptado`) VALUES
(1, 1, 2, 'Aceptas el partido?', 2, 0),
(2, 2, 1, 'Aceptas el partido?', 3, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `partido`
--

DROP TABLE IF EXISTS `partido`;
CREATE TABLE IF NOT EXISTS `partido` (
  `id` int(10) NOT NULL,
  `tipo` int(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- RELACIONES PARA LA TABLA `partido`:
--

--
-- Volcado de datos para la tabla `partido`
--

INSERT INTO `partido` (`id`, `tipo`) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reserva`
--

DROP TABLE IF EXISTS `reserva`;
CREATE TABLE IF NOT EXISTS `reserva` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `usuario` int(10) NOT NULL,
  `calendario_fecha` date NOT NULL,
  `calendario_pista` int(2) NOT NULL,
  `calendario_hora` varchar(5) NOT NULL,
  `partidoId` int(10) NOT NULL,
  `fecha` date NOT NULL,
  `fechaHoraInicio` datetime NOT NULL DEFAULT current_timestamp(),
  `fechaHoraFin` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_reserva_calendario` (`calendario_fecha`,`calendario_pista`,`calendario_hora`),
  KEY `fk_reserva_usuarioId` (`usuario`) USING BTREE,
  KEY `fk_reserva_partidoId` (`partidoId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

--
-- RELACIONES PARA LA TABLA `reserva`:
--   `calendario_fecha`
--       `calendario` -> `fecha`
--   `calendario_pista`
--       `calendario` -> `pista`
--   `calendario_hora`
--       `calendario` -> `hora`
--   `partidoId`
--       `partido` -> `id`
--   `usuario`
--       `usuario` -> `id`
--

--
-- Volcado de datos para la tabla `reserva`
--

INSERT INTO `reserva` (`id`, `usuario`, `calendario_fecha`, `calendario_pista`, `calendario_hora`, `partidoId`, `fecha`, `fechaHoraInicio`, `fechaHoraFin`) VALUES
(2, 2, '2019-10-01', 1, '20:00', 1, '0000-00-00', '2019-11-11 21:17:56', NULL),
(3, 1, '2019-10-01', 1, '14:00', 2, '0000-00-00', '2019-11-11 21:17:56', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reserva2`
--

DROP TABLE IF EXISTS `reserva2`;
CREATE TABLE IF NOT EXISTS `reserva2` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pista` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `hora` enum('10','11','12','13','14','15','16','17','18','19','20','21') NOT NULL,
  `disponible` tinyint(1) NOT NULL DEFAULT 1,
  `usuario_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5j88q93ifvm1n1ygat4cor7ov` (`usuario_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4;

--
-- RELACIONES PARA LA TABLA `reserva2`:
--   `usuario_id`
--       `usuario2` -> `id`
--

--
-- Volcado de datos para la tabla `reserva2`
--

INSERT INTO `reserva2` (`id`, `pista`, `fecha`, `hora`, `disponible`, `usuario_id`) VALUES
(1, 0, '2019-11-24', '19', 0, 1),
(3, 5, '2019-11-24', '10', 1, 1),
(4, 0, '2019-11-24', '11', 1, NULL),
(5, 0, '2019-11-24', '21', 0, 1),
(6, 0, '2019-11-24', '13', 1, NULL),
(7, 0, '2019-11-24', '14', 1, NULL),
(8, 3, '2019-11-24', '15', 1, NULL),
(9, 0, '2019-11-24', '16', 1, NULL),
(10, 0, '2019-11-24', '17', 1, NULL),
(11, 0, '2019-11-24', '18', 1, NULL),
(12, 0, '2019-11-24', '19', 1, NULL),
(13, 0, '2019-11-24', '20', 1, NULL),
(14, 0, '2019-11-24', '21', 1, NULL),
(15, 1, '2019-11-05', '14', 1, 11);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

DROP TABLE IF EXISTS `usuario`;
CREATE TABLE IF NOT EXISTS `usuario` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `apellidos` varchar(50) NOT NULL,
  `dni` varchar(9) NOT NULL,
  `fechaNacimiento` date NOT NULL,
  `administrador` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

--
-- RELACIONES PARA LA TABLA `usuario`:
--

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id`, `nombre`, `apellidos`, `dni`, `fechaNacimiento`, `administrador`) VALUES
(1, 'admin', 'adm', '45158726C', '2019-10-08', 1),
(2, 'Alex', 'Curras Rodri', '12345678D', '2019-10-29', 0),
(3, 'Dani', 'Duque Puga', '11122233T', '2019-10-22', 0),
(4, 'Fernando', 'x f', '11122244R', '2019-10-22', 0),
(5, 'David', 'v f', '33344455U', '2019-10-30', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario2`
--

DROP TABLE IF EXISTS `usuario2`;
CREATE TABLE IF NOT EXISTS `usuario2` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(30) NOT NULL,
  `apellidos` varchar(50) NOT NULL,
  `dni` varchar(9) DEFAULT NULL,
  `password` varchar(10) NOT NULL,
  `telefono` int(11) DEFAULT NULL,
  `administrador` tinyint(1) DEFAULT NULL,
  `fecha_nacimiento` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4;

--
-- RELACIONES PARA LA TABLA `usuario2`:
--

--
-- Volcado de datos para la tabla `usuario2`
--

INSERT INTO `usuario2` (`id`, `nombre`, `apellidos`, `dni`, `password`, `telefono`, `administrador`, `fecha_nacimiento`) VALUES
(1, 'Alex', 'Curras', '45158726E', '', 123456789, 0, '2019-11-03'),
(4, '12', '312', '132', '', 78998987, 0, '2019-11-07'),
(6, 'a', 'a', 'a', '', 0, 0, NULL),
(7, 'b', 'b', 'b', '', 0, 0, NULL),
(8, 'c', 'c', 'c', '', 0, 0, NULL),
(11, 'rueb', 'rueb', '45158726D', '', 697217053, 0, '2019-11-01');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario_partido_campeonato`
--

DROP TABLE IF EXISTS `usuario_partido_campeonato`;
CREATE TABLE IF NOT EXISTS `usuario_partido_campeonato` (
  `usuarioId` int(10) NOT NULL,
  `enfrentamientoId` int(10) NOT NULL,
  `campeonatoId` int(10) NOT NULL,
  PRIMARY KEY (`usuarioId`,`enfrentamientoId`,`campeonatoId`),
  KEY `fk_campeonatoId_usuario_partido_campeonato` (`campeonatoId`) USING BTREE,
  KEY `fk_usuario_partido_campeonato_enfrentamientoId` (`enfrentamientoId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- RELACIONES PARA LA TABLA `usuario_partido_campeonato`:
--   `campeonatoId`
--       `campeonato` -> `id`
--   `enfrentamientoId`
--       `enfrentamiento` -> `id`
--   `usuarioId`
--       `usuario` -> `id`
--

--
-- Volcado de datos para la tabla `usuario_partido_campeonato`
--

INSERT INTO `usuario_partido_campeonato` (`usuarioId`, `enfrentamientoId`, `campeonatoId`) VALUES
(2, 2, 1),
(3, 1, 2),
(4, 1, 2),
(5, 2, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario_reserva`
--

DROP TABLE IF EXISTS `usuario_reserva`;
CREATE TABLE IF NOT EXISTS `usuario_reserva` (
  `usuario` bigint(20) NOT NULL,
  `reserva` bigint(20) NOT NULL,
  `tipo` enum('normal','promocionado','ofertado','campeonato') NOT NULL,
  `jugador1` bigint(11) NOT NULL,
  `jugador2` bigint(11) NOT NULL,
  `jugador3` bigint(11) NOT NULL,
  KEY `fk_j1` (`jugador1`),
  KEY `fk_j2` (`jugador2`),
  KEY `fk_j3` (`jugador3`),
  KEY `fk_reserva` (`reserva`),
  KEY `fk_usuario2` (`usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- RELACIONES PARA LA TABLA `usuario_reserva`:
--   `jugador1`
--       `usuario2` -> `id`
--   `jugador2`
--       `usuario2` -> `id`
--   `jugador3`
--       `usuario2` -> `id`
--   `reserva`
--       `reserva2` -> `id`
--   `usuario`
--       `usuario2` -> `id`
--

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `enfrentamiento`
--
ALTER TABLE `enfrentamiento`
  ADD CONSTRAINT `fk_enfrentamiento_campeonatoId` FOREIGN KEY (`campeonatoId`) REFERENCES `campeonato` (`id`),
  ADD CONSTRAINT `fk_enfrentamiento_reservaId` FOREIGN KEY (`reservaId`) REFERENCES `reserva` (`id`);

--
-- Filtros para la tabla `notificacion`
--
ALTER TABLE `notificacion`
  ADD CONSTRAINT `fk_notificacion_campeonatoId` FOREIGN KEY (`campeonatoId`) REFERENCES `campeonato` (`id`),
  ADD CONSTRAINT `fk_notificacion_partidoId` FOREIGN KEY (`partidoId`) REFERENCES `enfrentamiento` (`id`),
  ADD CONSTRAINT `fk_notificacion_usuarioId` FOREIGN KEY (`usuarioId`) REFERENCES `usuario` (`id`);

--
-- Filtros para la tabla `reserva`
--
ALTER TABLE `reserva`
  ADD CONSTRAINT `fk_reserva_calendario` FOREIGN KEY (`calendario_fecha`,`calendario_pista`,`calendario_hora`) REFERENCES `calendario` (`fecha`, `pista`, `hora`),
  ADD CONSTRAINT `fk_reserva_partidoId` FOREIGN KEY (`partidoId`) REFERENCES `partido` (`id`),
  ADD CONSTRAINT `fk_reserva_usuarioId` FOREIGN KEY (`usuario`) REFERENCES `usuario` (`id`);

--
-- Filtros para la tabla `reserva2`
--
ALTER TABLE `reserva2`
  ADD CONSTRAINT `FK5j88q93ifvm1n1ygat4cor7ov` FOREIGN KEY (`usuario_id`) REFERENCES `usuario2` (`id`);

--
-- Filtros para la tabla `usuario_partido_campeonato`
--
ALTER TABLE `usuario_partido_campeonato`
  ADD CONSTRAINT `fk_usuario_partido_campeonato_campeonatoId` FOREIGN KEY (`campeonatoId`) REFERENCES `campeonato` (`id`),
  ADD CONSTRAINT `fk_usuario_partido_campeonato_enfrentamientoId` FOREIGN KEY (`enfrentamientoId`) REFERENCES `enfrentamiento` (`id`),
  ADD CONSTRAINT `fk_usuario_partido_campeonato_usuarioId` FOREIGN KEY (`usuarioId`) REFERENCES `usuario` (`id`);

--
-- Filtros para la tabla `usuario_reserva`
--
ALTER TABLE `usuario_reserva`
  ADD CONSTRAINT `fk_j1` FOREIGN KEY (`jugador1`) REFERENCES `usuario2` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_j2` FOREIGN KEY (`jugador2`) REFERENCES `usuario2` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_j3` FOREIGN KEY (`jugador3`) REFERENCES `usuario2` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_reserva` FOREIGN KEY (`reserva`) REFERENCES `reserva2` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_usuario2` FOREIGN KEY (`usuario`) REFERENCES `usuario2` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;


--
-- Metadatos
--
USE `phpmyadmin`;

--
-- Metadatos para la tabla calendario
--

--
-- Metadatos para la tabla campeonato
--

--
-- Metadatos para la tabla campeonato2
--

--
-- Metadatos para la tabla enfrentamiento
--

--
-- Metadatos para la tabla notificacion
--

--
-- Metadatos para la tabla partido
--

--
-- Metadatos para la tabla reserva
--

--
-- Metadatos para la tabla reserva2
--

--
-- Metadatos para la tabla usuario
--

--
-- Metadatos para la tabla usuario2
--

--
-- Metadatos para la tabla usuario_partido_campeonato
--

--
-- Metadatos para la tabla usuario_reserva
--

--
-- Metadatos para la base de datos clubpadel
--
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
