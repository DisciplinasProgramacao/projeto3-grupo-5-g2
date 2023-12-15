import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa um cliente do estacionamento.
 */
public class Cliente {
    private String nome;
    private String id;
    private List<Veiculo> veiculos;

    public CategoriaCliente getCategoriaCliente() {
        return categoriaCliente;
    }

    private CategoriaCliente categoriaCliente;

    /**
     * Construtor para criar um novo cliente.
     *
     * @param id           Identificação única do cliente.
     * @param nome         Nome do cliente.
     */
    public Cliente(String id, String nome, CategoriaCliente tipoCliente) {
        this.nome = nome;
        this.id = id;
        this.veiculos = new ArrayList<>();
        this.categoriaCliente = tipoCliente;
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
    public Veiculo obterVeiculo(String placa) {
        return veiculos.stream().filter(veiculo -> veiculo.getPlaca().equals(placa)).findFirst().orElse(null);
    }

    /**
     * Obtém o número total de usos de estacionamento registrados pelo cliente.
     *
     * @return Número total de usos de estacionamento.
     */
    public int totalDeUsos() {
        return 0;
    }

    /**
     * Calcula o valor total arrecadado pelo cliente com um veículo específico (por placa).
     *
     * @param placa Placa do veículo.
     * @return Valor total arrecadado pelo cliente com o veículo especificado.
     */
    public double arrecadadoPorVeiculo(String placa) {
        return 0d;
    }

    /**
     * Calcula o valor total arrecadado pelo cliente com todos os seus usos de estacionamento.
     *
     * @return Valor total arrecadado pelo cliente.
     */
    public double arrecadadoTotal() {
        return 0d;
    }

    /**
     * Calcula o valor arrecadado pelo cliente no mês especificado.
     *
     * @param mes Mês para o qual calcular a arrecadação.
     * @return Valor arrecadado pelo cliente no mês especificado.
     */
    public double arrecadadoNoMes(int mes) {
        return 0d;
    }

    public Veiculo getVeiculoByPlaca(String placa){
        return this.veiculos.stream().filter(veiculo -> veiculo.getPlaca().equals(placa)).findFirst().orElse(null);
    }
}
