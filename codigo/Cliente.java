import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe que representa um cliente do estacionamento.
 */
public class Cliente {
    private String nome;
    private int id;
    private List<Veiculo> veiculos;

    public ICategoriaCliente getCategoriaCliente() {
        return categoriaCliente;
    }

    private ICategoriaCliente categoriaCliente;

    /**
     * Construtor para criar um novo cliente.
     *
     * @param id           Identificação única do cliente.
     * @param nome         Nome do cliente.
     */
    public Cliente(int id, String nome, ICategoriaCliente tipoCliente) {
        this.nome = nome;
        this.id = id;
        this.veiculos = new LinkedList<>();
        this.categoriaCliente = tipoCliente;
    }

    /**
     * Obtém a identificação única do cliente.
     *
     * @return A identificação única do cliente.
     */
    public int getId() {
        return id;
    }

    /**
     * Obtém o nome do cliente.
     *
     * @return O nome do cliente.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Adiciona um veículo à lista de veículos pertencentes ao cliente.
     *
     * @param veiculo Veículo a ser adicionado.
     */
    public void addVeiculo(Veiculo veiculo) {
        veiculos.add(veiculo);
    }

    /**
     * Obtém o número total de usos de estacionamento registrados pelo cliente.
     *
     * @return Número total de usos de estacionamento.
     */
    public int totalDeUsos() {
        return this.getTodosUsos().size();
    }

    /**
     * Calcula o valor total arrecadado pelo cliente com todos os seus usos de estacionamento.
     *
     * @return Valor total arrecadado pelo cliente.
     */
    public double arrecadadoTotal() {
        return this.getTodosUsos()
                    .stream()
                    .mapToDouble(UsoDeVaga::getTotalPago)
                    .sum();
    }

    /**
     * Calcula o valor médio arrecadado por uso do cliente.
     *
     * @return Valor total arrecadado pelo cliente.
     */
    public double mediaPorUso() {
        return this.getTodosUsos()
                .stream()
                .mapToDouble(UsoDeVaga::getTotalPago)
                .average().orElse(0);
    }

    /**
     * Calcula o valor arrecadado pelo cliente no mês especificado.
     *
     * @param mes Mês para o qual calcular a arrecadação.
     * @return Valor arrecadado pelo cliente no mês especificado.
     */
    public double arrecadadoNoMes(int mes) {
        return this.getTodosUsos()
                .stream()
                .filter(usoDeVaga -> usoDeVaga.getSaida().getMonth().getValue() == mes && usoDeVaga.getSaida().getYear() == LocalDateTime.now().getYear())
                .mapToDouble(UsoDeVaga::getTotalPago)
                .sum();
    }


    /**
     * Verifica se o cliente possui um veículo com a placa especificada.
     *
     * @param placa Placa do veículo.
     * @return O veículo com a placa especificada se o cliente o possuir, caso contrário, retorna null.
     */
    public Veiculo getVeiculoByPlaca(String placa){
        return this.veiculos.stream().filter(veiculo -> veiculo.getPlaca().equals(placa)).findFirst().orElse(null);
    }

    public int getCountVeiculos() {
        return this.veiculos.size();
    }

    public void alterarCategoria(ICategoriaCliente novaCategoria){
        this.categoriaCliente = novaCategoria;
    }

    public String getNomeCategoria(){
        return this.getCategoriaCliente().getNome();
    }

    public String gerarTabelaHistoricoUso() {
        StringBuilder tabela = new StringBuilder();
        String colunaVaga = "Vaga ";
        String colunaDataEntrada = "Entrada               ";
        String colunaDataSaida = "Saída                 ";
        String colunaTempo = "Tempo (horas)                 ";
        String colunaServicosContratados = "Serviços contratados                       ";
        String colunaTotalPago = "Total pago          ";

        String topoTabela = String.format(" %s | %s | %s | %s | %s | %s ",
                colunaVaga,
                colunaDataEntrada,
                colunaDataSaida,
                colunaTempo,
                colunaServicosContratados,
                colunaTotalPago
        );
        tabela.append("_".repeat(topoTabela.length() + 2)).append('\n');
        tabela.append("|").append(topoTabela).append("|").append("\n");

        List<UsoDeVaga> listUsos = this.getTodosUsos();

        if(listUsos.isEmpty()){

            String naoExistemUsos = "Não existem usos para este cliente";
            String aviso = Utils.centralizarTexto(naoExistemUsos, topoTabela);

            tabela.append("|").append(aviso).append("|").append("\n");
        }else {
            for(UsoDeVaga usoVaga:listUsos){
                String vagaUso = String.valueOf(usoVaga.getVaga().getId());
                vagaUso = Utils.fillSpacesToLimit(vagaUso, colunaVaga.length());

                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy H:m:s");

                String dataEntradaUso = dateFormat.format(usoVaga.getEntrada());
                dataEntradaUso = Utils.fillSpacesToLimit(dataEntradaUso, colunaDataEntrada.length());


                LocalDateTime saida = usoVaga.getSaida();
                String dataSaidaUso;

                if(usoVaga.isEmUso()) dataSaidaUso = "EM USO";
                else dataSaidaUso = dateFormat.format(saida);
                dataSaidaUso = Utils.fillSpacesToLimit(dataSaidaUso, colunaDataSaida.length());

                String tempoUso;
                if(usoVaga.isEmUso()){
                    tempoUso = "EM USO";
                }else{
                    double horasUso = usoVaga.getHorasUso();
                    tempoUso = String.format("%5.1f", horasUso);
                }
                tempoUso = Utils.fillSpacesToLimit(tempoUso, colunaTempo.length());

                String servicosContratadosUso;
                if(!usoVaga.getServicos().isEmpty()){
                    servicosContratadosUso = usoVaga.getServicos().stream().map(Servicos::getNome).reduce("", (servicos, servico) -> servicos + servico + ", ");
                    servicosContratadosUso = servicosContratadosUso.substring(0, servicosContratadosUso.length() - 2);
                }else servicosContratadosUso = "Nenhum serviço contratado";
                servicosContratadosUso = Utils.fillSpacesToLimit(servicosContratadosUso, servicosContratadosUso.length());

                String totalPagoUso = String.format("%5.2f", usoVaga.getTotalPago());
                totalPagoUso = Utils.fillSpacesToLimit(totalPagoUso, colunaTotalPago.length());

                tabela.append(String.format("| %s | %s | %s | %s | %s | %s |\n",
                        vagaUso,
                        dataEntradaUso,
                        dataSaidaUso,
                        tempoUso,
                        servicosContratadosUso,
                        totalPagoUso
                ));
            }
        }
        tabela.append("_".repeat(topoTabela.length() + 2)).append('\n');
        return tabela.toString();
    }

    private List<UsoDeVaga> getTodosUsos() {
        return this.veiculos
                .stream()
                .map(Veiculo::getUsos)
                .reduce(new LinkedList<>(), (todosUsos, usoVeiculo) -> {
                    todosUsos.addAll(usoVeiculo);
                    return todosUsos;
                });
    }
}
