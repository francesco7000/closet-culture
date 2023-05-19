package model;

import java.util.Objects;

public class ElementoCarrello {
    private VariantiBean variante;
    private ArticoloBean articolo;
    private int quantita;

    public ElementoCarrello(VariantiBean variante, ArticoloBean articolo, int quantita) {
        this.variante = variante;
        this.articolo = articolo;
        this.quantita = quantita;
    }

    public VariantiBean getVariante() {
        return variante;
    }

    public void setVariante(VariantiBean variante) {
        this.variante = variante;
    }

    public ArticoloBean getArticolo() {
        return articolo;
    }

    public void setArticolo(ArticoloBean articolo) {
        this.articolo = articolo;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ElementoCarrello that = (ElementoCarrello) o;
        return Objects.equals(variante, that.variante) &&
                Objects.equals(articolo, that.articolo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(variante, articolo);
    }
}