package connection;

import java.sql.Connection;
import java.sql.DriverManager;

/*
 * Responsavel por fazer a conexão com o banco de dados
 * @author Pedro Luiz Aroma Filho
 */

public class SingleConnection {
	private static String banco = "jdbc:postgresql://localhost:5432/curso-jsp?autoReconnect";
	private static String user = "pedroaroma";
	private static String password = "ab2gqvd8";
	private static Connection connection = null;

	static {
		conectar();
	}

	public SingleConnection() {
		conectar();
	}

	private static void conectar() {

		try {

			if (connection == null) {
				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(banco, user, password);
				connection.setAutoCommit(false);

			} else {

			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Erro ao conectar com o banco de dados");
		}
	}

	public static Connection getConnection() {
		return connection;
	}

}