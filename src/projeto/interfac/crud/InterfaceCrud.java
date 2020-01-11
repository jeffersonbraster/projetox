package projeto.interfac.crud;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public interface InterfaceCrud<T> extends Serializable {
	
	//Salva dados
	void save(T obj) throws Exception;
	
	void persist(T obj) throws Exception;
	
	//Salva ou atualiza
	void saveOrUpdate(T obj) throws Exception;
	
	//Realiza o update de dados
	void update(T obj) throws Exception;
	
	//Exclui dados
	void delete(T obj) throws Exception;
	
	//Salva ou atualiza e retorna o objeto em estado persistente
	T merge (T obj) throws Exception;
	
	//Carrega lista de dados de determinada classe
	List<T> findList(Class<T> objs) throws Exception;
	
	Object findById(Class<T> entidade, Long id) throws Exception;
	
	T findByPorId(Class<T> entidade, Long id) throws Exception;
	
	List<T> findListByQueryDinamica(String s) throws Exception;
	
	//executa update com HQL
	void executeUpdateQueryDinamica(String s) throws Exception;
	
	//executar update com SQL
	void executeUpdateSqlDinamica(String s) throws Exception;
	
	//limpa a sessão do hibernate
	void clearSession() throws Exception;
	
	//retira um objeto da sessao do hibernate
	void evict(Object objs) throws Exception;
	
	Session getSession() throws Exception;
	
	List<?> getListSqlDinamica(String sql) throws Exception;
	
	//jdbc do spring
	JdbcTemplate getJdbcTemplate();
	
	SimpleJdbcTemplate getSimpleJdbcTemplate();
	
	SimpleJdbcInsert getSimpleJdbcInsert();
	
	Long totalRegistro(String table) throws Exception;
	
	Query obterQuery(String query) throws Exception;
	
	//Carregamento dinamico com JSF e Primefaces
	List<T> findListByQueyDinamica(String query, int iniciaRegistro, int maximoResultado) throws Exception;
	

}
