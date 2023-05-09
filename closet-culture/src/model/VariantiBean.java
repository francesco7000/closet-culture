package model;

public class VariantiBean {

	int id;
	String descrizione;
	String codice;
	String barcode;
	float quantita;
	TagliaBean taglia=new TagliaBean();
	ColoreBean colore = new ColoreBean();
	
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
	public float getQuantita() {
		return quantita;
	}
	public void setQuantita(float quantita) {
		this.quantita = quantita;
	}
	
}
