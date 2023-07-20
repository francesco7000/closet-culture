package model;

public class OrdineBean {
    private int id;
    private String data;
    private double imposta;
    private double imponibile;
    private double totale;
    private int numOrdine;
    private int idPagamento;
    private int idUtente;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }
    public double getImposta() {
        return imposta;
    }
    public void setImposta(double imposta) {
        this.imposta = imposta;
    }
    public double getTotale() {
        return totale;
    }
    public void setTotale(double totale) {
        this.totale = totale;
    }
    public int getNumOrdine() {
        return numOrdine;
    }
    public void setNumOrdine(int numOrdine) {
        this.numOrdine = numOrdine;
    }
    public int getIdPagamento() {
        return idPagamento;
    }
    public void setIdPagamento(int idPagamento) {
        this.idPagamento = idPagamento;
    }
    public int getIdUtente() {
        return idUtente;
    }
    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }
    public double getImponibile() {
        return imponibile;
    }
    public void setImponibile(double imponibile) {
        this.imponibile = imponibile;
    }

}
