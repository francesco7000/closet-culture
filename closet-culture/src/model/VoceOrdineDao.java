package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class VoceOrdineDao {

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
	        // Gestione dell'errore
	        //e.printStackTrace();
	        try {
	            if (currentCon != null) {
	                System.out.println("rollback");
	                currentCon.rollback(); // eseguo il rollback esplicitamente in caso di errore
	            }
	        } catch (SQLException ex) {
	            //ex.printStackTrace();
	        }
	        return false;
	    } finally {
	        if (preparedStatement != null) {
	            try {
	                preparedStatement.close();
	            } catch (SQLException e) {
	                // Gestione dell'errore
	                //e.printStackTrace();
	            }
	        }
	        if (currentCon != null) {
	            try {
	                currentCon.setAutoCommit(true); // riattivo l'autocommit
	                DriverManagerConnectionPool.releaseConnection(currentCon);
	            } catch (SQLException e) {
	                // Gestione dell'errore
	                //e.printStackTrace();
	            }
	        }
	    }

	    return result > 0;
	}

}
