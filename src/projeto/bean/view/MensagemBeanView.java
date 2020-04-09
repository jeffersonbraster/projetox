package projeto.bean.view;



import javax.faces.bean.ManagedBean;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import com.sun.faces.action.RequestMapping;

import projeto.bean.geral.BeanManagedViewAbstract;
import projeto.geral.controller.EntidadeController;
import projeto.geral.controller.MensagemController;
import projeto.interfac.crud.InterfaceCrud;
import projeto.model.classes.Entidade;
import projeto.model.classes.Mensagem;

@Controller
@Scope(value = "session")
@ManagedBean(name = "mensagemBeanView")
public class MensagemBeanView extends BeanManagedViewAbstract {

	private static final long serialVersionUID = 1L;
	
	private Mensagem objetoSelecionado = new Mensagem();
	
	@Autowired
	private ContextoBean contextoBean;
	
	@Autowired
	private EntidadeController entidadeController;
	
	@Autowired
	private MensagemController mensagemController;
	
	

	
	@Override
	public String novo() throws Exception {
		objetoSelecionado = new Mensagem();
		objetoSelecionado.setUsr_origem(contextoBean.getEntidadeLogada());
		return "";
	}
	
	@Override
	public void saveNotReturn() throws Exception {
		
		if(objetoSelecionado.getUsr_destino().getEnt_codigo().equals(objetoSelecionado.getUsr_origem().getEnt_codigo())) {
			addMsg("Não pode enviar mensagem para você mesmo.");
			return;
		}
		
		mensagemController.merge(objetoSelecionado);
		novo();
		addMsg("Enviado!");
	}
	

	@Override
	protected Class<?> getClassImplement() {
		return null;
	}

	@Override
	protected InterfaceCrud<?> getController() {
		return null;
	}

	@Override
	public String condicaoAndParaPesquisa() throws Exception {
		return null;
	}
	
	public void setObjetoSelecionado(Mensagem objetoSelecionado) {
		this.objetoSelecionado = objetoSelecionado;
	}
	
	public Mensagem getObjetoSelecionado() {
		return objetoSelecionado;
	}
	
	@org.springframework.web.bind.annotation.RequestMapping("**/buscarUsuarioDestinoMsg")
	public void buscarUsuarioDestinoMsg(HttpServletResponse httpServletResponse
			, @RequestParam(value = "codEntidade") Long codEntidade) throws Exception{
		Entidade entidade = entidadeController.findByPorId(Entidade.class, codEntidade);
		if (entidade != null){
			objetoSelecionado.setUsr_destino(entidade);
			httpServletResponse.getWriter().write(entidade.getJson().toString());
		}
	}

}
