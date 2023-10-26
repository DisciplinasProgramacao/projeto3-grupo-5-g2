import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class UsoDeVaga {

	private static final double FRACAO_USO = 0.25;
	private static final double VALOR_FRACAO = 4.0;
	private static final double VALOR_MAXIMO = 50.0;
	private Vaga vaga;
	private LocalDateTime entrada;
	private LocalDateTime saida;
	private double valorPago;
	private List<Servicos> servicosContratados;

	public UsoDeVaga(Vaga vaga) {
		this.vaga = vaga;
		this.entrada = LocalDateTime.now();
		this.servicosContratados = new ArrayList<Servicos>();
    }

	public double sair() {
		return this.sair(LocalDateTime.now());
	}

	public double sair(LocalDateTime horarioSaida) {
		this.saida = horarioSaida;
		long totalMinutos = ChronoUnit.MINUTES.between(this.entrada, this.saida);
		double totalHoras = (double) totalMinutos /60;

		double valorTotal = 0d;
		for(Servicos servico:this.servicosContratados){
			valorTotal += servico.calcularCustoServico(totalHoras);
		}

		valorTotal += Math.min((totalHoras/FRACAO_USO) * VALOR_FRACAO, VALOR_MAXIMO);
		this.valorPago = valorTotal;
		return valorTotal;
    }

	public double valorPago() {
        return valorPago;
    }

	public void contratarServico(Servicos servico){
		this.servicosContratados.add(servico);
	}

}
