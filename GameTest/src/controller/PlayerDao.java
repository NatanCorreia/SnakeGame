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
		String sql = "INSERT INTO player(nome,login,email,password) values (?,?,?,?) RETURNING id";
		PreparedStatement st = c.prepareStatement(sql);
		st.setString(1, item.getName());
		st.setString(2, item.getLogin());
		st.setString(3, item.getEmail());
		st.setString(4, item.getPassword());
		ResultSet rS = st.executeQuery();
		rS.next();
		item.setId(rS.getInt(1));

		return st;
	}

	@Override
	public PreparedStatement getUpdateStatement(Connection c, String idUnico, Player item) throws SQLException {
		String sql = "UPDATE player set nome = ?, password = ?, where id = ?";
		PreparedStatement st = c.prepareStatement(sql);
		st.setString(1, item.getName());
		st.setString(2, item.getPassword());
		st.setInt(3, Integer.parseInt(idUnico));

		return st;
	}

	@Override
	public PreparedStatement updateScore(Connection c, int idUnico, int score) throws SQLException {
		String sql = "UPDATE player set score = ? where id = ?";
		PreparedStatement st = c.prepareStatement(sql);
		st.setInt(1, score);
		st.setInt(2, idUnico);
		return st;
	}

	@Override
	public ArrayList<Player> selecionaTudo() throws SQLException {
		ArrayList<Player> Players = new ArrayList<Player>();
		Connection db = Conexao.getConexao();
		PreparedStatement st = db.prepareStatement("SELECT * FROM " + getTableName());
		ResultSet queryResult = st.executeQuery();

		while (queryResult.next()) {
			Player p = new Player();
			p.setName(queryResult.getString("nome"));
			p.setLogin(queryResult.getString("login"));
			p.setEmail(queryResult.getString("email"));
			p.setPassword(queryResult.getString("password"));
			p.setScore(queryResult.getInt("score"));

			Players.add(p);
		}
		st.close();
		return Players;
	}

	@Override
	public Player buscarItem(int idUnico) throws SQLException {
		Player p = new Player();
		Connection db = Conexao.getConexao();
		PreparedStatement st = db.prepareStatement("SELECT * FROM " + getTableName() + " WHERE id = ?");
		st.setInt(1, idUnico);
		ResultSet queryResult = st.executeQuery();
		queryResult.next();
		p.setName(queryResult.getString("nome"));
		p.setLogin(queryResult.getString("login"));
		p.setEmail(queryResult.getString("email"));
		p.setPassword(queryResult.getString("password"));
		p.setId(idUnico);

		return p;
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
