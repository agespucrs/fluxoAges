package br.ages.crud.bo;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import br.ages.crud.util.Constantes;

@MultipartConfig
public class ArquivoBO {
	
	public ArquivoBO() {
	}
	
	public void uploadArquivo(Part part, String nome) throws IOException, ServletException{
		
	    File uploads = new File(Constantes.PROJETO_UPLOAD_PATH);	    
	    File file = new File(uploads, nome + ".pdf");
	    
	    try (InputStream input = part.getInputStream()) {
	        Files.copy(input, file.toPath());
	    }
	}
	
	public boolean validaArquivo(File file, long tamanho){
		if(file.getTotalSpace() > tamanho)return false;		
		return true;
	}
	
	public boolean validaArquivo(Part part, long tamanho){
		if(part.getSize() > tamanho) return false;
		return true;
	}
	
}
