

public class Vaga {

    private String id;
    private boolean disponivel;

    public Vaga(char fila, int numero) {
        this.id = "Vaga " + fila + numero;
        this.disponivel = true;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

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
