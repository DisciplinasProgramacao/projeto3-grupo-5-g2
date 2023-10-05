

public class Cliente {

	private String nome;
	private String id;
	private Veiculo[] veiculos;
	private double totalArrecadado;
	private int usos;
	
	public getArrecadadoTotal(){
		return totalArrecadado;
	}

	public getQtdVeiculos {
		return veiculos;
	}

	public getTotalDeUsos {
		return usos; 	
	}
	
	public Cliente(String nome, String id) {
		this.nome = nome;
		this.id = id;
	}
		
	public void addVeiculo(Veiculo veiculo) {
		veiculos++;
	}

	public Veiculo possuiVeiculo(String placa) {
        return null;
    }

	public int totalDeUsos(int usos) {
        return 0;
    }

	public double arrecadadoPorVeiculo(String placa) {
        return 0;
    }

	public double arrecadadoTotal() {
        return 0;
    }

	public double arrecadadoNoMes(int mes) {
        return 0;
    }

}
