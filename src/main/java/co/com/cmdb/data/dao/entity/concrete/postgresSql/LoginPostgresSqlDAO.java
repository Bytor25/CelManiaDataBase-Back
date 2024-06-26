package co.com.cmdb.data.dao.entity.concrete.postgresSql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.com.cmdb.crosscutting.exceptions.custom.BusinessCMDBException;
import co.com.cmdb.crosscutting.exceptions.custom.DataCMDBException;
import co.com.cmdb.crosscutting.exceptions.mesagecatalog.MessageCatalogStrategy;
import co.com.cmdb.crosscutting.exceptions.mesagecatalog.data.CodigoMensaje;
import co.com.cmdb.crosscutting.helpers.BooleanHelper;
import co.com.cmdb.crosscutting.helpers.IntegerHelper;
import co.com.cmdb.crosscutting.helpers.TextHelper;
import co.com.cmdb.data.dao.entity.LoginDAO;
import co.com.cmdb.data.dao.entity.concrete.SqlConnection;
import co.com.cmdb.entity.LoginEntity;

public class LoginPostgresSqlDAO extends SqlConnection implements LoginDAO {
	
public LoginPostgresSqlDAO(final Connection conexion) { 
		
		super(conexion);
	}

	@Override
	public boolean validarUsuario(final String usuario, final int password) {
		
		final StringBuilder sentenciaSql = new StringBuilder();
		
		sentenciaSql.append("SELECT L.usuario as usuarioLogin ");
		sentenciaSql.append("FROM logins L ");
		sentenciaSql.append("WHERE usuario = ? ");
		sentenciaSql.append("AND password = ? ");
		
		try (final PreparedStatement sentenciaSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())) {
			
			sentenciaSqlPreparada.setString(1, usuario);
			sentenciaSqlPreparada.setInt(2, password);
			
			try (final ResultSet resultado = sentenciaSqlPreparada.executeQuery()) {
				
				return resultado.next();
				
			}
			
		} catch (SQLException exception) {
			
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00061);
			var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00062);
			
			throw new BusinessCMDBException(mensajeTecnico, mensajeUsuario, exception);
			
		} catch(final Exception exception) {
			
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00061);
			var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00063);
			
			throw new BusinessCMDBException(mensajeTecnico, mensajeUsuario, exception);
			
		}
		
	}

	@Override
	public List<LoginEntity> consultar(LoginEntity data) {
		final StringBuilder sentenciaSql = new StringBuilder();
		
		sentenciaSql.append("SELECT L.usuario as usuarioLogin, L.password as passwordLogin, L.estado as estadoLogin ");
		sentenciaSql.append("FROM logins L ");
		sentenciaSql.append("WHERE 1=1 ");
		
		final List<Object> parametros = new ArrayList<>();
		
		if(!TextHelper.isNullOrEmpty(data.getUsuario())) {
	    	sentenciaSql.append(" AND L.usuario = ?" );
	    	parametros.add(data.getUsuario());
	    }
		
		if(IntegerHelper.isNull(data.getPassword())) {
			sentenciaSql.append(" AND L.password = ?");
			parametros.add(data.getPassword());
		}
		
		if(!BooleanHelper.isNull(data.isEstado())) {
			sentenciaSql.append(" AND L.estado = ?");
			parametros.add(data.isEstado());
		}
		
		final List<LoginEntity> logins = new ArrayList<>();
		
		try (final PreparedStatement sentenciaSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())) {
	        for (int i = 0; i < parametros.size(); i++) {
	            sentenciaSqlPreparada.setObject(i + 1, parametros.get(i));
	        }
	        
	        try (final ResultSet resultado = sentenciaSqlPreparada.executeQuery()) {

	            while (resultado.next()) {
	                LoginEntity login = LoginEntity.build();
	           
	                login.setUsuario(resultado.getString("usuarioLogin"));
	                login.setPassword(resultado.getInt("passwordLogin"));
	                login.setEstado(resultado.getBoolean("estadoLogin"));
	                logins.add(login);
	            }
	        }
	        
		}  catch (final SQLException excepcion) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00064);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00065);
            throw new DataCMDBException(mensajeUsuario, mensajeTecnico, excepcion);

        } catch (final Exception excepcion) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00064);
            var mensajeTecnico =  MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00066);
            throw new DataCMDBException(mensajeUsuario, mensajeTecnico, excepcion);
        }
		
		return logins;
	}


	

}
