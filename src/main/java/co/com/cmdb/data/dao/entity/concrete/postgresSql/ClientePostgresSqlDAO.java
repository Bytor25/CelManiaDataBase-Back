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
import co.com.cmdb.crosscutting.helpers.BooleanHelper;
import co.com.cmdb.crosscutting.helpers.ObjectHelper;
import co.com.cmdb.crosscutting.helpers.TextHelper;
import co.com.cmdb.crosscutting.helpers.UUIDHelper;
import co.com.cmdb.data.dao.entity.ClienteDAO;
import co.com.cmdb.data.dao.entity.concrete.SqlConnection;
import co.com.cmdb.entity.ClienteEntity;
import co.com.cmdb.entity.TipoDocumentoEntity;

public final class ClientePostgresSqlDAO extends SqlConnection implements ClienteDAO {

	public ClientePostgresSqlDAO(final Connection conexion) { 
		
		super(conexion);
	}
	@Override
	public void crear(final ClienteEntity data) {
		  
		final StringBuilder sentenciaSql = new StringBuilder();

		sentenciaSql.append("INSERT INTO clientes(identificador, numero_documento, tipo_documento, nombre, apellidos, correo, telefono, estado) ");
		sentenciaSql.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?); ");
		
		try(final PreparedStatement sentenciaSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())){
			

			sentenciaSqlPreparada.setObject(1, data.getIdentificador());
			sentenciaSqlPreparada.setObject(2,data.getNumeroDocumento());
			sentenciaSqlPreparada.setObject(3, data.getTipoDocumento().getIdentificador());
			sentenciaSqlPreparada.setString(4,data.getNombre());
			sentenciaSqlPreparada.setString(5, data.getApellidos());
			sentenciaSqlPreparada.setString(6, data.getCorreo());
			sentenciaSqlPreparada.setLong(7, data.getTelefono());
			sentenciaSqlPreparada.setBoolean(8, data.isEstado());
			

			sentenciaSqlPreparada.executeUpdate();

			
		} catch (final SQLException excepcion) {
			
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00017);
			var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00018);
			
			throw new DataCMDBException(mensajeUsuario, mensajeTecnico, excepcion);
			
		} catch (final Exception excepcion) {
			
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00017);
			var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00019);
			
			throw new DataCMDBException(mensajeUsuario, mensajeTecnico, excepcion);
		}
		
	}



	@Override
	public void mofidicar(ClienteEntity data) {
		
	    final StringBuilder sentenciaSql = new StringBuilder();

	    sentenciaSql.append("UPDATE clientes SET ");
	    sentenciaSql.append("tipo_documento = ?, nombre = ?, apellidos = ?, correo = ?, telefono = ? ");
	    sentenciaSql.append("WHERE numero_documento = ? AND identificador = ?");

	    try (final PreparedStatement sentenciaSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())) {
	        sentenciaSqlPreparada.setObject(1, data.getTipoDocumento().getIdentificador());
	        sentenciaSqlPreparada.setString(2, data.getNombre());
	        sentenciaSqlPreparada.setString(3, data.getApellidos());
	        sentenciaSqlPreparada.setString(4, data.getCorreo());
	        sentenciaSqlPreparada.setLong(5, data.getTelefono());
	        sentenciaSqlPreparada.setString(6, data.getNumeroDocumento());
	        sentenciaSqlPreparada.setObject(7, data.getIdentificador());

	        sentenciaSqlPreparada.executeUpdate();

	    } catch (final SQLException excepcion) {
	        var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00080);
	        var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00081);
	        throw new DataCMDBException(mensajeUsuario, mensajeTecnico, excepcion);

	    } catch (final Exception excepcion) {
	    	
	        var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00080);
	        var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00082);
	        throw new DataCMDBException(mensajeUsuario, mensajeTecnico, excepcion);
	    }
		
	}

	@Override
	public List<ClienteEntity> consultar(ClienteEntity data) {
        final StringBuilder sentenciaSql = new StringBuilder();

        sentenciaSql.append("SELECT C.identificador as identificadorCliente,C.tipo_documento as identificadorTipoDocumento, "
        		+ "C.numero_documento as numeroDocumentoCliente, C.tipo_documento as identificadorDocumento, TD.nombre as nombreTipoId, "
        		+ "C.nombre as nombreCliente, C.apellidos as apellidosCliente, C.correo as correoCliente, C.telefono as telefonoCliente, C.estado as estadoCliente ");
        sentenciaSql.append("FROM clientes C ");
        sentenciaSql.append("INNER JOIN tipos_documentos TD ");
        sentenciaSql.append("ON C.tipo_documento = TD.identificador");
        sentenciaSql.append(" WHERE 1=1");
        
	    final List<Object> parametros = new ArrayList<>();
	    if (!ObjectHelper.getObjectHelper().isNull(data.getIdentificador()) && !data.getIdentificador().equals(UUIDHelper.getDefault())) {
	    	sentenciaSql.append(" AND C.identificador = ?");
	    	parametros.add(data.getIdentificador());
	    }
	    
	    if(!TextHelper.isNullOrEmpty(data.getNumeroDocumento())) {
	    	sentenciaSql.append(" AND C.numero_documento = ?" );
	    	parametros.add(data.getNumeroDocumento());
	    }
	    
	    if (!TextHelper.isNullOrEmpty(data.getNombre())) {
	        sentenciaSql.append(" AND C.nombre = ?");
	        parametros.add(data.getNombre());
	    }
	    
	    if (!TextHelper.isNullOrEmpty(data.getApellidos())) {
	        sentenciaSql.append(" AND C.apellidos = ?");
	        parametros.add(data.getApellidos());
	    }
	    
	    if (!TextHelper.isNullOrEmpty(data.getCorreo())) {
	        sentenciaSql.append(" AND C.correo = ?");
	        parametros.add(data.getCorreo());
	    }
	    
	    if (data.getTelefono() != 0) {
	        sentenciaSql.append(" AND C.telefono = ?");
	        parametros.add(data.getTelefono());
	    }
	    
		if(!BooleanHelper.isNull(data.isEstado())) {
			sentenciaSql.append(" AND C.estado = ?");
			parametros.add(data.isEstado());
		}
	    
	    if (!ObjectHelper.getObjectHelper().isNull(data.getTipoDocumento()) && 
	    		!ObjectHelper.getObjectHelper().isNull(data.getTipoDocumento().getNombre()) && 
	    		!data.getTipoDocumento().getNombre().equals(TextHelper.EMPTY)) {
	    	
	        sentenciaSql.append(" AND TD.nombre = ?");
	        parametros.add(data.getTipoDocumento().getNombre());
	    }
	    
	    if (!ObjectHelper.getObjectHelper().isNull(data.getTipoDocumento()) && 
	    		!ObjectHelper.getObjectHelper().isNull(data.getTipoDocumento().getIdentificador()) && 
	    		data.getTipoDocumento().getIdentificador() != 0) {
	    	
	        sentenciaSql.append(" AND C.tipo_documento = ?");
	        parametros.add(data.getTipoDocumento().getIdentificador());
	    }
	    
	    


        final List<ClienteEntity> clientes = new ArrayList<>();
        
	    try (final PreparedStatement sentenciaSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())) {
	        for (int i = 0; i < parametros.size(); i++) {
	            sentenciaSqlPreparada.setObject(i + 1, parametros.get(i));
	        }

        try (final ResultSet resultado = sentenciaSqlPreparada.executeQuery()) {

            while (resultado.next()) {
                ClienteEntity cliente = ClienteEntity.build();
                
                cliente.setIdentificador(UUID.fromString(resultado.getString("identificadorCliente")));
                cliente.setNumeroDocumento(resultado.getString("numeroDocumentoCliente"));
                cliente.setNombre(resultado.getString("nombreCliente"));
                cliente.setApellidos(resultado.getString("apellidosCliente"));
                cliente.setCorreo(resultado.getString("correoCliente"));
                cliente.setTelefono(resultado.getLong("telefonoCliente"));
                cliente.setEstado(resultado.getBoolean("estadoCliente"));
               
                TipoDocumentoEntity tipoDocumento = TipoDocumentoEntity.build();
                
                tipoDocumento.setNombre(resultado.getString("nombreTipoId"));
                tipoDocumento.setIdentificador(resultado.getInt("identificadorTipoDocumento"));

                cliente.setTipoDocumento(tipoDocumento);

                clientes.add(cliente);
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
        
	    

        return clientes;
	
	}
	
	@Override
	public List<ClienteEntity> consultarPorid(String numeroDocumento) {
	  
	    final StringBuilder sentenciaSql = new StringBuilder();

	    sentenciaSql.append("SELECT C.identificador as identificadorCliente, C.tipo_documento as identificadorTipoDocumento, ")
	                .append("C.numero_documento as numeroDocumentoCliente, TD.nombre as nombreTipoId, ")
	                .append("C.nombre as nombreCliente, C.apellidos as apellidosCliente, C.correo as correoCliente, C.telefono as telefonoCliente, C.estado as estadoCliente ")
	                .append("FROM clientes C ")
	                .append("INNER JOIN tipos_documentos TD ")
	                .append("ON C.tipo_documento = TD.identificador ")
	                .append("WHERE C.numero_documento = ?");

	    final List<ClienteEntity> clientes = new ArrayList<>();

	    try (final PreparedStatement sentenciaSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())) {
	        sentenciaSqlPreparada.setObject(1, numeroDocumento);
	        

	        try (final ResultSet resultado = sentenciaSqlPreparada.executeQuery()) {
	            while (resultado.next()) {

	                ClienteEntity cliente = ClienteEntity.build();

	                cliente.setIdentificador(UUID.fromString(resultado.getString("identificadorCliente")));
	                cliente.setNumeroDocumento(resultado.getString("numeroDocumentoCliente"));
	                cliente.setNombre(resultado.getString("nombreCliente"));
	                cliente.setApellidos(resultado.getString("apellidosCliente"));
	                cliente.setCorreo(resultado.getString("correoCliente"));
	                cliente.setTelefono(resultado.getLong("telefonoCliente"));
	                cliente.setEstado(resultado.getBoolean("estadoCliente"));

	                TipoDocumentoEntity tipoDocumento = TipoDocumentoEntity.build();
	                tipoDocumento.setNombre(resultado.getString("nombreTipoId"));
	                tipoDocumento.setIdentificador(resultado.getInt("identificadorTipoDocumento"));

	                cliente.setTipoDocumento(tipoDocumento);
	                
	                clientes.add(cliente);
	            }
	        }
	    } catch (final SQLException excepcion) {
	        var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00083);
	        var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00084);
	        throw new DataCMDBException(mensajeUsuario, mensajeTecnico, excepcion);

	    } catch (final Exception excepcion) {
	        var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00083);
	        var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00085);
	        throw new DataCMDBException(mensajeUsuario, mensajeTecnico, excepcion);
	    }

	    return clientes;
	}

	
    @Override
    public boolean existeCorreo(String correo, String numeroDocumento) {
        final StringBuilder sentenciaSql = new StringBuilder();
        
        sentenciaSql.append("SELECT COUNT(*) ");
        sentenciaSql.append("FROM clientes ");
        sentenciaSql.append("WHERE correo = ? AND numero_documento <> ?");
        
        try (final PreparedStatement sentenciaSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())) {
            sentenciaSqlPreparada.setString(1, correo);
            sentenciaSqlPreparada.setString(2, numeroDocumento);
            
            try (final ResultSet resultado = sentenciaSqlPreparada.executeQuery()) {
                if (resultado.next()) {
                    return resultado.getInt(1) > 0;
                }
            }
        } catch (final SQLException excepcion) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00086);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00087);
            throw new DataCMDBException(mensajeUsuario, mensajeTecnico, excepcion);
        } catch (final Exception excepcion) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00086);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00088);
            throw new DataCMDBException(mensajeUsuario, mensajeTecnico, excepcion);
        }
        
        return false;
    }

    @Override
    public boolean existeTelefono(long telefono, String numeroDocumento) {
        final StringBuilder sentenciaSql = new StringBuilder();
        
        sentenciaSql.append("SELECT COUNT(*) ");
        sentenciaSql.append("FROM clientes ");
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
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00089);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00090);
            throw new DataCMDBException(mensajeUsuario, mensajeTecnico, excepcion);
        } catch (final Exception excepcion) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00089);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00091);
            throw new DataCMDBException(mensajeUsuario, mensajeTecnico, excepcion);
        }
        
        return false;
    }
	@Override
	public ClienteEntity consultarPoridTipoDocumento(String numeroDocumento, int identificadorDocumento, UUID identificador) {
		
		  
	    final StringBuilder sentenciaSql = new StringBuilder();

	    sentenciaSql.append("SELECT C.identificador as identificadorCliente, C.tipo_documento as identificadorTipoDocumento, ")
	                .append("C.numero_documento as numeroDocumentoCliente, TD.nombre as nombreTipoId, ")
	                .append("C.nombre as nombreCliente, C.apellidos as apellidosCliente, C.correo as correoCliente, C.telefono as telefonoCliente, C.estado as estadoCliente ")
	                .append("FROM clientes C ")
	                .append("INNER JOIN tipos_documentos TD ")
	                .append("ON C.tipo_documento = TD.identificador ")
	                .append("WHERE C.numero_documento = ? AND C.tipo_documento = ? AND C.identificador <> ?");

	    ClienteEntity cliente = null;

	    try (final PreparedStatement sentenciaSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())) {
	    	
	        sentenciaSqlPreparada.setObject(1, numeroDocumento);
	        sentenciaSqlPreparada.setObject(2, identificadorDocumento);
	        sentenciaSqlPreparada.setObject(3, identificador);

	        try (final ResultSet resultado = sentenciaSqlPreparada.executeQuery()) {
	            if (resultado.next()) {

	                cliente = ClienteEntity.build();

	                cliente.setIdentificador(UUID.fromString(resultado.getString("identificadorCliente")));
	                cliente.setNumeroDocumento(resultado.getString("numeroDocumentoCliente"));
	                cliente.setNombre(resultado.getString("nombreCliente"));
	                cliente.setApellidos(resultado.getString("apellidosCliente"));
	                cliente.setCorreo(resultado.getString("correoCliente"));
	                cliente.setTelefono(resultado.getLong("telefonoCliente"));
	                cliente.setEstado(resultado.getBoolean("estadoCliente"));

	                TipoDocumentoEntity tipoDocumento = TipoDocumentoEntity.build();
	                tipoDocumento.setNombre(resultado.getString("nombreTipoId"));
	                tipoDocumento.setIdentificador(resultado.getInt("identificadorTipoDocumento"));

	                cliente.setTipoDocumento(tipoDocumento);
	            }
	        }
	    } catch (final SQLException excepcion) {
	        var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00083);
	        var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00084);
	        throw new DataCMDBException(mensajeUsuario, mensajeTecnico, excepcion);

	    } catch (final Exception excepcion) {
	        var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00083);
	        var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00085);
	        throw new DataCMDBException(mensajeUsuario, mensajeTecnico, excepcion);
	    }

	    return cliente;
	}

}
