package teste.junit;

import org.springframework.util.DigestUtils;

public class SenhaMd5 {
	
	
	String novaSenha = "123";
	
	String md5;
	
	
	public String codificar() {
	
	md5 = DigestUtils.md5DigestAsHex(novaSenha.getBytes());
	
	return md5;
	
	}
	
	System.out.println("df");
}
