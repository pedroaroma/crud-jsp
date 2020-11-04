package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import beans.BeanCursoJsp;
import beans.Telefone;
import dao.TelefoneDAO;
import dao.UsuarioDAO;

@WebServlet("/salvarTelefones")
public class TelefonesServlets extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private TelefoneDAO telefoneDAO = new TelefoneDAO();
	private UsuarioDAO usuarioDAO = new UsuarioDAO();

	public TelefonesServlets() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String acao = request.getParameter("acao");

			if (acao.equalsIgnoreCase("listarTelefones")) {

				String userId = request.getParameter("userId");
				BeanCursoJsp usuario = usuarioDAO.consultar(userId);

				request.getSession().setAttribute("userEscolhido", usuario);

				RequestDispatcher view = request.getRequestDispatcher("/telefones.jsp");

				request.setAttribute("userEscolhido", usuario);
				request.setAttribute("telefones", telefoneDAO.listar((int) usuario.getId()));
				request.setAttribute("msg", "entrou aqui no listarTelefones");
				view.forward(request, response);

			} else if (acao.equalsIgnoreCase("delete")) {

				String telefoneid = request.getParameter("telefoneId");

				telefoneDAO.delete(telefoneid);

				BeanCursoJsp beanCursoJsp = (BeanCursoJsp) request.getSession().getAttribute("userEscolhido");

				RequestDispatcher view = request.getRequestDispatcher("/telefones.jsp");

				request.setAttribute("userEscolhido", beanCursoJsp);
				request.setAttribute("telefones", telefoneDAO.listar((int) beanCursoJsp.getId()));
				request.setAttribute("msg", "Deletado com Sucesso");

				view.forward(request, response);

			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			BeanCursoJsp beanCursoJsp = (BeanCursoJsp) request.getSession().getAttribute("userEscolhido");
			String numero = request.getParameter("numero");
			String tipo = request.getParameter("tipo");

			Telefone telefone = new Telefone();

			telefone.setId_usuario((int) beanCursoJsp.getId());
			telefone.setNumero(numero);
			telefone.setTipo(tipo);

			telefoneDAO.salvar(telefone);

			request.getSession().setAttribute("userEscolhido", beanCursoJsp);
			request.setAttribute("userEscolhido", beanCursoJsp);

			RequestDispatcher view = request.getRequestDispatcher("/telefones.jsp");

			request.setAttribute("telefones", telefoneDAO.listar((int) beanCursoJsp.getId()));
			request.setAttribute("msg", "Salvo com Sucesso");

			view.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
