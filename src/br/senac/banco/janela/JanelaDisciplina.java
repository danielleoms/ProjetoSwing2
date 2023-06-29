package br.senac.banco.janela;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import br.senac.banco.modelo.Disciplina;
import br.senac.banco.modelo.Professor;
/**
 * Classe responsável por criar a janela de atualização de disciplina.
 * @see swing2.Disciplina
 * @see swing2.Professor
 */
public class JanelaDisciplina {
	/**
     * Cria a janela de atualização de disciplina.
     *
     * @return A instância da janela de disciplina criada.
     */
    public static JFrame criarJanelaDisciplina() {
        // Define a janela
        final JFrame janelaDisciplina = new JFrame("Atualização de Disciplina");
        janelaDisciplina.setResizable(false);
        janelaDisciplina.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        janelaDisciplina.setSize(600, 350);
        // Define o layout da janela
        Container caixa = janelaDisciplina.getContentPane();
        caixa.setLayout(null);
        // Define os labels dos campos
        JLabel labelID = new JLabel("ID: ");
        JLabel labelNome = new JLabel("Nome: ");
        JLabel labelDescricao = new JLabel("Descrição: ");
        JLabel labelProfessor = new JLabel("Professor: ");
        JLabel labelNomeProfessor = new JLabel("Nome do Professor: ");
        // Posiciona os labels na janela
        labelID.setBounds(50, 40, 100, 20);
        labelNome.setBounds(50, 80, 100, 20);
        labelDescricao.setBounds(50, 120, 130, 20);
        labelProfessor.setBounds(50, 160, 100, 20);
        labelNomeProfessor.setBounds(50, 200, 130, 20);
        // Define os input box
        final JTextField jTextID = new JTextField();
        final JTextField jTextNome = new JTextField();
        final JTextField jTextDescricao = new JTextField();
        final JTextField jTextCPFProfessor = new JTextField();
        final JTextField jTextNomeProfessor = new JTextField();
        
        
        // Define se os campos estão habilitados ou não no início
        jTextID.setEnabled(true);
        jTextNome.setEnabled(false);
        jTextDescricao.setEnabled(false);
        jTextCPFProfessor.setEnabled(false);
        jTextNomeProfessor.setEnabled(false);
        
        // Posiciona os input box
        
        jTextID.setBounds(180, 40, 50, 20);
        jTextNome.setBounds(180, 80, 150, 20);
        jTextDescricao.setBounds(180, 120, 250, 20);
        jTextCPFProfessor.setBounds(180, 160, 100, 20);
        jTextNomeProfessor.setBounds(180, 200, 150, 20);
        
        // Adiciona os rótulos e os input box na janela
        
        janelaDisciplina.add(labelID);
        janelaDisciplina.add(labelNome);
        janelaDisciplina.add(labelDescricao);
        janelaDisciplina.add(labelProfessor);
        janelaDisciplina.add(labelNomeProfessor);
        janelaDisciplina.add(jTextID);
        janelaDisciplina.add(jTextNome);
        janelaDisciplina.add(jTextDescricao);
        janelaDisciplina.add(jTextCPFProfessor);
        janelaDisciplina.add(jTextNomeProfessor);
        // Define botões e a localização deles na janela
        final JButton botaoConsultar = new JButton("Consultar");
        botaoConsultar.setBounds(50, 250, 100, 20);
        janelaDisciplina.add(botaoConsultar);
        final JButton botaoGravar = new JButton("Gravar");
        botaoGravar.setBounds(180, 250, 100, 20);
        botaoGravar.setEnabled(true);
        janelaDisciplina.add(botaoGravar);
        final JButton botaoLimpar = new JButton("Limpar");
        botaoLimpar.setBounds(310, 250, 100, 20);
        janelaDisciplina.add(botaoLimpar);
        final JButton botaoDeletar = new JButton("Deletar");
        botaoDeletar.setBounds(450, 250, 100, 20);
        botaoDeletar.setEnabled(true);
        janelaDisciplina.add(botaoDeletar);
        		
        // Define objeto disciplina para pesquisar no banco de dados
        final Disciplina disciplina = new Disciplina();
        // Define ações dos botões
        botaoConsultar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int id =  Integer.parseInt(jTextID.getText());
                    
                    botaoGravar.setEnabled(true);
                    String nome;
                    String descricao;
                    String cpfProfessor;
                    if (!disciplina.consultarDisciplina(id)) {
                        nome = "";
                        descricao = "";
                        cpfProfessor = "";
                    } else {
                        nome = disciplina.getNome();
                        jTextNome.setText(nome);
                        descricao = disciplina.getDescricao();
                        jTextDescricao.setText(descricao);
                        cpfProfessor = disciplina.getcpfProfessor();
                        jTextCPFProfessor.setText(cpfProfessor);
                        String nomeProfessor = buscarNomeProfessor(cpfProfessor);
                        jTextNomeProfessor.setText(nomeProfessor);
                    }
                    
                   
                  
                    botaoConsultar.setEnabled(false);
                    jTextID.setEnabled(true);
                    jTextNome.setEnabled(true);
                    jTextDescricao.setEnabled(true);
                    jTextCPFProfessor.setEnabled(true);
                    jTextNome.requestFocus();
                } catch (Exception erro) {
                    JOptionPane.showMessageDialog(janelaDisciplina, "Preencha o campo ID corretamente!");
                }
            }

			private String buscarNomeProfessor(String cpfProfessor) {
				// TODO Auto-generated method stub
				String nomeEncontrado = "";

			    Professor professor = new Professor();
			    if (professor.consultarProfessor(cpfProfessor)) {
			        nomeEncontrado = professor.getNome();
			    }

			    return nomeEncontrado;
			}
        });
       

        botaoGravar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int resposta = JOptionPane.showConfirmDialog(janelaDisciplina, "Deseja atualizar?", "Confirmação",
                        JOptionPane.YES_NO_OPTION);
                if (resposta == JOptionPane.YES_OPTION) {
                    int id =  Integer.parseInt(jTextID.getText());
                    String nome = jTextNome.getText().trim();
                    String descricao = jTextDescricao.getText().trim();
                    String cpfProfessor = jTextCPFProfessor.getText().trim();
                    if (id == 0) {
                        JOptionPane.showMessageDialog(janelaDisciplina, "Preencha o campo ID!");
                        jTextID.requestFocus();
                    } else if (nome.length() == 0) {
                        JOptionPane.showMessageDialog(janelaDisciplina, "Preencha o campo nome!");
                        jTextNome.requestFocus();
                    } else if (descricao.length() == 0) {
                        JOptionPane.showMessageDialog(janelaDisciplina, "Preencha o descrição!");
                        jTextDescricao.requestFocus();
                    } else if (cpfProfessor.length() == 0) {
                        JOptionPane.showMessageDialog(janelaDisciplina, "Preencha o campo Professor(cpf)!");
                        jTextCPFProfessor.requestFocus();
                    } else {
                        try {
                            if (!disciplina.consultarDisciplina(id)) {
                                if (!disciplina.cadastrarDisciplina(id, nome, descricao, cpfProfessor)) {
                                    JOptionPane.showMessageDialog(janelaDisciplina, "Erro na inclusão de disciplina!");
                                } else {
                                    JOptionPane.showMessageDialog(janelaDisciplina, "Inclusão realizada!");
                                }
                            } else {
                                if (!disciplina.atualizarDisciplina(id, nome, descricao, cpfProfessor)) {
                                    JOptionPane.showMessageDialog(janelaDisciplina, "Erro na atualização do professor!");
                                } else {
                                    JOptionPane.showMessageDialog(janelaDisciplina, "Alteração realizada!");
                                }
                            }
                        } catch (Exception erro) {
                            JOptionPane.showMessageDialog(janelaDisciplina, "Erro nao reconhecido!");
                        }
                    }
                }
            }
        });
        botaoLimpar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                jTextID.setText("");
                jTextNome.setText("");
                jTextDescricao.setText("");
                jTextCPFProfessor.setText("");
                jTextNomeProfessor.setText("");
                jTextID.setEnabled(true);
                jTextNome.setEnabled(true);
                jTextDescricao.setEnabled(true);
                jTextCPFProfessor.setEnabled(true);
                jTextNomeProfessor.setEnabled(false);
                botaoConsultar.setEnabled(true);
                botaoGravar.setEnabled(true);
                botaoDeletar.setEnabled(true);
                jTextID.requestFocus();
            }
        });
        botaoDeletar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int resposta = JOptionPane.showConfirmDialog(janelaDisciplina, "Deseja excluir disciplina?", "Confirmação",
                        JOptionPane.YES_NO_OPTION);
                if (resposta == JOptionPane.YES_OPTION) {
                    // Desabilitar campos
                    jTextNome.setEnabled(false);
                    jTextDescricao.setEnabled(false);
                    jTextCPFProfessor.setEnabled(false);
                    
                    // Desabilitar botões
                    botaoGravar.setEnabled(false);
                    botaoConsultar.setEnabled(false);
                    botaoLimpar.setEnabled(true); // Manter o botão Limpar habilitado
                    
                    // Realizar a exclusão da disciplina
                    int id = Integer.parseInt(jTextID.getText());
                    boolean exclusaoRealizada = disciplina.deletarDisciplina(id);
                    if (exclusaoRealizada) {
                        JOptionPane.showMessageDialog(janelaDisciplina, "Disciplina excluída com sucesso!");
                       
                        jTextID.setText("");
                        jTextNome.setText("");
                        jTextDescricao.setText("");
                        jTextCPFProfessor.setText("");
                        jTextID.setEnabled(true);
                        jTextID.requestFocus();
                    } else {
                        JOptionPane.showMessageDialog(janelaDisciplina, "Erro ao excluir disciplina!");
                    }
                }
            }
        });
   
        return janelaDisciplina;
    }
}
