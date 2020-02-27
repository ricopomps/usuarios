<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Banco de usuarios</title>
</head>
<body>
	<center>

		<table border="3" cellpadding="20">
			<tr>
				<th>
					<h1>Gerenciador de usuarios</h1>
				</th>
			</tr>
		</table>
		<h2>
			<table border="3" cellpadding="20">
				<tr>

					<th><a href="/Usuarios/new">Adicione um novo usuario</a></th>
					<th><a href="/Usuarios/newTelefone">Adicione um novo
							telefone</a></th>
					<th><a href="/Usuarios/list">Liste todos os usuarios</a></th>
					<th><a href="/Usuarios/listTelefones">Lista de telefones</a></th>
					<th><a href="/Usuarios/login"> Login </a></th>
					<th><a href="/Usuarios/searchUsuarios">Buscar usuario </a></th>
					<th><a href="/Usuarios/searchTelefones">Buscar telefone </a></th>
				</tr>
			</table>
		</h2>
	</center>
	<div align="center">
		<form action="searchTelefones" method="post">
			&nbsp;&nbsp;&nbsp;

			<caption>
				<h2>Buscar telefones</h2>
			</caption>

			<table border="1" cellpadding="5">

					<tr>
					<th>Id:</th>
					<td><input type="text" name="id" size="45"
						value="<c:out value='${telefone.id}' />" /></td>
				</tr>
				<tr>
					<th>Ddd:</th>
					<td><input type="text" name="ddd" size="45"
						value="<c:out value='${telefone.ddd}' />" /></td>
				</tr>
				<tr>
					<th>Numero:</th>
					<td><input type="text" name="numero" size="45"
						value="<c:out value='${telefone.numero}' />" /></td>
				</tr>
				<tr>
					<th>Tipo:</th>
					<td><input type="text" name="tipo" size="45"
						value="<c:out value='${telefone.tipo}' />" /></td>
				</tr>
				<tr>
					<th>Id_usuario:</th>
					<td><input type="text" name="id_usuario" size="45"
						value="<c:out value='${telefone.id_usuario}' />" /></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit"
						value="Buscar" /></td>
				</tr>

			</table>
			<table border="1" cellpadding="5">


				<caption>
					<h2>Lista de usuarios</h2>
				</caption>


				<tr>
					<th>ID</th>
					<th>DDD</th>
					<th>Numero</th>
					<th>Tipo</th>
					<th>Id_usuario</th>
					<th>Ações</th>
				</tr>
				<c:forEach var="telefone" items="${listaTelefone}">
					<tr>
						<td><c:out value="${telefone.id}" /></td>
						<td><c:out value="${telefone.ddd}" /></td>
						<td><c:out value="${telefone.numero}" /></td>
						<td><c:out value="${telefone.tipo}" /></td>
						<td><c:out value="${telefone.id_usuario}" /></td>

						<td><a
							href="/Usuarios/editTelefone?id=<c:out value='${telefone.id}' />">Edit</a>
							&nbsp;&nbsp;&nbsp;&nbsp; <a
							href="/Usuarios/deleteTelefone?id=<c:out value='${telefone.id}' />">Delete</a></td>
					</tr>
				</c:forEach>
			</table>
	</div>
</body>
</html>