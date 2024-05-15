package co.com.cmdb.crosscutting.exceptions.custom;

import co.com.cmdb.crosscutting.exceptions.CMDBExceptions;
import co.com.cmdb.crosscutting.exceptions.enums.Lugar;

public final class DtoCMDBException extends CMDBExceptions {

	private static final long serialVersionUID = 976316595255760570L;
	
	public DtoCMDBException(final String mensajeUsuario) {
		super(mensajeUsuario, Lugar.DTO);
	}
	public DtoCMDBException(final String mensajeTecnico, final String mensajeUsuario, Throwable excepcionRaiz) {
		super(mensajeTecnico, mensajeUsuario, Lugar.DTO, excepcionRaiz);
	}

}
