package doceria;

import java.sql.Connection;
import java.sql.SQLException;

public class Remocao {

	public static void main(String[] args) throws SQLException {
		CriaConexao criaConexao = new CriaConexao();
		Connection conexao = criaConexao.conecta();
	}
}
