import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.time.LocalDateTime;
import java.util.Objects;

public class Veiculo {
	private String placa;
	private List<UsoDeVaga> usos;
	private LocalDateTime horaEntrada;
	private Cliente cliente;

	public Veiculo(String placa) {
		this.placa = placa;
		this.usos = new ArrayList<>();
	}

	public Cliente getCliente() {
		return cliente;
	}

	public String getPlaca() {
		return placa;
	}

	public LocalDateTime getHoraEntrada() {
		return horaEntrada; // Obtenha a hora de entrada do veículo
	}

	public void estacionar(Vaga vaga, LocalDateTime entrada) {
		UsoDeVaga uso = new UsoDeVaga(vaga, this, entrada);
		usos.add(uso);
		vaga.estacionar(this, uso);
	}

	public double sair(LocalDateTime saida) {
		UsoDeVaga uso = encontrarUsoMaisRecente();
		if (uso != null) {
			double valorPago = uso.sair(saida);
			usos.remove(uso);
			return valorPago;
		}else{
			return 0.0; // Veículo não estava estacionado nesta vaga.
		}
	}

	private UsoDeVaga encontrarUsoMaisRecente() {
		return usos.stream().min(Comparator.comparing(UsoDeVaga::getEntrada)).orElse(null);
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
