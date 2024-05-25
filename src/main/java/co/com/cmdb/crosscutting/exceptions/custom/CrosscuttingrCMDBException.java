package co.com.cmdb.crosscutting.exceptions.custom;

import co.com.cmdb.crosscutting.exceptions.CMDBExceptions;
import co.com.cmdb.crosscutting.exceptions.enums.Lugar;

public final class CrosscuttingrCMDBException extends CMDBExceptions {

	private static final long serialVersionUID = 976316595255760570L;
	private static final Lugar lugar = Lugar.CROSSCUTTING;
	
	public CrosscuttingrCMDBException(final String mensajeUsuario) {
		super(mensajeUsuario, lugar);
	}
	
	public CrosscuttingrCMDBException(final String mensajeTecnico, final String mensajeUsuario) {
		super(mensajeUsuario, mensajeUsuario,lugar);
	}
	
	public CrosscuttingrCMDBException(final String mensajeTecnico, final String mensajeUsuario, Throwable excepcionRaiz) {
		super(mensajeTecnico, mensajeUsuario, lugar, excepcionRaiz);
	}

}
