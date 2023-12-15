import java.time.Duration;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe que representa o uso de uma vaga de estacionamento por um veículo.
 */
public class UsoDeVaga {

	public static final double FRACAO_USO = 0.25;
	public static final double VALOR_FRACAO = 4.0;
	public static final double VALOR_MAXIMO = 50.0;

    public Vaga getVaga() {
        return vaga;
    }

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
    public UsoDeVaga(Vaga vaga, LocalDateTime entrada) {
        this.vaga = vaga;
        this.entrada = entrada; // Inicializa a entrada com a data e hora atual
        this.saida = null; // A saída é inicialmente nula, pois o veículo ainda não saiu.
        this.servicosContratados = new LinkedList<>();
    }

    /**
     * Obtém a data e hora de entrada na vaga.
     *
     * @return Data e hora de entrada na vaga.
     */
    public LocalDateTime getEntrada() {
        return entrada;
    }


    public double sair(Cliente cliente) {
        return this.sair(LocalDateTime.now(), cliente);
    }
    /**
     * Registra a saída do veículo da vaga e calcula o valor a ser pago.
     *
     * @param dataSaida Data da saída (optional)
     * @return O valor a ser pago pelo uso da vaga.
     */
    public double sair(LocalDateTime dataSaida, Cliente cliente) {
        return cliente.getCategoriaCliente().sair(this, dataSaida);
    }

    /**
     * Obtém o valor pago pelo uso da vaga.
     *
     * @return O valor pago pelo uso da vaga.
     */
    public double valorPago() {
        return valorPago;
    }

    public void contratarServico(Servicos servico){
        this.servicosContratados.add(servico);
    }
}
