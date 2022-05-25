package ec.sicpa.latam.com.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the usuarios database table.
 */
@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "usuario_id", unique = true, nullable = false)
	private Long usuarioId;

	@Column(nullable = false)
	private Boolean enabled;

	@Column(nullable = false, length = 60)
	private String password;

	@Column(nullable = false, length = 20)
	private String username;

	// bi-directional many-to-one association to RolesUsuario
	@OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
	private List<RolesUsuario> rolesUsuarios;

	public Usuario() {
	}

	public Long getUsuarioId() {
		return this.usuarioId;
	}

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}

	public Boolean getEnabled() {
		return this.enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<RolesUsuario> getRolesUsuarios() {
		return this.rolesUsuarios;
	}

	public void setRolesUsuarios(List<RolesUsuario> rolesUsuarios) {
		this.rolesUsuarios = rolesUsuarios;
	}

	public RolesUsuario addRolesUsuario(RolesUsuario rolesUsuario) {
		getRolesUsuarios().add(rolesUsuario);
		rolesUsuario.setUsuario(this);

		return rolesUsuario;
	}

	public RolesUsuario removeRolesUsuario(RolesUsuario rolesUsuario) {
		getRolesUsuarios().remove(rolesUsuario);
		rolesUsuario.setUsuario(null);

		return rolesUsuario;
	}

}