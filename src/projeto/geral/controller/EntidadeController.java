package projeto.geral.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import projeto.implementacao.crud.ImplementacaoCrud;
import projeto.interfac.crud.InterfaceCrud;
import projeto.model.classes.Entidade;
import srv.interfaces.SrvEntidade;

@Controller
public class EntidadeController extends ImplementacaoCrud<Entidade> implements InterfaceCrud<Entidade> {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private SrvEntidade srvEntidade;
	
	
	public Entidade findUserLogado(String userLogado) throws Exception {
		return super.findInuqyeByProperty(Entidade.class, userLogado, "ent_login", " and entity.ent_inativo is false");
	}
	
	public Date getUltimoAcessoEntidadeLogada(String login) {
		return srvEntidade.getUltimoAcessoEntidadeLogada(login);
	}

	public void updateUltimoAcessoUser(String name) {
		
		srvEntidade.updateUltimoAcessoUser(name);
		
	}

}
