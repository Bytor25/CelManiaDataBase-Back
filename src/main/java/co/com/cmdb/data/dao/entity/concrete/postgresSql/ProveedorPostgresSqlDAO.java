package co.com.cmdb.data.dao.entity.concrete.postgresSql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.com.cmdb.crosscutting.exceptions.custom.DataCMDBException;
import co.com.cmdb.crosscutting.exceptions.mesagecatalog.MessageCatalogStrategy;
import co.com.cmdb.crosscutting.exceptions.mesagecatalog.data.CodigoMensaje;
import co.com.cmdb.crosscutting.helpers.ObjectHelper;
import co.com.cmdb.crosscutting.helpers.TextHelper;
import co.com.cmdb.crosscutting.helpers.UUIDHelper;
import co.com.cmdb.data.dao.entity.ProveedorDAO;
import co.com.cmdb.data.dao.entity.concrete.SqlConnection;
import co.com.cmdb.entity.ProveedorEntity;
import co.com.cmdb.entity.TipoDocumentoEntity;

public class ProveedorPostgresSqlDAO extends SqlConnection implements ProveedorDAO {
	
	public ProveedorPostgresSqlDAO(final Connection conexion) { 
		
		super(conexion);
	}

	@Override
	public void crear(final ProveedorEntity data) {
		
		final StringBuilder sentenciaSql = new StringBuilder();
		

		sentenciaSql.append("INSERT INTO proveedores(identificador, numero_documento, tipo_documento, nombre, telefono, estado ) ");
		sentenciaSql.append("VALUES (?, ?, ?, ?, ?, ?); ");


		
		try(final PreparedStatement sentenciaSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())){
			

			sentenciaSqlPreparada.setObject(1, data.getIdentificador());
			sentenciaSqlPreparada.setObject(2,data.getNumeroDocumento());
			sentenciaSqlPreparada.setObject(3, data.getTipoDocumento().getIdentificador());
			sentenciaSqlPreparada.setString(4,data.getNombre());
			sentenciaSqlPreparada.setLong(5, data.getTelefono());
			sentenciaSqlPreparada.setBoolean(6, data.isEstado());
			

			sentenciaSqlPreparada.executeUpdate();

			
		} catch (final SQLException excepcion) {
			
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00021);
			var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00022);
			
			throw new DataCMDBException(mensajeUsuario, mensajeTecnico, excepcion);
			
		} catch (final Exception excepcion) {
			
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00021);
			var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00023);
			
			throw new DataCMDBException(mensajeUsuario, mensajeTecnico, excepcion);
		}
		
	}

	@Override
	public List<ProveedorEntity> consultar(ProveedorEntity data) {
		 
		final StringBuilder sentenciaSql = new StringBuilder();

        sentenciaSql.append("SELECT P.identificador as identificadorProveedor, P.numero_documento as numeroDocumento, TD.nombre as nombreTipoId, P.nombre as nombreProveedor, P.telefono as telefonoProveedor ");
        sentenciaSql.append("FROM proveedores P ");
        sentenciaSql.append("INNER JOIN tipos_documentos TD ");
        sentenciaSql.append("ON P.tipo_documento = TD.identificador ");
        sentenciaSql.append(" WHERE 1=1");
        
	    final List<Object> parametros = new ArrayList<>();
	    if (!ObjectHelper.getObjectHelper().isNull(data.getIdentificador()) && !data.getIdentificador().equals(UUIDHelper.getDefault())) {
	    	sentenciaSql.append(" AND P.identificador = ?");
	    	parametros.add(data.getIdentificador());
	    }
	    
	    if(!TextHelper.isNullOrEmpty(data.getNumeroDocumento())) {
	    	sentenciaSql.append(" AND P.numero_documento = ?" );
	    	parametros.add(data.getNumeroDocumento());
	    }
	    
	    if (!TextHelper.isNullOrEmpty(data.getNombre())) {
	        sentenciaSql.append(" AND P.nombre = ?");
	        parametros.add(data.getNombre());
	    }
	    
	    if (data.getTelefono() != 0) {
	        sentenciaSql.append(" AND P.telefono = ?");
	        parametros.add(data.getTelefono());
	    }
	    
	    if (!ObjectHelper.getObjectHelper().isNull(data.getTipoDocumento()) && 
	    		!ObjectHelper.getObjectHelper().isNull(data.getTipoDocumento().getNombre()) && 
	    		!data.getTipoDocumento().getNombre().equals(TextHelper.EMPTY)) {
	    	
	        sentenciaSql.append(" AND TD.nombre = ?");
	        parametros.add(data.getTipoDocumento().getNombre());
	    }


        final List<ProveedorEntity> proveedores = new ArrayList<>();
        
	    try (final PreparedStatement sentenciaSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())) {
	        for (int i = 0; i < parametros.size(); i++) {
	            sentenciaSqlPreparada.setObject(i + 1, parametros.get(i));
	        }

        try (final ResultSet resultado = sentenciaSqlPreparada.executeQuery()) {

            while (resultado.next()) {
                ProveedorEntity proveedor = ProveedorEntity.build();
                
                proveedor.setIdentificador(UUID.fromString(resultado.getString("identificadorProveedor")));
                proveedor.setNumeroDocumento(resultado.getString("numeroDocumento"));
                proveedor.setNombre(resultado.getString("nombreProveedor"));
                proveedor.setTelefono(resultado.getLong("telefonoProveedor"));
                TipoDocumentoEntity tipoDocumento = TipoDocumentoEntity.build();
                tipoDocumento.setNombre(resultado.getString("nombreTipoId"));
                proveedor.setTipoDocumento(tipoDocumento);

                proveedores.add(proveedor);
            }
        }

        } catch (final SQLException excepcion) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00027);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00028);
            throw new DataCMDBException(mensajeUsuario, mensajeTecnico, excepcion);

        } catch (final Exception excepcion) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00027);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00029);
            throw new DataCMDBException(mensajeUsuario, mensajeTecnico, excepcion);
        }
        
	    

        return proveedores;
	
	}

	@Override
	public void mofidicar(ProveedorEntity data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean existeTelefono(long telefono, String numeroDocumento) {
		
		final StringBuilder sentenciaSql = new StringBuilder();
        
        sentenciaSql.append("SELECT COUNT(*) ");
        sentenciaSql.append("FROM proveedores ");
        sentenciaSql.append("WHERE telefono = ? AND numero_documento <> ?");
        
        try (final PreparedStatement sentenciaSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())) {
            sentenciaSqlPreparada.setLong(1, telefono);
            sentenciaSqlPreparada.setString(2, numeroDocumento);
            
            try (final ResultSet resultado = sentenciaSqlPreparada.executeQuery()) {
                if (resultado.next()) {
                    return resultado.getInt(1) > 0;
                }
            }
        } catch (final SQLException excepcion) {
            var mensajeUsuario = "a";
            var mensajeTecnico = "a";
            throw new DataCMDBException(mensajeUsuario, mensajeTecnico, excepcion);
        } catch (final Exception excepcion) {
            var mensajeUsuario = "a";
            var mensajeTecnico = "a";
            throw new DataCMDBException(mensajeUsuario, mensajeTecnico, excepcion);
        }
        
        return false;
    }

	@Override
	public ProveedorEntity consultarPorNumeroDocumento(String numeroDocumento) {
		
		  
	    final StringBuilder sentenciaSql = new StringBuilder();

	    sentenciaSql.append("SELECT P.identificador as identificadorProveedor, P.tipo_documento as identificadorTipoDocumento, ")
	                .append("P.numero_documento as numeroDocumentoProveedor, TD.nombre as nombreTipoId, ")
	                .append("P.nombre as nombreProveedor, P.telefono as telefonoProveedor ")
	                .append("FROM proveedores P ")
	                .append("INNER JOIN tipos_documentos TD ")
	                .append("ON P.tipo_documento = TD.identificador ")
	                .append("WHERE P.numero_documento = ?");

	    ProveedorEntity proveedor = null;

	    try (final PreparedStatement sentenciaSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())) {
	        sentenciaSqlPreparada.setObject(1, numeroDocumento);

	        try (final ResultSet resultado = sentenciaSqlPreparada.executeQuery()) {
	            if (resultado.next()) {
	            	
	            	
	                proveedor = ProveedorEntity.build();
	                
	                proveedor.setIdentificador(UUID.fromString(resultado.getString("identificadorProveedor")));
	                proveedor.setNumeroDocumento(resultado.getString("numeroDocumentoProveedor"));
	                proveedor.setNombre(resultado.getString("nombreProveedor"));
	                proveedor.setTelefono(resultado.getLong("telefonoProveedor"));
	                
	                TipoDocumentoEntity tipoDocumento = TipoDocumentoEntity.build();
	                tipoDocumento.setNombre(resultado.getString("nombreTipoId"));
	                proveedor.setTipoDocumento(tipoDocumento);


	            }
	        }
	    } catch (final SQLException excepcion) {
	        var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00024);
	        var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00025);
	        throw new DataCMDBException(mensajeUsuario, mensajeTecnico, excepcion);

	    } catch (final Exception excepcion) {
	        var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00024);
	        var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00026);
	        throw new DataCMDBException(mensajeUsuario, mensajeTecnico, excepcion);
	    }

	    return proveedor;

	}



}
