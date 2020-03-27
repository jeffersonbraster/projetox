package projeto.lazy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import framework.controller.crud.Controlle;
import projeto.listener.ContextLoaderListenerProjetoXUtils;

public class CarregamentoLazyListObject<T> extends LazyDataModel<T> {

	private static final long serialVersionUID = 1L;
	
	private List<T> list = new ArrayList<T>();
	
	private int totalRegistroConsulta = 0;
	
	private String query = null;
	
	private Controlle controlle = (Controlle) ContextLoaderListenerProjetoXUtils.getBean(Controlle.class);
	
	@Override
	public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
		
		try {
			if(query != null && !query.isEmpty()) {
				list = (List<T>) controlle.findListByQueyDinamica(query, first, pageSize);
				if(totalRegistroConsulta == 0) {
					setRowCount(0);
				}else {
					setRowCount(totalRegistroConsulta);
				}
			}
			setPageSize(pageSize);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return (List<T>) list;
	}
	
	public void setTotalRegistroConsulta(int totalRegistroConsulta, String queryDeBuscaConsulta) {
		this.query = queryDeBuscaConsulta;
		this.totalRegistroConsulta = totalRegistroConsulta;
	}
	
	public List<T> getList() {
		return list;
	}
	
	public void clean() {
		this.query = null;
		this.totalRegistroConsulta = 0;
		this.list.clear();
	}
	
	public void remove(T objetoSelecionado) {
		this.list.remove(objetoSelecionado);
	}
	
	public void add(T objetoSelecionado) {
		this.list.add(objetoSelecionado);		
	}
	
	public void addAll(List<T> collections) {
		this.list.addAll(collections);
		
	}

}
