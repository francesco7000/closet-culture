package model;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;

public class VarianteDAO {
	
	 static final Logger logger = Logger.getLogger("MyLogger");
	public static VariantiBean getVariante(String idarticolo, String id_coloren, String id_taglia) {

	    var idcolore = Integer.parseInt(id_coloren);
	    var id_articolo = Integer.parseInt(idarticolo);
	    var idtaglia = Integer.parseInt(id_taglia);


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
	    
	            vb.setId(id_v);
	            vb.setQuantita(quantita);
	            vb.setDescrizione(descrizione);

	        }
	        
	    } catch (SQLException ex) {
	        logger.log(null, "Eccezione non gestita: ");

	    } finally {
	        if (rs != null) {
	            try {
	                rs.close();
	            } catch (SQLException e) {
	               logger.log(null, "Eccezione non gestita: ");

	            }
	        }
	        if (preparedStatement != null) {
	            try {
	                preparedStatement.close();
	            } catch (SQLException e) {
	                logger.log(null, "Eccezione non gestita: ");

	            }
	        }
	        if (currentCon != null) {
	            try {
	                currentCon.close();
	            } catch (SQLException e) {
	               logger.log(null, "Eccezione non gestita: ");

	            }
	        }
	    }

	    return vb;
	}
	
	
	public static VariantiBean getVarianteById(int idVariante) {
	    String searchQuery = "SELECT * FROM variante_articolo WHERE id = ?";
	    Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    VariantiBean variante = new VariantiBean();

	    try {
	        conn = DriverManagerConnectionPool.getConnection();
	        ps = conn.prepareStatement(searchQuery);
	        ps.setInt(1, idVariante);
	        rs = ps.executeQuery();

	        if (rs.next()) {
	            String descrizione = rs.getString("descrizione");
	            String codice = rs.getString("codice");
	            String barcode = rs.getString("barcode");
	            int quantita = rs.getInt("quantita");
	            int idColore = rs.getInt("id_colore");
	            int idTaglia = rs.getInt("id_taglia");
	            int id_art = rs.getInt("id_articolo");
	    
	            
	            ColoreBean colore = ColoreDao.getColoreById(idColore);
	            TagliaBean taglia = TagliaDao.getTagliaById(idTaglia);
	            
	            variante.setId(idVariante);
	            variante.setDescrizione(descrizione);
	            variante.setCodice(codice);
	            variante.setBarcode(barcode);
	            variante.setQuantita(quantita);
	            variante.setColore(colore);
	            variante.setTaglia(taglia);
	            variante.setId_art(id_art);
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
	              logger.log(null, "Eccezione non gestita: ");

	            }
	        }
	    }

	    return variante;
	}

	
}
