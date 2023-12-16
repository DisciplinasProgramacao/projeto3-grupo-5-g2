public enum Servicos {

    MANOBRISTA ("Manobrista",5.0,0),
    LAVAGEM ("Lavagem",20.0,60),
    POLIMENTO ("Polimento",45.0, 0);

    private String nome;
    private Double valor;
    private int tempo;

    /**
     * Construtor dos Servicos.<br>
     * // TODO: Alterar o nome de Servicos para Servico)
     */
    Servicos(String nome, double valor, int tempo){
        this.nome= nome;
        this.valor = valor;
        this.tempo = tempo;   
    }

    /**
     * Obtem o nome do Serviço.<br>
     *
     * @return String Nome do serviço
     */
    public String getNome() {
        return nome;
    }

    /**
     * Obtem o tempo necessário para o serviço.<br>
     *
     * @return int Horas necessárias para o serviço
     */
    public int getTempo() {
        return tempo;
    }

    /**
     * Obtem o valor do serviço.<br>
     *
     * @return double Valor do serviço
     */
    public double getValor() {
        return valor;
    }
  
}