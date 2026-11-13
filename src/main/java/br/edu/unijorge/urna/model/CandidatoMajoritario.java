package br.edu.unijorge.urna.model;

public class CandidatoMajoritario extends Candidato {
    public CandidatoMajoritario(String numero, String nome, String partido) {
        super(numero, nome, partido, "PREFEITO");
    }

    @Override
    public int getQuantidadeDigitos() {
        return 2;
    }
}
