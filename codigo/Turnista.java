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
            return 0d;
        }else{
            return super.sair(usoVaga, horarioSaida);
        }
    }
}
