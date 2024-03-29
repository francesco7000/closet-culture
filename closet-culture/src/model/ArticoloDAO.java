package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.*;
public class ArticoloDAO {
	 static Connection currentCon = null;
	  static ResultSet rs = null;  
	   static final Logger logger = Logger.getLogger("MyLogger");
	   public static ArrayList<ArticoloBean> ricerca(String search) {
		   

		      PreparedStatement preparedStatement = null;
		      ArrayList<ArticoloBean> articles = new ArrayList<>();
		      Integer id=0;
		      String searchQuery =
		              "select a.nome,a.codice,a.prezzo,a.id,a.url from articolo a "
		    		  +"WHERE a.visibile = true ";

		      if (search!=null && !search.equals(""))  searchQuery += " AND a.nome LIKE ?  || a.codice= ? ";

		      try 
		      {
		         Connection currentCon = DriverManagerConnectionPool.getConnection();
		        
		         preparedStatement=currentCon.prepareStatement(searchQuery);
		         int index = 1;
		            if (search!=null && !search.equals("")) {
		            	preparedStatement.setString(index, "%"+search+"%");
		                index++;
		            	preparedStatement.setString(index, search);
		             
		            }
		         rs = preparedStatement.executeQuery();	        
		         while (rs.next()) {		      
		        	ArticoloBean bean_a=new ArticoloBean();
		            String nomea = rs.getString("nome");
		            String codicea = rs.getString("codice");
		           
		            Integer prezzo = rs.getInt("prezzo");
		            String url = rs.getString("url");
		            bean_a.setUrl(url);
		            id = rs.getInt("id");
		            bean_a.setNome(nomea);
		            bean_a.setCodice(codicea);
		       
		            bean_a.setPrezzo(prezzo);
		            bean_a.setId(id);
		          
		            articles.add(bean_a);
		 
		       
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
		            	DriverManagerConnectionPool.releaseConnection(currentCon);
		            } catch (Exception e) {
		               logger.log(null, "Eccezione non gestita: ");

		            }

		            currentCon = null;
		         }
		      }
		      
		  
		      
		   return articles;
		 
	   }
	     

	   public static ArrayList<TagliaBean> getTagliaByColore(String id_coloren,String id_articolon) {
		   var idcolore=Integer.parseInt(id_coloren);
		   var id_articolo=Integer.parseInt(id_articolon);

		   

		   PreparedStatement preparedStatement = null;
		   ArrayList<TagliaBean> taglie = new ArrayList<>();
		    String searchQuery =
		              "select t.id,t.nome from variante_articolo v join taglia t on v.id_taglia=t.id ";
	       if (idcolore>0 && id_articolo>0) searchQuery += " AND v.id_colore=? and v.id_articolo=? group by t.id ";
	       
	       
	       try 
		      {
		         Connection currentCon = DriverManagerConnectionPool.getConnection();
		        
		         preparedStatement=currentCon.prepareStatement(searchQuery);
		         int index = 1;
		            if (idcolore>0 && id_articolo>0) {
		            	preparedStatement.setInt(index, idcolore);	
		            	++index;
		            	preparedStatement.setInt(index, id_articolo);	

		            }
		         rs = preparedStatement.executeQuery();	        
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
		            	DriverManagerConnectionPool.releaseConnection(currentCon);
		            } catch (Exception e) {
		            	 logger.log(null, "Eccezione non gestita: ");

		            }

		            currentCon = null;
		         }
		      }

		   return taglie;

	   }
	   
	   
	   public static ArrayList<ArticoloBean> ricerca_per_categoria(String id_categoria) {
		   	 
		   	  var id=Integer.parseInt(id_categoria);
		      PreparedStatement preparedStatement = null;
		      ArrayList<ArticoloBean> articles = new ArrayList<>();
		      
		      String searchQuery =
		              "select a.nome,a.codice,a.prezzo,a.id,a.url from articolo a "
		    		  +"WHERE a.visibile = true ";

	            if (id>0) searchQuery += " AND a.id_categoria_articolo=? ";

		      try 
		      {
		         Connection currentCon = DriverManagerConnectionPool.getConnection();
		        
		         preparedStatement=currentCon.prepareStatement(searchQuery);
		         int index = 1;
		            if (id>0) {
		            	preparedStatement.setInt(index, id);		             
		            }
		         rs = preparedStatement.executeQuery();	        
		         while (rs.next()) {

		      
		        	ArticoloBean bean_a=new ArticoloBean();
		            String nomea = rs.getString("nome");
		            String codicea = rs.getString("codice");
		            Integer prezzo = rs.getInt("prezzo");
		            Integer id_a = rs.getInt("id");
		            bean_a.setNome(nomea);
		            String url = rs.getString("url");
		            bean_a.setUrl(url);
		            bean_a.setCodice(codicea);
		            bean_a.setPrezzo(prezzo);
		            bean_a.setId(id_a);
		            articles.add(bean_a);
		 
		       
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
		            	DriverManagerConnectionPool.releaseConnection(currentCon);
		    				
		            } catch (Exception e) {
		               logger.log(null, "Eccezione non gestita: ");
		            }

		            currentCon = null;
		         }
		      }

		   return articles;
		 
	   }
	   
	   
	   public static ArrayList<ArticoloBean> ricerca_per_linea(String id_linea) {
		   	 
		   	  var id=Integer.parseInt(id_linea);
		      PreparedStatement preparedStatement = null;
		      ArrayList<ArticoloBean> articles = new ArrayList<ArticoloBean>();
		      
		      String searchQuery =
		              "select a.nome,a.codice,a.prezzo,a.id,a.url from articolo a "
		    		  +"WHERE a.visibile = true ";

	            if (id>0) searchQuery += " AND a.linea_id=? ";

		      try 
		      {
		         Connection currentCon = DriverManagerConnectionPool.getConnection();
		         preparedStatement=currentCon.prepareStatement(searchQuery);
		         int index = 1;
		            if (id>0) {
		            	preparedStatement.setInt(index, id);		             
		            }
		         rs = preparedStatement.executeQuery();	        
		         while (rs.next()) {
		        	ArticoloBean bean_a=new ArticoloBean();
		            String nomea = rs.getString("nome");
		            String codicea = rs.getString("codice");
		            Integer prezzo = rs.getInt("prezzo");
		            Integer id_a = rs.getInt("id");
		            String url = rs.getString("url");
		            bean_a.setUrl(url);
		            bean_a.setNome(nomea);
		            bean_a.setCodice(codicea);
		            bean_a.setPrezzo(prezzo);
		            bean_a.setId(id_a);
		            articles.add(bean_a);
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
		            	DriverManagerConnectionPool.releaseConnection(currentCon);
		            } catch (Exception e) {
		               logger.log(null, "Eccezione non gestita: ");

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

		    try  {
		    	Connection currentCon = DriverManagerConnectionPool.getConnection();
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
		            String url = rs.getString("url");
		            bean_a.setUrl(url);
		            
		            bean_a.setLinea(getLinea(bean_a.getId()));
		            bean_a.setCategoria(CategoriaDao.getCategoriaArticolo(bean_a.getId()));
		            bean_a.setMateriale(getMateriale(bean_a.getId()));

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

		            PreparedStatement preparedStatement2 = null;
		            ResultSet rs2 = null;
		            try  {
		            	preparedStatement2 = currentCon.prepareStatement(searchQueryVar);
		                preparedStatement2.setInt(1, id);
		                rs2 = preparedStatement2.executeQuery();

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
		                logger.log(null, "Eccezione non gestita: ");



		            }
		            finally {
				        try {
				            if (rs2 != null) {
				                rs2.close();
				            }
				            if (preparedStatement2 != null) {
				                preparedStatement2.close();
				            }
				     
				        } catch (Exception ex) {
				            logger.log(null, "Eccezione non gestita: ");


				        }
				    }
		            

		            bean_a.setListaVarianti(varianti);

		            ArrayList<ColoreBean> colori = new ArrayList<>();

		            String searchColore = "select c.id as idc,c.nome as nomec,c.codice_html as chtml from variante_articolo vd "
		                + " join colore c on vd.id_colore=c.id "
		                + " WHERE vd.id_articolo = ?  group by c.id order by c.id";

		            PreparedStatement preparedStatement3 = null;
		            ResultSet rs3 = null;
		            try {
		            	preparedStatement3 = currentCon.prepareStatement(searchColore);
		                preparedStatement3.setInt(1, id);
		                rs3 = preparedStatement3.executeQuery();

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
		            	 logger.log(null, "Eccezione non gestita: ");

		            }
		            finally {
				        try {
				            if (rs3 != null) {
				                rs3.close();
				            }
				            if (preparedStatement3 != null) {
				                preparedStatement3.close();
				            }
				     
				        } catch (Exception ex) {
				            logger.log(null, "Eccezione non gestita: ");

				        }
				    }

		            bean_a.setListaColori(colori);
		        }
		    } catch (Exception ex) {
		        logger.log(null, "Eccezione non gestita: ");

		    } 
		    finally {
		        
		    	try {
		    		
		            if (rs != null) {
		                rs.close();
		            }
		            
		            if (preparedStatement != null) {
		                preparedStatement.close();
		            }
		            
		            if(currentCon!= null)
						DriverManagerConnectionPool.releaseConnection(currentCon);   
		        } 
		    	catch (Exception ex) {
		            logger.log(null, "Eccezione non gestita: ");

		        }
		    }

		    return bean_a;
		}
	   
	   
	   
	   public static MaterialeBean getMateriale(int id) {
		    PreparedStatement preparedStatement = null;
		    MaterialeBean bean_m = null;

		    String searchQuery = "SELECT m.id, m.tipo FROM materiale m JOIN articolo a ON m.id = a.materiale_id WHERE a.id = ?";

		    try {
		        Connection currentCon = DriverManagerConnectionPool.getConnection();
		        preparedStatement = currentCon.prepareStatement(searchQuery);
		        preparedStatement.setInt(1, id);

		        ResultSet rs = preparedStatement.executeQuery();

		        if (rs.next()) {
		            bean_m = new MaterialeBean();
		            Integer id_m = rs.getInt("id");
		            String tipo = rs.getString("tipo");

		            bean_m.setId(id_m);
		            bean_m.setTipo(tipo);
		        }
		    } catch (SQLException e) {
		       logger.log(null, "Eccezione non gestita: ");
		    } finally {
		        try {
		            if (preparedStatement != null) {
		                preparedStatement.close();
		            }
		        } catch (SQLException e) {
		           logger.log(null, "Eccezione non gestita: ");

		        }
		        finally {
			        try {
			            if (preparedStatement != null) {
			                preparedStatement.close();
			            }
			            DriverManagerConnectionPool.releaseConnection(currentCon);
			        } catch (SQLException e) {
			           logger.log(null, "Eccezione non gestita: ");

			        }
			    }
		    }

		    return bean_m;
		}

		public static LineaBean getLinea(int id) {

		    PreparedStatement preparedStatement = null;
		    LineaBean bean_l = null;

		    String searchQuery = "SELECT l.id, l.codice, l.descrizione FROM linea l JOIN articolo a ON l.id = a.linea_id WHERE a.id = ?";

		    try {
		        Connection currentCon = DriverManagerConnectionPool.getConnection();
		        preparedStatement = currentCon.prepareStatement(searchQuery);
		        preparedStatement.setInt(1, id);

		        ResultSet rs = preparedStatement.executeQuery();

		        if (rs.next()) {
		            bean_l = new LineaBean();
		            Integer id_l = rs.getInt("id");
		            String codice = rs.getString("codice");
		            String descrizione = rs.getString("descrizione");

		            bean_l.setId(id_l);
		            bean_l.setCodice(codice);
		            bean_l.setDescrizione(descrizione);
		        }
		    } catch (SQLException e) {
		       logger.log(null, "Eccezione non gestita: ");
		    } finally {
		        try {
		            if (preparedStatement != null) {
		                preparedStatement.close();
		            }
		            DriverManagerConnectionPool.releaseConnection(currentCon);
		        } catch (SQLException e) {
		           logger.log(null, "Eccezione non gestita: ");
		        }
		    }

		    return bean_l;
		}
		


	public static boolean eliminaArticolo(int id) {

		    PreparedStatement preparedStatement = null;
		    String updateQuery = "UPDATE articolo SET visibile = false WHERE id = ?";
		    int result = 0;

		    try (Connection currentCon = DriverManagerConnectionPool.getConnection()) {
		        currentCon.setAutoCommit(false); // disattivo l'autocommit

		        preparedStatement = currentCon.prepareStatement(updateQuery);
		        preparedStatement.setInt(1, id);
		        result = preparedStatement.executeUpdate();

		        currentCon.commit(); // eseguo il commit esplicitamente

		    } catch (SQLException e) {
		       logger.log(null, "Eccezione non gestita: ");

		        try {
		            if (currentCon != null) {
		                currentCon.rollback(); // eseguo il rollback esplicitamente in caso di errore
		            }
		        } catch (SQLException ex) {
		            logger.log(null, "Eccezione non gestita: ");


		        }
		        return false;
		    } finally {
		        if (preparedStatement != null) {
		            try {
		                preparedStatement.close();
		            } catch (SQLException e) {
		               logger.log(null, "Eccezione non gestita: ");

		            }
		        }
		        try {
		            if (currentCon != null) {
		                currentCon.setAutoCommit(true); // riattivo l'autocommit
		                DriverManagerConnectionPool.releaseConnection(currentCon);
		            }
		        } catch (SQLException e) {
		           logger.log(null, "Eccezione non gestita: ");

		        }
		    }

		    return result > 0;
		}
	
	
	public static boolean modificaArticolo(int id, boolean visibile, String codice, String barcode, String nome, String descrizione, double prezzo, int sconto, String stagione, int id_categoria, int id_linea, int id_materiale) {
	    PreparedStatement preparedStatement = null;
	    String updateQuery = "UPDATE articolo SET visibile = ?, codice = ?, barcode = ?, nome = ?, descrizione = ?, prezzo = ?, sconto = ?, stagione = ?, id_categoria_articolo = ?, linea_id = ?, materiale_id = ? WHERE id = ?";
	    int result = 0;
	    Connection currentCon = null;

	    try {
	        currentCon = DriverManagerConnectionPool.getConnection();
	        currentCon.setAutoCommit(false); // disattivo l'autocommit

	        preparedStatement = currentCon.prepareStatement(updateQuery);
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
	        preparedStatement.setInt(12, id);
	        result = preparedStatement.executeUpdate();

	        currentCon.commit(); 

	    } catch (SQLException e) {
	       logger.log(null, "Eccezione non gestita: ");

	        try {
	            if (currentCon != null) {
	                currentCon.rollback(); 
	            }
	        } catch (SQLException ex) {
	            logger.log(null, "Eccezione non gestita: ");


	        }
	        return false;
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

	    return result > 0;
	}
	
	
	public static boolean nuovoArticolo(boolean visibile, String codice, String barcode, String nome, String descrizione, double prezzo, int sconto, String stagione, int id_categoria, int id_linea, int id_materiale) {
	    PreparedStatement preparedStatement = null;
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
	       logger.log(null, "Eccezione non gestita: ");

	        try {
	            if (currentCon != null) {
	            	logger.log(null, "Eccezione non gestita: ");
	                currentCon.rollback(); 
	            }
	        } catch (SQLException ex) {
	            logger.log(null, "Eccezione non gestita: ");


	        }
	        return false;
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
	                currentCon.setAutoCommit(true); 
	                DriverManagerConnectionPool.releaseConnection(currentCon);
	            } catch (SQLException e) {
	               logger.log(null, "Eccezione non gestita: ");

	            }
	        }
	    }

	    return result > 0;
	}
	
	
	

	   public static int getQta(String id_coloren,String id_articolon,String id_tagliat) {
		   var idcolore=Integer.parseInt(id_coloren);
		   var id_articolo=Integer.parseInt(id_articolon);
		   var id_taglia=Integer.parseInt(id_tagliat);
		   int qta = 0;

		   PreparedStatement preparedStatement = null;
		    String searchQuery =
		              "select v.quantita from variante_articolo v join taglia t on v.id_taglia=t.id ";
	       if (idcolore>0 && id_articolo>0 && id_taglia>0) searchQuery += " AND v.id_colore=? and v.id_articolo=?  and v.id_taglia=? group by v.id";
	       
	       
	       try 
		      {
		         //connect to DB 
		         Connection currentCon = DriverManagerConnectionPool.getConnection();
		        
		         preparedStatement=currentCon.prepareStatement(searchQuery);
		         int index = 1;
		            if (idcolore>0 && id_articolo>0 && id_taglia>0) {
		            	preparedStatement.setInt(index, idcolore);	
		            	++index;
		            	preparedStatement.setInt(index, id_articolo);	
		            	++index;
		            	preparedStatement.setInt(index, id_taglia);

		            }
		         rs = preparedStatement.executeQuery();	        
		        
		         while (rs.next()) {

		      
		        	 qta= rs.getInt("quantita");
		     
		       
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
		            	 DriverManagerConnectionPool.releaseConnection(currentCon);
		            } catch (Exception e) {
		               logger.log(null, "Eccezione non gestita: ");

		            }

		            currentCon = null;
		         }
		      }

		   return qta;

	   }
	   
	

	   


	   
}