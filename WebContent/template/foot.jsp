    </div>
</body>

<footer>
<% String v = (String) session.getAttribute("versao"); 
	String ver = v.equals("$1.0.${build.number}") ? "homologação"  : v;
%>
	<p style="text-align: center;" >&copy; Copyright AGES 2015 - Todos os direitos reservados | v<%=ver %></p>
</footer>

</html>