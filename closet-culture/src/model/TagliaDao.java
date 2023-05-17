package model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TagliaDao {

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
	        System.out.println("Errore durante la ricerca della taglia: " + ex.getMessage());
	    } finally {
	        if (rs != null) {
	            try {
	                rs.close();
	            } catch (SQLException ex) {
	                System.out.println("Errore durante la chiusura del ResultSet: " + ex.getMessage());
	            }
	        }
	        if (ps != null) {
	            try {
	                ps.close();
	            } catch (SQLException ex) {
	                System.out.println("Errore durante la chiusura dello statement: " + ex.getMessage());
	            }
	        }
	        if (conn != null) {
	            try {
	                conn.close();
	            } catch (SQLException ex) {
	                System.out.println("Errore durante la chiusura della connessione: " + ex.getMessage());
	            }
	        }
	    }

	    return taglia;
	}
}
