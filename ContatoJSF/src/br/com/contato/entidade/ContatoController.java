package br.com.contato.entidade;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
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
	
	private void limparCamposTxt(){
		contato = new Contato();
	}
	
	public void insereContato(){
		int inseriu = contato.inserirContato(contato);
		
		if(inseriu != -1){
			carregarListaContatos();
			limparCamposTxt();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Contato inserido no Banco de Dados."));
		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha!", "Contato não foi inserido no Banco de Dados."));
		}
		
	}
	
	public void atualizaContato(){
		
		int atualizou = contato.atualizarContato(contato);
		
		if(atualizou != -1){
			carregarListaContatos();
			limparCamposTxt();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Contato atualizado no Banco de Dados."));
		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Contato não foi atualizado no Banco de Dados."));
		}
		
	}
	
	public void acaoTabEditar(Contato c){
		//simplesmente carrega o objeto da CONTROLLER de nome "cliente"
		//com o objeto que veio da VIEW de nome "c"
		contato = c;
	}
	
	public void acaoTabExcluir(Contato c){
		
		int excluiu = contato.excluirContato(c);
		
		if(excluiu != -1){
			carregarListaContatos();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Contato excluído do Banco de Dados."));
		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro!", "Contato não foi excluído do Banco de Dados."));
		}
		
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

