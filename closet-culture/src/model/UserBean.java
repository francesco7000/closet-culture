package model;

public class UserBean {

	private String username;
	private String password;
	private String email;
	private String nome;
	private String cognome;
	private Long cellulare;
	private String ruolo;
	private boolean valid;
	
	

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

	public Long getCellulare() {
		return cellulare;
	}

	public void setCellulare(Long cellulare) {
		this.cellulare = cellulare;
	}

	public String getRuolo() {
		return ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}

}
