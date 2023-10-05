

public class Vaga {

    private String id;
    private boolean disponivel;

    public Vaga(int fila, int numero) {
        
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public boolean estacionar() {
        return false;
    }

    public boolean sair() {
        return false;
    }

    public boolean disponivel() {
        return false;
    }
}