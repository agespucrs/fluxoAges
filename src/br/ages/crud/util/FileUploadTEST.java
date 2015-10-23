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

public class FileUploadTEST {
	
	private DiskFileItemFactory factory;
	
	private final int SIZE_THRESHOLD = 1024*1024*30;
	
	private final String TEMP = "/temp/";
	
	public FileUploadTEST() {
		factory = new DiskFileItemFactory();
		factory.setRepository(new File(TEMP));
		factory.setSizeThreshold(SIZE_THRESHOLD);
	}
	
	
	public void upload(HttpServletRequest request, String path, String fileName) throws Exception{		

		
		ServletFileUpload upload = new ServletFileUpload(factory);
		List<FileItem> items = upload.parseRequest(request);
		
		//processa
		Iterator<FileItem> iter = items.iterator();
		while (iter.hasNext()) {
		    FileItem item = iter.next();

		    if (!item.isFormField()) {		        	        
		        item.write(new File(path, fileName));
			    
		    }		    
		}
		
	}
}
