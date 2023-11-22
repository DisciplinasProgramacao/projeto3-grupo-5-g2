package enums;


public enum Turnos {

    MANHA("Manha", 08.00, 12.00, 200),
    TARDE("Tarde", 12.01, 18.00, 200),
    NOITE("Noite", 18.01, 23.59, 200);

    private String nome;
    private double horaInicio;
    private double horaFim;
    private double valor;

    Turnos (String nome, double horaInicio, double horaFim, double valor) {
        this.nome = nome;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public double getHoraInicio() {
        return horaInicio;
    }

    public double getHoraFim() {
        return horaFim;
    }

    public double getValor() {
        return valor;
    }
    public double setValor(double valor) {
        return this.valor = valor;
    }
}