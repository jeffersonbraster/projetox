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
import projeto.util.all.Messagens;

@Controller
@Scope("session")
@ManagedBean(name = "funcionarioBeanView")
public class FuncionarioBeanView extends BeanManagedViewAbstract {

	private static final long serialVersionUID = 1L;
	
	private String urlFind = "/cadastro/find_funcionario.jsf?faces-redirect=true";
	
	private String url = "/cadastro/cad_funcionario.jsf?faces-redirect=true";
	
	private Entidade objetoselecionado = new Entidade();
	
	@Autowired
	private ContextoBean contextBean;
	
	@Autowired
	private EntidadeController entidadeController;
	
	private CarregamentoLazyListObject<Entidade> list = new CarregamentoLazyListObject<Entidade>();
	
	
	
	@Override
	public String novo() throws Exception {
		objetoselecionado = new Entidade();
		list.clean();
		return url;
	}
	
	
	@Override
	public String redirecionarFindEntidade() throws Exception {
		return urlFind;
	}
	
	
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
		return " and entity.tipoentidade = 'FUNCIONARIO' ";
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
	
	@Override
	public void excluir() throws Exception {
		if(objetoselecionado.getEnt_codigo() != null && objetoselecionado.getEnt_codigo() > 0) {
			entidadeController.delete(objetoselecionado);
			list.remove(objetoselecionado);
			objetoselecionado = new Entidade();
			sucesso();
		}
	}
	
	@Override
	public void saveNotReturn() throws Exception {
		if(!objetoselecionado.getAcessos().contains("USER")) {
			objetoselecionado.getAcessos().add("USER");
		}
		objetoselecionado = entidadeController.merge(objetoselecionado);
		list.add(objetoselecionado);
		objetoselecionado = new Entidade();
		sucesso();
	}
	
	@Override
	public void saveEdit() throws Exception {
		objetoselecionado = entidadeController.merge(objetoselecionado);
		list.add(objetoselecionado);
		
		objetoselecionado = new Entidade();
		Messagens.msgSeverityInfo("Atualizado com sucesso!");
	}
	
	@Override
	public String editar() throws Exception {
		list.clean();
		return url;
	}
	

}
