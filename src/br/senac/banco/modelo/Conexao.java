package br.senac.banco.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * 
 * @author caios
 *
 */
public class Conexao {
    public static Connection conectaEscola() {
        Connection conexao = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost/escola";
            String user = "root"; // nome do usu�rio da escola
			String password = ""; // senha da escola
			conexao = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException erro) {
            System.out.println("Driver não encontrado: " + erro);
        } catch (SQLException erro) {
            System.out.println("Erro de conexão ao banco de dados: " + erro.toString());
        } catch (Exception erro) {
            System.out.println("Erro não identificado: " + erro.toString());
        }
        return conexao;
    }
/**
 * 
 * @param conexao		Nome da classe criada para estabelecer conexao com o banco de dados
 */
    public static void fechaConexao(Connection conexao) {
        try {
            if (conexao != null) {
                conexao.close();
            }
        } catch (Exception erro) {
            System.out.println("Erro ao fechar a conexão: " + erro.toString());
        }
    }
}
