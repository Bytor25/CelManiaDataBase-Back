package co.com.cmdb.crosscutting.exceptions.custom;

import co.com.cmdb.crosscutting.exceptions.CMDBExceptions;
import co.com.cmdb.crosscutting.exceptions.enums.Lugar;

public final class CrosscuttingCMDBException extends CMDBExceptions {

	private static final long serialVersionUID = 976316595255760570L;
	private static final Lugar lugar = Lugar.CROSSCUTTING;
	
	public CrosscuttingCMDBException(final String mensajeUsuario) {
		super(mensajeUsuario, lugar);
	}
	public CrosscuttingCMDBException(final String mensajeUsuario, final String mensajeTecnico) {
		super(mensajeUsuario, mensajeTecnico, lugar);
	}
	public CrosscuttingCMDBException(final String mensajeTecnico, final String mensajeUsuario, final Throwable excepcionRaiz) {
		super(mensajeTecnico, mensajeUsuario, lugar, excepcionRaiz);
	}
}
