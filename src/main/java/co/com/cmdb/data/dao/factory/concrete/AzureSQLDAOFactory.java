package co.com.cmdb.data.dao.factory.concrete;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import co.com.cmdb.crosscutting.exceptions.CMDBExceptions;
import co.com.cmdb.crosscutting.exceptions.custom.DataCMDBException;
import co.com.cmdb.crosscutting.exceptions.mesagecatalog.MessageCatalogStrategy;
import co.com.cmdb.crosscutting.exceptions.mesagecatalog.data.CodigoMensaje;
import co.com.cmdb.crosscutting.helpers.SQLHelper;
import co.com.cmdb.crosscutting.helpers.UUIDHelper;
import co.com.cmdb.data.dao.entity.ClienteDAO;
import co.com.cmdb.data.dao.entity.TipoDocumentoDAO;
import co.com.cmdb.data.dao.entity.concrete.SqlConnection;
import co.com.cmdb.data.dao.entity.concrete.azuresql.ClienteAzureSqlDAO;
import co.com.cmdb.data.dao.entity.concrete.azuresql.TipoDocumentoAzureSqlDAO;
import co.com.cmdb.data.dao.factory.DAOFactory;
import co.com.cmdb.entity.ClienteEntity;
import co.com.cmdb.entity.TipoDocumentoEntity;

public final class AzureSQLDAOFactory extends SqlConnection implements DAOFactory {
	
	public AzureSQLDAOFactory() {
		
		super();
		abrirConexion();
	}
	
	public void abrirConexion() {
		
        String url = "jdbc:postgresql://roundhouse.proxy.rlwy.net:52404/railway";
        String user = "postgres";
        String password = "SYamDPlVmZDbqznixMqCkVZUHKGDQFyz";
		try {
			
			setConexion(DriverManager.getConnection(url,user,password));
			
		} catch (final CMDBExceptions excepcion) {
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
			var mensajeTecnico = "Se ha presentado un problema tratando de obtener la conexión con la base de datos CMDB-DOO en el servidor de la base de datos roundhouse.proxy.rlwy.net. Por favor revise la traza de errores pra identificar y solucional el problema...";
			
			throw new DataCMDBException(mensajeTecnico, mensajeUsuario, excepcion);
			
		} catch (final SQLException excepcion) {
			
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
			var mensajeTecnico = "Se ha presentado un problema tratando de obtener la conexión con la base de datos CMDB-DOO en el servidor de la base de datos roundhouse.proxy.rlwy.net. Por favor revise la traza de errores pra identificar y solucional el problema...";
			
			throw new DataCMDBException(mensajeTecnico, mensajeUsuario, excepcion);
			
		} catch (final Exception excepcion) {
			
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
			var mensajeTecnico = "Se ha presentado un problema tratando de obtener la conexión con la base de datos CMDB-DOO en el servidor de la base de datos roundhouse.proxy.rlwy.net. Por favor revise la traza de errores pra identificar y solucional el problema...";
			
			throw new DataCMDBException(mensajeTecnico, mensajeUsuario, excepcion);
		}
		
	}
			
		
		public void cerrarConexion() {
			
			SQLHelper.close(getConexion());
			
		}
		
		public void iniciarTransaccion() {
			
				SQLHelper.initTransaction(getConexion());
			
		}
		
		public void confirmarTransaccion() {
			
			SQLHelper.commit(getConexion());
			
		}
		
		public void cancelarTransaccion() {
			
			SQLHelper.rollback(getConexion());
			
		}
		
		public ClienteDAO getClienteDAO() {
			
			return new ClienteAzureSqlDAO(getConexion());
		
		}
		
		public TipoDocumentoDAO getTipoDocumentoDAO() {
			
			return new TipoDocumentoAzureSqlDAO(getConexion());
			
		}
		    public static void main(String[] args) {
		    	try {
					DAOFactory factory = DAOFactory.getFactory();

					System.out.println("Iniciando transacción...");
					factory.iniciarTransaccion();

					System.out.println("Creando cliente aleatoriamente");
					TipoDocumentoEntity TipoDocumento = TipoDocumentoEntity.build()
							.setId(UUIDHelper.convertToUUID("3c2ee3e7-783a-4d8a-a3bc-323b3d36fb61"));
					ClienteEntity Cliente = ClienteEntity.build().setId(UUIDHelper.generate()).setIdentificador("1041440078").setTipoDocumento(TipoDocumento)
							.setNombre("Juan Pablo").setApellido("Hincapie Torres").setCorreo("JuanPHT1234@gmail.com").setTelefono(300000000).setEstado(true);

					factory.getClienteDAO().crear(Cliente);

					System.out.println("Consultamos ciudades: ");
					var resultados = factory.getClienteDAO().consultar(ClienteEntity.build());

					for (ClienteEntity ClienteEntity : resultados) {
						System.out.println("idCliente: " + ClienteEntity.getId() + ", Identificador:" + ClienteEntity.getIdentificador() + 
								", IdTipoDocumento: " + ClienteEntity.getTipoDocumento().getId() + ",TipoDocumento: " + ClienteEntity.getTipoDocumento().getNombre() + 
								", nombreCliente: " + ClienteEntity.getNombre() + ", ApellidoCliente: " + ClienteEntity.getApellido() + 
								", Correo: " + ClienteEntity.getCorreo() + ", Telefono: " + ClienteEntity.getTelefono() + ", Estado: " + ClienteEntity.isEstado());
					}

					System.out.println("Confirmando transacción...");
					factory.confirmarTransaccion();
					System.out.println("Cerrando conexión...");
					factory.cerrarConexion();
				} catch (final Exception excepcion) {
					excepcion.printStackTrace();
				}
		        

		    }
}


	
