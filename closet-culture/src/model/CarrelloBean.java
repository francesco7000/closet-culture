package model;

import java.util.*;

public class CarrelloBean {
    
    private int idUtente;
    
    private Map<Map<VariantiBean, ArticoloBean>, Integer> carrello;

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

    public Map<Map<VariantiBean, ArticoloBean>, Integer> getCarrello() {
        return carrello;
    }

    public void setCarrello(Map<Map<VariantiBean, ArticoloBean>, Integer> carrello) {
        this.carrello = carrello;
    }

    public void addElemento(VariantiBean variante, ArticoloBean articolo, int quantita) {
        Map<VariantiBean, ArticoloBean> elemento = new HashMap<>();
        elemento.put(variante, articolo);
        if (carrello.containsKey(elemento)) {
            int nuovaQuantita = carrello.get(elemento) + quantita;
            carrello.put(elemento, nuovaQuantita);
        } else {
            carrello.put(elemento, quantita);
        }
    }

    public void removeElemento(VariantiBean variante, ArticoloBean articolo, int quantita) {
        Map<VariantiBean, ArticoloBean> elemento = new HashMap<>();
        elemento.put(variante, articolo);
        if (carrello.containsKey(elemento)) {
            int nuovaQuantita = carrello.get(elemento) - quantita;
            if (nuovaQuantita <= 0) {
                carrello.remove(elemento);
            } else {
                carrello.put(elemento, nuovaQuantita);
            }
        }
    }

    public int getQuantita(VariantiBean variante, ArticoloBean articolo) {
        Map<VariantiBean, ArticoloBean> elemento = new HashMap<>();
        elemento.put(variante, articolo);
        return carrello.getOrDefault(elemento, 0);
    }

    public boolean getElemento(VariantiBean variante, ArticoloBean articolo) {
        Map<VariantiBean, ArticoloBean> elemento = new HashMap<>();
        elemento.put(variante, articolo);
        return carrello.containsKey(elemento);
    }
}