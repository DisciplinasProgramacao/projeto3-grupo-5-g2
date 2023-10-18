import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa um cliente do sistema de estacionamento da Xulambs Parking.
 */
public class Cliente {
	private String nome;
	private String id;
	private List<Veiculo> veiculos;
	private List<Estacionamento> historicoEstacionamentos;

	// Getters e Setters

	/**
	 * Obtém o nome do cliente.
	 *
	 * @return O nome do cliente.
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Define o nome do cliente.
	 *
	 * @param nome O nome do cliente.
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Obtém o identificador único do cliente.
	 *
	 * @return O identificador único do cliente.
	 */
	public String getId() {
		return id;
	}

	/**
	 * Define o identificador único do cliente.
	 *
	 * @param id O identificador único do cliente.
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Obtém a lista de veículos associados ao cliente.
	 *
	 * @return A lista de veículos associados ao cliente.
	 */
	public List<Veiculo> getVeiculos() {
		return veiculos;
	}

	/**
	 * Define a lista de veículos associados ao cliente.
	 *
	 * @param veiculos A lista de veículos associados ao cliente.
	 */
	public void setVeiculos(List<Veiculo> veiculos) {
		this.veiculos = veiculos;
	}

	/**
	 * Obtém o histórico de estacionamentos do cliente.
	 *
	 * @return O histórico de estacionamentos do cliente.
	 */
	public List<Estacionamento> getHistoricoEstacionamentos() {
		return historicoEstacionamentos;
	}

	/**
	 * Define o histórico de estacionamentos do cliente.
	 *
	 * @param historicoEstacionamentos O histórico de estacionamentos do cliente.
	 */
	public void setHistoricoEstacionamentos(List<Estacionamento> historicoEstacionamentos) {
		this.historicoEstacionamentos = historicoEstacionamentos;
	}

	/**
	 * Construtor para a classe Cliente.
	 *
	 * @param nome O nome do cliente.
	 * @param id   O identificador único do cliente.
	 */
	public Cliente(String nome, String id) {
		this.nome = nome;
		this.id = id;
		this.veiculos = new ArrayList<>();
		this.historicoEstacionamentos = new ArrayList<>();
	}

	/**
	 * Adiciona um veículo à lista de veículos do cliente.
	 *
	 * @param veiculo O veículo a ser adicionado.
	 */
	public void addVeiculo(Veiculo veiculo) {
		veiculos.add(veiculo);
	}

	/**
	 * Verifica se o cliente possui um veículo com a placa especificada.
	 *
	 * @param placa A placa do veículo a ser verificado.
	 * @return O veículo com a placa especificada ou null se não encontrado.
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
	 * Calcula o número total de usos do estacionamento pelo cliente.
	 *
	 * @return O número total de usos do estacionamento.
	 */
	public int totalDeUsos() {
		return historicoEstacionamentos.size();
	}

	/**
	 * Calcula o valor arrecadado por um veículo com a placa especificada.
	 *
	 * @param placa A placa do veículo.
	 * @return O valor arrecadado pelo veículo com a placa especificada.
	 */
	public double arrecadadoPorVeiculo(String placa) {
		double totalArrecadado = 0.0;
		for (Estacionamento estacionamento : historicoEstacionamentos) {
			if (estacionamento.getVeiculo().getPlaca().equals(placa)) {
				totalArrecadado += estacionamento.getValorPago();
			}
		}
		return totalArrecadado;
	}

	/**
	 * Calcula o valor total arrecadado pelo cliente.
	 *
	 * @return O valor total arrecadado pelo cliente.
	 */
	public double arrecadadoTotal() {
		double totalArrecadado = 0.0;
		for (Estacionamento estacionamento : historicoEstacionamentos) {
			totalArrecadado += estacionamento.getValorPago();
		}
		return totalArrecadado;
	}

	/**
	 * Calcula o valor arrecadado pelo cliente em um mês específico.
	 *
	 * @param mes O mês para o qual deseja calcular o valor arrecadado.
	 * @return O valor arrecadado pelo cliente no mês especificado.
	 */
	public double arrecadadoNoMes(int mes) {
		double totalArrecadado = 0.0;
		for (Estacionamento estacionamento : historicoEstacionamentos) {
			if (estacionamento.getData().getMonthValue() == mes) {
				totalArrecadado += estacionamento.getValorPago();
			}
		}
		return totalArrecadado;
	}

	// Métodos para registrar o uso do estacionamento (não implementados aqui)
	public void registrarEstacionamento(Estacionamento estacionamento) {
		historicoEstacionamentos.add(estacionamento);
	}
}
