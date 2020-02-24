package projeto.bean.geral;

import org.springframework.stereotype.Component;

import projeto.interfac.crud.InterfaceCrud;
import projeto.report.util.BeanReportView;

@Component
public abstract class BeanManagedViewAbstract extends BeanReportView {

	private static final long serialVersionUID = 1L;
	
	protected abstract Class getClassImplement();
	
	protected abstract InterfaceCrud<?> getController();
	
	
}
