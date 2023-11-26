import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Classe que representa o uso de uma vaga de estacionamento por um veículo.
 */
public class UsoDeVaga {

	private static final double FRACAO_USO = 0.25;
	private static final double VALOR_FRACAO = 4.0;
	private static final double VALOR_MAXIMO = 50.0;
	private Vaga vaga;
	private LocalDateTime entrada;
	private LocalDateTime saida;
	private double valorPago;
	private List<Servicos> servicosContratados;

    /**
     * Construtor para criar um novo uso de vaga.
     *
     * @param vaga Vaga que está sendo usada.
     */
    public UsoDeVaga(Vaga vaga) {
        this.vaga = vaga;
        this.entrada = LocalDateTime.now(); // Inicializa a entrada com a data e hora atual
        this.saida = null; // A saída é inicialmente nula, pois o veículo ainda não saiu.
    }

    /**
     * Obtém a data e hora de entrada na vaga.
     *
     * @return Data e hora de entrada na vaga.
     */
    public LocalDateTime getEntrada() {
        return entrada;
    }

    /**
     * Registra a saída do veículo da vaga e calcula o valor a ser pago.
     *
     * @return O valor a ser pago pelo uso da vaga.
     */
    public double sair() {
        if (saida == null) {
            // O veículo ainda não saiu, portanto, defina a saída como a data e hora atual.
            this.saida = LocalDateTime.now();
            Duration duracao = Duration.between(entrada, saida);
            long minutosEstacionado = duracao.toMinutes();
            double valorAPagar = calcularValor(minutosEstacionado);
            valorPago = Math.min(valorAPagar, VALOR_MAXIMO);
        }
        return valorPago;
    }

    /**
     * Obtém o valor pago pelo uso da vaga.
     *
     * @return O valor pago pelo uso da vaga.
     */
    public double valorPago() {
        return valorPago;
    }

    /**
     * Calcula o valor a ser pago com base no tempo de estacionamento.
     *
     * @param minutosEstacionado Tempo de estacionamento em minutos.
     * @return O valor a ser pago pelo uso da vaga.
     */
    private double calcularValor(long minutosEstacionado) {
        double valor = minutosEstacionado * FRACAO_USO * VALOR_FRACAO;
        return valor;
    }
}
