package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Fichavel;


public interface FichavelDao<T extends Fichavel> {
	public PreparedStatement getInsertStatement(Connection c, T item) throws SQLException;
	public PreparedStatement getUpdateStatement(Connection c, String idUnico, T item) throws SQLException;
	public PreparedStatement updateScore(Connection c, int idUnico, int score) throws SQLException;
	public ArrayList<T> selecionaTudo() throws SQLException;
	public T buscarItem(int idUnico) throws SQLException;
	public String getTableName();
	public String getIdUnicoColumnName();
}
