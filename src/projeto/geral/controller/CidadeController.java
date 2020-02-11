package projeto.geral.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import projeto.implementacao.crud.ImplementacaoCrud;
import projeto.interfac.crud.InterfaceCrud;
import projeto.model.classes.Cidade;
import repository.interfaces.RepositoryCidade;
import srv.interfaces.SrvCidade;

@Controller
public class CidadeController extends ImplementacaoCrud<Cidade> implements InterfaceCrud<Cidade> {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private SrvCidade srvCidade;
	
	@Autowired
	private RepositoryCidade repositoryCidade;

}
