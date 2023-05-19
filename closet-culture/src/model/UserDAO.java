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
            + "join indirizzo i on i.id_persona = p.id "
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
         String provincia = rs.getString("provincia");
         String citta = rs.getString("citta");
         String via = rs.getString("via");
         String cap = rs.getString("cap");
         String numero = rs.getString("numero");
         
         Long cellulare = rs.getLong("cellulare");
         
         String ruolo = rs.getString("ruolo");
         
	     	
         System.out.println("Welcome " + usn);
         bean.setUsername(usn);
         bean.setEmail(email);
         bean.setNome(nome);
         bean.setCognome(cognome);
         bean.setCellulare(cellulare);
         bean.setProvincia(provincia);
         bean.setCitta(citta);
         bean.setVia(via);
         bean.setCap(cap);
         bean.setNumero(numero);
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
	            + "where u.username='"+ username+ "'";
	      
	   try 
	   {
	      //connect to DB 
	      Connection currentCon = DriverManagerConnectionPool.getConnection();
	      preparedStatement=currentCon.prepareStatement(searchQuery);
	      rs = preparedStatement.executeQuery(searchQuery);	        
	      boolean more = rs.next();
		       
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
	    PreparedStatement indirizzoStatement = null;
	    
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
	        
	        
	        //Salvo l'indirizzo della persona nel database
	        String insertIndirizzoQuery = "INSERT INTO indirizzo (provincia, via, cap, citta, numero, id_persona) VALUES (?,?,?,?,?,?)";
	        indirizzoStatement = currentCon.prepareStatement(insertIndirizzoQuery);
	        indirizzoStatement.setString(1,bean.getProvincia());
	        indirizzoStatement.setString(2,bean.getVia());
	        indirizzoStatement.setString(3,bean.getCap());
	        indirizzoStatement.setString(4,bean.getCitta());
	        indirizzoStatement.setString(5,bean.getNumero());
	        indirizzoStatement.setInt(6,personaId);
	        int rowsAffectedIndirizzo = indirizzoStatement.executeUpdate();
	        
	        
	       
	        if (rowsAffectedPersona > 0 && rowsAffectedUtente > 0 && rowsAffectedIndirizzo > 0) {
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

public static boolean checkEmailAvaiable(String email) {
	
	   PreparedStatement preparedStatement = null;
	

    String searchQuery =
          "select * from utente u "
          + "where u.email like '"+ email+ "'";
    
 try 
 {
    //connect to DB 
    Connection currentCon = DriverManagerConnectionPool.getConnection();
    preparedStatement=currentCon.prepareStatement(searchQuery);
    rs = preparedStatement.executeQuery(searchQuery);	        
    boolean more = rs.next();
	       
    
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


public static ArrayList<UserBean> ricercautenti(String cerca) {
	

	ArrayList<UserBean> users = new ArrayList<>();
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	UserBean user = new UserBean();
	

 String searchQuery =
       "select email,username from utente u "
       + "where u.username=? ";
 
try 
{
 //connect to DB 
 Connection currentCon = DriverManagerConnectionPool.getConnection();
 preparedStatement=currentCon.prepareStatement(searchQuery);
 if (cerca!=null && cerca!="") {
	 	preparedStatement.setString(1, cerca);
  }
 rs = preparedStatement.executeQuery();	        

	while (rs.next()) {
		
		user.setEmail(rs.getString("email"));
		user.setUsername(rs.getString("username"));
		users.add(user);
	}
} 
catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} 
finally {
	try {
		preparedStatement.close();
		DriverManagerConnectionPool.releaseConnection(currentCon);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}


return users;
}

/*
public static boolean nuovoprofilo(String nome, String cognome, String email, String cellulare)
    String insertQuery = "INSERT INTO articolo (visibile, codice, barcode, nome, descrizione, prezzo, sconto, stagione, id_categoria_articolo, linea_id, materiale_id, quantità, composizione) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? , ?)";
    int result = 0;
    Connection currentCon = null;

    try {
        currentCon = DriverManagerConnectionPool.getConnection();
        currentCon.setAutoCommit(false); // disattivo l'autocommit

        preparedStatement = currentCon.prepareStatement(insertQuery);
        preparedStatement.setBoolean(1, visibile);
        preparedStatement.setString(2, codice);
        preparedStatement.setString(3, barcode);
        preparedStatement.setString(4, nome);
        preparedStatement.setString(5, descrizione);
        preparedStatement.setDouble(6, prezzo);
        preparedStatement.setInt(7, sconto);
        preparedStatement.setString(8, stagione);
        preparedStatement.setInt(9, id_categoria);
        preparedStatement.setInt(10, id_linea);
        preparedStatement.setInt(11, id_materiale);
        preparedStatement.setInt(12, 0);
        preparedStatement.setString(13, "composizione di prova");
        result = preparedStatement.executeUpdate();

        currentCon.commit(); // eseguo il commit esplicitamente

    } catch (SQLException e) {
        // Gestione dell'errore
        e.printStackTrace();
        try {
            if (currentCon != null) {
            	System.out.println("rollback");
                currentCon.rollback(); // eseguo il rollback esplicitamente in caso di errore
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    } finally {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                // Gestione dell'errore
                e.printStackTrace();
            }
        }
        if (currentCon != null) {
            try {
                currentCon.setAutoCommit(true); // riattivo l'autocommit
                currentCon.close();
            } catch (SQLException e) {
                // Gestione dell'errore
                e.printStackTrace();
            }
        }
    }

    return result > 0;
}
*/




public  UserBean dettagli_profilo() {

	ArrayList<UserBean> users = new ArrayList<>();
	PreparedStatement preparedStatement = null;
	UserBean user = new UserBean();
	

 String searchQuery =
       "select u.email,u.username,p.nome,p.cognome,p.cellulare from utente u join persona p on u.persona_id=p.id "
       + "where u.id=? ";
 
try 
{
 //connect to DB 
 Connection currentCon = DriverManagerConnectionPool.getConnection();
 preparedStatement=currentCon.prepareStatement(searchQuery);
 preparedStatement.setInt(1, 1);
 rs = preparedStatement.executeQuery();	        

	while (rs.next()) {
		
		user.setEmail(rs.getString("email"));
		user.setUsername(rs.getString("username"));
		user.setNome(rs.getString("nome"));
		user.setCognome(rs.getString("cognome"));
		user.setCellulare(rs.getString("cellulare"));
		users.add(user);
	}
} 
catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} 
finally {
	try {
		preparedStatement.close();
		DriverManagerConnectionPool.releaseConnection(currentCon);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}


return user;
	
}
   
}

