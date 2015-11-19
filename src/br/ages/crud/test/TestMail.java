package br.ages.crud.test;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class TestMail {

	public static void main(String[] args) {
		try {
			SimpleEmail email = new SimpleEmail();
			// Servidor
			email.setHostName("smtp.gmail.com");
  
			// Autenticação
			email.setAuthenticator(new DefaultAuthenticator("cassiowt@gmail.com", "c978c978"));
			email.setSSLOnConnect(true);
			
			// o servidor SMTP para envio do e-mail
			email.addTo("cassio.trindade@pucrs.br", "Cássio Trindade");
		
			// destinatário
			email.setFrom("cassiowt@gmail.com", "Cássio");
			
			// remetente
			email.setSubject("Mensagem de Teste");
			
			// assunto do e-mail
			email.setMsg("Teste de Email utilizando commons-email"); // conteudo do
	
			//Envio
			email.send();
			System.out.println(email.getSentDate());
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
