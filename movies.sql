-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: db
-- Tiempo de generación: 21-10-2024 a las 14:31:18
-- Versión del servidor: 8.0.40
-- Versión de PHP: 8.2.23

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `movies`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `movies`
--

CREATE TABLE `movies` (
  `id` bigint NOT NULL,
  `title` varchar(200) NOT NULL,
  `description` text,
  `year` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `movies`
--

INSERT INTO `movies` (`id`, `title`, `description`, `year`) VALUES
(1, 'Harry Potter', 'Abada kedabraaa', 2001),
(2, 'Back To The Future', 'Delorean goes brrrr', 1987),
(3, 'The Matrix', 'What is the Matrix?', 1999),
(4, 'Inception', 'A mind-bending thriller about dreams within dreams', 2010),
(5, 'The Dark Knight', 'Batman faces off against the Joker', 2008),
(6, 'Pulp Fiction', 'Multiple interconnected stories of crime and redemption', 1994),
(7, 'The Lord of the Rings: The Fellowship of the Ring', 'A hobbit begins an epic journey to destroy a powerful ring', 2001),
(8, 'The Godfather', 'A mafia family struggles for power in New York', 1972),
(9, 'Forrest Gump', 'A man with a low IQ experiences historical events', 1994),
(10, 'Fight Club', 'A disillusioned man forms an underground fight club', 1999),
(11, 'Jurassic Park', 'Scientists clone dinosaurs with disastrous results', 1993),
(12, 'Star Wars: A New Hope', 'A young farm boy becomes a hero in a galaxy far, far away', 1977),
(13, 'Gladiator', 'A Roman general seeks revenge after being betrayed', 2000),
(14, 'Avatar', 'A paraplegic marine is sent to a moon inhabited by a native tribe', 2009),
(15, 'The Avengers', 'Earth’s mightiest heroes team up to save the planet', 2012),
(16, 'Titanic', 'A romance aboard the ill-fated ship', 1997),
(17, 'The Shawshank Redemption', 'A man wrongly imprisoned fights for his freedom', 1994),
(18, 'Interstellar', 'A team of explorers travel through a wormhole in space', 2014),
(19, 'Braveheart', 'A Scottish warrior leads a rebellion against England', 1995),
(20, 'The Lion King', 'A young lion prince flees his kingdom after the murder of his father', 1994);


--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `movies`
--
ALTER TABLE `movies`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `movies`
--
ALTER TABLE `movies`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
