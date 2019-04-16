package br.trabalho3.frontend;

import br.trabalho3.backend.LexicalError;
import br.trabalho3.backend.WordAnalyser;
import br.trabalho3.utils.NumberedBorder;

import javax.swing.*;
import java.io.File;

/**
 * Classe que representa a parte gráfica do programa.
 */
public class Main extends javax.swing.JFrame {

    /**
     * Frame/tela principal da aplicação.
     */
    private static final Main FRAME_PRINCIPAL = new Main();

    public Main() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        barraDeRolagemInput = new javax.swing.JScrollPane(inputTextArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        inputTextArea = new javax.swing.JTextArea();
        barraDeRolagemOuput = new javax.swing.JScrollPane(inputTextArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        outputTextArea = new javax.swing.JTextArea();
        File file = new File("");
        System.out.println(file.getAbsolutePath());
        btnAnalisar = new javax.swing.JButton();
        btnEquipe = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setMinimumSize(new java.awt.Dimension(870, 670));
        setResizable(false);
        setSize(new java.awt.Dimension(870, 670));

        inputTextArea.setBorder(new NumberedBorder());
        inputTextArea.setColumns(20);
        inputTextArea.setRows(5);
        barraDeRolagemInput.setViewportView(inputTextArea);

        outputTextArea.setEditable(false);
        outputTextArea.setColumns(20);
        outputTextArea.setRows(5);
        barraDeRolagemOuput.setViewportView(outputTextArea);

        btnAnalisar.setText("Analisar");
        btnAnalisar.setIcon(new ImageIcon("src/main/java/br/trabalho3/icons/start-icon.png"));
        btnAnalisar.setFocusPainted(false);
        btnAnalisar.addActionListener(evt1 -> btnAnalisarActionPerformed());

        btnEquipe.setText("Equipe");
        btnEquipe.setIcon(new ImageIcon("src/main/java/br/trabalho3/icons/team-icon.png"));
        btnEquipe.setFocusPainted(false);
        btnEquipe.addActionListener(evt -> btnEquipeActionPerformed());

        btnLimpar.setText("Limpar");
        btnLimpar.setIcon(new ImageIcon("src/main/java/br/trabalho3/icons/scissor-icon.png"));
        btnLimpar.setFocusPainted(false);
        btnLimpar.addActionListener(evt -> btnLimparActionPerformed());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(barraDeRolagemInput)
                        .addComponent(barraDeRolagemOuput)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(btnAnalisar, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEquipe, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(barraDeRolagemInput, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnAnalisar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(btnEquipe, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(btnLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(barraDeRolagemOuput, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }

    private void btnLimparActionPerformed() {
        // Limpa as duas áreas de texto do programa
        inputTextArea.setText("");
        outputTextArea.setText("");
    }

    private void btnEquipeActionPerformed() {
        outputTextArea.setText("FURB\nSistemas de Informação 2018/1\n\nEquipe de desenvolvimento:\n\nAlan Boaventura\nBryan Leite");
    }

    private void btnAnalisarActionPerformed() {
        String text = inputTextArea.getText();

        String resultado;
        try {
            resultado = WordAnalyser.analyseText(text);
        } catch (LexicalError e) {
            outputTextArea.setText(e.getMessage());
            return;
        }

        outputTextArea.setText(resultado);
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> {
            FRAME_PRINCIPAL.setVisible(true);
            FRAME_PRINCIPAL.setLocationRelativeTo(null); // Faz o frame iniciar no centro da tela.
        });
    }

    private javax.swing.JScrollPane barraDeRolagemInput;
    private javax.swing.JScrollPane barraDeRolagemOuput;
    private javax.swing.JButton btnAnalisar;
    private javax.swing.JButton btnEquipe;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JTextArea inputTextArea;
    private javax.swing.JTextArea outputTextArea;
}
