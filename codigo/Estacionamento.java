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

    public Cliente getClienteById(int id){
        return this.getClientes().stream().filter(elementoCliente -> elementoCliente.getId() == id).findFirst().orElse(null);
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
    }

    /**
     * Adiciona um cliente ao estacionamento.
     *
     * @param cliente Cliente a ser adicionado.
     */
    public void addCliente(Cliente cliente) throws IllegalArgumentException{
        boolean existeMesmoId = this.clientes.stream().anyMatch(elementoCliente -> elementoCliente.getId() == cliente.getId());
        if(existeMesmoId){
            throw new IllegalArgumentException("Id de cliente já existente no estacionamento");
        }
        else this.clientes.add(cliente);
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
    public Vaga estacionar(String placa, LocalDateTime horaEntrada) throws IllegalStateException, IllegalArgumentException{
        Cliente donoVeiculo = this.buscarDonoVeiculoPorPlaca(placa);
        if(donoVeiculo == null){
            throw new IllegalArgumentException("Veiculo não encontrado, favor efetuar o cadastro");
        }

        Veiculo veiculo = donoVeiculo.getVeiculoByPlaca(placa);

        if (veiculo.isEstacionado()) {
            throw new IllegalStateException("O veículo com a placa " + placa + " já está estacionado");
        }

        Vaga vagaDisponivel = this.getVagaDisponivel();
        if(vagaDisponivel == null){
            throw new IllegalStateException("Não há vagas disponíveis");
        }

        veiculo.estacionar(vagaDisponivel, horaEntrada);
        return vagaDisponivel;
    }

    /**
     * Remove um veículo do estacionamento.
     *
     * @param placa     Placa do veículo a ser removido.
     * @param horaSaida Hora de saída do veículo.
     */
    public double sair(String placa, LocalDateTime horaSaida) throws IllegalArgumentException, IllegalStateException{
        Cliente cliente = this.buscarDonoVeiculoPorPlaca(placa);
        if(cliente == null){
            throw new IllegalArgumentException("Veiculo não encontrado");
        }

        Veiculo veiculo = cliente.getVeiculoByPlaca(placa);
        return veiculo.sair(horaSaida);
    }

    /**
     * Calcula o valor total arrecadado pelo estacionamento.
     *
     * @return Valor total arrecadado.
     */
    public double totalArrecadado() {
        return this.clientes.stream().mapToDouble(cliente -> cliente.arrecadadoTotal()).sum();
    }

    /**
     * Calcula a arrecadação no mês especificado.
     *
     * @param mes Mês para o qual calcular a arrecadação.
     * @return Arrecadação mensal.
     */
    public double arrecadacaoNoMes(int mes) {
        return this.clientes.stream().mapToDouble(cliente -> cliente.arrecadadoNoMes(mes)).sum();
    }

    /**
     * Calcula o valor médio por uso do estacionamento.
     *
     * @return Valor médio por uso.
     */
    public double valorMedioPorUso() {
        return this.clientes.stream().mapToDouble(cliente -> cliente.mediaPorUso()).average().orElse(0);
    }

    /**
     * Retorna os top 5 clientes com maior arrecadação no mês especificado.
     *
     * @param mes Mês para o qual calcular o ranking de clientes.
     * @return String representando o ranking dos top 5 clientes.
     */
    public String top5Clientes(int mes) {
        List<Cliente> sortedClientes = this.clientes.stream()
                                                    .sorted((clienteA, clienteB) -> clienteA.arrecadadoNoMes(mes) < clienteB.arrecadadoNoMes(mes) ? 1 : -1)
                                                    .limit(5)
                                                    .toList();
        StringBuilder tabela = new StringBuilder();
        String colunaId = "Id ";
        String colunaNome = "Nome               ";
        String colunaTotal = "Total arrecadado      ";
        String topoTabela = String.format(" %s | %s | %s ", colunaId, colunaNome, colunaTotal);
        tabela.append("_".repeat(topoTabela.length() + 2)).append('\n');
        tabela.append("|").append(topoTabela).append("|").append("\n");

        if(sortedClientes.isEmpty()){
            String naoExistemClientes = "Não existem clientes cadastrados";
            String aviso = Utils.centralizarTexto(naoExistemClientes, topoTabela);

            tabela.append("|").append(aviso).append("|").append("\n");
        }else {
            for(Cliente cliente:sortedClientes){
                String idCliente = String.valueOf(cliente.getId());
                idCliente += " ".repeat(colunaId.length() - idCliente.length());

                String nomeCliente = String.valueOf(cliente.getNome());
                nomeCliente += " ".repeat(colunaNome.length() - nomeCliente.length());

                String quantTotalCliente = String.format("R$%5.2f", cliente.arrecadadoNoMes(mes));
                quantTotalCliente = Utils.fillSpacesToLimit(quantTotalCliente, colunaTotal.length());

                tabela.append(String.format("| %s | %s | %s |\n", idCliente, nomeCliente, quantTotalCliente));
            }
        }
        tabela.append("_".repeat(topoTabela.length() + 2)).append('\n');
        return tabela.toString();
    }

    /**
     * Gera um histórico detalhado para um cliente específico.
     *
     * @param id Identificação do cliente.
     * @return Histórico detalhado do cliente.
     */
    public String historicoCliente(int id) {
        Cliente cliente = this.getClienteById(id);
        if(cliente == null){
            throw new IllegalArgumentException("Cliente não existente");
        }else{
            return cliente.gerarTabelaHistoricoUso();
        }
    }



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

    public int gerarProximoIdCliente(){
        if(this.getClientes().isEmpty()){
            return 1;
        }
        return this.getClientes()
                    .stream()
                    .mapToInt(Cliente::getId)
                    .max().orElse(0) + 1;
    }

    public String gerarTabelaClientes() {
        StringBuilder tabela = new StringBuilder();
        String colunaId = "Id ";
        String colunaNome = "Nome               ";
        String colunaQuantVeiculos = "Quant. Veiculos";
        String colunaTipoCliente = "Tipo Cliente      ";
        String topoTabela = String.format(" %s | %s | %s | %s ", colunaId, colunaNome, colunaQuantVeiculos, colunaTipoCliente);
        tabela.append("_".repeat(topoTabela.length() + 2)).append('\n');
        tabela.append("|").append(topoTabela).append("|").append("\n");

        if(this.getClientes().isEmpty()){
            String naoExistemClientes = "Não existem clientes cadastrados";
            String aviso = Utils.centralizarTexto(naoExistemClientes, topoTabela);

            tabela.append("|").append(aviso).append("|").append("\n");
        }else {
            for(Cliente cliente:this.getClientes()){
                String idCliente = String.valueOf(cliente.getId());
                idCliente += " ".repeat(colunaId.length() - idCliente.length());

                String nomeCliente = String.valueOf(cliente.getNome());
                nomeCliente += " ".repeat(colunaNome.length() - nomeCliente.length());

                String quantVeiculosCliente = String.valueOf(cliente.getCountVeiculos());
                quantVeiculosCliente += " ".repeat(colunaQuantVeiculos.length() - quantVeiculosCliente.length());

                String tipoCliente = String.valueOf(cliente.getNomeCategoria());
                tipoCliente += " ".repeat(colunaTipoCliente.length() - tipoCliente.length());

                tabela.append(String.format("| %s | %s | %s | %s |\n", idCliente, nomeCliente, quantVeiculosCliente, tipoCliente));
            }
        }
        tabela.append("_".repeat(topoTabela.length() + 2)).append('\n');
        return tabela.toString();
    }
}
    
