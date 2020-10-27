package t1SimulacaoMetodosAnaliticosEntregaFinal;

public class Evento {

	private String tipo;
	private double tempo;
	private double tempoGlobal;
	private String entrada;
	private String saida;

	public Evento(String tipo, String entrada, String saida, double tempo, double tempoGlocal) {
		this.setTipo(tipo);
		this.setTempo(tempo);
		this.setEntrada(entrada);
		this.setSaida(saida);
		this.setTempoGlobal(tempoGlocal);
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public double getTempo() {
		return tempo;
	}

	public void setTempo(double tempo) {
		this.tempo = tempo;
	}

	public double getTempoGlobal() {
		return tempoGlobal;
	}

	public void setTempoGlobal(double tempoGlobal) {
		this.tempoGlobal = tempoGlobal;
	}

	public String getEntrada() {
		return entrada;
	}

	public void setEntrada(String entrada) {
		this.entrada = entrada;
	}

	public String getSaida() {
		return saida;
	}

	public void setSaida(String saida) {
		this.saida = saida;
	}
}

