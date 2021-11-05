package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Conexao {
    private static Connection conn;
    
    public static Connection getConexao() throws SQLException{
      if((conn==null)|| (conn.isClosed()))
          conn = fabrica();
      return conn;
    }
    
	public static Connection fabrica() {
		String url;
		try {
				Class.forName("org.postgresql.Driver");
				Properties props = new Properties();
				props.put("user", "postgres");
				props.put("password", "151642");
				url = "jdbc:postgresql://localhost:5432/SnakeGame";
				return DriverManager.getConnection(url,props);
		}

		catch (Exception e) {
			System.err.println(" Erro: " + e.getMessage());
                        e.printStackTrace();
		}
		return null;
	}
}
