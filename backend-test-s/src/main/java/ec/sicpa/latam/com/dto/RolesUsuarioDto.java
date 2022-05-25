package ec.sicpa.latam.com.dto;

import java.io.Serializable;

public class RolesUsuarioDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long rolUsuarioId;

	private Boolean estado;

	private RoleDto roleDto;

	private UsuarioDto usuarioDto;

	public RolesUsuarioDto() {
	}

	public Long getRolUsuarioId() {
		return rolUsuarioId;
	}

	public void setRolUsuarioId(Long rolUsuarioId) {
		this.rolUsuarioId = rolUsuarioId;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public RoleDto getRoleDto() {
		return roleDto;
	}

	public void setRoleDto(RoleDto roleDto) {
		this.roleDto = roleDto;
	}

	public UsuarioDto getUsuarioDto() {
		return usuarioDto;
	}

	public void setUsuarioDto(UsuarioDto usuarioDto) {
		this.usuarioDto = usuarioDto;
	}

}