import java.time.LocalDateTime;

public class UsoDeVaga {

	private static final double FRACAO_USO = 0.25;
	private static final double VALOR_FRACAO = 4.0;
	private static final double VALOR_MAXIMO = 50.0;
	private Vaga vaga;
	private LocalDateTime entrada;
	private LocalDateTime saida;
	private double valorPago;

	public UsoDeVaga(Vaga vaga) {
		
	}

	public double sair() {
        return 0;
    }

	public double valorPago() {
        return 0;
    }

}
