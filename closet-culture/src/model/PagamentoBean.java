package model;


public class PagamentoBean {

	
	//tipo pagamento
	private int tp_id;
	private String tp_nome;
	private String tp_descrizione;
	
	//pagamento
	private int id;
	private String nome;
	private double importo;
	private String data;
	private int id_tipoPag;
	
	
	public String getTp_descrizione() {
		return tp_descrizione;
	}

	public void setTp_descrizione(String tp_descrizione) {
		this.tp_descrizione = tp_descrizione;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getImporto() {
		return importo;
	}

	public void setImporto(double importo) {
		this.importo = importo;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public int getId_tipoPag() {
		return id_tipoPag;
	}

	public void setId_tipoPag(int id_tipoPag) {
		this.id_tipoPag = id_tipoPag;
	}
		

	public int getTp_id() {
		return tp_id;
	}

	public void setTp_id(int tp_id) {
		this.tp_id = tp_id;
	}

	public String getTp_nome() {
		return tp_nome;
	}

	public void setTp_nome(String tp_nome) {
		this.tp_nome = tp_nome;
	}
	
}
