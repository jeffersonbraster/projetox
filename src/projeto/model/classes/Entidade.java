package projeto.model.classes;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.CollectionOfElements;
import org.hibernate.envers.Audited;
import org.primefaces.json.JSONObject;

import projeto.annotation.IdentificaCampoPesquisa;

@Audited
@Entity
public class Entidade implements Serializable {
	

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long ent_codigo;
	
	@IdentificaCampoPesquisa(campoConsulta = "ent_login", descricaoCampo = "Nome", principal = 1)
	private String ent_login = null;
	
	private String ent_senha;
	
	private boolean ent_inativo = false;
		
	private String ent_nomefantasia;
	
	@CollectionOfElements
	@ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
	@JoinTable(name = "entidadeacesso", uniqueConstraints = { @UniqueConstraint(name = "unique_acesso_entidade_key", columnNames = {
			"ent_codigo", "esa_codigo" }) }, joinColumns = { @JoinColumn(name = "ent_codigo") })
	@Column(name = "esa_codigo", length = 20)
	private Set<String> acessos = new HashSet<String>();
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date ent_ultimoacesso;
	
	private String tipoentidade = "";
	
	private String email;
	
	
		
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Set<String> getAcessos() {
		return acessos;
	}
	public void setAcessos(Set<String> acessos) {
		this.acessos = acessos;
	}
	public String getTipoentidade() {
		return tipoentidade;
	}
	public void setTipoentidade(String tipoentidade) {
		this.tipoentidade = tipoentidade;
	}
	public Date getEnt_ultimoacesso() {
		return ent_ultimoacesso;
	}
	public void setEnt_ultimoacesso(Date ent_ultimoacesso) {
		this.ent_ultimoacesso = ent_ultimoacesso;
	}
	public boolean getEnt_inativo() {
		return ent_inativo;
	}
	public void setEnt_inativo(boolean ent_inativo) {
		this.ent_inativo = ent_inativo;
	}
	public String getEnt_login() {
		return ent_login;
	}
	public void setEnt_login(String ent_login) {
		this.ent_login = ent_login;
	}
	public String getEnt_senha() {
		return ent_senha;
	}
	public void setEnt_senha(String ent_senha) {
		this.ent_senha = ent_senha;
	}
	public Long getEnt_codigo() {
		return ent_codigo;
	}
	public void setEnt_codigo(Long ent_codigo) {
		this.ent_codigo = ent_codigo;
	}
	
		
	public String getEnt_nomefantasia() {
		return ent_nomefantasia;
	}
	public void setEnt_nomefantasia(String ent_nomefantasia) {
		this.ent_nomefantasia = ent_nomefantasia;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ent_codigo == null) ? 0 : ent_codigo.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Entidade other = (Entidade) obj;
		if (ent_codigo == null) {
			if (other.ent_codigo != null)
				return false;
		} else if (!ent_codigo.equals(other.ent_codigo))
			return false;
		return true;
	}
	
	
	public JSONObject getJson() {
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("ent_codigo", ent_codigo);
		map.put("ent_login", ent_login);
		map.put("ent_nomefantasia", ent_nomefantasia);
		return new JSONObject(map);
	}
	

}
