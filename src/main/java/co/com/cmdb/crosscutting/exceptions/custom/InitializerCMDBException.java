package co.com.cmdb.crosscutting.exceptions.custom;

import co.com.cmdb.crosscutting.exceptions.CMDBExceptions;
import co.com.cmdb.crosscutting.exceptions.enums.Lugar;

public final class InitializerCMDBException extends CMDBExceptions {

	private static final long serialVersionUID = 976316595255760570L;
	
	public InitializerCMDBException(final String mensajeUsuario) {
		super(mensajeUsuario, Lugar.INITIALIZER);
	}
	public InitializerCMDBException(final String mensajeTecnico, final String mensajeUsuario, Throwable excepcionRaiz) {
		super(mensajeTecnico, mensajeUsuario, Lugar.INITIALIZER, excepcionRaiz);
	}

}
