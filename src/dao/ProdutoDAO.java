package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



import beans.BeanProduto;
import connection.SingleConnection;

public class ProdutoDAO {

	private Connection connection;

	public ProdutoDAO() {

		connection = SingleConnection.getConnection();

	}
	
	public void salvar(BeanProduto produto) { //CREATE
		
		try {
			
			String sql = "INSERT INTO produto (nome, quantidade, valor, id_usuario) VALUES (?, ?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, produto.getNome());
			preparedStatement.setInt(2, produto.getQuantidade());
			preparedStatement.setDouble(3, produto.getValor());
			preparedStatement.setInt(4, produto.getUsuario_id());
			
			preparedStatement.execute();
			connection.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			
			try {
				connection.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public ArrayList<BeanProduto> listar() throws Exception { //READ
		
		ArrayList<BeanProduto> listar = new ArrayList<BeanProduto>();
		String sql = "SELECT * FROM produto ORDER BY id_produto ASC";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		
		while(resultSet.next()){
			
			BeanProduto beanProduto = new BeanProduto();
		
			beanProduto.setId_produto(resultSet.getInt("id_produto"));
			beanProduto.setNome(resultSet.getString("nome"));
			beanProduto.setQuantidade(resultSet.getInt("quantidade"));
			beanProduto.setValor(resultSet.getDouble("valor"));
			beanProduto.setUsuario_id(resultSet.getInt("id_usuario"));
			
			listar.add(beanProduto);
		}
		
		return listar;
	}
	
	public void atualizar (BeanProduto produto) { //UPDATE
		
		String sql = "UPDATE produto SET nome = ?, quantidade = ?, valor = ? WHERE id_produto = " + produto.getId_produto();
		try {
			System.out.println(sql);
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1, produto.getNome());
			statement.setInt(2, produto.getQuantidade());
			statement.setDouble(3, produto.getValor());
			
			statement.executeUpdate();
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		
	}
	
	public void deletar (String id){
		
		String sql = "DELETE FROM produto WHERE id_produto = " + id;
		System.out.println(sql);
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.execute();
			connection.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public BeanProduto consultar(String produtoid) throws Exception {
		
		String sql = "SELECT * FROM produto WHERE id_produto = " + produtoid;
		System.out.println(sql);
		
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		
		if(resultSet.next()){
			BeanProduto beanProduto = new BeanProduto();
			
			beanProduto.setId_produto(resultSet.getInt("id_produto"));
			beanProduto.setNome(resultSet.getString("nome"));
			beanProduto.setQuantidade(resultSet.getInt("valor"));
			beanProduto.setValor(resultSet.getDouble("valor"));
			beanProduto.setUsuario_id(resultSet.getInt("id_usuario"));
			
			System.out.println(beanProduto.toString());
			
			return beanProduto;
		}
		
		return null;
	}


}