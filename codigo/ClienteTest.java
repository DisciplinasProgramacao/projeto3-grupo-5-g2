import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class ClienteTest {

    private Cliente cliente;
    private Veiculo veiculo;
    private Estacionamento estacionamento;

    @Before
    public void setUp() {
        cliente = new Cliente("João", "12345");
        veiculo = new Veiculo("ABC123", "Carro");
        estacionamento = new Estacionamento(veiculo, 10.0);
    }

    @Test
    public void testAdicionarVeiculo() {
        cliente.addVeiculo(veiculo);
        assertTrue(cliente.getVeiculos().contains(veiculo));
    }

    @Test
    public void testVerificarSePossuiVeiculo() {
        cliente.addVeiculo(veiculo);
        Veiculo encontrado = cliente.possuiVeiculo("ABC123");
        assertNotNull(encontrado);
        assertEquals("ABC123", encontrado.getPlaca());
    }

    @Test
    public void testTotalDeUsos() {
        cliente.registrarEstacionamento(estacionamento);
        assertEquals(1, cliente.totalDeUsos());
    }

    @Test
    public void testArrecadadoPorVeiculo() {
        cliente.registrarEstacionamento(estacionamento);
        double valorArrecadado = cliente.arrecadadoPorVeiculo("ABC123");
        assertEquals(10.0, valorArrecadado, 0.01); 
    }

    @Test
    public void testArrecadadoTotal() {
        cliente.registrarEstacionamento(estacionamento);
        assertEquals(10.0, cliente.arrecadadoTotal(), 0.01); 
    }

    @Test
    public void testArrecadadoNoMes() {
        cliente.registrarEstacionamento(estacionamento);
        assertEquals(10.0, cliente.arrecadadoNoMes(estacionamento.getData().getMonthValue()), 0.01);
    }
}
