package t1SimulacaoMetodosAnaliticosEntregaFinal;

import java.util.ArrayList;

public class Escalonador {

	private NumerosPseudoAleatorios numerosPseudoAleatorios;
	private ArrayList<Evento> eventos;
	private FilaSimples filaSimples;

	public Escalonador(FilaSimples filaSimples) {
		eventos = new ArrayList<Evento>();
		// implementação do gerador de numeros pseudo-aleatorios
		numerosPseudoAleatorios = new NumerosPseudoAleatorios();
		this.filaSimples = filaSimples;
	}

	public void add(Evento evento) {
		eventos.add(evento);
	}

	public Evento getProximoEvento() {
		// verifica se o vetor possui proximo evento
		if (eventos.size() > 0) {
			// procura o evento com menor tempo
			int index = getMenorTempo();
			// recupera o evento
			Evento eventoParaRetornor = eventos.get(index);
			// remove ele do arraylist
			eventos.remove(index);
			// retorna-o
			return eventoParaRetornor;
		} else {
			return null;
		}

	}

	private int getMenorTempo() {
		if (eventos.size() == 1) {
			return 0;
		} else {
			int index = 0;
			int i = 0;
			for (Evento evento : eventos) {
				if (evento.getTempoGlobal() < eventos.get(index).getTempoGlobal()) {
					index = i;
				}
				i++;
			}
			return index;
		}
	}

	void agendaSaida(int min, int max, double tempoGlobal) {
		// consome um aleatorio do numero limite
		filaSimples.setAleatorios();
		// geracao de um numero aleatorio
		double numero = numerosPseudoAleatorios.getNumero();
		// geracao de um numero dentro do intervalo
		double mudancaDeBase = numerosPseudoAleatorios.getMudancaDeBase(numero, min, max);
		// soma dos valores para saber quando ocorrera
		double tempoDoEvento = mudancaDeBase;
		// tempo em que o evento ira ocorrer
		double tempoEfetivo = tempoDoEvento + tempoGlobal;
		// guarda o valor
		eventos.add(new Evento("SAIDA", tempoDoEvento, tempoEfetivo));
	}

	public void agendaChegada(int min, int max, double tempoGlobal) {
		// consome um aleatorio do numero limite
		filaSimples.setAleatorios();
		// geracao de um numero aleatorio
		double numero = numerosPseudoAleatorios.getNumero();
		// geracao de um numero dentro do intervalo
		double mudancaDeBase = numerosPseudoAleatorios.getMudancaDeBase(numero, min, max);
		// soma dos valores para saber quando ocorrera
		double tempoDoEvento = mudancaDeBase;
		// tempo em que o evento ira ocorrer
		double tempoEfetivo = tempoDoEvento + tempoGlobal;
		// guarda o valor
		eventos.add(new Evento("CHEGADA", tempoDoEvento, tempoEfetivo));
	}

	public void agendaTransferencia(int atendimentoMIN, int atendimentoMAX, double tempoGlobal) {
		// consome um aleatorio do numero limite
		filaSimples.setAleatorios();
		// geracao de um numero aleatorio
		double numero = numerosPseudoAleatorios.getNumero();
		// geracao de um numero dentro do intervalo
		double mudancaDeBase = numerosPseudoAleatorios.getMudancaDeBase(numero, atendimentoMIN, atendimentoMAX);
		// soma dos valores para saber quando ocorrera
		double tempoDoEvento = mudancaDeBase;
		// tempo em que o evento ira ocorrer
		double tempoEfetivo = tempoDoEvento + tempoGlobal;
		// guarda o valor
		eventos.add(new Evento("TRANSFERENCIA", tempoDoEvento, tempoEfetivo));

	}

}

