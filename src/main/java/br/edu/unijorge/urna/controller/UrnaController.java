package br.edu.unijorge.urna.controller;

import br.edu.unijorge.urna.model.*;
import br.edu.unijorge.urna.service.FirebaseService;

import java.util.ArrayList;
import java.util.List;

public class UrnaController {
    private List<Candidato> candidatos;
    private FirebaseService firebaseService;

    // 0 = Vereador (Proporcional), 1 = Prefeito (Majoritário), 2 = FIM
    private int etapaAtual = 0;
    private StringBuilder digitosAtual = new StringBuilder();
    private boolean votoBranco = false;

    public UrnaController() {
        this.firebaseService = new FirebaseService();
        this.candidatos = new ArrayList<>();
        carregarCandidatosFicticios();
    }

    private void carregarCandidatosFicticios() {
        // Candidatos a Vereador (5 dígitos) - Temáticos e apartidários
        candidatos.add(new CandidatoProporcional("10101", "Ada Lovelace", "Partido da Programação (PPROG)"));
        candidatos.add(new CandidatoProporcional("20202", "Alan Turing", "Partido da Lógica (PLOG)"));
        candidatos.add(new CandidatoProporcional("30303", "Grace Hopper", "Partido dos Compiladores (PCOMP)"));

        // Candidatos a Prefeito (2 dígitos) - Temáticos e apartidários
        candidatos.add(new CandidatoMajoritario("10", "Linus Torvalds", "Partido do Código Aberto (PCA)"));
        candidatos.add(new CandidatoMajoritario("20", "Margaret Hamilton", "Partido da Engenharia (PENG)"));
    }

    public String getCargoAtual() {
        if (etapaAtual == 0) return "VEREADOR";
        if (etapaAtual == 1) return "PREFEITO";
        return "FIM";
    }

    public int getMaxDigitosEtapa() {
        if (etapaAtual == 0) return 5;
        if (etapaAtual == 1) return 2;
        return 0;
    }

    public void digitarNumero(String num) {
        if (etapaAtual > 1 || votoBranco) return;
        if (digitosAtual.length() < getMaxDigitosEtapa()) {
            digitosAtual.append(num);
        }
    }

    public void acionarBranco() {
        if (etapaAtual > 1) return;
        digitosAtual.setLength(0);
        votoBranco = true;
    }

    public void acionarCorrige() {
        if (etapaAtual > 1) return;
        digitosAtual.setLength(0);
        votoBranco = false;
    }

    public boolean confirmarVoto() {
        if (etapaAtual > 1) return false;

        // Só permite confirmar se for branco ou se todos os dígitos do cargo foram preenchidos
        if (!votoBranco && digitosAtual.length() < getMaxDigitosEtapa()) {
            return false;
        }

        String cargo = getCargoAtual();
        if (votoBranco) {
            firebaseService.registrarVoto(new Voto(cargo, "BRANCO", "BRANCO"));
        } else {
            Candidato c = buscarCandidato(digitosAtual.toString(), cargo);
            if (c != null) {
                firebaseService.registrarVoto(new Voto(cargo, c.getNumero(), "NOMINAL"));
            } else {
                firebaseService.registrarVoto(new Voto(cargo, digitosAtual.toString(), "NULO"));
            }
        }

        // Avança para o próximo cargo
        etapaAtual++;
        digitosAtual.setLength(0);
        votoBranco = false;
        return true;
    }

    public Candidato buscarCandidato(String numero, String cargo) {
        for (Candidato c : candidatos) {
            if (c.getCargo().equals(cargo) && c.getNumero().equals(numero)) {
                return c;
            }
        }
        return null;
    }

    public void reiniciarVotacao() {
        etapaAtual = 0;
        digitosAtual.setLength(0);
        votoBranco = false;
    }

    public String getDigitosAtual() { return digitosAtual.toString(); }
    public boolean isVotoBranco() { return votoBranco; }
    public int getEtapaAtual() { return etapaAtual; }
}