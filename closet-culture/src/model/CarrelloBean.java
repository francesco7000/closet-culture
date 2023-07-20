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
    
    public String generaOrdine(int idTipoPag,int idUtente) {
    	String carrelloString = "";

    	Map<Integer, ElementoCarrello> elementiCarrello = this.carrello;
    	double somma=0;
    	for (Map.Entry<Integer, ElementoCarrello> entry : elementiCarrello.entrySet()) {
    	    ElementoCarrello elemento = entry.getValue();
    	    VariantiBean variante = elemento.getVariante();
    	    ArticoloBean articolo = elemento.getArticolo();
    	    int quantita = elemento.getQuantita();
    	    somma+=(articolo.getPrezzo() * quantita);
    	    carrelloString += " - Articolo: " + articolo.getNome() + " - Variante: " + variante.getDescrizione() + " - Quantità: " + quantita + " - Prezzo totale: " + (articolo.getPrezzo() * quantita)+' '+'€' + "\n";
    	}
    	double totale=somma+((somma*22)/100);
    	carrelloString+="Totale da Pagare: "+" "+totale+' '+'€'+"\n";
    	
    	int idNuovoPagamento=PagamentoDao.newPagamento("Pagamento", somma,new Date().toString(),idTipoPag);
    	int idOrdine=OrdineDao.newOrdine(new Date().toString(), (somma/100)*22, somma-(somma/100)*22, somma+(somma/100)*22, idNuovoPagamento,idUtente);
    
    	for (Map.Entry<Integer, ElementoCarrello> entry : elementiCarrello.entrySet()) {
    	    ElementoCarrello elemento = entry.getValue();
    	    VariantiBean variante = elemento.getVariante();
    	    ArticoloBean articolo = elemento.getArticolo();
    	    int quantita = elemento.getQuantita();
    	    VoceOrdineDao.newVoceOrdine(quantita, articolo.getPrezzo(), idOrdine, variante.getId());
    	    
    	}
    	return carrelloString;
    }
    
    
    public void addElemento(VariantiBean variante, ArticoloBean articolo, int quantita, int idRow) {
    	
    	ElementoCarrello elemento = new ElementoCarrello(variante,articolo,quantita);
    	
        carrello.put(idRow, elemento);
        
    }
    

}