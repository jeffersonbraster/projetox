package projeto.bean.view;



import javax.faces.bean.ManagedBean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.sun.xml.internal.bind.v2.runtime.Name;

import projeto.bean.geral.BeanManagedViewAbstract;
import projeto.interfac.crud.InterfaceCrud;

@Controller
@Scope(value = "session")
@ManagedBean(name = "mensagemBeanView")
public class MensagemBeanView extends BeanManagedViewAbstract {

	private static final long serialVersionUID = 1L;
	
	@Override
	public String novo() throws Exception {
		System.out.println("chamou metodo novo bean mensagens");
		return "";
	}

	@Override
	protected Class getClassImplement() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected InterfaceCrud<?> getController() {
		// TODO Auto-generated method stub
		return null;
	}

}
