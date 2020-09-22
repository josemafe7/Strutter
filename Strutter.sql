-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 16-05-2020 a las 10:03:28
-- Versión del servidor: 10.4.10-MariaDB
-- Versión de PHP: 7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `strutter`
--
CREATE DATABASE IF NOT EXISTS `strutter`DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish2_ci;
USE `strutter`;
-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoria`
--

DROP TABLE IF EXISTS `categoria`;
CREATE TABLE IF NOT EXISTS `categoria` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `categoria` varchar(80) COLLATE utf8_spanish2_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `categoria`
--

INSERT INTO `categoria` (`id`, `categoria`) VALUES
(1, 'Hoteles y viajes'),
(2, 'Entretenimiento\r\n'),
(3, 'Alimentacion y restauracion'),
(4, 'Salud y belleza'),
(5, 'Coches'),
(6, 'Informatica y telecomunicaciones'),
(7, 'Hogar'),
(8, 'Deporte y aventura'),
(9, 'Otras');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

DROP TABLE IF EXISTS `cliente`;
CREATE TABLE IF NOT EXISTS `cliente` (
  `dni` varchar(9) COLLATE utf8_spanish2_ci NOT NULL,
  `nombre` varchar(60) COLLATE utf8_spanish2_ci NOT NULL,
  `apellido1` varchar(60) COLLATE utf8_spanish2_ci NOT NULL,
  `apellido2` varchar(60) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `sexo` varchar(1) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `fecha_nac` date NOT NULL,
  `direccion` varchar(60) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `localidad` varchar(60) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `codpostal` int(5) DEFAULT NULL,
  `telefono` int(9) DEFAULT NULL,
  `correo` varchar(60) COLLATE utf8_spanish2_ci NOT NULL,
  `usuario_asociado` int(11) NOT NULL,
  PRIMARY KEY (`dni`),
  KEY `usuario_asociado` (`usuario_asociado`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`dni`, `nombre`, `apellido1`, `apellido2`, `sexo`, `fecha_nac`, `direccion`, `localidad`, `codpostal`, `telefono`, `correo`, `usuario_asociado`) VALUES
('44444444D', 'ANTONIO', 'VERDUGO', 'ESCALERA', 'H', '1997-01-22', 'C/SAUCEJO', 'SEVILLA', 19700, 444444444, 'ANTONIO@CLIENTE.ES', 4),
('77777777G', 'CLAUDIA', 'NUNEZ', 'RIVERA', 'M', '1998-12-11', 'C/BARBATE', 'CADIZ', 11200, 777777777, 'CLAUDIA@CLIENTE.ES', 7);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `compra`
--

DROP TABLE IF EXISTS `compra`;
CREATE TABLE IF NOT EXISTS `compra` (
  `id_compra` int(11) NOT NULL AUTO_INCREMENT,
  `fecha_compra` date NOT NULL,
  `oferta_asociada` int(11) NOT NULL,
  `cliente_asociado` varchar(9) COLLATE utf8_spanish2_ci NOT NULL,
  PRIMARY KEY (`id_compra`),
  KEY `oferta_asociada` (`oferta_asociada`),
  KEY `cliente_asociado` (`cliente_asociado`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `compra`
--

INSERT INTO `compra` (`id_compra`, `fecha_compra`, `oferta_asociada`, `cliente_asociado`) VALUES
(1, '2020-05-16', 3, '44444444D');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `departamento`
--

DROP TABLE IF EXISTS `departamento`;
CREATE TABLE IF NOT EXISTS `departamento` (
  `dept_no` int(11) NOT NULL AUTO_INCREMENT,
  `dept_nombre` varchar(80) COLLATE utf8_spanish2_ci NOT NULL,
  `dept_descripcion` varchar(500) COLLATE utf8_spanish2_ci NOT NULL,
  PRIMARY KEY (`dept_no`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `departamento`
--

INSERT INTO `departamento` (`dept_no`, `dept_nombre`, `dept_descripcion`) VALUES
(1, 'Administrador', 'Tienen total poder sobre el sistema.'),
(2, 'Moderador', 'Son los encargados de comprobar que las ofertas cumplen los requisitos.'),
(3, 'Administrativo', 'Se encargan de gestionar a los empleados, tema sueldos, y contratarlos.');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleado`
--

DROP TABLE IF EXISTS `empleado`;
CREATE TABLE IF NOT EXISTS `empleado` (
  `dni` varchar(9) COLLATE utf8_spanish2_ci NOT NULL,
  `nombre` varchar(60) COLLATE utf8_spanish2_ci NOT NULL,
  `apellido1` varchar(60) COLLATE utf8_spanish2_ci NOT NULL,
  `apellido2` varchar(60) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `sexo` varchar(1) COLLATE utf8_spanish2_ci NOT NULL,
  `fecha_nac` date NOT NULL,
  `direccion` varchar(60) COLLATE utf8_spanish2_ci NOT NULL,
  `localidad` varchar(60) COLLATE utf8_spanish2_ci NOT NULL,
  `codpostal` int(5) NOT NULL,
  `telefono` int(9) NOT NULL,
  `correo` varchar(60) COLLATE utf8_spanish2_ci NOT NULL,
  `salario` double NOT NULL,
  `departamento` int(11) NOT NULL,
  `usuario_asociado` int(11) NOT NULL,
  PRIMARY KEY (`dni`),
  KEY `usuario_asociado` (`usuario_asociado`),
  KEY `departamento` (`departamento`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `empleado`
--

INSERT INTO `empleado` (`dni`, `nombre`, `apellido1`, `apellido2`, `sexo`, `fecha_nac`, `direccion`, `localidad`, `codpostal`, `telefono`, `correo`, `salario`, `departamento`, `usuario_asociado`) VALUES
('11111111A', 'DANIEL', 'BARCIELA', 'RUEDA', 'H', '1995-11-04', 'C/LA LINEA', 'CADIZ', 11300, 111111111, 'DANI@ADMINISTRATIVO.ES', 10000, 1, 1),
('22222222B', 'JOSE MANUEL', 'FERNANDEZ', 'LABRADOR', 'H', '1995-12-22', 'C/PUENTE GENIL', 'CORDOBA', 10200, 222222222, 'JOSEMA@MODERADOR.ES', 5000, 2, 2),
('33333333C', 'MANUEL', 'HERRERA', 'PULIDO', 'H', '1996-12-11', 'C/PRUNA', 'SEVILLA', 11000, 333333333, 'MANOLITOPRUNA@ADMINISTRATIVO.ES', 5000, 3, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empresa`
--

DROP TABLE IF EXISTS `empresa`;
CREATE TABLE IF NOT EXISTS `empresa` (
  `nif` varchar(9) COLLATE utf8_spanish2_ci NOT NULL,
  `nombre` varchar(60) COLLATE utf8_spanish2_ci NOT NULL,
  `descripcion` varchar(500) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `direccion` varchar(60) COLLATE utf8_spanish2_ci NOT NULL,
  `localidad` varchar(60) COLLATE utf8_spanish2_ci NOT NULL,
  `codpostal` int(5) NOT NULL,
  `telefono` int(9) NOT NULL,
  `correo` varchar(60) COLLATE utf8_spanish2_ci NOT NULL,
  `usuario_asociado` int(11) NOT NULL,
  PRIMARY KEY (`nif`),
  KEY `usuario_asociado` (`usuario_asociado`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `empresa`
--

INSERT INTO `empresa` (`nif`, `nombre`, `descripcion`, `direccion`, `localidad`, `codpostal`, `telefono`, `correo`, `usuario_asociado`) VALUES
('55555555E', 'BODEGUITAS HERRERA', 'COMIDA RICA EN PRUNA', 'C/PRUNA', 'SEVILLA', 10400, 555555555, 'BODEGUITA@PRUNA.ES', 5),
('66666666F', 'EL TIENDA DE LA SOFI', 'TIENDA DE TODO TIPO DE ARTICULOS', 'C/PUENTE GENIL', 'CORDOBA', 10000, 666666666, 'ELTIENDALASOFI@PUENTE.ES', 6);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `oferta`
--

DROP TABLE IF EXISTS `oferta`;
CREATE TABLE IF NOT EXISTS `oferta` (
  `id_oferta` int(11) NOT NULL AUTO_INCREMENT,
  `titulo` varchar(100) COLLATE utf8_spanish2_ci NOT NULL,
  `descripcion` varchar(800) COLLATE utf8_spanish2_ci NOT NULL,
  `fecha_oferta` date NOT NULL,
  `imagen` varchar(250) COLLATE utf8_spanish2_ci DEFAULT NULL,
  `precio` double NOT NULL,
  `estado` varchar(11) COLLATE utf8_spanish2_ci NOT NULL,
  `empresa_asociada` varchar(9) COLLATE utf8_spanish2_ci NOT NULL,
  `categoria_asociada` int(11) NOT NULL,
  PRIMARY KEY (`id_oferta`),
  KEY `empresa_asociada` (`empresa_asociada`),
  KEY `categoria_asociada` (`categoria_asociada`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `oferta`
--

INSERT INTO `oferta` (`id_oferta`, `titulo`, `descripcion`, `fecha_oferta`, `imagen`, `precio`, `estado`, `empresa_asociada`, `categoria_asociada`) VALUES
(1, 'Mochillo', 'Mochillo electrica lista para usar.', '2020-05-16', '2020-5-16_11-52-28-150_1972.jpeg', 30000, 'ACTIVA', '66666666F', 5),
(2, 'Mascarillas', 'Mascarillas para el COVID19.', '2020-05-16', '2020-5-16_11-57-10-628_7712.jpeg', 6, 'ACTIVA', '66666666F', 4),
(3, 'Cena', 'Cena para 2 en restaurante, con menu y buen vino.', '2020-05-16', '2020-5-16_0-0-58-746_7848.jpeg', 50, 'COMPRADA', '55555555E', 3),
(4, 'Serranito', 'Serranito 2x1', '2020-05-16', '2020-5-16_0-1-45-693_5408.jpeg', 2.5, 'ACTIVA', '55555555E', 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

DROP TABLE IF EXISTS `usuario`;
CREATE TABLE IF NOT EXISTS `usuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `usuario` varchar(60) COLLATE utf8_spanish2_ci NOT NULL,
  `clave` varchar(255) COLLATE utf8_spanish2_ci NOT NULL,
  `tipo` varchar(11) COLLATE utf8_spanish2_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id`, `usuario`, `clave`, `tipo`) VALUES
(1, 'Dani', '1234', 'EMPLEADO'),
(2, 'Josema', '1234', 'EMPLEADO'),
(3, 'Manuel', '1234', 'EMPLEADO'),
(4, 'Antonio', '1234', 'CLIENTE'),
(5, 'Bodeguita', '1234', 'EMPRESA'),
(6, 'Sofi', '1234', 'EMPRESA'),
(7, 'Claudia', '1234', 'CLIENTE');

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD CONSTRAINT `fk_clientes` FOREIGN KEY (`usuario_asociado`) REFERENCES `usuario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `compra`
--
ALTER TABLE `compra`
  ADD CONSTRAINT `fk_compras_cliente` FOREIGN KEY (`cliente_asociado`) REFERENCES `cliente` (`dni`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_compras_oferta` FOREIGN KEY (`oferta_asociada`) REFERENCES `oferta` (`id_oferta`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD CONSTRAINT `fk_empleados_dept` FOREIGN KEY (`departamento`) REFERENCES `departamento` (`dept_no`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_empleados_usuario` FOREIGN KEY (`usuario_asociado`) REFERENCES `usuario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `empresa`
--
ALTER TABLE `empresa`
  ADD CONSTRAINT `fk_empresas` FOREIGN KEY (`usuario_asociado`) REFERENCES `usuario` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `oferta`
--
ALTER TABLE `oferta`
  ADD CONSTRAINT `fk2_ofertas` FOREIGN KEY (`categoria_asociada`) REFERENCES `categoria` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_ofertas` FOREIGN KEY (`empresa_asociada`) REFERENCES `empresa` (`nif`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
