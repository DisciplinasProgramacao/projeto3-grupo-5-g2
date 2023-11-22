import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa um cliente do estacionamento.
 */
public class Cliente {
    private String nome;
    private String id;
    private List<Veiculo> veiculos;
    private List<EstacionamentoRegistro> historicoEstacionamentos;

    /**
     * Construtor para criar um novo cliente.
     *
     * @param nome Nome do cliente.
     * @param id   Identificação única do cliente.
     */
    public Cliente(String nome, String id) {
        this.nome = nome;
        this.id = id;
        this.veiculos = new ArrayList<>();
        this.historicoEstacionamentos = new ArrayList<>();
    }

    /**
     * Obtém a identificação única do cliente.
     *
     * @return A identificação única do cliente.
     */
    public String getId() {
        return id;
    }

    /**
     * Obtém o nome do cliente.
     *
     * @return O nome do cliente.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Adiciona um veículo à lista de veículos pertencentes ao cliente.
     *
     * @param veiculo Veículo a ser adicionado.
     */
    public void addVeiculo(Veiculo veiculo) {
        veiculos.add(veiculo);
    }

    /**
     * Verifica se o cliente possui um veículo com a placa especificada.
     *
     * @param placa Placa do veículo.
     * @return O veículo com a placa especificada se o cliente o possuir, caso contrário, retorna null.
     */
    public Veiculo possuiVeiculo(String placa) {
        for (Veiculo veiculo : veiculos) {
            if (veiculo.getPlaca().equals(placa)) {
                return veiculo;
            }
        }
        return null;
    }

    /**
     * Obtém o número total de usos de estacionamento registrados pelo cliente.
     *
     * @return Número total de usos de estacionamento.
     */
    public int totalDeUsos() {
        return historicoEstacionamentos.size();
    }

    /**
     * Calcula o valor total arrecadado pelo cliente com um veículo específico (por placa).
     *
     * @param placa Placa do veículo.
     * @return Valor total arrecadado pelo cliente com o veículo especificado.
     */
    public double arrecadadoPorVeiculo(String placa) {
        double totalArrecadado = 0.0;
        for (EstacionamentoRegistro registro : historicoEstacionamentos) {
            if (registro.getVeiculo().getPlaca().equals(placa)) {
                totalArrecadado += registro.getValorPago();
            }
        }
        return totalArrecadado;
    }

    /**
     * Calcula o valor total arrecadado pelo cliente com todos os seus usos de estacionamento.
     *
     * @return Valor total arrecadado pelo cliente.
     */
    public double arrecadadoTotal() {
        double totalArrecadado = 0.0;
        for (EstacionamentoRegistro registro : historicoEstacionamentos) {
            totalArrecadado += registro.getValorPago();
        }
        return totalArrecadado;
    }

    /**
     * Calcula o valor arrecadado pelo cliente no mês especificado.
     *
     * @param mes Mês para o qual calcular a arrecadação.
     * @return Valor arrecadado pelo cliente no mês especificado.
     */
    public double arrecadadoNoMes(int mes) {
        double totalArrecadado = 0.0;
        for (EstacionamentoRegistro registro : historicoEstacionamentos) {
            if (registro.getData().getMonthValue() == mes) {
                totalArrecadado += registro.getValorPago();
            }
        }
        return totalArrecadado;
    }

    /**
     * Registra um uso de estacionamento para o cliente.
     *
     * @param estacionamento   Estacionamento onde ocorreu o uso.
     * @param vaga             Vaga utilizada pelo veículo.
     * @param valorPago        Valor pago pelo uso.
     * @param horasEstacionado Número de horas em que o veículo esteve estacionado.
     */
    public void registrarUsoEstacionamento(Estacionamento estacionamento, Vaga vaga, double valorPago, int horasEstacionado) {
        Veiculo veiculo = new Veiculo("PlacaDummy"); // Substitua pela lógica de obtenção do veículo correto
        EstacionamentoRegistro registro = new EstacionamentoRegistro(estacionamento, vaga, veiculo, valorPago, horasEstacionado);
        historicoEstacionamentos.add(registro);
    }

    /**
     * Obtém o histórico de usos de estacionamento registrados pelo cliente.
     *
     * @return Lista de registros de uso de estacionamento do cliente.
     */
    public List<EstacionamentoRegistro> obterHistoricoEstacionamentos() {
        return historicoEstacionamentos;
    }
}
