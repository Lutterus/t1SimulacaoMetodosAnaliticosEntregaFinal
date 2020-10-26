package t1SimulacaoMetodosAnaliticosEntregaFinal;

public class Evento {

	private String tipo;
	private double tempo;
	private double tempoGlobal;

	public Evento(String tipo, double tempo, double tempoGlocal) {
		this.setTipo(tipo);
		this.setTempo(tempo);
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
}

