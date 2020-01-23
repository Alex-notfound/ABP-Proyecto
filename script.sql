-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 23-01-2020 a las 23:20:52
-- Versión del servidor: 10.4.11-MariaDB
-- Versión de PHP: 7.2.26

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

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `authorities_users`
--

CREATE TABLE `authorities_users` (
  `usuario_id` bigint(20) NOT NULL,
  `authority_id` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `authority`
--

CREATE TABLE `authority` (
  `id` bigint(20) NOT NULL,
  `authority` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `campeonato`
--

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
-- Volcado de datos para la tabla `campeonato`
--

INSERT INTO `campeonato` (`id`, `abierto`, `categoria`, `limite_inscripcion`, `max_num_participantes`, `nivel`, `precio_inscripcion`) VALUES
(1, b'0', 'Masculino', '2020-03-01', 16, 'Medio', '5'),
(2, b'1', 'Femenino', '2020-02-01', 8, 'Alto', '3'),
(3, b'1', 'Mixto', '2020-02-01', 12, 'Basico', '15'),
(4, b'1', 'Masculino', '2020-02-01', 12, 'Alto', '25'),
(5, b'1', 'Masculino', '2020-02-26', 16, 'Basico', '5');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `enfrentamiento`
--

CREATE TABLE `enfrentamiento` (
  `id` bigint(20) NOT NULL,
  `fase` int(11) NOT NULL DEFAULT 1,
  `grupo` int(11) DEFAULT NULL,
  `resultado` varchar(255) DEFAULT '',
  `campeonato_id` bigint(20) DEFAULT NULL,
  `ganador_id` bigint(20) DEFAULT NULL,
  `pareja1_id` bigint(20) DEFAULT NULL,
  `pareja2_id` bigint(20) DEFAULT NULL,
  `reserva_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `enfrentamiento`
--

INSERT INTO `enfrentamiento` (`id`, `fase`, `grupo`, `resultado`, `campeonato_id`, `ganador_id`, `pareja1_id`, `pareja2_id`, `reserva_id`) VALUES
(1, 1, 1, '0 - 2', 1, 1, 1, 2, 7),
(2, 1, 1, '0 - 2', 1, 1, 1, 3, 8),
(3, 1, 1, '0 - 2', 1, 1, 1, 4, 9),
(4, 1, 1, '0 - 2', 1, 1, 1, 5, 10),
(5, 1, 1, '0 - 2', 1, 1, 1, 6, 11),
(6, 1, 1, '0 - 2', 1, 1, 1, 7, 12),
(7, 1, 1, '0 - 2', 1, 1, 1, 8, 13),
(8, 1, 1, '0 - 2', 1, 2, 2, 3, 14),
(9, 1, 1, '0 - 2', 1, 2, 2, 4, 15),
(10, 1, 1, '0 - 2', 1, 2, 2, 5, 16),
(11, 1, 1, '2 - 0', 1, 2, 2, 6, 17),
(12, 1, 1, '2 - 0', 1, 2, 2, 7, 18),
(13, 1, 1, '2 - 0', 1, 2, 2, 8, 19),
(14, 1, 1, '2 - 0', 1, 3, 3, 4, 20),
(15, 1, 1, '2 - 0', 1, 3, 3, 5, 21),
(16, 1, 1, '2 - 0', 1, 3, 3, 6, 22),
(17, 1, 1, '2 - 0', 1, 3, 3, 7, 23),
(18, 1, 1, '2 - 0', 1, 3, 3, 8, 24),
(19, 1, 1, '2 - 0', 1, 4, 4, 5, 25),
(20, 1, 1, '2 - 0', 1, 4, 4, 6, 26),
(21, 1, 1, '2 - 0', 1, 4, 4, 7, 27),
(22, 1, 1, '2 - 0', 1, 4, 4, 8, 28),
(23, 1, 1, '2 - 0', 1, 5, 5, 6, 29),
(24, 1, 1, '2 - 0', 1, 5, 5, 7, 30),
(25, 1, 1, '2 - 0', 1, 5, 5, 8, 31),
(26, 1, 1, '0 - 2', 1, 6, 6, 7, 32),
(27, 1, 1, '0 - 2', 1, 6, 6, 8, 33),
(28, 1, 1, '0 - 2', 1, 7, 7, 8, 34),
(29, 1, 2, '0 - 2', 1, 9, 9, 10, 35),
(30, 1, 2, '0 - 2', 1, 9, 9, 11, 36),
(31, 1, 2, '0 - 2', 1, 9, 9, 12, 37),
(32, 1, 2, '0 - 2', 1, 9, 9, 13, 38),
(33, 1, 2, '0 - 2', 1, 9, 9, 14, 39),
(34, 1, 2, '0 - 2', 1, 9, 9, 15, 40),
(35, 1, 2, '0 - 2', 1, 9, 9, 17, 41),
(36, 1, 2, '0 - 2', 1, 10, 10, 11, 42),
(37, 1, 2, '0 - 2', 1, 10, 10, 12, 43),
(38, 1, 2, '0 - 2', 1, 13, 10, 13, 44),
(39, 1, 2, '0 - 2', 1, 10, 10, 14, 45),
(40, 1, 2, '0 - 2', 1, 10, 10, 15, 46),
(41, 1, 2, '0 - 2', 1, 10, 10, 17, 47),
(42, 1, 2, '0 - 2', 1, 11, 11, 12, 48),
(43, 1, 2, '0 - 2', 1, 11, 11, 13, 49),
(44, 1, 2, '0 - 2', 1, 11, 11, 14, 50),
(45, 1, 2, '0 - 2', 1, 11, 11, 15, 51),
(46, 1, 2, '0 - 2', 1, 11, 11, 17, 52),
(47, 1, 2, '0 - 2', 1, 12, 12, 13, 53),
(48, 1, 2, '0 - 2', 1, 12, 12, 14, 54),
(49, 1, 2, '0 - 2', 1, 12, 12, 15, 55),
(50, 1, 2, '0 - 2', 1, 12, 12, 17, 56),
(51, 1, 2, '0 - 2', 1, 13, 13, 14, 57),
(52, 1, 2, '0 - 2', 1, 13, 13, 15, 58),
(53, 1, 2, '0 - 2', 1, 13, 13, 17, 59),
(54, 1, 2, '0 - 2', 1, 14, 14, 15, 60),
(55, 1, 2, '0 - 2', 1, 14, 14, 17, 61),
(56, 1, 2, '0 - 2', 1, 15, 15, 17, 62),
(57, 2, 1, NULL, 1, NULL, 1, 8, 63),
(58, 2, 1, NULL, 1, NULL, 7, 3, 64),
(59, 2, 1, NULL, 1, NULL, 6, 4, 65),
(60, 2, 1, NULL, 1, NULL, 5, 2, 66),
(61, 2, 2, NULL, 1, NULL, 15, 17, 67),
(62, 2, 2, NULL, 1, NULL, 14, 9, 68),
(63, 2, 2, NULL, 1, NULL, 13, 10, 69),
(64, 2, 2, NULL, 1, NULL, 12, 11, 70);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `notificacion`
--

CREATE TABLE `notificacion` (
  `id` bigint(20) NOT NULL,
  `campeonato_id` bigint(20) DEFAULT NULL,
  `capitan_receptor_id` bigint(20) DEFAULT NULL,
  `emisora_id` bigint(20) DEFAULT NULL,
  `enfrentamiento_id` bigint(20) DEFAULT NULL,
  `receptora_id` bigint(20) DEFAULT NULL,
  `reserva_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pareja`
--

CREATE TABLE `pareja` (
  `id` bigint(20) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `miembro1_id` bigint(20) DEFAULT NULL,
  `miembro2_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `pareja`
--

INSERT INTO `pareja` (`id`, `nombre`, `miembro1_id`, `miembro2_id`) VALUES
(1, '1A', 21, 22),
(2, '3A', 23, 24),
(3, '4A', 25, 26),
(4, '5A', 27, 28),
(5, '6A', 29, 30),
(6, '7A', 31, 32),
(7, 'Ferro - Manrique', 19, 18),
(8, '8A', 33, 34),
(9, '9A', 35, 36),
(10, '0A', 37, 38),
(11, 'Caro-Meira', 17, 20),
(12, 'Rodriguez-Pereiro', 15, 16),
(13, 'Puga-Fonseca', 13, 14),
(14, 'Barros-Lopez', 11, 12),
(15, 'Nuñez-Crespo', 9, 10),
(16, '0A', 21, 22),
(17, '0CD', 39, 40);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pareja_campeonato`
--

CREATE TABLE `pareja_campeonato` (
  `grupo` int(11) DEFAULT NULL,
  `puntos` int(11) NOT NULL DEFAULT 0,
  `campeonato_id` bigint(20) NOT NULL,
  `pareja_id` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `pareja_campeonato`
--

INSERT INTO `pareja_campeonato` (`grupo`, `puntos`, `campeonato_id`, `pareja_id`) VALUES
(1, 50, 1, 1),
(1, 30, 1, 2),
(1, 20, 1, 3),
(1, 20, 1, 4),
(1, 40, 1, 5),
(1, 40, 1, 6),
(1, 40, 1, 7),
(1, 20, 1, 8),
(2, 20, 1, 9),
(2, 20, 1, 10),
(2, 20, 1, 11),
(2, 20, 1, 12),
(2, 20, 1, 13),
(2, 20, 1, 14),
(2, 20, 1, 15),
(0, 20, 5, 16),
(2, 20, 1, 17);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `partido`
--

CREATE TABLE `partido` (
  `id` bigint(20) NOT NULL,
  `abierto` bit(1) NOT NULL DEFAULT b'1',
  `tipo` varchar(255) DEFAULT NULL,
  `reserva_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `partido`
--

INSERT INTO `partido` (`id`, `abierto`, `tipo`, `reserva_id`) VALUES
(1, b'1', 'Promocionado', 1),
(2, b'1', 'Promocionado', 2),
(3, b'1', 'Promocionado', 3),
(4, b'1', 'Ofertado', 4),
(5, b'1', 'Ofertado', 5);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pista`
--

CREATE TABLE `pista` (
  `id` bigint(20) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `numero` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `pista`
--

INSERT INTO `pista` (`id`, `descripcion`, `numero`) VALUES
(1, 'Abierta', 1),
(2, 'Cubierta', 2),
(3, 'Cubierta con pared de cristal', 3),
(4, 'Cubierta con pared de cristal y grada', 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reserva`
--

CREATE TABLE `reserva` (
  `id` bigint(20) NOT NULL,
  `disponible` bit(1) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `hora` varchar(255) DEFAULT NULL,
  `pista_id` bigint(20) DEFAULT NULL,
  `usuario_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `reserva`
--

INSERT INTO `reserva` (`id`, `disponible`, `fecha`, `hora`, `pista_id`, `usuario_id`) VALUES
(1, b'1', '2020-01-26', '12.00', NULL, 2),
(2, b'1', '2020-01-26', '15.00', NULL, 2),
(3, b'1', '2020-01-26', '21.00', NULL, 2),
(4, b'0', '2020-01-26', '9.00', 2, 39),
(5, b'0', '2020-01-27', '10.30', 2, 39),
(6, b'0', '2020-01-28', '15.00', 2, 39),
(7, b'0', NULL, NULL, NULL, NULL),
(8, b'0', NULL, NULL, NULL, NULL),
(9, b'0', NULL, NULL, NULL, NULL),
(10, b'0', NULL, NULL, NULL, NULL),
(11, b'0', NULL, NULL, NULL, NULL),
(12, b'0', NULL, NULL, NULL, NULL),
(13, b'0', NULL, NULL, NULL, NULL),
(14, b'0', NULL, NULL, NULL, NULL),
(15, b'0', NULL, NULL, NULL, NULL),
(16, b'0', NULL, NULL, NULL, NULL),
(17, b'0', NULL, NULL, NULL, NULL),
(18, b'0', NULL, NULL, NULL, NULL),
(19, b'0', NULL, NULL, NULL, NULL),
(20, b'0', NULL, NULL, NULL, NULL),
(21, b'0', NULL, NULL, NULL, NULL),
(22, b'0', NULL, NULL, NULL, NULL),
(23, b'0', NULL, NULL, NULL, NULL),
(24, b'0', NULL, NULL, NULL, NULL),
(25, b'0', NULL, NULL, NULL, NULL),
(26, b'0', NULL, NULL, NULL, NULL),
(27, b'0', NULL, NULL, NULL, NULL),
(28, b'0', NULL, NULL, NULL, NULL),
(29, b'0', NULL, NULL, NULL, NULL),
(30, b'0', NULL, NULL, NULL, NULL),
(31, b'0', NULL, NULL, NULL, NULL),
(32, b'0', NULL, NULL, NULL, NULL),
(33, b'0', NULL, NULL, NULL, NULL),
(34, b'0', NULL, NULL, NULL, NULL),
(35, b'0', NULL, NULL, NULL, NULL),
(36, b'0', NULL, NULL, NULL, NULL),
(37, b'0', NULL, NULL, NULL, NULL),
(38, b'0', NULL, NULL, NULL, NULL),
(39, b'0', NULL, NULL, NULL, NULL),
(40, b'0', NULL, NULL, NULL, NULL),
(41, b'0', NULL, NULL, NULL, NULL),
(42, b'0', NULL, NULL, NULL, NULL),
(43, b'0', NULL, NULL, NULL, NULL),
(44, b'0', NULL, NULL, NULL, NULL),
(45, b'0', NULL, NULL, NULL, NULL),
(46, b'0', NULL, NULL, NULL, NULL),
(47, b'0', NULL, NULL, NULL, NULL),
(48, b'0', NULL, NULL, NULL, NULL),
(49, b'0', NULL, NULL, NULL, NULL),
(50, b'0', NULL, NULL, NULL, NULL),
(51, b'0', NULL, NULL, NULL, NULL),
(52, b'0', NULL, NULL, NULL, NULL),
(53, b'0', NULL, NULL, NULL, NULL),
(54, b'0', NULL, NULL, NULL, NULL),
(55, b'0', NULL, NULL, NULL, NULL),
(56, b'0', NULL, NULL, NULL, NULL),
(57, b'0', NULL, NULL, NULL, NULL),
(58, b'0', NULL, NULL, NULL, NULL),
(59, b'0', NULL, NULL, NULL, NULL),
(60, b'0', NULL, NULL, NULL, NULL),
(61, b'0', NULL, NULL, NULL, NULL),
(62, b'0', NULL, NULL, NULL, NULL),
(63, b'0', NULL, NULL, NULL, NULL),
(64, b'0', NULL, NULL, NULL, NULL),
(65, b'0', NULL, NULL, NULL, NULL),
(66, b'0', NULL, NULL, NULL, NULL),
(67, b'0', NULL, NULL, NULL, NULL),
(68, b'0', NULL, NULL, NULL, NULL),
(69, b'0', NULL, NULL, NULL, NULL),
(70, b'0', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id` bigint(20) NOT NULL,
  `administrador` bit(1) DEFAULT b'0',
  `apellidos` varchar(255) DEFAULT NULL,
  `dni` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `enabled` bit(1) DEFAULT b'0',
  `fecha_nacimiento` date DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `sexo` varchar(255) DEFAULT 'Masculino',
  `telefono` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id`, `administrador`, `apellidos`, `dni`, `email`, `enabled`, `fecha_nacimiento`, `nombre`, `password`, `sexo`, `telefono`) VALUES
(1, b'0', 'Duque', '77484954R', 'a@gmail.com', b'0', '1998-04-26', 'Daniel', '$2a$04$3yE4piuMJ.ummpSM/jVPG.0EEFTOlZP9IusaUmnwy0n6giywVjD0O', 'Masculino', 673449470),
(2, b'1', 'admin', '12345678A', 'b@gmail.com', b'1', '2019-11-01', 'admin', '$2a$04$YFWatPcRPwJflbfhMicUVecegvEqE6k1pvWJCrfaJUR0R1Y0jR/uC', 'Masculino', 697217053),
(3, b'0', 'Rego', '11111111Q', 'c@gmail.com', b'0', '1961-02-15', 'Pepe', '$2a$04$W4IqQ.iJLj9anD5y90usN.RA06bM2LMBqdQx4mVbXOveDoiwbjMJe', 'Masculino', 666999333),
(4, b'0', 'Suarez', '99889988G', 'd@gmail.com', b'0', '1990-05-22', 'Maria', '$2a$04$SAeyr8PO2dRNx9/4PWIrI.lUoNm7t.L0O7o8TV/VV39tE6MnbzXX6', 'Femenino', 666555444),
(5, b'0', 'Perez', '44444444W', 'e@gmail.com', b'0', '1975-08-17', 'Jose', '$2a$04$ypGRPS5KrL8elQOIDD2okuR2Wq5hsb0dX0IcI.BYx0ajUKTsKLDlC', 'Masculino', 698521145),
(6, b'0', 'Ramirez', '14725836H', 'f@gmail.com', b'0', '1994-04-04', 'Juan', '$2a$04$P06tx4wfTAOX1InlKAk9KO613XyeSa55xcpwYuCX7YvQ16K4dJ9ta', 'Masculino', 693685625),
(7, b'0', 'del Mar', '99887788N', 'g@gmail.com', b'0', '1995-08-26', 'Maria', '$2a$04$GrMcS2bXgJ3OIs3t22KuhuNx8s6REIICfuJio5y.jgQ7lFWXtK/lW', 'Femenino', 632256698),
(8, b'0', 'Mendez', '98765432C', 'h@gmail.com', b'0', '1997-02-28', 'Brais', '$2a$04$Z6QhHWnH8ag97iT.9NYMD.kK6IYMqlO2r8bHwXTquFXBTVIqzzVSS', 'Masculino', 666333222),
(9, b'0', 'Nuñez', '33221155Y', 'ie@gmail.com', b'0', '1991-12-15', 'Tomás', '$2a$04$OT8tLD1fHk/slAyOOqLOC.Zf4jcH1UQzYlNIU0VNpDo6qZxG/groa', 'Masculino', 666999888),
(10, b'0', 'Crespo', '55446699Z', 'i@gmail.com', b'0', '1992-12-18', 'Pablo', '$2a$04$9wPAaZU8M62t3pMdoSnLmuBqCM40Mrko2SO6f8ZM.xnpdkbHdjeM6', 'Masculino', 666888444),
(11, b'0', 'Barros', '77445566H', 'je@gmail.com', b'0', '2004-01-17', 'Paula', '$2a$04$s9t.8/KCf34hG03MD24s5OM1vHOxyTaelORDX1STAQQmx4kDAI8Qm', 'Femenino', 741258963),
(12, b'0', 'Lopez', '78965412L', 'el@gmail.com', b'0', '1985-04-21', 'Héctor', '$2a$04$wEGnQwa3yhzADa7OLqrZP.U0UJ2iXfLE8PXE6TZm6w36a/YkJimCu', 'Masculino', 654123987),
(13, b'0', 'Puga', '66554455T', 'a@gmail.com', b'0', '1987-05-14', 'Manuel', '$2a$04$kjiuSGcebPt9.Zv78cpvm.vH6GID5gJdzRENTaim1mTOVSCV87TXq', 'Masculino', 654987321),
(14, b'0', 'Fonseca', '22113366M', 'h@gmail.com', b'0', '1984-08-17', 'Paulo', '$2a$04$wKGfWcMH9yGwLgCBZtX8Y.PCIRirkY1GBNHDxKQUb03EPPOCECuH6', 'Masculino', 654123999),
(15, b'0', 'Rodriguez', '88888888Z', 'gh@gmail.com', b'0', '1995-08-28', 'Carlos', '$2a$04$6RNQT5UiQT6G3i4Gfl8H2OJO2zITjAiCKj7Dv3tudHguI3V4rrW.6', 'Masculino', 693546871),
(16, b'0', 'Pereiro', '66699933D', 'fe@gmail.com', b'0', '1981-09-23', 'Pedro', '$2a$04$y4nUf5aPB06GTWzZKZW2W.cBoNM.pnp38HQ1UHHK7HC/wJTqJYqXK', 'Masculino', 632212369),
(17, b'0', 'Meira', '99663366A', 'rm@gmail.com', b'0', '1987-02-02', 'Xavier', '$2a$04$SjUYNHg3X2dLvmNnQleRX.YIO8lCPu.HxAoaVjZkidf10vQH3Sy/O', 'Masculino', 698852369),
(18, b'0', 'Manrique', '11225544E', 'sl@gmail.com', b'0', '1964-08-01', 'Jorge', '$2a$04$KLsR8MrwBjX8twU.eC951.vM1JcewOZoSeWD0Z1GlwwjkDUeqWKti', 'Masculino', 666555888),
(19, b'0', 'Ferro', '78963654V', 'js@gmail.com', b'0', '2001-06-25', 'Braulio', '$2a$04$/baJ4pV39XmGw7h2gSx13uB5gX4L7I0/sOO/qgtVH5.tPbqoNqccG', 'Masculino', 699966333),
(20, b'0', 'Caro', '44558877P', 'ds@gmail.com', b'0', '1985-06-14', 'Alex', '$2a$04$Gj5Wdui39qa4dm9VtQ/bP.k5Tq5JTQzEQ9HoWGQefSMz6ZgQwhwmS', 'Masculino', 654123987),
(21, b'0', 'Julai', '11111111A', 'alx.curras@gmail.com', b'0', '2020-01-02', 'Julio', '$2a$04$6FgCXt6xPuPTmiuyqIn2/u/wUGJyv8JHHWOzz5bDjheRWpNoshuSe', 'Masculino', 697217053),
(22, b'0', 'Arce', '11111111B', 'alx.curras@gmail.com', b'0', '2020-01-02', 'Jorge', '$2a$04$6FgCXt6xPuPTmiuyqIn2/u/wUGJyv8JHHWOzz5bDjheRWpNoshuSe', 'Masculino', 697217053),
(23, b'0', 'Ron', '33333333A', 'alx.curras@gmail.com', b'0', '2020-01-02', 'Jose', '$2a$04$6FgCXt6xPuPTmiuyqIn2/u/wUGJyv8JHHWOzz5bDjheRWpNoshuSe', 'Masculino', 697217053),
(24, b'0', 'Cao', '33333333B', 'alx.curras@gmail.com', b'0', '2020-01-02', 'Dani', '$2a$04$6FgCXt6xPuPTmiuyqIn2/u/wUGJyv8JHHWOzz5bDjheRWpNoshuSe', 'Masculino', 697217053),
(25, b'0', 'Perez', '44444444A', 'alx.curras@gmail.com', b'0', '2020-01-02', 'David', '$2a$04$6FgCXt6xPuPTmiuyqIn2/u/wUGJyv8JHHWOzz5bDjheRWpNoshuSe', 'Masculino', 697217053),
(26, b'0', 'Lopez', '44444444B', 'alx.curras@gmail.com', b'0', '2020-01-02', 'Santi', '$2a$04$6FgCXt6xPuPTmiuyqIn2/u/wUGJyv8JHHWOzz5bDjheRWpNoshuSe', 'Masculino', 697217053),
(27, b'0', 'Jer', '55555555A', 'alx.curras@gmail.com', b'0', '2020-01-02', 'Miguel', '$2a$04$6FgCXt6xPuPTmiuyqIn2/u/wUGJyv8JHHWOzz5bDjheRWpNoshuSe', 'Masculino', 697217053),
(28, b'0', 'Sor', '55555555B', 'alx.curras@gmail.com', b'0', '2020-01-02', 'Victor', '$2a$04$6FgCXt6xPuPTmiuyqIn2/u/wUGJyv8JHHWOzz5bDjheRWpNoshuSe', 'Masculino', 697217053),
(29, b'0', 'Sil', '66666666A', 'alx.curras@gmail.com', b'0', '2020-01-02', 'Pedro', '$2a$04$6FgCXt6xPuPTmiuyqIn2/u/wUGJyv8JHHWOzz5bDjheRWpNoshuSe', 'Masculino', 697217053),
(30, b'0', 'Mar', '66666666B', 'alx.curras@gmail.com', b'0', '2020-01-02', 'Rodrigo', '$2a$04$6FgCXt6xPuPTmiuyqIn2/u/wUGJyv8JHHWOzz5bDjheRWpNoshuSe', 'Masculino', 697217053),
(31, b'0', 'Qer', '77777777A', 'alx.curras@gmail.com', b'0', '2020-01-02', 'Sergio', '$2a$04$6FgCXt6xPuPTmiuyqIn2/u/wUGJyv8JHHWOzz5bDjheRWpNoshuSe', 'Masculino', 697217053),
(32, b'0', 'Cas', '77777777B', 'alx.curras@gmail.com', b'0', '2020-01-02', 'Roi', '$2a$04$6FgCXt6xPuPTmiuyqIn2/u/wUGJyv8JHHWOzz5bDjheRWpNoshuSe', 'Masculino', 697217053),
(33, b'0', 'Ter', '88888888A', 'alx.curras@gmail.com', b'0', '2020-01-02', 'Manuel', '$2a$04$6FgCXt6xPuPTmiuyqIn2/u/wUGJyv8JHHWOzz5bDjheRWpNoshuSe', 'Masculino', 697217053),
(34, b'0', 'Rel', '88888888B', 'alx.curras@gmail.com', b'0', '2020-01-02', 'Diego', '$2a$04$6FgCXt6xPuPTmiuyqIn2/u/wUGJyv8JHHWOzz5bDjheRWpNoshuSe', 'Masculino', 697217053),
(35, b'0', 'Rio', '99999999A', 'alx.curras@gmail.com', b'0', '2020-01-02', 'German', '$2a$04$6FgCXt6xPuPTmiuyqIn2/u/wUGJyv8JHHWOzz5bDjheRWpNoshuSe', 'Masculino', 697217053),
(36, b'0', 'Sar', '99999999B', 'alx.curras@gmail.com', b'0', '2020-01-02', 'Pablo', '$2a$04$6FgCXt6xPuPTmiuyqIn2/u/wUGJyv8JHHWOzz5bDjheRWpNoshuSe', 'Masculino', 697217053),
(37, b'0', 'Col', '00000000A', 'alx.curras@gmail.com', b'0', '2020-01-02', 'Alejandro', '$2a$04$6FgCXt6xPuPTmiuyqIn2/u/wUGJyv8JHHWOzz5bDjheRWpNoshuSe', 'Masculino', 697217053),
(38, b'0', 'Gil', '00000000B', 'alx.curras@gmail.com', b'0', '2020-01-02', 'Marcos', '$2a$04$6FgCXt6xPuPTmiuyqIn2/u/wUGJyv8JHHWOzz5bDjheRWpNoshuSe', 'Masculino', 697217053),
(39, b'0', 'Tarantino', '00000000C', 'alx.curras@gmail.com', b'0', '2020-01-02', 'Fin', '$2a$04$6FgCXt6xPuPTmiuyqIn2/u/wUGJyv8JHHWOzz5bDjheRWpNoshuSe', 'Masculino', 697217053),
(40, b'0', 'Quentin', '00000000D', 'alx.curras@gmail.com', b'0', '2020-01-02', 'James', '$2a$04$6FgCXt6xPuPTmiuyqIn2/u/wUGJyv8JHHWOzz5bDjheRWpNoshuSe', 'Masculino', 697217053);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario_partido`
--

CREATE TABLE `usuario_partido` (
  `partido_id` bigint(20) NOT NULL,
  `usuario_id` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `usuario_partido`
--

INSERT INTO `usuario_partido` (`partido_id`, `usuario_id`) VALUES
(1, 39),
(4, 39),
(5, 39);

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
-- Indices de la tabla `notificacion`
--
ALTER TABLE `notificacion`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKlmqukif9rhee6dmg2tnb8g498` (`campeonato_id`),
  ADD KEY `FKli3nfbvmm9fqgxducvkxhqtfj` (`capitan_receptor_id`),
  ADD KEY `FKf9vxxdom3bvml5buxttpq28ge` (`emisora_id`),
  ADD KEY `FKtin5fsxjfslve7bd7a9ceydw4` (`enfrentamiento_id`),
  ADD KEY `FKk6okkv3jomupupxv1jmm60kha` (`receptora_id`),
  ADD KEY `FK8oehwb0gvxuyl55mnncrpt6w2` (`reserva_id`);

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
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `enfrentamiento`
--
ALTER TABLE `enfrentamiento`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=65;

--
-- AUTO_INCREMENT de la tabla `notificacion`
--
ALTER TABLE `notificacion`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `pareja`
--
ALTER TABLE `pareja`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT de la tabla `partido`
--
ALTER TABLE `partido`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `pista`
--
ALTER TABLE `pista`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `reserva`
--
ALTER TABLE `reserva`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=71;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
