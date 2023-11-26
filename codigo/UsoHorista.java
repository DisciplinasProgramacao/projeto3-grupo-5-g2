import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import entities.Enums.Servicos;

public class UsoHorista extends UsoDeVaga {

	private static final double FRACAO_USO = 0.25;
	private static final double VALOR_FRACAO = 4.0;
	private static final double VALOR_MAXIMO = 50.0;
	private Vaga vaga;
	private LocalDateTime entrada;
	private LocalDateTime saida;
	private double valorPago;
	private Servicos servicos;
	private boolean saiu;

    public UsoHorista(Vaga vaga) {
        super(vaga);
    }

    public double sair() {
		this.saida = LocalDateTime.now();
		int tempoPermanenciaMinutos = (int) entrada.until(saida, ChronoUnit.MINUTES);
		if (servicos != null) {
			if (tempoPermanenciaMinutos >= servicos.getTempo()) {
				return valorPago() + servicos.getValor();
			}
		}
		return valorPago();
	}

	public boolean ehDoMes(int mes) {
		return this.entrada.getMonthValue() == mes;
	}

	public double valorPago() {
		if (entrada == null || saida == null) {
			throw new IllegalArgumentException("Entrada and Saida cannot be null");
		}

		int calcTempo = (int) entrada.until(saida, ChronoUnit.MINUTES);
		if (calcTempo == 0)
			calcTempo = 1;
		int quantidadeFracoesTempo = (int) Math.ceil(calcTempo / 15.0);
		double valorPago = quantidadeFracoesTempo * VALOR_FRACAO;

		if (valorPago > VALOR_MAXIMO)
			valorPago = VALOR_MAXIMO;

		return valorPago;
	}

	public void contratarServico(Servicos servico) {
		this.servicos = servico;
	}
}
    
