import java.time.LocalDateTime;
import java.time.LocalTime;

public interface CategoriaCliente {
    double sair(UsoDeVaga usoVaga, LocalDateTime horario);
}
