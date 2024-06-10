package co.com.cmdb.dto;

import co.com.cmdb.crosscutting.helpers.BooleanHelper;
import co.com.cmdb.crosscutting.helpers.IntegerHelper;
import co.com.cmdb.crosscutting.helpers.TextHelper;

public final class LoginDTO {
	
	private String usuario;
	private int password;
	private boolean estado;
	
	public LoginDTO() {
		
		super();
		
		setUsuario(TextHelper.EMPTY);
		setPassword(IntegerHelper.DEFAULT_INT);
		setEstado(BooleanHelper.DEFAULT_BOOLEAN);
		
	}
	
	public LoginDTO(final String usuario, final int password, final boolean estado) {
		
		setUsuario(usuario);
		setPassword(password);
		setEstado(estado);

	}
	
	public static final LoginDTO build() {
		
		return new LoginDTO();
		
	}
	
	//Getters

	public final String getUsuario() {
		return usuario;
	}

	public final int getPassword() {
		return password;
	}
	
	public final boolean isEstado() {
		return estado;
	}
	
	//Setters


	public final LoginDTO setUsuario(String usuario) {
		this.usuario = TextHelper.applyTrim(usuario);
		return this;
	}

	public final LoginDTO setPassword(int password) {
		this.password = password;
		return this;
	}
	
	public final LoginDTO setEstado(boolean estado) {
		this.estado = BooleanHelper.getDefaultValue(estado);
		return this;
	}

}
