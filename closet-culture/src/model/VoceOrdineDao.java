package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

}
