package t1SimulacaoMetodosAnaliticosEntregaFinal;

public class SimulacaoFilaSimples {
	private FilaSimples fila1;
	private Escalonador escalonador;
	private FilaSimples fila2;
	private FilaSimples fila3;
	private NumerosPseudoAleatorios aleatorios;

	public SimulacaoFilaSimples(NumerosPseudoAleatorios aleatorios, FilaSimples filaSimples1, FilaSimples filaSimples2,
			FilaSimples filaSimples3) {
		this.fila1 = filaSimples1;
		this.fila2 = filaSimples2;
		this.fila3 = filaSimples3;
		this.aleatorios = aleatorios;
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
			while (aleatorios.getAleatoriosRestantes() > 0) {
				// pega o proximo evento do escalonador
				Evento evento = escalonador.getProximoEvento();

				// certificacao de que valor e valido
				if (evento != null) {
					// contailiza o tempo
					contabilizaTempo(evento);

					// se for uma chegada
					if (evento.getTipo().contentEquals("CHEGADA")) {
						ch1();

						// se for uma transferencia
					} else if (evento.getTipo().contentEquals("TRANSFERENCIA")) {
						// esta saindo de f1
						if (evento.getEntrada().contentEquals("f1")) {
							// se esta indo para f2
							if (evento.getSaida().contentEquals("f2")) {
								p12();
								// se esta indo para f3
							} else {
								p13();
							}

							// se esta saindo de f2
						} else if (evento.getEntrada().contentEquals("f2")) {
							// se esta indo para f1
							if (evento.getSaida().contentEquals("f1")) {
								p21();

								// se esta indo para f3
							} else {
								p23();
							}

							// se esta saindo de f3
						} else {
							// se esta indo para f1
							if (evento.getSaida().contentEquals("f1")) {
								p31();

								// se esta indo para f2
							} else {
								p32();
							}

						}
						// se for uma saida
					} else {
						if (evento.getEntrada().contentEquals("f2")) {
							sa2();
						} else {
							sa3();
						}

					}

				}
			}
			resetaFila();
		}

		printResultado();

	}

	private void ch1() {
		// verifica se ha espaco na fila
		if (fila1.haEspaco()) {
			// aumenta o ponteiro da fila
			fila1.setPonteiro(fila1.getPonteiro() + 1);

			// se a fila eh menor ou igual a quantidade de servidores
			if (fila1.podeAgendar()) {

				// checa probabilidade
				double probabilidade = aleatorios.getNumeroEntre0e1();

				// cria o aleatorio
				double random = aleatorios.getMudancaDeBase(probabilidade, fila1.getAtendimentoMIN(),
						fila1.getAtendimentoMAX());

				if (probabilidade < 0.8) {
					// agenda uma transferencia para fila 2
					escalonador.agendaTransferencia(random, fila1.getTempoGlobal(), "f1", "f2");
				} else {
					// agenda uma transferencia para fila 3
					escalonador.agendaTransferencia(random, fila1.getTempoGlobal(), "f1", "f3");
				}

			}
			// se nao puder
		} else {
			// contabiliza perda de cliente
			fila1.clientePerdido();
		}

		// sempre agenda uma chegada ao final

		// cria o aleatorio
		double random = aleatorios.getMudancaDeBase(aleatorios.getNumeroEntre0e1(), fila1.getChegadaMIN(),
				fila1.getChegadaMAX());
		escalonador.agendaChegada(random, fila1.getTempoGlobal(), "f1", null);
	}

	private void p12() {
		// verifica se eh possivel diminuir a fila
		if (fila1.getPonteiro() > 0) {
			// diminui a fila
			fila1.setPonteiro(fila1.getPonteiro() - 1);
		}

		// se a fila eh maior ou igual a quantidade de servidores
		if (fila1.podeTransferir()) {

			// checa probabilidade
			double probabilidade = aleatorios.getNumeroEntre0e1();

			// cria o aleatorio
			double random = aleatorios.getMudancaDeBase(probabilidade, fila1.getAtendimentoMIN(),
					fila1.getAtendimentoMAX());

			if (probabilidade < 0.8) {
				// agenda uma transferencia para fila 2
				escalonador.agendaTransferencia(random, fila1.getTempoGlobal(), "f1", "f2");
			} else {
				// agenda uma transferencia para fila 3
				escalonador.agendaTransferencia(random, fila1.getTempoGlobal(), "f1", "f3");
			}

		}

		// se existe espaco na fila 2
		// se sim
		if (fila2.haEspaco()) {
			// adiciona a fila
			fila2.setPonteiro(fila2.getPonteiro() + 1);

			// se a fila eh menor ou igual a quantidade de servidores
			if (fila2.podeAgendar()) {
				// checa probabilidade
				double probabilidade = aleatorios.getNumeroEntre0e1();

				// cria um aleatorio
				double random = aleatorios.getMudancaDeBase(aleatorios.getNumeroEntre0e1(), fila2.getAtendimentoMIN(),
						fila2.getAtendimentoMAX());

				if (probabilidade < 0.5) {
					// agenda uma transferencia para fila 2
					escalonador.agendaTransferencia(random, fila2.getTempoGlobal(), "f2", "f3");
				} else if (probabilidade < 0.8) {
					// agenda uma transferencia para fila 1
					escalonador.agendaTransferencia(random, fila2.getTempoGlobal(), "f2", "f1");
				} else {
					// agenda saida
					escalonador.agendaSaida(random, fila2.getTempoGlobal(), "f2", null);
				}

			}
			// se nao
		} else {
			// contabiliza perda
			fila2.clientePerdido();
		}

	}

	private void p13() {
		// verifica se eh possivel diminuir a fila
		if (fila1.getPonteiro() > 0) {
			// diminui a fila
			fila1.setPonteiro(fila1.getPonteiro() - 1);
		}

		// se a fila eh maior ou igual a quantidade de servidores
		if (fila1.podeTransferir()) {

			// checa probabilidade
			double probabilidade = aleatorios.getNumeroEntre0e1();

			// cria o aleatorio
			double random = aleatorios.getMudancaDeBase(probabilidade, fila1.getAtendimentoMIN(),
					fila1.getAtendimentoMAX());

			if (probabilidade < 0.8) {
				// agenda uma transferencia para fila 2
				escalonador.agendaTransferencia(random, fila1.getTempoGlobal(), "f1", "f2");
			} else {
				// agenda uma transferencia para fila 3
				escalonador.agendaTransferencia(random, fila1.getTempoGlobal(), "f1", "f3");
			}

		}

		// se existe espaco na fila 2
		// se sim
		if (fila3.haEspaco()) {
			// adiciona a fila
			fila3.setPonteiro(fila3.getPonteiro() + 1);

			// se a fila eh menor ou igual a quantidade de servidores
			if (fila3.podeAgendar()) {
				// checa probabilidade
				double probabilidade = aleatorios.getNumeroEntre0e1();

				// cria um aleatorio
				double random = aleatorios.getMudancaDeBase(aleatorios.getNumeroEntre0e1(), fila3.getAtendimentoMIN(),
						fila3.getAtendimentoMAX());

				if (probabilidade < 0.7) {
					// agenda uma transferencia para fila 2
					escalonador.agendaTransferencia(random, fila3.getTempoGlobal(), "f3", "f2");
				} else {
					// agenda saida
					escalonador.agendaSaida(random, fila3.getTempoGlobal(), "f3", null);
				}

			}
			// se nao
		} else {
			// contabiliza perda
			fila3.clientePerdido();
		}

	}

	private void p21() {
		// verifica se eh possivel diminuir a fila
		if (fila2.getPonteiro() > 0) {
			// diminui a fila
			fila2.setPonteiro(fila2.getPonteiro() - 1);
		}

		// se a fila eh maior ou igual a quantidade de servidores
		if (fila2.podeTransferir()) {

			// checa probabilidade
			double probabilidade = aleatorios.getNumeroEntre0e1();

			// cria o aleatorio
			double random = aleatorios.getMudancaDeBase(probabilidade, fila2.getAtendimentoMIN(),
					fila2.getAtendimentoMAX());

			if (probabilidade < 0.5) {
				// agenda uma transferencia para fila 2
				escalonador.agendaTransferencia(random, fila2.getTempoGlobal(), "f2", "f3");
			} else if (probabilidade < 0.8) {
				// agenda uma transferencia para fila 1
				escalonador.agendaTransferencia(random, fila2.getTempoGlobal(), "f2", "f1");
			} else {
				// agenda saida
				escalonador.agendaSaida(random, fila2.getTempoGlobal(), "f2", null);
			}

		}

		// se existe espaco na fila 2
		// se sim
		if (fila1.haEspaco()) {
			// adiciona a fila
			fila1.setPonteiro(fila1.getPonteiro() + 1);

			// se a fila eh menor ou igual a quantidade de servidores
			if (fila1.podeAgendar()) {
				// checa probabilidade
				double probabilidade = aleatorios.getNumeroEntre0e1();

				// cria um aleatorio
				double random = aleatorios.getMudancaDeBase(aleatorios.getNumeroEntre0e1(), fila1.getAtendimentoMIN(),
						fila1.getAtendimentoMAX());

				if (probabilidade < 0.8) {
					// agenda uma transferencia para fila 2
					escalonador.agendaTransferencia(random, fila1.getTempoGlobal(), "f1", "f2");
				} else {
					// agenda uma transferencia para fila 3
					escalonador.agendaTransferencia(random, fila1.getTempoGlobal(), "f1", "f3");
				}

			}
			// se nao
		} else {
			// contabiliza perda
			fila1.clientePerdido();
		}

	}

	private void p23() {
		// verifica se eh possivel diminuir a fila
		if (fila2.getPonteiro() > 0) {
			// diminui a fila
			fila2.setPonteiro(fila2.getPonteiro() - 1);
		}

		// se a fila eh maior ou igual a quantidade de servidores
		if (fila2.podeTransferir()) {

			// checa probabilidade
			double probabilidade = aleatorios.getNumeroEntre0e1();

			// cria o aleatorio
			double random = aleatorios.getMudancaDeBase(probabilidade, fila2.getAtendimentoMIN(),
					fila2.getAtendimentoMAX());

			if (probabilidade < 0.5) {
				// agenda uma transferencia para fila 2
				escalonador.agendaTransferencia(random, fila2.getTempoGlobal(), "f2", "f3");
			} else if (probabilidade < 0.8) {
				// agenda uma transferencia para fila 1
				escalonador.agendaTransferencia(random, fila2.getTempoGlobal(), "f2", "f1");
			} else {
				// agenda saida
				escalonador.agendaSaida(random, fila2.getTempoGlobal(), "f2", null);
			}

		}

		// se existe espaco na fila 2
		// se sim
		if (fila3.haEspaco()) {
			// adiciona a fila
			fila3.setPonteiro(fila3.getPonteiro() + 1);

			// se a fila eh menor ou igual a quantidade de servidores
			if (fila3.podeAgendar()) {
				// checa probabilidade
				double probabilidade = aleatorios.getNumeroEntre0e1();

				// cria um aleatorio
				double random = aleatorios.getMudancaDeBase(aleatorios.getNumeroEntre0e1(), fila3.getAtendimentoMIN(),
						fila3.getAtendimentoMAX());

				if (probabilidade < 0.7) {
					// agenda uma transferencia para fila 2
					escalonador.agendaTransferencia(random, fila3.getTempoGlobal(), "f3", "f2");
				} else {
					// agenda saida
					escalonador.agendaSaida(random, fila3.getTempoGlobal(), "f3", null);
				}

			}
			// se nao
		} else {
			// contabiliza perda
			fila3.clientePerdido();
		}

	}

	private void p31() {
		// verifica se eh possivel diminuir a fila
		if (fila3.getPonteiro() > 0) {
			// diminui a fila
			fila3.setPonteiro(fila3.getPonteiro() - 1);
		}

		// se a fila eh maior ou igual a quantidade de servidores
		if (fila3.podeTransferir()) {

			// checa probabilidade
			double probabilidade = aleatorios.getNumeroEntre0e1();

			// cria o aleatorio
			double random = aleatorios.getMudancaDeBase(probabilidade, fila3.getAtendimentoMIN(),
					fila3.getAtendimentoMAX());

			if (probabilidade < 0.5) {
				// agenda uma transferencia para fila 2
				escalonador.agendaTransferencia(random, fila3.getTempoGlobal(), "f2", "f3");
			} else if (probabilidade < 0.8) {
				// agenda uma transferencia para fila 1
				escalonador.agendaTransferencia(random, fila3.getTempoGlobal(), "f2", "f1");
			} else {
				// agenda saida
				escalonador.agendaSaida(random, fila3.getTempoGlobal(), "f2", null);
			}

		}

		// se existe espaco na fila 2
		// se sim
		if (fila1.haEspaco()) {
			// adiciona a fila
			fila1.setPonteiro(fila1.getPonteiro() + 1);

			// se a fila eh menor ou igual a quantidade de servidores
			if (fila1.podeAgendar()) {
				// checa probabilidade
				double probabilidade = aleatorios.getNumeroEntre0e1();

				// cria um aleatorio
				double random = aleatorios.getMudancaDeBase(aleatorios.getNumeroEntre0e1(), fila1.getAtendimentoMIN(),
						fila1.getAtendimentoMAX());

				if (probabilidade < 0.8) {
					// agenda uma transferencia para fila 2
					escalonador.agendaTransferencia(random, fila1.getTempoGlobal(), "f1", "f2");
				} else {
					// agenda uma transferencia para fila 3
					escalonador.agendaTransferencia(random, fila1.getTempoGlobal(), "f1", "f3");
				}

			}
			// se nao
		} else {
			// contabiliza perda
			fila1.clientePerdido();
		}

	}

	private void p32() {
		// verifica se eh possivel diminuir a fila
		if (fila3.getPonteiro() > 0) {
			// diminui a fila
			fila3.setPonteiro(fila3.getPonteiro() - 1);
		}

		// se a fila eh maior ou igual a quantidade de servidores
		if (fila3.podeTransferir()) {

			// checa probabilidade
			double probabilidade = aleatorios.getNumeroEntre0e1();

			// cria o aleatorio
			double random = aleatorios.getMudancaDeBase(probabilidade, fila3.getAtendimentoMIN(),
					fila3.getAtendimentoMAX());

			if (probabilidade < 0.5) {
				// agenda uma transferencia para fila 2
				escalonador.agendaTransferencia(random, fila3.getTempoGlobal(), "f2", "f3");
			} else if (probabilidade < 0.8) {
				// agenda uma transferencia para fila 1
				escalonador.agendaTransferencia(random, fila3.getTempoGlobal(), "f2", "f1");
			} else {
				// agenda saida
				escalonador.agendaSaida(random, fila3.getTempoGlobal(), "f2", null);
			}

		}

		// se existe espaco na fila 2
		// se sim
		if (fila2.haEspaco()) {
			// adiciona a fila
			fila2.setPonteiro(fila2.getPonteiro() + 1);

			// se a fila eh menor ou igual a quantidade de servidores
			if (fila2.podeAgendar()) {
				// checa probabilidade
				double probabilidade = aleatorios.getNumeroEntre0e1();

				// cria um aleatorio
				double random = aleatorios.getMudancaDeBase(aleatorios.getNumeroEntre0e1(), fila2.getAtendimentoMIN(),
						fila2.getAtendimentoMAX());

				if (probabilidade < 0.5) {
					// agenda uma transferencia para fila 2
					escalonador.agendaTransferencia(random, fila2.getTempoGlobal(), "f2", "f3");
				} else if (probabilidade < 0.8) {
					// agenda uma transferencia para fila 1
					escalonador.agendaTransferencia(random, fila2.getTempoGlobal(), "f2", "f1");
				} else {
					// agenda saida
					escalonador.agendaSaida(random, fila2.getTempoGlobal(), "f2", null);
				}

			}
			// se nao
		} else {
			// contabiliza perda
			fila2.clientePerdido();
		}

	}

	private void sa2() {
		// verifica se eh possivel diminuir fila
		if (fila2.getPonteiro() > 0) {
			fila2.setPonteiro(fila2.getPonteiro() - 1);
		}

		// se a fila eh maior ou igual a quantidade de servidores
		if (fila2.podeTransferir()) {
			// checa probabilidade
			double probabilidade = aleatorios.getNumeroEntre0e1();

			// cria um aleatorio
			double random = aleatorios.getMudancaDeBase(aleatorios.getNumeroEntre0e1(), fila2.getAtendimentoMIN(),
					fila2.getAtendimentoMAX());

			if (probabilidade < 0.5) {
				// agenda uma transferencia para fila 2
				escalonador.agendaTransferencia(random, fila2.getTempoGlobal(), "f2", "f3");
			} else if (probabilidade < 0.8) {
				// agenda uma transferencia para fila 1
				escalonador.agendaTransferencia(random, fila2.getTempoGlobal(), "f2", "f1");
			} else {
				// agenda saida
				escalonador.agendaSaida(random, fila2.getTempoGlobal(), "f2", null);
			}
		}

	}

	private void sa3() {
		// verifica se eh possivel diminuir fila
		if (fila3.getPonteiro() > 0) {
			fila3.setPonteiro(fila3.getPonteiro() - 1);
		}

		// se a fila eh maior ou igual a quantidade de servidores
		if (fila3.podeTransferir()) {
			// checa probabilidade
			double probabilidade = aleatorios.getNumeroEntre0e1();

			// cria um aleatorio
			double random = aleatorios.getMudancaDeBase(aleatorios.getNumeroEntre0e1(), fila3.getAtendimentoMIN(),
					fila3.getAtendimentoMAX());

			if (probabilidade < 0.7) {
				// agenda uma transferencia para fila 2
				escalonador.agendaTransferencia(random, fila3.getTempoGlobal(), "f3", "f2");
			} else {
				// agenda saida
				escalonador.agendaSaida(random, fila3.getTempoGlobal(), "f3", null);
			}
		}

	}

	private void preparaExecucao() {
		escalonador.add(new Evento("CHEGADA", "f1", null, 0, fila1.getPrimeirocliente()));

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
		fila3.updateTempoGlobalAnterior();

		// altera o tempo global para tempo global do evento (quando o mesmo ira
		// acontecer)
		fila1.updateTempoGlobal(evento.getTempoGlobal());
		fila2.updateTempoGlobal(evento.getTempoGlobal());
		fila3.updateTempoGlobal(evento.getTempoGlobal());

		// atualiza o tempo no vetor
		fila1.updateTempoFila();
		fila2.updateTempoFila();
		fila3.updateTempoFila();

	}

	private void resetaFila() {
		fila1.resetaFila();
		fila2.resetaFila();
		fila3.resetaFila();
	}

	private void printResultado() {
		// print estado final do vetor e clientes perdidos
		System.out.println("----------------RESULTADO FINAL DA FILA 1----------------");
		fila1.resultadoFinal();
		System.out.println("----------------RESULTADO FINAL DA FILA 2----------------");
		fila2.resultadoFinal();
		System.out.println("----------------RESULTADO FINAL DA FILA 2----------------");
		fila3.resultadoFinal();
	}
}
