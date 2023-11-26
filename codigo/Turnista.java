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
    public double sair(UsoDeVaga usoVaga, LocalTime horario){
        if(this.turno.verificarDentroTurno(horario)){
            return 0d;
        }else{
            return super.sair(usoVaga, horario);
        }
    }
}
