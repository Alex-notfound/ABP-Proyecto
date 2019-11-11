-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 11-11-2019 a las 16:39:36
-- Versión del servidor: 10.4.8-MariaDB
-- Versión de PHP: 7.3.10

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
CREATE TABLE `calendario` (
  `fecha` date NOT NULL,
  `pista` int(2) NOT NULL,
  `disponibilidad` tinyint(1) NOT NULL,
  `hora` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
CREATE TABLE `campeonato` (
  `id` int(10) NOT NULL,
  `categoria` varchar(30) NOT NULL,
  `nivel` varchar(10) NOT NULL,
  `numParticipantes` int(5) NOT NULL,
  `precioInscripcion` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
-- Estructura de tabla para la tabla `reserva`
--

DROP TABLE IF EXISTS `reserva`;
CREATE TABLE `reserva` (
  `id` int(10) NOT NULL,
  `usuario` int(10) NOT NULL,
  `calendario_fecha` date NOT NULL,
  `calendario_pista` int(2) NOT NULL,
  `calendario_hora` varchar(5) NOT NULL,
  `partidoId` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `reserva`
--

INSERT INTO `reserva` (`id`, `usuario`, `calendario_fecha`, `calendario_pista`, `calendario_hora`, `partidoId`) VALUES
(2, 2, '2019-10-01', 1, '20:00', 1),
(3, 1, '2019-10-01', 1, '14:00', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

DROP TABLE IF EXISTS `usuario`;
CREATE TABLE `usuario` (
  `id` int(10) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `apellidos` varchar(50) NOT NULL,
  `dni` varchar(9) NOT NULL,
  `fechaNacimiento` date NOT NULL,
  `administrador` tinyint(1) NOT NULL,
  `fecha_nacimiento` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id`, `nombre`, `apellidos`, `dni`, `fechaNacimiento`, `administrador`, `fecha_nacimiento`) VALUES
(1, 'admin', 'adm', '45158726C', '2019-10-08', 1, NULL),
(2, 'Alex', 'Curras Rodri', '12345678D', '2019-10-29', 0, NULL),
(3, 'Dani', 'Duque Puga', '11122233T', '2019-10-22', 0, NULL),
(4, 'Fernando', 'x f', '11122244R', '2019-10-22', 0, NULL),
(5, 'David', 'v f', '33344455U', '2019-10-30', 0, NULL);

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
-- Indices de la tabla `calendario`
--
ALTER TABLE `calendario`
  ADD PRIMARY KEY (`fecha`,`pista`,`hora`);

--
-- Indices de la tabla `campeonato`
--
ALTER TABLE `campeonato`
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
-- Indices de la tabla `reserva`
--
ALTER TABLE `reserva`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_reserva_calendario` (`calendario_fecha`,`calendario_pista`,`calendario_hora`),
  ADD KEY `fk_reserva_usuarioId` (`usuario`) USING BTREE,
  ADD KEY `fk_reserva_partidoId` (`partidoId`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
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
-- AUTO_INCREMENT de la tabla `campeonato`
--
ALTER TABLE `campeonato`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

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
-- AUTO_INCREMENT de la tabla `reserva`
--
ALTER TABLE `reserva`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

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
-- Filtros para la tabla `usuario_partido_campeonato`
--
ALTER TABLE `usuario_partido_campeonato`
  ADD CONSTRAINT `fk_usuario_partido_campeonato_campeonatoId` FOREIGN KEY (`campeonatoId`) REFERENCES `campeonato` (`id`),
  ADD CONSTRAINT `fk_usuario_partido_campeonato_enfrentamientoId` FOREIGN KEY (`enfrentamientoId`) REFERENCES `enfrentamiento` (`id`),
  ADD CONSTRAINT `fk_usuario_partido_campeonato_usuarioId` FOREIGN KEY (`usuarioId`) REFERENCES `usuario` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
