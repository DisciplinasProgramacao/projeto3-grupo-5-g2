import java.time.LocalDateTime;
import java.time.LocalTime;

public class Turnista extends Horista{
    private Turno turno;

    public Turnista(Turno turno){
        this.turno = turno;
    }

    public Turnista() {}

    public void setTurno(Turno turno) {
        this.turno = turno;
    }

    @Override
    public double sair(UsoDeVaga usoVaga, LocalDateTime horarioSaida){
        if(this.turno.verificarDentroTurno(LocalTime.from(horarioSaida))){
            return 0 + usoVaga.getValorServicos(horarioSaida);
        }else{
            return super.sair(usoVaga, horarioSaida);
        }
    }

    @Override
    public String getNome(){
        return String.format("Turnista (%s)", this.turno.getNome());
    }
}
