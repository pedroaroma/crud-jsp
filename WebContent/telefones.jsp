<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cadastro de usuário</title>
<link rel="stylesheet" href="resources/css/register.css">

</head>
<body>
	<center>
		<a href="acessoliberado.jsp">Voltar</a>

		<h1>Cadastro de Telefones</h1>
		<h3 style="color: orange;">${msg}</h3>
		<form action="salvarTelefones" method="post" id="formUser">

			<table>
				<tr>
					<td>ID do usuário:</td>
					<td><input type="text" id="userId" name="userId"
						value="${userEscolhido.id}" readonly="readonly" name="field1"
						class="field-divided"></td>
				</tr>

				<tr>
					<td>Nome do usuário:</td>
					<td><input type="text" id="userNome" name="userNome"
						value="${userEscolhido.nome}" readonly="readonly" name="field1"
						class="field-divided"></td>
				</tr>

				<tr>
					<td>Número:</td>
					<td><input type="text" id="numero" name="numero" name="field1"
						class="field-divided"></td>

				</tr>

				<tr>
					<td></td>

					<td><select id="tipo" name="tipo">
							<option>Casa</option>
							<option>Contato</option>
							<option>Celular</option>
					</select></td>
				</tr>

				<tr>
					<td><input type="submit" value="salvar"> <input
						type="reset" value="cancelar"></td>
				</tr>

			</table>

		</form>

		<div class="container">
			<div>
				<table class="responsive-table">
					<tr>
						<th>ID USUARIO</th>
						<th>ID TELEFONE</th>
						<th>Número</th>
						<th>Tipo</th>
						<th>Excluir</th>

					</tr>

					<c:forEach items="${telefones}" var="telefone">
						<tr>
							<td><c:out value="${telefone.id_usuario}"></c:out></td>
							<td><c:out value="${telefone.id_telefone}"></c:out></td>
							<td><c:out value="${telefone.numero}"></c:out></td>
							<td><c:out value="${telefone.tipo}"></c:out></td>

							<td style="width: 100px"><a
								href="salvarTelefones?acao=delete&telefoneId=${telefone.id_telefone}"><img
									alt="Excluir" src="resources/img/delete.png" width="20px"
									height="20px"></a></td>
					</c:forEach>

				</table>
			</div>
		</div>
	</center>
</body>
</html>