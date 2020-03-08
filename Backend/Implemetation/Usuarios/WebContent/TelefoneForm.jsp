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
		<c:if test="${telefone != null}">
			<form action="updateTelefone" method="post">
		</c:if>
		<c:if test="${telefone == null}">
			<form action="insertTelefone" method="post">
		</c:if>
		<table border="1" cellpadding="5">
			<caption>
				<h2>
					<c:if test="${telefone != null}">
                        Editar telefone
                    </c:if>
					<c:if test="${telefone == null}">
                        Adicionar novo telefone
                    </c:if>
				</h2>
			</caption>
			<c:if test="${telefone != null}">
				<input type="hidden" name="id"
					value="<c:out value='${telefone.id}' />" />
			</c:if>
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
					value="Save" /></td>
			</tr>
		</table>
		</form>
		<button type="button" name="back" onclick="history.back()">back</button>
	</div>
</body>
</html>