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

import br.senac.banco.modelo.Professor;
/**
 * Classe responsável por criar a janela de atualização de professor.
 * @see swing2.Professor
 */
public class JanelaProfessor {
	/**
     * Cria a janela de atualização de professor.
     *
     * @return A instância da janela de professor criada.
     */
    public static JFrame criarJanelaProfessor() {
        // Define a janela
        final JFrame janelaProfessor = new JFrame("Atualização de Professor");
        janelaProfessor.setResizable(false);
        janelaProfessor.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        janelaProfessor.setSize(600, 300);
        // Define o layout da janela
        Container caixa = janelaProfessor.getContentPane();
        caixa.setLayout(null);
        // Define os labels dos campos
        JLabel labelCPF = new JLabel("CPF: ");
        JLabel labelNome = new JLabel("Nome: ");
        JLabel labelDataNascimento = new JLabel("Data de Nascimento: ");
        JLabel labelEmail = new JLabel("Email: ");
        // Posiciona os labels na janela
        labelCPF.setBounds(50, 40, 100, 20);
        labelNome.setBounds(50, 80, 100, 20);
        labelDataNascimento.setBounds(50, 120, 130, 20);
        labelEmail.setBounds(50, 160, 100, 20);
        // Define os input box
        final JTextField jTextCPF = new JTextField();
        final JTextField jTextNome = new JTextField();
        final JTextField jTextDataNascimento = new JTextField();
        final JTextField jTextEmail = new JTextField();
        // Define se os campos estão habilitados ou não no início
        jTextCPF.setEnabled(true);
        jTextNome.setEnabled(false);
        jTextDataNascimento.setEnabled(false);
        jTextEmail.setEnabled(false);
        // Posiciona os input box
        
        jTextCPF.setBounds(180, 40, 100, 20);
        jTextNome.setBounds(180, 80, 150, 20);
        jTextDataNascimento.setBounds(180, 120, 100, 20);
        jTextEmail.setBounds(180, 160, 200, 20);
        // Adiciona os rótulos e os input box na janela
        
        janelaProfessor.add(labelCPF);
        janelaProfessor.add(labelNome);
        janelaProfessor.add(labelDataNascimento);
        janelaProfessor.add(labelEmail);
        janelaProfessor.add(jTextCPF);
        janelaProfessor.add(jTextNome);
        janelaProfessor.add(jTextDataNascimento);
        janelaProfessor.add(jTextEmail);
        // Define botões e a localização deles na janela
        final JButton botaoConsultar = new JButton("Consultar");
        botaoConsultar.setBounds(50, 200, 100, 20);
        janelaProfessor.add(botaoConsultar);
        final JButton botaoGravar = new JButton("Gravar");
        botaoGravar.setBounds(180, 200, 100, 20);
        botaoGravar.setEnabled(true);
        janelaProfessor.add(botaoGravar);
        final JButton botaoLimpar = new JButton("Limpar");
        botaoLimpar.setBounds(310, 200, 100, 20);
        janelaProfessor.add(botaoLimpar);
        final JButton botaoDeletar = new JButton("Deletar");
        botaoDeletar.setBounds(450, 200, 100, 20);
        botaoDeletar.setEnabled(true);
        janelaProfessor.add(botaoDeletar);
        		
        // Define objeto professor para pesquisar no banco de dados
        final Professor professor = new Professor();
        // Define ações dos botões
        botaoConsultar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String cpf = jTextCPF.getText();
                    
                    botaoGravar.setEnabled(true);
                    String nome;
                    Date dataNascimento;
                    String email;
                    if (!professor.consultarProfessor(cpf)) {
                        nome = "";
                        dataNascimento = null;
                        email = "";
                    } else {
                        nome = professor.getNome();
                        jTextNome.setText(nome);
                        dataNascimento = (Date) professor.getDataNascimento();
                        email = professor.getEmail();
                        jTextEmail.setText(email);
                    }
                   
                    if (dataNascimento != null) {
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        jTextDataNascimento.setText(sdf.format(dataNascimento));
                    } else {
                        jTextDataNascimento.setText("");
                    }
                    
                    botaoConsultar.setEnabled(false);
                    jTextCPF.setEnabled(true);
                    jTextNome.setEnabled(true);
                    jTextDataNascimento.setEnabled(true);
                    jTextEmail.setEnabled(true);
                    jTextNome.requestFocus();
                } catch (Exception erro) {
                    JOptionPane.showMessageDialog(janelaProfessor, "Preencha o campo cpf corretamente!");
                }
            }
        });
        botaoGravar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int resposta = JOptionPane.showConfirmDialog(janelaProfessor, "Deseja atualizar?", "Confirmação",
                        JOptionPane.YES_NO_OPTION);
                if (resposta == JOptionPane.YES_OPTION) {
                    String cpf = jTextCPF.getText();
                    String nome = jTextNome.getText().trim();
                    String dataNascimentoStr = jTextDataNascimento.getText().trim();
                    String email = jTextEmail.getText().trim();
                    if (cpf == "") {
                        JOptionPane.showMessageDialog(janelaProfessor, "Preencha o campo CPF!");
                        jTextCPF.requestFocus();
                    } else if (nome.length() == 0) {
                        JOptionPane.showMessageDialog(janelaProfessor, "Preencha o campo nome!");
                        jTextNome.requestFocus();
                    } else if (dataNascimentoStr.length() == 0) {
                        JOptionPane.showMessageDialog(janelaProfessor, "Preencha o campo data de nascimento!");
                        jTextDataNascimento.requestFocus();
                    } else if (email.length() == 0) {
                        JOptionPane.showMessageDialog(janelaProfessor, "Preencha o campo email!");
                        jTextEmail.requestFocus();
                    } else {
                        try {
                            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                            Date dataNascimento = new Date(sdf.parse(dataNascimentoStr).getTime());
                            if (!professor.consultarProfessor(cpf)) {
                                if (!professor.cadastrarProfessor(cpf, nome, dataNascimento, email)) {
                                    JOptionPane.showMessageDialog(janelaProfessor, "Erro na inclusão do professor!");
                                } else {
                                    JOptionPane.showMessageDialog(janelaProfessor, "Inclusão realizada!");
                                }
                            } else {
                                if (!professor.atualizarProfessor(cpf, nome, dataNascimento, email)) {
                                    JOptionPane.showMessageDialog(janelaProfessor, "Erro na atualização do professor!");
                                } else {
                                    JOptionPane.showMessageDialog(janelaProfessor, "Alteração realizada!");
                                }
                            }
                        } catch (ParseException ex) {
                            JOptionPane.showMessageDialog(janelaProfessor, "Formato de data inválido! Utilize o formato dd/MM/yyyy.");
                            jTextDataNascimento.requestFocus();
                        }
                    }
                }
            }
        });
        botaoLimpar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                jTextCPF.setText("");
                jTextNome.setText("");
                jTextDataNascimento.setText("");
                jTextEmail.setText("");
                jTextCPF.setEnabled(true);
                jTextNome.setEnabled(true);
                jTextDataNascimento.setEnabled(true);
                jTextEmail.setEnabled(true);
                botaoConsultar.setEnabled(true);
                botaoGravar.setEnabled(true);
                botaoDeletar.setEnabled(true);
                jTextCPF.requestFocus();
            }
        });
        botaoDeletar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int resposta = JOptionPane.showConfirmDialog(janelaProfessor, "Deseja excluir professor?", "Confirmação",
                        JOptionPane.YES_NO_OPTION);
                if (resposta == JOptionPane.YES_OPTION) {
                    // Desabilitar campos
                    jTextNome.setEnabled(false);
                    jTextDataNascimento.setEnabled(false);
                    jTextEmail.setEnabled(false);
                    
                    // Desabilitar botões
                    botaoGravar.setEnabled(false);
                    botaoConsultar.setEnabled(false);
                    botaoLimpar.setEnabled(true); // Manter o botão Limpar habilitado
                    
                    // Realizar a exclusão do aluno
                    String cpf = jTextCPF.getText();
                    boolean exclusaoRealizada = professor.deletarProfessor(cpf);
                    if (exclusaoRealizada) {
                        JOptionPane.showMessageDialog(janelaProfessor, "Professor excluído com sucesso!");
                       
                        jTextCPF.setText("");
                        jTextNome.setText("");
                        jTextDataNascimento.setText("");
                        jTextEmail.setText("");
                        jTextCPF.setEnabled(true);
                        jTextCPF.requestFocus();
                    } else {
                        JOptionPane.showMessageDialog(janelaProfessor, "Erro ao excluir professor!");
                    }
                }
            }
        });
   
        return janelaProfessor;
    }
}
