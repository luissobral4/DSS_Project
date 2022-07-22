-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema DSS_Project
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema DSS_Project
-- -----------------------------------------------------
DROP SCHEMA `DSS_Project` ;
CREATE SCHEMA IF NOT EXISTS `DSS_Project` DEFAULT CHARACTER SET utf8 ;
USE `DSS_Project` ;

-- -----------------------------------------------------
-- Table `DSS_Project`.`Prateleira`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DSS_Project`.`Prateleira` (
  `prateleiraID` INT NOT NULL,
  `capacidade` INT NULL,
  `ocupacao` INT NULL,
  PRIMARY KEY (`prateleiraID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DSS_Project`.`Localizacao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DSS_Project`.`Localizacao` (
  `idLocalizacao` INT NOT NULL,
  `zonaID` VARCHAR(20) NULL,
  `Prateleira_prateleiraID` INT NULL,
  PRIMARY KEY (`idLocalizacao`),
  INDEX `fk_Localizacao_Prateleira1_idx` (`Prateleira_prateleiraID` ASC) VISIBLE,
  CONSTRAINT `fk_Localizacao_Prateleira1`
    FOREIGN KEY (`Prateleira_prateleiraID`)
    REFERENCES `DSS_Project`.`Prateleira` (`prateleiraID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DSS_Project`.`Palete`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DSS_Project`.`Palete` (
  `qrCode` VARCHAR(10) NOT NULL,
  `Localizacao_idLocalizacao` INT NOT NULL,
  PRIMARY KEY (`qrCode`),
  INDEX `fk_Palete_Localizacao1_idx` (`Localizacao_idLocalizacao` ASC) VISIBLE,
  CONSTRAINT `fk_Palete_Localizacao1`
    FOREIGN KEY (`Localizacao_idLocalizacao`)
    REFERENCES `DSS_Project`.`Localizacao` (`idLocalizacao`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DSS_Project`.`InfoTransporte`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DSS_Project`.`InfoTransporte` (
  `idInfoTransporte` INT NOT NULL,
  `Palete_qrCode` VARCHAR(10) NOT NULL,
  `Prateleira_prateleiraID` INT NOT NULL,
  PRIMARY KEY (`idInfoTransporte`),
  INDEX `fk_InfoTransporte_Palete1_idx` (`Palete_qrCode` ASC) VISIBLE,
  INDEX `fk_InfoTransporte_Prateleira1_idx` (`Prateleira_prateleiraID` ASC) VISIBLE,
  CONSTRAINT `fk_InfoTransporte_Palete1`
    FOREIGN KEY (`Palete_qrCode`)
    REFERENCES `DSS_Project`.`Palete` (`qrCode`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_InfoTransporte_Prateleira1`
    FOREIGN KEY (`Prateleira_prateleiraID`)
    REFERENCES `DSS_Project`.`Prateleira` (`prateleiraID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DSS_Project`.`Robot`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DSS_Project`.`Robot` (
  `RobotID` INT NOT NULL,
  `Disponivel` INT NULL,
  `Recolheu` INT NULL,
  `InfoTransporte_idInfoTransporte` INT NULL,
  `Localizacao_idLocalizacao` INT NOT NULL,
  PRIMARY KEY (`RobotID`),
  INDEX `fk_Robot_InfoTransporte1_idx` (`InfoTransporte_idInfoTransporte` ASC) VISIBLE,
  INDEX `fk_Robot_Localizacao1_idx` (`Localizacao_idLocalizacao` ASC) VISIBLE,
  CONSTRAINT `fk_Robot_InfoTransporte1`
    FOREIGN KEY (`InfoTransporte_idInfoTransporte`)
    REFERENCES `DSS_Project`.`InfoTransporte` (`idInfoTransporte`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Robot_Localizacao1`
    FOREIGN KEY (`Localizacao_idLocalizacao`)
    REFERENCES `DSS_Project`.`Localizacao` (`idLocalizacao`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DSS_Project`.`Gestor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DSS_Project`.`Gestor` (
  `idGestor` INT NOT NULL,
  `Nome` VARCHAR(30) NULL,
  `Password` VARCHAR(30) NULL,
  `Online` INT NULL,
  PRIMARY KEY (`idGestor`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
