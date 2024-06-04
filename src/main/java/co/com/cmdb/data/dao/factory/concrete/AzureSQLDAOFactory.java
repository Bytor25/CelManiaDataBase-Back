package co.com.cmdb.data.dao.factory.concrete;

import java.sql.DriverManager;
import java.sql.SQLException;

import co.com.cmdb.crosscutting.exceptions.CMDBExceptions;
import co.com.cmdb.crosscutting.exceptions.custom.DataCMDBException;
import co.com.cmdb.crosscutting.exceptions.mesagecatalog.MessageCatalogStrategy;
import co.com.cmdb.crosscutting.exceptions.mesagecatalog.data.CodigoMensaje;
import co.com.cmdb.crosscutting.helpers.SQLHelper;
import co.com.cmdb.data.dao.entity.ClienteDAO;
import co.com.cmdb.data.dao.entity.TipoDocumentoDAO;
import co.com.cmdb.data.dao.entity.concrete.SqlConnection;
import co.com.cmdb.data.dao.entity.concrete.azuresql.ClienteAzureSqlDAO;
import co.com.cmdb.data.dao.entity.concrete.azuresql.TipoDocumentoAzureSqlDAO;
import co.com.cmdb.data.dao.factory.DAOFactory;


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
			
			return new ClienteAzureSqlDAO(getConexion());
		
		}
		
		public TipoDocumentoDAO getTipoDocumentoDAO() {
			
			return new TipoDocumentoAzureSqlDAO(getConexion());
			
		}
		
		
		/*public static void main(String[] args) {
			try {
				DAOFactory factory = DAOFactory.getFactory();

				System.out.println("Iniciando transacción...");
				factory.iniciarTransaccion();

//				System.out.println("Creando ciudad aleatoriamente");
//				DepartamentoEntity departamento = DepartamentoEntity.build()
//						.setId(UUIDHelper.convertToUUID("7827155D-0A6B-4D6E-9807-C5B7097D94F0"));
//				CiudadEntity ciudad = CiudadEntity.build().setId(UUIDHelper.generate())
//						.setNombre("Rionegro-" + UUIDHelper.generate()).setDepartamento(departamento);
//
//				factory.getCiudadDAO().crear(ciudad);

				System.out.println("Consultamos cli: ");
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


	
