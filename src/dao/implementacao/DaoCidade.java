package dao.implementacao;

import org.springframework.stereotype.Repository;

import projeto.implementacao.crud.ImplementacaoCrud;
import projeto.model.classes.Cidade;
import repository.interfaces.RepositoryCidade;

@Repository
public class DaoCidade extends ImplementacaoCrud<Cidade> implements RepositoryCidade{

	private static final long serialVersionUID = 1L;

}
