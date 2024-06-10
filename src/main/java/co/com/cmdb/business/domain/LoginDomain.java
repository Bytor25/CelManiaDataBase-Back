package co.com.cmdb.business.domain;

import co.com.cmdb.crosscutting.helpers.BooleanHelper;
import co.com.cmdb.crosscutting.helpers.IntegerHelper;
import co.com.cmdb.crosscutting.helpers.TextHelper;

public class LoginDomain {
	
	private String usuario;
	private int password;
	private boolean estado;


	private LoginDomain(final String usuario, final int password, final boolean estado) {
		
		setUsuario(usuario);
		setPassword(password);
		setEstado(estado);
	
	}
	
	public static final LoginDomain build(final String usuario, final int password,final boolean estado) {

return new LoginDomain(usuario, password, estado);

}

public static final LoginDomain build() {
return new LoginDomain(TextHelper.EMPTY, IntegerHelper.DEFAULT_INT, BooleanHelper.DEFAULT_BOOLEAN);

}

//Getters
	
	public final String getUsuario() {
		return usuario;
	}
	
	public int getPassword() {
		return password;
	}
	
	public final boolean isEstado() {
		return estado;
	}
	
//Setters
	
	public void setUsuario(String usuario) {
		this.usuario = TextHelper.applyTrim(usuario);
	}
	
	private void setPassword(int password) {
		this.password = IntegerHelper.DEFAULT_INT;
	}
	
	private void setEstado(boolean estado) {
		this.estado = BooleanHelper.getDefaultValue(estado);
	}

}
