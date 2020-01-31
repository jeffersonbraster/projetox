package projeto.geral.controller;

import java.util.HashMap;

import javax.faces.bean.ApplicationScoped;
import javax.servlet.http.HttpSession;

@ApplicationScoped
public class SessionControlerImpl implements SessionController {

	private static final long serialVersionUID = 1L;
	
	private HashMap<String, HttpSession> hasMap = new HashMap<String, HttpSession>();

	@Override
	public void addSession(String keyLoginUser, HttpSession httpSession) {
		hasMap.put(keyLoginUser, httpSession);
	}

	@Override
	public void invalidateSession(String keyLoginUser) {
		
		HttpSession session = hasMap.get(keyLoginUser);
		
		if(session != null) {// remove sess�o do usu�rio passado como parametro
			try {
				session.invalidate();
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}else {
			System.out.println("N�o tem usu�rio");
		}
		
		hasMap.remove(keyLoginUser);
	}

}
