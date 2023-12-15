import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.time.LocalDateTime;
import java.util.Objects;

public class Veiculo {
	private String placa;

	public UsoDeVaga getUltimoUso() {
		return usos.get(usos.size() - 1);
	}

	private List<UsoDeVaga> usos;

	public Cliente getDonoVeiculo() {
		return donoVeiculo;
	}

	private Cliente donoVeiculo;
	private boolean estacionado;

	public boolean isEstacionado() {
		return estacionado;
	}

	private Cliente cliente;

	public Veiculo(String placa, Cliente donoVeiculo) {
		this.placa = placa;
		this.donoVeiculo = donoVeiculo;
		this.usos = new ArrayList<>();
		this.estacionado = false;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public String getPlaca() {
		return placa;
	}
	public void estacionar(Vaga vaga, LocalDateTime entrada) {
		UsoDeVaga uso = new UsoDeVaga(vaga, entrada);
		usos.add(uso);
		vaga.estacionar();
	}

	public double sair(LocalDateTime saida) {
		UsoDeVaga uso = encontrarUsoMaisRecente();
		if (uso != null) {
			double valorPago = uso.sair(saida, this.donoVeiculo);
			usos.remove(uso);
			return valorPago;
		}else{
			return 0.0; // Veículo não estava estacionado nesta vaga.
		}
	}

	private UsoDeVaga encontrarUsoMaisRecente() {
		return usos.stream().min(Comparator.comparing(UsoDeVaga::getEntrada)).orElse(null);
	}

	public void contratarServico(Servicos servico){
		this.getUltimoUso().contratarServico(servico);
	}

	public double totalArrecadado() {
		return 0d;
	}

	public double arrecadadoNoMes(int mes) {
		return 0d;
	}

	public int totalDeUsos() {
		return usos.size();
	}
}
