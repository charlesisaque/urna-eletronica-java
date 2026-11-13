package br.edu.unijorge.urna.model;

public abstract class Candidato {
    private String numero;
    private String nome;
    private String partido;
    private String cargo;

    public Candidato(String numero, String nome, String partido, String cargo) {
        this.numero = numero;
        this.nome = nome;
        this.partido = partido;
        this.cargo = cargo;
    }

    // Método polimórfico: cada subclasse define quantos dígitos exige
    public abstract int getQuantidadeDigitos();

    public String getNumero() { return numero; }
    public String getNome() { return nome; }
    public String getPartido() { return partido; }
    public String getCargo() { return cargo; }
}