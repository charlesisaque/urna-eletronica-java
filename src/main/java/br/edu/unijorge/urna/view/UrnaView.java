package br.edu.unijorge.urna.view;

import br.edu.unijorge.urna.controller.UrnaController;
import br.edu.unijorge.urna.model.Candidato;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class UrnaView extends JFrame {
    private UrnaController controller;

    // Componentes do Visor
    private JLabel lblSeuVotoPara;
    private JLabel lblCargo;
    private JLabel lblDigitos;
    private JLabel lblNomeCandidato;
    private JLabel lblPartidoCandidato;
    private JLabel lblAviso;
    private JLabel lblRodapeInstrucoes;
    private JButton btnNovoEleitor;

    public UrnaView() {
        this.controller = new UrnaController();
        configurarJanela();
        inicializarComponentes();
        atualizarTela();
    }

    private void configurarJanela() {
        setTitle("Urna Cidadã - Simulador Educativo (UNIJORGE)");
        setSize(850, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout(15, 15));
        getContentPane().setBackground(new Color(230, 230, 225));
    }

    private void inicializarComponentes() {
        // --- PAINEL ESQUERDO: VISOR DA URNA ---
        JPanel painelVisor = new JPanel();
        painelVisor.setLayout(new BoxLayout(painelVisor, BoxLayout.Y_AXIS));
        painelVisor.setBackground(new Color(245, 248, 245));
        painelVisor.setBorder(BorderFactory.createCompoundBorder(
                new EmptyBorder(20, 20, 20, 10),
                new LineBorder(Color.DARK_GRAY, 2)
        ));
        painelVisor.setPreferredSize(new Dimension(490, 440));

        JPanel conteudoVisor = new JPanel();
        conteudoVisor.setLayout(new BoxLayout(conteudoVisor, BoxLayout.Y_AXIS));
        conteudoVisor.setBackground(new Color(245, 248, 245));
        conteudoVisor.setBorder(new EmptyBorder(15, 20, 15, 20));

        lblSeuVotoPara = new JLabel("SEU VOTO PARA");
        lblSeuVotoPara.setFont(new Font("Arial", Font.BOLD, 14));

        lblCargo = new JLabel("VEREADOR");
        lblCargo.setFont(new Font("Arial", Font.BOLD, 24));
        lblCargo.setBorder(new EmptyBorder(10, 30, 15, 0));

        lblDigitos = new JLabel("[ _ ] [ _ ] [ _ ] [ _ ] [ _ ]");
        lblDigitos.setFont(new Font("Monospaced", Font.BOLD, 28));
        lblDigitos.setBorder(new EmptyBorder(5, 0, 15, 0));

        lblNomeCandidato = new JLabel("Nome: ");
        lblNomeCandidato.setFont(new Font("Arial", Font.PLAIN, 16));
        lblNomeCandidato.setBorder(new EmptyBorder(5, 0, 5, 0));

        lblPartidoCandidato = new JLabel("Partido: ");
        lblPartidoCandidato.setFont(new Font("Arial", Font.PLAIN, 16));

        lblAviso = new JLabel("");
        lblAviso.setFont(new Font("Arial", Font.BOLD, 22));
        lblAviso.setForeground(new Color(180, 30, 30));
        lblAviso.setBorder(new EmptyBorder(15, 0, 15, 0));

        lblRodapeInstrucoes = new JLabel("<html><hr>Aperte a tecla:<br><b>CONFIRMA</b> para CONFIRMAR este voto<br><b>CORRIGE</b> para REINICIAR este voto</html>");
        lblRodapeInstrucoes.setFont(new Font("Arial", Font.PLAIN, 12));

        btnNovoEleitor = new JButton("Iniciar Próximo Eleitor");
        btnNovoEleitor.setVisible(false);
        btnNovoEleitor.addActionListener(e -> {
            controller.reiniciarVotacao();
            atualizarTela();
        });

        conteudoVisor.add(lblSeuVotoPara);
        conteudoVisor.add(lblCargo);
        conteudoVisor.add(lblDigitos);
        conteudoVisor.add(lblNomeCandidato);
        conteudoVisor.add(lblPartidoCandidato);
        conteudoVisor.add(lblAviso);
        conteudoVisor.add(Box.createVerticalGlue());
        conteudoVisor.add(lblRodapeInstrucoes);
        conteudoVisor.add(btnNovoEleitor);

        painelVisor.add(conteudoVisor);
        add(painelVisor, BorderLayout.CENTER);

        // --- PAINEL DIREITO: TECLADO DA URNA ---
        JPanel painelDireito = new JPanel(new BorderLayout(10, 10));
        painelDireito.setBackground(new Color(45, 45, 48));
        painelDireito.setBorder(new EmptyBorder(20, 20, 20, 20));
        painelDireito.setPreferredSize(new Dimension(320, 440));

        // Cabeçalho Justiça Eleitoral
        JLabel lblTituloJustica = new JLabel("JUSTIÇA ELEITORAL", SwingConstants.CENTER);
        lblTituloJustica.setForeground(Color.WHITE);
        lblTituloJustica.setFont(new Font("Arial", Font.BOLD, 16));
        lblTituloJustica.setBorder(new EmptyBorder(0, 0, 10, 0));
        painelDireito.add(lblTituloJustica, BorderLayout.NORTH);

        // Grade Numérica (1 a 9 e 0)
        JPanel painelNumeros = new JPanel(new GridLayout(4, 3, 10, 10));
        painelNumeros.setBackground(new Color(45, 45, 48));

        String[] teclas = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "", "0", ""};
        for (String tecla : teclas) {
            if (tecla.isEmpty()) {
                JLabel vazio = new JLabel();
                painelNumeros.add(vazio);
            } else {
                JButton btnNum = criarBotaoNumerico(tecla);
                painelNumeros.add(btnNum);
            }
        }
        painelDireito.add(painelNumeros, BorderLayout.CENTER);

        // Botões de Ação (BRANCO, CORRIGE, CONFIRMA)
        JPanel painelAcoes = new JPanel(new GridLayout(1, 3, 8, 0));
        painelAcoes.setBackground(new Color(45, 45, 48));
        painelAcoes.setBorder(new EmptyBorder(15, 0, 0, 0));

        JButton btnBranco = criarBotaoAcao("BRANCO", Color.WHITE, Color.BLACK);
        btnBranco.addActionListener(e -> {
            tocarBipe();
            controller.acionarBranco();
            atualizarTela();
        });

        JButton btnCorrige = criarBotaoAcao("CORRIGE", new Color(230, 110, 30), Color.BLACK);
        btnCorrige.addActionListener(e -> {
            tocarBipe();
            controller.acionarCorrige();
            atualizarTela();
        });

        JButton btnConfirma = criarBotaoAcao("CONFIRMA", new Color(40, 167, 69), Color.BLACK);
        btnConfirma.addActionListener(e -> {
            if (controller.confirmarVoto()) {
                tocarBipe();
                atualizarTela();
            }
        });

        painelAcoes.add(btnBranco);
        painelAcoes.add(btnCorrige);
        painelAcoes.add(btnConfirma);

        painelDireito.add(painelAcoes, BorderLayout.SOUTH);
        add(painelDireito, BorderLayout.EAST);
    }

    private JButton criarBotaoNumerico(String numero) {
        JButton btn = new JButton(numero);
        btn.setFont(new Font("Arial", Font.BOLD, 20));
        btn.setBackground(new Color(25, 25, 25));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.addActionListener(e -> {
            tocarBipe();
            controller.digitarNumero(numero);
            atualizarTela();
        });
        return btn;
    }

    private JButton criarBotaoAcao(String texto, Color fundo, Color textoCor) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Arial", Font.BOLD, 11));
        btn.setBackground(fundo);
        btn.setForeground(textoCor);
        btn.setPreferredSize(new Dimension(85, 45));
        btn.setFocusPainted(false);
        return btn;
    }

    private void atualizarTela() {
        if (controller.getEtapaAtual() > 1) {
            // Tela FIM
            lblSeuVotoPara.setVisible(false);
            lblCargo.setText("");
            lblDigitos.setText("FIM");
            lblDigitos.setFont(new Font("Arial", Font.BOLD, 64));
            lblNomeCandidato.setText("Votação concluída com sucesso!");
            lblPartidoCandidato.setText("");
            lblAviso.setText("");
            lblRodapeInstrucoes.setVisible(false);
            btnNovoEleitor.setVisible(true);
            return;
        }

        // Restaura visualização normal
        lblSeuVotoPara.setVisible(true);
        lblRodapeInstrucoes.setVisible(true);
        btnNovoEleitor.setVisible(false);
        lblDigitos.setFont(new Font("Monospaced", Font.BOLD, 28));

        String cargo = controller.getCargoAtual();
        lblCargo.setText(cargo);

        if (controller.isVotoBranco()) {
            lblDigitos.setText("");
            lblNomeCandidato.setText("");
            lblPartidoCandidato.setText("");
            lblAviso.setText("VOTO EM BRANCO");
            return;
        }

        // Monta caixinhas de dígitos
        String digitado = controller.getDigitosAtual();
        int max = controller.getMaxDigitosEtapa();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < max; i++) {
            if (i < digitado.length()) {
                sb.append("[").append(digitado.charAt(i)).append("] ");
            } else {
                sb.append("[_] ");
            }
        }
        lblDigitos.setText(sb.toString());

        // Verifica candidato se completou os dígitos
        if (digitado.length() == max) {
            Candidato c = controller.buscarCandidato(digitado, cargo);
            if (c != null) {
                lblNomeCandidato.setText("Nome: " + c.getNome());
                lblPartidoCandidato.setText("Partido: " + c.getPartido());
                lblAviso.setText("");
            } else {
                lblNomeCandidato.setText("NÚMERO ERRADO");
                lblPartidoCandidato.setText("");
                lblAviso.setText("VOTO NULO");
            }
        } else {
            lblNomeCandidato.setText("Nome: ");
            lblPartidoCandidato.setText("Partido: ");
            lblAviso.setText("");
        }
    }

    private void tocarBipe() {
        Toolkit.getDefaultToolkit().beep();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new UrnaView().setVisible(true);
        });
    }
}