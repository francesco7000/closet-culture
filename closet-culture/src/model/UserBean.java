package model;

public class UserBean {

	private String username;
	private String password;
	private String email;
	private String nome;
	private String cognome;
	private String cellulare;
	private String ruolo;
	private boolean valid;
	
	
	private String provincia;
	private String via;
	private String cap;
	private String citta;
	private String numero;
	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username =  username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean newValid) {
		valid = newValid;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getCellulare() {
		return cellulare;
	}

	public void setCellulare(String cellulare) {
		this.cellulare = cellulare;
	}

	public String getRuolo() {
		return ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}

	public String getProvincia() {
		
		return provincia;
	}

	public String getVia() {
		
		return via;
	}

	public String getCap() {
		
		return cap;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public void setVia(String via) {
		this.via = via;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public String getCitta() {
		return citta;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

}
