package projeto.enums;

public enum CondicaoPesquisa {
	
	CONTEM("Cont�m"), INICIA("Inicia com"),
	TERMINA_COM("Termina com"), IGUAL_A("Igual");
	
	private String condicao;
	
	private CondicaoPesquisa(String condicao) {
		this.condicao = condicao;
	}
	
	
	public void setCondicao(String condicao) {
		this.condicao = condicao;
	}
	
	public String getCondicao() {
		return condicao;
	}
	
	@Override
	public String toString() {
		return this.condicao;
	}

}
