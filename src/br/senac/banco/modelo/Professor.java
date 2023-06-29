package br.senac.banco.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
/**
 * Classe que representa um Professor.
 * @author 
 * @see swing2.Conexao
 */
public class Professor {
    private String cpf;
    private String nome;
    private Date dataNascimento;
    private String email;
    // Adicione os demais atributos específicos do professor

    // Getters e Setters
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

    // Métodos de consulta, cadastro, atualização e exclusão
/**
 * Consulta um professor no banco de dados com base no CPF.
 * 
 * @param numcpf               O CPF do professor a ser consultado.
 * @return                     true se o professor for encontrado, false caso contrário.
 */
    public boolean consultarProfessor(String numcpf) {
        Connection conexao = null;
        try {
            conexao = Conexao.conectaEscola();
            String sql = "SELECT * FROM professor WHERE cpf=?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, numcpf);

            ResultSet rs = ps.executeQuery();
            if (!rs.isBeforeFirst()) {
                System.out.println("Professor não encontrado!");
                return false;
            } else {
                while (rs.next()) {
                    this.nome = rs.getString("nome");
                    this.dataNascimento = rs.getDate("dataNascimento");
                    this.email = rs.getString("email");
                    // Atributos específicos do professor
                }
                return true;
            }
        } catch (SQLException erro) {
            System.out.println("Erro ao consultar o professor: " + erro.toString());
            return false;
        } finally {
            Conexao.fechaConexao(conexao);
        }
    }
/**
 * Cadastra um professor no banco de dados.
 * 
 * @param cpf            O CPF do professor.
 * @param nome           O nome do professor.
 * @param dataNascimento A data de nascimento do professor.
 * @param email          O email do professor.
 * @throws SQLException  Erro ao cadastrar o professor.
 * @return               true se o cadastro for realizado com sucesso, false caso contrário.
 */
    public boolean cadastrarProfessor(String cpf, String nome, Date dataNascimento, String email) {
        Connection conexao = null;
        try {
            conexao = Conexao.conectaEscola();
            String sql = "INSERT INTO professor (cpf, nome, dataNascimento, email) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, cpf);
            ps.setString(2, nome);
            ps.setDate(3, new java.sql.Date(dataNascimento.getTime()));
            ps.setString(4, email);

            int totalRegistrosAfetados = ps.executeUpdate();
            if (totalRegistrosAfetados == 0) {
                System.out.println("Não foi feito o cadastro do professor!");
                return false;
            }
            System.out.println("Cadastro de professor realizado!");
            return true;
        } catch (SQLException erro) {
            System.out.println("Erro ao cadastrar professor: " + erro.toString());
            return false;
        } finally {
            Conexao.fechaConexao(conexao);
        }
    }
/**
 * Atualiza as informações de um professor no banco de dados.
 * 
 * @param cpf            O CPF do professor.
 * @param nome           O nome do professor atualizado.
 * @param dataNascimento A data de nascimento do professor atualizada.
 * @param email          O email do professor atualizado.
 * @throws SQLException  Erro ao atualizar o professor.
 * @return               true se a atualização for realizada com sucesso, false caso contrário.
 */
    public boolean atualizarProfessor(String cpf, String nome, Date dataNascimento, String email) {
        if (!consultarProfessor(cpf)) {
            return false;
        } else {
            Connection conexao = null;
            try {
                conexao = Conexao.conectaEscola();
                String sql = "UPDATE professor SET nome=?, dataNascimento=?, email=? WHERE cpf=?";
                PreparedStatement ps = conexao.prepareStatement(sql);
                ps.setString(1, nome);
                ps.setDate(2, new java.sql.Date(dataNascimento.getTime()));
                ps.setString(3, email);
                ps.setString(4, cpf);

                int totalRegistrosAfetados = ps.executeUpdate();
                if (totalRegistrosAfetados == 0) {
                    System.out.println("Não foi feita a atualização do professor!");
                } else {
                    System.out.println("Atualização do professor realizada!");
                }
                return true;
            } catch (SQLException erro) {
                System.out.println("Erro ao atualizar o professor: " + erro.toString());
                return false;
            } finally {
                Conexao.fechaConexao(conexao);
            }
        }
    }
/**
 * Exclui um professor do banco de dados com base no CPF.
 * 
 * @param cpf O CPF do professor a ser excluído.
 * @throws SQLException Erro ao excluir o professor.
 * @return true se a exclusão for realizada com sucesso, false caso contrário.
 */
    public boolean deletarProfessor(String cpf) {
        Connection conexao = null;
        try {
            conexao = Conexao.conectaEscola();
            String sql = "DELETE FROM professor WHERE cpf=?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, cpf);

            int totalRegistrosAfetados = ps.executeUpdate();
            if (totalRegistrosAfetados == 0) {
                System.out.println("Não foi possível realizar a exclusão do professor!");
                return false;
            }
            System.out.println("Exclusão do professor realizada!");
            return true;
        } catch (SQLException erro) {
            System.out.println("Erro ao excluir professor: " + erro.toString());
            return false;
        } finally {
            Conexao.fechaConexao(conexao);
        }
    }

 
}
