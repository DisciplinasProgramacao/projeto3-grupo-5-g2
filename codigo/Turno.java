import java.time.LocalTime;

public enum Turno {
    MANHA(LocalTime.of(8, 0, 0), LocalTime.of(12, 0, 0), "Manha"),
    TARDE(LocalTime.of(12, 1, 0), LocalTime.of(18, 0, 0), "Tarde"),
    NOITE(LocalTime.of(18, 1, 0), LocalTime.of(23, 59, 0), "Noite");
    private LocalTime inicio, fim;
    private String nome;

    public String getNome() {
        return nome;
    }

    /**
     * Constroi o Turno com suas configurações<br>
     *
     * @param inicio Inicio do turno
     * @param fim Fim do turno
     * @param nome Nome do turno
     * @return void
     */
    Turno(LocalTime inicio, LocalTime fim, String nome){
        this.inicio = inicio;
        this.fim = fim;
        this.nome = nome;
    }

    /**
     * Verifica se determinado horário está dentro de um turno<br>
     *
     * @param horario Horário para verificar
     * @return boolean "true" caso o horário esteja dentro do turno
     */
    public boolean verificarDentroTurno(LocalTime horario){
        return horario.isAfter(this.inicio) && horario.isBefore(this.fim);
    }
}
