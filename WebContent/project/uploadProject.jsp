<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="br.ages.crud.model.Projeto"%>
    
<%Projeto projeto = (Projeto) request.getAttribute("projeto"); %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="upload" enctype="multipart/form-data" >
	
		<label class="form-label ages">Projeto:</label>
			<input class="form-control" id="codigo" name="codigo" value="<%= projeto.getNomeProjeto() %>" type="text" maxlength="120" disabled>
		
		
		<label class="form-label ages">Arquivo:</label>
		<input class="form-control" id="file" name="file" value="${param.file}" type="file" maxlength="120">
	</form>
</body>
</html>