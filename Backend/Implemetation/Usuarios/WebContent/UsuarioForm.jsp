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
		<c:if test="${usuario != null}">
			<form action="update" method="post">
		</c:if>
		<c:if test="${usuario == null}">
			<form action="insert" method="post">
		</c:if>
		<table border="1" cellpadding="5">
			<caption>
				<h2>
					<c:if test="${usuario != null}">
                        Editar usuario
                    </c:if>
					<c:if test="${usuario == null}">
                        Adicionar novo usuario
                    </c:if>
				</h2>
			</caption>
			<c:if test="${usuario != null}">
				<input type="hidden" name="id"
					value="<c:out value='${usuario.id}' />" />
			</c:if>
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
					value="Salvar" /></td>
			</tr>
		</table>
		</form>
	</div>
</body>
</html>