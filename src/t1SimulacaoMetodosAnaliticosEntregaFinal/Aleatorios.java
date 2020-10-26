package t1SimulacaoMetodosAnaliticosEntregaFinal;

public class Aleatorios {
	private int numAleatorios;
	private int backupAleatorios;

	public Aleatorios(int aleatorios) {
		this.setNumAleatorios(aleatorios);
	}

	public int getNumAleatorios() {
		return numAleatorios;
	}

	public void setNumAleatorios(int numAleatorios) {
		this.numAleatorios = numAleatorios;
		this.backupAleatorios = numAleatorios;
	}

	public void updateAleatorios() {
		this.numAleatorios--;
	}

	public void resetAleatorios() {
		this.numAleatorios = backupAleatorios;
	}
}
