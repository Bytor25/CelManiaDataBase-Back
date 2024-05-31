package co.com.cmdb.data.dao.entity.concrete;

import java.sql.Connection;

import co.com.cmdb.crosscutting.exceptions.custom.DataCMDBException;
import co.com.cmdb.crosscutting.exceptions.mesagecatalog.MessageCatalogStrategy;
import co.com.cmdb.crosscutting.exceptions.mesagecatalog.data.CodigoMensaje;
import co.com.cmdb.crosscutting.helpers.SQLHelper;

public class SqlConnection {
	
	private Connection conexion;
	
	protected SqlConnection(Connection conexion) {
		
		setConexion(conexion);
		
		
	}
	
	protected SqlConnection() {
		
		super();
		
	}
	
	protected final Connection getConexion() {
		return conexion;
	}
	
	protected final void setConexion(Connection conexion) {
		
		if(!SQLHelper.isOpen(conexion)) {
			
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00002);
			var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00016);
			
			throw new DataCMDBException(mensajeUsuario, mensajeTecnico);
	
		}
		
		this.conexion = conexion;
		
	}

}
