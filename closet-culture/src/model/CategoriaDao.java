package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



public class CategoriaDao {
	
	public synchronized ArrayList<CategoriaBean> doRetrieveAll(){
		ArrayList<CategoriaBean> categorie = new ArrayList<>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		CategoriaBean tempCategoria = new CategoriaBean() ;
		
		String sqlSelect = "select id,descrizione from categoria_articolo ";
		try {
			connection = (Connection) DriverManagerConnectionPool.getConnection();
			preparedStatement = (PreparedStatement) connection.prepareStatement(sqlSelect);

			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				tempCategoria=new CategoriaBean();
				tempCategoria.setId(rs.getInt("id"));
				tempCategoria.setDescrizione(rs.getString("descrizione"));
				categorie.add(tempCategoria);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				preparedStatement.close();
				DriverManagerConnectionPool.releaseConnection(connection);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return categorie;
	}
 
}
