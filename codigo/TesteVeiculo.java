
public class TesteVeiculo {
  
    public static void main(String[] args) {
      
        Veiculo veiculo = new Veiculo("ABC123");
        Vaga vaga = new Vaga('A', 1);
        
        veiculo.estacionar(vaga); // Veículo com placa ABC123 estacionado na vaga A1
        veiculo.sair(); // Veículo com placa ABC123 saiu da vaga. Valor pago: R$10.0
        
        System.out.println("Total arrecadado: R$" + veiculo.totalArrecadado());
        System.out.println("Arrecadado no mês 10: R$" + veiculo.arrecadadoNoMes(10));
        System.out.println("Total de usos: " + veiculo.totalDeUsos());
    }
}
