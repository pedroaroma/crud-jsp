package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import connection.SingleConnection;


public class LoginDAO {
	
	private Connection connection;
	
	public LoginDAO(){
		connection = SingleConnection.getConnection();
	}
	
	public boolean validarLogin(String login, String senha) throws Exception{
		
		String sql = "select * from usuario WHERE login = '"+login+"' and senha = '"+ senha + "'";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		
		if (resultSet.next()) {
			return true; //possui usuario
		} else {
			return false; // não validou usuario
		}
	}
	
	public int getUserId(String login, String senha) throws Exception{
		
		if(validarLogin(login, senha)){
			
			
			String sql = "SELECT id FROM usuario WHERE login = '" + login +"'";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			
			if(resultSet.next()){
				
				return resultSet.getInt("id");
			}else {
				return -1;
			}
		}else {
			return -1;
		}
	}

}