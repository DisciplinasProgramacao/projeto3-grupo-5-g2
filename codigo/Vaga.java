

public class Vaga {

    private String id;
    private boolean disponivel;

    /**
     * Construtor da classe Vaga.
     * @param fila Número da fila da vaga.
     * @param numero Número da vaga na fila.
     */
    public Vaga(char fila, int numero) {
        this.id = "Vaga " + fila + numero;
        this.disponivel = true;
    }

    /**
     * Obtém o identificador da vaga.
     * @return O identificador da vaga.
     */
    public String getId() {
        return id;
    }

   /**
     * Define o identificador alfanumérico da vaga.
     * 
     * @param id O novo identificador alfanumérico da vaga no formato XY.
     */ 
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Verifica se a vaga está disponível.
     * @return true se a vaga estiver disponível, false caso contrário.
     */
    public boolean isDisponivel() {
        return disponivel;
    }

    /**
     * Define o status de disponibilidade da vaga.
     * 
     * @param disponivel true se a vaga estiver disponível, false caso contrário.
     */
    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    /**
     * Estaciona um veículo na vaga, marcando-a como ocupada.
     * @return true se o veículo foi estacionado com sucesso, false caso a vaga já esteja ocupada.
     */
    public boolean estacionar() {
        if (disponivel) {
            disponivel = false;
            System.out.println("Veículo estacionado na " + id);
            return true;
        } else {
            System.out.println("Vaga ocupada, não é possível estacionar.");
            return false;
        }
    }

    /**
     * Libera a vaga, marcando-a como disponível.
     * @return true se o veículo saiu com sucesso, false caso a vaga já esteja vazia.
     */
    public boolean sair() {
        if (!disponivel) {
            disponivel = true;
            System.out.println("Veículo saiu da " + id);
            return true;
        } else {
            System.out.println("Vaga já está vazia, nenhum veículo para sair.");
            return false;
        }
    }

}
