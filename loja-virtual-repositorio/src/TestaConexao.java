import java.sql.Connection;
import java.sql.DriverManager;

public class TestaConexao {

	public static void main(String[] args) {
		//Cria conex�o com o banco de dados
		Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost/loja_virtual?useTimezone=true&serverTimezone=UTC", "root", "wUjaspeF9u*");
	}
}
