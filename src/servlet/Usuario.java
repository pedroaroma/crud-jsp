package servlet;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.codec.binary.Base64;

import beans.BeanCursoJsp;
import dao.UsuarioDAO;

@WebServlet("/Usuario")
@MultipartConfig
public class Usuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UsuarioDAO usuarioDAO = new UsuarioDAO();

	public Usuario() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getParameter("acao");
		String user = request.getParameter("user");
		String id = request.getParameter("id");

		if (acao.equalsIgnoreCase("delete")) {

			usuarioDAO.delete(id);

			try {

				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("msg", "Usuário Deletado com sucesso");
				request.setAttribute("usuarios", usuarioDAO.listar());
				view.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		else if (acao.equalsIgnoreCase("editar")) {

			BeanCursoJsp beanCursoJsp;
			try {
				System.out.println(id);
				beanCursoJsp = usuarioDAO.consultar(id);
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("user", beanCursoJsp);
				view.forward(request, response);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (acao.equalsIgnoreCase("listartodos")) {

			RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
			try {
				request.setAttribute("usuarios", usuarioDAO.listar());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			view.forward(request, response);

		} else if (acao.equalsIgnoreCase("download")) {

			try {
				BeanCursoJsp usuario = usuarioDAO.consultar(id);

				if (usuario != null) {

					String contenttype = "";
					byte[] fileBytes = null;
					String tipo = request.getParameter("tipo");

					if (tipo.equalsIgnoreCase("image")) {

						contenttype = usuario.getContentType();

						/* Converte a base64 das imagem do banco para byte[] */
						new Base64();
						fileBytes = Base64.decodeBase64(usuario.getFotoBase64());

					} else if (tipo.equalsIgnoreCase("curriculo")) {

						contenttype = usuario.getContenttypecurriculo();

						new Base64();
						fileBytes = Base64.decodeBase64(usuario.getCurriculoBase64());

					}

					response.setHeader("Content-Disposition",
							"attachment;filename=arquivo." + contenttype.split("\\/")[1]);

					/* Coloca os Bytes em um objeto de entrada para processar */
					InputStream is = new ByteArrayInputStream(fileBytes);

					/* Inicio da resposta */
					int read = 0;
					byte[] bytes = new byte[1024];
					OutputStream os = response.getOutputStream();

					while ((read = is.read(bytes)) != -1) {
						os.write(bytes, 0, read);
					}

					os.flush();
					os.close();

				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		String nome = request.getParameter("nome");
		String telefone = request.getParameter("telefone");
		String cep = request.getParameter("cep");
		String rua = request.getParameter("rua");
		String bairro = request.getParameter("bairro");
		String cidade = request.getParameter("cidade");
		String estado = request.getParameter("uf");
		String ibge = request.getParameter("ibge");

		BeanCursoJsp usuario = new BeanCursoJsp();

		usuario.setId(!id.isEmpty() ? Long.parseLong(id) : 0);
		usuario.setLogin(login);
		usuario.setSenha(senha);
		usuario.setNome(nome);
		usuario.setTelefone(telefone);
		usuario.setCep(cep);
		usuario.setRua(rua);
		usuario.setBairro(bairro);
		usuario.setCidade(cidade);
		usuario.setEstado(estado);
		usuario.setIbge(Integer.parseInt(ibge));

		RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");

		try {

			if (ServletFileUpload.isMultipartContent(request)) {

				/* Processa imagem */
				Part imageFoto = request.getPart("foto");

				if (imageFoto != null && imageFoto.getInputStream().available() > 0) {

					new Base64();
					String fotoBase64 = Base64.encodeBase64String(converteStreamParaByte(imageFoto.getInputStream()));

					usuario.setFotoBase64(fotoBase64);
					usuario.setContentType(imageFoto.getContentType());
					
				} else {

					usuario.setFotoBase64(request.getParameter("fotoTemp"));
					usuario.setContentType(request.getParameter("contentTypeTemp"));
					
				}
				/* Processa PDF */

				Part curriculoPdf = request.getPart("curriculo");

				if (curriculoPdf != null && curriculoPdf.getInputStream().available() > 0) {

					new Base64();
					String curriculoBase64 = Base64
							.encodeBase64String(converteStreamParaByte(curriculoPdf.getInputStream()));

					usuario.setCurriculoBase64(curriculoBase64);
					usuario.setContenttypecurriculo(curriculoPdf.getContentType());

				}else {
					usuario.setCurriculoBase64(request.getParameter("curriculoBase64Temp"));
					usuario.setContenttypecurriculo(request.getParameter("curriculoContentTypeTemp"));
				}

			}

			if (id == null || id.isEmpty() && usuarioDAO.validarLogin(login)) {

				usuarioDAO.salvar(usuario);
				request.setAttribute("msg", "Usuário Salvo com Sucesso");

			} else if (id != null && !id.isEmpty()) {
				usuarioDAO.atualizar(usuario);

				request.setAttribute("msg", "Usuário editado com sucesso");
			}

			else {
				request.setAttribute("msg", "usuário já cadastrado");
				request.setAttribute("user", usuario);
			}

			request.setAttribute("usuarios", usuarioDAO.listar());
			view.forward(request, response);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/* Converte a entrada de fluxo de dados para array de bytes */

	/* Este método serve para qualquer tipo de arquivo (pdf, imagem) */

	private byte[] converteStreamParaByte(InputStream imagem) throws Exception {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int reads = imagem.read();

		while (reads != -1) {
			baos.write(reads);
			reads = imagem.read();
		}

		return baos.toByteArray();
	}

}