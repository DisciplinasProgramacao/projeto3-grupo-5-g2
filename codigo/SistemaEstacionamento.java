import java.util.List;
import java.util.Scanner;

/**
 * Representa o sistema de estacionamento da Xulambs Parking.
 */
public class SistemaEstacionamento {
    private Cliente cliente; // Mantenha a instância de Cliente
    private Estacionamento estacionamento; // Mantenha a instância de Estacionamento

    /**
     * Construtor para inicializar o Sistema de Estacionamento.
     * Inicializa as instâncias de Cliente e Estacionamento conforme necessário.
     */
    public SistemaEstacionamento() {
        cliente = new Cliente("Nome do Cliente", "ID do Cliente"); // Isso pode variar dependendo da estrutura do seu sistema
        estacionamento = new Estacionamento("Nome do Estacionamento", 3, 5); // Isso pode variar dependendo da estrutura do seu sistema
    }

    /**
     * Inicializa e executa o sistema de estacionamento.
     */
    public void iniciarSistema() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Bem-vindo ao Sistema de Estacionamento da Xulambs Parking!");
            System.out.println("Escolha uma opção:");
            System.out.println("1. Estacionar veículo");
            System.out.println("2. Contratar serviço adicional");
            System.out.println("3. Sair do estacionamento");
            System.out.println("4. Encerrar o sistema");

            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    // Opção para estacionar veículo
                    System.out.println("Informe a placa do veículo:");
                    String placa = scanner.next();
                    System.out.println("Informe a vaga (ID da vaga) onde deseja estacionar:");
                    String idVaga = scanner.next();

                    Cliente clienteEncontrado = cliente.encontrarClientePorPlaca(placa);
                    Vaga vaga = estacionamento.encontrarVagaPorId(idVaga);

                    if (clienteEncontrado != null && vaga != null) {
                        Veiculo veiculoDoCliente = clienteEncontrado.possuiVeiculo(placa); // Procure o veículo no cliente
                        if (veiculoDoCliente != null) {
                            veiculoDoCliente.estacionar(vaga); // Estacione o veículo na vaga
                            System.out.println("Veículo estacionado com sucesso na vaga " + vaga.getId());
                        } else {
                            System.out.println("Veículo não pertence a esse cliente. Verifique os dados e tente novamente.");
                        }
                    } else {
                        System.out.println("Veículo ou vaga não encontrados. Verifique os dados e tente novamente.");
                    }
                    break;

                case 2:
                    // Opção para contratar serviço adicional
                    System.out.println("Informe a placa do veículo:");
                    placa = scanner.next();

                    clienteEncontrado = cliente.encontrarClientePorPlaca(placa);

                    if (clienteEncontrado != null) {
                        Veiculo veiculoDoCliente = clienteEncontrado.possuiVeiculo(placa);

                        if (veiculoDoCliente != null) {
                            System.out.println("Escolha um serviço adicional:");
                            System.out.println("1. Lavagem");
                            System.out.println("2. Manobrista");
                            System.out.println("3. Polimento");

                            int opcaoServico = scanner.nextInt();

                            switch (opcaoServico) {
                                case 1:
                                    veiculoDoCliente.contratarServico(new ServicoLavagem());
                                    System.out.println("Serviço de Lavagem contratado para o veículo.");
                                    break;
                                case 2:
                                    veiculoDoCliente.contratarServico(new ServicoManobrista());
                                    System.out.println("Serviço de Manobrista contratado para o veículo.");
                                    break;
                                case 3:
                                    veiculoDoCliente.contratarServico(new ServicoPolimento());
                                    System.out.println("Serviço de Polimento contratado para o veículo.");
                                    break;
                                default:
                                    System.out.println("Opção de serviço inválida. Tente novamente.");
                                    break;
                            }
                        } else {
                            System.out.println("Veículo não pertence a esse cliente. Verifique os dados e tente novamente.");
                        }
                    } else {
                        System.out.println("Veículo não encontrado. Verifique os dados e tente novamente.");
                    }
                    break;

                case 3:
                    System.out.println("Informe a placa do veículo que deseja sair:");
                    String placaSaida = scanner.next();

                    clienteEncontrado = estacionamento.encontrarClientePorPlaca(placaSaida);

                    if (clienteEncontrado != null) {
                        Veiculo veiculoDoCliente = clienteEncontrado.possuiVeiculo(placaSaida);

                        if (veiculoDoCliente != null) {
                            List<Cliente> listaClientes = estacionamento.getClientes(); // Acessa a lista de clientes do estacionamento
                            for (Cliente c : listaClientes) {
                                if (c.equals(clienteEncontrado)) {
                                    c.removerVeiculo(veiculoDoCliente);
                                    break;
                                }
                            }

                            Vaga vagaDoVeiculo = estacionamento.encontrarVagaPorVeiculo(veiculoDoCliente);

                            if (vagaDoVeiculo != null) {
                                estacionamento.liberarVaga(vagaDoVeiculo);
                                System.out.println("Veículo saiu do estacionamento com sucesso.");
                            } else {
                                System.out.println("Vaga do veículo não encontrada.");
                            }
                        } else {
                            System.out.println("Veículo não pertence a esse cliente. Verifique os dados e tente novamente.");
                        }
                    } else {
                        System.out.println("Veículo não encontrado. Verifique os dados e tente novamente.");
                    }
                    break;

                case 4:
                    System.out.println("Encerrando o sistema de estacionamento.");
                    scanner.close(); // Fecha o scanner
                    System.exit(0); // Encerra o programa
                    break;
            }
        }
    }
}
