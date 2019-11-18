-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 18-11-2019 a las 15:11:54
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
-- Base de datos: `padelbd`
--
CREATE DATABASE IF NOT EXISTS `padelbd` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `padelbd`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `authorities_users`
--

DROP TABLE IF EXISTS `authorities_users`;
CREATE TABLE `authorities_users` (
  `usuario_id` bigint(20) NOT NULL,
  `authority_id` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- RELACIONES PARA LA TABLA `authorities_users`:
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `authority`
--

DROP TABLE IF EXISTS `authority`;
CREATE TABLE `authority` (
  `id` bigint(20) NOT NULL,
  `authority` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- RELACIONES PARA LA TABLA `authority`:
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `campeonato`
--

DROP TABLE IF EXISTS `campeonato`;
CREATE TABLE `campeonato` (
  `id` bigint(20) NOT NULL,
  `abierto` bit(1) NOT NULL DEFAULT b'1',
  `categoria` varchar(255) DEFAULT NULL,
  `limite_inscripcion` date DEFAULT NULL,
  `max_num_participantes` int(11) DEFAULT NULL,
  `nivel` varchar(255) DEFAULT NULL,
  `precio_inscripcion` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- RELACIONES PARA LA TABLA `campeonato`:
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `enfrentamiento`
--

DROP TABLE IF EXISTS `enfrentamiento`;
CREATE TABLE `enfrentamiento` (
  `id` bigint(20) NOT NULL,
  `resultado` varchar(255) DEFAULT '',
  `campeonato_id` bigint(20) DEFAULT NULL,
  `ganador_id` bigint(20) DEFAULT NULL,
  `pareja1_id` bigint(20) DEFAULT NULL,
  `pareja2_id` bigint(20) DEFAULT NULL,
  `reserva_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- RELACIONES PARA LA TABLA `enfrentamiento`:
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- RELACIONES PARA LA TABLA `hibernate_sequence`:
--

--
-- Volcado de datos para la tabla `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pareja`
--

DROP TABLE IF EXISTS `pareja`;
CREATE TABLE `pareja` (
  `id` bigint(20) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `miembro1_id` bigint(20) DEFAULT NULL,
  `miembro2_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- RELACIONES PARA LA TABLA `pareja`:
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pareja_campeonato`
--

DROP TABLE IF EXISTS `pareja_campeonato`;
CREATE TABLE `pareja_campeonato` (
  `puntos` int(11) NOT NULL DEFAULT 0,
  `campeonato_id` bigint(20) NOT NULL,
  `pareja_id` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- RELACIONES PARA LA TABLA `pareja_campeonato`:
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `partido`
--

DROP TABLE IF EXISTS `partido`;
CREATE TABLE `partido` (
  `id` bigint(20) NOT NULL,
  `abierto` bit(1) NOT NULL DEFAULT b'1',
  `tipo` varchar(255) DEFAULT NULL,
  `reserva_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- RELACIONES PARA LA TABLA `partido`:
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pista`
--

DROP TABLE IF EXISTS `pista`;
CREATE TABLE `pista` (
  `id` bigint(20) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `numero` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- RELACIONES PARA LA TABLA `pista`:
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reserva`
--

DROP TABLE IF EXISTS `reserva`;
CREATE TABLE `reserva` (
  `id` bigint(20) NOT NULL,
  `disponible` bit(1) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `hora` varchar(255) DEFAULT NULL,
  `pista_id` bigint(20) DEFAULT NULL,
  `usuario_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- RELACIONES PARA LA TABLA `reserva`:
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

DROP TABLE IF EXISTS `usuario`;
CREATE TABLE `usuario` (
  `id` bigint(20) NOT NULL,
  `administrador` bit(1) DEFAULT b'0',
  `apellidos` varchar(255) DEFAULT NULL,
  `dni` varchar(255) DEFAULT NULL,
  `enabled` bit(1) DEFAULT b'0',
  `fecha_nacimiento` date DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `sexo` varchar(255) DEFAULT 'Masculino',
  `telefono` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- RELACIONES PARA LA TABLA `usuario`:
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario_partido`
--

DROP TABLE IF EXISTS `usuario_partido`;
CREATE TABLE `usuario_partido` (
  `partido_id` bigint(20) NOT NULL,
  `usuario_id` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- RELACIONES PARA LA TABLA `usuario_partido`:
--

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `authorities_users`
--
ALTER TABLE `authorities_users`
  ADD PRIMARY KEY (`usuario_id`,`authority_id`),
  ADD KEY `FK1hk335nyb5icwqy64y2mhov2v` (`authority_id`);

--
-- Indices de la tabla `authority`
--
ALTER TABLE `authority`
  ADD PRIMARY KEY (`id`);

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
  ADD KEY `FK4udheebqtngntwbi6hk69ueid` (`campeonato_id`),
  ADD KEY `FKi15dvx0ba5eja0l2qw8smq222` (`ganador_id`),
  ADD KEY `FKjik1ec60o2gb2u16jnxbajd3g` (`pareja1_id`),
  ADD KEY `FK1kc76l9xa6m7hs8bplf53ogq0` (`pareja2_id`),
  ADD KEY `FK3cne5pf1hwvihlej8hn77rtcy` (`reserva_id`);

--
-- Indices de la tabla `pareja`
--
ALTER TABLE `pareja`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKq1anglu2rben41aitrm7on2vi` (`miembro1_id`),
  ADD KEY `FKd8yyeej2y7cwmqclv1o4exnyn` (`miembro2_id`);

--
-- Indices de la tabla `pareja_campeonato`
--
ALTER TABLE `pareja_campeonato`
  ADD PRIMARY KEY (`campeonato_id`,`pareja_id`),
  ADD KEY `FKphm9c4kcmrkifi26kag6ljop2` (`pareja_id`);

--
-- Indices de la tabla `partido`
--
ALTER TABLE `partido`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK14ayv0gkng3gyj342nsxmiepe` (`reserva_id`);

--
-- Indices de la tabla `pista`
--
ALTER TABLE `pista`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `reserva`
--
ALTER TABLE `reserva`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKeu0vdx75tpgjaq1nnfd4raom8` (`pista_id`),
  ADD KEY `FKiad9w96t12u3ms2ul93l97mel` (`usuario_id`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `usuario_partido`
--
ALTER TABLE `usuario_partido`
  ADD PRIMARY KEY (`partido_id`,`usuario_id`),
  ADD KEY `FK17psfhmqbu7396eoxi5ngoyg6` (`usuario_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `campeonato`
--
ALTER TABLE `campeonato`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `enfrentamiento`
--
ALTER TABLE `enfrentamiento`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `pareja`
--
ALTER TABLE `pareja`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `partido`
--
ALTER TABLE `partido`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `pista`
--
ALTER TABLE `pista`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `reserva`
--
ALTER TABLE `reserva`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
SET FOREIGN_KEY_CHECKS=1;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
