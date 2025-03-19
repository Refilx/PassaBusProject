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
  UNIQUE INDEX `CPF_UNIQUE` (`CPF` ASC) VISIBLE)
ENGINE = InnoDB;

--
-- Dumping data for table `pessoa`
--

LOCK TABLES `pessoa` WRITE;
/*!40000 ALTER TABLE `pessoa` DISABLE KEYS */;
INSERT INTO `pessoa` VALUES (1,'Bruno Sousa','091.319.945-12','brunosillva1079@gmail.com','(73)98835-9652','2002-08-24'),(2,'Bruno Sousa','123.456.789-09','jovenestudantex11@gmail.com','(73)98835-9652','2002-08-24');
/*!40000 ALTER TABLE `pessoa` ENABLE KEYS */;
UNLOCK TABLES;


-- -----------------------------------------------------
-- Table `passabus`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `passabus`.`usuario` (
  `idUsuario` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(100) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `role` VARCHAR(13) NOT NULL,
  `dtRegistro` TIMESTAMP NOT NULL,
  `status` VARCHAR(10) NOT NULL,
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

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'bruno.silva','5D7J8EfTY7PhB+OjFrBDHwoKJk7nD+AS','Administrador','2025-03-11 03:00:00','Ativo',1),(2,'bruno.operador','5D7J8EfTY7PhB+OjFrBDHwoKJk7nD+AS','Operador','2025-03-11 03:00:00','Ativo',2);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;


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


--
-- Dumping data for table `viacao`
--

LOCK TABLES `viacao` WRITE;
/*!40000 ALTER TABLE `viacao` DISABLE KEYS */;
INSERT INTO `viacao` VALUES (1,'PASSABUS TRANSPORTES LTDA','52.853.083/0001-65','R. Prof. Paulo Freire, 56-84 - Jaguar, Jaguaquara - BA, 45345-000');
/*!40000 ALTER TABLE `viacao` ENABLE KEYS */;
UNLOCK TABLES;


-- -----------------------------------------------------
-- Table `passabus`.`viagem`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `passabus`.`viagem` (
  `idViagem` INT NOT NULL AUTO_INCREMENT,
  `origem` VARCHAR(150) NOT NULL,
  `destino` VARCHAR(150) NOT NULL,
  `distancia` DOUBLE NOT NULL,
  `linha` VARCHAR(45) NOT NULL,
  `tipoViagem` VARCHAR(45) NOT NULL,
  `classe` VARCHAR(45) NOT NULL,
  `horario` TIME NOT NULL,
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

--
-- Dumping data for table `viagem`
--

LOCK TABLES `viagem` WRITE;
/*!40000 ALTER TABLE `viagem` DISABLE KEYS */;
INSERT INTO `viagem` VALUES (1,'ITAPETINGA','VITÓRIA DA CONQUISTA',100,'ITABUNA - VITÓRIA DA CONQUISTA','ORDINÁRIA','CONVENCIONAL','08:00:00',1);
/*!40000 ALTER TABLE `viagem` ENABLE KEYS */;
UNLOCK TABLES;


-- -----------------------------------------------------
-- Table `passabus`.`passageiro`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `passabus`.`passageiro` (
  `idPassageiro` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(180) NOT NULL,
  `CPF` VARCHAR(14) NOT NULL,
  `dtNascimento` DATE NOT NULL,
  `poltrona` INT NOT NULL,
  `origem` VARCHAR(150) NOT NULL,
  `destino` VARCHAR(150) NOT NULL,
  `dataviagem` DATE NOT NULL,
  `idViagem` INT NOT NULL,
  PRIMARY KEY (`idPassageiro`),
  UNIQUE INDEX `idPassageiro_UNIQUE` (`idPassageiro` ASC) VISIBLE,
  INDEX `fk_passageiro_viagem1_idx` (`idViagem` ASC) VISIBLE,
  CONSTRAINT `fk_passageiro_viagem1`
    FOREIGN KEY (`idViagem`)
    REFERENCES `passabus`.`viagem` (`idViagem`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

--
-- Dumping data for table `passageiro`
--

LOCK TABLES `passageiro` WRITE;
/*!40000 ALTER TABLE `passageiro` DISABLE KEYS */;
INSERT INTO `passageiro` VALUES (1,'Bruno Lima','123.456.789-09','2000-06-22',29,'ITAPETINGA','VITÓRIA DA CONQUISTA','2025-03-14',1),(2,'Lucas Marques','070.376.785-20','2000-09-20',5,'ITAPETINGA','VITÓRIA DA CONQUISTA','2025-03-14',1),(3,'Gabriel Antônio','377.520.255-26','1983-03-02',21,'ITAPETINGA','VITÓRIA DA CONQUISTA','2025-03-14',1),(4,'Vitor Furtado','007.067.355-10','2006-02-06',40,'ITAPETINGA','VITÓRIA DA CONQUISTA','2025-03-14',1),(5,'Bruno Lima','123.456.789-09','2000-06-22',21,'ITAPETINGA','VITÓRIA DA CONQUISTA','2025-02-15',1),(6,'Bruno Lima','123.456.789-09','2000-06-22',36,'ITAPETINGA','VITÓRIA DA CONQUISTA','2025-05-12',1),(7,'Bruno Lira','123.456.789-09','2000-06-22',21,'ITAPETINGA','VITÓRIA DA CONQUISTA','2025-05-12',1),(8,'Bruno Lira','123.456.789-09','2000-06-22',23,'ITAPETINGA','VITÓRIA DA CONQUISTA','2025-05-12',1),(9,'Bruno Lira','123.456.789-09','2000-06-22',38,'ITAPETINGA','VITÓRIA DA CONQUISTA','2025-05-12',1),(10,'Bruno Lira','123.456.789-09','2000-06-22',40,'ITAPETINGA','VITÓRIA DA CONQUISTA','2025-05-12',1),(11,'Bruno Sousa','123.456.789-09','2002-08-24',25,'ITAPETINGA','VITÓRIA DA CONQUISTA','2025-03-30',1);
/*!40000 ALTER TABLE `passageiro` ENABLE KEYS */;
UNLOCK TABLES;


-- -----------------------------------------------------
-- Table `passabus`.`venda`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `passabus`.`venda` (
  `idVenda` INT NOT NULL AUTO_INCREMENT,
  `tarifa` DOUBLE NOT NULL,
  `seguro` DOUBLE NOT NULL,
  `valorTotal` DOUBLE NOT NULL,
  `dtVenda` TIMESTAMP NOT NULL,
  `opcaoPagamento` VARCHAR(17) NOT NULL,
  `status` VARCHAR(15) NOT NULL,
  `bilhete` BIGINT NOT NULL,
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

--
-- Dumping data for table `venda`
--

LOCK TABLES `venda` WRITE;
/*!40000 ALTER TABLE `venda` DISABLE KEYS */;
INSERT INTO `venda` VALUES (2,5,2,37,'2025-03-14 02:01:42','Dinheiro','EM VIGOR',1741917701690,6,1),(3,5,2,37,'2025-03-14 02:05:37','Dinheiro','EM VIGOR',1741917937235,7,1),(4,5,2,37,'2025-03-14 02:08:57','Dinheiro','EM VIGOR',1741918137188,8,1),(5,5,2,37,'2025-03-14 02:10:52','Dinheiro','CANCELADA',1741918252055,9,1),(6,5,2,37,'2025-03-14 02:14:17','Cartão de Crédito','EM VIGOR',1741918457380,10,1),(7,5,2,37,'2025-03-17 21:39:54','Dinheiro','EM VIGOR',1742247594177,11,1);
/*!40000 ALTER TABLE `venda` ENABLE KEYS */;
UNLOCK TABLES;


-- -----------------------------------------------------
-- Table `passabus`.`log`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `passabus`.`log` (
  `idLog` INT NOT NULL AUTO_INCREMENT,
  `dtLogin` TIMESTAMP NULL,
  `dtLogout` TIMESTAMP NULL,
  `idUsuario` INT NOT NULL,
  PRIMARY KEY (`idLog`),
  UNIQUE INDEX `idLog_UNIQUE` (`idLog` ASC) VISIBLE,
  INDEX `fk_log_usuario1_idx` (`idUsuario` ASC) VISIBLE,
  CONSTRAINT `fk_log_usuario1`
    FOREIGN KEY (`idUsuario`)
    REFERENCES `passabus`.`usuario` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

--
-- Dumping data for table `log`
--

LOCK TABLES `log` WRITE;
/*!40000 ALTER TABLE `log` DISABLE KEYS */;
INSERT INTO `log` VALUES (1,'2025-03-14 03:03:47','2025-03-14 03:04:03',2),(2,'2025-03-14 03:04:15','2025-03-14 03:05:40',1),(3,'2025-03-14 19:25:08',NULL,1),(4,'2025-03-17 22:15:51','2025-03-17 22:18:03',2);
/*!40000 ALTER TABLE `log` ENABLE KEYS */;
UNLOCK TABLES;


USE `passabus` ;

-- -----------------------------------------------------
-- Placeholder table for view `passabus`.`ultimo_logado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `passabus`.`ultimo_logado` (`idLog` INT, `idUsuario` INT, `username` INT, `role` INT);

-- -----------------------------------------------------
-- Placeholder table for view `passabus`.`destinoPopular`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `passabus`.`destinoPopular` (`quantViagem` INT, `destino` INT);

-- -----------------------------------------------------
-- Placeholder table for view `passabus`.`quantidadeVendida`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `passabus`.`quantidadeVendida` (`quantidadeVendida` INT);

-- -----------------------------------------------------
-- View `passabus`.`ultimo_logado`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `passabus`.`ultimo_logado`;
USE `passabus`;
CREATE  OR REPLACE VIEW ultimo_logado AS
	SELECT L.idLog, L.idUsuario, U.username, U.role 
	FROM log L
	JOIN usuario U ON (L.idUsuario = U.idUsuario)
	ORDER BY idLog DESC
	LIMIT 1;

-- -----------------------------------------------------
-- View `passabus`.`destinoPopular`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `passabus`.`destinoPopular`;
USE `passabus`;
CREATE  OR REPLACE VIEW destinoPopular AS
	SELECT count(VD.idViagem) AS quantViagem, VG.destino
	FROM venda VD
	JOIN viagem VG ON (VD.idViagem = VG.idViagem)
	GROUP BY VG.destino
	ORDER BY quantViagem DESC limit 1;

-- -----------------------------------------------------
-- View `passabus`.`quantidadeVendida`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `passabus`.`quantidadeVendida`;
USE `passabus`;
CREATE  OR REPLACE VIEW quantidadeVendida AS
	SELECT count(idVenda) AS quantidadeVendida
	FROM venda
	WHERE MONTH(dtVenda) = MONTH(CURDATE())
	  AND YEAR(dtVenda) = YEAR(CURDATE());

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
