package br.com.contato.entidade;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BancoDadosConfig {
	
	static final String JDBC_DRIVER = "org.postgresql.Driver";
	static final String JDBC_BD = "loja";
	//static final String JDBC_URL = "jdbc:postgresql://localhost:5432/"+JDBC_BD;

	//Credenciais Banco de Dados Local
	//static final String USER_BD = "postgres";
	//static final String PASS_BD = "postgres";

	//Credenciais Banco de Dados Remoto
	static final String USER_BD = "striketec2";
	static final String PASS_BD = "unama";	
	static final String JDBC_URL = "jdbc:postgresql://pgsql.striketec.com.br:5432/striketec2";

	private Connection con;
	
	public void conectar() throws SQLException {
		con = null;
		try {
			
			Class.forName(JDBC_DRIVER);
			con = DriverManager.getConnection(JDBC_URL, USER_BD, PASS_BD);
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw sqle;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConexao() throws Exception{
		reconectarCasoSejaNecessario();
		return con;
	}
	
	public void desconectar() throws SQLException {
		if (con != null) {
			con.close();
			con = null;
		}
	}
	
	public ResultSet executarQuery(String sql) throws SQLException {
		reconectarCasoSejaNecessario();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		return rs;
	}
	
	private void reconectarCasoSejaNecessario() throws SQLException {
		if ((con == null) || (con.isClosed())) {
			try {
				conectar();
			} catch (SQLException sqle) {
				throw sqle;
			}
		}
	}
	
	

}
