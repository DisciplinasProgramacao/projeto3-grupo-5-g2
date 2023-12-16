import java.time.Duration;
import java.time.LocalDateTime;

public class Horista implements ICategoriaCliente {
    public double sair(UsoDeVaga usoVaga, LocalDateTime horarioSaida) {
        long tempoGastoSegundos = Duration.between(usoVaga.getEntrada(), horarioSaida).getSeconds();
        double tempoGastoHoras = ((double) tempoGastoSegundos) / (60 * 60);
        double fracoesUtilizadas = Math.floor(tempoGastoHoras / UsoDeVaga.FRACAO_USO);
        double valorTotal = Math.min(UsoDeVaga.VALOR_MAXIMO, fracoesUtilizadas * UsoDeVaga.VALOR_FRACAO);
        valorTotal += usoVaga.getValorServicos(horarioSaida);
        return valorTotal;
    }

    public String getNome(){
        return "Horista";
    }
}
