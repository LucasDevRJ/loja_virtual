package doceria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import doceria.Doce;

public class DoceDAO {

	private Connection conexao;
	
	public DoceDAO(Connection conexao) {
		this.conexao = conexao;
	}
	
	public void salvar(Doce doce) throws SQLException {
		String sql = "INSERT INTO DOCE (NOME, INGREDIENTES) VALUES (?, ?)";
		
		try (PreparedStatement ps = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, doce.getNome());
			ps.setString(2, doce.getIngredientes());
			
			ps.execute();
			
			try (ResultSet rs = ps.getGeneratedKeys()){
				while (rs.next()) {
					doce.setId(rs.getInt(1));
				}
			}
		}
	}
}
