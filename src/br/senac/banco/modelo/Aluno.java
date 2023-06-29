package br.senac.banco.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import br.senac.banco.modelo.Conexao;
/**
 * Essa classe descreve informações sobre um Aluno
 * @author 
 * @see swing2.Conexao
 */

public class Aluno {
    private String cpf;
    private String nome;
    private Date dataNascimento;
    private String email;

    

    // Getters e Setters
/**
 *   
 * @return
 */
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
/**
 * 
 * @param numcpf    		Número do CPF
 * @return                  true se o aluno for encontrado, false caso contrário
 * @throws SQLException   	Erro ao consultar o aluno
 */
    public boolean consultarAluno(String numcpf) {
        // Define a conexão
        Connection conexao = null;
        try {
            conexao = Conexao.conectaEscola();
            // Define a consulta
            String sql = "select * from aluno where cpf=?";
            // Prepara a consulta
            PreparedStatement ps = conexao.prepareStatement(sql);
            // Define o parâmetro da consulta
            ps.setString(1, numcpf);
           
            // Executa a consulta, resultando em um objeto da classe ResultSet
            ResultSet rs = ps.executeQuery();
            if (!rs.isBeforeFirst()) { // Verifica se não está antes do primeiro registro
                System.out.println("Aluno não encontrado!");
                return false; // Aluno não encontrado
            } else {
                // Efetua a leitura do registro da tabela
                while (rs.next()) {
                    this.nome = rs.getString("nome");
                    this.dataNascimento = rs.getDate("dataNascimento");
                    this.email = rs.getString("email");
                   
                }
                return true;
            }
        } catch (SQLException erro) {
            System.out.println("Erro ao consultar o aluno: " + erro.toString());
            return false;
        } finally {
            Conexao.fechaConexao(conexao);
        }
    }

/**
 * Cadastra um novo aluno.
 * @param cpf				Número do CPF
 * @param nome				Nome do aluno
 * @param dataNascimento	Data de nascimento do aluno
 * @param email				Email do aluno
 * @throws SQLException		Erro ao cadastrar aluno
 * @return                  true se o cadastro for realizado com sucesso, false caso contrário.
 */
    public boolean cadastrarAluno( String cpf, String nome, Date dataNascimento, String email) {
        // Define a conexão
        Connection conexao = null;
        try {
            conexao = Conexao.conectaEscola();
            // Define a consulta
            String sql = "INSERT INTO aluno (cpf, nome, dataNascimento, email) VALUES (?, ?, ?, ?)";
            // Prepara a consulta
            PreparedStatement ps = conexao.prepareStatement(sql);
            // Define os parâmetros da consulta
            ps.setString(1, cpf);
            ps.setString(2, nome);
            ps.setDate(3, new java.sql.Date(dataNascimento.getTime()));
            ps.setString(4, email);
            int totalRegistrosAfetados = ps.executeUpdate();
            if (totalRegistrosAfetados == 0) {
                System.out.println("Não foi feito o cadastro!");
                return false;
            }
            System.out.println("Cadastro de aluno realizado!");
            return true;
        } catch (SQLException erro) {
            System.out.println("Erro ao cadastrar aluno: " + erro.toString());
            return false;
        } finally {
            Conexao.fechaConexao(conexao);
        }
    }
/**
 * Deleta um aluno pelo CPF
 * @param cpf				Número do CPF
 * @throws SQLException		Erro ao excluir aluno
 * @return                  true se a exclusão for realizada com sucesso, false caso contrário.
 */
    public boolean deletarAluno(String cpf) {
        Connection conexao = null;
        try {
            conexao = Conexao.conectaEscola();
            String sql = "DELETE FROM aluno WHERE cpf = ?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, cpf);
            int totalRegistrosAfetados = ps.executeUpdate();
            if (totalRegistrosAfetados == 0) {
                System.out.println("Não foi possível realizar a exclusão do aluno!");
                return false;
            }
            System.out.println("Exclusão de aluno realizada!");
            return true;
        } catch (SQLException erro) {
            System.out.println("Erro ao excluir aluno: " + erro.toString());
            return false;
        } finally {
            Conexao.fechaConexao(conexao);
        }
    }

/**
 * Atualiza os dados de um aluno
 * @param cpf				Número do CPF
 * @param nome				Nome do aluno
 * @param dataNascimento	Data de nascimento do aluno
 * @param email				Email do aluno
 * @throws SQLException		Erro ao atualizar o aluno
 * @return                  true se a atualização for realizada com sucesso, false caso contrário.
 */
	public boolean atualizarAluno(String cpf, String nome, Date dataNascimento, String email) {
	    if (!consultarAluno(cpf))
	        return false;
	    else {
	        // Define a conexão
	        Connection conexao = null;
	        try {
	            // Define a conexão
	            conexao = Conexao.conectaEscola();
	            // Define a consulta
	            String sql = "update aluno set nome=?, dataNascimento=?, email=? where cpf=?";
	            // Prepara a consulta
	            PreparedStatement ps = conexao.prepareStatement(sql);
	            // Define os parâmetros da atualização
	            ps.setString(1, nome);
	            ps.setDate(2, new java.sql.Date(dataNascimento.getTime()));
	            ps.setString(3, email);
	            ps.setString(4,  cpf);
	            
	            int totalRegistrosAfetados = ps.executeUpdate();
	            if (totalRegistrosAfetados == 0)
	                System.out.println("Não foi feita a atualização!");
	            else
	                System.out.println("Atualização realizada!");
	            return true;
	        } catch (SQLException erro) {
	            System.out.println("Erro ao atualizar o aluno: " + erro.toString());
	            return false;
	        } finally {
	            Conexao.fechaConexao(conexao);
	        }
	    }
	}

}
