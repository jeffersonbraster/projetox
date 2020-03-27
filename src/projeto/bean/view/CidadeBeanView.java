package projeto.bean.view;



import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import projeto.bean.geral.BeanManagedViewAbstract;
import projeto.geral.controller.CidadeController;
import projeto.interfac.crud.InterfaceCrud;
import projeto.lazy.CarregamentoLazyListObject;
import projeto.model.classes.Cidade;

@Controller
@Scope(value = "session")
@ManagedBean(name = "cidadeBeanView")
public class CidadeBeanView extends BeanManagedViewAbstract {

	private static final long serialVersionUID = 1L;
	
	private String url = "/cadastro/cad_cidade.jsf?faces-redirect=true";
	private String urlFind = "/cadastro/find_cidade.jsf?faces-redirect=true";
	
	private CarregamentoLazyListObject<Cidade> list = new CarregamentoLazyListObject<Cidade>();
	
	private Cidade objetoSelecionado = new Cidade();
	
	@Autowired
	private CidadeController cidadeController;
	

	@Override
	public StreamedContent getArquivoReport() throws Exception {
		super.setNomeRelatorioJasper("cidade");
		super.setNomeRelatorioSaida("cidade");
		super.setListDataBeanCollectionReport(cidadeController.findList(getClassImplement()));
		return super.getArquivoReport();
	}
	
	@Override
	public String save() throws Exception {
		objetoSelecionado = cidadeController.merge(objetoSelecionado);
		return "";
	}
	
	@Override
	public void saveNotReturn() throws Exception {
		list.clean();
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
		list.clean();
		objetoSelecionado = new Cidade();
	}
	
	@Override
	public String editar() throws Exception {
		list.clean();
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
	public CarregamentoLazyListObject<Cidade> getList() throws Exception {
		return list;
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

	
	@Override
	public void consultarEntidade() throws Exception {
		objetoSelecionado = new Cidade();
		list.clean();
		list.setTotalRegistroConsulta(super.totalRegistroConsulta(), super.getSqlLazyQuery());
	}

	@Override
	public String condicaoAndParaPesquisa() throws Exception {
		// TODO Auto-generated method stub
		return "";
	}
	

}
