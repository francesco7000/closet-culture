package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;


public class ColoreDao {
	   static final Logger logger = Logger.getLogger("MyLogger");

	public static ColoreBean getColoreById(int idColore) {
	    String searchQuery = "SELECT * FROM colore WHERE id = ?";
	    Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    ColoreBean colore = new ColoreBean();

	    try {
	        conn = DriverManagerConnectionPool.getConnection();
	        ps = conn.prepareStatement(searchQuery);
	        ps.setInt(1, idColore);
	        rs = ps.executeQuery();

	        if (rs.next()) {
	            String nome = rs.getString("nome");
	            int id = rs.getInt("id");
	            
	            colore.setId(id);
	            colore.setNome(nome);
	            
	        }

	    } catch (SQLException ex) {
	    	logger.log(null, "Eccezione non gestita: ");
	    } finally {
	        if (rs != null) {
	            try {
	                rs.close();
	            } catch (SQLException ex) {
	            	logger.log(null, "Eccezione non gestita: ");	            }
	        }
	        if (ps != null) {
	            try {
	                ps.close();
	            } catch (SQLException ex) {
	            	logger.log(null, "Eccezione non gestita: ");	            }
	        }
	        if (conn != null) {
	            try {
	                conn.close();
	            } catch (SQLException ex) {
	            	logger.log(null, "Eccezione non gestita: ");	            }
	        }
	    }

	    return colore;
	}

}
