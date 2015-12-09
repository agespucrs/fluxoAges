package br.ages.crud.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class FileManagerTEST {
	
	private final int SIZE_THRESHOLD = 1024*1024*30;
	
	private final String TEMP = "/temp/";
	
	private DiskFileItemFactory factory;
	
	private FileItem item;
	
	
	public FileManagerTEST() {
		factory = new DiskFileItemFactory();
		factory.setRepository(new File(TEMP));
		factory.setSizeThreshold(SIZE_THRESHOLD);
	}
	
	public void prep(HttpServletRequest request) throws FileUploadException{
		ServletFileUpload upload = new ServletFileUpload(factory);
		List<FileItem> items = upload.parseRequest(request);
		item = items.get(0);
		if(item.isFormField()){
			item = null;
			throw new FileUploadException("Falha de upload: Arquivo não encontrado.");
		}
	}
	
	public boolean upload(String path, String fileName) throws Exception {			
	    if (!item.isFormField()) {		        	        
	        item.write(new File(path, fileName));
	        return true;
	    }
	    return false;
	}
	
	public boolean validaExtensao(String desiredExt){
		String fileName = item.getName();
		String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
		
		if(fileExt.equals(desiredExt)) return true;
		return false;
	}
	
	public boolean validaTamanho(int tamanhoBytes){
		if(item.getSize() < tamanhoBytes)return true;
		return false;
	}
	
}
