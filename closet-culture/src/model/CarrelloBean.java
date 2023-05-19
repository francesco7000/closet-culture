package model;

import java.util.*;

public class CarrelloBean {
    
    private int idUtente;
    
    private Map<Integer,ElementoCarrello> carrello;

    public CarrelloBean() {
        this.idUtente = -1;
        this.carrello = new HashMap<>();
    }

    public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }

    public Map<Integer,ElementoCarrello> getCarrello() {
        return carrello;
    }

    public void setCarrello(Map<Integer,ElementoCarrello> carrello) {
        this.carrello = carrello;
    }

    
    
    public void addElemento(VariantiBean variante, ArticoloBean articolo, int quantita, int idRow) {
    	
    	ElementoCarrello elemento = new ElementoCarrello(variante,articolo,quantita);
    	
        carrello.put(idRow, elemento);
        
    }
    

    public void removeElemento(int idRow) {
    	
    	System.out.println(idRow);
    	
   
    	
    	//carrello.remove(idRow);
    	
    	
    }
    
}