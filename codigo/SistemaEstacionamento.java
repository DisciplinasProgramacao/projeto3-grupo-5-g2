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
                case 1:
                    cadastrarCliente();
                    break;
                case 2:
                    cadastrarVeiculo();
                    break;
                case 3:
                    estacionarVeiculo();
                    break;
                case 4:
                    contratarServicoAdd();
                    break;
                case 5:
                    sairVaga();
                    break;
                case 6:
                    encerrarSistema();
                    break;
            }

        }
    }

    private void menu() {
        System.out.println("Bem-vindo ao Sistema de Estacionamento da Xulambs Parking!");
        System.out.println("Escolha uma opção:");
        System.out.println("1. Cadastrar Cliente");
        System.out.println("2. Cadastrar veículo");
        System.out.println("3. Estacionar veículo");
        System.out.println("4. Contratar serviço adicional");
        System.out.println("5. Sair do estacionamento");
        System.out.println("6. Encerrar o sistema");

    }

    private void cadastrarCliente() {
        System.out.println("------ Cadastro de Cliente ------");
        System.out.print("Nome do cliente: ");
        scanner.next();
        String nome = scanner.nextLine();
        System.out.print("Id do cliente: ");
        String id = scanner.nextLine();


        System.out.println("Selecione o tipo de cliente:");
        int idxTipo = 1;
        List<String> listCategorias = CategoriaClienteFactory.getListaCategorias();
        for (String tipo : listCategorias) {
            System.out.println((idxTipo++) + ". " + tipo);
        }
        int escolhaTipo = scanner.nextInt();
        scanner.nextLine();

        CategoriaCliente tipoCliente = new Horista();
        if (escolhaTipo > 0 && escolhaTipo <= listCategorias.size()) {
            tipoCliente = CategoriaClienteFactory.getCategoriaByName(listCategorias.get(escolhaTipo - 1));
        }

        if (tipoCliente instanceof Turnista) {
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
            } else {
                System.out.println("Turno escolhido invalido! O cliente será registrado como horista.");
                tipoCliente = CategoriaClienteFactory.getCategoriaByName("Horista");
            }
        }

        Cliente cliente = new Cliente(nome, id, tipoCliente);
        estacionamento.addCliente(cliente);
    }

    private void cadastrarVeiculo() {
        System.out.println("------ Cadastro de Veículo ------");
        System.out.print("Id do cliente: ");
        scanner.next();
        String id = scanner.nextLine();
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

        System.out.println("Informe a placa do veículo:");
        scanner.next();
        String placa = scanner.nextLine();

        estacionamento.estacionar(placa, LocalDateTime.now());
    }

    private void contratarServicoAdd() {
        System.out.println("Informe a placa do veículo:");
        String placa = scanner.nextLine();

        Cliente clienteEncontrado = estacionamento.buscarDonoVeiculoPorPlaca(placa);

        if (clienteEncontrado != null) {
            Veiculo veiculoDoCliente = clienteEncontrado.getVeiculoByPlaca(placa);

            if (veiculoDoCliente != null) {
                System.out.println("Escolha um serviço adicional:");
                System.out.println("1. Lavagem");
                System.out.println("2. Manobrista");
                System.out.println("3. Polimento");

                int opcaoServico = scanner.nextInt();

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
            } else {
                System.out.println(
                        "Veículo não encontrado. Verifique os dados e tente novamente.");
            }
        } else {
            System.out.println("Veículo não encontrado. Verifique os dados e tente novamente.");
        }

    }

    private void sairVaga() {
        System.out.println("Informe a placa do veículo que deseja sair:");
        String placaSaida = scanner.nextLine();
        estacionamento.sair(placaSaida, LocalDateTime.now());
    }

    private void encerrarSistema() {
        System.out.println("Encerrando o sistema de estacionamento.");
        scanner.close(); // Fecha o scanner
        System.exit(0); // Encerra o programa
    }
}
