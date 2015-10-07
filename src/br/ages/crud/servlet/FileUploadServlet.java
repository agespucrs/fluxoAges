package br.ages.crud.servlet;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

public class FileUploadServlet {
	
	private static final long serialVersionUID = 1L;
    
    // location to store file uploaded
    private static final String UPLOAD_DIRECTORY = "upload";
 
    // upload settings
    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 20; // 20MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 21; // 21MB
	
	
	protected void doPost(HttpServletRequest request, HttpServletRequest response){
		
		 // configures upload settings
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // sets memory threshold - beyond which files are stored in disk
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        // sets temporary location to store files
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
 
        ServletFileUpload upload = new ServletFileUpload(factory);
         
        // sets maximum size of upload file
        upload.setFileSizeMax(MAX_FILE_SIZE);
         
        // sets maximum size of request (include file + form data)
        upload.setSizeMax(MAX_REQUEST_SIZE);
		
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletRequest response){
		
	}

}