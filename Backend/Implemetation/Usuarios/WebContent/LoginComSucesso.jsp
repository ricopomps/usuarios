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
				href="/Usuarios/listTelefones">Lista de telefones</a>
		</h2>
	</center>
	<div align="center">
		
			<form action="loginExecute" method="post">
		
		
		<table border="1" cellpadding="5">
			<caption>
				<h2>LOGIN COM SUCESSO!!</h2>
			</caption>
			
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
			<tr>
				<td colspan="2" align="center"><input type="submit"
					value="Login" /></td>
			</tr>
		</table>
		</form>
	</div>
</body>
</html>