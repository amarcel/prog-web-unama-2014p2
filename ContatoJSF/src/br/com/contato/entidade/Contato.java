package br.com.contato.entidade;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Contato {

	private String codContato;
	private String nome;
	private String email;
	private String endereco;
	private Date dataNascimento;
	
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
	
	public List<Contato> selecionarContatos() {
		dao = new ContatoDAO();
		
		try {
			List<Contato> listaContatos = new ArrayList<Contato>();
			
			listaContatos = dao.selecionarContatos();
			
			return listaContatos;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
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
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getCodContato() {
		return codContato;
	}
	public void setCodContato(String codContato) {
		this.codContato = codContato;
	}

	
	
	
	
}
