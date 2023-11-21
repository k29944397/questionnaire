CREATE TABLE IF NOT EXISTS `questtionnaire`.`user` (
  `name` VARCHAR(20) NULL DEFAULT NULL,
  `phone_number` VARCHAR(45) NOT NULL,
  `email` VARCHAR(20) NULL DEFAULT NULL,
  `age` INT NULL DEFAULT 0,
  `q_id` INT NOT NULL,
  `qn_id` INT NOT NULL,
  `ans` VARCHAR(60) NULL DEFAULT NULL,
  PRIMARY KEY (`phone_number`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;
