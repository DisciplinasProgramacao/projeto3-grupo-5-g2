import static org.junit.Assert.assertEquals;

import org.junit.Test;

import entities.Vaga;

public class TestVaga {

    @Test
    public void testEstacionarVagaNaoDisponivel() {
        Vaga vaga = new Vaga(1, 1);
        vaga.disponivel = false;
        assertEquals(false, vaga.estacionar());
    }

    @Test
    public void testSairVagaDisponivel() {
        Vaga vaga = new Vaga(1, 1);
        assertEquals(true, vaga.sair());
    }

    @Test
    public void testSairVagaNaoDisponivel() {
        Vaga vaga = new Vaga(1, 1);
        vaga.disponivel = false;
        assertEquals(true, vaga.sair());
    }

    @Test
    public void testDisponivelVagaDisponivel() {
        Vaga vaga = new Vaga(1, 1);
        vaga.disponivel = true;
        assertEquals(true, vaga.disponivel());
    }

    @Test
    public void testDisponivelVagaNaoDisponivel() {
        Vaga vaga = new Vaga(1, 1);
        vaga.disponivel = false;
        assertEquals(false, vaga.disponivel());
    }
}
