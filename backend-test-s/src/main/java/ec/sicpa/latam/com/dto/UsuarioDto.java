package ec.sicpa.latam.com.dto;

import java.io.Serializable;
import java.util.List;

public class UsuarioDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long usuarioId;

	private Boolean enabled;

	private String password;

	private String username;

	private List<RolesUsuarioDto> rolesUsuarioDtos;

	public UsuarioDto() {
	}

	public Long getUsuarioId() {
		return this.usuarioId;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<RolesUsuarioDto> getRolesUsuarioDtos() {
		return rolesUsuarioDtos;
	}

	public void setRolesUsuarioDtos(List<RolesUsuarioDto> rolesUsuarioDtos) {
		this.rolesUsuarioDtos = rolesUsuarioDtos;
	}

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}

}