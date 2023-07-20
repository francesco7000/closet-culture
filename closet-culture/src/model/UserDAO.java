package model;

import java.text.*;
import java.util.*;
import java.util.logging.Logger;
import java.sql.*;

public class UserDAO 	
{
   static Connection currentCon = null;
   static ResultSet rs = null;  
   static final Logger logger = Logger.getLogger("MyLogger");

   public static UserBean utenteByID(int id) {
	   

	      PreparedStatement preparedStatement = null;
		

	      UserBean bean=new UserBean();
	      
	      String searchQuery =
	            "select * from utente u "
	            + "join persona p on u.persona_id = p.id "
	            + "join profilo pr on u.profilo_id = pr.id "
	            + "left join indirizzo i on i.id_persona = p.id "
	            + "where u.id=? ";
	      
	 
		    
	   try 
	   {
		  
	      //connect to DB 
	      Connection currentCon = DriverManagerConnectionPool.getConnection();
	      preparedStatement=currentCon.prepareStatement(searchQuery);
	       preparedStatement.setInt(1,id);
	      rs = preparedStatement.executeQuery();

	      boolean more = rs.next();
		       
	      if (!more) 
	      {
	        
	         bean.setValid(false);
	      } 
		        
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
	         String cellulare = rs.getString("cellulare");
	         
	         String ruolo = rs.getString("ruolo");
	         Integer id_persona=rs.getInt("persona_id");
	         
		     	
	       
	         bean.setUsername(usn);
	         bean.setEmail(email);
	         bean.setNome(nome);
	         bean.setCognome(cognome);
	         bean.setCellulare(cellulare);
	         bean.setProvincia(provincia);
	         bean.setCitta(citta);
	         bean.setVia(via);
	         bean.setCap(cap);
	         bean.setIdPersona(id_persona);
	         bean.setNumero(numero);
	         bean.setRuolo(ruolo);
	         bean.setValid(true);
	      }
	   } 

	   catch (Exception ex) 
	   {
		   logger.log(null, "Eccezione non gestita: ");
	   } 
		    
	   finally 
	   {
	      if (rs != null)	{
	         try {
	            rs.close();
	         } catch (Exception e) {
	        	 logger.log(null, "Eccezione non gestita: ");
	         }
	            rs = null;
	         }
		
	      if (preparedStatement != null) {
	         try {
	        	 preparedStatement.close();
	         } catch (Exception e) {logger.log(null, "Eccezione non gestita: ");}
	         preparedStatement = null;
	         }
		
	      if (currentCon != null) {
	         try {
	            currentCon.close();
	         } catch (Exception e) {
	        	 logger.log(null, "Eccezione non gestita: ");
	         }

	         currentCon = null;
	      }
	   }

	return bean;
		
	   }	

   public static UserBean doRetrieve(UserBean bean) {
	   

      PreparedStatement preparedStatement = null;
	
      String username = bean.getUsername();    
      String password = bean.getPassword();   
      
      
      String searchQuery =
            "select * from utente u "
            + "join persona p on u.persona_id = p.id "
            + "join profilo pr on u.profilo_id = pr.id "
            + "join indirizzo i on i.id_persona = p.id "
            + "where username=?  AND password=? ";
      
 
	    
   try 
   {
      //connect to DB 
      Connection currentCon = DriverManagerConnectionPool.getConnection();
      preparedStatement=currentCon.prepareStatement(searchQuery);
      
    
      preparedStatement.setString(1,username);
      preparedStatement.setString(2,password);
      rs = preparedStatement.executeQuery();

      boolean more = rs.next();
	       
      // if user does not exist set the isValid variable to false
      if (!more) 
      {
        
         bean.setValid(false);
      } 
	        
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
         String cellulare = rs.getString("cellulare");
         Integer id=rs.getInt("id");
         String ruolo = rs.getString("ruolo");
         Integer id_persona=rs.getInt("persona_id");
         
	     	
         bean.setId(id);
         bean.setUsername(usn);
         bean.setEmail(email);
         bean.setNome(nome);
         bean.setCognome(cognome);
         bean.setCellulare(cellulare);
         bean.setProvincia(provincia);
         bean.setCitta(citta);
         bean.setVia(via);
         bean.setCap(cap);
         bean.setIdPersona(id_persona);
         bean.setNumero(numero);
         bean.setRuolo(ruolo);
         bean.setValid(true);
      }
   } 

   catch (Exception ex) 
   {
	   logger.log(null, "Eccezione non gestita: ");
   } 
	    
   finally 
   {
      if (rs != null)	{
         try {
            rs.close();
         } catch (Exception e) {
        	 logger.log(null, "Eccezione non gestita: ");
         }
            rs = null;
         }
	
      if (preparedStatement != null) {
         try {
        	 preparedStatement.close();
         } catch (Exception e) {
        	 logger.log(null, "Eccezione non gestita: ");
         }
         
         preparedStatement = null;
         }
	
      if (currentCon != null) {
         try {
            currentCon.close();
         } catch (Exception e) {
        	 logger.log(null, "Eccezione non gestita: ");
         }

         currentCon = null;
      }
   }

return bean;
	
   }	
   
   public static boolean checkUsernameAvaiable(String username) {
	   
	   PreparedStatement preparedStatement = null;
		

	      String searchQuery =
	            "select * from utente u where u.username=? ";
	      
	   try 
	   {
	      //connect to DB 
	      Connection currentCon = DriverManagerConnectionPool.getConnection();
	      preparedStatement=currentCon.prepareStatement(searchQuery);
	      preparedStatement.setString(1,username);
	      rs = preparedStatement.executeQuery();	        
	      boolean more = rs.next();
		       
	      if (!more) return true;
	      else return false;
	      
	   } 

	   catch (Exception ex) 
	   {
		   logger.log(null, "Eccezione non gestita: ");
	   } 
	   finally 
	   {
	      if (rs != null)	{
	         try {
	            rs.close();
	         } catch (Exception e) {
	        	 logger.log(null, "Eccezione non gestita: ");
	         }
	            rs = null;
	         }
		
	      if (preparedStatement != null) {
	         try {
	        	 preparedStatement.close();
	         } catch (Exception e) {
	        	 logger.log(null, "Eccezione non gestita: ");
	         }
	         	preparedStatement = null;
	         }
		
	      if (currentCon != null) {
	         try {
	            currentCon.close();
	         } catch (Exception e) {
	        	 logger.log(null, "Eccezione non gestita: ");
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
	        currentCon = DriverManagerConnectionPool.getConnection();
	        currentCon.setAutoCommit(false);

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

	        int profiloId = 3;
	        if(bean.getRuolo().equals("utente")) profiloId = 2;
	        else if(bean.getRuolo().equals("admin")) profiloId = 1;

	        String insertUtenteQuery = "INSERT INTO utente(username, password, email, persona_id, profilo_id) VALUES (?, ?, ?, ?, ?)";
	        utenteStatement = currentCon.prepareStatement(insertUtenteQuery);
	        utenteStatement.setString(1, bean.getUsername());
	        utenteStatement.setString(2, bean.getPassword());
	        utenteStatement.setString(3, bean.getEmail());
	        utenteStatement.setInt(4, personaId);
	        utenteStatement.setInt(5, profiloId);
	        int rowsAffectedUtente = utenteStatement.executeUpdate();
	        
	        
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
	            currentCon.commit();
	            return true;
	        } else {
	            currentCon.rollback();
	            return false;
	        }
	    } catch (SQLException ex) {
	    	 logger.log(null, "Eccezione non gestita: ");
	    	 try {
	            // Rollback della transazione in caso di eccezione
	            if (currentCon != null) {
	                currentCon.rollback();
	            }
	        } catch (SQLException ex2) {
	        	 logger.log(null, "Eccezione non gestita: ");
	        }
	        return false;
	    } finally {
	        if (indirizzoStatement != null) {
	            try {
	            	indirizzoStatement.close();
	            } catch (SQLException ex) {
	            	 logger.log(null, "Eccezione non gestita: ");
	            }
	        }
	        // Chiusura delle risorse e ripristino dell'autocommit
	        if (personaStatement != null) {
	            try {
	                personaStatement.close();
	            } catch (SQLException ex) {
	            	 logger.log(null, "Eccezione non gestita: ");
	            }
	        }
	        if (utenteStatement != null) {
	            try {
	                utenteStatement.close();
	            } catch (SQLException ex) {
	            	 logger.log(null, "Eccezione non gestita: ");
	            }
	        }
	        if (currentCon != null) {
	            try {
	                currentCon.setAutoCommit(true);
	                currentCon.close();
	            } catch (SQLException ex) {
	            	 logger.log(null, "Eccezione non gestita: ");
	            }
	        }
	    }
	}

public static boolean checkEmailAvaiable(String email) {
	
	   PreparedStatement preparedStatement = null;
	

    String searchQuery =
          "select * from utente u where u.email like ? ";
    
 try 
 {
    //connect to DB 
    Connection currentCon = DriverManagerConnectionPool.getConnection();

    preparedStatement=currentCon.prepareStatement(searchQuery);
    preparedStatement.setString(1,email);

    rs = preparedStatement.executeQuery();	        
    boolean more = rs.next();
	       
    
    if (!more) return true;
    else return false;
    
 } 

 catch (Exception ex) 
 {
	 logger.log(null, "Eccezione non gestita: ");
 } 
 finally 
 {
    if (rs != null)	{
       try {
          rs.close();
       } catch (Exception e) {
    	   logger.log(null, "Eccezione non gestita: ");
       }
          rs = null;
       }
	
    if (preparedStatement != null) {
       try {
      	 preparedStatement.close();
       } catch (Exception e) {
    	   logger.log(null, "Eccezione non gestita: ");
       }
       	preparedStatement = null;
       }
	
    if (currentCon != null) {
       try {
          currentCon.close();
       } catch (Exception e) {
    	   logger.log(null, "Eccezione non gestita: ");
       }

       currentCon = null;
    }
 }

 return false;
	
}


public static ArrayList<UserBean> ricercautenti(String cerca) {
	

	ArrayList<UserBean> users = new ArrayList<>();
	PreparedStatement preparedStatement = null;
	
	String searchQuery ="";
if(cerca!=null && !cerca.equals("")) {
	  searchQuery +=
		       "select email,username,id from utente u where u.username=? ";
		 
}else {
	 searchQuery+="select email,username,id from utente u ";
}


try 
{
 //connect to DB 
 Connection currentCon = DriverManagerConnectionPool.getConnection();
 preparedStatement=currentCon.prepareStatement(searchQuery);
 
 if (cerca!=null && !cerca.equals("")) {
	 	preparedStatement.setString(1, cerca);
	 	
 }
 
 rs = preparedStatement.executeQuery();	  
 
 
   
	while (rs.next()) {
		UserBean user=new UserBean();
		user.setEmail(rs.getString("email"));
		user.setUsername(rs.getString("username"));
		user.setId(rs.getInt("id"));
		users.add(user);
	}
} 
catch (SQLException e) {
	 logger.log(null, "Eccezione non gestita: ");
} 
finally {
	try {
		if(preparedStatement != null)
			preparedStatement.close();
		if(currentCon != null)
			DriverManagerConnectionPool.releaseConnection(currentCon);
	} catch (SQLException e) {
		 logger.log(null, "Eccezione non gestita: ");
	}
}


return users;
}

public  UserBean dettagli_profilo() {

	ArrayList<UserBean> users = new ArrayList<>();
	PreparedStatement preparedStatement = null;
	UserBean user = new UserBean();
	Connection currentConn = null;

 String searchQuery =
       "select u.email,u.username,p.nome,p.cognome,p.cellulare from utente u join persona p on u.persona_id=p.id "
       + "where u.id=? ";
 
try 
{
 //connect to DB 
 currentConn = DriverManagerConnectionPool.getConnection();
 preparedStatement=currentConn.prepareStatement(searchQuery);
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
	 logger.log(null, "Eccezione non gestita: ");
} 
finally {
	try {
		if(preparedStatement!= null)
			preparedStatement.close();
		if(currentConn!= null)
				DriverManagerConnectionPool.releaseConnection(currentConn);
	} catch (SQLException e) {
		 logger.log(null, "Eccezione non gestita: ");
	}
}


return user;
	
}

public static boolean updateUser(UserBean bean) throws SQLException {

    Connection conn = null;
    PreparedStatement psUtente = null;
    PreparedStatement psPersona = null;
    PreparedStatement psIndirizzo = null;
    boolean success = false;

    try {
        // Connessione al database
        conn = DriverManagerConnectionPool.getConnection();
        conn.setAutoCommit(false);
        // Update di nome, cognome e cellulare della persona
        String updatePersonaQuery = "UPDATE persona SET nome = ?, cognome = ?, cellulare = ? WHERE id = ?";
        psPersona = conn.prepareStatement(updatePersonaQuery);
        psPersona.setString(1, bean.getNome());
        psPersona.setString(2, bean.getCognome());
        psPersona.setString(3, bean.getCellulare().toString());
        psPersona.setInt(4, bean.getIdPersona());
        int rowsAffectedPersona = psPersona.executeUpdate();

        // Update di via, cap, città e numero dell'indirizzo
        String updateIndirizzoQuery = "UPDATE indirizzo SET via = ?, cap = ?, citta = ?, numero = ? WHERE id_persona = ?";
        psIndirizzo = conn.prepareStatement(updateIndirizzoQuery);
        psIndirizzo.setString(1, bean.getVia());
        psIndirizzo.setString(2, bean.getCap());
        psIndirizzo.setString(3, bean.getCitta());
        psIndirizzo.setString(4, bean.getNumero());
        psIndirizzo.setInt(5, bean.getIdPersona());
        int rowsAffectedIndirizzo = psIndirizzo.executeUpdate();

        // Verifica se tutti gli update sono stati eseguiti correttamente
        if (rowsAffectedPersona > 0 && rowsAffectedIndirizzo > 0) {
            success = true;
            conn.commit();
           
        } else {
            throw new SQLException("Errore durante l'update"+rowsAffectedPersona+rowsAffectedIndirizzo);
        }
    } catch (SQLException ex) {
        // Errore durante l'update, gestione dell'eccezione e rollback della transazione
        if (conn != null) {
            conn.rollback();
        }
        logger.log(null, "Eccezione non gestita: ");
        throw ex;
    } finally {
        // Chiusura delle risorse e ripristino dell'autocommit
        if (psUtente != null) {
            try {
                psUtente.close();
            } catch (SQLException ex) {
            	 logger.log(null, "Eccezione non gestita: ");            }
        }
        if (psPersona != null) {
            try {
                psPersona.close();
            } catch (SQLException ex) {
            	 logger.log(null, "Eccezione non gestita: ");            }
        }
        if (psIndirizzo != null) {
            try {
                psIndirizzo.close();
            } catch (SQLException ex) {
            	 logger.log(null, "Eccezione non gestita: ");            }
        }
        if (conn != null) {
            try {
                conn.setAutoCommit(true);
                conn.close();
            } catch (SQLException ex) {
            	 logger.log(null, "Eccezione non gestita: ");            }
        }
    }
    return success;
}
   
}

