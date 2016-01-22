
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="br.ages.crud.model.Projeto"%>
    
<%Projeto projeto = (Projeto)request.getSession().getAttribute("projeto"); %>

<jsp:include page="../template/head.jsp"></jsp:include>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<style type="text/css" >
	.file-upload-panel {
    max-width: 500px;
    float: none;
    margin-left: auto;
    margin-right: auto;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

</head>
<body>
	<div class="panel panel-primary file-upload-panel">
	
	<div class="panel-heading text-center">Upload de Arquivo</div>
	
	<div class="panel-body">
	<jsp:include page="/template/msg.jsp"></jsp:include>
		<form method="post" action='upload' enctype="multipart/form-data" >
			<label class="form-label ages">Projeto: </label>

			<input class="form-control" id="idProjeto" name="idProjeto" value="<%= projeto.getIdProjeto() %>" type="hidden" maxlength="10" disabled>
			<input class="form-control" id="codigo" name="codigo" value="<%= projeto.getNomeProjeto() %>" type="text" maxlength="120" disabled>
			<label class="form-label ages">Arquivo:</label>
			<input class="form-control" id="file" name="file" value="${param.file}" type="file" maxlength="120">
			
			<input class="btn btn-primary pull-right" type="submit"	value="Enviar">
		</form>
	</div>
		
	</div>
		
	<jsp:include page="/template/foot.jsp"></jsp:include>
</body>
</html>