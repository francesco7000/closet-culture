package model;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CarrelloDao {

	private static boolean inserisciVarianteNelCarrello(VariantiBean variante, int idUtente, int quantita) {
	    var idVarianteArticolo = variante.getId();
	    String checkQuery = "SELECT quantita FROM carrello WHERE id_variante_articolo = ? AND id_utente = ?";
	    String updateQuery = "UPDATE carrello SET quantita = quantita + ? WHERE id_variante_articolo = ? AND id_utente = ?";
	    String insertQuery = "INSERT INTO carrello (id_variante_articolo, id_utente, quantita) VALUES (?, ?, ?)";
	    Connection conn = null;
	    PreparedStatement checkPs = null;
	    PreparedStatement updatePs = null;
	    PreparedStatement insertPs = null;

	    try {
	        conn = DriverManagerConnectionPool.getConnection();
	        conn.setAutoCommit(false);

	        // Verifica se la variante è già presente nel carrello
	        checkPs = conn.prepareStatement(checkQuery);
	        checkPs.setInt(1, idVarianteArticolo);
	        checkPs.setInt(2, idUtente);
	        ResultSet rs = checkPs.executeQuery();
	        if (rs.next()) {
	            // La variante è già presente, esegui un'operazione di aggiornamento
	            int quantitaPrecedente = rs.getInt("quantita");
	            updatePs = conn.prepareStatement(updateQuery);
	            updatePs.setInt(1, quantita);
	            updatePs.setInt(2, idVarianteArticolo);
	            updatePs.setInt(3, idUtente);
	            int rowsAffected = updatePs.executeUpdate();
	            if (rowsAffected == 0) {
	                System.out.println("Errore durante l'aggiornamento della quantità della variante nel carrello.");
	                conn.rollback();
	                return false;
	            }
	        } else {
	            // La variante non è presente, esegui un'operazione di inserimento
	            insertPs = conn.prepareStatement(insertQuery);
	            insertPs.setInt(1, idVarianteArticolo);
	            insertPs.setInt(2, idUtente);
	            insertPs.setInt(3, quantita);
	            int rowsAffected = insertPs.executeUpdate();
	            if (rowsAffected == 0) {
	                System.out.println("Errore durante l'inserimento della variante nel carrello.");
	                conn.rollback();
	                return false;
	            }
	        }

	        conn.commit();
	        return true;

	    } catch (SQLException ex) {
	        System.out.println("Errore durante l'inserimento della variante nel carrello: " + ex.getMessage());
	        try {
	            conn.rollback();
	        } catch (SQLException ex2) {
	            System.out.println("Errore durante il rollback: " + ex2.getMessage());
	        }
	        return false;
	    } finally {
	        if (checkPs != null) {
	            try {
	                checkPs.close();
	            } catch (SQLException ex) {
	                System.out.println("Errore durante la chiusura dello statement di verifica: " + ex.getMessage());
	            }
	        }
	        if (updatePs != null) {
	            try {
	                updatePs.close();
	            } catch (SQLException ex) {
	                System.out.println("Errore durante la chiusura dello statement di aggiornamento: " + ex.getMessage());
	            }
	        }
	        if (insertPs != null) {
	            try {
	                insertPs.close();
	            } catch (SQLException ex) {
	                System.out.println("Errore durante la chiusura dello statement di inserimento: " + ex.getMessage());
	            }
	        }
	        if (conn != null) {
	            try {
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
	        	int idRow = rs.getInt("id");
	            int idVarianteArticolo = rs.getInt("id_variante_articolo");
	            int quantita = rs.getInt("quantita");
	            var = VarianteDAO.getVarianteById(idVarianteArticolo);
	            art = ArticoloDAO.idRicerca(var.getId_art());
	           

	            carrello.setIdUtente(rs.getInt("id_utente"));
	            carrello.addElemento(var,art,quantita,idRow);
	            
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

	public static boolean rimuoviVarianteDalCarrello(int idRow, int idUtente) {
	    String deleteQuery = "DELETE FROM carrello WHERE id = ? AND id_utente = ?";
	    Connection conn = null;
	    PreparedStatement deletePs = null;

	    try {
	        conn = DriverManagerConnectionPool.getConnection();
	        conn.setAutoCommit(false); // Disabilita il commit automatico
	        deletePs = conn.prepareStatement(deleteQuery);
	        deletePs.setInt(1, idRow);
	        deletePs.setInt(2, idUtente);
	        int rowsAffected = deletePs.executeUpdate();
	        if (rowsAffected == 0) {
	            System.out.println("Errore durante la rimozione della variante dal carrello.");
	            conn.rollback(); // Effettua il rollback
	            return false;
	        } else {
	            conn.commit(); // Effettua il commit
	            return true;
	        }
	    } catch (SQLException ex) {
	        System.out.println("Errore durante la rimozione della variante dal carrello: " + ex.getMessage());
	        try {
	            conn.rollback(); // Effettua il rollback in caso di eccezione
	        } catch (SQLException ex2) {
	            System.out.println("Errore durante il rollback: " + ex2.getMessage());
	        }
	        return false;
	    } finally {
	        if (deletePs != null) {
	            try {
	                deletePs.close();
	            } catch (SQLException ex) {
	                System.out.println("Errore durante la chiusura dello statement di rimozione: " + ex.getMessage());
	            }
	        }
	        if (conn != null) {
	            try {
	                conn.setAutoCommit(true); // Riabilita il commit automatico
	                conn.close();
	            } catch (SQLException ex) {
	                System.out.println("Errore durante la chiusura della connessione: " + ex.getMessage());
	            }
	        }
	    }
	}
	
	

public static boolean svuotaCarrello(int idUtente) {

    String deleteQuery = "DELETE FROM carrello WHERE id_utente = ?";
    Connection conn = null;
    PreparedStatement deletePs = null;

    try {
        conn = DriverManagerConnectionPool.getConnection();
        conn.setAutoCommit(false); // Disabilita il commit automatico
        deletePs = conn.prepareStatement(deleteQuery);
        deletePs.setInt(1, idUtente);
        int rowsAffected = deletePs.executeUpdate();
        if (rowsAffected == 0) {
            System.out.println("Errore durante la rimozione degli elementi dal carrello.");
            conn.rollback(); // Effettua il rollback
            return false;
        } else {
            conn.commit(); // Effettua il commit
            return true;
        }
    } catch (SQLException ex) {
        System.out.println("Errore durante la rimozione degli elementi dal carrello: " + ex.getMessage());
        try {
            conn.rollback(); // Effettua il rollback in caso di eccezione
        } catch (SQLException ex2) {
            System.out.println("Errore durante il rollback: " + ex2.getMessage());
        }
        return false;
    } finally {
        if (deletePs != null) {
            try {
                deletePs.close();
            } catch (SQLException ex) {
                System.out.println("Errore durante la chiusura dello statement di rimozione: " + ex.getMessage());
            }
        }
        if (conn != null) {
            try {
                conn.setAutoCommit(true); // Riabilita il commit automatico
                conn.close();
            } catch (SQLException ex) {
                System.out.println("Errore durante la chiusura della connessione: " + ex.getMessage());
            }
        }
    }
}
}
