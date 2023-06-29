package br.senac.banco.teste;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import br.senac.banco.janela.JanelaAluno;
import br.senac.banco.janela.JanelaDisciplina;
import br.senac.banco.janela.JanelaProfessor;
import br.senac.banco.janela.JanelaAlunosDisciplina;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * 
 * @author caios
 * @see swing2.JanelAluno
 * @see swing2.JanelaDisciplina
 * @see swing2.JanelProfessor
 * @see swing2.JanelaAlunosDisciplina
 */
public class TesteSwing2 {

    public static void apresentarMenu() {
        // Define a janela principal
        JFrame janelaPrincipal = new JFrame("Banco Escola");
        janelaPrincipal.setResizable(false);
        janelaPrincipal.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        janelaPrincipal.setSize(400, 300);
        UIManager.put("OptionPane.yesButtonText", "Sim");
        UIManager.put("OptionPane.noButtonText", "Não");

        // Cria a barra de menus
        JMenuBar menuBar = new JMenuBar();
        janelaPrincipal.setJMenuBar(menuBar);

        // Define e adiciona o menu "Aluno" na barra de menus
        JMenu menuAluno = new JMenu("Aluno");
        menuBar.add(menuAluno);
       
        // Cria e adiciona o item de menu "Atualizar Aluno"
        JMenuItem menuAtualizarAluno = new JMenuItem("Atualizar Aluno");
        menuAluno.add(menuAtualizarAluno);

        // Cria a janela de atualização de aluno
        final JFrame janelaAluno = JanelaAluno.criarJanelaAluno();

        // Adiciona a ação para o item de menu "Atualizar Aluno"
        menuAtualizarAluno.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                janelaAluno.setVisible(true); //abrir a janela Aluno
            }
        });

        // Define e adiciona o menu "Professor"
        JMenu menuProfessor = new JMenu("Professor");
        menuBar.add(menuProfessor);
        
        // Cria e adiciona o item de menu "Atualizar Professor"
        JMenuItem menuAtualizarProfessor = new JMenuItem("Atualizar Professor");
        menuProfessor.add(menuAtualizarProfessor);

        // Cria a janela de atualização de professor
        final JFrame janelaProfessor = JanelaProfessor.criarJanelaProfessor();

        // Adiciona a ação para o item de menu "Atualizar Professor"
        menuAtualizarProfessor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                janelaProfessor.setVisible(true);
            }
        });

        // Define e adiciona o menu "Disciplina"
        JMenu menuDisciplina = new JMenu("Disciplina");
        menuBar.add(menuDisciplina);
        
        // Cria e adiciona o item de menu "Atualizar Disciplina"
        JMenuItem menuAtualizarDisciplina = new JMenuItem("Atualizar Disciplina");
        menuDisciplina.add(menuAtualizarDisciplina);

        // Cria a janela de atualização de disciplina
        final JFrame janelaDisciplina = JanelaDisciplina.criarJanelaDisciplina();

        // Adiciona a ação para o item de menu "Atualizar Disciplina"
        menuAtualizarDisciplina.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                janelaDisciplina.setVisible(true);
            }
        });

        // Cria e adiciona o item de menu "Pesquisar Alunos por Disciplina"
        JMenuItem menuAlunosDisciplina = new JMenuItem("Pesquisar Alunos por Disciplina");
        menuDisciplina.add(menuAlunosDisciplina);

        // Cria a janela de pesquisa de alunos por disciplina
        final JFrame janelaAlunosDisciplina = JanelaAlunosDisciplina.criarJanelaAlunosDisciplina();

        // Adiciona a ação para o item de menu "Pesquisar Alunos por Disciplina"
        menuAlunosDisciplina.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                janelaAlunosDisciplina.setVisible(true);
            }
        });

        // Define e adiciona o menu "Sair"
        JMenu menuSair = new JMenu("Sair");
        menuBar.add(menuSair);

        // Cria e adiciona o item de menu "Sair"
        JMenuItem menuSairItem = new JMenuItem("Sair do Programa");
        menuSair.add(menuSairItem);

        // Adiciona a ação para o item de menu "Sair"
        menuSairItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.exit(0);
            }
        });

        janelaPrincipal.setVisible(true);
    }

    public static void main(String[] args) {
        apresentarMenu();
    }
}
