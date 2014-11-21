package br.com.contato.entidade;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
			
			ps.setDate(4, new java.sql.Date (c.getDataNascimento().getTime() ));
			
			return ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}

	}

	public List<Contato> selecionarContatos() throws SQLException {
		
		try {
			
			List<Contato> listaContatos = new ArrayList<Contato>();
			
			String sql = "select cliente_id, data_nascimento, nome, email, endereco "
					+ " from cliente "
					+ " order by nome";
			
			PreparedStatement ps = bd.getConexao().prepareStatement(sql);
			
			ResultSet resultado = ps.executeQuery();
			
			while (resultado.next()) {
				Contato contato = new Contato();
				
				contato.setCodContato(resultado.getInt("cliente_id"));
				contato.setNome(resultado.getString("nome"));
				contato.setEmail(resultado.getString("email"));
				contato.setEndereco(resultado.getString("endereco"));
				
				contato.setDataNascimento( new java.util.Date( resultado.getDate("data_nascimento").getTime() ));
				
				listaContatos.add(contato);
			}
			
			return listaContatos;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	public int excluirContato(Contato c) throws SQLException {
		try {
			String sql = "delete from cliente where cliente_id = ?";
			
			PreparedStatement ps = bd.getConexao().prepareStatement(sql);
			
			ps.setInt(1, c.getCodContato());
			
			return ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	public int atualizarContato(Contato contato) throws SQLException{
		try {
			String sql = "update cliente "
					+ "	set nome = ? "
					+ " ,email = ? "
					+ " ,endereco = ? "
					+ " ,data_nascimento = ? "
					+ "	where cliente_id = ?";
			
			PreparedStatement ps = bd.getConexao().prepareStatement(sql);
			
			ps.setString(1, contato.getNome());
			ps.setString(2, contato.getEmail());
			ps.setString(3, contato.getEndereco());
			ps.setDate(4, new java.sql.Date( contato.getDataNascimento().getTime() ));
			ps.setInt(5, contato.getCodContato());
			
			return ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	/*
	private Calendar converterData(String dataString){
		try {
			Date date = (Date) new SimpleDateFormat("dd/MM/yyyy").parse(dataString);
			Calendar dataNascimento = Calendar.getInstance();
			dataNascimento.setTime(date);
			return dataNascimento;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	*/

}
