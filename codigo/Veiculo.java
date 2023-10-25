import java.util.ArrayList;
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

	public double sair(Vaga vaga, LocalDateTime saida) {
		UsoDeVaga uso = encontrarUso(vaga);
		if (uso != null) {
			double valorPago = uso.sair(saida);
			usos.remove(uso);
			return valorPago;
		}
		return 0.0; // Veículo não estava estacionado nesta vaga.
	}

	private UsoDeVaga encontrarUso(Vaga vaga) {
		for (UsoDeVaga uso : usos) {
			if (Objects.equals(uso.getVaga(), vaga)) {
				return uso;
			}
		}
		return null;
	}

	public double totalArrecadado() {
		double total = 0.0;
		for (UsoDeVaga uso : usos) {
			total += uso.getValorPago();
		}
		return total;
	}

	public double arrecadadoNoMes(int mes) {
		double total = 0.0;
		for (UsoDeVaga uso : usos) {
			if (uso.getEntrada().getMonthValue() == mes) {
				total += uso.getValorPago();
			}
		}
		return total;
	}

	public int totalDeUsos() {
		return usos.size();
	}
}
