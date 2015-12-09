<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@page import="br.ages.crud.model.Stakeholder"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<jsp:include page="../template/head.jsp"></jsp:include>

<div class="panel panel-primary panel-addStakeholder">

	<div class="panel-heading text-center">Cadastro de Stakeholder</div>


	<div class="panel-body">

		<jsp:include page="/template/msg.jsp"></jsp:include>

		<div class="table-responsive">

			<form method="post" action="main?acao=addStakeholder">
				<div class="form-group">
					<label class="form-label ages">Nome do Stakeholder: <span
						class="red">*</span></label> <input class="form-control"
						id="nomeStakeholder" name="nomeStakeholder"
						value="${param.nomeStakeholder}" type="text" maxlength="120"
						required>
				</div>

				<hr>

				<p>
					Campos que contém <span class="red">*</span> são obrigatórios
				</p>


				<div class="text-center">
					<input class="btn btn-warning limparUser pull-left" type="reset"
						value="Limpar"> <input
						class="btn btn-primary addUser pull-right" type="submit"
						value="Cadastrar">
				</div>

			</form>
		</div>
	</div>
</div>

<jsp:include page="/template/foot.jsp"></jsp:include>
