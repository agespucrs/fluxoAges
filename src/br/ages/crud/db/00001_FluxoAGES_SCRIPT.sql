--liquibase formatted sql
--changeset cassio:1
--comment Script para criação das primeiras tabelas do projeto.
/***
* Scripts para criacao e insersao de dados
* Base Dados Fluxo AGES
* Casssio Trindade, Daniele Souza e Victor Diehl
* 09/2015
***/
-- alter table ages_e.tb_usuario
-- add constraint U_username unique(usuario);

USE ages_e;

-- DROP TABLE TB_TIPO_USUARIO;
-- DROP TABLE TB_PROJETO_USUARIO;
-- DROP TABLE TB_PROJETO_STAKEHOLDERS;
-- DROP TABLE TB_USUARIO;
-- DROP TABLE TB_PROJETO;
-- DROP TABLE TB_STAKEHOLDERS;

-- Tabela Usuario
CREATE TABLE tb_usuario (
  ID_USUARIO int(11) NOT NULL AUTO_INCREMENT,
  USUARIO varchar(45) NOT NULL,
  SENHA varchar(45) NOT NULL,
  PERFIL_ACESSO varchar(20) NOT NULL,
  STATUS_USUARIO varchar(20) NOT NULL,
  ID_TIPO_USUARIO int(11) NOT NULL,
  MATRICULA varchar(45) NOT NULL,
  NOME varchar(120) DEFAULT NULL,
  EMAIL varchar(120) DEFAULT NULL,
  DATA_INCLUSAO datetime DEFAULT NULL,
  PRIMARY KEY (ID_USUARIO,MATRICULA),
  UNIQUE KEY MATRICULA_UNIQUE (MATRICULA),
  CONSTRAINT U_USERNAME UNIQUE (USUARIO)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- Tabela Tipo Usuario
CREATE TABLE tb_tipo_usuario (
  ID_TIPO_USUARIO int(11) NOT NULL AUTO_INCREMENT,
  NOME varchar(20) NOT NULL,
  DESCRICAO varchar(120) DEFAULT NULL,
  DATA_INCLUSAO datetime DEFAULT NULL,
  PRIMARY KEY (ID_TIPO_USUARIO)
);

-- Inserts
INSERT INTO tb_tipo_usuario VALUES
('1', 'Arquiteto', 'Responsavel pela parte tecnica', '2015-10-01 00:00:00');
INSERT INTO tb_tipo_usuario VALUES
('2', 'Aluno', '', '2015-10-01 00:00:00');
INSERT INTO tb_tipo_usuario VALUES
('3', 'Professor', '', '2015-10-01 00:00:00');
INSERT INTO tb_tipo_usuario VALUES
('4', 'Secretaria', '', '2015-10-01 00:00:00');

INSERT INTO tb_usuario
(ID_USUARIO,USUARIO,SENHA,PERFIL_ACESSO,STATUS_USUARIO,ID_TIPO_USUARIO,MATRICULA,NOME,EMAIL,DATA_INCLUSAO)
VALUES
('10', 'admin', 'admin', 'ADMINISTRADOR', 'ATIVO', '1', '00000', 'Cassio Trindade', 'cassio.trindade@pucrs.br', '2015-10-01 00:00:00');

select * from tb_usuario;

-- Tabela Projeto
  CREATE TABLE tb_projeto (
  ID_PROJETO int(11) NOT NULL AUTO_INCREMENT,
  NOME_PROJETO varchar(120) NOT NULL, 
  STATUS_PROJETO varchar(10) NOT NULL,  
  WORKSPACE varchar(60) DEFAULT NULL,  
  DATA_INICIO datetime NOT NULL, 
  DATA_FIM datetime DEFAULT NULL,
  DATA_FIM_PREVISTO datetime NOT NULL,
  DATA_INCLUSAO datetime NOT NULL,
  PRIMARY KEY (ID_PROJETO) 
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
 
-- Tabela Stakeholders
CREATE TABLE tb_stakeholders (
  ID_STAKEHOLDER int(11) NOT NULL AUTO_INCREMENT,
  NOME_STAKEHOLDER varchar(45) NOT NULL,
  DATA_INCLUSAO datetime DEFAULT NULL, 
  PRIMARY KEY (ID_STAKEHOLDER)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- Tabela Projeto/Usuario
CREATE TABLE tb_projeto_usuario (
  ID_PROJETO int(11) NOT NULL,
  ID_USUARIO int(11) NOT NULL,
  PRIMARY KEY (ID_PROJETO,ID_USUARIO),
  KEY fk_usuario_idx (ID_USUARIO),
  CONSTRAINT fk_projeto FOREIGN KEY (ID_PROJETO) REFERENCES tb_projeto (ID_PROJETO) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT fk_usuario FOREIGN KEY (ID_USUARIO) REFERENCES tb_usuario (ID_USUARIO) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Tabela Projeto/Stakeholders
CREATE TABLE tb_projeto_stakeholders (
  ID_PROJETO int(11) NOT NULL,
  ID_STAKEHOLDER int(11) NOT NULL,
  PRIMARY KEY (ID_PROJETO,ID_STAKEHOLDER),
  KEY fk_stakeholder_idx (ID_STAKEHOLDER),
  CONSTRAINT fk_projeto_s FOREIGN KEY (ID_PROJETO) REFERENCES tb_projeto (ID_PROJETO) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT fk_stakeholder FOREIGN KEY (ID_STAKEHOLDER) REFERENCES tb_stakeholders (ID_STAKEHOLDER) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Tabela para o Registro do Ponto, horas fora aula na agencia.
CREATE TABLE tb_ponto (
  id_ponto INT NOT NULL AUTO_INCREMENT COMMENT '',
  data_entrada DATETIME NULL COMMENT '',
  hora_entrada DATETIME NULL COMMENT '',
  data_saida DATETIME NULL COMMENT '',
  hora_saida DATETIME NULL COMMENT '',
  id_usuario_aluno INT NULL COMMENT '',
  id_usuario_responsavel INT NULL COMMENT '',
  status_ponto VARCHAR(30) NULL COMMENT '',
  PRIMARY KEY (id_ponto)  COMMENT '')
COMMENT = 'Tabela para o Registro do Ponto, horas fora aula na agencia. ';

/*
-- Tabela de estados do Brasil	
CREATE TABLE TB_UF (
  id_uf INTEGER NOT NULL AUTO_INCREMENT,
  sigla VARCHAR(2) NOT NULL,
  descricao VARCHAR(30) NOT NULL,
  PRIMARY KEY(id_uf)
);

INSERT INTO TB_UF (`sigla`, `descricao`) VALUES ('AC', 'Acre');
INSERT INTO TB_UF (`sigla`, `descricao`) VALUES ('AL', 'Alagoas');
INSERT INTO TB_UF (`sigla`, `descricao`) VALUES ('AP', 'AmapÃ¯Â¿Â½');
INSERT INTO TB_UF (`sigla`, `descricao`) VALUES ('AM', 'Amazonas');
INSERT INTO TB_UF (`sigla`, `descricao`) VALUES ('BA', 'Bahia');
INSERT INTO TB_UF (`sigla`, `descricao`) VALUES ('CE', 'CearÃ¯Â¿Â½');
INSERT INTO TB_UF (`sigla`, `descricao`) VALUES ('DF', 'Distrito Federal');
INSERT INTO TB_UF (`sigla`, `descricao`) VALUES ('ES', 'EspÃ¯Â¿Â½rito Santo');
INSERT INTO TB_UF (`sigla`, `descricao`) VALUES ('GO', 'GoiÃ¯Â¿Â½s');
INSERT INTO TB_UF (`sigla`, `descricao`) VALUES ('MA', 'MaranhÃ¯Â¿Â½o');
INSERT INTO TB_UF (`sigla`, `descricao`) VALUES ('MT', 'Mato Grosso');
INSERT INTO TB_UF (`sigla`, `descricao`) VALUES ('MS', 'Mato Grosso do Sul');
INSERT INTO TB_UF (`sigla`, `descricao`) VALUES ('MG', 'Minas Gerais');
INSERT INTO TB_UF (`sigla`, `descricao`) VALUES ('PA', 'ParÃ¯Â¿Â½');
INSERT INTO TB_UF (`sigla`, `descricao`) VALUES ('PB', 'ParaÃ¯Â¿Â½ba');
INSERT INTO TB_UF (`sigla`, `descricao`) VALUES ('PR', 'ParanÃ¯Â¿Â½');
INSERT INTO TB_UF (`sigla`, `descricao`) VALUES ('PE', 'Pernambuco');
INSERT INTO TB_UF (`sigla`, `descricao`) VALUES ('PI', 'PiauÃ¯Â¿Â½');
INSERT INTO TB_UF (`sigla`, `descricao`) VALUES ('RJ', 'Rio de Janeiro');
INSERT INTO TB_UF (`sigla`, `descricao`) VALUES ('RN', 'Rio Grande do Norte');
INSERT INTO TB_UF (`sigla`, `descricao`) VALUES ('RS', 'Rio Grande do Sul');
INSERT INTO TB_UF (`sigla`, `descricao`) VALUES ('RO', 'RondÃ¯Â¿Â½nia');
INSERT INTO TB_UF (`sigla`, `descricao`) VALUES ('RR', 'Roraima');
INSERT INTO TB_UF (`sigla`, `descricao`) VALUES ('SC', 'Santa Catarina');
INSERT INTO TB_UF (`sigla`, `descricao`) VALUES ('SP', 'SÃ¯Â¿Â½o Paulo');
INSERT INTO TB_UF (`sigla`, `descricao`) VALUES ('SE', 'Sergipe');
INSERT INTO TB_UF (`sigla`, `descricao`) VALUES ('TO', 'Tocantins');

CREATE TABLE TB_CIDADE (
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
	VALUES ('Porto Alegre', 21),
		   ('Alegrete', 21),
		   ('BagÃ¯Â¿Â½', 21),
		   ('Canoas', 21);
		   
-- Tabela de para endereÃ¯Â¿Â½o de Pessoas			   
CREATE TABLE IF NOT EXISTS `ages_e`.`TB_ENDERECO` (
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
*/
