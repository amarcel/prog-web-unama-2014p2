package br.com.contato.entidade;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ContatoDAO {

	private BancoDadosConfig bd;

	public ContatoDAO() {
		try {
			bd = new BancoDadosConfig();
			bd.conectar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int inserirContato(Contato c) throws SQLException {

		try {
			String sql = "insert into cliente (nome, email, endereco, data_nascimento) "
					+ " values (?, ?, ?, ?) ";

			PreparedStatement ps = bd.getConexao().prepareStatement(sql);

			ps.setString(1, c.getNome());
			ps.setString(2, c.getEmail());
			ps.setString(3, c.getEndereco());
			ps.setDate(4, new Date(c.getDataNascimento().getInstance().getTimeInMillis()));
			
			return ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}

	}

}
