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
		      Integer id=0;
		      String searchQuery =
		              "select a.nome,a.codice,a.prezzo,a.id from articolo a "
		    		  +"WHERE a.visibile = true ";

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
		            id = rs.getInt("id");
		           
		            
		            
 		                   
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
	   
	   
	   
	  
	   

	   public static ArrayList<TagliaBean> getTagliaByColore(String id_coloren,String id_articolon) {
		   var idcolore=Integer.parseInt(id_coloren);
		   var id_articolo=Integer.parseInt(id_articolon);

		   

		   System.out.println("ciao"+id_coloren+id_articolon);
		   PreparedStatement preparedStatement = null;
		   ArrayList<TagliaBean> taglie = new ArrayList<TagliaBean>();
		    String searchQuery =
		              "select t.id,t.nome from variante_articolo v join taglia t on v.id_taglia=t.id ";
	       if (idcolore>0 && id_articolo>0) searchQuery += " AND v.id_colore=? and v.id_articolo=? group by t.id ";
	       
	       
	       try 
		      {
		         //connect to DB 
		         Connection currentCon = DriverManagerConnectionPool.getConnection();
		        
		         preparedStatement=currentCon.prepareStatement(searchQuery);
		         int index = 1;
		            if (idcolore>0 && id_articolo>0) {
		            	preparedStatement.setInt(index, idcolore);	
		            	++index;
		            	preparedStatement.setInt(index, id_articolo);	

		            }
		         rs = preparedStatement.executeQuery();	        
		         //boolean more = rs.next();
		         while (rs.next()) {

		      
		        	TagliaBean bean_t=new TagliaBean();
		           
		            Integer id_t = rs.getInt("id");
		            String nomet = rs.getString("nome");
		            bean_t.setNome(nomet);
		          
		            bean_t.setId(id_t);
		            taglie.add(bean_t);
		 
		       
		      } 
		      }

		      catch (Exception ex) 
		      {
		         System.out.println("Errore Ricerca TAGLIA " + ex);
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

		   return taglie;

	   }
	   
	   
	   
	   public static ArrayList<ArticoloBean> ricerca_per_categoria(String id_categoria) {
		   	 
		   	  var id=Integer.parseInt(id_categoria);
		      PreparedStatement preparedStatement = null;
		      ArrayList<ArticoloBean> articles = new ArrayList<ArticoloBean>();
		      
		      String searchQuery =
		              "select a.nome,a.codice,a.prezzo,a.id from articolo a "
		    		  +"WHERE a.visibile = true ";

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
		    String searchQuery = "select a.* from articolo a WHERE a.id= ?";
		    ArticoloBean bean_a = null;
		    ResultSet rs = null;

		    try (Connection currentCon = DriverManagerConnectionPool.getConnection()) {
		        preparedStatement = currentCon.prepareStatement(searchQuery);
		        preparedStatement.setInt(1, id);
		        rs = preparedStatement.executeQuery();

		        while (rs.next()) {
		            bean_a = new ArticoloBean();
		            String nomea = rs.getString("nome");
		            Integer ida= rs.getInt("id");
		            String codicea = rs.getString("codice");
		            Integer prezzo = rs.getInt("prezzo");
		            String descr = rs.getString("descrizione");
		            String barc = rs.getString("barcode");
		            String comp = rs.getString("composizione");
		            String st = rs.getString("stagione");
		            int visibile = rs.getInt("visibile");

		            bean_a.setNome(nomea);
		            bean_a.setCodice(codicea);
		            bean_a.setPrezzo(prezzo);
		            bean_a.setDescrizione(descr);
		            bean_a.setBarcode(barc);
		            bean_a.setComposizione(comp);
		            bean_a.setId(ida);
		            bean_a.setStagione(st);

		            if (visibile == 1) {
		                bean_a.setVisibile(true);
		            } else {
		                bean_a.setVisibile(false);
		            }

		            ArrayList<VariantiBean> varianti = new ArrayList<>();

		            String searchQueryVar = "select vd.id as idvar,vd.codice as codvar,vd.descrizione as descrvar,t.nome as nomet,t.id as idt,c.id as idc,c.nome as nomec,c.codice_html as chtml from variante_articolo vd "
		                + " left join colore c on vd.id_colore=c.id "
		                + " left join taglia t on vd.id_taglia=t.id "
		                + " WHERE vd.id_articolo = ? ";

		            try (PreparedStatement preparedStatement2 = currentCon.prepareStatement(searchQueryVar)) {
		                preparedStatement2.setInt(1, id);
		                ResultSet rs2 = preparedStatement2.executeQuery();

		                while (rs2.next()) {
		                    VariantiBean bean_v = new VariantiBean();
		                    TagliaBean taglia = new TagliaBean();
		                    ColoreBean colore = new ColoreBean();

		                    Integer idvar = rs2.getInt("idvar");
		                    String codvar = rs2.getString("codvar");
		                    String descrvar = rs2.getString("descrvar");
		                    Integer idt = rs2.getInt("idt");
		                    String nomet = rs2.getString("nomet");
		                    Integer idc = rs2.getInt("idc");
		                    String nomec = rs2.getString("nomec");
		                    String chtml = rs2.getString("chtml");

		                    bean_v.setId(idvar);
		                    bean_v.setCodice(codvar);
		                    bean_v.setDescrizione(descrvar);
		                    taglia.setId(idt);
		                    taglia.setNome(nomet);
		                    colore.setId(idc);
		                    colore.setNome(nomec);
		                    colore.setCodice_html(chtml);
		                    bean_v.setColore(colore);
		                    bean_v.setTaglia(taglia);
		                    varianti.add(bean_v);
		                }
		            } catch (Exception ex) {
		                System.out.println("Errore Ricerca ARTICOLO VARIANTE" + ex);
		            }

		            bean_a.setListaVarianti(varianti);

		            ArrayList<ColoreBean> colori = new ArrayList<>();

		            String searchColore = "select c.id as idc,c.nome as nomec,c.codice_html as chtml from variante_articolo vd "
		                + " join colore c on vd.id_colore=c.id "
		                + " WHERE vd.id_articolo = ?  group by c.id order by c.id";

		            try (PreparedStatement preparedStatement3 = currentCon.prepareStatement(searchColore)) {
		                preparedStatement3.setInt(1, id);
		                ResultSet rs3 = preparedStatement3.executeQuery();

		                while (rs3.next()) {
		                    ColoreBean colore = new ColoreBean();
		                    Integer idc = rs3.getInt("idc");
		                    String nomec = rs3.getString("nomec");
		                    String chtml = rs3.getString("chtml");

		                    colore.setId(idc);
		                    colore.setNome(nomec);
		                    colore.setCodice_html(chtml);
		                    colori.add(colore);
		                }
		            } catch (Exception ex) {
		                System.out.println("Errore Ricerca ARTICOLO COLORE" + ex);
		            }

		            bean_a.setListaColori(colori);
		        }
		    } catch (Exception ex) {
		        System.out.println("Errore ricerca articolo " + ex);
		    }

		    return bean_a;
		}
	   
	   


	   
}