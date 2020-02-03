package repository.interfaces;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

/*identifica que � uma interface de conex�o com o banco de dados*/
@Repository
public interface RepositoryLogin extends Serializable {
	
	boolean autentico(String login, String senha) throws Exception;

}
