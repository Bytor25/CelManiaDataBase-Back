package co.com.cmdb.data.dao.entity.concrete.azuresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import co.com.cmdb.crosscutting.exceptions.custom.DataCMDBException;
import co.com.cmdb.data.dao.entity.ClienteDAO;
import co.com.cmdb.data.dao.entity.concrete.SqlConnection;
import co.com.cmdb.entity.ClienteEntity;

public class ClienteAzureSqlDAO extends SqlConnection implements ClienteDAO {

	public ClienteAzureSqlDAO(final Connection conexion) { 
		
		super(conexion);
	}
	@Override
	public void crear(ClienteEntity data) {
		  
		final StringBuilder sentenciaSql = new StringBuilder();
		

		sentenciaSql.append("INSERT INTO Cliente (id,numero_documento, tipo_documento, nombre, apellido, correo, telefono, estado)");
		sentenciaSql.append("SELECT ?, ?, ?, ?, ?, ?, ?, ?)");


		
		try(final PreparedStatement sentenciaSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())){
			
			sentenciaSqlPreparada.setObject(1, data.getId());

			sentenciaSqlPreparada.setObject(2, data.getTipoDocumento().getId());
			sentenciaSqlPreparada.setString(3, data.getIdentificador());
			sentenciaSqlPreparada.setString(4,data.getNombre());
			sentenciaSqlPreparada.setString(5, data.getApellido());
			sentenciaSqlPreparada.setString(6, data.getCorreo());
			sentenciaSqlPreparada.setLong(7, data.getTelefono());
			sentenciaSqlPreparada.setBoolean(8, data.isEstado());
			
			sentenciaSqlPreparada.executeUpdate();
			
		} catch (final SQLException excepcion) {
			
			var mensajeUsuario = "Se ha presentado un problema tratando de registrar un cliente, Si el problema persiste contacte al administrador";
			var mensajeTecnico = "Se ha presentado una excepción tipo SQLException tratando de realizar el insert del cliente ... en la tabla de Cliente de la base de datos Azure SQL. Para más detalles, revise de forma completa la excepción raíz presentada";
			
			throw new DataCMDBException(mensajeUsuario, mensajeTecnico, excepcion);
			
		} catch (final Exception excepcion) {
			
			var mensajeUsuario = "Se ha presentado un problema tratando de registrar un cliente, Si el problema persiste contacte al administrador";
			var mensajeTecnico = "Se ha presentado un problema INESPERADO con una excepción de tipo Exception tratando de realizar el insert del cliente ... en la tabla cliente de la base de datos Azure SQL. Para más detalles, revise de forma completa la excepción raíz presentada";
			
			throw new DataCMDBException(mensajeUsuario, mensajeTecnico, excepcion);
		}
		
	}
	


	@Override
	public List<ClienteEntity> consultar(ClienteEntity data) {
		// TODO Auto-generated method stub
		return null;
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
	public Optional<ClienteEntity> consultarPorId(final UUID id) {
		final StringBuilder sentencia = new StringBuilder();
		
		sentencia.append("SLECT C.numero_documento, TD.nombre, C.nombre, C.apellidos,C.correo, C.telefono");
		sentencia.append("FROM clientes C");
		sentencia.append("JOIN tipos_documentos TD");
		sentencia.append("ON C.tipo_documento = TD.identificador");
		sentencia.append("WHERE C.numero_documento = ?");
		
		Optional<ClienteEntity> resultado = Optional.empty();
		
		try(final var sentenciaPreparada = getConexion().prepareStatement(sentencia.toString())){
			sentenciaPreparada.setObject(1,id);
			resultado = ejecutarConsultaPorId(sentenciaPreparada);
			
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
	
	private final Optional<ClienteEntity> ejecutarConsultaPorId(final PreparedStatement sentenciaPreparada){
		
		Optional<ClienteEntity> resultado = Optional.empty();
		try(final var resultados = sentenciaPreparada.executeQuery()){
			if(resultados.next()) {
				var NumeroDocumentoEntity =  resultados.getString("numeroIdentificacion");
				
			}
		}catch(final SQLException excepcion) {
			
		}catch(final Exception excepcion) {
			
		}
		return resultado;
	}
	
	

}
