CREATE TABLE IF EXISTS `questionnaire` (
  `id` int NOT NULL AUTO_INCREMENT,
  `uuid` varchar(45) NOT NULL,
  `title` varchar(60) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `is_published` tinyint DEFAULT '0',
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  PRIMARY KEY (`id`,`uuid`)
) 
ENGINE=InnoDB AUTO_INCREMENT=2 
DEFAULT CHARSET=utf8mb4 
COLLATE=utf8mb4_0900_ai_ci;