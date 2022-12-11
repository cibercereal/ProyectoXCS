
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
DROP TABLE IF EXISTS `User`;

CREATE TABLE `User`(
                       `login` varchar(70) COLLATE utf8mb4_unicode_ci NOT NULL,
                       `password` varchar(70) COLLATE utf8mb4_unicode_ci NOT NULL,
                       PRIMARY KEY (`login`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

DROP TABLE IF EXISTS `Story`;

CREATE TABLE `Story` (
                         `id` int(11) NOT NULL AUTO_INCREMENT,
                         `author` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                         `content` varchar(1000) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                         `date` datetime DEFAULT NULL,
                         `genre` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                         `mainTheme` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                         `published` bit(1) NOT NULL,
                         `secondaryTheme` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                         `title` varchar(70) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                         PRIMARY KEY (`id`),
                         KEY `STORY_AUTHOR_FK` (`author`),
                         CONSTRAINT `FKm6topghg00xrv2ulrwsv354wg` FOREIGN KEY (`author`) REFERENCES `User` (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


--
-- Table structure for table `visitDate`
--

DROP TABLE IF EXISTS `VisitDate`;

CREATE TABLE `VisitDate`
(
    `storyId` INT NOT NULL,
    `visitDate` datetime NULL,
    KEY `STORY_AUTHOR_FK` (`storyId`),
    CONSTRAINT `VisitDate_id_FK` FOREIGN KEY (`storyId`) REFERENCES `Story` (`id`)
);


--
-- Dumping data for table `story`
--
LOCK TABLES `User` WRITE;

INSERT INTO `User` (`login`,`password`) VALUES ('jpsilva17','df278d72f388a75c065302f7c826c349');
INSERT INTO `User` (`login`,`password`) VALUES ('bdalvarez','72e785a24c48380ed74d051de0968606');
INSERT INTO `User` (`login`,`password`) VALUES ('asfeijoo','38c6cef43f2e27fd75b36670a5017b43');
INSERT INTO `User` (`login`,`password`) VALUES ('pmperez','19571908a528c7cbb98da693915cb2c5');
INSERT INTO `User` (`login`,`password`) VALUES ('bcgonzalez4','553e466d2707422f3083f4c7dd3802a5');
INSERT INTO `User` (`login`,`password`) VALUES ('ncgomez17','df278d72f388a75c065302f7c536c349');


UNLOCK TABLES;
LOCK TABLES `Story` WRITE;
INSERT INTO `Story` VALUES (1,'jpsilva17','Nam vel semper lacus. Pellentesque a turpis odio. Aliquam cursus massa id diam feugiat, sed rutrum massa hendrerit. Nam eget placerat lacus, nec lobortis nibh. Mauris a orci et risus convallis blandit quis id est. Aliquam ex nulla, vestibulum ac elit non, bibendum euismod orci. Mauris lectus tortor, pulvinar vel sem at, dictum ullamcorper lorem. In sed dolor in diam iaculis pretium vel sed leo. Etiam nec mollis risus, ac ornare erat.','2022-11-10 00:00:00','STORY','SUSPENSE',true,'HISTORIC','Titulo1'),
                           (2,'bdalvarez','Nunc volutpat vehicula dui vitae imperdiet. Nam quis sapien tortor. Vivamus dignissim mauris a nisi imperdiet varius. Proin a urna ullamcorper risus luctus malesuada. Morbi non posuere est.','2022-11-11 00:00:00','STORY','ADVENTURE',true,'ROMANCE','Titulo2'),
                           (3,'asfeijoo','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce sagittis, tortor vitae accumsan imperdiet, massa massa sagittis sapien, quis varius erat nisi et erat.','2022-11-12 00:00:00','NANOSTORY','HORROR',true,'HISTORIC','Titulo3'),
                           (4,'pmperez','Maecenas et fringilla lectus, ut molestie elit. Nulla sagittis tellus vehicula accumsan dignissim. Proin vulputate lectus lacus. ','2022-11-13 00:00:00','NANOSTORY','ADVENTURE',true,'ROMANCE','Titulo4'),
                           (5,'bcgonzalez4','Nulla varius cursus volutpat. Aliquam et ipsum lacus. Vivamus dictum ullamcorper turpis nec feugiat. Quisque posuere lectus eget dui molestie, eget interdum nulla laoreet','2022-11-14 00:00:00','POETRY','SCIENCE_FICTION',true,'HISTORIC','Titulo5'),
                           (6,'ncgomez17','Morbi tempus quam at felis efficitur, sed iaculis sapien feugiat. Nulla vehicula eleifend sapien, non.','2022-11-15 00:00:00','POETRY','ADVENTURE',true,'ROMANCE','Titulo6');
LOCK TABLES `VisitDate` WRITE;
INSERT INTO VisitDate(storyId, visitDate)
VALUES  (1, '2022-09-01 01:05:00'),
        (1, '2022-09-03 13:01:20'),
        (1, '2022-09-09 21:22:45');
UNLOCK TABLES;