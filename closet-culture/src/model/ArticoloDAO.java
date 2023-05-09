package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;

public class ArticoloDAO {
	 static Connection currentCon = null;
	 static Connection currentCon2 = null;
	  static ResultSet rs = null;
	  static ResultSet rs2 = null;  
	  
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
	   
	   
	   
	   
	   
	   public static ArticoloBean idRicerca(int id) {
		   
		   
			PreparedStatement preparedStatement = null;
			   
			   String searchQuery =
				"select a.* from articolo a "
				+ "WHERE a.id= ? ";
			   ArticoloBean bean_a = null;
			try {
				  //connect to DB 
				  Connection currentCon = DriverManagerConnectionPool.getConnection();

				preparedStatement = currentCon.prepareStatement(searchQuery);
				  int index = 1;
				if (id > 0) {
					preparedStatement.setInt(index, id);

				}

				rs = preparedStatement.executeQuery();



				while (rs.next()) {
					bean_a = new ArticoloBean();
				 String nomea = rs.getString("nome");
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
					bean_a.setStagione(st);

					if (visibile == 1)
						bean_a.setVisibile(true);
					else bean_a.setVisibile(false);



					ArrayList < VariantiBean > varianti = new ArrayList < VariantiBean > ();

				 String searchQueryVar = "select vd.id as idvar,vd.codice as codvar,vd.descrizione as descrvar,t.nome as nomet,t.id as idt,c.id as idc,c.nome as nomec,c.codice_html as chtml from variante_articolo vd "
						+ "  join colore c on vd.id_colore=c.id "
						+ "  join taglia t on vd.id_taglia=t.id "
						+ " WHERE vd.id_articolo = ? ";

					try {
						//connect to DB 
						PreparedStatement preparedStatement2 = null;
						preparedStatement2 = currentCon2.prepareStatement(searchQueryVar);
					   int index1 = 1;
						if (id > 0) {
							preparedStatement2.setInt(index1, id);

						}
						rs2 = preparedStatement2.executeQuery();
						//boolean more = rs.next();
						while (rs2.next()) {

					   // if user does not exist set the isValid variable to false
					
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


							//System.out.println("Welcome " + usn);
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



						bean_a.setListaVarianti(varianti);




						ArrayList < ColoreBean > colori = new ArrayList < ColoreBean > ();

					  String searchColore = "select c.id as idc,c.nome as nomec,c.codice_html as chtml from variante_articolo vd "
							+ " join colore c on vd.id_colore=c.id "
							+ " WHERE vd.id_articolo = ?  group by c.id order by c.id";

						preparedStatement = currentCon.prepareStatement(searchColore);
					   int indexc = 1;
						if (id > 0) {
							preparedStatement.setInt(indexc, id);

						}
						rs = preparedStatement.executeQuery();
						//boolean more = rs.next();
						while (rs.next()) {

					   // if user does not exist set the isValid variable to false
					
				   
						  ColoreBean colore = new ColoreBean();

						 
						  Integer idc = rs.getInt("idc");
						  String nomec = rs.getString("nomec");
						  String chtml = rs.getString("chtml");

							colore.setId(idc);
							colore.setNome(nomec);
							colore.setCodice_html(chtml);
							colori.add(colore);



						}
						bean_a.setListaColori(colori);
					}

					catch (Exception ex)
					{
						System.out.println("Errore Ricerca  ARTICOLO VARIANTE" + ex);
					}

					//some exception handling
					finally {
						if (rs != null) {
							try {
								rs.close();
							} catch (Exception e) { }
							rs = null;
						}

						if (preparedStatement != null) {
							try {
								preparedStatement.close();
							} catch (Exception e) { }
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






				}


			}

			catch (Exception ex)
			{
				System.out.println("Errore ricerca articolo " + ex);
			}

			   //some exception handling
			   finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (Exception e) { }
					rs = null;
				}

				if (preparedStatement != null) {
					try {
						preparedStatement.close();
					} catch (Exception e) { }
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
	   
	   

	   public static ArrayList<TagliaBean> getTagliaByColore(String id_coloren,String id_articolon) {
		   var idcolore=Integer.parseInt(id_coloren);
		   var id_articolo=Integer.parseInt(id_articolon);

		   
		   PreparedStatement preparedStatement = null;
		   ArrayList<TagliaBean> taglie = new ArrayList<TagliaBean>();
		    String searchQuery =
		              "select t.id,t.nome from articolo a "
		    		  +" join taglia t on a.id_taglia=t.id "
		    		  +" WHERE a.visibile = true ";

	       if (idcolore>0 && id_articolo>0) searchQuery += " AND a.id_colore=? and a.id= ? group by t.id ";
	       
	       
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
	   
	   


	   
}