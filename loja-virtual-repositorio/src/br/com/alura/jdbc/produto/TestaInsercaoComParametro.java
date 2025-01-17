package br.com.alura.jdbc.produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaInsercaoComParametro {

	public static void main(String[] args) throws SQLException {
		ConnectionFactory factory = new ConnectionFactory();
		try (Connection connection = factory.recuperarConexao()) {
			connection.setAutoCommit(false); // para controlar a transa��o

			// Ter o controle melhor das transa��es
			try (PreparedStatement stm = connection.prepareStatement(
					"INSERT INTO PRODUTO (nome, descricao) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);) { // Para
																												// permitir
																												// os
																												// parametros
																												// e
																												// transformar
																												// tudo
																												// em
																												// String,
																												// dando
																												// mais
																												// seguran�a
																												// para
																												// o
																												// sistema

				// Setar os atributos do banco, pegando como refer�ncia o ? do Statement
				adicionarVariavel("SmartTV", "45 polegadas", stm);
				adicionarVariavel("Radio", "Radio de bateria", stm);

				// Evitar conflitos de erro de transa��o, caso havendo problema, a transa��o n�o
				// ser� feita
				connection.commit();

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("ROLLBACK EXECUTADO");
				connection.rollback();
			}
		}
	}

	private static void adicionarVariavel(String nome, String descricao, PreparedStatement stm) throws SQLException {
		stm.setString(1, nome);
		stm.setString(2, descricao);

		if (nome.equals("Radio")) {
			throw new RuntimeException("N�o foi possivel adicionar o produto!!!");
		}

		stm.execute();

		// Forma para evitar o fechamento de m�todo
		try (ResultSet rst = stm.getGeneratedKeys()) {

			// retorna as chaves geradas
			while (rst.next()) {
				Integer id = rst.getInt(1);
				System.out.println("O id criado foi: " + id);
			}
			rst.close();
		}
	}
}
