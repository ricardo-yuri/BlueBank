SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';


-- Schema bluebank
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema bluebank
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `bluebank` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `bluebank` ;

-- -----------------------------------------------------
-- Table `bluebank`.`tb_contact`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bluebank`.`tb_contact` (
  `id_contact` BIGINT NOT NULL AUTO_INCREMENT,
  `create_at` DATETIME(6) NULL DEFAULT NULL,
  `email` VARCHAR(100) NULL DEFAULT NULL,
  `telephone` VARCHAR(14) NULL DEFAULT NULL,
  PRIMARY KEY (`id_contact`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `bluebank`.`contact_cell`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bluebank`.`contact_cell` (
  `contact_id_contact` BIGINT NOT NULL,
  `cell` VARCHAR(14) NOT NULL,
  INDEX `FK2pm6ilsd6alw0mpi2c054aaii` (`contact_id_contact` ASC) VISIBLE,
  CONSTRAINT `FK2pm6ilsd6alw0mpi2c054aaii`
    FOREIGN KEY (`contact_id_contact`)
    REFERENCES `bluebank`.`tb_contact` (`id_contact`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `bluebank`.`historic`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bluebank`.`historic` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `create_at` DATETIME(6) NULL DEFAULT NULL,
  `id_account` BIGINT NOT NULL,
  `log` VARCHAR(255) NOT NULL,
  `operation_type` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `bluebank`.`tb_address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bluebank`.`tb_address` (
  `id_address` BIGINT NOT NULL AUTO_INCREMENT,
  `cep` VARCHAR(10) NULL DEFAULT NULL,
  `complement` VARCHAR(100) NULL DEFAULT NULL,
  `district` VARCHAR(100) NULL DEFAULT NULL,
  `locality` VARCHAR(100) NULL DEFAULT NULL,
  `public_place` VARCHAR(100) NULL DEFAULT NULL,
  `uf` VARCHAR(2) NOT NULL,
  PRIMARY KEY (`id_address`, `uf`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `bluebank`.`tb_client`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bluebank`.`tb_client` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `client_type` INT NOT NULL,
  `cpf` VARCHAR(11) NOT NULL,
  `create_at` DATETIME(6) NULL DEFAULT NULL,
  `deleted` TINYINT(1) NULL DEFAULT '0',
  `name` VARCHAR(200) NULL DEFAULT NULL,
  `rg` VARCHAR(20) NULL DEFAULT NULL,
  `id_account` BIGINT NULL DEFAULT NULL,
  `id_address` BIGINT NULL DEFAULT NULL,
  `id_contact` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UK_q5ytvlvjsf67yw11ydrh4xwrj` (`cpf` ASC) VISIBLE,
  INDEX `FK6e8f6mhvcp10d37xyclltu5r2` (`id_account` ASC) VISIBLE,
  INDEX `FKo9x7udlchfjjym78kq1b78bed` (`id_address` ASC) VISIBLE,
  INDEX `FK1qwpml4j2v5xev9ryb6lte7ih` (`id_contact` ASC) VISIBLE,
  CONSTRAINT `FK1qwpml4j2v5xev9ryb6lte7ih`
    FOREIGN KEY (`id_contact`)
    REFERENCES `bluebank`.`tb_contact` (`id_contact`),
  CONSTRAINT `FK6e8f6mhvcp10d37xyclltu5r2`
    FOREIGN KEY (`id_account`)
    REFERENCES `bluebank`.`tb_account` (`id_account`),
  CONSTRAINT `FKo9x7udlchfjjym78kq1b78bed`
    FOREIGN KEY (`id_address`)
    REFERENCES `bluebank`.`tb_address` (`id_address`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `bluebank`.`tb_account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bluebank`.`tb_account` (
  `id_account` BIGINT NOT NULL AUTO_INCREMENT,
  `account_balance` DOUBLE NULL DEFAULT NULL,
  `account_number` VARCHAR(20) NOT NULL,
  `account_type` INT NOT NULL,
  `agency` VARCHAR(20) NOT NULL,
  `create_at` DATETIME(6) NULL DEFAULT NULL,
  `deleted` TINYINT(1) NULL DEFAULT '0',
  `id_client` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`id_account`),
  UNIQUE INDEX `UK_abcgp1gb27xpjxtdtjremgfk3` (`account_number` ASC) VISIBLE,
  INDEX `FKqrscopq4kb7jk2681ha83enle` (`id_client` ASC) VISIBLE,
  CONSTRAINT `FKqrscopq4kb7jk2681ha83enle`
    FOREIGN KEY (`id_client`)
    REFERENCES `bluebank`.`tb_client` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `bluebank`.`tb_account_historic`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bluebank`.`tb_account_historic` (
  `account_id_account` BIGINT NOT NULL,
  `historic_id` BIGINT NOT NULL,
  UNIQUE INDEX `UK_currbw2h79il4d4p4ox69m7yd` (`historic_id` ASC) VISIBLE,
  INDEX `FKeejnmldittmbgq8u4bog4skmf` (`account_id_account` ASC) VISIBLE,
  CONSTRAINT `FKeejnmldittmbgq8u4bog4skmf`
    FOREIGN KEY (`account_id_account`)
    REFERENCES `bluebank`.`tb_account` (`id_account`),
  CONSTRAINT `FKlkuelc7kovg7wbpdwdunl291x`
    FOREIGN KEY (`historic_id`)
    REFERENCES `bluebank`.`historic` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
