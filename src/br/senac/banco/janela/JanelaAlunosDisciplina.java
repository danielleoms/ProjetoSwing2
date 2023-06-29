package br.senac.banco.janela;

import br.senac.banco.modelo.Conexao;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 * Classe responsável por criar a janela de pesquisa de alunos por disciplina.
 */
public class JanelaAlunosDisciplina {
	   /**
     * Cria e retorna uma instância da janela de pesquisa de alunos por disciplina.
     *
     * @return A instância da janela de pesquisa de alunos por disciplina.
     */
    public static JFrame criarJanelaAlunosDisciplina() {
        JFrame janelaAlunosDisciplina = new JFrame("Pesquisa de Alunos por Disciplina");
        janelaAlunosDisciplina.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        janelaAlunosDisciplina.setSize(400, 300);

        JPanel painel = new JPanel();
        janelaAlunosDisciplina.add(painel);

        JLabel lblIdDisciplina = new JLabel("ID da Disciplina:");
        final JTextField txtIdDisciplina = new JTextField(10);
        JButton botaoPesquisar = new JButton("Pesquisar");

        painel.add(lblIdDisciplina);
        painel.add(txtIdDisciplina);
        painel.add(botaoPesquisar);

        final JTextField caixaAluno1 = new JTextField(30);
        final JTextField caixaAluno2 = new JTextField(30);
        final JTextField caixaAluno3 = new JTextField(30);
        final JTextField caixaAluno4 = new JTextField(30);

        caixaAluno1.setEnabled(false);
        caixaAluno2.setEnabled(false);
        caixaAluno3.setEnabled(false);
        caixaAluno4.setEnabled(false);

        painel.add(caixaAluno1);
        painel.add(caixaAluno2);
        painel.add(caixaAluno3);
        painel.add(caixaAluno4);

        botaoPesquisar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int idDisciplina = Integer.parseInt(txtIdDisciplina.getText());
                List<String> alunos = buscarAlunosPorDisciplina(idDisciplina);

                if (alunos.size() > 0) {
                    caixaAluno1.setText(alunos.get(0));
                    caixaAluno1.setEnabled(true);
                } else {
                    caixaAluno1.setText("");
                    caixaAluno1.setEnabled(false);
                }

                if (alunos.size() > 1) {
                    caixaAluno2.setText(alunos.get(1));
                    caixaAluno2.setEnabled(true);
                } else {
                    caixaAluno2.setText("");
                    caixaAluno2.setEnabled(false);
                }

                if (alunos.size() > 2) {
                    caixaAluno3.setText(alunos.get(2));
                    caixaAluno3.setEnabled(true);
                } else {
                    caixaAluno3.setText("");
                    caixaAluno3.setEnabled(false);
                }

                if (alunos.size() > 3) {
                    caixaAluno4.setText(alunos.get(3));
                    caixaAluno4.setEnabled(true);
                } else {
                    caixaAluno4.setText("");
                    caixaAluno4.setEnabled(false);
                }
            }
        });

        return janelaAlunosDisciplina;
    }
    /**
     * Busca os alunos matriculados em uma disciplina pelo ID da disciplina.
     *
     * @param idDisciplina O ID da disciplina.
     * @return Uma lista contendo os nomes dos alunos matriculados na disciplina.
     */
    private static List<String> buscarAlunosPorDisciplina(int idDisciplina) {
        List<String> alunos = new ArrayList<>();

        Connection conexao = Conexao.conectaEscola();
        if (conexao != null) {
            try {
                String sql = "SELECT aluno.nome FROM aluno " +
                        "INNER JOIN matricula ON matricula.idAluno = aluno.cpf " +
                        "WHERE matricula.idDisciplina = ? " +
                        "LIMIT 4";
                PreparedStatement stmt = conexao.prepareStatement(sql);
                stmt.setInt(1, idDisciplina);

                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    String nomeAluno = rs.getString("nome");
                    alunos.add(nomeAluno);
                }

                stmt.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao buscar alunos matriculados na disciplina: " + e.getMessage());
            } finally {
                Conexao.fechaConexao(conexao);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Não foi possível conectar ao banco de dados");
        }

        return alunos;
    }

 
            
        
    }

