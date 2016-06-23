--liquibase formatted sql
--changeset cassio:2

/***
* Criação das tablesa para avaliação de SKILLs e
* Alteração da tabela tb_tipo_usuario 
* 
* Casssio Trindade
* 06/2016
* 
***/
-- alter table tb_ponto pra acertaras datas
ALTER TABLE `ages_e`.`tb_ponto` 
CHANGE COLUMN `hora_entrada` `hora_entrada` DATETIME NULL DEFAULT NULL COMMENT '' ,
CHANGE COLUMN `hora_saida` `hora_saida` DATETIME NULL DEFAULT NULL COMMENT '' ;


-- alter table tb_tipo_usuario
ALTER TABLE `ages_e`.`tb_tipo_usuario` 
ADD COLUMN `FLAG_RESPONSAVEL` VARCHAR(1) NULL COMMENT '' AFTER `DATA_INCLUSAO`;



-- table tb_skills_definicao armazena a definição de quad tipo de skill.
CREATE TABLE `tb_skills_definicao` (
  `ID_SKILLS_DEFINICAO` int(11) NOT NULL,
  `TIPO` varchar(20) DEFAULT NULL,
  `NOME` varchar(45) DEFAULT NULL,
  `DESCRICAO` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`ID_SKILLS_DEFINICAO`)
);

-- table tb_skills para armazenar as avaliações dos alunos
CREATE TABLE TB_SKILLS (
  `ID_SKILLS` int(11) NOT NULL AUTO_INCREMENT,
  `ID_DEFINICAO` varchar(45) DEFAULT NULL,
  `VALOR` int(1) DEFAULT NULL,
  `DATA_VALOR` datetime DEFAULT NULL,
  `OBSERVACAO` varchar(145) DEFAULT NULL,
  `ID_USUARIO_ALUNO` int(11) DEFAULT NULL,
  `ID_USUARIO_AVALIADOR` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID_SKILLS`),
  KEY `FK_USUARIO_ALUNO_idx` (`ID_USUARIO_ALUNO`),
  KEY `FK_USUARIO_AVALIADOR_idx` (`ID_USUARIO_AVALIADOR`),
  CONSTRAINT `FK_DEFINICAO_SKILL` FOREIGN KEY (`ID_SKILLS`) REFERENCES `tb_skills_definicao` (`ID_SKILLS_DEFINICAO`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_USUARIO_ALUNO` FOREIGN KEY (`ID_USUARIO_ALUNO`) REFERENCES `tb_usuario` (`ID_USUARIO`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_USUARIO_AVALIADOR` FOREIGN KEY (`ID_USUARIO_AVALIADOR`) REFERENCES `tb_usuario` (`ID_USUARIO`) ON DELETE NO ACTION ON UPDATE NO ACTION
);

-- adicionando FKs para tabela Usuário
ALTER TABLE `tb_ponto` 
ADD INDEX `fk_aluno_idx` (`id_usuario_aluno` ASC)  COMMENT '',
ADD INDEX `fk_responsavel_idx` (`id_usuario_responsavel` ASC)  COMMENT '';
ALTER TABLE `ages_e`.`tb_ponto` 
ADD CONSTRAINT `fk_aluno`
  FOREIGN KEY (`id_usuario_aluno`)
  REFERENCES `ages_e`.`tb_usuario` (`ID_USUARIO`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_responsavel`
  FOREIGN KEY (`id_usuario_responsavel`)
  REFERENCES `ages_e`.`tb_usuario` (`ID_USUARIO`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
