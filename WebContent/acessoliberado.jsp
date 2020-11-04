<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<jsp:useBean id="calcula" class="beans.BeanCursoJsp"
	type="beans.BeanCursoJsp" scope="page"></jsp:useBean>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Acesso Liberado</title>
</head>
<body>
<center>

		<h3>Acesso Liberado!</h3>
		<h3>Seja bem vindo ao sistema em jsp</h3>
		<h3>${userid}</h3>
		<a href="Usuario?acao=listartodos">Cadastro de Usuários</a> 
		<a href="Produto?acao=listarprodutos&userid=${userid}">Cadastrar Produtos</a>
		<a href="index.jsp">Sair</a>
	</center>
</body>
</html>