package model;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;

public class CarrelloDao {

    static final Logger logger = Logger.getLogger("MyLogger");

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
	            updatePs = conn.prepareStatement(updateQuery);
	            updatePs.setInt(1, quantita);
	            updatePs.setInt(2, idVarianteArticolo);
	            updatePs.setInt(3, idUtente);
	            int rowsAffected = updatePs.executeUpdate();
	            if (rowsAffected == 0) {
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
	            	    	                conn.rollback();
	                return false;
	            }
	        }

	        conn.commit();
	        return true;

	    } catch (SQLException ex) {
            logger.log(null, "Eccezione non gestita: ");
	    	    	        try {
	    	    if(conn!=null)
	            conn.rollback();
	        } catch (SQLException ex2) {
                logger.log(null, "Eccezione non gestita: ");
	        	    	        }
	        return false;
	    } finally {
	        if (checkPs != null) {
	            try {
	                checkPs.close();
	            } catch (SQLException ex) {
                    logger.log(null, "Eccezione non gestita: ");
	            	    	            	   }
	        }
	        if (updatePs != null) {
	            try {
	                updatePs.close();
	            } catch (SQLException ex) {
                    logger.log(null, "Eccezione non gestita: ");
	            	    	            	   }
	        }
	        if (insertPs != null) {
	            try {
	                insertPs.close();
	            } catch (SQLException ex) {
                    logger.log(null, "Eccezione non gestita: ");
	            	    	            }
	        }
	        if (conn != null) {
	            try {
	                conn.setAutoCommit(true);
	                conn.close();
	            } catch (SQLException ex) {
                    logger.log(null, "Eccezione non gestita: ");
	            	    	            }
	        }
	    }
	}
	
	public static boolean addToCart(VariantiBean variante, int idUtente, int quantita) {
	    if (verificaEsistenza(variante, quantita)) {
	       return inserisciVarianteNelCarrello(variante, idUtente, quantita);
	    } else {
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
            logger.log(null, "Eccezione non gestita: ");
	    	    	        return false;
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
	}


	public static CarrelloBean caricaCarrello(int idUtente) {
	    String searchQuery = "SELECT * FROM carrello WHERE id_utente = ?";
	    Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    CarrelloBean carrello = new CarrelloBean();
	    VariantiBean varArt = new VariantiBean();
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
	            varArt = VarianteDAO.getVarianteById(idVarianteArticolo);
	            art = ArticoloDAO.idRicerca(varArt.getId_art());
	           

	            carrello.setIdUtente(rs.getInt("id_utente"));
	            carrello.addElemento(varArt,art,quantita,idRow);
	            
	        }
	        
	        conn.commit(); // conferma le modifiche

	    } catch (SQLException ex) {
            logger.log(null, "Eccezione non gestita: ");
	    	 
	    	   if (conn != null) {
	            try {
	                conn.rollback(); // annulla le modifiche in caso di errore
	            } catch (SQLException ex2) {
                    logger.log(null, "Eccezione non gestita: ");
	            	             }
	        }
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
	                conn.setAutoCommit(true); // riattiva l'autocommit
	                conn.close();
	            } catch (SQLException ex) {
                    logger.log(null, "Eccezione non gestita: ");
	            	  
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
	        	    	        	   conn.rollback(); // Effettua il rollback
	            return false;
	        } else {
	            conn.commit(); // Effettua il commit
	            return true;
	        }
	    } catch (SQLException ex) {
            logger.log(null, "Eccezione non gestita: ");
	    	    	    	   try {
	    	    		    	    if(conn!=null)

	            conn.rollback(); // Effettua il rollback in caso di eccezione
	        } catch (SQLException ex2) {
                logger.log(null, "Eccezione non gestita: ");
	        	    	        	   }
	        return false;
	    } finally {
	        if (deletePs != null) {
	            try {
	                deletePs.close();
	            } catch (SQLException ex) {
                    logger.log(null, "Eccezione non gestita: ");
	            	    	            }
	        }
	        if (conn != null) {
	            try {
	                conn.setAutoCommit(true); // Riabilita il commit automatico
	                conn.close();
	            } catch (SQLException ex) {
                    logger.log(null, "Eccezione non gestita: ");
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
        	                conn.rollback(); // Effettua il rollback
            return false;
        } else {
            conn.commit(); // Effettua il commit
            return true;
        }
    } catch (SQLException ex) {
        logger.log(null, "Eccezione non gestita: ");
    	            try {
    		    	    if(conn!=null)
            conn.rollback(); // Effettua il rollback in caso di eccezione
        } catch (SQLException ex2) {
            logger.log(null, "Eccezione non gestita: ");
        	            }
        return false;
    } finally {
        if (deletePs != null) {
            try {
                deletePs.close();
            } catch (SQLException ex) {
                logger.log(null, "Eccezione non gestita: ");
            	                }
        if (conn != null) {
            try {
                conn.setAutoCommit(true); // Riabilita il commit automatico
                conn.close();
            } catch (SQLException ex) {
                logger.log(null, "Eccezione non gestita: ");
            	                }
        }
    }

    }
}
}
