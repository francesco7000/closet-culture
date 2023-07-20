package model;

import java.sql.Connection;

import java.text.*;
import java.util.*;
import java.util.logging.Logger;
import java.sql.*;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.xdevapi.Statement;

public class OrdineDao {
	   static final Logger logger = Logger.getLogger("MyLogger");

	public static int newOrdine(String data, double imposta, double imponibile, double totale, int idPagamento, int idUtente) {
	    PreparedStatement preparedStatement = null;

	    String insertQuery = "INSERT INTO ordine (data, imposta, imponibile, totale, id_pagamento, id_utente, numero_ordine) VALUES (?, ?, ?, ?, ?, ?, ?)";
	    int id = 0;
	    Connection currentCon = null;

	    try {
	        currentCon = DriverManagerConnectionPool.getConnection();
	        currentCon.setAutoCommit(false); // disattivo l'autocommit

	        preparedStatement = currentCon.prepareStatement(insertQuery,1);
	        preparedStatement.setString(1, data);
	        preparedStatement.setDouble(2, imposta);
	        preparedStatement.setDouble(3, imponibile);
	        preparedStatement.setDouble(4, totale);
	        preparedStatement.setInt(5, idPagamento);
	        preparedStatement.setInt(6, idUtente);
	        preparedStatement.setInt(7, (int) (System.currentTimeMillis() % Integer.MAX_VALUE));

	        preparedStatement.executeUpdate();

	        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
	        if (generatedKeys.next()) {
	            id = generatedKeys.getInt(1);
	        }

	        currentCon.commit(); 

	    } catch (SQLException e) {
	    	logger.log(null, "Eccezione non gestita: ");
	        try {
	            if (currentCon != null) {
	            	logger.log(null, "Eccezione non gestita: ");	                currentCon.rollback(); // eseguo il rollback esplicitamente in caso di errore
	            }
	        } catch (SQLException ex) {
	        	logger.log(null, "Eccezione non gestita: ");	        }
	        return 0;
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

	    return id;
	}
	
	public static OrdineBean getOrdineById(int idOrdine) throws SQLException {
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		String searchQuery = "SELECT * FROM ordine WHERE id = ?";
		OrdineBean ordine = null;
		Connection currentCon = null;

		try {
		    currentCon = DriverManagerConnectionPool.getConnection();
		    preparedStatement = currentCon.prepareStatement(searchQuery);
		    preparedStatement.setInt(1, idOrdine);
		    rs = preparedStatement.executeQuery();

		    if (rs.next()) {
		        ordine = new OrdineBean();
		        ordine.setId(rs.getInt("id"));
		        ordine.setData(rs.getString("data"));
		        ordine.setImposta(rs.getDouble("imposta"));
		        ordine.setImponibile(rs.getDouble("imponibile"));
		        ordine.setTotale(rs.getDouble("totale"));
		        ordine.setIdPagamento(rs.getInt("id_pagamento"));
		        ordine.setIdUtente(rs.getInt("id_utente"));
		        ordine.setNumOrdine(rs.getInt("numero_ordine"));
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

		return ordine;
		}
	
	
	
	
	
	
	
	
	public static String generaFattura(OrdineBean ordine) {
		
		
	    ArrayList<VoceOrdineBean> voci;
		String htmlContent="Fattura \n";

	    try {
	    	double somma=0;
	    	 UserBean user=UserDAO.utenteByID(ordine.getIdUtente());
			voci=VoceOrdineDao.getVociOrdineByIdOrdine(ordine.getId());
	    	htmlContent+="--------------------------------------------- \n";
	    	htmlContent+="Indirizzo di Spedizione:"+" "+user.getProvincia()+" "+user.getVia()+" "+user.getNumero()+" "+user.getCap()+" "+user.getCitta()+" "+"\n";
	    	htmlContent+="---------------------------------------------"+"\n";
		    PagamentoBean pagamento=PagamentoDao.getPagamentoById(ordine.getIdPagamento());
	    	for (VoceOrdineBean voceOrdine : voci) {
			    int idVarianteArticolo = voceOrdine.getIdVarianteArticolo();
			    VariantiBean variante=VarianteDAO.getVarianteById(idVarianteArticolo);
			    ArticoloBean articolo=ArticoloDAO.idRicerca(variante.getId_art());
			    int quantita = voceOrdine.getQuantita();
	    	    somma+=(voceOrdine.getPrezzo() * quantita);
	    	    htmlContent += " - Articolo: " + articolo.getNome() + " - Variante: " + variante.getDescrizione() + " - Quantità: " + quantita + " - Prezzo totale: " + (voceOrdine.getPrezzo() * quantita)+' '+'€' + "\n";			    
		       	
		       
			}
	    
	    	double totale=somma+((somma*22)/100);
	    	htmlContent+="Totale da Pagare: "+" "+totale+' '+'€'+"\n";
	 		htmlContent+="Metodo di pagamento utilizzato:"+" "+pagamento.getTp_descrizione()+"\n";
	 		
			
	
		} catch (SQLException e) {
			logger.log(null, "Eccezione non gestita: ");
		}

		return htmlContent;
	}
	
	
	
	
	
	
	
	public static ArrayList<OrdineBean> ricercaOrdini(String username) throws SQLException {
	    PreparedStatement preparedStatement = null;
	    ResultSet rs = null;
	    String searchQuery = "SELECT * FROM ordine JOIN utente ON ordine.id_utente = utente.id WHERE utente.username = ?";
	    ArrayList<OrdineBean> ordini = new ArrayList<OrdineBean>();
	    Connection currentCon = null;

	    try {
	        currentCon = DriverManagerConnectionPool.getConnection();
	        preparedStatement = currentCon.prepareStatement(searchQuery);
	        preparedStatement.setString(1, username);
	        rs = preparedStatement.executeQuery();

	        while (rs.next()) {
	            OrdineBean ordine = new OrdineBean();
	            ordine.setId(rs.getInt("id"));
	            ordine.setData(rs.getString("data"));
	            ordine.setImposta(rs.getDouble("imposta"));
	            ordine.setImponibile(rs.getDouble("imponibile"));
	            ordine.setTotale(rs.getDouble("totale"));
	            ordine.setIdPagamento(rs.getInt("id_pagamento"));
	            ordine.setIdUtente(rs.getInt("id_utente"));
	            ordine.setNumOrdine(rs.getInt("numero_ordine"));
	            ordini.add(ordine);
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

	    return ordini;
	}
	
	
	
}
