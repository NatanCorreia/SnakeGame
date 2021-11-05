package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Player;

public class PlayerDao implements FichavelDao<Player> {

	@Override
	public PreparedStatement getInsertStatement(Connection c, Player item) throws SQLException {
		String sql = "INSERT INTO player(nome,login,email,password) values (?,?,?,?)";
		PreparedStatement st = c.prepareStatement(sql);
		st.setString(1, item.getName());
		st.setString(2, item.getLogin());
		st.setString(3, item.getEmail());
		st.setString(4, item.getPassword());
		
		
		return st;
	}

	@Override
	public PreparedStatement getUpdateStatement(Connection c, String idUnico, Player item) throws SQLException {
		String sql = "UPDATE player set nome = ?, password = ?, where id = ?";
		PreparedStatement st = c.prepareStatement(sql);
		st.setString(1, item.getName());
		st.setString(2, item.getPassword());
                st.setInt(3,Integer.parseInt(idUnico));
		
		return st;
	}

	@Override
	public ArrayList<Player> selecionaTudo() throws SQLException {
		ArrayList<Player> Players = new ArrayList<Player>();
		Connection db = Conexao.getConexao();
		PreparedStatement st = db.prepareStatement("SELECT * FROM " + getTableName() );
		ResultSet queryResult = st.executeQuery();
		
		while(queryResult.next()) {
			Player p = new Player();
			p.setName(queryResult.getString("nome"));
			p.setLogin(queryResult.getString("login"));
			p.setEmail(queryResult.getString("email"));
			p.setPassword(queryResult.getString("password"));
			
			Players.add(p);
		}
		st.close();
		return Players;
	}

	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return "player";
	}

	@Override
	public String getIdUnicoColumnName() {
		// TODO Auto-generated method stub
		return "id";
	}
	

	

	

	
}
