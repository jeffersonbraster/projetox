package projeto.framework.utils;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class UtilFramework implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**
	 *  Metodo para que dois metodos nao podem executar ao mesmo tempo ex: dois insert no mesmo local.
	 *  Podendo separar os usuarios que estão efetuando as operações
	 *  Classe responsavel para cadastrar os usuarios que estão inserindo, editando e deletando na tabela de auditoria do hibernate
	 * 
	 */
	
	private static ThreadLocal<Long> threadLocal = new ThreadLocal<Long>();
	
	public synchronized static ThreadLocal<Long> getThreadLocal() {
		return threadLocal;
	}

}
