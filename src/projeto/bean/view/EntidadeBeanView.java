package projeto.bean.view;

import java.util.Date;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import projeto.bean.geral.BeanManagedViewAbstract;
import projeto.bean.geral.EntidadeAtualizaSenhaBean;
import projeto.geral.controller.EntidadeController;
import projeto.interfac.crud.InterfaceCrud;
import projeto.model.classes.Entidade;

@Controller
@Scope(value = "session")
@ManagedBean(name= "entidadeBeanView")
public class EntidadeBeanView extends BeanManagedViewAbstract {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ContextoBean contextoBean;
	
	@Autowired
	private EntidadeController entidadeController;
	
	private EntidadeAtualizaSenhaBean entidadeAtualizaSenhaBean = new EntidadeAtualizaSenhaBean();
	
	
	
	public void updateSenha() throws Exception {
		Entidade entidadeLogada = contextoBean.getEntidadeLogada();
		if(!entidadeAtualizaSenhaBean.getSenhaAtual().equals(entidadeLogada.getEnt_senha())) {
			addMsg("A senha atual informada, n�o � valida.");
			return;
		}else if(entidadeAtualizaSenhaBean.getSenhaAtual().equals(entidadeAtualizaSenhaBean.getNovaSenha())) {
			addMsg("A senha atual n�o pode ser igual a nova senha.");
			return;
		}else if(!entidadeAtualizaSenhaBean.getNovaSenha().equals(entidadeAtualizaSenhaBean.getConfirmaSenha())) {
			addMsg("A nova senha � diferente da senha confirmada, verifique e tente novamente.");
			return;
		}else {
			entidadeLogada.setEnt_senha(entidadeAtualizaSenhaBean.getNovaSenha());
			entidadeController.saveOrUpdate(entidadeLogada);
			entidadeLogada = entidadeController.findByPorId(Entidade.class, entidadeLogada.getEnt_codigo());
			if(entidadeLogada.getEnt_senha().equals(entidadeAtualizaSenhaBean.getNovaSenha())) {
				sucesso();
			}else {
				addMsg("A nova senha n�o foi atualizado");
				error();
			}
		}
		
	}
	

	public EntidadeAtualizaSenhaBean getEntidadeAtualizaSenhaBean() {
		return entidadeAtualizaSenhaBean;
	}

	public void setEntidadeAtualizaSenhaBean(EntidadeAtualizaSenhaBean entidadeAtualizaSenhaBean) {
		this.entidadeAtualizaSenhaBean = entidadeAtualizaSenhaBean;
	}

	public String getUsuarioLogadoSecurity() {
		return contextoBean.getAuthentication().getName();
	}
	
	public Date getUltimoAcesso() throws Exception {
		return contextoBean.getEntidadeLogada().getEnt_ultimoacesso();
	}

	@Override
	protected Class<Entidade> getClassImplement() {
		return Entidade.class;
	}

	@Override
	protected InterfaceCrud<Entidade> getController() {
		// TODO Auto-generated method stub
		return entidadeController;
	}

	@Override
	public String condicaoAndParaPesquisa() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
