package co.com.cmdb.crosscutting.exceptions.custom;

import co.com.cmdb.crosscutting.exceptions.CMDBExceptions;
import co.com.cmdb.crosscutting.exceptions.enums.Lugar;

public final class EntityCMDBException extends CMDBExceptions {

	private static final long serialVersionUID = 976316595255760570L;
	
	public EntityCMDBException(final String mensajeUsuario) {
		super(mensajeUsuario, Lugar.ENTITY);
	}
	public EntityCMDBException(final String mensajeTecnico, final String mensajeUsuario, Throwable excepcionRaiz) {
		super(mensajeTecnico, mensajeUsuario, Lugar.ENTITY, excepcionRaiz);
	}

}
