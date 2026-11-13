package br.edu.unijorge.urna.model;

public class CandidatoProporcional extends Candidato {
    public CandidatoProporcional(String numero, String nome, String partido) {
        super(numero, nome, partido, "VEREADOR");
    }

    @Override
    public int getQuantidadeDigitos() {
        return 5;
    }
}