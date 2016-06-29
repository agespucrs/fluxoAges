--liquibase formatted sql
--changeset vaccaro:3
--comment Atualizacao dos valores de FLAG_RESPONSAVEL para os registros em tb_tipo_usuario.

USE ages_e;

UPDATE `tb_tipo_usuario` SET `FLAG_RESPONSAVEL`='1' WHERE `ID_TIPO_USUARIO`='1';
UPDATE `tb_tipo_usuario` SET `FLAG_RESPONSAVEL`='0' WHERE `ID_TIPO_USUARIO`='2';
UPDATE `tb_tipo_usuario` SET `FLAG_RESPONSAVEL`='1' WHERE `ID_TIPO_USUARIO`='3';
UPDATE `tb_tipo_usuario` SET `FLAG_RESPONSAVEL`='1' WHERE `ID_TIPO_USUARIO`='4';
