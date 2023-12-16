import java.time.LocalDateTime;

public interface ICategoriaCliente {

    /**
     * Calcula o valor total para ser pago com as regras da categoria.
     *
     * @return double Valor total à ser pago.
     */
    double sair(UsoDeVaga usoVaga, LocalDateTime horario);

    /**
     * Retorna o nome da categoria
     *
     * @return String Nome da categoria.
     */
    String getNome();
}
