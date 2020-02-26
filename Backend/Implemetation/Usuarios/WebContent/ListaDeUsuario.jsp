<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Banco de usuarios</title>
</head>
<body>
	<center>
		<h1>Gerenciador de usuarios</h1>
		<h2>
			<a href="/Usuarios/new">Adicione um novo usuario</a>
			&nbsp;&nbsp;&nbsp;<a href="/Usuarios/newTelefone">Adicione um
				novo telefone</a> &nbsp;&nbsp;&nbsp;<a href="/Usuarios/list">Liste
				todos os usuarios</a>&nbsp;&nbsp;&nbsp; <a
				href="/Usuarios/listTelefones">Lista de telefones</a>&nbsp;&nbsp;&nbsp;<a
				href="/Usuarios/login"> Login </a>
				&nbsp;&nbsp;&nbsp;<a
				href="/Usuarios/searchUsuarios">Buscar usuario </a>

		</h2>
	</center>
	<div align="center">
		<table border="1" cellpadding="5">
			<caption>
				<h2>Lista de usuarios</h2>
			</caption>
			<tr>
				<th>ID</th>
				<th>Nome</th>
				<th>Email</th>
				<th>Ações</th>
			</tr>
			<c:forEach var="usuario" items="${listaUsuario}">
				<tr>
					<td><c:out value="${usuario.id}" /></td>
					<td><c:out value="${usuario.nome}" /></td>
					<td><c:out value="${usuario.email}" /></td>
					<td><a
						href="/Usuarios/edit?id=<c:out value='${usuario.id}' />">Edit</a>
						&nbsp;&nbsp;&nbsp;&nbsp; <a
						href="/Usuarios/delete?id=<c:out value='${usuario.id}' />">Delete</a>
						&nbsp;&nbsp;&nbsp;&nbsp; <a
						href="/Usuarios/searchTelefone?id=<c:out value='${usuario.id}' />">Telefones</a>
					</td>

				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>