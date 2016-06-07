<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@page import="br.ages.crud.model.Usuario"%>
<%@page import="br.ages.crud.model.SkillsDefinicao"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<jsp:include page="../template/head.jsp"></jsp:include>
	
	<div class="panel panel-primary panel-addSkills">

		<div class="panel-heading text-center">Avaliação Hard e Soft Skills</div>
		<div class="panel-body">
		
		<jsp:include page="/template/msg.jsp"></jsp:include>

	
				<form method="post" action="main?acao=adicionaSkill" >
					<div class="form-group">
					<div class="row">
						<div class="col-sm-6">
						<div class='' id='nomeAluno'>
							<label  for="sel1" class="form-label ages">Aluno:<span class="red">*</span></label> 
							<select class="form-control" id="idAluno" name="idAluno"  required="required"  "${param.idAluno}">
							 	<option value="">Selecione um aluno</option>
							 	<%
									List<Usuario> listaUsuarios = (List<Usuario>) request.getAttribute("usuarios");
									for (Usuario u : listaUsuarios) {
							  	 %>
								<option value="<%=u.getIdUsuario()%>"><%=u.getNome()%></option>
								<%
									}
								%>
					        </select>
						</div>
						</div>
						<div class="col-sm-6">
					    	<label class="form-label ages">Data Avaliação:<span class="red">*</span></label> 
							<div class='input-group date' id='dataAvalicao'>
								<input type='text' class="form-control" id='dtAvaliacao' name="dtAvaliacao" required="required"/>
								<span class="input-group-addon">
									<span class="glyphicon glyphicon-calendar"></span>
								</span>
							</div>
						</div>
					</div>
						<div class='' id='avaliacao'>
						<div class="well  well-sm" style="margin-top: 10px;">
							<label  class="form-label ages titulo-skill">HardSkills</label>
					 		<%
								List<SkillsDefinicao> skillsHards = (List<SkillsDefinicao>) request.getAttribute("skillsHards");
								for (SkillsDefinicao h : skillsHards) {
						  	 %>
							<div class="row ages">
							  	 <div class="col-sm-3">
							  	 	<label class="form-label ages" data-toggle="tooltip" title="<%=h.getDescricao()%>"><%=h.getNome()%></label> 
							  	 </div>
							  	 <div class="col-sm-3">
					  	 			<input type="radio"  class="" id="<%=h.getIdSkillsDefinicao()%>"  name="<%=h.getIdSkillsDefinicao()%>" value="999" required="required"> S/A
									<input type="radio"  class="" id="<%=h.getIdSkillsDefinicao()%>"  name="<%=h.getIdSkillsDefinicao()%>" value="1" required="required"> 1
									<input type="radio"  class="" id="<%=h.getIdSkillsDefinicao()%>"  name="<%=h.getIdSkillsDefinicao()%>" value="2" required="required"> 2
									<input type="radio"  class="" id="<%=h.getIdSkillsDefinicao()%>"  name="<%=h.getIdSkillsDefinicao()%>" value="3" required="required"> 3
									<input type="radio"  class="" id="<%=h.getIdSkillsDefinicao()%>"  name="<%=h.getIdSkillsDefinicao()%>" value="4"required="required"> 4 <br/>
							  	 </div>
							  	 <div class="col-sm-6">
							  	 	<input type='text' class="form-control" id="obsHard" name="obs<%=h.getIdSkillsDefinicao()%>" /> 
							  	 </div>
							</div>
							<%
								}
							%>
							<span class="red pull-right">S/A: Sem condições de avaliar </span>
							</div>
							<div class="well  well-sm">
							<label  class="form-label ages titulo-skill">SoftsSkills</label>
					 		<%
								List<SkillsDefinicao> skillsSofts = (List<SkillsDefinicao>) request.getAttribute("skillsSofts");
								for (SkillsDefinicao s : skillsSofts) {
						  	 %>
							<div class="row ages">
							  	 <div class="col-sm-3">
							  	 	<label class="form-label ages" data-toggle="tooltip" title="<%=s.getDescricao()%>"><%=s.getNome()%></label> 
							  	 </div>
							  	 <div class="col-sm-3">
					  	 			<input type="radio"  class="" id="<%=s.getIdSkillsDefinicao()%>"  name="<%=s.getIdSkillsDefinicao()%>" value="999" required="required"> S/A
									<input type="radio"  class="" id="<%=s.getIdSkillsDefinicao()%>"  name="<%=s.getIdSkillsDefinicao()%>" value="1" required="required"> 1
									<input type="radio"  class="" id="<%=s.getIdSkillsDefinicao()%>"  name="<%=s.getIdSkillsDefinicao()%>" value="2" required="required"> 2
									<input type="radio"  class="" id="<%=s.getIdSkillsDefinicao()%>"  name="<%=s.getIdSkillsDefinicao()%>" value="3" required="required"> 3
									<input type="radio"  class="" id="<%=s.getIdSkillsDefinicao()%>"  name="<%=s.getIdSkillsDefinicao()%>" value="4" required="required"> 4 <br/>
							  	 </div>
							  	 <div class="col-sm-6">
							  	 	<input type='text' class="form-control" id="obsSoft" name="obs<%=s.getIdSkillsDefinicao()%>" /> 
							  	 </div>
							</div>
							<%
								}
							%>
							<span class="red pull-right">S/A: Sem condições de avaliar </span>
							</div>
				 		</div>
				 		<div class="row">
				 			<div class="col-sm-6">
						 		<div class='' id='nomeResponsavel'>
									<label for="sel2" class="form-label ages">Responsável Avaliação:</label> 
									<select class="form-control" id="idResponsavel" name="idResponsavel" required="required">
								        <option value="">Selecione um responsável</option>
								         
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
				 			</div>
				 			<div class="col-sm-6">
								<div class='' id='senhaResponsavel'>
									<label class="form-label ages">Senha:</label> 
									<input type='password' class="form-control" id="senhaResponsavel" name="senhaResponsavel" required="required"/> 
								</div>
				 			</div>
				 		</div>
					</div>
	                    
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
		$('#dataAvalicao').datetimepicker({
			locale : 'pt-br',
			showTodayButton: true
		});
	});
	
	$(document).ready(function(){
	    $('[data-toggle="tooltip"]').tooltip();
	});
	
</script>
<jsp:include page="/template/foot.jsp"></jsp:include>