package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.*;
import java.util.*;
import java.sql.*;


public class PagamentoDao {

	
	
	   static Connection currentCon = null;
	   static ResultSet rs = null;  
	
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
