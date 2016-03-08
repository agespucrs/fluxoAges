package br.ages.crud.bo;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import javax.servlet.ServletException;
import javax.servlet.http.Part;

public class ArquivoBO {
	
	
	public ArquivoBO() {
	}
	
	public void uploadArquivo(Part part, String nome, String path) throws IOException, ServletException{
		
	    File uploads = new File(path);	    
	    File file = new File(uploads, nome + ".pdf");
	    
	    try (InputStream input = part.getInputStream()) {
	        Files.copy(input, file.toPath());
	    }
	}
	
	public boolean validaTamanho(File file, long tamanho){
		if(file.getTotalSpace() > tamanho)return false;		
		return true;
	}
	
	public boolean validaTamanho(Part part, long tamanho){
		if(part.getSize() > tamanho) return false;
		return true;
	}
	
	public boolean validaExtensao(File file, String ext){
		String fileExt = getFileExtension(file);
		
		if(fileExt.equals(ext)) return true;
		return false;		
	}
	
	public boolean validaExtensao(Part part, String ext){
		String fileExt = getFileExtension(part);
		
		if(fileExt.equals(ext)) return true;
		return false;		
	}
	
	///m�todos para achar a extens�o de um arquivo
	private String getFileExtension(File file) {
	    String name = file.getName();
	    try {
	        return name.substring(name.lastIndexOf(".") + 1);
	    } catch (Exception e) {
	        return "";
	    }
	}	
	private String getFileExtension(Part part) {
	    String name = part.getName();
	    try {
	        return name.substring(name.lastIndexOf(".") + 1);
	    } catch (Exception e) {
	        return "";
	    }
	}
	
}
