package model;

import java.util.ArrayList;

public class ArticoloBean {
	
		
	int id;
	boolean visibile;
	int sconto;
	double prezzo;
	int quantita;
	String stagione;
	String nome;
	String codice;
	String barcode;
	String composizione;
	String descrizione;
	String url;

	ArrayList<VariantiBean> listaVarianti;
	ArrayList<ColoreBean> listaColori;
	LineaBean linea;
    MaterialeBean materiale;
    CategoriaBean categoria;
    
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public ArrayList<ColoreBean> getListaColori() {
		return listaColori;
	}

	public void setListaColori(ArrayList<ColoreBean> listaColori) {
		this.listaColori = listaColori;
	}

	public LineaBean getLinea() {
		return linea;
	}

	public void setLinea(LineaBean linea) {
		this.linea = linea;
	}

	public MaterialeBean getMateriale() {
		return materiale;
	}

	public void setMateriale(MaterialeBean materiale) {
		this.materiale = materiale;
	}

	public CategoriaBean getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaBean categoria) {
		this.categoria = categoria;
	}

	public ArrayList<VariantiBean> getListaVarianti() {
		return listaVarianti;
	}

	public void setListaVarianti(ArrayList<VariantiBean> listaVarianti) {
		this.listaVarianti = listaVarianti;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public boolean getVisibile() {
		return visibile;
	}

	public void setVisibile(boolean visibile) {
		this.visibile = visibile;
	}
	public int getSconto() {
		return sconto;
	}
	public void setSconto(int sconto) {
		this.sconto = sconto;
	}
	public double getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}
	public int getQuantita() {
		return quantita;
	}
	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
	public String getStagione() {
		return stagione;
	}
	public void setStagione(String stagione) {
		this.stagione = stagione;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCodice() {
		return codice;
	}
	public void setCodice(String codice) {
		this.codice = codice;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getComposizione() {
		return composizione;
	}
	public void setComposizione(String composizione) {
		this.composizione = composizione;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	
}
