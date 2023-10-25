import java.time.LocalDateTime;

/**
 * Classe que representa uma vaga de estacionamento.
 */
public class Vaga {
    private String id;
    private Veiculo veiculo;
    private LocalDateTime horaEntrada; // Hora de entrada do veículo

    /**
     * Construtor para criar uma nova vaga de estacionamento.
     *
     * @param id Identificador único da vaga.
     */
    public Vaga(String id) {
        this.id = id;
        this.veiculo = null;
        this.horaEntrada = null; // Inicialmente, a hora de entrada é nula
    }

    /**
     * Obtém a hora de entrada do veículo na vaga.
     *
     * @return Hora de entrada do veículo.
     */
    public LocalDateTime getHoraEntrada() {
        return horaEntrada;
    }

    /**
     * Obtém o identificador único da vaga.
     *
     * @return Identificador único da vaga.
     */
    public String getId() {
        return id;
    }

    /**
     * Obtém o veículo estacionado na vaga.
     *
     * @return Veículo estacionado na vaga, ou null se a vaga estiver vazia.
     */
    public Veiculo getVeiculo() {
        return veiculo;
    }

    /**
     * Define o veículo que está estacionando na vaga.
     *
     * @param veiculo Veículo a ser estacionado.
     */
    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    /**
     * Verifica se a vaga está disponível (não ocupada por um veículo).
     *
     * @return true se a vaga estiver disponível, caso contrário, false.
     */
    public boolean isDisponivel() {
        return veiculo == null;
    }

    /**
     * Registra o momento em que um veículo estaciona na vaga.
     */
    public void estacionar() {
        // Defina a hora de entrada quando um veículo for estacionado
        horaEntrada = LocalDateTime.now();
    }

    /**
     * Remove o veículo da vaga e calcula o tempo de estacionamento.
     *
     * @return O tempo de estacionamento (em horas).
     */
    public double sair() {
        if (veiculo != null) {
            double tempoEstacionado = calcularTempoEstacionado();
            this.veiculo = null;
            return tempoEstacionado;
        } else {
            return 0.0;
        }
    }

    /**
     * Calcula o tempo de estacionamento com base na hora de entrada e saída.
     *
     * @return O tempo de estacionamento (em horas).
     */
    public double calcularTempoEstacionado() {
        if (horaEntrada != null) {
            LocalDateTime horaSaida = LocalDateTime.now();
            // Calcule a diferença entre a hora de entrada e a hora de saída
            // para obter o tempo estacionado
            // Você pode personalizar a lógica de cálculo conforme necessário
            return calcularDiferencaHoras(horaEntrada, horaSaida);
        } else {
            return 0.0;
        }
    }

    /**
     * Calcula a diferença de horas entre duas datas.
     *
     * @param entrada Hora de entrada.
     * @param saida   Hora de saída.
     * @return A diferença de horas entre a hora de entrada e a hora de saída.
     */
    private double calcularDiferencaHoras(LocalDateTime entrada, LocalDateTime saida) {
        // Cálculo da diferença de horas personalizado
        // Esta implementação retorna a diferença em minutos para fins de exemplo
        long minutos = java.time.Duration.between(entrada, saida).toMinutes();
        return minutos / 60.0; // Converte minutos para horas
    }
}
