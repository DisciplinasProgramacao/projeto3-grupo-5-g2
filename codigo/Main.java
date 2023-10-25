public class Main {
    public static void main(String[] args) {

        Vaga vaga1 = new Vaga(1, 1);
        Vaga vaga2 = new Vaga(1, 2);

        vaga1.estacionar(); // Veículo estacionado na vaga F1-N1
        vaga2.estacionar(); // Veículo estacionado na vaga F1-N2

        vaga1.sair(); // Veículo saiu da vaga F1-N1
        vaga1.estacionar(); // Veículo estacionado na vaga F1-N1        
    }
}
