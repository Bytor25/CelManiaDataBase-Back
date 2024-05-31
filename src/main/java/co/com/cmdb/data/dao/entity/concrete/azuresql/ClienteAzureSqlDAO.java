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
import co.com.cmdb.crosscutting.helpers.ObjectHelper;
import co.com.cmdb.crosscutting.helpers.TextHelper;
import co.com.cmdb.crosscutting.helpers.UUIDHelper;
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
		  
		final StringBuilder sentenciaSqlSql = new StringBuilder();
		

		sentenciaSqlSql.append("INSERT INTO Cliente (id,numero_documento, tipo_documento, nombre, apellido, correo, telefono, estado)");
		sentenciaSqlSql.append("SELECT ?, ?, ?, ?, ?, ?, ?, ?)");


		
		try(final PreparedStatement sentenciaSqlSqlPreparada = getConexion().prepareStatement(sentenciaSqlSql.toString())){
			
			sentenciaSqlSqlPreparada.setObject(1, data.getIdentificador());


			sentenciaSqlSqlPreparada.setObject(2, data.getTipoDocumento().getIdentificador());
			sentenciaSqlSqlPreparada.setString(3, data.getIdentificador());
			sentenciaSqlSqlPreparada.setString(4,data.getNombre());
			sentenciaSqlSqlPreparada.setString(5, data.getApellido());
			sentenciaSqlSqlPreparada.setString(6, data.getCorreo());
			sentenciaSqlSqlPreparada.setLong(7, data.getTelefono());
			sentenciaSqlSqlPreparada.setBoolean(8, data.isEstado());
			

			sentenciaSqlSqlPreparada.executeUpdate();
			
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
	
	/*@Override
	public List<ClienteEntity> consultar(ClienteEntity data) {
		final StringBuilder sentenciaSql = new StringBuilder();
		sentenciaSql.append("SLECT C.numero_documento, TD.nombre, C.nombre, C.apellidos,C.correo, C.telefono");
		sentenciaSql.append("FROM clientes C");
		sentenciaSql.append("JOIN tipos_documentos TD");
		sentenciaSql.append("ON C.tipo_documento = TD.identificador");
		sentenciaSql.append("WHERE C.numero_documento = ?");

		final List<Object> parametros = new ArrayList<>();

		if (!ObjectHelper.getObjectHelper().isNull(data.getId()) && !data.getId().equals(UUIDHelper.getDefault())) {
			sentenciaSql.append(" AND C.numero_documento = ?");
			parametros.add(data.getId());
		}
		if (!TextHelper.isNullOrEmpty(data.getNombre())) {
			sentenciaSql.append(" AND C.nombre = ?");
			parametros.add(data.getNombre());
		}
		if (!ObjectHelper.getObjectHelper().isNull(data.getTipoDocumento())
				&& !ObjectHelper.getObjectHelper().isNull(data.getTipoDocumento().getId())
				&& !data.getTipoDocumento().getId().equals(UUIDHelper.getDefault())) {
			sentenciaSql.append(" AND c.departamento_id = ?");
			parametros.add(data.getTipoDocumento().getId());
		}

		final List<ClienteEntity> clientees = new ArrayList<>();

		try (final PreparedStatement sentenciaSqlSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())) {
			for (int i = 0; i < parametros.size(); i++) {
				sentenciaSqlSqlPreparada.setObject(i + 1, parametros.get(i));
			}

			try (final ResultSet resultado = sentenciaSqlSqlPreparada.executeQuery()) {
				while (resultado.next()) {
					ClienteEntity cliente = ClienteEntity.build();
					cliente.setId(UUIDHelper.convertToUUID(resultado.getString("id")));
					cliente.setNombre(resultado.getString("nombre"));

					TipoDocumentoEntity departamento = TipoDocumentoEntity.build();
					departamento.setId(UUIDHelper.convertToUUID(resultado.getString("idDepartamento")));
					departamento.setNombre(resultado.getString("nombreDepartamento"));

					PaisEntity pais = PaisEntity.build();
					pais.setId(UUIDHelper.convertToUUID(resultado.getString("idPais")));
					pais.setNombre(resultado.getString("nombrePais"));

					departamento.setPais(pais);
					cliente.setDepartamento(departamento);

					clientees.add(cliente);
				}
			}

		} catch (final SQLException excepcion) {
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00023);
			var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00048);
			throw new DataCMDBException(mensajeUsuario, mensajeTecnico, excepcion);

		} catch (final Exception excepcion) {
			var mensajeUsuario =MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00023);
			var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00048);
			throw new DataCMDBException(mensajeUsuario, mensajeTecnico, excepcion);
		}

		return clientees;
	}*/


	@Override
	public void mofidicar(ClienteEntity data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminar(UUID data) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Optional<ClienteEntity> consultarPorId(final UUID id) {
		final StringBuilder sentenciaSql = new StringBuilder();
		
		sentenciaSql.append("SELECT C.numero_documento, C.nombre, C.apellidos,C.correo, C.telefono");
		sentenciaSql.append("FROM clientes C");
		sentenciaSql.append("WHERE C.numero_documento = ?");
		
		Optional<ClienteEntity> resultado = Optional.empty();
		
		try(final var sentenciaSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())){
			sentenciaSqlPreparada.setObject(1,id);
			resultado = ejecutarConsultaPorId(sentenciaSqlPreparada);
			
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
	
	private final Optional<ClienteEntity> ejecutarConsultaPorId(final PreparedStatement sentenciaSqlPreparada){
		
		Optional<ClienteEntity> resultado = Optional.empty();
		try(final var resultados = sentenciaSqlPreparada.executeQuery()){
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
		sentenciaSql.append("SELECT C.id,C.numero_documento, C.nombre, C.apellidos,C.correo, C.telefono");
		sentenciaSql.append("FROM clientes C");
		sentenciaSql.append("JOIN tipos_documentos TD");
		sentenciaSql.append("ON C.tipo_documento = TD.identificador");
		sentenciaSql.append(" WHERE 1=1");

		final List<Object> parametros = new ArrayList<>();

		if (!ObjectHelper.getObjectHelper().isNull(data.getIdentificador()) && !data.getIdentificador().equals(UUIDHelper.getDefault())) {
			sentenciaSql.append(" AND C.numero_documento = ?");
			parametros.add(data.getIdentificador());
		}
		if (!TextHelper.isNullOrEmpty(data.getNombre())) {
			sentenciaSql.append(" AND C.nombre = ?");
			parametros.add(data.getNombre());
		}
		if (!ObjectHelper.getObjectHelper().isNull(data.getTipoDocumento())
				&& !ObjectHelper.getObjectHelper().isNull(data.getTipoDocumento().getIdentificador())) {
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
					cliente.setIdentificador(resultado.getString("C.numero_documento"));
					cliente.setNombre(resultado.getString("nombre"));

					TipoDocumentoEntity tipoDocumento = TipoDocumentoEntity.build();
					tipoDocumento.setNombre(resultado.getString("TD.nombre"));


					clientes.add(cliente);
				}
			}

		} catch (final SQLException excepcion) {
			var mensajeUsuario = "XD";
			var mensajeTecnico = "XD";
			throw new DataCMDBException(mensajeUsuario,mensajeTecnico,excepcion);

		} catch (final Exception excepcion) {
			var mensajeUsuario = "LOL que mal";
			var mensajeTecnico = "LOL que mal";
			throw new DataCMDBException(mensajeUsuario,mensajeTecnico,excepcion);
		}

		return clientes;
	}
	
}
