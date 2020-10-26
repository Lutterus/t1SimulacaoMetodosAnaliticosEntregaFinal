package t1SimulacaoMetodosAnaliticosEntregaFinal;

public class SimulacaoFilaSimples {
	private FilaSimples fila1;
	private Escalonador escalonador;
	private FilaSimples fila2;

	public SimulacaoFilaSimples(FilaSimples filaSimples1, FilaSimples filaSimples2) {
		this.fila1 = filaSimples1;
		this.fila2 = filaSimples2;
		escalonador = new Escalonador(fila1);

	}

	public void exec() {
		// usa como referencia a media apenas a primeira fila
		// assumimos que a media de execuacao e SEMPRE a mesma em TODAS as filas
		// adicionas]

		for (int i = 0; i < fila1.getMedia(); i++) {
			// insere o primeiro cliente em todos os ecalonadores de todas as filas
			preparaExecucao();

			// a quantidade de aleatorios eh um numero independente e que pode ser alcancado
			// a partir de qualquer fila
			while (fila1.getAleatorios() >= 0) {
				// pega o proximo evento do escalonador
				Evento evento = escalonador.getProximoEvento();

				// certificacao de que valor e valido
				if (evento != null) {
					// contailiza o tempo
					contabilizaTempo(evento);

					// se for uma chegada
					if (evento.getTipo().contentEquals("CHEGADA")) {
						ch1(evento);

						// se for uma transferencia
					} else if (evento.getTipo().contentEquals("TRANSFERENCIA")) {
						p12(evento);

						// se for uma saida
					} else {
						sa2();
					}

				}
			}
			resetaFila();
		}

		printResultado();

	}

	private void sa2() {
		// verifica se eh possivel diminuir fila
		if (fila2.getPonteiro() > 0) {
			fila2.setPonteiro(fila2.getPonteiro() - 1);
		}

		// se a fila eh maior ou igual a quantidade de servidores
		if (fila2.podeTransferir()) {
			escalonador.agendaSaida(fila2.getAtendimentoMIN(), fila2.getAtendimentoMAX(), fila2.getTempoGlobal());
		}

	}

	private void p12(Evento evento) {
		// verifica se eh possivel diminuir a fila
		if (fila1.getPonteiro() > 0) {
			// diminui a fila
			fila1.setPonteiro(fila1.getPonteiro() - 1);
		}

		// se a fila eh maior ou igual a quantidade de servidores
		if (fila1.podeTransferir()) {
			escalonador.agendaTransferencia(fila1.getAtendimentoMIN(), fila1.getAtendimentoMAX(),
					fila1.getTempoGlobal());
		}

		// se existe espaco na fila 2
		// se sim
		if (fila2.haEspaco()) {
			// adiciona a fila
			fila2.setPonteiro(fila2.getPonteiro() + 1);

			// se a fila eh menor ou igual a quantidade de servidores
			if (fila2.podeAgendar()) {
				// agenda um sa3
				escalonador.agendaSaida(fila2.getAtendimentoMIN(), fila2.getAtendimentoMAX(), fila2.getTempoGlobal());
			}
			// se nao
		} else {
			// contabiliza perda
			fila2.clientePerdido();
		}

	}

	private void ch1(Evento evento) {
		// verifica se ha espaco na fila
		if (fila1.haEspaco()) {
			// aumenta o ponteiro da fila
			fila1.setPonteiro(fila1.getPonteiro() + 1);

			// se a fila eh menor ou igual a quantidade de servidores
			if (fila1.podeAgendar()) {
				// agenda uma saida
				escalonador.agendaTransferencia(fila1.getAtendimentoMIN(), fila1.getAtendimentoMAX(),
						fila1.getTempoGlobal());
			}
			// se nao puder
		} else {
			// contabiliza perda de cliente
			fila1.clientePerdido();
		}

		// sempre agenda uma chegada ao final
		escalonador.agendaChegada(fila1.getChegadaMIN(), fila1.getChegadaMAX(), fila1.getTempoGlobal());
	}

	private void preparaExecucao() {
		escalonador.add(new Evento("CHEGADA", 0, fila1.getPrimeirocliente()));

	}

	private void contabilizaTempo(Evento evento) {
		// tempo atual - tempo anterior
		// tempo atual = tempo do evento
		// tempo anterior = tempo global desatualizado

		// entao:
		// altera o tempo global anterior para o tempo global atual (quando aconteceu o
		// ultimo evento)
		fila1.updateTempoGlobalAnterior();
		fila2.updateTempoGlobalAnterior();

		// altera o tempo global para tempo global do evento (quando o mesmo ira
		// acontecer)
		fila1.updateTempoGlobal(evento.getTempoGlobal());
		fila2.updateTempoGlobal(evento.getTempoGlobal());

		// atualiza o tempo no vetor
		fila1.updateTempoFila();
		fila2.updateTempoFila();

	}

	private void resetaFila() {
		fila1.resetaFila();
		fila2.resetaFila();
	}

	private void printResultado() {
		// print estado final do vetor e clientes perdidos
		System.out.println("----------------RESULTADO FINAL DA FILA 1----------------");
		fila1.resultadoFinal();
		System.out.println("----------------RESULTADO FINAL DA FILA 2----------------");
		fila2.resultadoFinal();
	}
}
