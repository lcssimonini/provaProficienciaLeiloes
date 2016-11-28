<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<title>Sistema de leil�es online</title>
		<style>
			table, th, td {
				border:1px solid black;
				padding: 2px;
			}
		</style>
	</head>
	
	<body>
		<h1>Artigos Salvos</h1>
		
		<a href="login">Login no sistema</a>
		<br>
		<a href="cadastro_usuario">Cadastro de usu�rios no sistema</a>
		<br>
		<a href="cadastro_produto">Cadastro de produtos no sistema</a>
		<br>
		<br>
		<a href="lance">Fazer um lance</a>
		<br>
		<br>
		
		<table>
			<tr>
				<th>numero</th>
				<th>descricao</th>
				<th>valor minimo</th>
				<th>Est� aberto?</th>
			<tr>
			<c:forEach var="artigo" items="${artigos}">
				<tr>
					<td> ${artigo.numero} </td>
					<td> ${artigo.descricao} </td> 
					<td> ${artigo.valorMinimo} </td> 
					<td> ${artigo.isOpen()} </li>
				</tr>
			</c:forEach>
		</table>		
	</body>	
</html>