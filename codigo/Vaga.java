public class Vaga {
    private String id;
    private boolean disponivel;
    private String filas = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * Construtor da classe Vaga.
     *
     * @param fila   A letra da fila da vaga.
     * @param numero O número da vaga.
     */
    public Vaga(char fila, int numero) {
        String vagaFinal;
        String letraVaga = "";
        letraVaga += filas.charAt(fila);
        vagaFinal = letraVaga + numero;

        // Define a disponibilidade da vaga com base no valor da vagaFinal
        if (vagaFinal != null)
            disponivel = false;
    }

    /**
     * Estaciona um veículo na vaga, se a vaga estiver disponível.
     *
     * @return true se o veículo foi estacionado com sucesso, false se a vaga estiver ocupada.
     */
    public boolean estacionar() {
        if (disponivel) {
            disponivel = false;
            System.out.println("Veículo estacionado na " + id);
            return true;
        } else {
            return 0.0;
        }
    }

    /**
     * Libera a vaga, marcando-a como disponível.
     *
     * @return true se a vaga foi liberada com sucesso, false se a vaga já estiver vazia.
     */
    public boolean sair() {
        if (!disponivel) {
            disponivel = true;
            System.out.println("Veículo saiu da " + id);
            return true;
        } else {
            return 0.0;
        }
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
