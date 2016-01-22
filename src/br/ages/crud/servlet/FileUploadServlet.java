package br.ages.crud.servlet;

import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;

import br.ages.crud.model.Projeto;
import br.ages.crud.util.Constantes;

@WebServlet("/upload")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 15, maxFileSize = Constantes.PROJETO_ARQUIVO_MAX_BYTES, maxRequestSize = 1024 * 1024 * 15)
public class FileUploadServlet extends HttpServlet {
	Logger logger = Logger.getLogger("servlet.FileUploadServlet");
	private static final long serialVersionUID = 2L;
	private static ResourceBundle configPath = ResourceBundle.getBundle(Constantes.AMBIENTE_PROPERTIES);

	private static final String SAVE_DIR =  configPath.getString(Constantes.PROJETO_UPLOAD_PATH);

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			logger.debug("Iniciando o Upload");
			Projeto projeto = (Projeto) request.getSession().getAttribute("projeto");

			String appPath = projeto.getWorkspace();
			String savePath = SAVE_DIR + File.separator + appPath ;
			File fileSaveDir = new File(savePath);

			if (!fileSaveDir.exists())
				fileSaveDir.mkdir();

			Part part = request.getPart("file");
			String fileName = extractFileName(part);
			part.write(new File(savePath + File.separator + fileName).toString());

			request.setAttribute("msgSucesso", "Upload feito com sucesso!");

			// request.setAttribute("acao", "listaProjetos");
			getServletContext().getRequestDispatcher("/main?acao=listaProjetos").forward(request, response);
			
			logger.info("Executado o Upload em: " + savePath + " - " + fileName );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String extractFileName(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				return s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		return "";
	}
	
	}