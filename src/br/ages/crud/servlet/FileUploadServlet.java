package br.ages.crud.servlet;

import javax.servlet.http.HttpServletRequest;

public class FileUploadServlet {
	
	private static final long serialVersionUID = 1L;
    
    // location to store file uploaded
    private static final String UPLOAD_DIRECTORY = "upload";
 
    // upload settings
    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB
	
	
	protected void doPost(HttpServletRequest request, HttpServletRequest response){
		
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletRequest response){
		
	}

}