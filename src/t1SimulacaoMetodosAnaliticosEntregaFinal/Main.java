package t1SimulacaoMetodosAnaliticosEntregaFinal;

public class Main {
	public static void main(String[] args) {
		// G/G/ servidores / capacidade da fila
		System.out.println("Fila 1: G/G/2/3, chegadas entre 2..3, atendimento entre 2..5");
		
		//corrigir os print dos clientes perdidos

		// media deve ser obtida a partir de quantas execucoes (default=1)
		int media = 5;

		// tempo total de simulacao - total o numero de aleatorios 
		int totalAleatorios = 100000;
		// int totalAleatorios = 7;

		Aleatorios aleatorios = new Aleatorios(totalAleatorios);

		// tempo para primeiro cliente
		double tempoPrimeirocliente = 2.5;

		// numero de servidores
		int numeroDeServidores = 2;

		// capacidade da fila
		int capacidadeDaFila = 3;

		// Intervalo de tempo para a chegada de clientes na fila;
		int intervaloDeChegadaMIN = 2;
		int intervaloDeChegadaMAX = 3;

		// Intervalo de tempo de atendimento de um cliente na fila;
		int intervaloDeAtendimentoMIN = 2;
		int intervaloDeAtendimentoMAX = 5;

		// implementação de filas simples
		FilaSimples filaSimples1 = new FilaSimples(media, aleatorios, tempoPrimeirocliente, numeroDeServidores,
				capacidadeDaFila, intervaloDeAtendimentoMIN, intervaloDeAtendimentoMAX, intervaloDeChegadaMIN,
				intervaloDeChegadaMAX);

		// ------------------------------------------------------------------------

		System.out.println("-------------------");
		System.out.println("Fila 2: G/G/1/3, atendimento entre 3..5");

		// numero de servidores
		numeroDeServidores = 1;

		// Intervalo de tempo de atendimento de um cliente na fila;
		intervaloDeAtendimentoMIN = 3;
		intervaloDeAtendimentoMAX = 5;

		// implementação de filas simples
		FilaSimples filaSimples2 = new FilaSimples(media, aleatorios, tempoPrimeirocliente, numeroDeServidores,
				capacidadeDaFila, intervaloDeAtendimentoMIN, intervaloDeAtendimentoMAX, intervaloDeChegadaMIN,
				intervaloDeChegadaMAX);

		System.out.println("-------------------");
		System.out.println("INICIO DA EXECUCAO: ");
		System.out.println("////////////////////////////////////////////");

		// executa com as variaveis inseridas e printa o resultado
		SimulacaoFilaSimples simulacao = new SimulacaoFilaSimples(filaSimples1, filaSimples2);
		simulacao.exec();

	}
}
