DROP DATABASE IF EXISTS `dgss2223_teamA_microstories`;
CREATE DATABASE `dgss2223_teamA_microstories` CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE `dgss2223_teamA_microstories`;

--
-- User creation
--

-- Below MySql 5.7.6, doesn't work in MySQL 8
GRANT ALL ON `dgss2223_teamA_microstories`.* TO 'microstories'@'localhost' IDENTIFIED BY 'microstoriespass';

-- MySql 5.7.6 and above
-- CREATE USER microstories@'localhost' IDENTIFIED BY 'microstoriespass';

GRANT ALL PRIVILEGES ON dgss2223_teamA_microstories.* TO microstories@'localhost';
FLUSH PRIVILEGES;

--
-- Table structure for table `story`
--

DROP TABLE IF EXISTS `story`;

CREATE TABLE `story` (
                         `id` int(11) NOT NULL AUTO_INCREMENT,
                         `author` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                         `content` varchar(1000) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                         `date` datetime DEFAULT NULL,
                         `genre` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                         `mainTheme` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                         `published` bit(1) NOT NULL,
                         `secondaryTheme` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                         `title` varchar(70) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `story`
--

LOCK TABLES `story` WRITE;
INSERT INTO `story` VALUES (1,'autor','Nam vel semper lacus. Pellentesque a turpis odio. Aliquam cursus massa id diam feugiat, sed rutrum massa hendrerit. Nam eget placerat lacus, nec lobortis nibh. Mauris a orci et risus convallis blandit quis id est. Aliquam ex nulla, vestibulum ac elit non, bibendum euismod orci. Mauris lectus tortor, pulvinar vel sem at, dictum ullamcorper lorem. In sed dolor in diam iaculis pretium vel sed leo. Etiam nec mollis risus, ac ornare erat.','2022-11-10 00:00:00','STORY','SUSPENSE','','HISTORIC','Titulo1'),(2,'escritor','Nunc volutpat vehicula dui vitae imperdiet. Nam quis sapien tortor. Vivamus dignissim mauris a nisi imperdiet varius. Proin a urna ullamcorper risus luctus malesuada. Morbi non posuere est.','2022-11-11 00:00:00','STORY','ADVENTURE','','ROMANCE','Titulo2'),(3,'escritora','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce sagittis, tortor vitae accumsan imperdiet, massa massa sagittis sapien, quis varius erat nisi et erat.','2022-11-12 00:00:00','NANOSTORY','HORROR','','HISTORIC','Titulo3'),(4,'autora','Maecenas et fringilla lectus, ut molestie elit. Nulla sagittis tellus vehicula accumsan dignissim. Proin vulputate lectus lacus. ','2022-11-13 00:00:00','NANOSTORY','ADVENTURE','','ROMANCE','Titulo4'),(5,'poetisa','Nulla varius cursus volutpat. Aliquam et ipsum lacus. Vivamus dictum ullamcorper turpis nec feugiat. Quisque posuere lectus eget dui molestie, eget interdum nulla laoreet','2022-11-14 00:00:00','POETRY','SCIENCE_FICTION','','HISTORIC','Titulo5'),(6,'poeta','Morbi tempus quam at felis efficitur, sed iaculis sapien feugiat. Nulla vehicula eleifend sapien, non.','2022-11-15 00:00:00','POETRY','ADVENTURE','','ROMANCE','Titulo6');
UNLOCK TABLES;