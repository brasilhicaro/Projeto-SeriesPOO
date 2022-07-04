package aquecimento;

public abstract class Canal {
	private String indentificador;
	public Canal(String indentificador) {
		this.indentificador = indentificador;
	}
	public String getIndentificador() {
		return indentificador;
	}
	public String toString() {
		return getIndentificador();
	}
	public boolean equals(Canal c) {
		if(getIndentificador().equals(c.getIndentificador()))
			return true;
		return false;
	}
	
}
