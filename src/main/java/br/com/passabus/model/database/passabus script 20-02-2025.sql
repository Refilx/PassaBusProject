-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema passabus
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema passabus
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `passabus` DEFAULT CHARACTER SET utf8 ;
USE `passabus` ;

-- -----------------------------------------------------
-- Table `passabus`.`pessoa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `passabus`.`pessoa` (
  `idPessoa` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(180) NOT NULL,
  `CPF` VARCHAR(14) NOT NULL,
  `email` VARCHAR(180) NOT NULL,
  `telefone` VARCHAR(15) NOT NULL,
  `dtNascimento` DATE NOT NULL,
  PRIMARY KEY (`idPessoa`),
  UNIQUE INDEX `idpessoa_UNIQUE` (`idPessoa` ASC) VISIBLE,
  UNIQUE INDEX `CPF_UNIQUE` (`CPF` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  UNIQUE INDEX `telefone_UNIQUE` (`telefone` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `passabus`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `passabus`.`usuario` (
  `idUsuario` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(100) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `role` VARCHAR(13) NOT NULL,
  `dtRegistro` TIMESTAMP NOT NULL,
  `idPessoa` INT NOT NULL,
  PRIMARY KEY (`idUsuario`),
  UNIQUE INDEX `idUsuario_UNIQUE` (`idUsuario` ASC) VISIBLE,
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE,
  INDEX `fk_usuario_pessoa_idx` (`idPessoa` ASC) VISIBLE,
  CONSTRAINT `fk_usuario_pessoa`
    FOREIGN KEY (`idPessoa`)
    REFERENCES `passabus`.`pessoa` (`idPessoa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `passabus`.`viacao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `passabus`.`viacao` (
  `idViacao` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(120) NOT NULL,
  `CNPJ` VARCHAR(18) NOT NULL,
  `sede` VARCHAR(250) NOT NULL,
  PRIMARY KEY (`idViacao`),
  UNIQUE INDEX `CNPJ_UNIQUE` (`CNPJ` ASC) VISIBLE,
  UNIQUE INDEX `nome_UNIQUE` (`nome` ASC) VISIBLE,
  UNIQUE INDEX `idViacao_UNIQUE` (`idViacao` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `passabus`.`viagem`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `passabus`.`viagem` (
  `idViagem` INT NOT NULL AUTO_INCREMENT,
  `linha` VARCHAR(45) NOT NULL,
  `tipoViagem` VARCHAR(45) NOT NULL,
  `classe` VARCHAR(45) NOT NULL,
  `idViacao` INT NOT NULL,
  PRIMARY KEY (`idViagem`),
  UNIQUE INDEX `idViagem_UNIQUE` (`idViagem` ASC) VISIBLE,
  INDEX `fk_viagem_viacao1_idx` (`idViacao` ASC) VISIBLE,
  CONSTRAINT `fk_viagem_viacao1`
    FOREIGN KEY (`idViacao`)
    REFERENCES `passabus`.`viacao` (`idViacao`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `passabus`.`passageiro`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `passabus`.`passageiro` (
  `idPassageiro` INT NOT NULL AUTO_INCREMENT,
  `poltrona` INT NOT NULL,
  `origem` VARCHAR(100) NOT NULL,
  `destino` VARCHAR(100) NOT NULL,
  `idPessoa` INT NOT NULL,
  `idViagem` INT NOT NULL,
  PRIMARY KEY (`idPassageiro`),
  UNIQUE INDEX `idPassageiro_UNIQUE` (`idPassageiro` ASC) VISIBLE,
  INDEX `fk_passageiro_pessoa1_idx` (`idPessoa` ASC) VISIBLE,
  INDEX `fk_passageiro_viagem1_idx` (`idViagem` ASC) VISIBLE,
  CONSTRAINT `fk_passageiro_pessoa1`
    FOREIGN KEY (`idPessoa`)
    REFERENCES `passabus`.`pessoa` (`idPessoa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_passageiro_viagem1`
    FOREIGN KEY (`idViagem`)
    REFERENCES `passabus`.`viagem` (`idViagem`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `passabus`.`venda`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `passabus`.`venda` (
  `idVenda` INT NOT NULL AUTO_INCREMENT,
  `taxaEmbarque` DOUBLE NOT NULL,
  `tarifa` DOUBLE NOT NULL,
  `seguro` DOUBLE NOT NULL,
  `valorTotal` DOUBLE NOT NULL,
  `dtVenda` TIMESTAMP NOT NULL,
  `opcaoPagamento` VARCHAR(17) NOT NULL,
  `idPassageiro` INT NOT NULL,
  `idViagem` INT NOT NULL,
  PRIMARY KEY (`idVenda`),
  UNIQUE INDEX `idVenda_UNIQUE` (`idVenda` ASC) VISIBLE,
  INDEX `fk_venda_passageiro1_idx` (`idPassageiro` ASC) VISIBLE,
  INDEX `fk_venda_viagem1_idx` (`idViagem` ASC) VISIBLE,
  CONSTRAINT `fk_venda_passageiro1`
    FOREIGN KEY (`idPassageiro`)
    REFERENCES `passabus`.`passageiro` (`idPassageiro`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_venda_viagem1`
    FOREIGN KEY (`idViagem`)
    REFERENCES `passabus`.`viagem` (`idViagem`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
