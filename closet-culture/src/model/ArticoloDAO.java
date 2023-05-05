package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;

public class ArticoloDAO {
	 static Connection currentCon = null;
	  static ResultSet rs = null;  
	  
	   public static ArrayList<ArticoloBean> ricerca(String search) {

		      PreparedStatement preparedStatement = null;
		      ArrayList<ArticoloBean> articles = new ArrayList<ArticoloBean>();
		      
		      String searchQuery =
		              "select a.nome,a.codice,a.prezzo,a.id from articolo a "
		    		  +"WHERE 1=1 ";

	            if (search!=null && search!="") searchQuery += " AND a.nome LIKE ?  || a.codice= ? ";

		      try 
		      {
		         //connect to DB 
		         Connection currentCon = DriverManagerConnectionPool.getConnection();
		        
		         preparedStatement=currentCon.prepareStatement(searchQuery);
		         int index = 1;
		            if (search!=null && search!="") {
		            	preparedStatement.setString(index, "%"+search+"%");
		                index++;
		            	preparedStatement.setString(index, search);
		             
		            }
		         rs = preparedStatement.executeQuery();	        
		         //boolean more = rs.next();
		         while (rs.next()) {

		         // if user does not exist set the isValid variable to false
		      
		        	ArticoloBean bean_a=new ArticoloBean();
		            String nomea = rs.getString("nome");
		            String codicea = rs.getString("codice");
		            Integer prezzo = rs.getInt("prezzo");
		            Integer id = rs.getInt("id");
 		                   
		            //System.out.println("Welcome " + usn);
		            bean_a.setNome(nomea);
		            bean_a.setCodice(codicea);
		            bean_a.setPrezzo(prezzo);
		            bean_a.setId(id);
		            articles.add(bean_a);
		 
		       
		      } 
		      }

		      catch (Exception ex) 
		      {
		         System.out.println("Errore Ricerca " + ex);
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

		   return articles;
		 
	   }
	   
	   
	   public static ArrayList<ArticoloBean> ricerca_per_categoria(String id_categoria) {
		   	 
		   	  var id=Integer.parseInt(id_categoria);
		      PreparedStatement preparedStatement = null;
		      ArrayList<ArticoloBean> articles = new ArrayList<ArticoloBean>();
		      
		      String searchQuery =
		              "select a.nome,a.codice,a.prezzo,a.id from articolo a "
		    		  +"WHERE 1=1 ";

	            if (id>0) searchQuery += " AND a.id_categoria_articolo=? ";

		      try 
		      {
		         //connect to DB 
		         Connection currentCon = DriverManagerConnectionPool.getConnection();
		        
		         preparedStatement=currentCon.prepareStatement(searchQuery);
		         int index = 1;
		            if (id>0) {
		            	preparedStatement.setInt(index, id);		             
		            }
		         rs = preparedStatement.executeQuery();	        
		         //boolean more = rs.next();
		         while (rs.next()) {

		         // if user does not exist set the isValid variable to false
		      
		        	ArticoloBean bean_a=new ArticoloBean();
		            String nomea = rs.getString("nome");
		            String codicea = rs.getString("codice");
		            Integer prezzo = rs.getInt("prezzo");
		            Integer id_a = rs.getInt("id");
		            bean_a.setNome(nomea);
		            bean_a.setCodice(codicea);
		            bean_a.setPrezzo(prezzo);
		            bean_a.setId(id_a);
		            articles.add(bean_a);
		 
		       
		      } 
		      }

		      catch (Exception ex) 
		      {
		         System.out.println("Errore Ricerca " + ex);
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

		   return articles;
		 
	   }
	   
	   
	   
	   public static ArticoloBean idRicerca(int id) {
		   
		   
		   PreparedStatement preparedStatement = null;
		      
		      String searchQuery =
		              "select a.nome,a.codice,a.prezzo,a.descrizione,a.barcode,a.composizione,a.stagione from articolo a "
		    		  +"WHERE a.id= ? ";
		      ArticoloBean bean_a=new ArticoloBean();
		      try 
		      {
		         //connect to DB 
		         Connection currentCon = DriverManagerConnectionPool.getConnection();
		        
		         preparedStatement=currentCon.prepareStatement(searchQuery);
		         int index = 1;
		            if (id>0) {
		            	preparedStatement.setInt(index, id);

		            }
		         rs = preparedStatement.executeQuery();	  
		         while (rs.next()) {
	            String nomea = rs.getString("nome");
	            String codicea = rs.getString("codice");
	            Integer prezzo = rs.getInt("prezzo");
	            String descr = rs.getString("descrizione");
	            String barc = rs.getString("barcode");
	            String comp = rs.getString("composizione");
	            String st = rs.getString("stagione");

	            bean_a.setNome(nomea);
	            bean_a.setCodice(codicea);
	            bean_a.setPrezzo(prezzo);
	            bean_a.setDescrizione(descr);
	           	bean_a.setBarcode(barc);
	        	bean_a.setComposizione(comp);
	        	bean_a.setStagione(st);
		         }
		      
		      }

		      catch (Exception ex) 
		      {
		         System.out.println("Errore ricerca " + ex);
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

		   return bean_a;
	
	   }
	   
	   
}