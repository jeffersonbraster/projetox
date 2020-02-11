package projeto.model.classes;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.envers.Audited;

import projeto.annotation.IdentificaCampoPesquisa;

@Audited
@Entity
@Table(name = "Pais")
@SequenceGenerator(name = "pais_seq", sequenceName = "pais_seq", initialValue = 1, allocationSize = 1)
public class Pais implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@IdentificaCampoPesquisa(descricaoCampo = "C�digo", campoConsulta = "pai_id")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pais_seq")
	private Long id;
	
	@IdentificaCampoPesquisa(descricaoCampo = "Nome", campoConsulta = "pai_nome", principal = 1)
	@Column(nullable = false, length = 50)
	private String pai_nome;
	
	@Column(nullable = true, length = 15)
	private String pai_sigla;
	
	@Version
	@Column(name = "versionNum")
	private int versionNum;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPai_nome() {
		return pai_nome;
	}

	public void setPai_nome(String pai_nome) {
		this.pai_nome = pai_nome;
	}

	public String getPai_sigla() {
		return pai_sigla;
	}

	public void setPai_sigla(String pai_sigla) {
		this.pai_sigla = pai_sigla;
	}

	public int getVersionNum() {
		return versionNum;
	}

	public void setVersionNum(int versionNum) {
		this.versionNum = versionNum;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Pais other = (Pais) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	

}
