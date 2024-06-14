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
				new Mensaje(CodigoMensaje.M00012, "Ya existe un cliente asociado al número de documento y tipo de documento ingresados. Por favor verifique la información "));
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
				new Mensaje(CodigoMensaje.M00033, "Se ha presentado un problema INESPERADO tratando de consultar el proveedor."));
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
		mensajes.put(CodigoMensaje.M00079.getIdentificador(),
				new Mensaje(CodigoMensaje.M00079, "Cliente consultado exitosamente"));
		mensajes.put(CodigoMensaje.M00080.getIdentificador(), 
				new Mensaje(CodigoMensaje.M00080, "Se ha presentado un problema tratando de actualizar un cliente, Si el problema persiste contacte al administrador"));
		mensajes.put(CodigoMensaje.M00081.getIdentificador(), 
				new Mensaje(CodigoMensaje.M00081, "Se ha presentado una excepción tipo SQLException tratando de realizar la actualización del cliente ... en la tabla de clienres de la base de datos POSTGRESS SQL. Para más detalles, revise de forma completa la excepción raíz presentada"));
		mensajes.put(CodigoMensaje.M00082.getIdentificador(), 
				new Mensaje(CodigoMensaje.M00082, "Se ha presentado un problema INESPERADO con una excepción de tipo Exception tratando de realizar la actualización del proveedor ... en la tabla clientes de la base de datos POSTGRESS SQL. Para más detalles, revise de forma completa la excepción raíz presentada"));
		mensajes.put(CodigoMensaje.M00083.getIdentificador(), 
				new Mensaje(CodigoMensaje.M00083, "Se ha presentado un problema tratando de consultar un cliente, Si el problema persiste contacte al administrador"));
		mensajes.put(CodigoMensaje.M00084.getIdentificador(), 
				new Mensaje(CodigoMensaje.M00084, "Se ha presentado una excepción tipo SQLException tratando de realizar la consulta del cliente ... en la tabla de clientes de la base de datos POSTGRESS SQL. Para más detalles, revise de forma completa la excepción raíz presentada"));
		mensajes.put(CodigoMensaje.M00085.getIdentificador(), 
				new Mensaje(CodigoMensaje.M00085, "Se ha presentado un problema INESPERADO con una excepción de tipo Exception tratando de realizar la consulta del cliente ... en la tabla clientes de la base de datos POSTGRESS SQL. Para más detalles, revise de forma completa la excepción raíz presentada"));
		mensajes.put(CodigoMensaje.M00086.getIdentificador(), 
				new Mensaje(CodigoMensaje.M00086, "El correo que se ingresó ya existe. Por favor intente con otro"));
		mensajes.put(CodigoMensaje.M00087.getIdentificador(), 
				new Mensaje(CodigoMensaje.M00087, "Se ha presentado una excepción tipo SQLException tratando de realizar la validación del correo del cliente ... en la tabla de clientes de la base de datos POSTGRESS SQL. Para más detalles, revise de forma completa la excepción raíz presentada"));
		mensajes.put(CodigoMensaje.M00088.getIdentificador(), 
				new Mensaje(CodigoMensaje.M00088, "Se ha presentado un problema INESPERADO con una excepción de tipo Exception tratando de realizar la validación del correo del cliente ... en la tabla clientes de la base de datos POSTGRESS SQL. Para más detalles, revise de forma completa la excepción raíz presentada"));
		mensajes.put(CodigoMensaje.M00089.getIdentificador(), 
				new Mensaje(CodigoMensaje.M00089, "El teléfono que se ingresó ya está asociado a otro cliente. Por favor intente con otro"));
		mensajes.put(CodigoMensaje.M00090.getIdentificador(), 
				new Mensaje(CodigoMensaje.M00090, "Se ha presentado una excepción tipo SQLException tratando de realizar la validación del teléfono del cliente ... en la tabla de clientes de la base de datos POSTGRESS SQL. Para más detalles, revise de forma completa la excepción raíz presentada"));
		mensajes.put(CodigoMensaje.M00091.getIdentificador(), 
				new Mensaje(CodigoMensaje.M00091, "Se ha presentado un problema INESPERADO con una excepción de tipo Exception tratando de realizar la validación del teléfono del cliente ... en la tabla clientes de la base de datos POSTGRESS SQL. Para más detalles, revise de forma completa la excepción raíz presentada"));
		mensajes.put(CodigoMensaje.M00092.getIdentificador(), 
				new Mensaje(CodigoMensaje.M00092, "El teléfono que se ingresó ya está asociado a otro proveedor. Por favor intente con otro"));
		mensajes.put(CodigoMensaje.M00093.getIdentificador(), 
				new Mensaje(CodigoMensaje.M00093, "Se ha presentado una excepción tipo SQLException tratando de realizar la validación del teléfono del proveedor ... en la tabla de proveedores de la base de datos POSTGRESS SQL. Para más detalles, revise de forma completa la excepción raíz presentada"));
		mensajes.put(CodigoMensaje.M00094.getIdentificador(), 
				new Mensaje(CodigoMensaje.M00094, "Se ha presentado un problema INESPERADO con una excepción de tipo Exception tratando de realizar la validación del teléfono del proveedor ... en la tabla proveedores de la base de datos POSTGRESS SQL. Para más detalles, revise de forma completa la excepción raíz presentada"));
		mensajes.put(CodigoMensaje.M00095.getIdentificador(), 
				new Mensaje(CodigoMensaje.M00095, "Se ha presentado un problema tratando de consultar el proveedor por su número de documento, Si el problema persiste contacte al administrador"));
		mensajes.put(CodigoMensaje.M00096.getIdentificador(), 
				new Mensaje(CodigoMensaje.M00096, "Se ha presentado una excepción tipo SQLException tratando de consultar un proveedor por su número de documento ... en la tabla de proveedores de la base de datos POSTGRESS SQL. Para más detalles, revise de forma completa la excepción raíz presentada"));
		mensajes.put(CodigoMensaje.M00097.getIdentificador(), 
				new Mensaje(CodigoMensaje.M00097, "Se ha presentado un problema INESPERADO con una excepción de tipo Exception tratando de consultar un proveedor por su número de documento ... en la tabla proveedores de la base de datos POSTGRESS SQL. Para más detalles, revise de forma completa la excepción raíz presentada"));
		mensajes.put(CodigoMensaje.M00098.getIdentificador(), 
				new Mensaje(CodigoMensaje.M00098, "Se ha presentado un problema tratando de consultar el proveedor por su número de documento y tipo de documento, Si el problema persiste contacte al administrador"));
		mensajes.put(CodigoMensaje.M00099.getIdentificador(), 
				new Mensaje(CodigoMensaje.M00099, "Se ha presentado una excepción tipo SQLException tratando de consultar un proveedor por su número de documento y tipo de documento ... en la tabla de proveedores de la base de datos POSTGRESS SQL. Para más detalles, revise de forma completa la excepción raíz presentada"));
		mensajes.put(CodigoMensaje.M00100.getIdentificador(), 
				new Mensaje(CodigoMensaje.M00100, "Se ha presentado un problema INESPERADO con una excepción de tipo Exception tratando de consultar un proveedor por su número de documento y tipo de documento ... en la tabla proveedores de la base de datos POSTGRESS SQL. Para más detalles, revise de forma completa la excepción raíz presentada"));
		mensajes.put(CodigoMensaje.M00101.getIdentificador(), 
				new Mensaje(CodigoMensaje.M00101, "Se ha presentado un problema tratando de consultar el cliente por ID."));
		mensajes.put(CodigoMensaje.M00102.getIdentificador(), 
				new Mensaje(CodigoMensaje.M00102, "Error técnico al ejecutar el caso de uso ConsultarPorIdCliente."));
		mensajes.put(CodigoMensaje.M00103.getIdentificador(),
				new Mensaje(CodigoMensaje.M00103, "Se ha presentado un problema tratando de actualizar la informacion del cliente..."));
		mensajes.put(CodigoMensaje.M00104.getIdentificador(),
				new Mensaje(CodigoMensaje.M00104, "Se ha presentado un problema INESPERADO tratando de actualizar el cliente"));
		mensajes.put(CodigoMensaje.M00105.getIdentificador(),
				new Mensaje(CodigoMensaje.M00105, "Se ha producido un problema tratando de llevar a cabo la actualización del cliente"));
		mensajes.put(CodigoMensaje.M00106.getIdentificador(),
				new Mensaje(CodigoMensaje.M00106, "El dao factory para consultar el cliente llegó nulo"));
		mensajes.put(CodigoMensaje.M00107.getIdentificador(),
				new Mensaje(CodigoMensaje.M00107, "No fue posible el registro cliente debido a que ya existe un cliente asociado al número de documento y tipo de documento ingresados"));
		mensajes.put(CodigoMensaje.M00108.getIdentificador(),
				new Mensaje(CodigoMensaje.M00108, "El número de teléfono ingresado ya está asociado a otro cliente"));
		mensajes.put(CodigoMensaje.M00109.getIdentificador(),
				new Mensaje(CodigoMensaje.M00109, "No fue posible el registro del cliente debido a que el número de teléfono ya está en uso"));
		mensajes.put(CodigoMensaje.M00110.getIdentificador(),
				new Mensaje(CodigoMensaje.M00110, "El correo ingresado ya está asociado a otro cliente"));
		mensajes.put(CodigoMensaje.M00111.getIdentificador(),
				new Mensaje(CodigoMensaje.M00111, "No fue posible el registro del cliente debido a que el correo ya está en uso"));
		mensajes.put(CodigoMensaje.M00112.getIdentificador(),
				new Mensaje(CodigoMensaje.M00112, "No fue posible el registro del cliente ya que el número de teléfono ya está en uso"));
		mensajes.put(CodigoMensaje.M00113.getIdentificador(),
				new Mensaje(CodigoMensaje.M00113, "No fue posible el registro del cliente ya que el número de teléfono solo puede contener números"));
		mensajes.put(CodigoMensaje.M00114.getIdentificador(),
				new Mensaje(CodigoMensaje.M00114, "El numero de identificacion del cliente no cumple con el rango establecido.Debe proporcionar un numero de identificacion válido para el cliente."));
		mensajes.put(CodigoMensaje.M00115.getIdentificador(),
				new Mensaje(CodigoMensaje.M00115, "No fue posible el registro del cliente ya que el número de teléfono solo puede iniciar desde el número 3..."));
		mensajes.put(CodigoMensaje.M00116.getIdentificador(),
				new Mensaje(CodigoMensaje.M00116, "No fue posible el registro del cliente debido a que el campo de nombre llegó vacío"));
		mensajes.put(CodigoMensaje.M00117.getIdentificador(),
				new Mensaje(CodigoMensaje.M00117, "El nombre ingresado no se encuentra en el rango permitido. Revise los datos nuevamente"));
		mensajes.put(CodigoMensaje.M00118.getIdentificador(),
				new Mensaje(CodigoMensaje.M00118, "No fue posible el registro del cliente debido a que el nombre ingresado no se encuentra en el rango permitido"));
		mensajes.put(CodigoMensaje.M00119.getIdentificador(),
				new Mensaje(CodigoMensaje.M00119, "No fue posible el registro del cliente debido a que el campo de apellido llegó vacío"));
		mensajes.put(CodigoMensaje.M00120.getIdentificador(),
				new Mensaje(CodigoMensaje.M00120, "El apellido ingresado no se encuentra en el rango permitido. Revise los datos nuevamente"));
		mensajes.put(CodigoMensaje.M00121.getIdentificador(),
				new Mensaje(CodigoMensaje.M00121, "No fue posible el registro del cliente debido a que el apellido ingresado no se encuentra en el rango permitido"));
		mensajes.put(CodigoMensaje.M00122.getIdentificador(),
				new Mensaje(CodigoMensaje.M00122, "No fue posible el registro del cliente debido a que el campo de correo llegó vacío"));
		mensajes.put(CodigoMensaje.M00123.getIdentificador(),
				new Mensaje(CodigoMensaje.M00123, "No fue posible el registro del cliente debido a que el correo ingresado no se encuentra bajo el formato permitido"));
		mensajes.put(CodigoMensaje.M00124.getIdentificador(),
				new Mensaje(CodigoMensaje.M00124, "El correo ingresado no se encuentra en el rango permitido. Revise los datos nuevamente"));
		mensajes.put(CodigoMensaje.M00125.getIdentificador(),
				new Mensaje(CodigoMensaje.M00125, "No fue posible el registro del cliente debido a que el correo ingresado no se encuentra en el rango permitido"));
		mensajes.put(CodigoMensaje.M00126.getIdentificador(),
				new Mensaje(CodigoMensaje.M00126, "No fue posible el registro del cliente debido a que el campo de teléfono llegó vacío"));
		mensajes.put(CodigoMensaje.M00127.getIdentificador(),
				new Mensaje(CodigoMensaje.M00127, "No fue posbile el registro del cliente debido a que el teléfono ingresado no se encuentra en el rango permitido"));
		mensajes.put(CodigoMensaje.M00128.getIdentificador(),
				new Mensaje(CodigoMensaje.M00128, "El teléfono ingresado no se encuentra en el formato correcto. Revise los datos nuevamente"));
		mensajes.put(CodigoMensaje.M00129.getIdentificador(),
				new Mensaje(CodigoMensaje.M00129, "No fue posible el registro del cliente debido a que el teléfono ingresado no se encuentra en el formato permitido"));
		mensajes.put(CodigoMensaje.M00130.getIdentificador(),
				new Mensaje(CodigoMensaje.M00130, "El teléfono ingresado no se encuentra en el rango definido. Revise los datos nuevamente"));
		mensajes.put(CodigoMensaje.M00131.getIdentificador(),
				new Mensaje(CodigoMensaje.M00131, "No fue posible el registro del cliente debido a que el teléfono ingresado no se encuentra dentro del rango definido"));
		mensajes.put(CodigoMensaje.M00132.getIdentificador(),
				new Mensaje(CodigoMensaje.M00132, "El tipo de documento ingresado no existe o está vacio. Por favor ingrese un tipo de documento o intente con otro "));
		mensajes.put(CodigoMensaje.M00133.getIdentificador(),
				new Mensaje(CodigoMensaje.M00133, "No fue posible el registro del cliente debido a que el Tipo de documento seleccionado no se encuentra registrado en la base de datos POSTGRE SQL"));
		mensajes.put(CodigoMensaje.M00134.getIdentificador(),
				new Mensaje(CodigoMensaje.M00134, "No fue posible la actualización del cliente debido a que el número de teléfono ya está en uso"));
		mensajes.put(CodigoMensaje.M00135.getIdentificador(),
				new Mensaje(CodigoMensaje.M00135, "No fue posible la actualización del cliente debido a que el correo ya está en uso"));
		mensajes.put(CodigoMensaje.M00136.getIdentificador(),
				new Mensaje(CodigoMensaje.M00136, "No fue posible la actualización del cliente debido a que el campo de nombre llegó vacío"));
		mensajes.put(CodigoMensaje.M00137.getIdentificador(),
				new Mensaje(CodigoMensaje.M00137, "No fue posible la actualización del cliente debido a que el nombre ingresado no se encuentra en el rango permitido"));
		mensajes.put(CodigoMensaje.M00138.getIdentificador(),
				new Mensaje(CodigoMensaje.M00138, "No fue posible la actualización del cliente debido a que el campo de apellido llegó vacío"));
		mensajes.put(CodigoMensaje.M00139.getIdentificador(),
				new Mensaje(CodigoMensaje.M00139, "No fue posible la actualización del cliente debido a que el apellido ingresado no se encuentra en el rango permitido"));
		mensajes.put(CodigoMensaje.M00140.getIdentificador(),
				new Mensaje(CodigoMensaje.M00140, "No fue posible la actualización del cliente debido a que el campo de correo llegó vacío"));
		mensajes.put(CodigoMensaje.M00141.getIdentificador(),
				new Mensaje(CodigoMensaje.M00141, "No fue posible la actualización del cliente debido a que el correo ingresado no se encuentra bajo el formato permitido"));
		mensajes.put(CodigoMensaje.M00142.getIdentificador(),
				new Mensaje(CodigoMensaje.M00142, "No fue posible la actualización del cliente debido a que el correo ingresado no se encuentra en el rango permitido"));
		mensajes.put(CodigoMensaje.M00143.getIdentificador(),
				new Mensaje(CodigoMensaje.M00143, "No fue posible la actualización del cliente debido a que el campo de teléfono llegó vacío"));
		mensajes.put(CodigoMensaje.M00144.getIdentificador(),
				new Mensaje(CodigoMensaje.M00144, "No fue posible la actualización del cliente debido a que el teléfono ingresado no se encuentra en el rango permitido"));
		mensajes.put(CodigoMensaje.M00145.getIdentificador(),
				new Mensaje(CodigoMensaje.M00145, "No fue posible la actualización del cliente debido a que el teléfono ingresado no se encuentra en el formato permitido"));
		mensajes.put(CodigoMensaje.M00146.getIdentificador(),
				new Mensaje(CodigoMensaje.M00146, "No fue posible la actualización del cliente debido a que el teléfono ingresado no se encuentra dentro del rango definido"));
		mensajes.put(CodigoMensaje.M00147.getIdentificador(),
				new Mensaje(CodigoMensaje.M00147, "No fue posible la actualización del cliente debido a que el Tipo de documento seleccionado no se encuentra registrado en la base de datos POSTGRE SQL"));
		mensajes.put(CodigoMensaje.M00148.getIdentificador(),
				new Mensaje(CodigoMensaje.M00148, "Ya existe un proveedor asociado al mismo número de documento y tipo de documento ingresados"));
		mensajes.put(CodigoMensaje.M00149.getIdentificador(),
				new Mensaje(CodigoMensaje.M00149, "No fue posible el registro de proveedor debido a que ya existe un proveedor con el mismo número de documento y tipo de documento ingresados"));
		mensajes.put(CodigoMensaje.M00150.getIdentificador(),
				new Mensaje(CodigoMensaje.M00150, "El numero de identificación del proveedor está vacío.Debe proporcionar un numero de identificacion válido para el proveedor."));
		mensajes.put(CodigoMensaje.M00151.getIdentificador(),
				new Mensaje(CodigoMensaje.M00151, "No fue posible el registro de proveedor debido a que el campo de numero de documento llegó nulo"));
		mensajes.put(CodigoMensaje.M00152.getIdentificador(),
				new Mensaje(CodigoMensaje.M00152, "El numero de identificación del proveedor contiene caracteres no válidos. Debe proporcionar un numero de identificación válido que contenga solo números."));
		mensajes.put(CodigoMensaje.M00153.getIdentificador(),
				new Mensaje(CodigoMensaje.M00153, "No fue posible el registro del proveedor ya que el número de teléfono solo puede contener números"));
		mensajes.put(CodigoMensaje.M00154.getIdentificador(),
				new Mensaje(CodigoMensaje.M00154, "El numero de identificacion del proveedor no cumple con el rango establecido.Debe proporcionar un numero de identificacion válido para el proveedor."));
		mensajes.put(CodigoMensaje.M00155.getIdentificador(),
				new Mensaje(CodigoMensaje.M00155, "No fue posible el registro del proveedor ya que el número de teléfono solo puede iniciar desde el número 3..."));
		mensajes.put(CodigoMensaje.M00156.getIdentificador(),
				new Mensaje(CodigoMensaje.M00156, "El número de teléfono ingresado ya está asociado a otro proveedor"));
		mensajes.put(CodigoMensaje.M00157.getIdentificador(),
				new Mensaje(CodigoMensaje.M00157, "No fue posible el registro del proveedor debido a que el número de teléfono ya está en uso"));
		mensajes.put(CodigoMensaje.M00158.getIdentificador(),
				new Mensaje(CodigoMensaje.M00158, "El nombre del proveedor está vacío. Debe proporcionar un nombre válido para el proveedor."));
		mensajes.put(CodigoMensaje.M00159.getIdentificador(),
				new Mensaje(CodigoMensaje.M00159, "No fue posible el registro del proveedor debido a que el campo de nombre llegó vacío"));
		mensajes.put(CodigoMensaje.M00160.getIdentificador(),
				new Mensaje(CodigoMensaje.M00160, "El nombre ingresado no se encuentra en el rango permitido. Revise los datos nuevamente"));
		mensajes.put(CodigoMensaje.M00161.getIdentificador(),
				new Mensaje(CodigoMensaje.M00161, "No fue posible el registro del proveedor debido a que el nombre ingresado no se encuentra en el rango permitido"));
		mensajes.put(CodigoMensaje.M00162.getIdentificador(),
				new Mensaje(CodigoMensaje.M00162, "El número de teléfono del proveedor es nulo. Debe proporcionar un número de teléfono válido para el proveedor."));
		mensajes.put(CodigoMensaje.M00163.getIdentificador(),
				new Mensaje(CodigoMensaje.M00163, "No fue posible el registro del proveedor debido a que el campo de teléfono llegó vacío"));
		mensajes.put(CodigoMensaje.M00164.getIdentificador(),
				new Mensaje(CodigoMensaje.M00164, "El número de teléfono del proveedor es inválido. Debe proporcionar un número de teléfono válido de 10 dígitos para el proveedor."));
		mensajes.put(CodigoMensaje.M00165.getIdentificador(),
				new Mensaje(CodigoMensaje.M00165, "No fue posbile el registro del proveedor debido a que el teléfono ingresado no se encuentra en el rango permitido"));
		mensajes.put(CodigoMensaje.M00166.getIdentificador(),
				new Mensaje(CodigoMensaje.M00166, "El teléfono ingresado no se encuentra en el formato correcto. Revise los datos nuevamente"));
		mensajes.put(CodigoMensaje.M00167.getIdentificador(),
				new Mensaje(CodigoMensaje.M00167, "No fue posible el registro del proveedor debido a que el teléfono ingresado no se encuentra en el formato permitido"));
		mensajes.put(CodigoMensaje.M00168.getIdentificador(),
				new Mensaje(CodigoMensaje.M00168, "El teléfono ingresado no se encuentra en el rango definido. Revise los datos nuevamente"));
		mensajes.put(CodigoMensaje.M00169.getIdentificador(),
				new Mensaje(CodigoMensaje.M00169, "No fue posible el registro del proveedor debido a que el teléfono ingresado no se encuentra dentro del rango definido"));
		mensajes.put(CodigoMensaje.M00170.getIdentificador(),
				new Mensaje(CodigoMensaje.M00170, "No fue posible el registro del proveedor debido a que el Tipo de documento seleccionado no se encuentra registrado en la base de datos POSTGRE SQL"));
		mensajes.put(CodigoMensaje.M00171.getIdentificador(),
				new Mensaje(CodigoMensaje.M00171, "Se ha presentado un problema tratando de actualizar la informacion del proveedor..."));
		mensajes.put(CodigoMensaje.M00172.getIdentificador(),
				new Mensaje(CodigoMensaje.M00172, "Se ha presentado un problema INESPERADO tratando de actualizar el proveedor"));
		mensajes.put(CodigoMensaje.M00173.getIdentificador(), 
				new Mensaje(CodigoMensaje.M00173, "Se ha presentado un problema tratando de consultar el proveedor por numero de documento y tipo de documento."));
		mensajes.put(CodigoMensaje.M00174.getIdentificador(), 
				new Mensaje(CodigoMensaje.M00174, "Error técnico al ejecutar el caso de uso ConsultarPorNDTDProveedoresFacade."));
		mensajes.put(CodigoMensaje.M00175.getIdentificador(), 
				new Mensaje(CodigoMensaje.M00175, "Se ha presentado un problema tratando de consultar el proveedor por numero de id."));
		mensajes.put(CodigoMensaje.M00176.getIdentificador(), 
				new Mensaje(CodigoMensaje.M00176, "Error técnico al ejecutar el caso de uso ConsultarPorIdProveedoresFacade."));
		mensajes.put(CodigoMensaje.M00177.getIdentificador(),
				new Mensaje(CodigoMensaje.M00177, "Se ha producido un problema tratando de llevar a cabo la actualización del proveedor"));
		mensajes.put(CodigoMensaje.M00178.getIdentificador(),
				new Mensaje(CodigoMensaje.M00178, "El dao factory para consultar el proveedor llegó nulo"));
		mensajes.put(CodigoMensaje.M00179.getIdentificador(),
				new Mensaje(CodigoMensaje.M00179, "Ya existe un proveedor asociado al mismo número de documento y tipo de documento ingresados"));
		mensajes.put(CodigoMensaje.M00180.getIdentificador(),
				new Mensaje(CodigoMensaje.M00180, "No fue posible actualizar el proveedor debido a que ya existe un proveedor con el mismo número de documento y tipo de documento ingresados"));
		mensajes.put(CodigoMensaje.M00181.getIdentificador(),
				new Mensaje(CodigoMensaje.M00181, "No fue posible actualizar el proveedor debido a que el número de teléfono ya está en uso"));
		mensajes.put(CodigoMensaje.M00182.getIdentificador(),
				new Mensaje(CodigoMensaje.M00182, "No fue posible actualizar el proveedor debido a que el campo de nombre llegó vacío"));
		mensajes.put(CodigoMensaje.M00183.getIdentificador(),
				new Mensaje(CodigoMensaje.M00183, "No fue posible actualizar el proveedor debido a que el nombre ingresado no se encuentra en el rango permitido"));
		mensajes.put(CodigoMensaje.M00184.getIdentificador(),
				new Mensaje(CodigoMensaje.M00184, "No fue posible actualizar el proveedor debido a que el campo de teléfono llegó vacío"));
		mensajes.put(CodigoMensaje.M00185.getIdentificador(),
				new Mensaje(CodigoMensaje.M00185, "No fue posbile actualizar el proveedor debido a que el teléfono ingresado no se encuentra en el rango permitido"));
		mensajes.put(CodigoMensaje.M00186.getIdentificador(),
				new Mensaje(CodigoMensaje.M00186, "No fue posible actualizar el proveedor debido a que el teléfono ingresado no se encuentra en el formato permitido"));
		mensajes.put(CodigoMensaje.M00187.getIdentificador(),
				new Mensaje(CodigoMensaje.M00187, "No fue posible actualizar el proveedor debido a que el teléfono ingresado no se encuentra dentro del rango definido"));
		mensajes.put(CodigoMensaje.M00188.getIdentificador(),
				new Mensaje(CodigoMensaje.M00188, "No fue posible la actualización del proveedor debido a que el Tipo de documento seleccionado no se encuentra registrado en la base de datos POSTGRE SQL"));
		mensajes.put(CodigoMensaje.M00189.getIdentificador(),
				new Mensaje(CodigoMensaje.M00189, "El número de documento no puede ser nulo."));
		mensajes.put(CodigoMensaje.M00190.getIdentificador(),
				new Mensaje(CodigoMensaje.M00190, "El número de documento es nulo o vacío en el método execute de ConsultarPorIdProveedor."));
		mensajes.put(CodigoMensaje.M00191.getIdentificador(),
				new Mensaje(CodigoMensaje.M00191, "No se encontró el proveedor con el ID proporcionado."));
		mensajes.put(CodigoMensaje.M00192.getIdentificador(),
				new Mensaje(CodigoMensaje.M00192, "No se encontró ninguna entidad Proveedor con el ID: "));
		mensajes.put(CodigoMensaje.M00193.getIdentificador(),
				new Mensaje(CodigoMensaje.M00193, "El número de documento no puede ser nulo."));
		mensajes.put(CodigoMensaje.M00194.getIdentificador(),
				new Mensaje(CodigoMensaje.M00194, "El número de documento es nulo o vacío en el método execute de ConsultarPorNDTDProveedor."));
		mensajes.put(CodigoMensaje.M00195.getIdentificador(),
				new Mensaje(CodigoMensaje.M00195, "El tipo de documento no puede ser nulo."));
		mensajes.put(CodigoMensaje.M00196.getIdentificador(),
				new Mensaje(CodigoMensaje.M00196,"El tipo de documento es nulo o vacío en el método execute de ConsultarPorNDTDProveedor."));
		mensajes.put(CodigoMensaje.M00198.getIdentificador(),
				new Mensaje(CodigoMensaje.M00198, "No se encontró el proveedor con el numero de documento proporcionado."));
		mensajes.put(CodigoMensaje.M00199.getIdentificador(),
				new Mensaje(CodigoMensaje.M00199, "No se encontró ninguna entidad Proveedor con el numero de documento: " ));
		mensajes.put(CodigoMensaje.M00200.getIdentificador(),
				new Mensaje(CodigoMensaje.M00200, "El número de documento es nulo o vacío en el método execute de ConsultarPorIdCliente." ));
		mensajes.put(CodigoMensaje.M00201.getIdentificador(),
				new Mensaje(CodigoMensaje.M00201, "El tipo de documento es nulo o vacío en el método execute de ConsultarPorIdTipoDocumentoCliente." ));
		mensajes.put(CodigoMensaje.M00202.getIdentificador(),
				new Mensaje(CodigoMensaje.M00202, "No se encontró el cliente con el numero de documento proporcionado." ));
		mensajes.put(CodigoMensaje.M00203.getIdentificador(),
				new Mensaje(CodigoMensaje.M00203, "No se encontró ninguna entidad Cliente en con el numero de documento: " ));
		mensajes.put(CodigoMensaje.M00204.getIdentificador(),
				new Mensaje(CodigoMensaje.M00204, "el DAOfactory para consultar el cliente por su número de identificación llegó nulo..."));

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
