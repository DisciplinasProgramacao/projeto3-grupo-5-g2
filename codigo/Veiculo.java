import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.time.LocalDateTime;
import java.util.Objects;

public class Veiculo {
	private String placa;
	private List<UsoDeVaga> usos;
	private Cliente donoVeiculo;
	private boolean estacionado;

	public Veiculo(String placa, Cliente donoVeiculo) {
		this.placa = placa;
		this.donoVeiculo = donoVeiculo;
		this.usos = new ArrayList<>();
		this.estacionado = false;
	}

	/**
	 * Verifica se o veículo está estacionado
	 * @return boolean "true" caso o veículo esteja estacionado
	 */
	public boolean isEstacionado() {
		return estacionado;
	}

	/**
	 * Obtem o dono do veículo
	 * @return Cliente Dono do veículo
	 */
	public Cliente getDonoVeiculo() {
		return donoVeiculo;
	}

	/**
	 * Obtem a placa do veículo
	 * @return String Placa do veículo
	 */
	public String getPlaca() {
		return placa;
	}

	/**
	 * Obtem o último uso do veículo. (null caso não existam usos)
	 * @return UsoDeVaga Último uso
	 */
	public UsoDeVaga getUltimoUso() {
		if(this.usos.isEmpty()){
			return null;
		} else return usos.get(usos.size() - 1);
	}

	/**
	 * Estaciona o veículo em determinada vaga em determinado dia/hora
	 * @param vaga vaga para estacionar
	 * @param entrada Data/Hora entrada
	 */
	public void estacionar(Vaga vaga, LocalDateTime entrada) {
		UsoDeVaga uso = new UsoDeVaga(vaga, entrada);
		this.estacionado = true;
		usos.add(uso);
		vaga.estacionar();
	}

	/**
	 * Calcula o valor total para ser pago ao sair do estacionamento.
	 * @param saida Horário de saída
	 * @return double Valor total devido
	 * @throws IllegalStateException Caso o veículo não esteja estacionado
	 */
	public double sair(LocalDateTime saida) throws IllegalStateException{
		if(this.isEstacionado()){
			UsoDeVaga uso = encontrarUsoMaisRecente();
			this.estacionado = false;
			return uso.sair(saida, this.donoVeiculo);
		}else{
			throw new IllegalStateException("Veiculo não está estacionado");
		}
	}

	private UsoDeVaga encontrarUsoMaisRecente() {
		return usos.stream().min(Comparator.comparing(UsoDeVaga::getEntrada)).orElse(null);
	}

	public void contratarServico(Servicos servico) throws IllegalStateException{
		UsoDeVaga ultimoUso = this.getUltimoUso();
		if(ultimoUso == null || !ultimoUso.isEmUso()){
			throw new IllegalStateException("Veículo não está estacionado");
		}else this.getUltimoUso().contratarServico(servico);
	}

	public double totalArrecadado() {
		return this.getUsos()
				.stream()
				.mapToDouble(UsoDeVaga::getTotalPago)
				.sum();
	}

	public double arrecadadoNoMes(int mes) {
		return this.getUsos()
				.stream()
				.filter(usoDeVaga -> usoDeVaga.getSaida().getMonth().getValue() == mes && usoDeVaga.getSaida().getYear() == LocalDateTime.now().getYear())
				.mapToDouble(UsoDeVaga::getTotalPago)
				.sum();
	}

	public int totalDeUsos() {
		return usos.size();
	}

	public List<UsoDeVaga> getUsos() {
		return this.usos;
	}
}
