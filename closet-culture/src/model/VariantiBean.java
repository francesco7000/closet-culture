package model;

public class VariantiBean {

	int id;
	String descrizione;
	String codice;
	String barcode;
	int quantita;
	TagliaBean taglia=new TagliaBean();
	ColoreBean colore = new ColoreBean();
	int id_art;
	
	public int getId_art() {
		return id_art;
	}
	public void setId_art(int id_art) {
		this.id_art = id_art;
	}
	
	public TagliaBean getTaglia() {
		return taglia;
	}
	public void setTaglia(TagliaBean taglia) {
		this.taglia = taglia;
	}
	public ColoreBean getColore() {
		return colore;
	}
	public void setColore(ColoreBean colore) {
		this.colore = colore;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
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
	public int getQuantita() {
		return quantita;
	}
	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
	
}
