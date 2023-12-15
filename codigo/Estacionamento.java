import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Classe que representa um estacionamento.
 */
public class Estacionamento {
    private String nome;

    public List<Cliente> getClientes() {
        return clientes;
    }

    public Cliente getClienteById(String id){
        return this.getClientes().stream().filter(elementoCliente -> elementoCliente.getId().equals(id)).findFirst().orElse(null);
    }

    private List<Cliente> clientes;

    public Vaga getVagaDisponivel() {
        return vagas.stream().filter(vaga -> vaga.isDisponivel()).findFirst().orElse(null);
    }

    private List<Vaga> vagas;

    private int quantFileiras;
    private int vagasPorFileira;

    /**
     * Construtor para inicializar o estacionamento.
     *
     * @param nome         Nome do estacionamento.
     * @param fileiras     Número de fileiras de vagas.
     * @param vagasPorFila Número de vagas por fileira.
     */
    public Estacionamento(String nome, int fileiras, int vagasPorFila) {
        this.nome = nome;
        this.quantFileiras = fileiras;
        this.vagasPorFileira = vagasPorFila;
        this.clientes = new LinkedList<>();
        this.vagas = gerarVagas();
        gerarVagas();
    }

    /**
     * Adiciona um cliente ao estacionamento.
     *
     * @param cliente Cliente a ser adicionado.
     */
    public void addCliente(Cliente cliente) {
        boolean existeMesmoId = this.clientes.stream().anyMatch(elementoCliente -> elementoCliente.getId().equals(cliente.getId()));
        if(existeMesmoId) return;
        else {
            this.clientes.add(cliente);
        }
    }

    /**
     * Gera as vagas do estacionamento com base no número de fileiras e vagas por
     * fileira.
     */
    private List<Vaga> gerarVagas() {
        int totalVagas = quantFileiras * vagasPorFileira;
        List<Vaga> listVaga = new ArrayList<>(totalVagas);

        for(int i = 0; i < quantFileiras; i++){
            for(int j = 0; j < vagasPorFileira; j++){
                listVaga.add(new Vaga(i, j + 1));
            }
        }

        return listVaga;
    }

    /**
     * Estaciona um veículo no estacionamento.
     *
     * @param placa       Placa do veículo a ser estacionado.
     * @param horaEntrada Hora de entrada do veículo.
     */
    public void estacionar(String placa, LocalDateTime horaEntrada) {
        Cliente donoVeiculo = this.buscarDonoVeiculoPorPlaca(placa);
        if(donoVeiculo == null){
            System.out.println("Veiculo não encontrado, favor efetuar o cadastro");
            return;
        }

        Veiculo veiculo = donoVeiculo.getVeiculoByPlaca(placa);

        if (veiculo.isEstacionado()) {
            System.out.println("O veículo com a placa " + placa + " já está estacionado");
            return;
        }

        Vaga vagaDisponivel = this.getVagaDisponivel();
        if(vagaDisponivel == null){
            System.out.println("Não há vagas disponíveis");
            return;
        }

        veiculo.estacionar(vagaDisponivel, horaEntrada);
    }

    /**
     * Remove um veículo do estacionamento.
     *
     * @param placa     Placa do veículo a ser removido.
     * @param horaSaida Hora de saída do veículo.
     */
    public void sair(String placa, LocalDateTime horaSaida) {
        Cliente cliente = this.buscarDonoVeiculoPorPlaca(placa);
        if(cliente == null){
            System.out.println("Veiculo não encontrado.");
            return;
        }

        Veiculo veiculo = cliente.getVeiculoByPlaca(placa);
        double valorTotal = veiculo.sair(horaSaida);
        System.out.println("Veículo com a placa " + placa + " saiu do estacionamento. Valor pago: R$" + valorTotal);
    }

    /**
     * Calcula o valor total arrecadado pelo estacionamento.
     *
     * @return Valor total arrecadado.
     */
    public double totalArrecadado() {
        return 0d;
    }

    /**
     * Calcula a arrecadação no mês especificado.
     *
     * @param mes Mês para o qual calcular a arrecadação.
     * @return Arrecadação mensal.
     */
    public double arrecadacaoNoMes(int mes) {
        return 0d;
    }

    /**
     * Calcula o valor médio por uso do estacionamento.
     *
     * @return Valor médio por uso.
     */
    public double valorMedioPorUso() {
        return 0d;
    }

    /**
     * Retorna os top 5 clientes com maior arrecadação no mês especificado.
     *
     * @param mes Mês para o qual calcular o ranking de clientes.
     * @return String representando o ranking dos top 5 clientes.
     */
    public String top5Clientes(int mes) {
//        List<EstacionamentoRegistro> registrosNoMes = registros.stream()
//                .filter(registro -> registro.getData().getMonthValue() == mes)
//                .collect(Collectors.toList());
//
//        Map<Cliente, Double> arrecadacaoClientes = new HashMap<>();
//        for (EstacionamentoRegistro registro : registrosNoMes) {
//            Cliente cliente = getClienteByPlaca(registro.getVeiculo().getPlaca());
//            if (cliente != null) {
//                double valorPago = registro.getValorPago();
//                arrecadacaoClientes.put(cliente, arrecadacaoClientes.getOrDefault(cliente, 0.0) + valorPago);
//            }
//        }
//
//        List<Cliente> topClientes = arrecadacaoClientes.entrySet()
//                .stream()
//                .sorted(Map.Entry.<Cliente, Double>comparingByValue().reversed())
//                .map(Map.Entry::getKey)
//                .collect(Collectors.toList());
//
//        int numClientesNoRanking = Math.min(5, topClientes.size());
//        StringBuilder ranking = new StringBuilder("Top 5 Clientes no Mês " + mes + ":\n");
//        for (int i = 0; i < numClientesNoRanking; i++) {
//            Cliente cliente = topClientes.get(i);
//            double arrecadacao = arrecadacaoClientes.get(cliente);
//            ranking.append(i + 1).append(". ")
//                    .append(cliente.getNome()).append(": R$")
//                    .append(arrecadacao).append("\n");
//        }

//        return ranking.toString();
        return "";
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

    
    /**
     * Gera um histórico detalhado para um cliente específico.
     *
     * @param id Identificação do cliente.
     * @return Histórico detalhado do cliente.
     */
//    public String historicoCliente(String id) {
//        Cliente busca = new Cliente(id, id);
//        String historico = "";
//
//        for (Cliente cliente : clientes.values()) {
//            if (busca.equals(cliente)) {
//                historico = cliente.historicoCliente();
//            }
//        }
//        return historico;
//    }



    /**
     * Obtém um cliente com base na placa do veículo.
     *
     * @param placa Placa do veículo.
     * @return Cliente que possui o veículo com a placa especificada.
     */
    public Cliente buscarDonoVeiculoPorPlaca(String placa){
        for(Cliente cliente : this.clientes){
            if(cliente.getVeiculoByPlaca(placa) != null){
                return cliente;
            }
        }
        return null;
    }
}
    
