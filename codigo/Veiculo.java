import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.time.LocalDateTime;
import java.util.Objects;

public class Veiculo {
	private String placa;

	public UsoDeVaga getUltimoUso() {
		if(this.usos.isEmpty()){
			return null;
		} else return usos.get(usos.size() - 1);
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

	public Veiculo(String placa, Cliente donoVeiculo) {
		this.placa = placa;
		this.donoVeiculo = donoVeiculo;
		this.usos = new ArrayList<>();
		this.estacionado = false;
	}

	public String getPlaca() {
		return placa;
	}
	public void estacionar(Vaga vaga, LocalDateTime entrada) {
		UsoDeVaga uso = new UsoDeVaga(vaga, entrada);
		this.estacionado = true;
		usos.add(uso);
		vaga.estacionar();
	}

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
		return 0d;
	}

	public double arrecadadoNoMes(int mes) {
		return 0d;
	}

	public int totalDeUsos() {
		return usos.size();
	}

	public List<UsoDeVaga> getUsos() {
		return this.usos;
	}
}
