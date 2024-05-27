package co.com.cmdb.data.dao.factory.concrete;

import java.sql.DriverManager;
import java.sql.SQLException;

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
		
		try {
			
			String connectionString = "jdbc://<server>:<port>.....";
			setConexion(DriverManager.getConnection(connectionString));
			
		} catch (final SQLException excepcion) {
			
		} catch (final Exception excepcion) {
			
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
	}
	
