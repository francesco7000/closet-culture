package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;

public class VoceOrdineDao {

	   static final Logger logger = Logger.getLogger("MyLogger");

	public static boolean newVoceOrdine(int quantita, double prezzo, int id_ordine, int id_variante_articolo) {
	    PreparedStatement preparedStatement = null;
	    String insertQuery = "INSERT INTO voce_ordine (quantita, prezzo, id_ordine, id_variante_articolo) VALUES (?, ?, ?, ?)";
	    int result = 0;
	    Connection currentCon = null;

	    try {
	        currentCon = DriverManagerConnectionPool.getConnection();
	        currentCon.setAutoCommit(false); // disattivo l'autocommit

	        preparedStatement = currentCon.prepareStatement(insertQuery);
	        preparedStatement.setInt(1, quantita);
	        preparedStatement.setDouble(2, prezzo);
	        preparedStatement.setInt(3, id_ordine);
	        preparedStatement.setInt(4, id_variante_articolo);
	        result = preparedStatement.executeUpdate();

	        currentCon.commit(); // eseguo il commit esplicitamente

	    } catch (SQLException e) {
	    	logger.log(null, "Eccezione non gestita: ");
	        try {
	            if (currentCon != null) {
	            	logger.log(null, "Eccezione non gestita: ");
	                currentCon.rollback(); // eseguo il rollback esplicitamente in caso di errore
	            }
	        } catch (SQLException ex) {
	        	logger.log(null, "Eccezione non gestita: ");
	        }
	        return false;
	    } finally {
	        if (preparedStatement != null) {
	            try {
	                preparedStatement.close();
	            } catch (SQLException e) {
	            	logger.log(null, "Eccezione non gestita: ");
	            }
	        }
	        if (currentCon != null) {
	            try {
	                currentCon.setAutoCommit(true); // riattivo l'autocommit
	                DriverManagerConnectionPool.releaseConnection(currentCon);
	            } catch (SQLException e) {
	            	logger.log(null, "Eccezione non gestita: ");
	            }
	        }
	    }

	    return result > 0;
	}
	
	public static ArrayList<VoceOrdineBean> getVociOrdineByIdOrdine(int idOrdine) throws SQLException {
	    PreparedStatement preparedStatement = null;
	    ResultSet rs = null;
	    String searchQuery = "SELECT * FROM voce_ordine WHERE id_ordine = ?";
	    ArrayList<VoceOrdineBean> vociOrdine = new ArrayList<>();
	    Connection currentCon = null;

	    try {
	        currentCon = DriverManagerConnectionPool.getConnection();
	        preparedStatement = currentCon.prepareStatement(searchQuery);
	        preparedStatement.setInt(1, idOrdine);
	        rs = preparedStatement.executeQuery();

	        while (rs.next()) {
	            VoceOrdineBean voceOrdine = new VoceOrdineBean();
	            voceOrdine.setId(rs.getInt("id"));
	            voceOrdine.setQuantita(rs.getInt("quantita"));
	            voceOrdine.setPrezzo(rs.getDouble("prezzo"));
	            voceOrdine.setIdOrdine(rs.getInt("id_ordine"));
	            voceOrdine.setIdVarianteArticolo(rs.getInt("id_variante_articolo"));
	            vociOrdine.add(voceOrdine);
	        }

	    } catch (SQLException e) {
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
	        DriverManagerConnectionPool.releaseConnection(currentCon);
	    }

	    return vociOrdine;
	}

}
