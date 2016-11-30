-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         10.1.19-MariaDB - mariadb.org binary distribution
-- SO del servidor:              Win32
-- HeidiSQL Versión:             9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Volcando estructura de base de datos para equipo
DROP DATABASE IF EXISTS `equipo`;
CREATE DATABASE IF NOT EXISTS `equipo` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `equipo`;

-- Volcando estructura para procedimiento equipo.ActualizarPersona
DROP PROCEDURE IF EXISTS `ActualizarPersona`;
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `ActualizarPersona`(
	IN `PidPersona` INT,
	IN `PNombre` VARCHAR(50),
	IN `PEquipo` VARCHAR(50)

,
	IN `PNacimiento` DATE,
	IN `PRuta` VARCHAR(50)

)
BEGIN

update persona

set	Nombre=PNombre,
		Equipo=PEquipo,
		Nacimiento=PNacimiento,
		Ruta=PRuta
		
		
where idPersona = PidPersona;

END//
DELIMITER ;

-- Volcando estructura para procedimiento equipo.BorrarPersona
DROP PROCEDURE IF EXISTS `BorrarPersona`;
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `BorrarPersona`(
	IN `PidPersona` INT
)
BEGIN

delete from persona
where idPersona = PidPersona;

END//
DELIMITER ;

-- Volcando estructura para procedimiento equipo.ConsultarPersona
DROP PROCEDURE IF EXISTS `ConsultarPersona`;
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `ConsultarPersona`()
BEGIN

select * from persona;

END//
DELIMITER ;

-- Volcando estructura para procedimiento equipo.InsertarPersona
DROP PROCEDURE IF EXISTS `InsertarPersona`;
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `InsertarPersona`(
	IN `PNombre` VARCHAR(50)
,
	IN `PEquipo` VARCHAR(50),
	IN `PNacimiento` DATE,
	IN `PRuta` VARCHAR(50)

)
BEGIN

insert into persona(Nombre, Equipo, Nacimiento, Ruta)
values(PNombre, PEquipo, PNacimiento, PRuta);

END//
DELIMITER ;

-- Volcando estructura para tabla equipo.persona
DROP TABLE IF EXISTS `persona`;
CREATE TABLE IF NOT EXISTS `persona` (
  `idPersona` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(50) NOT NULL,
  `Equipo` varchar(50) NOT NULL,
  `Nacimiento` date NOT NULL,
  `Ruta` varchar(50) NOT NULL,
  PRIMARY KEY (`idPersona`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1 COMMENT='datos de las personas del equipo';

-- Volcando datos para la tabla equipo.persona: ~2 rows (aproximadamente)
DELETE FROM `persona`;
/*!40000 ALTER TABLE `persona` DISABLE KEYS */;
INSERT INTO `persona` (`idPersona`, `Nombre`, `Equipo`, `Nacimiento`, `Ruta`) VALUES
	(1, 'Juan Santana', 'Movistar', '1975-01-01', '2'),
	(2, 'Nairo Quintana', 'Sky', '2000-01-01', '2');
/*!40000 ALTER TABLE `persona` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
