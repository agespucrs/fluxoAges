/***
* Scripts para criaÃ§Ã£o e insersÃ£o de dados
* Base Exemplo AgES_E
* Casssio Trindade
* 01/07/2015
***/

USE ages_e;


-- Tabela usuarios e asuario de adm
CREATE TABLE tb_usuario (
  ID_USUARIO` int(11) NOT NULL AUTO_INCREMENT,
  USUARIO varchar(45) NOT NULL,
  SENHA varchar(45) NOT NULL,
  ADMINISTRADOR varchar(1) DEFAULT NULL,
  MATRICULA varchar(45) NOT NULL,
  NOME varchar(120) DEFAULT NULL,
  EMAIL varchar(120) DEFAULT NULL,
  DATA_CADASTRO` datetime DEFAULT NULL,
  PRIMARY KEY (ID_USUARIO,MATRICULA),
  UNIQUE KEY MATRICULA_UNIQUE (MATRICULA)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

INSERT INTO TB_USUARIO(USUARIO, SENHA)
	VALUES('cassio', '123456'), ('admin', 'admin');
	
-- Tabela de estados do Brasil	
CREATE TABLE TB_UF (
  id_uf INTEGER NOT NULL AUTO_INCREMENT,
  sigla VARCHAR(2) NOT NULL,
  descricao VARCHAR(30) NOT NULL,
  PRIMARY KEY(id_uf)
);

INSERT INTO TB_UF (`sigla`, `descricao`) VALUES ('AC', 'Acre');
INSERT INTO TB_UF (`sigla`, `descricao`) VALUES ('AL', 'Alagoas');
INSERT INTO TB_UF (`sigla`, `descricao`) VALUES ('AP', 'Amapá');
INSERT INTO TB_UF (`sigla`, `descricao`) VALUES ('AM', 'Amazonas');
INSERT INTO TB_UF (`sigla`, `descricao`) VALUES ('BA', 'Bahia');
INSERT INTO TB_UF (`sigla`, `descricao`) VALUES ('CE', 'Ceará');
INSERT INTO TB_UF (`sigla`, `descricao`) VALUES ('DF', 'Distrito Federal');
INSERT INTO TB_UF (`sigla`, `descricao`) VALUES ('ES', 'Espírito Santo');
INSERT INTO TB_UF (`sigla`, `descricao`) VALUES ('GO', 'Goiás');
INSERT INTO TB_UF (`sigla`, `descricao`) VALUES ('MA', 'Maranhão');
INSERT INTO TB_UF (`sigla`, `descricao`) VALUES ('MT', 'Mato Grosso');
INSERT INTO TB_UF (`sigla`, `descricao`) VALUES ('MS', 'Mato Grosso do Sul');
INSERT INTO TB_UF (`sigla`, `descricao`) VALUES ('MG', 'Minas Gerais');
INSERT INTO TB_UF (`sigla`, `descricao`) VALUES ('PA', 'Pará');
INSERT INTO TB_UF (`sigla`, `descricao`) VALUES ('PB', 'Paraíba');
INSERT INTO TB_UF (`sigla`, `descricao`) VALUES ('PR', 'Paraná');
INSERT INTO TB_UF (`sigla`, `descricao`) VALUES ('PE', 'Pernambuco');
INSERT INTO TB_UF (`sigla`, `descricao`) VALUES ('PI', 'Piauí');
INSERT INTO TB_UF (`sigla`, `descricao`) VALUES ('RJ', 'Rio de Janeiro');
INSERT INTO TB_UF (`sigla`, `descricao`) VALUES ('RN', 'Rio Grande do Norte');
INSERT INTO TB_UF (`sigla`, `descricao`) VALUES ('RS', 'Rio Grande do Sul');
INSERT INTO TB_UF (`sigla`, `descricao`) VALUES ('RO', 'Rondônia');
INSERT INTO TB_UF (`sigla`, `descricao`) VALUES ('RR', 'Roraima');
INSERT INTO TB_UF (`sigla`, `descricao`) VALUES ('SC', 'Santa Catarina');
INSERT INTO TB_UF (`sigla`, `descricao`) VALUES ('SP', 'São Paulo');
INSERT INTO TB_UF (`sigla`, `descricao`) VALUES ('SE', 'Sergipe');
INSERT INTO TB_UF (`sigla`, `descricao`) VALUES ('TO', 'Tocantins');


CREATE TABLE `crud_devmedia`.`tb_cidade` (
  `ID_CIDADE` INT NOT NULL AUTO_INCREMENT,
  `DESCRICAO` VARCHAR(45) NOT NULL,
  `COD_ESTADO` INT NOT NULL,
  PRIMARY KEY (`ID_CIDADE`),
  INDEX `FK_UF_CIDADE_idx` (`COD_ESTADO` ASC),
  CONSTRAINT `FK_UF_CIDADE`
    FOREIGN KEY (`COD_ESTADO`)
    REFERENCES `crud_devmedia`.`tb_uf` (`id_uf`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

INSERT INTO TB_CIDADE (`descricao`, `cod_estado`) 
	VALUES ('Rio Branco', 1),
		   ('Cruzeiro do Sul', 1),
		   ('Maceió', 2),
		   ('Macapá', 3),
		   ('Manaus', 4),
		   ('Salvador', 5);
		   ('Porto Alegre', 21),
		   ('Alegrete', 21),
		   ('Bagé', 21),
		   ('Canoas', 21);
		   
-- Tabela de para endereço de Pessoas			   
CREATE TABLE IF NOT EXISTS `ages_e`.`tb_endereco` (
  `id_endereco` INT(11) NOT NULL AUTO_INCREMENT,
  `logradouro` VARCHAR(45) NOT NULL,
  `cod_cidade` INT(11) NOT NULL,
  PRIMARY KEY (`id_endereco`),
  INDEX `fk_tb_endereco_tb_cidade1_idx` (`cod_cidade` ASC),
  CONSTRAINT `fk_tb_endereco_tb_cidade1`
    FOREIGN KEY (`cod_cidade`)
    REFERENCES `ages_e`.`tb_cidade` (`ID_CIDADE`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COLLATE = latin1_swedish_ci;

-- Tabela de Pessoas
CREATE TABLE IF NOT EXISTS `ages_e`.`tb_pessoa` (
  `id_pessoa` INT(11) NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `cpf` FLOAT(11) NOT NULL,
  `dt_nasc` DATE NULL DEFAULT NULL,
  `sexo` CHAR(1) NOT NULL,
  `mini_bio` VARCHAR(100) NULL DEFAULT NULL,
  `cod_endereco` INT(11) NOT NULL,
  PRIMARY KEY (`id_pessoa`),
  INDEX `fk_tb_pessoa_tb_endereco1_idx` (`cod_endereco` ASC),
  CONSTRAINT `fk_tb_pessoa_tb_endereco1`
    FOREIGN KEY (`cod_endereco`)
    REFERENCES `ages_e`.`tb_endereco` (`id_endereco`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COLLATE = latin1_swedish_ci;

-- Tabela de preferencias
CREATE TABLE IF NOT EXISTS `ages_e`.`tb_preferencia` (
  `id_preferencia` INT(11) NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_preferencia`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COLLATE = latin1_swedish_ci;

-- Tabela associativa entre pessoas e suas preferencias
CREATE TABLE IF NOT EXISTS `ages_e`.`tb_preferencia_pessoa` (
  `cod_preferencia` INT(11) NOT NULL,
  `cod_pessoa` INT(11) NOT NULL,
  PRIMARY KEY (`cod_preferencia`, `cod_pessoa`),
  INDEX `fk_tb_preferencia_has_tb_pessoa_tb_pessoa1_idx` (`cod_pessoa` ASC),
  INDEX `fk_tb_preferencia_has_tb_pessoa_tb_preferencia1_idx` (`cod_preferencia` ASC),
  CONSTRAINT `fk_tb_preferencia_has_tb_pessoa_tb_preferencia1`
    FOREIGN KEY (`cod_preferencia`)
    REFERENCES `ages_e`.`tb_preferencia` (`id_preferencia`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tb_preferencia_has_tb_pessoa_tb_pessoa1`
    FOREIGN KEY (`cod_pessoa`)
    REFERENCES `ages_e`.`tb_pessoa` (`id_pessoa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COLLATE = latin1_swedish_ci;

-- Valores das preferencias musicais
insert into TB_PREFERENCIA (`descricao`) values('	Jazz'),('Blues'),('MPB'),('Pop'),('Rock');

/*
 *  Script principal para retorno dos dados de pessoa
 */
SELECT p.*, e.logradouro, c.descricao, uf.descricao FROM ages_e.tb_pessoa p
   inner join tb_endereco e
   on p.cod_endereco = id_endereco
   inner join tb_cidade c
   on e.cod_cidade = c.id_cidade
   inner join tb_uf uf
   on c.cod_estado = uf.id_uf;