package repository.interfaces;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

/*identifica que é uma interface de conexão com o banco de dados*/
@Repository
public interface RepositoryLogin extends Serializable {
	
	boolean autentico(String login, String senha) throws Exception;

}
