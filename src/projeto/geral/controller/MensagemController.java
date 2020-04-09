package projeto.geral.controller;

import org.springframework.stereotype.Controller;

import projeto.implementacao.crud.ImplementacaoCrud;
import projeto.interfac.crud.InterfaceCrud;
import projeto.model.classes.Mensagem;

@Controller
public class MensagemController extends ImplementacaoCrud<Mensagem> implements InterfaceCrud<Mensagem> {
	
	private static final long serialVersionUID = 1L;

	@Override
	public Mensagem merge(Mensagem obj) throws Exception {
		return super.merge(obj);
	}

}
