package co.com.cmdb.crosscutting.exceptions.mesagecatalog;

import co.com.cmdb.crosscutting.exceptions.mesagecatalog.data.CodigoMensaje;
import co.com.cmdb.crosscutting.exceptions.mesagecatalog.data.Mensaje;

public interface MessageCatalog {
	void inicializar();
	
	String obtenerContenidoMensaje(final CodigoMensaje codigo, final String...parametros);
	
	Mensaje obtenerMensaje(final CodigoMensaje codigo, final String...parametros);

}
