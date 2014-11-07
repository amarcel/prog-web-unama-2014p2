package br.com.contato.entidade;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class ContatoController {

	//lista para armazenar dados de um select
	private List<Contato> listaContatos = new ArrayList<Contato>();
	private Contato contato;
	
	//construtor padrão
	public ContatoController() { 
		contato = new Contato();
	}

	@PostConstruct
	public void carregarListaContatos(){
		listaContatos = contato.selecionarContatos();
	}
	
	public void insereContato(){
		int inseriu = contato.inserirContato(contato);
		
		if(inseriu != -1){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Contato inserido no Banco de Dados."));
		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha!", "Contato não foi inserido no Banco de Dados."));
		}
		
	}
	
	public void atualizaContato(){
		System.out.println("ATUALIZAR CONTATO");
	}

	
	/*SETTERs AND GETTERs*/
	public List<Contato> getListaContatos() {
		return listaContatos;
	}
	public void setListaContatos(List<Contato> listaContatos) {
		this.listaContatos = listaContatos;
	}
	public Contato getContato() {
		return contato;
	}
	public void setContato(Contato contato) {
		this.contato = contato;
	}
	
	
	
}
