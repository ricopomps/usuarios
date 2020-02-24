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
			<a href="/Usuarios/new">Adicione um novo usuario</a> &nbsp;&nbsp;&nbsp; <a
				href="/Usuarios/list">Liste todos os usuarios</a>

		</h2>
	</center>
	<div align="center">
			<form action="update" method="post">
		<table border="1" cellpadding="5">
			<caption>
				<h2>
                        Editar usuario        					
				</h2>
			</caption>
			<input id="id" name="id" type="hidden" value='${usuario.nome}'>
				<input type="text" name="id"
					value='${usuario.id}'  />
					
					<input id="id" name="id" type="text" value='${usuario.id}'>
					<input id="id" name="id" type="text" value='${usuario.nome}'>
					<input id="id" name="id" type="text" value='${usuario.email}'>
					<input id="id" name="id" type="text" value='${usuario.senha}'>
			<tr>
				<th>id:</th>
				<td><input type="text" name="id" size="45"
					value="<c:out value='${usuario.id}' />" /></td>
			</tr>
			<tr>
				<th>Nome:</th>
				<td><input type="text" name="nome" size="45"
					value="<c:out value='${usuario.nome}' />" /></td>
			</tr>
			<tr>
				<th>Email:</th>
				<td><input type="text" name="email" size="45"
					value="<c:out value='${usuario.email}' />" /></td>
			</tr>
			<tr>
				<th>Senha:</th>
				<td><input type="text" name="senha" size="45"
					value="<c:out value='${usuario.senha}' />" /></td>
			</tr>
			<h2><a href="/Usuarios/update?id=1">Edit</a></h2>
			<tr>
				<td colspan="2" align="center"><input type="submit"
					value="Save" /></td>
			</tr>
		</table>
		</form>
	</div>
</body>
</html>