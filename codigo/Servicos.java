/**
 * Classe que representa um serviço oferecido nos estacionamentos.
 */
public abstract class Servicos {
    private String nome;
    private double valor;
    private double tempoMinimoPermanenciaHoras;

    /**
     * Construtor da classe Servico.
     *
     * @param nome                       O nome do serviço.
     * @param valor                      O valor do serviço.
     * @param tempoMinimoPermanenciaHoras O tempo mínimo de permanência em horas para aplicação do serviço.
     */
    public Servicos(String nome, double valor, double tempoMinimoPermanenciaHoras) {
        this.nome = nome;
        this.valor = valor;
        this.tempoMinimoPermanenciaHoras = tempoMinimoPermanenciaHoras;
    }

    /**
     * Calcula o custo do serviço com base no tempo de permanência.
     *
     * @param horasEstacionado O tempo de permanência em horas.
     * @return O custo do serviço.
     */
    public double calcularCustoServico(double horasEstacionado) {
        if (horasEstacionado < tempoMinimoPermanenciaHoras) {
            return 0.0; // O serviço é gratuito se o tempo mínimo não for atingido
        }
        return valor;
    }

    /**
     * Obtém o nome do serviço.
     *
     * @return O nome do serviço.
     */
    public String getNome() {
        return nome;
    }
}

