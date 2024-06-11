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
				new Mensaje(CodigoMensaje.M00002, "Se ha presentado un problema tratando de llevar a cabo la operacion deseada"));
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
				new Mensaje(CodigoMensaje.M00009, "el DAOfactory para consultar el cliente llegó nulo..."));
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
		mensajes.put(CodigoMensaje.M00021.getIdentificador(), 
				new Mensaje(CodigoMensaje.M00021, "Se ha presentado un problema tratando de registrar un proveedor, Si el problema persiste contacte al administrador"));
		mensajes.put(CodigoMensaje.M00022.getIdentificador(), 
				new Mensaje(CodigoMensaje.M00022, "Se ha presentado una excepción tipo SQLException tratando de realizar el insert del proveedor ... en la tabla de proveedores de la base de datos POSTGRESS SQL. Para más detalles, revise de forma completa la excepción raíz presentada"));
		mensajes.put(CodigoMensaje.M00023.getIdentificador(), 
				new Mensaje(CodigoMensaje.M00023, "Se ha presentado un problema INESPERADO con una excepción de tipo Exception tratando de realizar el insert del proveedor ... en la tabla proveedores de la base de datos POSTGRESS SQL. Para más detalles, revise de forma completa la excepción raíz presentada"));
		mensajes.put(CodigoMensaje.M00024.getIdentificador(), 
				new Mensaje(CodigoMensaje.M00024, "Se ha presentado un problema tratando de consultar los clientes. Por favor, contacte al administrador del sistema"));
		mensajes.put(CodigoMensaje.M00025.getIdentificador(), 
				new Mensaje(CodigoMensaje.M00025, "Se ha presentado una SQLException tratando de realizar la consulta de los clientes en la tabla \\\"Clientes\\\" de la base de datos POSTGRESQL."));
		mensajes.put(CodigoMensaje.M00026.getIdentificador(),
				new Mensaje(CodigoMensaje.M00026, "Se ha presentado un problema INESPERADO con una excepción de tipo Exception tratando de realizar la consulta del cliente ... en la tabla clientes de la base de datos POSTGRESS SQL. Para más detalles, revise de forma completa la excepción raíz presentada"));
		mensajes.put(CodigoMensaje.M00027.getIdentificador(), 
				new Mensaje(CodigoMensaje.M00027, "Se ha presentado un problema tratando de consultar los proveedores. Por favor, contacte al administrador del sistema"));
		mensajes.put(CodigoMensaje.M00028.getIdentificador(), 
				new Mensaje(CodigoMensaje.M00028, "Se ha presentado una SQLException tratando de realizar la consulta de los proveedores en la tabla \\\\\\\"Proveedores\\\\\\\" de la base de datos POSTGRESQL."));
		mensajes.put(CodigoMensaje.M00029.getIdentificador(),
				new Mensaje(CodigoMensaje.M00029, "Se ha presentado un problema INESPERADO con una excepción de tipo Exception tratando de realizar la consulta del proveedor ... en la tabla proveedores de la base de datos POSTGRESS SQL. Para más detalles, revise de forma completa la excepción raíz presentada"));
		mensajes.put(CodigoMensaje.M00030.getIdentificador(),
				new Mensaje(CodigoMensaje.M00030, "Se ha presentado un problema tratando de consultar la informacion del proveedor..."));
		mensajes.put(CodigoMensaje.M00031.getIdentificador(),
				new Mensaje(CodigoMensaje.M00031, "el DAOfactory para proveedor el cliente llegó nulo..."));
		mensajes.put(CodigoMensaje.M00032.getIdentificador(),
				new Mensaje(CodigoMensaje.M00032, "Se ha presentado un problema tratando de consultar la informacion del proveedor..."));
		mensajes.put(CodigoMensaje.M00033.getIdentificador(),
				new Mensaje(CodigoMensaje.M00033, "Se ha presentado un problema INESPERADO tratando de consultar el cliente."));
		mensajes.put(CodigoMensaje.M00034.getIdentificador(),
				new Mensaje(CodigoMensaje.M00034, "Ya existe un proveedor con el nombre ... asociada con el tipo de documento"));
		mensajes.put(CodigoMensaje.M00035.getIdentificador(),
				new Mensaje(CodigoMensaje.M00035, "El numero de identificacion del proveedor está vacío. Debe proporcionar un número de identificacion válido para el proveedor."));
		mensajes.put(CodigoMensaje.M00036.getIdentificador(),
				new Mensaje(CodigoMensaje.M00036, "El nombre del proveedor está vacío. Debe proporcionar un nombre válido para el proveedor."));
		mensajes.put(CodigoMensaje.M00037.getIdentificador(),
				new Mensaje(CodigoMensaje.M00037, "El tipo de documento del proveedor es nulo. Debe proporcionar un tipo de documento válido para el proveedor."));
		mensajes.put(CodigoMensaje.M00038.getIdentificador(),
				new Mensaje(CodigoMensaje.M00038, "El número de teléfono del proveedor es nulo. Debe proporcionar un número de teléfono válido para el proveedor."));
		mensajes.put(CodigoMensaje.M00039.getIdentificador(),
				new Mensaje(CodigoMensaje.M00039, "El número de teléfono del proveedor es inválido. Debe proporcionar un número de teléfono válido de 10 dígitos para el proveedor."));
		mensajes.put(CodigoMensaje.M00040.getIdentificador(),
				new Mensaje(CodigoMensaje.M00040, "Se ha producido un problema tratando de llevar a cabo el registro del proveedor"));
		mensajes.put(CodigoMensaje.M00041.getIdentificador(),
				new Mensaje(CodigoMensaje.M00041, "El dao factory para registrar el proveedor llegó nulo"));
		mensajes.put(CodigoMensaje.M00042.getIdentificador(),
				new Mensaje(CodigoMensaje.M00042, "Se ha presentado un problema tratando de consultar la informacion del proveedor..."));
		mensajes.put(CodigoMensaje.M00043.getIdentificador(),
				new Mensaje(CodigoMensaje.M00043, "Se ha presentado un problema tratando de registrar la informacion del proveedor..."));
		mensajes.put(CodigoMensaje.M00044.getIdentificador(),
				new Mensaje(CodigoMensaje.M00044, "Se ha presentado un problema tratando de actualizar la informacion del proveedor..."));
		mensajes.put(CodigoMensaje.M00045.getIdentificador(),
				new Mensaje(CodigoMensaje.M00045, "El numero de identificacion del cliente está vacío.Debe proporcionar un numero de identificacion válido para el cliente."));
		mensajes.put(CodigoMensaje.M00046.getIdentificador(),
				new Mensaje(CodigoMensaje.M00046, "El numero de identificacion del cliente contiene caracteres no válidos. Debe proporcionar un numero de identificacion válido que contenga solo números."));
		mensajes.put(CodigoMensaje.M00047.getIdentificador(),
				new Mensaje(CodigoMensaje.M00047, "El nombre del cliente está vacío. Debe proporcionar un nombre válido para el cliente."));
		mensajes.put(CodigoMensaje.M00048.getIdentificador(),
				new Mensaje(CodigoMensaje.M00048, "El/Los apellidos del cliente están vacíos. Debe de proporcionar apellido/s válidos para el cliente"));
		mensajes.put(CodigoMensaje.M00049.getIdentificador(),
				new Mensaje(CodigoMensaje.M00049, "El tipo de documento del cliente es nulo. Debe proporcionar un tipo de documento válido para el cliente."));
		mensajes.put(CodigoMensaje.M00050.getIdentificador(),
				new Mensaje(CodigoMensaje.M00050, "El correo electrónico del cliente está vacío. Debe proporcionar un correo electrónico válido para el cliente."));
		mensajes.put(CodigoMensaje.M00051.getIdentificador(),
				new Mensaje(CodigoMensaje.M00051, "El correo electrónico del cliente es inválido. Debe proporcionar un correo electrónico válido para el cliente."));
		mensajes.put(CodigoMensaje.M00052.getIdentificador(),
				new Mensaje(CodigoMensaje.M00052, "El número de teléfono del cliente es nulo. Debe proporcionar un número de teléfono válido para el cliente."));
		mensajes.put(CodigoMensaje.M00053.getIdentificador(),
				new Mensaje(CodigoMensaje.M00053, "El número de teléfono del cliente es inválido. Debe proporcionar un número de teléfono válido de 10 dígitos para el cliente."));
		mensajes.put(CodigoMensaje.M00054.getIdentificador(),
				new Mensaje(CodigoMensaje.M00054, "Se ha presentado un problema tratando de registrar la informacion del proveedor..."));
		mensajes.put(CodigoMensaje.M00055.getIdentificador(),
				new Mensaje(CodigoMensaje.M00055, "Se ha presentado un problema INESPERADO tratando de registrar el proveedor"));
		mensajes.put(CodigoMensaje.M00056.getIdentificador(),
				new Mensaje(CodigoMensaje.M00056, "Se ha presentado un problema tratando de consultar la informacion del usuario..."));
		mensajes.put(CodigoMensaje.M00057.getIdentificador(),
				new Mensaje(CodigoMensaje.M00057, "Se ha presentado un problema INESPERADO tratando de consultar el usuario.."));
		mensajes.put(CodigoMensaje.M00058.getIdentificador(),
				new Mensaje(CodigoMensaje.M00058, "el DAOfactory para consultar el usuario llegó nulo..."));
		mensajes.put(CodigoMensaje.M00059.getIdentificador(),
				new Mensaje(CodigoMensaje.M00059, "El campo de usuario no puede estar vacío"));
		mensajes.put(CodigoMensaje.M00060.getIdentificador(),
				new Mensaje(CodigoMensaje.M00060, "El usuario y/o el password es incorrecto. Vuelva a intentar"));
		mensajes.put(CodigoMensaje.M00061.getIdentificador(),
				new Mensaje(CodigoMensaje.M00061, "No fue posible validar la información de los usuarios. Intente de nuevo"));
		mensajes.put(CodigoMensaje.M00062.getIdentificador(),
				new Mensaje(CodigoMensaje.M00062, "Se ha presentado un problema ejecutando la sentencia SQL en la base de datos de POSTGRESQL"));
		mensajes.put(CodigoMensaje.M00063.getIdentificador(),
				new Mensaje(CodigoMensaje.M00063, "Se ha presentado un problema INESPERADO ejecutando la sentencia SQL en la base de datos de POSTGRESQL"));
		mensajes.put(CodigoMensaje.M00064.getIdentificador(),
				new Mensaje(CodigoMensaje.M00064, "Se ha presentado un problema tratando de consultar los Usuarios. Por favor, contacte al administrador del sistema"));
		mensajes.put(CodigoMensaje.M00065.getIdentificador(),
				new Mensaje(CodigoMensaje.M00065, "Se ha presentado una SQLException tratando de realizar la consulta de los usuarios en la tabla \\\\\\\"Logins\\\\\\\" de la base de datos POSTGRESQL."));
		mensajes.put(CodigoMensaje.M00066.getIdentificador(),
				new Mensaje(CodigoMensaje.M00066, "Se ha presentado un problema INESPERADO con una excepción de tipo Exception tratando de realizar la consulta del usuario ... en la tabla Logins de la base de datos POSTGRESS SQL. Para más detalles, revise de forma completa la excepción raíz presentada"));
		mensajes.put(CodigoMensaje.M00067.getIdentificador(),
				new Mensaje(CodigoMensaje.M00067, "Clientes consultados exitosamente"));
		mensajes.put(CodigoMensaje.M00068.getIdentificador(),
				new Mensaje(CodigoMensaje.M00068, "Cliente registrado exitosamente"));
		mensajes.put(CodigoMensaje.M00069.getIdentificador(),
				new Mensaje(CodigoMensaje.M00069, "Cliente actualizado exitosamente"));
		mensajes.put(CodigoMensaje.M00070.getIdentificador(),
				new Mensaje(CodigoMensaje.M00070, "Usuarios consultados exitosamente"));
		mensajes.put(CodigoMensaje.M00071.getIdentificador(),
				new Mensaje(CodigoMensaje.M00071, "Se ha presentado un problema tratando de consultar la informacion del usuario..."));
		mensajes.put(CodigoMensaje.M00072.getIdentificador(),
				new Mensaje(CodigoMensaje.M00072, "Usuario validado exitosamente"));
		mensajes.put(CodigoMensaje.M00073.getIdentificador(),
				new Mensaje(CodigoMensaje.M00073, "Credenciales incorrectas"));
		mensajes.put(CodigoMensaje.M00074.getIdentificador(),
				new Mensaje(CodigoMensaje.M00074, "Se presentó un problema validando el usuario"));
		mensajes.put(CodigoMensaje.M00075.getIdentificador(),
				new Mensaje(CodigoMensaje.M00075, "Proveedores consultados exitosamente"));
		mensajes.put(CodigoMensaje.M00076.getIdentificador(),
				new Mensaje(CodigoMensaje.M00076, "Proveedor registrado exitosamente"));
		mensajes.put(CodigoMensaje.M00077.getIdentificador(),
				new Mensaje(CodigoMensaje.M00077, "Proveedor actualizado exitosamente"));
		mensajes.put(CodigoMensaje.M00078.getIdentificador(),
				new Mensaje(CodigoMensaje.M00078, "TipoDocumento eliminado exitosamente"));
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
