package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.LoginDAO;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private LoginDAO loginDAO = new LoginDAO();

	public LoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			// BeanCursoJsp beanCursoJsp = new BeanCursoJsp();

			String login = request.getParameter("login");
			String senha = request.getParameter("senha");

			if ((login != null && !login.isEmpty()) && (senha != null && !senha.isEmpty())) {

				if (loginDAO.validarLogin(login, senha)) { // acesso ok

					RequestDispatcher dispatcher = request.getRequestDispatcher("acessoliberado.jsp");

					request.setAttribute("userid", loginDAO.getUserId(login, senha));
					dispatcher.forward(request, response);

				} else {
					// acesso negado
					RequestDispatcher dispatcher = request.getRequestDispatcher("acessonegado.jsp");
					dispatcher.forward(request, response);

				}
			}else {
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
				dispatcher.forward(request, response);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}