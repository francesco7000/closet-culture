package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ColoreDao {
	
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
	        System.out.println("Errore durante la ricerca del colore: " + ex.getMessage());
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

	    return colore;
	}

}
