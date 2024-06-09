package co.com.cmdb.data.dao.factory;

import co.com.cmdb.data.dao.entity.ClienteDAO;
import co.com.cmdb.data.dao.entity.ProveedorDAO;
import co.com.cmdb.data.dao.entity.TipoDocumentoDAO;

import co.com.cmdb.data.dao.factory.concrete.PostgresSQLDAOFactory;

public interface DAOFactory {
	
	static DAOFactory getFactory() {
		
		return new PostgresSQLDAOFactory();
		
	}
	
	void cerrarConexion();
	
	void iniciarTransaccion();
	
	void confirmarTransaccion();
	
	void cancelarTransaccion();
	
	ClienteDAO getClienteDAO();
	
	TipoDocumentoDAO getTipoDocumentoDAO();
	
	ProveedorDAO getProveedorDAO();


}
