package co.com.cmdb.crosscutting.exceptions.custom;

import co.com.cmdb.crosscutting.exceptions.CMDBExceptions;
import co.com.cmdb.crosscutting.exceptions.enums.Lugar;

public final class BusinessCMDBException extends CMDBExceptions {

	private static final long serialVersionUID = 976316595255760570L;
	
	public BusinessCMDBException(final String mensajeUsuario) {
		super(mensajeUsuario, Lugar.BUSINESS);
	}
	
	public BusinessCMDBException(final String mensajeUsuario, final String mensajeTecnico) {
		super(mensajeUsuario, Lugar.BUSINESS);
	}
	
	public BusinessCMDBException(final String mensajeTecnico, final String mensajeUsuario, Throwable excepcionRaiz) {
		super(mensajeTecnico, mensajeUsuario, Lugar.BUSINESS, excepcionRaiz);
	}
	
}
