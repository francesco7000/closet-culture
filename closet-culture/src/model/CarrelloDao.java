package model;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CarrelloDao {

	private static boolean inserisciVarianteNelCarrello(VariantiBean variante, int idUtente, int quantita) {


	    var idVarianteArticolo=variante.getId();
	    String insertQuery = "INSERT INTO carrello (id_variante_articolo, id_utente, quantita) VALUES (?, ?, ?)";
	    Connection conn = null;
	    PreparedStatement ps = null;
	    
	    try {
	        conn = DriverManagerConnectionPool.getConnection();
	        
	        // Disattiva l'autocommit
	        conn.setAutoCommit(false);
	        
	        ps = conn.prepareStatement(insertQuery);

	        ps.setInt(1, idVarianteArticolo);
	        ps.setInt(2, idUtente);
	        ps.setInt(3, quantita);

	        int rowsAffected = ps.executeUpdate();

	        if (rowsAffected == 0) {
	            System.out.println("Errore durante l'inserimento della variante nel carrello.");
	            return false;
	        }
	        
	        // Esegui il commit manualmente
	        conn.commit();
	        
	        return true;

	    } catch (SQLException ex) {
	        System.out.println("Errore durante l'inserimento della variante nel carrello: " + ex.getMessage());
	        
	        try {
	            // Rollback in caso di eccezione
	            conn.rollback();
	        } catch (SQLException ex2) {
	            System.out.println("Errore durante il rollback: " + ex2.getMessage());
	        }
	        
	        return false;
	    } finally {
	        if (ps != null) {
	            try {
	                ps.close();
	            } catch (SQLException ex) {
	                System.out.println("Errore durante la chiusura dello statement: " + ex.getMessage());
	            }
	        }
	        if (conn != null) {
	            try {
	                // Riattiva l'autocommit prima di chiudere la connessione
	                conn.setAutoCommit(true);
	                conn.close();
	            } catch (SQLException ex) {
	                System.out.println("Errore durante la chiusura della connessione: " + ex.getMessage());
	            }
	        }
	    }
	}
	
	public static boolean addToCart(VariantiBean variante, int idUtente, int quantita) {
	    if (verificaEsistenza(variante, quantita)) {
	       return inserisciVarianteNelCarrello(variante, idUtente, quantita);
	    } else {
	        System.out.println("Impossibile aggiungere la variante al carrello.");
	        return false;
	    }
	}
	
	
	public static boolean verificaEsistenza(VariantiBean variante, int quantitaDaAggiungere) {

	    String searchQuery = "SELECT quantita FROM variante_articolo WHERE id = ?";
	    Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;

	    try {
	        conn = DriverManagerConnectionPool.getConnection();
	        ps = conn.prepareStatement(searchQuery);
	        ps.setInt(1, variante.getId());
	        rs = ps.executeQuery();

	        if (rs.next()) {
	        	
	            int quantitaDisponibile = rs.getInt("quantita");
	            return quantitaDisponibile >= quantitaDaAggiungere;
	        } else {
	            return false;
	        }

	    } catch (SQLException ex) {
	        System.out.println("Errore durante la verifica della quantità disponibile: " + ex.getMessage());
	        return false;
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
	                System.out.println("Errore durante la chiusura dello Statement: " + ex.getMessage());
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
	}


	
	
	public static CarrelloBean caricaCarrello(int idUtente) {
	    String searchQuery = "SELECT * FROM carrello WHERE id_utente = ?";
	    Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    CarrelloBean carrello = new CarrelloBean();
	    VariantiBean var = new VariantiBean();
	    ArticoloBean art = new ArticoloBean();

	    try {
	        conn = DriverManagerConnectionPool.getConnection();
	        conn.setAutoCommit(false); // disattiva l'autocommit
	        ps = conn.prepareStatement(searchQuery);
	        ps.setInt(1, idUtente);
	        rs = ps.executeQuery();
	        
	        

	        while (rs.next()) {
	            int idVarianteArticolo = rs.getInt("id_variante_articolo");
	            int quantita = rs.getInt("quantita");
	            var = VarianteDAO.getVarianteById(idVarianteArticolo);
	            art = ArticoloDAO.idRicerca(var.getId_art());
	           

	            carrello.setIdUtente(rs.getInt("id_utente"));
	            carrello.addElemento(var,art, quantita);
	            
	        }
	        
	        conn.commit(); // conferma le modifiche

	    } catch (SQLException ex) {
	        System.out.println("Errore durante la ricerca degli elementi nel carrello: " + ex.getMessage());
	        if (conn != null) {
	            try {
	                conn.rollback(); // annulla le modifiche in caso di errore
	            } catch (SQLException ex2) {
	                System.out.println("Errore durante l'annullamento delle modifiche: " + ex2.getMessage());
	            }
	        }
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
	                conn.setAutoCommit(true); // riattiva l'autocommit
	                conn.close();
	            } catch (SQLException ex) {
	                System.out.println("Errore durante la chiusura della connessione: " + ex.getMessage());
	            }
	        }
	    }

	    return carrello;
	}
}
