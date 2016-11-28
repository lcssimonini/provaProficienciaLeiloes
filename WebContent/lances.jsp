<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<title>Sistema de leilões online</title>
		<style>
			table, th, td {
				border:1px solid black;
				padding: 2px;
			}
		</style>
	</head>
	
	<body>
		<h1>Artigos disponiveis para lance</h1>
		
		<a href="login">Login no sistema</a>
		<br>
		<a href="cadastro_usuario">Cadastro de usuários no sistema</a>
		<br>
		<a href="cadastro_produto">Cadastro de produtos no sistema</a>
		<br>
		<br>
		<a href="lance">Fazer um lance</a>
		<br>
		<br>
		
		<h1>Artigos</h1>
		<table>
			<tr>
				<th>numero</th>
				<th>descricao</th>
				<th>valor minimo</th>
				<th>novo lance</th>
			<tr>
			<c:forEach var="artigo" items="${artigos}">
				<c:if test="${artigo.isOpen()}">
					<tr>
						<td> ${artigo.numero} </td>
						<td> ${artigo.descricao} </td> 
						<td> ${artigo.valorMinimo} </td> 
						<td> 
							<form action="lance" method="POST">
								<input type="text" name="valor_lance" placeholder="valor do lance">
								<input type="hidden" name="numero_artigo" value="${artigo.numero}">
								<input type="submit" value="Lance">
							</form> 
						</td>
					</tr>
  				</c:if>
			</c:forEach>
		</table>
		
		
		<h1>Lances</h1>
		<table>
			<tr>
				<th>Numero do Artigo</th>
				<th>Valor do lance</th>
			<tr>
			<c:forEach var="lance" items="${lances}">
				<tr>
					<td> ${lance.numeroArtigo} </td>
					<td> ${lance.getValorLance()} </td> 
				</tr>
			</c:forEach>
		</table>		
	</body>	
</html>