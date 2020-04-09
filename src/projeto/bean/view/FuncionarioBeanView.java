package projeto.bean.view;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import projeto.bean.geral.BeanManagedViewAbstract;
import projeto.geral.controller.EntidadeController;
import projeto.interfac.crud.InterfaceCrud;
import projeto.lazy.CarregamentoLazyListObject;
import projeto.model.classes.Entidade;

@Controller
@Scope("session")
@ManagedBean(name = "funcionarioBeanView")
public class FuncionarioBeanView extends BeanManagedViewAbstract {

	private static final long serialVersionUID = 1L;

	
	private Entidade objetoselecionado = new Entidade();
	
	@Autowired
	private ContextoBean contextBean;
	
	@Autowired
	private EntidadeController entidadeController;
	
	private CarregamentoLazyListObject<Entidade> list = new CarregamentoLazyListObject<Entidade>();
	
	
	@Override
	protected Class<?> getClassImplement() {
		return Entidade.class;
	}

	@Override
	protected InterfaceCrud<?> getController() {
		return entidadeController;
	}

	@Override
	public String condicaoAndParaPesquisa() throws Exception {
		return "";
	}
	
	

	public Entidade getObjetoselecionado() {
		return objetoselecionado;
	}

	public void setObjetoselecionado(Entidade objetoselecionado) {
		this.objetoselecionado = objetoselecionado;
	}

	public CarregamentoLazyListObject<Entidade> getList() {
		return list;
	}

	public void setList(CarregamentoLazyListObject<Entidade> list) {
		this.list = list;
	}
	
	@Override
	public void consultarEntidade() throws Exception {
		objetoselecionado = new Entidade();
		list.clean();
		list.setTotalRegistroConsulta(super.totalRegistroConsulta(), super.getSqlLazyQuery());
	}
	

}
