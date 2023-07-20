package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;



public class CategoriaDao {
    static final Logger logger = Logger.getLogger("MyLogger");

	public synchronized ArrayList<CategoriaBean> doRetrieveAll(){
		ArrayList<CategoriaBean> categorie = new ArrayList<>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		CategoriaBean tempCategoria = new CategoriaBean() ;
		
		String sqlSelect = "select id,descrizione from categoria_articolo ";
		try {
			connection =  DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(sqlSelect);

			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				tempCategoria=new CategoriaBean();
				tempCategoria.setId(rs.getInt("id"));
				tempCategoria.setDescrizione(rs.getString("descrizione"));
				categorie.add(tempCategoria);
				
			}
		} catch (SQLException e) {
			logger.log(null, "Eccezione non gestita: ");
		}finally{
			try {
				if(preparedStatement!=null) {
				preparedStatement.close();
				DriverManagerConnectionPool.releaseConnection(connection);
				}
			} catch (SQLException e) {
				logger.log(null, "Eccezione non gestita: ");
			}
		}
		
		return categorie;
	}
	
	
	public static CategoriaBean getCategoriaArticolo(int id) {
	    PreparedStatement preparedStatement = null;
	    CategoriaBean bean_c = null;

	    String searchQuery = "SELECT c.id, c.descrizione FROM categoria_articolo c JOIN articolo a ON c.id = a.id_categoria_articolo WHERE a.id = ?";

	    try {
	        Connection currentCon = DriverManagerConnectionPool.getConnection();
	        preparedStatement = currentCon.prepareStatement(searchQuery);
	        preparedStatement.setInt(1, id);

	        ResultSet rs = preparedStatement.executeQuery();

	        if (rs.next()) {
	            bean_c = new CategoriaBean();
	            Integer id_c = rs.getInt("id");
	            String descrizione = rs.getString("descrizione");

	            bean_c.setId(id_c);
	            bean_c.setDescrizione(descrizione);
	        }
	    } catch (SQLException e) {
	    	logger.log(null, "Eccezione non gestita: ");
	    } finally {
	        try {
	            if (preparedStatement != null) {
	                preparedStatement.close();
	            }
	        } catch (SQLException e) {
	        	logger.log(null, "Eccezione non gestita: ");
	        }
	    }

	    return bean_c;
	}
	
 
}
