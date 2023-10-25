import org.junit.Test;
import static org.junit.Assert.*;

public class VeiculoTest {

    @Test
    public void testEstacionarESair() {
        Veiculo veiculo = new Veiculo("ABC123");
        Vaga vaga = new Vaga('A', 1);
        
        veiculo.estacionar(vaga);
        assertFalse(vaga.isDisponivel()); // Verifica se a vaga foi marcada como ocupada após estacionar
        
        double valorPago = veiculo.sair();
        assertTrue(vaga.isDisponivel()); // Verifica se a vaga foi marcada como disponível após sair
        assertEquals(10.0, valorPago, 0.01); // Verifica se o valor pago é correto (considerando um valor de exemplo)
    }

    @Test
    public void testTotalArrecadado() {
        Veiculo veiculo = new Veiculo("ABC123");
        Vaga vaga1 = new Vaga('A', 1);
        Vaga vaga2 = new Vaga('A', 2);

        veiculo.estacionar(vaga1);
        veiculo.estacionar(vaga2);
        veiculo.sair();
        veiculo.sair();

        assertEquals(20.0, veiculo.totalArrecadado(), 0.01); // Verifica se o total arrecadado é correto
    }

    // Adicione mais métodos de teste conforme necessário
}
