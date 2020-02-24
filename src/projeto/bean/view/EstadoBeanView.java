package projeto.bean.view;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import projeto.bean.geral.BeanManagedViewAbstract;
import projeto.geral.controller.EstadoController;
import projeto.interfac.crud.InterfaceCrud;
import projeto.model.classes.Estado;

@Controller
@Scope(value = "session")
@ManagedBean(name = "estadoBeanView")
public class EstadoBeanView extends BeanManagedViewAbstract {

	private static final long serialVersionUID = 1L;
	
	
	@Autowired
	private EstadoController estadoController;
	
	
	public List<SelectItem> getEstados() throws Exception {
		return estadoController.getListEstado();
	}


	@Override
	protected Class<Estado> getClassImplement() {
		return Estado.class;
	}


	@Override
	protected InterfaceCrud<Estado> getController() {
		return estadoController;
	}

}
