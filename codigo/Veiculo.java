import java.util.ArrayList;
import java.util.List;

public class Veiculo {

	private String placa;
	private List<UsoDeVaga> usos;
	private UsoDeVaga usoDeVagaAtual;

	public Veiculo(String placa) {
		this.placa = placa;
		this.usos = new ArrayList<>();
		this.usoDeVagaAtual = null;
	}

	public void estacionar(Vaga vaga) {
		vaga.estacionar();
		UsoDeVaga usoVaga = new UsoDeVaga(vaga);
		this.usos.add(usoVaga);
	}

	public void contratarServico(Servicos servico){
		this.usoDeVagaAtual.contratarServico(servico);
	}

	public double sair() {
		if(usoDeVagaAtual == null){
			return 0;
		}else{
			double totalAPagar =  usoDeVagaAtual.sair();
        	usoDeVagaAtual = null;
			return  totalAPagar;
		}
    }

	public double totalArrecadado() {
        double totalArrecadado = 0d;
		for(UsoDeVaga usoVaga:this.usos){
			totalArrecadado += usoVaga.valorPago();
		}

		return totalArrecadado;
    }

	public double arrecadadoNoMes(int mes) {
        return 0;
    }

	public int totalDeUsos() {
        return 0;
    }

}
