package co.com.cmdb.crosscutting.exceptions.mesagecatalog.impl;

import java.util.HashMap;
import java.util.Map;

import co.com.cmdb.crosscutting.exceptions.custom.CrosscuttingCMDBException;
import co.com.cmdb.crosscutting.exceptions.mesagecatalog.MessageCatalog;
import co.com.cmdb.crosscutting.exceptions.mesagecatalog.data.CodigoMensaje;
import co.com.cmdb.crosscutting.exceptions.mesagecatalog.data.Mensaje;
import co.com.cmdb.crosscutting.helpers.ObjectHelper;

public final class MessageCatalogBase implements MessageCatalog {
	
	private final Map<String, Mensaje> mensajes = new HashMap<>();

	@Override
	public void inicializar() {
		mensajes.clear();
		
		mensajes.put(CodigoMensaje.M00001.getIdentificador(), 
				new Mensaje(CodigoMensaje.M00001, "El código del mensaje que se quiere obtener del catalogo de mensajes llego nulo"));
		mensajes.put(CodigoMensaje.M00002.getIdentificador(), 
				new Mensaje(CodigoMensaje.M00002, "Se ha presentado un problema tratando de llevar a cabo la operaci+on deseada"));
		mensajes.put(CodigoMensaje.M00003.getIdentificador(), 
				new Mensaje(CodigoMensaje.M00003, "El idetificador del mensaje \"${1}\" que se intentó obtener, no está en el catálo de mensajes base"));
		mensajes.put(CodigoMensaje.M00004.getIdentificador(), 
				new Mensaje(CodigoMensaje.M00004, "El mensaje con identificador \"${1}\" que se intento obtener, no esta configurado para residir en el catalogo de mensajes base"));
		mensajes.put(CodigoMensaje.M00005.getIdentificador(),
				new Mensaje(CodigoMensaje.M00005, "Se ha presentado un problema INESPERADO tratando de consultar el cliente.."));
		mensajes.put(CodigoMensaje.M00006.getIdentificador(),
				new Mensaje(CodigoMensaje.M00006, "Se ha presentado un problema tratando de consultar la informacion del cliente..."));
		mensajes.put(CodigoMensaje.M00007.getIdentificador(),
				new Mensaje(CodigoMensaje.M00007, "Se ha presentado un problema tratando de registrar la informacion del cliente..."));
		mensajes.put(CodigoMensaje.M00008.getIdentificador(),
				new Mensaje(CodigoMensaje.M00008, "Se ha presentado un problema INESPERADO tratando de registrar el cliente"));
		mensajes.put(CodigoMensaje.M00009.getIdentificador(),
				new Mensaje(CodigoMensaje.M00009, "el DAOfactoty para consultar el cliente llegó nulo..."));
		mensajes.put(CodigoMensaje.M00010.getIdentificador(),
				new Mensaje(CodigoMensaje.M00010, "Se ha producido un problema tratando de llevar a cabo el registro del cliente"));
		mensajes.put(CodigoMensaje.M00011.getIdentificador(),
				new Mensaje(CodigoMensaje.M00011, "El dao factory para registrar el cliente llegó nulo"));
		mensajes.put(CodigoMensaje.M00012.getIdentificador(),
				new Mensaje(CodigoMensaje.M00012, "Ya existe un cliente con el nombre ... asociada con el tipo de documento"));
		mensajes.put(CodigoMensaje.M00013.getIdentificador(),
				new Mensaje(CodigoMensaje.M00013, "Se ha presentado un problema tratando de eliminar la informacion del cliente..."));
		mensajes.put(CodigoMensaje.M00014.getIdentificador(),
				new Mensaje(CodigoMensaje.M00014, "Se ha presentado un problema tratando de actualizar la informacion del cliente..."));
		mensajes.put(CodigoMensaje.M00015.getIdentificador(),
				new Mensaje(CodigoMensaje.M00015, "Se ha presentado un problema tratando de eliminar la informacion del Tipo de documento..."));
		mensajes.put(CodigoMensaje.M00016.getIdentificador(),
				new Mensaje(CodigoMensaje.M00016, "No es posible crear el DAO deseado con una conexión cerrada"));
		mensajes.put(CodigoMensaje.M00017.getIdentificador(),
				new Mensaje(CodigoMensaje.M00017, "Se ha presentado un problema tratando de registrar un cliente, Si el problema persiste contacte al administrador"));
		mensajes.put(CodigoMensaje.M00018.getIdentificador(),
				new Mensaje(CodigoMensaje.M00018, "Se ha presentado una excepción tipo SQLException tratando de realizar el insert del cliente ... en la tabla de clientes de la base de datos POSTGRESS SQL. Para más detalles, revise de forma completa la excepción raíz presentada"));
		mensajes.put(CodigoMensaje.M00019.getIdentificador(),
				new Mensaje(CodigoMensaje.M00019, "Se ha presentado un problema INESPERADO con una excepción de tipo Exception tratando de realizar el insert del cliente ... en la tabla clientes de la base de datos POSTGRESS SQL. Para más detalles, revise de forma completa la excepción raíz presentada"));
		mensajes.put(CodigoMensaje.M00020.getIdentificador(),
				new Mensaje(CodigoMensaje.M00020, "Se ha presentado un problema tratando de obtener la conexión con la base de datos CMDB-DOO en el servidor de la base de datos roundhouse.proxy.rlwy.net. Por favor revise la traza de errores pra identificar y solucional el problema..."));
		
	}

	@Override
	public final String obtenerContenidoMensaje(final CodigoMensaje codigo, String... parametros) {
		return obtenerMensaje(codigo,parametros).getContenido();
	}

	@Override
	public final Mensaje obtenerMensaje(final CodigoMensaje codigo, final String... parametros) {
		if(ObjectHelper.getObjectHelper().isNull(codigo)) {
			var mensajeUsuario = obtenerContenidoMensaje(CodigoMensaje.M00002);
			var mensajeTecnico = obtenerContenidoMensaje(CodigoMensaje.M00001);
			
			throw new CrosscuttingCMDBException(mensajeTecnico, mensajeUsuario);

		}
		if(!codigo.isBase()) {
			
			var mensajeUsuario = obtenerContenidoMensaje(CodigoMensaje.M00002);
			var mensajeTecnico = obtenerContenidoMensaje(CodigoMensaje.M00004,codigo.getIdentificador());
			
			throw new CrosscuttingCMDBException(mensajeTecnico, mensajeUsuario);
		}
		if(!mensajes.containsKey(codigo.getIdentificador())) {
			
			var mensajeUsuario = obtenerContenidoMensaje(CodigoMensaje.M00002);
			var mensajeTecnico = obtenerContenidoMensaje(CodigoMensaje.M00004,codigo.getIdentificador());
			throw new CrosscuttingCMDBException(mensajeTecnico, mensajeUsuario);
		}
		
		return mensajes.get(codigo.getIdentificador());
	}
	

}
