import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

/**
 * Representa o sistema de estacionamento da Xulambs Parking.
 */
public class SistemaEstacionamento {
    private Cliente cliente; // Mantenha a instância de Cliente
    private Estacionamento estacionamento; // Mantenha a instância de Estacionamento
    private Scanner scanner;

    /**
     * Construtor para inicializar o Sistema de Estacionamento.
     * Inicializa as instâncias de Cliente e Estacionamento conforme necessário.
     */
    public SistemaEstacionamento() {
        estacionamento = new Estacionamento("Nome do Estacionamento", 3, 5); // Isso pode variar dependendo da estrutura do seu sistema
    }

    /**
     * Inicializa e executa o sistema de estacionamento.
     */
    public void iniciarSistema() {
        this.scanner = new Scanner(System.in);

        while (true) {
            menu();
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1: {
                    cadastrarCliente();
                    break;
                }
                case 2: {
                    cadastrarVeiculo();
                    break;
                }
                case 3: {
                    estacionarVeiculo();
                    break;
                }
                case 4: {
                    sairVaga();
                    break;
                }
                case 5: {
                    contratarServicoAdd();
                    break;
                }
                case 6: {
                    gerenciarClientes();
                    break;
                }
                case 7: {
                    gerenciarVeiculo();
                    break;
                }
                case 99: {
                    encerrarSistema();
                    break;
                }

            }

        }
    }

    private void gerenciarVeiculo() {
    }

    private void gerenciarClientes() {
        int opcao = 0;
        do {
            System.out.println("------ Gerenciamento de clientes ------");
            System.out.println("Escolha uma opção:");
            System.out.println("1. Listar clientes");
            System.out.println("2. Ranking mês");
            System.out.println("3. Alterar categoria cliente");
            System.out.println("4. Histórico cliente");
            System.out.println("99. Retornar ao menu");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1: {
                    listarClientes();
                    break;
                }
                case 2: {
                    rankingMes();
                    break;
                }
                case 3: {
                    alterarCategoriaCliente();
                    break;
                }
                case 4: {
                    historicoCliente();
                    break;
                }
                case 99: {
                    System.out.println("Retornando ao menu.");
                    return;
                }

            }
        }while(opcao != 99 && opcao > 0);

    }

    private void rankingMes() {
        System.out.println("------ Ranking mês estacionamento ------");
        System.out.print("Mês (1 à 12): ");
        scanner.nextLine();
        int mes;
        try {
            mes = Integer.parseInt(scanner.nextLine());
            if(mes < 1 || mes > 12){
                throw new NumberFormatException();
            }else System.out.println(estacionamento.top5Clientes(mes));
        }catch(NumberFormatException exception){
            System.out.println("Mês inválido, por favor utilize apenas números de 1 à 12.");
        }
    }

    private void historicoCliente() {
        System.out.println("------ Historico cliente ------");
        System.out.print("Id do cliente: ");
        scanner.nextLine();
        int id;
        try {
            id = Integer.parseInt(scanner.nextLine());
            estacionamento.historicoCliente(id);
        }catch(NumberFormatException exception){
            System.out.println("Id inválido, por favor utilize apenas números.");
            return;
        }

        Cliente cliente = estacionamento.getClienteById(id);
        if(cliente == null){
            System.out.println("Cliente não existente");
        }else{
            try{
                System.out.println(cliente.gerarTabelaHistoricoUso());
            }catch(IllegalArgumentException exception){
                System.out.println("Erro: " + exception.getMessage());
            }
        }
    }

    private void alterarCategoriaCliente() {
        System.out.println("------ Alterar categoria de cliente ------");
        System.out.print("Id do cliente: ");
        scanner.nextLine();
        int id;
        try {
            id = Integer.parseInt(scanner.nextLine());
        }catch(NumberFormatException exception){
            System.out.println("Id inválido, por favor utilize apenas números.");
            return;
        }

        Cliente cliente = estacionamento.getClienteById(id);
        if(cliente == null){
            System.out.println("Cliente não existente");
        }else{
            System.out.println("Cliente selecionado: " + cliente.getId() + " - " + cliente.getNome());
            System.out.println("Categoria atual: " + cliente.getNomeCategoria());
            ICategoriaCliente novoTipo = requisitarTipoCliente();
            cliente.alterarCategoria(novoTipo);
            System.out.println("Nova categoria: " + cliente.getNomeCategoria());
        }
    }

    private void listarClientes() {
        System.out.println(estacionamento.gerarTabelaClientes());
    }

    private void menu() {
        System.out.println("Bem-vindo ao Sistema de Estacionamento da Xulambs Parking!");
        System.out.println("Escolha uma opção:");
        System.out.println("1. Cadastrar Cliente");
        System.out.println("2. Cadastrar veículo");
        System.out.println("3. Estacionar veículo");
        System.out.println("4. Sair do estacionamento");
        System.out.println("5. Contratar serviço adicional");
        System.out.println("6. Gerenciar cliente");
        System.out.println("7. Gerenciar veículo");
        System.out.println("99. Encerrar o sistema");

    }

    private void cadastrarCliente() {
        System.out.println("------ Cadastro de Cliente ------");
        int id = estacionamento.gerarProximoIdCliente();
        System.out.println("Id gerado para o cliente: " + id);
        System.out.print("Nome do cliente: ");
        scanner.nextLine();
        String nome = scanner.nextLine();

        ICategoriaCliente tipoCliente = requisitarTipoCliente();

        Cliente cliente = new Cliente(id, nome, tipoCliente);
        try {
            estacionamento.addCliente(cliente);
        }catch(IllegalArgumentException exception){
            System.out.println("Erro: " + exception.getMessage());
        }
    }

    private ICategoriaCliente requisitarTipoCliente() {
        System.out.println("Selecione o tipo de cliente:");
        ICategoriaCliente tipoCliente = CategoriaClienteFactory.getPadrao();
        boolean tipoValido = false;
        do {
            int idxTipo = 1;
            List<String> listCategorias = CategoriaClienteFactory.getListaCategorias();
            for (String tipo : listCategorias) {
                System.out.println((idxTipo++) + ". " + tipo);
            }
            int escolhaTipo = scanner.nextInt();
            scanner.nextLine();

            if (escolhaTipo > 0 && escolhaTipo <= listCategorias.size()) {
                tipoCliente = CategoriaClienteFactory.getCategoriaByName(listCategorias.get(escolhaTipo - 1));
                tipoValido = true;
            }else{
                System.out.println("Tipo escolhido invalido!");
                continue;
            }

            if (tipoCliente instanceof Turnista) {
                boolean turnoValido = false;
                do {
                    System.out.println("Selecione o turno do cliente: ");
                    for (Turno turno : Turno.values()) {
                        System.out.println((turno.ordinal() + 1) + ". " + turno.getNome());
                    }
                    int escolhaTurno = scanner.nextInt();
                    scanner.nextLine();

                    Turno turnoCliente = null;
                    if (escolhaTurno > 0 && escolhaTurno <= Turno.values().length) {
                        turnoCliente = Turno.values()[escolhaTurno - 1];
                        ((Turnista) tipoCliente).setTurno(turnoCliente);
                        turnoValido = true;
                    } else {
                        System.out.println("Turno escolhido invalido!");
                    }
                } while (!turnoValido);
            }
        }while(!tipoValido);

        return tipoCliente;
    }

    private void cadastrarVeiculo() {
        System.out.println("------ Cadastro de Veículo ------");
        System.out.print("Id do cliente: ");
        scanner.nextLine();
        int id;
        try {
            id = Integer.parseInt(scanner.nextLine());
        }catch(NumberFormatException exception){
            System.out.println("Id inválido, por favor utilize apenas números.");
            return;
        }

        Cliente cliente = estacionamento.getClienteById(id);
        if(cliente == null){
            System.out.println("Cliente não existente, favor efetuar cadastro");
            return;
        }

        System.out.print("Placa do Veiculo: ");
        String placa = scanner.nextLine();
        cliente.addVeiculo(new Veiculo(placa, cliente));
    }

    private void estacionarVeiculo() {

        System.out.print("Informe a placa do veículo: ");
        scanner.nextLine();
        String placa = scanner.nextLine();
        try{
            Vaga vagaEstacinada = estacionamento.estacionar(placa, LocalDateTime.now());
            System.out.println("Veiculo de placa " + placa + " estacionado na vaga " + vagaEstacinada.getId());
        }catch(IllegalStateException | IllegalArgumentException exception){
            System.out.println("Erro: " + exception.getMessage());
        }
    }

    private void contratarServicoAdd() {
        System.out.print("Informe a placa do veículo: ");
        scanner.nextLine();
        String placa = scanner.nextLine();

        Cliente clienteEncontrado = estacionamento.buscarDonoVeiculoPorPlaca(placa);

        if (clienteEncontrado != null) {
            Veiculo veiculoDoCliente = clienteEncontrado.getVeiculoByPlaca(placa);
            if(!veiculoDoCliente.isEstacionado()){
                System.out.println("Erro: Veiculo não está estacionado");
            }

            if (veiculoDoCliente != null) {
                System.out.println("Escolha um serviço adicional:");
                System.out.println("1. Lavagem");
                System.out.println("2. Manobrista");
                System.out.println("3. Polimento");

                int opcaoServico = scanner.nextInt();
                try {
                    switch (opcaoServico) {
                        case 1:
                            veiculoDoCliente.contratarServico(Servicos.LAVAGEM);
                            System.out.println("Serviço de Lavagem contratado para o veículo.");
                            break;
                        case 2:
                            veiculoDoCliente.contratarServico(Servicos.MANOBRISTA);
                            System.out.println("Serviço de Manobrista contratado para o veículo.");
                            break;
                        case 3:
                            veiculoDoCliente.contratarServico(Servicos.POLIMENTO);
                            System.out.println("Serviço de Polimento contratado para o veículo.");
                            break;
                        default:
                            System.out.println("Opção de serviço inválida. Tente novamente.");
                            break;
                    }
                }catch (IllegalStateException exception){
                    System.out.println("Erro: " + exception.getMessage());
                }
            } else {
                System.out.println("Erro: Veículo não encontrado. Verifique os dados e tente novamente.");
            }
        } else {
            System.out.println("Erro: Veículo não encontrado. Verifique os dados e tente novamente.");
        }

    }

    private void sairVaga() {
        System.out.print("Informe a placa do veículo que deseja sair: ");
        scanner.nextLine();
        String placaSaida = scanner.nextLine();
        try {
            double valorTotal = estacionamento.sair(placaSaida, LocalDateTime.now());
            System.out.println("Veículo de placa " + placaSaida + " saiu do estacionamento.\nValor total: R$" + String.format("%5.2f", valorTotal));
        }catch (IllegalArgumentException | IllegalStateException exception){
            System.out.println("Erro: " + exception.getMessage());
        }
    }

    private void encerrarSistema() {
        System.out.println("Encerrando o sistema de estacionamento.");
        scanner.close(); // Fecha o scanner
        System.exit(0); // Encerra o programa
    }
}
