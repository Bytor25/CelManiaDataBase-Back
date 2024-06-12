package co.com.cmdb.crosscutting.exceptions.custom;

import co.com.cmdb.crosscutting.exceptions.CMDBExceptions;
import co.com.cmdb.crosscutting.exceptions.enums.Lugar;

public final class DataCMDBException extends CMDBExceptions {

	private static final long serialVersionUID = 976316595255760570L;
	
	public DataCMDBException(final String mensajeUsuario) {
		super(mensajeUsuario, Lugar.DATA);
	}
	
	public DataCMDBException(final String mensajeUsuario, final String mensajeTecnico) {
		super(mensajeUsuario, Lugar.DATA);
	}
	
	public DataCMDBException(final String mensajeTecnico, final String mensajeUsuario, Throwable excepcionRaiz) {
		super(mensajeUsuario, mensajeTecnico, Lugar.DATA, excepcionRaiz);
	}

}
