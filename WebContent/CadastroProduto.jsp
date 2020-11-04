<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cadastro de Produtos</title>
</head>
<body>

<a href="acessoliberado.jsp">Voltar</a>
	<center>
		<h3>Cadastro de Produtos</h3>
		<h4>${msg}</h4>


		<form action="Produto" method="post">
			<ul>
				<li>
					<table>
						<tr>
							<td>ID:</td>
							<td><input type="text" id="id" name="id"
								value="${produto.id_produto}" readonly="readonly"></td>
						</tr>

						<tr>
							<td>Nome Produto</td>
							<td><input type="text" id="nomeproduto" name="nomeproduto"
								value="${produto.nome}"></td>
						</tr>

						<tr>
							<td>QUANTIDADE</td>
							<td><input type="text" id="quantidade" name="quantidade"
								value="${produto.quantidade}"></td>
						</tr>

						<tr>
							<td>VALOR</td>
							<td><input type="text" id="valor" name="valor"
								value="${produto.valor}"></td>
						</tr>

						<tr>
							<td>ID DO USUÁRIO:</td>
							<td><input type="text" id="userid" name="userid"
								value="${userid == null ? produto.usuario_id : userid}"
								readonly="readonly"></td>
						</tr>

						<tr>
							<td><input type="submit" value="Cadastrar Produto"></td>

						</tr>
					</table>
				</li>
			</ul>
		</form>

		<table>
			<tr>
				<th>ID PRODUTO</th>
				<th>NOME PRODUTO</th>
				<th>QUANTIDADE</th>
				<th>VALOR</th>
				<th>ID DE USUÁRIO</th>
			</tr>

			<c:forEach items="${produtos}" var="produto">
				<tr>
					<td><c:out value="${produto.id_produto}"></c:out>
					<td><c:out value="${produto.nome}"></c:out>
					<td><c:out value="${produto.quantidade}"></c:out>
					<td><c:out value="${produto.valor}"></c:out>
					<td><c:out value="${produto.usuario_id}"></c:out>
					<td><a
						href="Produto?acao=editar&produtoid=${produto.id_produto}">Editar
							Produto</a>
					<td><a
						href="Produto?acao=deletar&produtoid=${produto.id_produto}">Excluir
							Produto</a>
				</tr>
			</c:forEach>
		</table>

	</center>
</body>
</html>