package model;

import java.text.*;
import java.util.*;
import java.sql.*;
import java.sql.Date;


public class PagamentoDao {

	
	
	   static Connection currentCon = null;
	   static ResultSet rs = null;  
	   
	  
public static int newPagamento(String nome, double importo, String data, int idTipoPagamento) {
    PreparedStatement preparedStatement = null;
    String insertQuery = "INSERT INTO pagamento (nome, importo, data, id_tipo_pagamento) VALUES (?, ?, ?, ?)";
    int result = 0;
    int idPagamento = 0;
    Connection currentCon = null;

    try {
        currentCon = DriverManagerConnectionPool.getConnection();
        currentCon.setAutoCommit(false); // disattivo l'autocommit

        preparedStatement = currentCon.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, nome);
        preparedStatement.setDouble(2, importo);
        preparedStatement.setString(3, data);
        preparedStatement.setInt(4, idTipoPagamento);
        result = preparedStatement.executeUpdate();

        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
        if (generatedKeys.next()) {
            idPagamento = generatedKeys.getInt(1);
        }

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
        return 0;
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

    return idPagamento;
}	
	   public static ArrayList<PagamentoBean> chekTipiPagamentoDisponibili() {
		   

		      PreparedStatement preparedStatement = null;
			
		      
		      String searchQuery = "Select * from tipo_pagamento";
		      
		      
		      ArrayList<PagamentoBean> tipiPag = new ArrayList<PagamentoBean>();
		
			    
		   try 
		   {
		      //connect to DB 
		      Connection currentCon = DriverManagerConnectionPool.getConnection();
		      preparedStatement=currentCon.prepareStatement(searchQuery);
		      rs = preparedStatement.executeQuery();	
		      
		      
		      while(rs.next()) {
		    	  PagamentoBean bean = new PagamentoBean();
		    	  int tp_id = rs.getInt("id");
		    	  String tp_nome = rs.getString("nome");
		    	  String tp_descrizione = rs.getString("descrizione");
		    	  bean.setId(tp_id);
		    	  bean.setTp_id(tp_id);
		    	  bean.setTp_nome(tp_nome);
		    	  bean.setTp_descrizione(tp_descrizione);
		    	  tipiPag.add(bean);
		      }
		       
		   } 

		   catch (Exception ex) 
		   {
		      System.out.println("Log In failed: An Exception has occurred! " + ex);
		   } 
			    
		   //some exception handling
		   finally 
		   {
		      if (rs != null)	{
		         try {
		            rs.close();
		         } catch (Exception e) {}
		            rs = null;
		         }
			
		      if (preparedStatement != null) {
		         try {
		        	 preparedStatement.close();
		         } catch (Exception e) {}
		         preparedStatement = null;
		         }
			
		      if (currentCon != null) {
		         try {
		            currentCon.close();
		         } catch (Exception e) {
		         }

		         currentCon = null;
		      }
		   }

		return tipiPag;
			
		   }	
}
