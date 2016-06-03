<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@page import="br.ages.crud.model.Usuario"%>
<%@page import="br.ages.crud.model.Ponto"%>
<%@page import="br.ages.crud.util.Util"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%Ponto ponto = (Ponto) request.getAttribute("ponto"); %>


<jsp:include page="../template/head.jsp"></jsp:include>
	
	<div class="panel panel-primary panel-addPonto">

		<div class="panel-heading text-center">Edição Ponto Aluno</div>
		<div class="panel-body">
		
		<jsp:include page="/template/msg.jsp"></jsp:include>

			<div class="table-responsive">
				<form method="post" action="main?acao=adicionaPonto&isEdit=true" >
					<div class="form-group">
					    <input hidden="" id="idPonto" name="idPonto" value="<%=ponto.getIdPonto()%>">
						<div class='' id='nomeAluno'>
							<label  class="form-label ages">Aluno:<span class="red">*</span></label> 
							<input hidden="" id="idAluno" name="idAluno" value="<%=ponto.getAluno().getIdUsuario()%>">
							<input class="form-control" id="aluno" name="aluno" value="<%=ponto.getAluno().getNome()%>" type="text" readonly>					
						</div>
						<label class="form-label ages">Entrada:<span class="red">*</span></label> 
						<div class='input-group date' id='dataEntrada'>
							<input value="<%=Util.dateTimeToString(ponto.getDataEntrada())%>" type='text' class="form-control" id='dtEntrada' name="dtEntrada"/>
							<span class="input-group-addon">
								<span class="glyphicon glyphicon-calendar"></span>
							</span>
						</div>
						<label class="form-label ages">Saída:</label> 
						<div class='input-group date' id='dataSaida'>
							<input value="<%=Util.dateTimeToString(ponto.getDataSaida())%>" type='text' class="form-control" id="dtSaida" name="dtSaida"/> 
							<span class="input-group-addon">
								<span class="glyphicon glyphicon-calendar"></span>
							</span>
						</div>
					</div>
					
						<div class='' id='nomeResponsavel'>
							<label for="sel2" class="form-label ages">Responsável:</label> 
							<select class="form-control" id="idResponsavel" name="idResponsavel" >
						        <option value="<%=ponto.getResponsavel().getNome()%>">Selecione...</option>
						         
							 	<%
									List<Usuario> listaResponsaveis = (List<Usuario>) request.getAttribute("responsaveis");
									for (Usuario u : listaResponsaveis) {
							  	 %>
								<option value="<%=u.getIdUsuario()%>"><%=u.getNome()%></option>
								<%
									}
								%>
					        </select>
						</div>
					
					<div class="form-group">
						<div class='' id='senhaResponsavel'>
							<label class="form-label ages">Senha:</label> 
							<input type='password' class="form-control" id="senhaResponsavel" name="senhaResponsavel" /> 
						</div>
					</div>

					
					<hr>
                    
                    <p>Campos que contém <span class="red">*</span> são obrigatórios</p>
                    
                    
                    <div class="text-center">
			           	<input class="btn btn-warning limparUser pull-left" type="reset" value="Limpar">
			           	<input class="btn btn-primary addUser pull-right" type="submit" value="Salvar">
			        </div>
				</form>
			</div>
		</div>
	</div>


<!-- Initialize the plugin: -->

<script type="text/javascript">
	$(function() {
		$('#dataEntrada').datetimepicker({
			locale : 'pt-br',
			sideBySide : true,
			showTodayButton: true
		});

		$('#dataSaida').datetimepicker({
			useCurrent : false, //Important! See issue #1075
			locale : 'pt-br',
			sideBySide : true
		});

		$("#dataEntrada").on("dp.change", function(e) {
			$('#dataSaida').data("DateTimePicker").minDate(e.date);
			/* alert(document.getElementById('dataSaida').value); */
		});

		$("#dataSaida").on("dp.change", function(e) {
			$('#dataEntrada').data("DateTimePicker").maxDate(e.date);
			/* alert(document.getElementById('dataEntrada').value); */
		});
	});
</script>
<jsp:include page="/template/foot.jsp"></jsp:include>