import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Horista implements CategoriaCliente{
    public double sair(UsoDeVaga usoVaga, LocalDateTime horarioSaida) {
        long tempoGastoSegundos = Duration.between(usoVaga.getEntrada(), horarioSaida).getSeconds();
        double tempoGastoHoras = ((double) tempoGastoSegundos) / (60 * 60);
        double fracoesUtilizadas = Math.floor(tempoGastoHoras / UsoDeVaga.FRACAO_USO);
        double valorTotal = Math.min(UsoDeVaga.VALOR_MAXIMO, fracoesUtilizadas * UsoDeVaga.VALOR_FRACAO);

        return valorTotal;
    }
}
