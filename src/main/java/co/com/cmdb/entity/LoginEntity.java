package co.com.cmdb.entity;

import co.com.cmdb.crosscutting.helpers.BooleanHelper;
import co.com.cmdb.crosscutting.helpers.IntegerHelper;
import co.com.cmdb.crosscutting.helpers.TextHelper;;

public class LoginEntity {
	
	private String usuario;
	private int password;
	private boolean estado;
	
	public LoginEntity() {
		
		super();
		
		setUsuario(TextHelper.EMPTY);
		setPassword(IntegerHelper.DEFAULT_INT);
		setEstado(BooleanHelper.DEFAULT_BOOLEAN);
		
	}
	
	public LoginEntity(final String usuario, final int password, final boolean estado) {
		
		setUsuario(usuario);
		setPassword(password);
		setEstado(estado);

	}
	
	public static final LoginEntity build() {
		
		return new LoginEntity();
		
	}
	
	//Getters

	public final String getUsuario() {
		return usuario;
	}

	public final int getPassword() {
		return password;
	}
	
	public final boolean getEstado() {
		return estado;
	}

	
	//Setters


	public final LoginEntity setUsuario(String usuario) {
		this.usuario = TextHelper.applyTrim(usuario);
		return this;
	}

	public final LoginEntity setPassword(int password) {
		this.password = IntegerHelper.DEFAULT_INT;
		return this;
	}
	
	public final LoginEntity setEstado(boolean estado) {
		this.estado = BooleanHelper.DEFAULT_BOOLEAN;
		return this;
	}
	
}
