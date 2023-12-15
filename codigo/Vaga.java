public class Vaga {
    private String id;
    private boolean disponivel;
    private static final String FILAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * Construtor da classe Vaga.
     *
     * @param fila   A letra da fila da vaga.
     * @param numero O número da vaga.
     */
    public Vaga(int fila, int numero) {
        char letraVaga = FILAS.charAt(fila);
        String vagaFinal = letraVaga + String.valueOf(numero);
        this.id = vagaFinal;
    }

    /**
     * Estaciona um veículo na vaga, se a vaga estiver disponível.
     *
     * @return true se o veículo foi estacionado com sucesso, false se a vaga estiver ocupada.
     */
    public void estacionar() {
        disponivel = false;
        System.out.println("Veículo estacionado na " + id);
    }

    /**
     * Libera a vaga, marcando-a como disponível.
     *
     * @return true se a vaga foi liberada com sucesso, false se a vaga já estiver vazia.
     */
    public void sair() {
        disponivel = true;
        System.out.println("Veículo saiu da " + id);
    }

    /**
     * Obtém o identificador único da vaga.
     *
     * @return O identificador único da vaga.
     */
    public String getId() {
        return id;
    }

    /**
     * Define o identificador único da vaga.
     *
     * @param id O novo identificador único da vaga.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Verifica se a vaga está disponível.
     *
     * @return true se a vaga estiver disponível, false se estiver ocupada.
     */
    public boolean isDisponivel() {
        return disponivel;
    }

    /**
     * Define a disponibilidade da vaga.
     *
     * @param disponivel O novo estado de disponibilidade da vaga (true para disponível, false para ocupada).
     */
    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
}
