<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Banco de telefones</title>
</head>
<body>
	<center>
		<h1>Gerenciador de telefones</h1>
		<h2>
			<a href="/new">Adicione um novo telefone</a> &nbsp;&nbsp;&nbsp; <a
				href="/list">Liste todos os telefones</a>

		</h2>
	</center>
	<div align="center">
		<table border="1" cellpadding="5">
			<caption>
				<h2>Lista de telefones</h2>
			</caption>
			<tr>
				<th>ID</th>
				<th>Ddd</th>
				<th>Numero</th>
				<th>Tipo</th>
				<th>Id_usuario</th>
			</tr>
			<c:forEach var="telefone" items="${listaUsuarios}">
				<tr>
					<td><c:out value="${telefone.id}" /></td>
					<td><c:out value="${telefone.ddd}" /></td>
					<td><c:out value="${telefone.numero}" /></td>
					<td><c:out value="${telefone.tipo}" /></td>
					<td><c:out value="${telefone.id_usuario}" /></td>
					<td><a href="/edit?id=<c:out value='${telefone.id}' />">Edit</a>
						&nbsp;&nbsp;&nbsp;&nbsp; <a
						href="/delete?id=<c:out value='${telefone.id}' />">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>