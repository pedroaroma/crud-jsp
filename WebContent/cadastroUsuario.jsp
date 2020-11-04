<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cadastro de usuário</title>
<link rel="stylesheet" href="resources/css/register.css">

<script src="https://code.jquery.com/jquery-3.4.1.min.js"
	integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
	crossorigin="anonymous"></script>

<script type="text/javascript">
	function consultaCep() {
		var cep = $("#cep").val();
		//alert(cep);

		$.getJSON("https://viacep.com.br/ws/" + cep + "/json/?callback=?",
				function(dados) {

					if (!("erro" in dados)) {
						//Atualiza os campos com os valores da consulta.
						$("#rua").val(dados.logradouro);
						$("#bairro").val(dados.bairro);
						$("#cidade").val(dados.localidade);
						$("#uf").val(dados.uf);
						$("#ibge").val(dados.ibge);

					} //end if.
					else {
						//CEP pesquisado não foi encontrado.
						$("#cep").val('');
						$("#rua").val('');
						$("#bairro").val('');
						$("#cidade").val('');
						$("#uf").val('');
						$("#ibge").val('');
						alert("CEP não encontrado.");
					}
				});

	}
</script>

</head>
<body>
	<center>

		<h1>Cadastro de usuário</h1>
		<h3>${msg}</h3>
		<a href="acessoliberado.jsp">Voltar</a>
		<form action="Usuario" method="post" id="formUser"
			enctype="multipart/form-data">
			<ul class="form-style-1">
				<li>
					<table>
						<tr>
							<td>ID:</td>
							<td><input type="text" id="id" name="id" value="${user.id}"
								readonly="readonly" name="field1" class="field-divided"></td>
						</tr>

						<tr>
							<td>Login:</td>
							<td><input type="text" id="login" name="login"
								value="${user.login}" name="field1" class="field-divided"></td>
						</tr>

						<tr>
							<td>Senha:</td>
							<td><input type="password" id="senha" name="senha"
								value="${user.senha}" name="field1" class="field-divided"></td>
						</tr>

						<tr>
							<td>Nome:</td>
							<td><input type="text" id="nome" name="nome"
								value="${user.nome}" name="field1" class="field-divided"></td>
						</tr>

						<tr>
							<td>Telefone:</td>
							<td><input type="text" id="telefone" name="telefone"
								value="${user.telefone}" name="field1" class="field-divided"></td>
						</tr>

						<tr>
							<td>CEP:</td>
							<td><input type="text" id="cep" name="cep"
								onblur="consultaCep();" value="${user.cep}" name="field1"
								class="field-divided"></td>
						</tr>

						<tr>
							<td>Rua:</td>
							<td><input type="text" id="rua" name="rua"
								readonly="readonly" value="${user.rua}" name="field1"
								class="field-divided"></td>
						</tr>

						<tr>
							<td>Bairro:</td>
							<td><input type="text" id="bairro" name="bairro"
								readonly="readonly" value="${user.bairro}" name="field1"
								class="field-divided"></td>
						</tr>

						<tr>
							<td>Cidade:</td>
							<td><input type="text" id="cidade" name="cidade"
								readonly="readonly" value="${user.cidade}" name="field1"
								class="field-divided"></td>
						</tr>

						<tr>
							<td>Estado:</td>
							<td><input type="text" id="uf" name="uf" readonly="readonly"
								value="${user.estado}" name="field1" class="field-divided"></td>
						</tr>

						<tr>
							<td>IBGE:</td>
							<td><input type="text" id="ibge" name="ibge"
								readonly="readonly" value="${user.ibge}" name="field1"
								class="field-divided"></td>
						</tr>

						<tr>
							<td>Foto:</td>
							<td><input type="file" name="foto" id="foto"></td>
							<td><input type="hidden" name="fotoTemp"
								value="${user.fotoBase64}" readonly="readonly"></td>
							<td><input type="hidden" name="contentTypeTemp"
								value="${user.contentType}" readonly="readonly"></td>
						</tr>

						<tr>
							<td>Curriculo:</td>
							<td><input type="file" name="curriculo" value="curriculo"></td>
							<td><input type="hidden" name="curriculoBase64Temp"
								value="${user.curriculoBase64}" readonly="readonly"></td>
							<td><input type="hidden" name="curriculoContentTypeTemp"
								value="${user.contenttypecurriculo}" readonly="readonly"></td>
						</tr>

						<tr>
							<td><input type="submit" value="Cadastrar"></td>
							<td><input type="reset" value="Cancelar"></td>
						</tr>


					</table>
				</li>
			</ul>
		</form>

		<div class="container">
			<div>
				<table class="responsive-table">
					<tr>
						<th>ID</th>
						<th>Login</th>
						<th>Foto:</th>
						<th>Curriculo</th>
						<th>Nome</th>
						<th>Telefone</th>
						<th>Apagar</th>
						<th>Editar</th>
						<th>telefone</th>
					</tr>

					<c:forEach items="${usuarios}" var="user">
						<tr>
							<td style="width: 100px"><c:out value="${user.id}"></c:out></td>
							<td><c:out value="${user.login}"></c:out></td>
							
							<c:if test="${user.fotoBase64.isEmpty() == false}">
								<td><a
									href="Usuario?acao=download&tipo=image&id=${user.id}"><img
										src="<c:out value="${user.tempFotoUser}"/>" width="32px"
										height="32px" /></a></td>
							</c:if>
							
							<c:if test="${user.fotoBase64.isEmpty() == true}">
								<td><img alt="Image User" src="resources/img/user.png" width="32px" height="32px"></td>
							</c:if>
							
							<c:if test="${user.curriculoBase64.isEmpty() == false}">

								<td><a
									href="Usuario?acao=download&tipo=curriculo&id=${user.id}"><img
										alt="curriculo" src="resources/img/pdf.png" width="32px"
										height="32px"></a></td>

							</c:if>
							
							<c:if test="${user.curriculoBase64.isEmpty() == true}">
								<td><img alt="Sem Pdf" src="resources/img/pdfblock.jpg" height="32px" width="32px"></td>
							</c:if>

							<td><c:out value="${user.nome}"></c:out></td>
							<td><c:out value="${user.telefone}"></c:out></td>

							<td><a href="Usuario?acao=delete&id=${user.id}"><img
									alt="Excluir" src="resources/img/delete.png" width="20px"
									height="20px"></a></td>

							<td><a href="Usuario?acao=editar&id=${user.id}"><img
									alt="Editar" src="resources/img/edit.png" width="20px"
									height="20px"></a></td>

							<td><a
								href="salvarTelefones?acao=listarTelefones&userId=${user.id}"><img
									alt="Editar" src="resources/img/edit.png" width="20px"
									height="20px"></a></td>
						</tr>
					</c:forEach>

				</table>
			</div>
		</div>
	</center>
</body>
</html>