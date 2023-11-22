import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Classe que representa um estacionamento.
 */
public class Estacionamento {
    private String nome;
    private Cliente[] clientes;
    private Vaga[] vagas;
    private int quantFileiras;
    private int vagasPorFileira;
    private List<EstacionamentoRegistro> registros;

    /**
     * Construtor para inicializar o estacionamento.
     *
     * @param nome           Nome do estacionamento.
     * @param fileiras       Número de fileiras de vagas.
     * @param vagasPorFila   Número de vagas por fileira.
     */
    public Estacionamento(String nome, int fileiras, int vagasPorFila) {
        this.nome = nome;
        this.quantFileiras = fileiras;
        this.vagasPorFileira = vagasPorFila;
        this.registros = new ArrayList<>();
        gerarVagas();
    }

    /**
     * Adiciona um cliente ao estacionamento.
     *
     * @param cliente Cliente a ser adicionado.
     */
    public void addCliente(Cliente cliente) {
        if (clientes == null) {
            clientes = new Cliente[1];
            clientes[0] = cliente;
        } else {
            for (Cliente c : clientes) {
                if (c != null && c.getId().equals(cliente.getId())) {
                    return;
                }
            }
            Cliente[] novaLista = new Cliente[clientes.length + 1];
            for (int i = 0; i < clientes.length; i++) {
                novaLista[i] = clientes[i];
            }
            novaLista[clientes.length] = cliente;
            clientes = novaLista;
        }
    }

    /**
     * Gera as vagas do estacionamento com base no número de fileiras e vagas por fileira.
     */
    private void gerarVagas() {
        int totalVagas = quantFileiras * vagasPorFileira;
        vagas = new Vaga[totalVagas];
        char fila = 'A';

        for (int i = 0; i < totalVagas; i++) {
            String id = "Vaga " + fila + (i % vagasPorFileira + 1);
            vagas[i] = new Vaga(id);
            if (i % vagasPorFileira == vagasPorFileira - 1) {
                fila++;
            }
        }
    }

    /**
     * Estaciona um veículo no estacionamento.
     *
     * @param placa       Placa do veículo a ser estacionado.
     * @param horaEntrada Hora de entrada do veículo.
     */
    public void estacionar(String placa, LocalDateTime horaEntrada) {
        for (Vaga vaga : vagas) {
            if (!vaga.isDisponivel() && vaga.getVeiculo() != null && vaga.getVeiculo().getPlaca().equals(placa)) {
                System.out.println("O veículo com a placa " + placa + " já está estacionado na vaga " + vaga.getId());
                return;
            }
        }

        for (Vaga vaga : vagas) {
            if (vaga.isDisponivel()) {
                vaga.estacionar();
                Veiculo veiculo = new Veiculo(placa);
                vaga.setVeiculo(veiculo);

                Cliente cliente = getClienteByPlaca(placa);
                if (cliente != null) {
                    // Registra o uso do estacionamento para o cliente
                    int horasEstacionado = calcularHorasEstacionado(horaEntrada, LocalDateTime.now());
                    double valorPago = calcularValorPago(horasEstacionado);
                    cliente.registrarUsoEstacionamento(this, vaga, valorPago, horasEstacionado);
                }

                System.out.println("Veículo com a placa " + placa + " estacionado na vaga " + vaga.getId());
                return;
            }
        }

        System.out.println("Não há vagas disponíveis para o veículo com a placa " + placa);
    }

    /**
     * Remove um veículo do estacionamento.
     *
     * @param placa      Placa do veículo a ser removido.
     * @param horaSaida  Hora de saída do veículo.
     */
    public void sair(String placa, LocalDateTime horaSaida) {
        Vaga vagaDoVeiculo = null;

        for (Vaga vaga : vagas) {
            if (!vaga.isDisponivel() && vaga.getVeiculo() != null && vaga.getVeiculo().getPlaca().equals(placa)) {
                vagaDoVeiculo = vaga;
                break;
            }
        }

        if (vagaDoVeiculo == null) {
            System.out.println("Veículo com a placa " + placa + " não encontrado no estacionamento.");
        } else {
            LocalDateTime horaEntrada = vagaDoVeiculo.getHoraEntrada();
            int horasEstacionado = calcularHorasEstacionado(horaEntrada, horaSaida);
            double valorPago = calcularValorPago(horasEstacionado);

            System.out.println("Veículo com a placa " + placa + " saiu do estacionamento. Valor pago: R$" + valorPago);

            Cliente cliente = getClienteByPlaca(placa);
            if (cliente != null) {
                EstacionamentoRegistro registro = new EstacionamentoRegistro(this, vagaDoVeiculo, vagaDoVeiculo.getVeiculo(), valorPago, horasEstacionado);
                registros.add(registro);
            }

            vagaDoVeiculo.sair();
        }
    }

    /**
     * Calcula o valor total arrecadado pelo estacionamento.
     *
     * @return Valor total arrecadado.
     */
    public double totalArrecadado() {
        double totalArrecadado = 0.0;
        for (EstacionamentoRegistro registro : registros) {
            totalArrecadado += registro.getValorPago();
        }
        return totalArrecadado;
    }

    /**
     * Calcula a arrecadação no mês especificado.
     *
     * @param mes Mês para o qual calcular a arrecadação.
     * @return Arrecadação mensal.
     */
    public double arrecadacaoNoMes(int mes) {
        double arrecadacaoMensal = 0.0;
        for (EstacionamentoRegistro registro : registros) {
            if (registro.getData().getMonthValue() == mes) {
                arrecadacaoMensal += registro.getValorPago();
            }
        }
        return arrecadacaoMensal;
    }

    /**
     * Calcula o valor médio por uso do estacionamento.
     *
     * @return Valor médio por uso.
     */
    public double valorMedioPorUso() {
        if (registros.isEmpty()) {
            return 0.0;
        }

        double totalArrecadado = totalArrecadado();
        int totalRegistros = registros.size();
        return totalArrecadado / totalRegistros;
    }

    /**
     * Retorna os top 5 clientes com maior arrecadação no mês especificado.
     *
     * @param mes Mês para o qual calcular o ranking de clientes.
     * @return String representando o ranking dos top 5 clientes.
     */
    public String top5Clientes(int mes) {
        List<EstacionamentoRegistro> registrosNoMes = registros.stream()
                .filter(registro -> registro.getData().getMonthValue() == mes)
                .collect(Collectors.toList());

        Map<Cliente, Double> arrecadacaoClientes = new HashMap<>();
        for (EstacionamentoRegistro registro : registrosNoMes) {
            Cliente cliente = getClienteByPlaca(registro.getVeiculo().getPlaca());
            if (cliente != null) {
                double valorPago = registro.getValorPago();
                arrecadacaoClientes.put(cliente, arrecadacaoClientes.getOrDefault(cliente, 0.0) + valorPago);
            }
        }

        List<Cliente> topClientes = arrecadacaoClientes.entrySet()
                .stream()
                .sorted(Map.Entry.<Cliente, Double>comparingByValue().reversed())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        int numClientesNoRanking = Math.min(5, topClientes.size());
        StringBuilder ranking = new StringBuilder("Top 5 Clientes no Mês " + mes + ":\n");
        for (int i = 0; i < numClientesNoRanking; i++) {
            Cliente cliente = topClientes.get(i);
            double arrecadacao = arrecadacaoClientes.get(cliente);
            ranking.append(i + 1).append(". ")
                    .append(cliente.getNome()).append(": R$")
                    .append(arrecadacao).append("\n");
        }

        return ranking.toString();
    }

    /**
     * Obtém um cliente com base na placa do veículo.
     *
     * @param placa Placa do veículo.
     * @return Cliente que possui o veículo com a placa especificada.
     */
    public Cliente getClienteByPlaca(String placa) {
        for (Cliente cliente : clientes) {
            Veiculo veiculo = cliente.possuiVeiculo(placa);
            if (veiculo != null) {
                return cliente;
            }
        }
        return null; // Retorna null se nenhum cliente possuir o veículo
    }

    /**
     * Calcula o valor a ser pago com base no número de horas estacionado.
     *
     * @param horasEstacionado Número de horas estacionado.
     * @return Valor a ser pago.
     */
    private double calcularValorPago(int horasEstacionado) {
        // Por exemplo, um valor fixo de R$ 5 por hora de estacionamento
        double taxaPorHora = 5.0;
        return horasEstacionado * taxaPorHora;
    }

    /**
     * Calcula o número de horas entre a entrada e a saída.
     *
     * @param entrada Hora de entrada.
     * @param saida   Hora de saída.
     * @return Número de horas estacionado.
     */
    private int calcularHorasEstacionado(LocalDateTime entrada, LocalDateTime saida) {
        Duration duracao = Duration.between(entrada, saida);
        return (int) duracao.toHours();
    }
}
