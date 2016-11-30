-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.5.16 - Source distribution
-- Server OS:                    Linux
-- HeidiSQL Version:             9.4.0.5138
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for equipo
DROP DATABASE IF EXISTS `equipo`;
CREATE DATABASE IF NOT EXISTS `equipo` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `equipo`;

-- Dumping structure for procedure equipo.ActualizarPersona
DROP PROCEDURE IF EXISTS `ActualizarPersona`;
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `ActualizarPersona`(
	IN `PidPersona` INT,
	IN `PNombre` VARCHAR(50),
	IN `PEquipo` VARCHAR(50)

,
	IN `PNacimiento` DATE,
	IN `PRuta` VARCHAR(200)


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

-- Dumping structure for procedure equipo.BorrarPersona
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

-- Dumping structure for procedure equipo.ConsultarPersona
DROP PROCEDURE IF EXISTS `ConsultarPersona`;
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `ConsultarPersona`()
BEGIN

select * from persona;

END//
DELIMITER ;

-- Dumping structure for procedure equipo.InsertarPersona
DROP PROCEDURE IF EXISTS `InsertarPersona`;
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `InsertarPersona`(
	IN `PNombre` VARCHAR(50)
,
	IN `PEquipo` VARCHAR(50),
	IN `PNacimiento` DATE,
	IN `PRuta` VARCHAR(200)


)
BEGIN

insert into persona(Nombre, Equipo, Nacimiento, Ruta)
values(PNombre, PEquipo, PNacimiento, PRuta);

END//
DELIMITER ;

-- Dumping structure for table equipo.persona
DROP TABLE IF EXISTS `persona`;
CREATE TABLE IF NOT EXISTS `persona` (
  `idPersona` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(50) NOT NULL,
  `Equipo` varchar(50) NOT NULL,
  `Nacimiento` date NOT NULL,
  `Ruta` varchar(200) NOT NULL,
  PRIMARY KEY (`idPersona`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=latin1 COMMENT='datos de las personas del equipo';

-- Dumping data for table equipo.persona: ~10 rows (approximately)
/*!40000 ALTER TABLE `persona` DISABLE KEYS */;
REPLACE INTO `persona` (`idPersona`, `Nombre`, `Equipo`, `Nacimiento`, `Ruta`) VALUES
	(15, 'Diego Rosa', 'Astana', '1989-03-27', '/home/juansantana/Archivos/Captura15.PNG'),
	(16, 'Nairo Quintana', 'Movistar', '2000-01-01', '/home/juansantana/Archivos/Captura.PNG'),
	(18, 'Chris Frome', 'Sky', '1985-05-20', '/home/juansantana/Archivos/Captura3.PNG'),
	(19, 'Alejandro Valverde', 'Movistar', '1980-04-25', '/home/juansantana/Archivos/Captura1.PNG'),
	(20, 'Ion Izaguirre', 'Movistar', '2000-01-01', '/home/juansantana/Archivos/Captura2.PNG'),
	(21, 'Sergio Luis Henao', 'Sky', '2000-01-01', '/home/juansantana/Archivos/Captura5.PNG'),
	(22, 'Luke Rowe', 'Sky', '1990-03-10', '/home/juansantana/Archivos/Captura6.PNG'),
	(23, 'Richi Porte', 'Astana', '1985-01-30', '/home/juansantana/Archivos/Captura7.PNG'),
	(24, 'Brent Bookwalter', 'Astana', '1984-02-16', '/home/juansantana/Archivos/Captura8.PNG'),
	(25, 'Michael Shär', 'Astana', '1986-09-29', '/home/juansantana/Archivos/Captura9.PNG');
/*!40000 ALTER TABLE `persona` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
