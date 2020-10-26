package t1SimulacaoMetodosAnaliticosEntregaFinal;

import java.util.ArrayList;
import java.util.Random;

public class NumerosPseudoAleatorios {

	private Random gerador;
	private ArrayList<Double> lista;

	public NumerosPseudoAleatorios() {
		this.gerador = new Random();
		lista = new ArrayList<Double>();
		lista.add(3.9828);
		lista.add(1.8851);
		lista.add(1.1643);
		lista.add(1.5542);
		lista.add(5.0439);
		lista.add(1.7221);
		lista.add(1.9981);
	}

	public double getNumero() {
		double paraRetornar = gerador.nextDouble();
		return paraRetornar;
	}

	public Double getNumeros(int quantNumeros) {
		return gerador.nextDouble();
	}

	public double getMudancaDeBase(double aleatorioGerado, int intervaloMinimo, int intervaloMaximo) {
		// U(A,B) = (B-A) x U(0,1) + A
		// por partes:
		// U(A,B) = (B-A)
		double paraRetornar = intervaloMaximo - intervaloMinimo;
		// U(A,B) = (B-A) x U(0,1)
		paraRetornar = paraRetornar * aleatorioGerado;
		// U(A,B) = (B-A) x U(0,1) + A
		paraRetornar = paraRetornar + intervaloMinimo;
		return paraRetornar;
	}

	public double getTesteAleatorio() {
		double paraRetornar = lista.get(0);
		lista.remove(0);
		return paraRetornar;
	}

}
