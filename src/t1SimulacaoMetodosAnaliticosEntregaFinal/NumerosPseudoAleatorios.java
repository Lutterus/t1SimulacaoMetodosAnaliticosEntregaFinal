package t1SimulacaoMetodosAnaliticosEntregaFinal;

import java.util.Random;

public class NumerosPseudoAleatorios {

	private Random gerador;
	private int totalAleatorios = 0;

	public NumerosPseudoAleatorios(int aleatorios) {
		this.gerador = new Random();
		this.totalAleatorios = aleatorios;
	}

	public double getNumeroEntre0e1() {
		consumeAleatorio();
		double paraRetornar = gerador.nextDouble();
		if(paraRetornar<0) {
			System.out.println("deu merdaaaaa");
		}
		return paraRetornar;
	}

	public double getMudancaDeBase(double aleatorioGerado, double A, double B) {
		// U(A,B) = (B-A) x U(0,1) + A
		// por partes:
		// U(A,B) = (B-A)
		double paraRetornar = B - A;
		// U(A,B) = (B-A) x U(0,1)
		paraRetornar = paraRetornar * aleatorioGerado;
		// U(A,B) = (B-A) x U(0,1) + A
		paraRetornar = paraRetornar + A;
		if(paraRetornar<0) {
			System.out.println("deu merdaaaaa");
		}
		return paraRetornar;
	}

	public void consumeAleatorio() {
		totalAleatorios--;
	}

	public int getAleatoriosRestantes() {
		return totalAleatorios;
	}
}
