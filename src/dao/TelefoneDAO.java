package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import beans.Telefone;
import connection.SingleConnection;

public class TelefoneDAO {

	private Connection connection;

	public TelefoneDAO() {

		connection = SingleConnection.getConnection();

	}

	public void salvar(Telefone telefone) {

		try {

			String sql = "INSERT INTO telefones(id_usuario, numero, tipo) values (?, ?, ?)";
			PreparedStatement insert = connection.prepareStatement(sql);

			insert.setInt(1, telefone.getId_usuario());
			insert.setString(2, telefone.getNumero());
			insert.setString(3, telefone.getTipo());

			insert.execute();
			connection.commit();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

			try {
				connection.rollback();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}

	}

	public ArrayList<Telefone> listar(int id_usuario) throws Exception {

		ArrayList<Telefone> listar = new ArrayList<Telefone>();
		String sql = "select * from telefones where id_usuario = " + id_usuario;

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {

			Telefone telefone = new Telefone();

			telefone.setId_telefone(resultSet.getInt("id_telefone"));
			telefone.setId_usuario(resultSet.getInt("id_usuario"));
			telefone.setNumero(resultSet.getString("numero"));
			telefone.setTipo(resultSet.getString("tipo"));

			listar.add(telefone);

		}

		return listar;

	}

	public void delete(String id_telefone) {

		String sql = "DELETE FROM telefones WHERE id_telefone = '" + id_telefone + "'";
		
		try {

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.execute();

			connection.commit();

		} catch (SQLException e) {

			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

}