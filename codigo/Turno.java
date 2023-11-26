import java.time.LocalTime;

public enum Turno {
    MANHA(LocalTime.of(8, 0, 0), LocalTime.of(12, 0, 0)),
    TARDE(LocalTime.of(12, 1, 0), LocalTime.of(18, 0, 0)),
    NOITE(LocalTime.of(18, 1, 0), LocalTime.of(23, 59, 0));
    private LocalTime inicio, fim;

    Turno(LocalTime inicio, LocalTime fim){
        this.inicio = inicio;
        this.fim = fim;
    }

    public boolean verificarDentroTurno(LocalTime horario){
        return horario.isAfter(this.inicio) && horario.isBefore(this.fim);
    }
}
