
package co.com.cmdb.data.dao.entity.concrete.postgresSql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.com.cmdb.crosscutting.exceptions.custom.DataCMDBException;
import co.com.cmdb.crosscutting.helpers.IntegerHelper;
import co.com.cmdb.crosscutting.helpers.TextHelper;
import co.com.cmdb.data.dao.entity.TipoDocumentoDAO;
import co.com.cmdb.data.dao.entity.concrete.SqlConnection;
import co.com.cmdb.entity.TipoDocumentoEntity;

public class TipoDocumentoPostgresSqlDAO extends SqlConnection implements TipoDocumentoDAO{
	
	public TipoDocumentoPostgresSqlDAO(final Connection conexion) {
		
		super(conexion);

	}

	public List<TipoDocumentoEntity> consultar(TipoDocumentoEntity data){
		final StringBuilder sentenciaSql = new StringBuilder();
		
		sentenciaSql.append("SELECT TD.identificador as idTipoDocumento, TD.nombre as nombreTipoDocumento ");
		sentenciaSql.append("FROM tipos_documentos TD ");
		sentenciaSql.append("WHERE 1=1");
		
		final List<Object> parametros = new ArrayList<>();
		
		if(!IntegerHelper.isNullOrEmpty(data.getIdentificador())) {
			sentenciaSql.append(" AND TD.identificador = ?");
			parametros.add(data.getIdentificador());
		}
		
		if(!TextHelper.isNullOrEmpty(data.getNombre())) {
			sentenciaSql.append(" AND TD.nombre = ?");
			parametros.add(data.getNombre());
		}
		
		final List<TipoDocumentoEntity> tiposDocumentos = new ArrayList<>();
		
		try (final PreparedStatement sentenciaSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())){
			for(int i = 0; i< parametros.size(); i++) {
				sentenciaSqlPreparada.setObject(i + 1, parametros.get(i));
			}
			
		try (final ResultSet resultado = sentenciaSqlPreparada.executeQuery()){
			while (resultado.next()) {
				TipoDocumentoEntity tipoDocumento = TipoDocumentoEntity.build();
				
				tipoDocumento.setIdentificador(resultado.getInt("idTipoDocumento"));
				tipoDocumento.setNombre(resultado.getString("nombreTipoDocumento"));
				tiposDocumentos.add(tipoDocumento);
			}
		}
		} catch ( final SQLException excepcion) {
			
			var mensajeUsuario = "consultarTiposDocumentos";
			var mensajeTecnico = "ConsultarTiposDocumentos";
			throw new DataCMDBException(mensajeUsuario, mensajeTecnico, excepcion);
		} catch ( final Exception excepcion){
			
			var mensajeUsuario = "consultarTiposDocumentos";
			var mensajeTecnico = "ConsultarTiposDocumentos";
			throw new DataCMDBException(mensajeUsuario, mensajeTecnico, excepcion);
		}
		
		return tiposDocumentos;
		
	}

}
