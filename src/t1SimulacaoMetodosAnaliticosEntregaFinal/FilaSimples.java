package t1SimulacaoMetodosAnaliticosEntregaFinal;

public class FilaSimples {
	// resultado final deve ser obtivo a partir do resoltado de quntas iteracoes
	private int media;

	// limite de aleatorios
	private Aleatorios aleatorios;

	// Tempo para primeiro cliente
	private double primeiroCliente;

	// Número de servidores;
	private int numeroDeServidores;

	// Capacidade da fila.
	private int capacidadeDaFila;

	// Intervalo de tempo de atendimento de um cliente na fila;
	private int intervaloDeAtendimentoMIN;
	private int intervaloDeAtendimentoMAX;

	// Intervalo de tempo para a chegada de clientes na fila;
	private int intervaloDeChegadaMIN;
	private int intervaloDeChegadaMAX;

	// tempo global atual
	private double tempoGlobalAtual;

	// tempo global anterior (ultima iteracao)
	private double tempoGlobalAnterior;

	// numero de clientes que nao couberam na fila
	private int clientesPerdidos = 0;

	// fila
	private double vetorFila[];

	// ponteiro para referencia da posicao da fila em que estamos
	private int ponteiroDaFila = 0;

	// fila para calculo final
	private double vetorFinal[];

	// numero de clientes que nao couberam na fila
	private int clientesPerdidosFinal = 0;

	public FilaSimples(int media, Aleatorios aleatorios, double primeiroCliente, int numeroDeServidores,
			int capacidadeDaFila, int intervaloDeAtendimentoMIN, int intervaloDeAtendimentoMAX,
			int intervaloDeChegadaMIN, int intervaloDeChegadaMAX) {
		this.media = media;
		this.aleatorios = aleatorios;
		this.primeiroCliente = primeiroCliente;
		this.numeroDeServidores = numeroDeServidores;
		this.capacidadeDaFila = capacidadeDaFila;
		this.intervaloDeAtendimentoMIN = intervaloDeAtendimentoMIN;
		this.intervaloDeAtendimentoMAX = intervaloDeAtendimentoMAX;
		this.intervaloDeChegadaMIN = intervaloDeChegadaMIN;
		this.intervaloDeChegadaMAX = intervaloDeChegadaMAX;
		this.vetorFila = new double[capacidadeDaFila + 1];
		this.vetorFinal = new double[capacidadeDaFila + 1];
	}

	public int getMedia() {
		return media;
	}

	public int getAleatorios() {
		return aleatorios.getNumAleatorios();
	}

	public void setPonteiro(int valor) {
		ponteiroDaFila = valor;

	}

	public int getPonteiro() {
		return ponteiroDaFila;
	}

	public void clientePerdido() {
		clientesPerdidos++;
	}

	public int getServidores() {
		return numeroDeServidores;
	}

	public int getAtendimentoMIN() {
		return intervaloDeAtendimentoMIN;
	}

	public int getAtendimentoMAX() {
		return intervaloDeAtendimentoMAX;
	}

	public double getTempoGlobal() {
		return tempoGlobalAtual;
	}

	public void setAleatorios() {
		aleatorios.updateAleatorios();
	}

	public int getChegadaMIN() {
		return intervaloDeChegadaMIN;
	}

	public int getChegadaMAX() {
		return intervaloDeChegadaMAX;
	}
	
	public boolean haEspaco() {
		if (ponteiroDaFila < capacidadeDaFila) {
			return true;
		} else {
			return false;
		}
	}

	public boolean podeAgendar() {
		if (ponteiroDaFila <= numeroDeServidores) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean podeTransferir() {
		if (ponteiroDaFila >= numeroDeServidores) {
			return true;
		} else {
			return false;
		}
	}

	public void updateTempoGlobalAnterior() {
		tempoGlobalAnterior = tempoGlobalAtual;

	}

	public void updateTempoGlobal(double tempoGlobal) {
		tempoGlobalAtual = tempoGlobal;

	}

	public void updateTempoFila() {
		// tempo a ser adicionado
		double tempo = tempoGlobalAtual - tempoGlobalAnterior;

		// adiciona o tempo
		vetorFila[ponteiroDaFila] = vetorFila[ponteiroDaFila] + tempo;

	}

	public double getPrimeirocliente() {
		return primeiroCliente;
	}

	public void resultadoFinal() {
		// variavel que guarda o tempo total global de todas as execucoes
		double somaDosTempos = 0;
		for (int i = 0; i < vetorFinal.length; i++) {
			somaDosTempos = somaDosTempos + vetorFinal[i];
		}

		// variavel para usar como reservatorio para o avanco dos calculos
		// feito em partes para garantir a ausencia de erros
		double auxDeCalculo = 0;

		System.out.println("media de clientes perdidos: " + (clientesPerdidosFinal / media));
		System.out.println("tempo total, resultado da soma: " + somaDosTempos);
		somaDosTempos = somaDosTempos / media;
		System.out.println("tempo total dividido pela media: " + somaDosTempos);

		for (int i = 0; i < vetorFinal.length; i++) {
			auxDeCalculo = vetorFinal[i] / media;
			// System.out.println("resultado final é: " + vetorFinal[i]);
			double porcentagem = auxDeCalculo / somaDosTempos;
			porcentagem = porcentagem * 100;
			System.out.println("tempo " + i + ": " + auxDeCalculo + " | %= " + porcentagem + "%");
		}

	}

	public void resetaFila() {
		// guarda a quantidade de clientes perdidos
		clientesPerdidosFinal = clientesPerdidos;
		clientesPerdidos = 0;

		// variavel para verificar se nao houve erro
		double testeDeSeguranca = 0;

		// guarda os estados da fila
		for (int i = 0; i < vetorFila.length; i++) {
			vetorFinal[i] = vetorFinal[i] + vetorFila[i];
			testeDeSeguranca = testeDeSeguranca + vetorFila[i];
			vetorFila[i] = 0;
		}

		// verifica se houve erro
		if (testeDeSeguranca != tempoGlobalAtual) {
			System.out.println("TEMPO DIFERENTES");
			System.out.println("tempo glocal atual: " + tempoGlobalAtual);
			System.out.println("soma das posicoes da fila: " + testeDeSeguranca);
			System.out.println("------------------------");
		}

		tempoGlobalAtual = 0;
		tempoGlobalAnterior = 0;

	}

}
