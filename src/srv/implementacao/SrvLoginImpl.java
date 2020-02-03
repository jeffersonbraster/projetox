package srv.implementacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import framework.interfac.crud.SrvLogin;
import repository.interfaces.RepositoryLogin;

@Service
public class SrvLoginImpl implements SrvLogin {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private RepositoryLogin repositoryLogin;

	@Override
	public boolean autentico(String login, String senha) throws Exception {
		return repositoryLogin.autentico(login, senha);
	}

}
