package model;

public class Telefone {
	
	private long id;
	private String numero;
	private String tipo;
	private long usuariopessoa;
	
	public Telefone() {
		
	}
	
	public Telefone(long id, String numero, String tipo, long usuariopessoa) {
		super();
		this.id = id;
		this.numero = numero;
		this.tipo = tipo;
		this.usuariopessoa = usuariopessoa;
	}
		
	
	@Override
	public String toString() {
		return "Telefone [id=" + id + ", numero=" + numero + ", tipo=" + tipo + ", usuariopessoa=" + usuariopessoa
				+ "]";
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public long getUsuariopessoa() {
		return usuariopessoa;
	}
	public void setUsuariopessoa(long usuariopessoa) {
		this.usuariopessoa = usuariopessoa;
	}
	
	

}
