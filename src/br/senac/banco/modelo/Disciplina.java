package br.senac.banco.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * A classe Disciplina representa uma disciplina do curso.
 * @author caios
 * @see swing2.Conexao
 */
public class Disciplina {
    private int id;
    private String nome;
    private String descricao;
    String cpfProfessor;
    String cpfAluno;
    // Adicione os demais atributos específicos da disciplina

    public String getcpfProfessor() {
		return cpfProfessor;
	}

	public void setcpfProfessor(String cpfProfessor) {
		this.cpfProfessor = cpfProfessor;
	}

	// Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    // Métodos de consulta, cadastro, atualização e exclusão
/**
 * Consulta uma disciplina pelo seu número de ID.
 * @param idDisciplina       Número de ID da disciplina
 * @return                   true se a disciplina for encontrada, false caso contrário
 * @throws SQLException  	 Erro ao consultar a disciplina				
 */
    public boolean consultarDisciplina(int idDisciplina) {
        Connection conexao = null;
        try {
            conexao = Conexao.conectaEscola();
            String sql = "SELECT d.id, d.nome, d.descricao, p.cpf, p.nome AS nomeProfessor " +
                         "FROM disciplina d " +
                         "JOIN professor p ON d.cpfProfessor = p.cpf " +
                         "WHERE d.id=?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, idDisciplina);

            ResultSet rs = ps.executeQuery();
            if (!rs.isBeforeFirst()) {
                System.out.println("Disciplina não encontrada!");
                return false;
            } else {
                while (rs.next()) {
                    this.id = rs.getInt("id");
                    this.nome = rs.getString("nome");
                    this.descricao = rs.getString("descricao");
                    this.cpfProfessor = rs.getString("cpf");
                    String nomeProfessor = rs.getString("nomeProfessor");
                    
                   
                }
                return true;
            }
        } catch (SQLException erro) {
            System.out.println("Erro ao consultar a disciplina: " + erro.toString());
            return false;
        } finally {
            Conexao.fechaConexao(conexao);
        }
    }

/**
 * Cadastra uma nova disciplina
 * @param id               Número de ID da disciplina
 * @param nome			   Nome da disciplina
 * @param descricao        Descriçao da disciplina
 * @param cpfProfessor     Número do CPF do professor
 * @return                 true se o cadastro for realizado com sucesso, false caso contrário.
 * @throws SQLException    Erro ao cadastrar disciplina
 */
    public boolean cadastrarDisciplina(int id, String nome, String descricao, String cpfProfessor) {
        Connection conexao = null;
        try {
            conexao = Conexao.conectaEscola();
            String sql = "INSERT INTO disciplina (id, nome, descricao, cpfProfessor) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, nome);
            ps.setString(3, descricao);
            ps.setString(4, cpfProfessor);

            int totalRegistrosAfetados = ps.executeUpdate();
            if (totalRegistrosAfetados == 0) {
                System.out.println("Não foi feito o cadastro da disciplina!");
                return false;
            }
            System.out.println("Cadastro da disciplina realizado!");
            return true;
        } catch (SQLException erro) {
            System.out.println("Erro ao cadastrar disciplina: " + erro.toString());
            return false;
        } finally {
            Conexao.fechaConexao(conexao);
        }
    }
/**
 * Atualiza dados de uma disciplina
 * @param id               Número de ID da disciplina
 * @param nome			   Nome da disciplina
 * @param descricao        Descriçao da disciplina
 * @param cpfProfessor     Número do CPF do professor
 * @return                 true se a atualização for realizada com sucesso, false caso contrário.
 * @throws SQLException    Erro ao cadastrar disciplina
 */
    public boolean atualizarDisciplina(int id, String nome, String descricao, String cpfProfessor) {
        if (!consultarDisciplina(id)) {
            return false;
        } else {
            Connection conexao = null;
            try {
                conexao = Conexao.conectaEscola();
                String sql = "UPDATE disciplina SET nome=?, descricao=? WHERE id=?";
                PreparedStatement ps = conexao.prepareStatement(sql);
                ps.setString(1, nome);
                ps.setString(2, descricao);
                ps.setInt(3, id);

                int totalRegistrosAfetados = ps.executeUpdate();
                if (totalRegistrosAfetados == 0) {
                    System.out.println("Não foi feita a atualização da disciplina!");
                } else {
                    System.out.println("Atualização da disciplina realizada!");
                }
                return true;
            } catch (SQLException erro) {
                System.out.println("Erro ao atualizar a disciplina: " + erro.toString());
                return false;
            } finally {
                Conexao.fechaConexao(conexao);
            }
        }
    }
/**
 * Deleta uma disciplina
 * @param id     		   Número do ID da disciplina
 * @return                 true se a exclusão for realizada com sucesso, false caso contrário.
 * @throws SQLException    Erro ao excluir disciplina
 */
    public boolean deletarDisciplina(int id) {
        Connection conexao = null;
        try {
            conexao = Conexao.conectaEscola();
            String sql = "DELETE FROM disciplina WHERE id=?";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);

            int totalRegistrosAfetados = ps.executeUpdate();
            if (totalRegistrosAfetados == 0) {
                System.out.println("Não foi possível realizar a exclusão da disciplina!");
                return false;
            }
            System.out.println("Exclusão da disciplina realizada!");
            return true;
        } catch (SQLException erro) {
            System.out.println("Erro ao excluir disciplina: " + erro.toString());
            return false;
        } finally {
            Conexao.fechaConexao(conexao);
        }
    }

   
}
