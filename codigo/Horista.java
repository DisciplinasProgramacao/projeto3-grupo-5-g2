import java.time.LocalTime;

public class Horista extends CategoriaCliente{

    @Override
    public double sair(UsoDeVaga usoVaga, LocalTime horario) {
        int tempoGastoSegundos = horario.toSecondOfDay() - usoVaga.getEntrada().toLocalTime().toSecondOfDay();
        double tempoGastoHoras = ((double) tempoGastoSegundos) / (60 * 60);
        double fracoesUtilizadas = Math.floor(tempoGastoHoras / UsoDeVaga.FRACAO_USO);
        double valorTotal = Math.min(UsoDeVaga.VALOR_MAXIMO, fracoesUtilizadas * UsoDeVaga.VALOR_FRACAO);

        return valorTotal;
    }
}
