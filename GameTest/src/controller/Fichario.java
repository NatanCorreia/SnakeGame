package controller;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Fichavel;


public class Fichario<T extends Fichavel> {
	private FichavelDao<T> catalogoDao;
	private Connection conn;
	

	public Fichario(FichavelDao<T> catalogoDao) throws SQLException {
		this.catalogoDao = catalogoDao;
		conn = Conexao.getConexao();
	}

	public boolean cadastrar(T novo) {
		try {
			PreparedStatement stmt = catalogoDao.getInsertStatement(conn, novo);
			stmt.execute();
			stmt.close();
			return true;
		}catch(SQLException e) {
			System.out.println("Existe no banco de dados um item cadastrado com a mesma chave.");
			e.printStackTrace();
			return false;}
		
	}

	

	public boolean alterar(String idUnico, T novo)  {
		
		try {
			PreparedStatement stmt = catalogoDao.getUpdateStatement(conn, idUnico, novo);
			boolean result = stmt.execute();
			stmt.close();
			return result;
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
       

	public boolean deletar(String idUnico) {
		ResultSet rs;
		String t =null;
		try {
			PreparedStatement st = conn.prepareStatement("DELETE FROM " + catalogoDao.getTableName() + " WHERE " + catalogoDao.getIdUnicoColumnName() + " = ? RETURNING " + catalogoDao.getIdUnicoColumnName());
			st.setString(1, idUnico);
			rs=st.executeQuery();
			while(rs.next()) {
				t =rs.getString(catalogoDao.getIdUnicoColumnName());
			}
			if(t!=null) {
			st.close();
			return true;
			}
			else {st.close();
				return false;}
		}catch(SQLException e) {
			System.out.println(catalogoDao.getTableName());
			e.printStackTrace();
			return false;
		}
	}
public boolean deletar(int idUnico) {
		ResultSet rs;
		String t =null;
		try {
			PreparedStatement st = conn.prepareStatement("DELETE FROM " + catalogoDao.getTableName() + " WHERE " + catalogoDao.getIdUnicoColumnName() + " = ? RETURNING " + catalogoDao.getIdUnicoColumnName());
			st.setInt(1, idUnico);
			rs=st.executeQuery();
			while(rs.next()) {
				t =rs.getString(catalogoDao.getIdUnicoColumnName());
			}
			if(t!=null) {
			st.close();
			return true;
			}
			else {st.close();
				return false;}
		}catch(SQLException e) {
			System.out.println(catalogoDao.getTableName());
			e.printStackTrace();
			return false;
		}
	}

	
	public ArrayList<T> buscarTudo() {
	
		ArrayList<T> catalogo =null;
				try {
					 catalogo = catalogoDao.selecionaTudo();
					
					
					
					}
				catch(SQLException e){
                                    
					e.printStackTrace();
				}
			
		
		return catalogo;
	}

	

}
