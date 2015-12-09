package br.ages.crud.util;

public class MensagemContantes {

	public static final String MSG_ERR_CAMPO_OBRIGATORIO = "Campo ? obrigat�rio!";
	
	public static final String MSG_ERR_CAMPO_NOME_OBRIGATORIO = "Campo Nome obrigat�rio!";

	public static final String MSG_ERR_CAMPO_SEXO_OBRIGATORIO = "Campo Sexo obrigat�rio!";
	
	public static final String MSG_ERR_CAMPO_CIDADE_OBRIGATORIO = "Campo Cidade obrigat�rio!";
	
	public static final String MSG_ERR_CAMPO_ESTADO_OBRIGATORIO = "Campo Estado obrigat�rio!";
	
	public static final String MSG_ERR_CAMPO_LOGRADOURO_OBRIGATORIO = "Campo Logradouro obrigat�rio!";
	
	public static final String MSG_ERR_CAMPO_INVALIDO = "Campo ? inv�lido!";
	
	public static final String MSG_ERR_USUARIO_SENHA_INVALIDOS = "Usu�rio/Senha inv�lidos!";
	
	public static final String MSG_ERR_PESSOA_DADOS_INVALIDOS = "Dados da pessoa inv�lidos ou inconsistentes!";

	public static final String MSG_ERR_USUARIO_DADOS_INVALIDOS = "Dados do usu�rio inv�lidos ou inconsistentes!";
	
	public static final String MSG_ERR_CAMPO_CPF_MAIOR_RECOMENDADO = "Campo ? com mais de 11 caracteres!";
	
	public static final String MSG_ERR_CAMPO_CPF_MENOR_RECOMENDADO = "Campo ? com menos de 11 caracteres!";

	public static final String MSG_ERR_CAMPO_DATA_MAIOR_RECOMENDADO = "Campo ? com mais de 10 caracteres!";

	public static final String MSG_ERR_CAMPO_DATA_MENOR_RECOMENDADO = "Campo ? com menos de 10 caracteres!";
	
	public static final String MSG_ERR_CAMPO_DATA_INVALIDO = "Ocorreu algum problema no envio da data";	
	
	public static final String MSG_ERR_SENHA_INVALIDA = "A senha do usuario � inv�lida: Deve ter no m�nimo 3 e no m�ximo 8 caracteres, apenas n�meros e letras!";
	
	public static final String MSG_ERR_MATRICULA_INVALIDA = "Matr�cula inv�lida!";
	
	public static final String MSG_ERR_EMAIL_INVALIDO = "E-mail com formato inv�lido.<br>N�o use acentua��o e caracteres especiais!"
			+ "<br>Exemplo correto: ages_user@acad.pucrs.br";
	
	public static final String MSG_ERR_NOME_INVALIDO = "Nome com formato inv�lido.<br>Exemplo correto: Jo�o Cardoso";
	
	public static final String MSG_ERR_USUARIO_JA_EXISTENTE = "Usu�rio ou matr�cula j� cadastrados!";
	
	public static final String MSG_ERR_REMOVE_USUARIO_EM_PROJETO = "Este usu�rio j� est� em algum projeto e n�o pode ser removido!";
	
	public static final String MSG_SUC_CADASTRO_PESSOA = "Cadastro de Pessoa efetuado com sucesso!";
	
	public static final String MSG_SUC_CADASTRO_USUARIO = "Usu�rio ? cadastrado com sucesso!";
	
	public static final String MSG_SUC_EDICAO_USUARIO = "Usu�rio ? editado com sucesso!";
	
	public static final String MSG_SUC_REMOVE_USUARIO= "Usu�rio ? removido com sucesso!";
	
	public static final String MSG_SUC_ATUALIZADA_PESSOA = "Atualiza��o de Pessoa efetuada com sucesso!";
	
	public static final String MSG_INF_LOGOUT = "Logout do usu�rio efetuado com sucesso!";
	
	public static final String MSG_INF_DENY = "Acesso negado!";
	

	//msgs de projeto

	public static final String MSG_ERR_PROJETO_DADOS_INVALIDOS = "Dados do projeto inv�lidos ou inconsistentes!";
		
	public static final String MSG_ERR_PROJETO_ARQUIVO_INVALIDO = "Arquivo excede o ?Mb ou n�o � do tipo PDF!";
	
	public static final String MSG_ERR_PROJETO_DATA_INCONSISTENTE = "Data de in�cio deve ser anterior � data de fim?!"; //'?' proposital
	
	public static final String MSG_ERR_CADASTRO_PROJETO = "Ocorreu um erro no cadastro do projeto.";

	public static final String MSG_SUC_CADASTRO_PROJETO = "Projeto ? cadastrado com sucesso!";
	
	public static final String MSG_SUC_EDICAO_PROJETO = "Projeto ? editado com sucesso!";

	public static final String MSG_SUC_REMOVE_PROJETO = "Projeto ? removido com sucesso!";

	//msgs de stakeholder
	
	public static final String  MSG_ERR_STAKEHOLDER_JA_EXISTENTE = "Stakeholder ? j� cadastrado!";
	
	public static final String MSG_ERR_STAKEHOLDER_DADO_INVALIDO = "Dado do Stakeholder inv�lido ou inconsistente!";
	
	public static final String MSG_ERR_STAKEHOLDER_NOME_OBRIGATORIO = "Campo Nome obrigat�rio!";
	

	public static final String MSG_ERR_STAKEHOLDER_NOME_INVALIDO = "Nome com formato inv�lido.<br>Exemplo: Jo�o Pedro";
	
	public static final String MSG_ERR_REMOVE_STAKEHOLDER_EM_PROJETO = "Este stakeholder j� est� em algum projeto e n�o pode ser removido!";

	public static final String MSG_SUC_CADASTRO_STAKEHOLDER = "Stakeholder ? cadastrado com sucesso";
	
	public static final String MSG_SUC_EDITA_STAKEHOLDER ="Stakeholder ? editado com sucesso";

	public static final String MSG_SUC_REMOVE_STAKEHOLDER = "Stakeholder de id ? removido com sucesso";
	
	//msgs do Ponto

	public static final String MSG_ERR_CADASTRO_PONTO= "Erro ao salvar o Ponto do Aluno ?";

	public static final String MSG_ERR_CADASTRO_PONTO_DATA_INVALIDA = "Data de Saida menor que Data de Entrada";

	public static final String MSG_SUC_CADASTRO_PONTO = "Cadastrado com SUCESSO o Ponto do Aluno ?";

	public static final String MSG_ERR_PONTO_JA_EXISTENTE = "Ponto ja existente para o Aluno ? na data";

}
