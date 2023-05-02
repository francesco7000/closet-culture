package model;

import java.text.*;
import java.util.*;
import java.sql.*;

public class UserDAO 	
{
   static Connection currentCon = null;
   static ResultSet rs = null;  
	
   public static UserBean doRetrieve(UserBean bean) {
	   

      PreparedStatement preparedStatement = null;
	
      String username = bean.getUsername();    
      String password = bean.getPassword();   
      
      
      String searchQuery =
            "select * from utente u "
            + "join persona p on u.persona_id = p.id "
            + "join profilo pr on u.profilo_id = pr.id "
            + "where username='"+ username+ "' AND password='"+ password+ "'";
      
 
	    
   try 
   {
      //connect to DB 
      Connection currentCon = DriverManagerConnectionPool.getConnection();
      preparedStatement=currentCon.prepareStatement(searchQuery);
      rs = preparedStatement.executeQuery(searchQuery);	        
      boolean more = rs.next();
	       
      // if user does not exist set the isValid variable to false
      if (!more) 
      {
         System.out.println("Sorry, you are not a registered user! Please sign up first");
         bean.setValid(false);
      } 
	        
      //if user exists set the isValid variable to true
      else if (more) 
      {
         String usn = rs.getString("username");
         String email = rs.getString("email");
         String nome = rs.getString("nome");
         String cognome = rs.getString("cognome");
         Long cellulare = rs.getLong("cellulare");
         
         String ruolo = rs.getString("ruolo");
         
	     	
         System.out.println("Welcome " + usn);
         bean.setUsername(usn);
         bean.setEmail(email);
         bean.setNome(nome);
         bean.setCognome(cognome);
         bean.setCellulare(cellulare);
         bean.setRuolo(ruolo);
         bean.setValid(true);
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

return bean;
	
   }	
   
   public static boolean checkUsernameAvaiable(String username) {
	   
	   PreparedStatement preparedStatement = null;
		

	      String searchQuery =
	            "select * from utente u "
	            + "where username='"+ username+ "'";
	      
	   try 
	   {
	      //connect to DB 
	      Connection currentCon = DriverManagerConnectionPool.getConnection();
	      preparedStatement=currentCon.prepareStatement(searchQuery);
	      rs = preparedStatement.executeQuery(searchQuery);	        
	      boolean more = rs.next();
		       
	      // if user does not exist set the isValid variable to false
	      if (!more) return true;
	      else return false;
	      
	   } 

	   catch (Exception ex) 
	   {
	      System.out.println("Registration Failed: An Exception has occurred! " + ex);
	   } 
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

	   return false;
		
	   }
   
   
   public static boolean saveUser(UserBean bean) {
	    PreparedStatement personaStatement = null;
	    PreparedStatement utenteStatement = null;
	    Connection currentCon = null;

	    try {
	        // Connessione al database con autocommit disabilitato
	        currentCon = DriverManagerConnectionPool.getConnection();
	        currentCon.setAutoCommit(false);

	        // Salvo la persona nel database
	        String insertPersonaQuery = "INSERT INTO persona(nome, cognome, cellulare) VALUES (?, ?, ?)";
	        personaStatement = currentCon.prepareStatement(insertPersonaQuery, Statement.RETURN_GENERATED_KEYS);
	        personaStatement.setString(1, bean.getNome());
	        personaStatement.setString(2, bean.getCognome());
	        personaStatement.setString(3, bean.getCellulare().toString());
	        int rowsAffectedPersona = personaStatement.executeUpdate();
	        ResultSet personaKeys = personaStatement.getGeneratedKeys();
	        int personaId = -1;
	        if (personaKeys.next()) {
	            personaId = personaKeys.getInt(1);
	        }

	        System.out.println("Chiave esterna persona: " + personaId);

	        //default = 3 = ospite
	        int profiloId = 3;
	        if(bean.getRuolo().equals("utente")) profiloId = 2;
	        else if(bean.getRuolo().equals("admin")) profiloId = 1;

	        // Salvo l'utente nel database
	        String insertUtenteQuery = "INSERT INTO utente(username, password, email, persona_id, profilo_id) VALUES (?, ?, ?, ?, ?)";
	        utenteStatement = currentCon.prepareStatement(insertUtenteQuery);
	        utenteStatement.setString(1, bean.getUsername());
	        utenteStatement.setString(2, bean.getPassword());
	        utenteStatement.setString(3, bean.getEmail());
	        utenteStatement.setInt(4, personaId);
	        utenteStatement.setInt(5, profiloId);
	        int rowsAffectedUtente = utenteStatement.executeUpdate();

	        if (rowsAffectedPersona > 0 && rowsAffectedUtente > 0) {
	            // Commit della transazione
	            currentCon.commit();
	            // L'utente è stato salvato correttamente nel database
	            return true;
	        } else {
	            // Rollback della transazione
	            currentCon.rollback();
	            // Qualcosa è andato storto durante il salvataggio dell'utente nel database
	            return false;
	        }
	    } catch (SQLException ex) {
	        System.out.println("Errore durante il salvataggio dell'utente nel database: " + ex.getMessage());
	        try {
	            // Rollback della transazione in caso di eccezione
	            if (currentCon != null) {
	                currentCon.rollback();
	            }
	        } catch (SQLException ex2) {}
	        return false;
	    } finally {
	        // Chiusura delle risorse e ripristino dell'autocommit
	        if (personaStatement != null) {
	            try {
	                personaStatement.close();
	            } catch (SQLException ex) {}
	        }
	        if (utenteStatement != null) {
	            try {
	                utenteStatement.close();
	            } catch (SQLException ex) {}
	        }
	        if (currentCon != null) {
	            try {
	                currentCon.setAutoCommit(true);
	                currentCon.close();
	            } catch (SQLException ex) {}
	        }
	    }
	}
   
}

