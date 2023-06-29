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

import br.senac.banco.modelo.Aluno;
/**
 * 
 * @author caios
 * @see swing2.Aluno
 */
public class JanelaAluno {
	 /**
     * Cria e retorna uma instância da janela de atualização de aluno.
     *
     * @return A instância da janela de atualização de aluno.
     */
    public static JFrame criarJanelaAluno() {
        // Define a janela
        final JFrame janelaAluno = new JFrame("Atualização de Aluno");
        janelaAluno.setResizable(false);
        janelaAluno.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        janelaAluno.setSize(400, 300);
        // Define o layout da janela
        Container caixa = janelaAluno.getContentPane();
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
        
        janelaAluno.add(labelCPF);
        janelaAluno.add(labelNome);
        janelaAluno.add(labelDataNascimento);
        janelaAluno.add(labelEmail);
        janelaAluno.add(jTextCPF);
        janelaAluno.add(jTextNome);
        janelaAluno.add(jTextDataNascimento);
        janelaAluno.add(jTextEmail);
        // Define botões e a localização deles na janela
        final JButton botaoConsultar = new JButton("Consultar");
        botaoConsultar.setBounds(50, 200, 100, 20);
        janelaAluno.add(botaoConsultar);
        final JButton botaoGravar = new JButton("Gravar");
        botaoGravar.setBounds(180, 200, 100, 20);
        botaoGravar.setEnabled(true);
        janelaAluno.add(botaoGravar);
        final JButton botaoLimpar = new JButton("Limpar");
        botaoLimpar.setBounds(310, 200, 100, 20);
        janelaAluno.add(botaoLimpar);
        final JButton botaoDeletar = new JButton("Deletar");
        botaoDeletar.setBounds(450, 200, 100, 20);
        botaoDeletar.setEnabled(true);
        janelaAluno.add(botaoDeletar);
        		
        // Define objeto aluno para pesquisar no banco de dados
        final Aluno aluno = new Aluno();
        // Define ações dos botões
        botaoConsultar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String cpf = jTextCPF.getText();
                    
                    botaoGravar.setEnabled(true);
                    String nome;
                    Date dataNascimento;
                    String email;
                    if (!aluno.consultarAluno(cpf)) {
                        nome = "";
                        dataNascimento = null;
                        email = "";
                    } else {
                        nome = aluno.getNome();
                        jTextNome.setText(nome);
                        dataNascimento = (Date) aluno.getDataNascimento();
                        email = aluno.getEmail();
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
                    JOptionPane.showMessageDialog(janelaAluno, "Preencha o campo cpf corretamente!");
                }
            }
        });
        botaoGravar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int resposta = JOptionPane.showConfirmDialog(janelaAluno, "Deseja atualizar?", "Confirmação",
                        JOptionPane.YES_NO_OPTION);
                if (resposta == JOptionPane.YES_OPTION) {
                    String cpf = jTextCPF.getText();
                    String nome = jTextNome.getText().trim();
                    String dataNascimentoStr = jTextDataNascimento.getText().trim();
                    String email = jTextEmail.getText().trim();
                    if (cpf == "") {
                        JOptionPane.showMessageDialog(janelaAluno, "Preencha o campo CPF!");
                        jTextCPF.requestFocus();
                    } else if (nome.length() == 0) {
                        JOptionPane.showMessageDialog(janelaAluno, "Preencha o campo nome!");
                        jTextNome.requestFocus();
                    } else if (dataNascimentoStr.length() == 0) {
                        JOptionPane.showMessageDialog(janelaAluno, "Preencha o campo data de nascimento!");
                        jTextDataNascimento.requestFocus();
                    } else if (email.length() == 0) {
                        JOptionPane.showMessageDialog(janelaAluno, "Preencha o campo email!");
                        jTextEmail.requestFocus();
                    } else {
                        try {
                            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                            Date dataNascimento = new Date(sdf.parse(dataNascimentoStr).getTime());
                            if (!aluno.consultarAluno(cpf)) {
                                if (!aluno.cadastrarAluno(cpf, nome, dataNascimento, email)) {
                                    JOptionPane.showMessageDialog(janelaAluno, "Erro na inclusão do aluno!");
                                } else {
                                    JOptionPane.showMessageDialog(janelaAluno, "Inclusão realizada!");
                                }
                            } else {
                                if (!aluno.atualizarAluno(cpf, nome, dataNascimento, email)) {
                                    JOptionPane.showMessageDialog(janelaAluno, "Erro na atualização do aluno!");
                                } else {
                                    JOptionPane.showMessageDialog(janelaAluno, "Alteração realizada!");
                                }
                            }
                        } catch (ParseException ex) {
                            JOptionPane.showMessageDialog(janelaAluno, "Formato de data inválido! Utilize o formato dd/MM/yyyy.");
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
                int resposta = JOptionPane.showConfirmDialog(janelaAluno, "Deseja excluir aluno?", "Confirmação",
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
                    boolean exclusaoRealizada = aluno.deletarAluno(cpf);
                    if (exclusaoRealizada) {
                        JOptionPane.showMessageDialog(janelaAluno, "Aluno excluído com sucesso!");
                       
                        jTextCPF.setText("");
                        jTextNome.setText("");
                        jTextDataNascimento.setText("");
                        jTextEmail.setText("");
                        jTextCPF.setEnabled(true);
                        jTextCPF.requestFocus();
                    } else {
                        JOptionPane.showMessageDialog(janelaAluno, "Erro ao excluir aluno!");
                    }
                }
            }
        });
   
        return janelaAluno;
    }
}
