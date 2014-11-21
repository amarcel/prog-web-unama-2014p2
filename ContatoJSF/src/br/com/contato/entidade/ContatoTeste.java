package br.com.contato.entidade;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class ContatoTeste {

	@Test
	public void deveInserirContato() {
		Contato c = new Contato();
		c.setNome("Roberta");
		c.setEmail("betinha@yahoo.com.br");
		c.setEndereco("Rua das Oliveiras");
		c.setDataNascimento(deveConverterDataNascimento("15/02/1999"));

		ContatoDAO dao = new ContatoDAO();

		try {
			System.out.println("resultado: " + dao.inserirContato(c));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("Erro ao tentar inserir Contato");
		}

	}

	private Date deveConverterDataNascimento(String dataString) {
		// fazendo a conversão da data
		try {
			Date date = new SimpleDateFormat("dd/MM/yyyy").parse(dataString);
			return date;
		} catch (ParseException e) {
			System.out.println("Erro na conversao de data!");
			return null; // para a execução do método
		}
	}

}
