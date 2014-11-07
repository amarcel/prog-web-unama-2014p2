package br.com.contato.entidade;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/adicionaContato")
public class AdicionaContatoServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		// pegando os parâmetros do request
		String nome = request.getParameter("nome");
		String endereco = request.getParameter("endereco");
		String email = request.getParameter("email");
		String dataEmTexto = request.getParameter("dataNascimento");
		Calendar dataNascimento = null;

		// fazendo a conversão da data
		try {
			Date date = new SimpleDateFormat("dd/MM/yyyy").parse(dataEmTexto);
			dataNascimento = Calendar.getInstance();
			dataNascimento.setTime(date);
		} catch (ParseException e) {
			out.println("Erro de conversão da data");
			return; // para a execução do método
		}

		// monta um objeto contato
		Contato contato = new Contato();
		contato.setNome(nome);
		contato.setEndereco(endereco);
		contato.setEmail(email);
		contato.setDataNascimento(dataNascimento);

		// imprime as informações na página:
		out.println("<html>");
		out.println("<body>");
		out.println("Nome: " + contato.getNome() + "<br/>");
		out.println("Endereço: " + contato.getEndereco() + "<br/>");
		out.println("E-mail: " + contato.getEmail() + "<br/>");
		out.println("Data nascimento: "
				+ contato.getDataNascimento().get(Calendar.DAY_OF_MONTH) + "/"
				+ (contato.getDataNascimento().get(Calendar.MONTH) + 1) + "/"
				+ contato.getDataNascimento().get(Calendar.YEAR) + "<br/>");
		out.println("<br/>");
		
		//armazena no banco de dados:
		int contatoInserido;
		contatoInserido = contato.inserirContato(contato);
		if(contatoInserido != -1){
			out.println("<p style='color:green;'>Contato inserido com sucesso no Banco de Dados!</p>");			
		}else{
			out.println("<p style='color:red;'>Falha ao tentar inserir contato no Banco de Dados!</p>");
		}
		
		//fim do HTML
		out.println("</body>");
		out.println("</html>");
		
		

	}

}
