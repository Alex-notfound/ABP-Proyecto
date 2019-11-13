-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 13-11-2019 a las 16:56:02
-- Versión del servidor: 10.4.8-MariaDB
-- Versión de PHP: 7.3.11

SET FOREIGN_KEY_CHECKS=0;
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
-- Estructura de tabla para la tabla `campeonato2`
--

DROP TABLE IF EXISTS `campeonato2`;
CREATE TABLE `campeonato2` (
  `id` bigint(20) NOT NULL,
  `categoria` enum('Absoluto','Benjamin','Alevin','Juvenil','Veteranos','Leyendas') NOT NULL,
  `nivel` enum('Open','Basico','Medio','Alto','Profesional') NOT NULL,
  `limite_inscripcion` date DEFAULT NULL,
  `num_participantes` int(11) DEFAULT NULL,
  `precio_inscripcion` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
CREATE TABLE `enfrentamiento` (
  `id` int(10) NOT NULL,
  `resultado` varchar(20) NOT NULL,
  `campeonatoId` int(10) NOT NULL,
  `reservaId` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
CREATE TABLE `notificacion` (
  `id` int(10) NOT NULL,
  `campeonatoId` int(10) NOT NULL,
  `partidoId` int(10) NOT NULL,
  `mensaje` text NOT NULL,
  `usuarioId` int(10) NOT NULL,
  `aceptado` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
CREATE TABLE `partido` (
  `id` int(10) NOT NULL,
  `tipo` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
-- Estructura de tabla para la tabla `pista`
--

DROP TABLE IF EXISTS `pista`;
CREATE TABLE `pista` (
  `id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `pista`
--

INSERT INTO `pista` (`id`) VALUES
(1),
(2),
(3),
(4),
(5);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reserva2`
--

DROP TABLE IF EXISTS `reserva2`;
CREATE TABLE `reserva2` (
  `id` bigint(20) NOT NULL,
  `pista` bigint(20) NOT NULL,
  `fecha` date NOT NULL,
  `hora` enum('10.00','11.00','12.00','13.00','14.00','15.00','16.00','17.00','18.00','19.00','20.00') NOT NULL,
  `disponible` tinyint(1) NOT NULL DEFAULT 1,
  `usuario_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario2`
--

DROP TABLE IF EXISTS `usuario2`;
CREATE TABLE `usuario2` (
  `id` bigint(20) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `apellidos` varchar(50) NOT NULL,
  `dni` varchar(9) DEFAULT NULL,
  `password` varchar(10) NOT NULL,
  `telefono` int(11) DEFAULT NULL,
  `administrador` tinyint(1) DEFAULT NULL,
  `fecha_nacimiento` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
CREATE TABLE `usuario_partido_campeonato` (
  `usuarioId` int(10) NOT NULL,
  `enfrentamientoId` int(10) NOT NULL,
  `campeonatoId` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `usuario_partido_campeonato`
--

INSERT INTO `usuario_partido_campeonato` (`usuarioId`, `enfrentamientoId`, `campeonatoId`) VALUES
(2, 2, 1),
(3, 1, 2),
(4, 1, 2),
(5, 2, 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `campeonato2`
--
ALTER TABLE `campeonato2`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `enfrentamiento`
--
ALTER TABLE `enfrentamiento`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_enfrentamiento_campeonatoId` (`campeonatoId`),
  ADD KEY `fk_enfrentamiento_reservaId` (`reservaId`);

--
-- Indices de la tabla `notificacion`
--
ALTER TABLE `notificacion`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_notificacion_campeonatoId` (`campeonatoId`),
  ADD KEY `fk_notificacion_partidoId` (`partidoId`),
  ADD KEY `fk_notificacion_usuarioId` (`usuarioId`);

--
-- Indices de la tabla `partido`
--
ALTER TABLE `partido`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `pista`
--
ALTER TABLE `pista`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `reserva2`
--
ALTER TABLE `reserva2`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK5j88q93ifvm1n1ygat4cor7ov` (`usuario_id`),
  ADD KEY `fk_reserva_pista` (`pista`);

--
-- Indices de la tabla `usuario2`
--
ALTER TABLE `usuario2`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `usuario_partido_campeonato`
--
ALTER TABLE `usuario_partido_campeonato`
  ADD PRIMARY KEY (`usuarioId`,`enfrentamientoId`,`campeonatoId`),
  ADD KEY `fk_campeonatoId_usuario_partido_campeonato` (`campeonatoId`) USING BTREE,
  ADD KEY `fk_usuario_partido_campeonato_enfrentamientoId` (`enfrentamientoId`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `campeonato2`
--
ALTER TABLE `campeonato2`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `enfrentamiento`
--
ALTER TABLE `enfrentamiento`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `notificacion`
--
ALTER TABLE `notificacion`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `reserva2`
--
ALTER TABLE `reserva2`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `usuario2`
--
ALTER TABLE `usuario2`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

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
-- Filtros para la tabla `reserva2`
--
ALTER TABLE `reserva2`
  ADD CONSTRAINT `FK5j88q93ifvm1n1ygat4cor7ov` FOREIGN KEY (`usuario_id`) REFERENCES `usuario2` (`id`),
  ADD CONSTRAINT `fk_reserva_pista` FOREIGN KEY (`pista`) REFERENCES `pista` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `usuario_partido_campeonato`
--
ALTER TABLE `usuario_partido_campeonato`
  ADD CONSTRAINT `fk_usuario_partido_campeonato_campeonatoId` FOREIGN KEY (`campeonatoId`) REFERENCES `campeonato` (`id`),
  ADD CONSTRAINT `fk_usuario_partido_campeonato_enfrentamientoId` FOREIGN KEY (`enfrentamientoId`) REFERENCES `enfrentamiento` (`id`),
  ADD CONSTRAINT `fk_usuario_partido_campeonato_usuarioId` FOREIGN KEY (`usuarioId`) REFERENCES `usuario` (`id`);
SET FOREIGN_KEY_CHECKS=1;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
