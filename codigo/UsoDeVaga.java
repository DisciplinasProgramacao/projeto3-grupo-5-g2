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

    public LocalDateTime getSaida() {
        return saida;
    }

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
        this.valorPago = 0d;
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
        this.getVaga().sair();
        this.valorPago = cliente.getCategoriaCliente().sair(this, dataSaida);
        this.saida = dataSaida;
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
     * Contratar serviço e adicionar na lista de serviços no UsoDeVaga.
     *
     * @param servico Serviço à ser contratado
     * @return void
     */
    public void contratarServico(Servicos servico){
        this.servicosContratados.add(servico);
    }

    /**
     * Retorna o valor total dos serviços que puderam ser cumpridos.
     *
     * @param horarioSaida Horário de saída para verificação do tempo dos serviços
     * @return double Valor total dos serviços
     */
    public double getValorServicos(LocalDateTime horarioSaida) {
        long tempoGastoSegundos = Duration.between(this.getEntrada(), horarioSaida).getSeconds();
        double tempoGastoHoras = ((double) tempoGastoSegundos) / (60 * 60);
        return this.servicosContratados.stream().filter(servico -> servico.getTempo() < tempoGastoHoras).map(Servicos::getValor).reduce(0d, Double::sum);
    }

    /**
     * Calcula o número de horas entre a entrada e a saída.
     *
     * @return Número de horas estacionado.
     * @throws IllegalStateException Erro caso o veículo ainda esteja estacionado
     */
    public double getHorasUso(){
        if(this.isEmUso()){
            throw new IllegalStateException("Veículo ainda não saiu");
        }else return this.getHorasUso(this.getSaida());
    }

    /**
     * Calcula o número de horas entre a entrada e a saída.
     *
     * @param horarioSaida   Hora de saída.
     * @return Número de horas estacionado.
     */
    public double getHorasUso(LocalDateTime horarioSaida) {
        double tempoGastoHoras = (double) Duration.between(this.getEntrada(), horarioSaida).toHours();
        return tempoGastoHoras;
    }

    /**
     * Retorna a lista de serviços do UsoDeVaga.
     *
     * @return Lista de serviços do UsoDeVaga.
     */
    public List<Servicos> getServicos() {
        return this.servicosContratados;
    }

    /**
     * Retorna o valor total pago no Uso.
     *
     * @return Valor total pago no Uso até então
     */
    public double getTotalPago() {
        return this.valorPago();
    }

    /**
     * Verifica se o veículo está estacionado.
     *
     * @return boolean "true" caso o veículo não tenha saído do estacionamento ainda
     */
    public boolean isEmUso(){
        return this.getSaida() == null;
    }
}
