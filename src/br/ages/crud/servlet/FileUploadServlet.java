package br.ages.crud.servlet;

import java.io.File;
import java.io.IOException;
 
import br.ages.crud.util.Constantes;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
 
@WebServlet("/upload")
@MultipartConfig(fileSizeThreshold=1024*1024*15, maxFileSize=Constantes.PROJETO_ARQUIVO_MAX_BYTES, maxRequestSize=1024*1024*15)
public class FileUploadServlet extends HttpServlet {
 
	private static final long serialVersionUID = 2L;
	private static final String SAVE_DIR = Constantes.PROJETO_UPLOAD_PATH;
     
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String appPath = request.getServletContext().getRealPath(""); 
        String savePath = appPath + File.separator + SAVE_DIR;
        File fileSaveDir = new File(savePath);        
        
        if (!fileSaveDir.exists()) fileSaveDir.mkdir();
         
        Part part = request.getPart("file");
        String fileName = extractFileName(part);    
        part.write(savePath + File.separator + fileName);        
 
        request.setAttribute("msgSucesso", "Upload feito com sucesso!");
        
        request.setAttribute("acao", "listaProjetos");
        getServletContext().getRequestDispatcher("main").forward(
                request, response);
    }
 
    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return "";
    }
}