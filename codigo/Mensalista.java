import java.time.LocalDateTime;

public class Mensalista implements ICategoriaCliente {
    public double sair(UsoDeVaga usoVaga, LocalDateTime horarioSaida) {
        return 0 + usoVaga.getValorServicos(horarioSaida);
    }

    public String getNome(){
        return "Mensalista";
    }
}
