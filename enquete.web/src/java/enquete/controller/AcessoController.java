package enquete.controller;

import enquete.modelo.*;
import enquete.servico.*;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.*;
import javax.crypto.*;

@ManagedBean
@SessionScoped
public class AcessoController {

	/** Login digitado na página. */
	String login;
	public String getLogin(){ return login; }
	public void setLogin(String login){ this.login = login; }

	/** Senha digitada na página. */
	String senha;
	public String getSenha(){ return senha; }
	public void setSenha(String senha){ this.senha = senha; }

	public void autenticar(ActionEvent e)
	{

	}

}
