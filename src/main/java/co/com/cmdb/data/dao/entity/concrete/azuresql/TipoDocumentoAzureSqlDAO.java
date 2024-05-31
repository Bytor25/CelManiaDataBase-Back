
package co.com.cmdb.data.dao.entity.concrete.azuresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import co.com.cmdb.crosscutting.exceptions.custom.DataCMDBException;
import co.com.cmdb.data.dao.entity.TipoDocumentoDAO;
import co.com.cmdb.data.dao.entity.concrete.SqlConnection;
import co.com.cmdb.entity.TipoDocumentoEntity;

public class TipoDocumentoAzureSqlDAO extends SqlConnection implements TipoDocumentoDAO{
	
	public TipoDocumentoAzureSqlDAO(final Connection conexion) {
		
		super(conexion);

	}
	
	public void crear(TipoDocumentoEntity data) {
		
		final StringBuilder sentenciaSql = new StringBuilder();
		
		sentenciaSql.append("INSERT INTO TipoDocumento (id, Identificador, Nombre)");
		sentenciaSql.append("SELECT ?, ?, ?)");
		
		try(final PreparedStatement sentenciaSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())){
			
			sentenciaSqlPreparada.setObject(1, data.getId());
			sentenciaSqlPreparada.setObject(2, data.getIdentificador());
			sentenciaSqlPreparada.setString(3,data.getNombre());
			
			sentenciaSqlPreparada.executeUpdate();
			
		} catch (final SQLException excepcion) {
			
			var mensajeUsuario = "Se ha presentado un problema tratando de registrar un TipoDocumento, Si el problema persiste contacte al administrador";
			var mensajeTecnico = "Se ha presentado una excepción tipo SQLException tratando de realizar el insert del cliente ... en la tabla de TipoDocumento de la base de datos Azure SQL. Para más detalles, revise de forma completa la excepción raíz presentada";
			
			throw new DataCMDBException(mensajeUsuario, mensajeTecnico, excepcion);
			
		} catch (final Exception excepcion) {
			
			var mensajeUsuario = "Se ha presentado un problema tratando de registrar un TipoDocumento, Si el problema persiste contacte al administrador";
			var mensajeTecnico = "Se ha presentado un problema INESPERADO con una excepción de tipo Exception tratando de realizar el insert del TipoDocumento ... en la tabla TipoDocumento de la base de datos Azure SQL. Para más detalles, revise de forma completa la excepción raíz presentada";
			
			throw new DataCMDBException(mensajeUsuario, mensajeTecnico, excepcion);
		}
		
	}
		
	
	
	
	public List<TipoDocumentoEntity> consultar(TipoDocumentoEntity data){
		
		return null;
		
	}

}
