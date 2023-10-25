import java.util.ArrayList;
import java.util.List;

public class Estacionamento {

    private String nome;
    private Cliente[] clientes;
    private Vaga[] vagas;
    private int quantFileiras;
    private int vagasPorFileira;
    private List<RegistroEstacionamento> registros;

    public Estacionamento(String nome, int fileiras, int vagasPorFileira) {
        this.nome = nome;
        this.quantFileiras = fileiras;
        this.vagasPorFileira = vagasPorFileira;
        this.clientes = new Cliente[0];
        this.vagas = new Vaga[0];
        this.registros = new ArrayList<>();
        gerarVagas();
    }

    public void addVeiculo(Veiculo veiculo, String idCli) {
        Cliente cliente = getClienteById(idCli);
        if (cliente != null) {
            RegistroEstacionamento registro = new RegistroEstacionamento(veiculo, cliente);
            registros.add(registro);
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    public Veiculo getVeiculo(String placa) {
        for (RegistroEstacionamento registro : registros) {
            if (registro.getVeiculo().getPlaca().equals(placa)) {
                return registro.getVeiculo();
            }
        }
        return null;
    }

    public void addCliente(Cliente cliente) {
        Cliente[] newClientes = new Cliente[clientes.length + 1];
        for (int i = 0; i < clientes.length; i++) {
            newClientes[i] = clientes[i];
        }
        newClientes[clientes.length] = cliente;
        clientes = newClientes;
    }

    private void gerarVagas() {
        int totalVagas = quantFileiras * vagasPorFileira;
        vagas = new Vaga[totalVagas];
        for (int i = 0; i < totalVagas; i++) {
            vagas[i] = new Vaga(i + 1);
        }
    }

    public void estacionar(String placa) {
        Veiculo veiculo = getVeiculo(placa);
        if (veiculo != null) {
            for (Vaga vaga : vagas) {
                if (!vaga.isOcupada()) {
                    vaga.estacionar(veiculo);
                    break;
                }
            }
        } else {
            System.out.println("Veículo não encontrado.");
        }
    }

    public double sair(String placa) {
        Veiculo veiculo = getVeiculo(placa);
        if (veiculo != null) {
            for (Vaga vaga : vagas) {
                if (vaga.isOcupada() && vaga.getVeiculo().equals(veiculo)) {
                    vaga.sair();
                    return vaga.calcularCusto();
                }
            }
        }
        System.out.println("Veículo não encontrado no estacionamento.");
        return 0;
    }

    public double totalArrecadado() {
        double totalArrecadado = 0;
        for (Vaga vaga : vagas) {
            if (!vaga.isOcupada()) {
                totalArrecadado += vaga.getCusto();
            }
        }
        return totalArrecadado;
    }

    public double arrecadacaoNoMes(int mes) {
        double arrecadacaoMensal = 0;
        for (RegistroEstacionamento registro : registros) {
            if (registro.getEntrada().getMonthValue() == mes) {
                arrecadacaoMensal += registro.getVaga().getCusto();
            }
        }
        return arrecadacaoMensal;
    }

    public double valorMedioPorUso() {
        if (registros.size() > 0) {
            double totalCusto = 0;
            for (RegistroEstacionamento registro : registros) {
                totalCusto += registro.getVaga().getCusto();
            }
            return totalCusto / registros.size();
        }
        return 0;
    }

    public String top5Clientes(int mes) {
        // Implemente a lógica para encontrar os top 5 clientes com base no mês especificado
        return "Lista dos top 5 clientes em " + mes;
    }

    private Cliente getClienteById(String idCli) {
        for (Cliente cliente : clientes) {
            if (cliente.getId().equals(idCli)) {
                return cliente;
            }
        }
        return null;
    }
}
