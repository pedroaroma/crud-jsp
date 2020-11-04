package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanProduto;
import dao.ProdutoDAO;

@WebServlet("/Produto")
public class Produto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProdutoDAO produtoDAO = new ProdutoDAO();

	public Produto() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getParameter("acao");
		
		String id_usuario = request.getParameter("userid");
		
		String produtoid = request.getParameter("produtoid");
		
		

		if (acao.equalsIgnoreCase("listarprodutos")) {

			RequestDispatcher view = request.getRequestDispatcher("/CadastroProduto.jsp");
			try {
				System.out.println("caiu aqui no listarprodutos");
				request.setAttribute("userid", id_usuario);
				request.setAttribute("produtos", produtoDAO.listar());
			} catch (Exception e) {
				// TODO: handle exception
			}
			view.forward(request, response);
		}
		else if (acao.equalsIgnoreCase("editar")) {
			BeanProduto beanProduto;
			
			try {
				beanProduto = produtoDAO.consultar(produtoid);
				RequestDispatcher view = request.getRequestDispatcher("/CadastroProduto.jsp");
				request.setAttribute("produto", beanProduto);
				System.out.println("caiu no editar");
				view.forward(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			

		}
		else if (acao.equalsIgnoreCase("deletar")) {
			produtoDAO.deletar(produtoid);
			try {
				RequestDispatcher view = request.getRequestDispatcher("/CadastroProduto.jsp");
				request.setAttribute("msg", "Produto Deletado com Sucesso");
				request.setAttribute("produtos", produtoDAO.listar());
				view.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String id = request.getParameter("id");
		String nome_produto = request.getParameter("nomeproduto");
		String quantidade = request.getParameter("quantidade");
		String valor = request.getParameter("valor");
		String id_usuario = request.getParameter("userid");
		
		System.out.println("id do usuario " + id_usuario);
		
		BeanProduto beanProduto = new BeanProduto();
		
		beanProduto.setNome(nome_produto);
		beanProduto.setQuantidade(Integer.parseInt(quantidade));
		beanProduto.setValor(Double.parseDouble(valor));
		beanProduto.setUsuario_id(Integer.parseInt(id_usuario));
		
		RequestDispatcher view = request.getRequestDispatcher("/CadastroProduto.jsp");
		
		try {
			
			if(id == null || id.isEmpty()){
				
				produtoDAO.salvar(beanProduto);
				request.setAttribute("msg", "Produto salvo com sucesso");			
				
			} else if (id != null && !id.isEmpty()) {
				beanProduto.setId_produto(Integer.parseInt(id));
				produtoDAO.atualizar(beanProduto);
				request.setAttribute("msg", "Produto Atualizado com Sucesso");
			}
			
			
			request.setAttribute("userid", id_usuario);
			request.setAttribute("produtos", produtoDAO.listar());
			view.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
			
	}

}