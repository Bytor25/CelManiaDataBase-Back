package co.com.cmdb.data.dao.entity.concrete.azuresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import co.com.cmdb.crosscutting.exceptions.custom.DataCMDBException;
import co.com.cmdb.crosscutting.exceptions.mesagecatalog.MessageCatalogStrategy;
import co.com.cmdb.crosscutting.exceptions.mesagecatalog.data.CodigoMensaje;


import co.com.cmdb.data.dao.entity.ClienteDAO;
import co.com.cmdb.data.dao.entity.concrete.SqlConnection;
import co.com.cmdb.entity.ClienteEntity;
import co.com.cmdb.entity.TipoDocumentoEntity;

public class ClienteAzureSqlDAO extends SqlConnection implements ClienteDAO {

	public ClienteAzureSqlDAO(final Connection conexion) { 
		
		super(conexion);
	}
	@Override
	public void crear(ClienteEntity data) {
		  
		final StringBuilder sentenciaSqlSqlSql = new StringBuilder();
		

		sentenciaSqlSqlSql.append("INSERT INTO Cliente (id,numero_documento, tipo_documento, nombre, apellido, correo, telefono, estado)");
		sentenciaSqlSqlSql.append("SELECT ?, ?, ?, ?, ?, ?, ?, ?)");


		
		try(final PreparedStatement sentenciaSqlSqlSqlPreparada = getConexion().prepareStatement(sentenciaSqlSqlSql.toString())){
			

			sentenciaSqlSqlSqlPreparada.setObject(1, data.getIdentificador());


			sentenciaSqlSqlSqlPreparada.setObject(2, data.getTipoDocumento().getIdentificador());
			sentenciaSqlSqlSqlPreparada.setString(3, data.getIdentificador());
			sentenciaSqlSqlSqlPreparada.setString(4,data.getNombre());
			sentenciaSqlSqlSqlPreparada.setString(5, data.getApellidos());
			sentenciaSqlSqlSqlPreparada.setString(6, data.getCorreo());
			sentenciaSqlSqlSqlPreparada.setLong(7, data.getTelefono());
			sentenciaSqlSqlSqlPreparada.setBoolean(8, data.isEstado());
			

			sentenciaSqlSqlSqlPreparada.executeUpdate();

			
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminar(UUID data) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Optional<ClienteEntity> consultarPorId(final String identificador) {
		final StringBuilder sentenciaSqlSql = new StringBuilder();
		
		sentenciaSqlSql.append("SELECT C.numero_documento, C.nombre, C.apellidos,C.correo, C.telefono");
		sentenciaSqlSql.append("FROM clientes C");
		sentenciaSqlSql.append("WHERE C.numero_documento = ?");
		
		Optional<ClienteEntity> resultado = Optional.empty();
		
		try(final var sentenciaSqlSqlPreparada = getConexion().prepareStatement(sentenciaSqlSql.toString())){
			sentenciaSqlSqlPreparada.setObject(1,identificador);
			resultado = ejecutarConsultaPorId(sentenciaSqlSqlPreparada);
			
		} catch (final DataCMDBException excepcion) {
			throw excepcion;
		} catch (final SQLException excepcion) {
			var mensajeUsuario = "";
			var mensajeTecnico = "";
			throw new DataCMDBException(mensajeUsuario, mensajeTecnico, excepcion);
			
		} catch (final Exception excepcion) {
			var mensajeUsuario = "";
			var mensajeTecnico = "";
			throw new DataCMDBException(mensajeUsuario, mensajeTecnico, excepcion);
		}
		return resultado;
	}
	
	private final Optional<ClienteEntity> ejecutarConsultaPorId(final PreparedStatement sentenciaSqlSqlPreparada){
		
		Optional<ClienteEntity> resultado = Optional.empty();
		try(final var resultados = sentenciaSqlSqlPreparada.executeQuery()){
			if(resultados.next()) {
				
				var clienteEntity = ClienteEntity.build( resultados.getString("numero_documento"),null , resultados.getString("nombre"), resultados.getString("apellidos"), resultados.getString("correo"), resultados.getInt("telefono"),resultados.getBoolean("estado"));
				resultado = Optional.of(clienteEntity);
			}
		}catch(final SQLException excepcion) {
			
		}catch(final Exception excepcion) {
			
		}
		return resultado;
	}
	@Override
	public List<ClienteEntity> consultar(ClienteEntity data) {
        final StringBuilder sentenciaSql = new StringBuilder();

        sentenciaSql.append("SELECT C.numero_documento as numeroDocumento, TD.nombre as nombreTipoId, C.nombre as nombreCliente, C.apellidos as apellidosCliente, C.correo as correoCliente, C.telefono as telefonoCliente ");
        sentenciaSql.append("FROM clientes C ");
        sentenciaSql.append("JOIN tipos_documentos TD ");
        sentenciaSql.append("ON C.tipo_documento = TD.identificador");

        final List<ClienteEntity> clientes = new ArrayList<>();

        try (final Connection conexion = getConexion();
             final PreparedStatement sentenciaSqlPreparada = conexion.prepareStatement(sentenciaSql.toString());
             final ResultSet resultado = sentenciaSqlPreparada.executeQuery()) {

            while (resultado.next()) {
                ClienteEntity cliente = ClienteEntity.build();
                cliente.setIdentificador(resultado.getString("numeroDocumento"));
                cliente.setNombre(resultado.getString("nombreCliente"));
                cliente.setApellidos(resultado.getString("apellidosCliente"));
                cliente.setCorreo(resultado.getString("correoCliente"));
                cliente.setTelefono(resultado.getInt("telefonoCliente"));
                TipoDocumentoEntity tipoDocumento = TipoDocumentoEntity.build();
                tipoDocumento.setNombre(resultado.getString("nombreTipoId"));
                cliente.setTipoDocumento(tipoDocumento);

                clientes.add(cliente);
            }

        } catch (final SQLException excepcion) {
            var mensajeUsuario = "Error al consultar los datos.";
            var mensajeTecnico = "Error técnico: " + excepcion.getMessage();
            throw new DataCMDBException(mensajeUsuario, mensajeTecnico, excepcion);

        } catch (final Exception excepcion) {
            var mensajeUsuario = "Error inesperado al consultar los datos.";
            var mensajeTecnico = "Error técnico: " + excepcion.getMessage();
            throw new DataCMDBException(mensajeUsuario, mensajeTecnico, excepcion);
        }

        return clientes;
	}

}
