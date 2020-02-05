package dao.implementacao;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import projeto.implementacao.crud.ImplementacaoCrud;
import projeto.model.classes.Entidade;
import repository.interfaces.RepositoryEntidade;

@Repository
public class DaoEntidade extends ImplementacaoCrud<Entidade> implements RepositoryEntidade {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private RepositoryEntidade repositoryEntidade;

	@Override
	public Date getUltimoAcessoEntidadeLogada(String name) {
		return repositoryEntidade.getUltimoAcessoEntidadeLogada(name);
	}

	@Override
	public void updateUltimoAcessoUser(String login) {
		repositoryEntidade.updateUltimoAcessoUser(login);
	}

	@Override
	public boolean existeUsuario(String ent_login) {
		return repositoryEntidade.existeUsuario(ent_login);
	}

}
