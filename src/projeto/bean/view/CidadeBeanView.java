package projeto.bean.view;



import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import projeto.bean.geral.BeanManagedViewAbstract;
import projeto.geral.controller.CidadeController;
import projeto.interfac.crud.InterfaceCrud;
import projeto.model.classes.Cidade;

@Controller
@Scope(value = "session")
@ManagedBean(name = "cidadeBeanView")
public class CidadeBeanView extends BeanManagedViewAbstract {

	private static final long serialVersionUID = 1L;
	
	private String url = "/cadastro/cad_cidade.jsf?faces-redirect=true";
	private String urlFind = "/cadastro/find_cidade.jsf?faces-redirect=true";
	
	private List<Cidade> list = new ArrayList<Cidade>();
	
	private Cidade objetoSelecionado = new Cidade();
	
	@Autowired
	private CidadeController cidadeController;
	
	
	
	@Override
	public String save() throws Exception {
		objetoSelecionado = cidadeController.merge(objetoSelecionado);
		return "";
	}
	
	@Override
	public void saveNotReturn() throws Exception {
		list.clear();
		objetoSelecionado = cidadeController.merge(objetoSelecionado);
		list.add(objetoSelecionado);
		objetoSelecionado = new Cidade();
		sucesso();
	}
	
	@Override
	public void saveEdit() throws Exception {
		saveNotReturn();
	}
	
	@Override
	public String novo() throws Exception {
		setarVeriaveisNulas();
		return getUrl();
	}
	
	@Override
	public void setarVeriaveisNulas() throws Exception {
		list.clear();
		objetoSelecionado = new Cidade();
	}
	
	@Override
	public String editar() throws Exception {
		list.clear();
		return getUrl();
	}
	
	@Override
	public void excluir() throws Exception {
		objetoSelecionado = (Cidade) cidadeController.getSession().get(getClassImplement(), objetoSelecionado.getCid_codigo());
		cidadeController.delete(objetoSelecionado);
		list.remove(objetoSelecionado);
		novo();
		sucesso();
	}
	
	@Override
	public String redirecionarFindEntidade() throws Exception {
		setarVeriaveisNulas();
		return urlFind;
	}
	
	

	public Cidade getObjetoSelecionado() {
		return objetoSelecionado;
	}

	public void setObjetoSelecionado(Cidade objetoSelecionado) {
		this.objetoSelecionado = objetoSelecionado;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@SuppressWarnings("unchecked")
	public List<Cidade> getList() throws Exception {
		list = cidadeController.findList(getClassImplement());
		return list;
	}

	public void setList(List<Cidade> list) {
		this.list = list;
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected Class getClassImplement() {
		return Cidade.class;
	}

	@Override
	protected InterfaceCrud<?> getController() {
		return cidadeController;
	}	
	
	

}
