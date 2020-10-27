package t1SimulacaoMetodosAnaliticosEntregaFinal;

import java.util.ArrayList;

public class Escalonador {

	private ArrayList<Evento> eventos;

	public Escalonador(FilaSimples filaSimples) {
		eventos = new ArrayList<Evento>();
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

	void agendaSaida(double random, double tempoGlobal, String entrada, String saida) {
		// soma dos valores para saber quando ocorrera
		double tempoDoEvento = random;
		// tempo em que o evento ira ocorrer
		double tempoEfetivo = tempoDoEvento + tempoGlobal;
		// guarda o valor
		eventos.add(new Evento("SAIDA", entrada, saida, tempoDoEvento, tempoEfetivo));
	}

	public void agendaChegada(double random, double tempoGlobal, String entrada, String saida) {
		// soma dos valores para saber quando ocorrera
		double tempoDoEvento = random;
		// tempo em que o evento ira ocorrer
		double tempoEfetivo = tempoDoEvento + tempoGlobal;
		// guarda o valor
		eventos.add(new Evento("CHEGADA", entrada, saida, tempoDoEvento, tempoEfetivo));
	}

	public void agendaTransferencia(double random, double tempoGlobal, String entrada, String saida) {
		// soma dos valores para saber quando ocorrera
		double tempoDoEvento = random;
		// tempo em que o evento ira ocorrer
		double tempoEfetivo = tempoDoEvento + tempoGlobal;
		// guarda o valor
		eventos.add(new Evento("TRANSFERENCIA", entrada, saida, tempoDoEvento, tempoEfetivo));

	}

}
