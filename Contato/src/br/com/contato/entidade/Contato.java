package br.com.contato.entidade;

import java.sql.SQLException;
import java.util.Calendar;

public class Contato {

	private String nome;
	private String email;
	private String endereco;
	private Calendar dataNascimento;
	
	ContatoDAO dao;
	
	public int inserirContato(Contato c){
		dao = new ContatoDAO();
		
		int inseriu = -1;
		try {
			inseriu = dao.inserirContato(c);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return inseriu;
		
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public Calendar getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Calendar dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	
	
}
