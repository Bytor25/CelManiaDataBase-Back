package co.com.cmdb.crosscutting.exceptions.custom;

import co.com.cmdb.crosscutting.exceptions.CMDBExceptions;
import co.com.cmdb.crosscutting.exceptions.enums.Lugar;

public final class ControllerCMDBException extends CMDBExceptions {

	private static final long serialVersionUID = 976316595255760570L;
	
	public ControllerCMDBException(final String mensajeUsuario) {
		super(mensajeUsuario, Lugar.CONTROLLER);
	}
	
	public ControllerCMDBException(final String mensajeUsuario, final String mensajeTecnico) {
		super(mensajeUsuario, Lugar.CONTROLLER);
	}
	
	
	public ControllerCMDBException(final String mensajeTecnico, final String mensajeUsuario, Throwable excepcionRaiz) {
		super(mensajeTecnico, mensajeUsuario, Lugar.CONTROLLER, excepcionRaiz);
	}

}
