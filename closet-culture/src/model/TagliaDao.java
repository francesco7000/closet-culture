package model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class TagliaDao {
	   static final Logger logger = Logger.getLogger("MyLogger");

	public static TagliaBean getTagliaById(int idTaglia) {
	    String searchQuery = "SELECT * FROM taglia WHERE id = ?";
	    Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    TagliaBean taglia = new TagliaBean();

	    try {
	        conn = DriverManagerConnectionPool.getConnection();
	        ps = conn.prepareStatement(searchQuery);
	        ps.setInt(1, idTaglia);
	        rs = ps.executeQuery();

	        if (rs.next()) {
	            String nome = rs.getString("nome");
	            int id = rs.getInt("id");
	            
	            taglia.setId(id);
	            taglia.setNome(nome);
	            
	        }

	    } catch (SQLException ex) {
	    	logger.log(null, "Eccezione non gestita: ");
	    } finally {
	        if (rs != null) {
	            try {
	                rs.close();
	            } catch (SQLException ex) {
	            	logger.log(null, "Eccezione non gestita: ");
	            }
	        }
	        if (ps != null) {
	            try {
	                ps.close();
	            } catch (SQLException ex) {
	            	logger.log(null, "Eccezione non gestita: ");
	            }
	        }
	        if (conn != null) {
	            try {
	                conn.close();
	            } catch (SQLException ex) {
	            	logger.log(null, "Eccezione non gestita: ");	            }
	        }
	    }

	    return taglia;
	}
}
