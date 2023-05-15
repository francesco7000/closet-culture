package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VarianteDAO {
	
	   

	public static VariantiBean getVariante(String idarticolo, String id_coloren, String id_taglia) {
	    var idcolore = Integer.parseInt(id_coloren);
	    var id_articolo = Integer.parseInt(idarticolo);
	    var idtaglia = Integer.parseInt(id_taglia);
		System.out.println("****"+idcolore+"***"+id_articolo+"**"+idtaglia);


	    VariantiBean vb = new VariantiBean();

	    String searchQuery =
	            "SELECT v.id, v.quantita, v.descrizione " +
	            "FROM variante_articolo v " +
	            "WHERE v.id_colore= ? AND v.id_articolo= ? AND v.id_taglia= ? ";

	    PreparedStatement preparedStatement = null;
	    ResultSet rs = null;
	    Connection currentCon = null;

	    try {
	        currentCon = DriverManagerConnectionPool.getConnection();
	        preparedStatement = currentCon.prepareStatement(searchQuery);

	        preparedStatement.setInt(1, idcolore);
	        preparedStatement.setInt(2, id_articolo);
	        preparedStatement.setInt(3, idtaglia);

	        rs = preparedStatement.executeQuery();

	        while (rs.next()) {
	            int id_v = rs.getInt("id");
	            int quantita = rs.getInt("quantita");
	            String descrizione = rs.getString("descrizione");
	            System.out.println("ID VARIANTE TROVATA"+id_v+"CIAO");
	            vb.setId(id_v);
	            vb.setQuantita(quantita);
	            vb.setDescrizione(descrizione);

	        }
	        
	    } catch (SQLException ex) {
	        System.out.println("Errore durante la ricerca delle varianti: " + ex);
	    } finally {
	        if (rs != null) {
	            try {
	                rs.close();
	            } catch (SQLException e) {
	                // log or handle the exception
	            }
	        }
	        if (preparedStatement != null) {
	            try {
	                preparedStatement.close();
	            } catch (SQLException e) {
	                // log or handle the exception
	            }
	        }
	        if (currentCon != null) {
	            try {
	                currentCon.close();
	            } catch (SQLException e) {
	                // log or handle the exception
	            }
	        }
	    }

	    return vb;
	}
}
