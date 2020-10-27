package t1SimulacaoMetodosAnaliticosEntregaFinal;

public class Main {
	public static void main(String[] args) {
		// G/G/ servidores / capacidade da fila
		System.out.println("Fila 1: G/G/1, chegadas entre 1-4, atendimento entre 1-1.5");

		// corrigir os print dos clientes perdidos

		// media deve ser obtida a partir de quantas execucoes (default=1)
		int media = 1;

		// tempo total de simulacao - total o numero de aleatorios
		int totalAleatorios = 100000;
		//int totalAleatorios = 1000;

		NumerosPseudoAleatorios aleatorios = new NumerosPseudoAleatorios(totalAleatorios);

		// tempo para primeiro cliente
		double tempoPrimeirocliente = 1;

		// numero de servidores
		int numeroDeServidores = 1;

		// capacidade da fila
		int capacidadeDaFila =7;

		// Intervalo de tempo para a chegada de clientes na fila;
		double intervaloDeChegadaMIN = 1;
		double intervaloDeChegadaMAX = 4;

		// Intervalo de tempo de atendimento de um cliente na fila;
		double intervaloDeAtendimentoMIN = 1;
		double intervaloDeAtendimentoMAX = 1.5;

		// implementação de filas simples
		FilaSimples filaSimples1 = new FilaSimples(media, tempoPrimeirocliente, numeroDeServidores, capacidadeDaFila,
				intervaloDeAtendimentoMIN, intervaloDeAtendimentoMAX, intervaloDeChegadaMIN, intervaloDeChegadaMAX);

		// ------------------------------------------------------------------------

		System.out.println("-------------------");
		System.out.println("Fila 2: G/G/3/5, atendimento entre 3..10");

		// numero de servidores
		numeroDeServidores = 3;

		// capacidade da fila
		capacidadeDaFila = 5;

		// Intervalo de tempo de atendimento de um cliente na fila;
		intervaloDeAtendimentoMIN = 5;
		intervaloDeAtendimentoMAX = 10;

		// implementação de filas simples
		FilaSimples filaSimples2 = new FilaSimples(media, tempoPrimeirocliente, numeroDeServidores, capacidadeDaFila,
				intervaloDeAtendimentoMIN, intervaloDeAtendimentoMAX, intervaloDeChegadaMIN, intervaloDeChegadaMAX);

		// ------------------------------------------------------------------------

		System.out.println("-------------------");
		System.out.println("Fila 2: G/G/2/8, atendimento entre 10..20");

		// numero de servidores
		numeroDeServidores = 2;

		// capacidade da fila
		capacidadeDaFila = 8;

		// Intervalo de tempo de atendimento de um cliente na fila;
		intervaloDeAtendimentoMIN = 10;
		intervaloDeAtendimentoMAX = 20;

		// implementação de filas simples
		FilaSimples filaSimples3 = new FilaSimples(media, tempoPrimeirocliente, numeroDeServidores, capacidadeDaFila,
				intervaloDeAtendimentoMIN, intervaloDeAtendimentoMAX, intervaloDeChegadaMIN, intervaloDeChegadaMAX);

		// ------------------------------------------------------------------------

		System.out.println("-------------------");
		
		// probabilidades de f1
		double f1ParaF2 = 0.8;
		double f1ParaF3 = 0.2;

		// probabilidades de f2
		double f2ParaF1 = 0.3;
		double f2ParaF3 = 0.5;
		double f3ParaSaida = 0.2;

		// probabilidades de f3
		double f3ParaF2 = 0.7;
		double f3ParaFSaida = 0.3;

		
		System.out.println("probabilidades de roteamento:");
		System.out.println("fila 1 para fila 2: ");

		System.out.println("-------------------");
		System.out.println("INICIO DA EXECUCAO: ");
		System.out.println("////////////////////////////////////////////");

		// executa com as variaveis inseridas e printa o resultado
		SimulacaoFilaSimples simulacao = new SimulacaoFilaSimples(aleatorios, filaSimples1, filaSimples2, filaSimples3);
		simulacao.exec();

	}
}
