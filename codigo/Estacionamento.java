

public class Estacionamento {

	private String nome;
	private HashMap<>[] clientes;
	private Vaga[] vagas;
	private int quantFileiras;
	private int vagasPorFileira;

	public Estacionamento(String nome, int fileiras, int vagasPorFila) {

	this.nome = nome;
        this.quantFileiras = quantFileiras;
        this.vagasPorFileira = vagasPorFila;
        this.clientes = new HashMap<>();
        this.veiculosEstacionados = new HashMap<>();
        this.vagas = new Vaga[quantFileiras * vagasPorFileira];
        inicializarVagas()
	 }

    private void inicializarVagas() {
        for (int i = 0; i < vagas.length; i++) {
            vagas[i] = new Vaga(i + 1);
        }
		
	}


	public void addVeiculo(Veiculo veiculo, String idCli) {
        Cliente clientes = clientes.get(idCli);
        if (cliente != null) {
            veiculosEstacionados.put(veiculo.getPlaca(), veiculo);
            cliente.adicionarVeiculo(veiculo);
            estacionar(veiculo.getPlaca());
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }
	
	public static void getVeiculo(){
		return veiculo;
	}

	public void addCliente(Cliente cliente) {
		
	}

	private void gerarVagas() {
		
	}

	public void estacionar(String placa) {
		
	}

	public double sair(String placa) {
        return 0;
    }

	public double totalArrecadado() {
        return 0;
    }

	public double arrecadacaoNoMes(int mes) {
        return 0;
    }

	public double valorMedioPorUso() {
        return 0;
    }

	public String top5Clientes(int mes) {
        return null;
    }

}
