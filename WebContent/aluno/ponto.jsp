<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@page import="br.ages.crud.model.Usuario"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<jsp:include page="../template/head.jsp"></jsp:include>
	
	<div class="panel panel-primary panel-addPonto">

		<div class="panel-heading text-center">Livro Ponto</div>
		<div class="panel-body">

			<div class="table-responsive">
				<form method="post">
				<%
				
				Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioSessao");
				
				%>
					<div class="form-group">
						<div class='' id='nomeAluno'>
							<label  for="sel1" class="form-label ages">Aluno:<span class="red">*</span></label> 
							<select class="form-control" id="sel1" >
						        <option><%=usuario.getNome()%></option>
							 	<%
									List<Usuario> listaUsuarios = (List<Usuario>) request.getAttribute("usuarios");
									for (Usuario u : listaUsuarios) {
							  	 %>
								<option value="<%=u.getIdUsuario()%>"><%=u.getNome()%></option>
								<%
									}
								%>
					]        </select>
						</div>
					</div>
					<div class="form-group">
					<label class="form-label ages">Entrada:<span class="red">*</span></label> 
						<div class='input-group date' id='dataEntrada'>
							<input id='dataEntrada' type='text' class="form-control" />
							<span class="input-group-addon">
								<span class="glyphicon glyphicon-calendar"></span>
							</span>
						</div>
					</div>
					<div class="form-group">
						<label class="form-label ages">Saída:<span class="red">*</span></label> 
						<div class='input-group date' id='dataSaida'>
							<input type='text' class="form-control" /> <span class="input-group-addon">
								<span class="glyphicon glyphicon-calendar"></span>
							</span>
						</div>
					</div>
					<div class="form-group">
						<div class='' id='nomeResponsavel'>
							<label for="sel2" class="form-label ages">Responsável:<span class="red">*</span></label> 
							<select class="form-control" id="sel2"  >
						        <option>Selecione...</option>
						        <option>Responsável 1</option>
						        <option>Responsável 2</option>
						        <option>Responsável 3</option>
					        </select>
						</div>
					</div>
					<div class="form-group">
						<div class='' id='senhaResponsavel'>
							<label class="form-label ages">Senha:<span class="red">*</span></label> 
							<input type='password' class="form-control" /> 
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
		});

		$('#dataSaida').datetimepicker({
			useCurrent : false, //Important! See issue #1075
			locale : 'pt-br',
			sideBySide : true
		});

		$("#dataEntrada").on("dp.change", function(e) {
			$('#dataSaida').data("DateTimePicker").minDate(e.date);
		});

		$("#dataSaida").on("dp.change", function(e) {
			$('#dataEntrada').data("DateTimePicker").maxDate(e.date);
		});
	});
</script>
<jsp:include page="/template/foot.jsp"></jsp:include>