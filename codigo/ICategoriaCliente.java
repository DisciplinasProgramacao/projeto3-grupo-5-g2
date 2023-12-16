import java.time.LocalDateTime;

public interface ICategoriaCliente {
    double sair(UsoDeVaga usoVaga, LocalDateTime horario);
    String getNome();
}
