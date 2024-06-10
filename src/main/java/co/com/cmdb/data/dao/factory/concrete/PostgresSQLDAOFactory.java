package co.com.cmdb.data.dao.factory.concrete;

import java.sql.DriverManager;
import java.sql.SQLException;

import co.com.cmdb.crosscutting.exceptions.CMDBExceptions;
import co.com.cmdb.crosscutting.exceptions.custom.DataCMDBException;
import co.com.cmdb.crosscutting.exceptions.mesagecatalog.MessageCatalogStrategy;
import co.com.cmdb.crosscutting.exceptions.mesagecatalog.data.CodigoMensaje;
import co.com.cmdb.crosscutting.helpers.SQLHelper;
import co.com.cmdb.data.dao.entity.ClienteDAO;
import co.com.cmdb.data.dao.entity.ProveedorDAO;
import co.com.cmdb.data.dao.entity.TipoDocumentoDAO;
import co.com.cmdb.data.dao.entity.concrete.SqlConnection;
import co.com.cmdb.data.dao.entity.concrete.postgresSql.ClientePostgresSqlDAO;
import co.com.cmdb.data.dao.entity.concrete.postgresSql.ProveedorPostgresSqlDAO;
import co.com.cmdb.data.dao.entity.concrete.postgresSql.TipoDocumentoPostgresSqlDAO;
import co.com.cmdb.data.dao.factory.DAOFactory;



public final class PostgresSQLDAOFactory extends SqlConnection implements DAOFactory {
	
	public PostgresSQLDAOFactory() {
		
		super();
		abrirConexion();
	}
	
	private void abrirConexion() {
		

        String url = "jdbc:postgresql://roundhouse.proxy.rlwy.net:52404/railway";
        String user = "postgres";
        String password = "SYamDPlVmZDbqznixMqCkVZUHKGDQFyz";
		

		try {
			
			setConexion(DriverManager.getConnection(url,user,password));
			
		} catch (final CMDBExceptions excepcion) {
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
			var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00020);
			
			throw new DataCMDBException(mensajeTecnico, mensajeUsuario, excepcion);
			
		} catch (final SQLException excepcion) {
			
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
			var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00020);
			
			throw new DataCMDBException(mensajeTecnico, mensajeUsuario, excepcion);
			
		} catch (final Exception excepcion) {
			
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
			var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00020);
			
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
			
			return new ClientePostgresSqlDAO(getConexion());
		
		}
		
		public TipoDocumentoDAO getTipoDocumentoDAO() {
			
			return new TipoDocumentoPostgresSqlDAO(getConexion());
			
		}

	
		public ProveedorDAO getProveedorDAO() {
			
			return new ProveedorPostgresSqlDAO(getConexion());
		}
		
		
			/*public static void main(String[] args) {
				try {
					DAOFactory factory = DAOFactory.getFactory();
	
					System.out.println("Iniciando transacción...");
					factory.iniciarTransaccion();
	
					System.out.println("Creando cliente aleatoriamente");
					TipoDocumentoEntity tipoDocumento = TipoDocumentoEntity.build().setIdentificador(1);
					ClienteEntity cliente = ClienteEntity.build().setNumeroDocumento("1041440078").setTipoDocumento(tipoDocumento)
							.setNombre("Juan Pablo").setApellidos("Hincapie Torres").setCorreo("Juanpht2004@gmail.com")
							.setTelefono(3216945654L).setEstado(true);
	
					factory.getClienteDAO().crear(cliente);
	
					/*System.out.println("Consultamos cli: ");
					var resultados = factory.getClienteDAO().consultar(ClienteEntity.build());
	
					for (ClienteEntity ciudadEntity : resultados) {
						System.out.print("idCliente: " + ciudadEntity.getIdentificador() + " --- " );
						System.out.println("nombre: " + ciudadEntity.getNombre());
					}
	
					System.out.println("Confirmando transacción...");
					factory.confirmarTransaccion();
					System.out.println("Cerrando conexión...");
					factory.cerrarConexion();
				} catch (final Exception excepcion) {
					excepcion.printStackTrace();
				}
			}*/
}


	
