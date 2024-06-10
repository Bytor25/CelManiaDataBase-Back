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
	public void validar(LoginEntity data) {
		
	}

	@Override
	public List<LoginEntity> consultar(LoginEntity data) {
		final StringBuilder sentenciaSql = new StringBuilder();
		
		sentenciaSql.append("SELECT L.usuario as usuario, L.password as password, L.estado as estado ");
		sentenciaSql.append("FROM logins L ");
		sentenciaSql.append(" WHERE 1=1 ");
		
		final List<Object> parametros = new ArrayList<>();
		
		if(!TextHelper.isNullOrEmpty(data.getUsuario())) {
	    	sentenciaSql.append(" AND L.usuario = ?" );
	    	parametros.add(data.getUsuario());
	    }
		
		if(!IntegerHelper.isNull(data.getPassword())) {
			sentenciaSql.append(" AND L.password = ? ");
			parametros.add(data.getPassword());
		}
		
		final List<LoginEntity> logins = new ArrayList<>();
		
		try (final PreparedStatement sentenciaSqlPreparada = getConexion().prepareStatement(sentenciaSql.toString())) {
	        for (int i = 0; i < parametros.size(); i++) {
	            sentenciaSqlPreparada.setObject(i + 1, parametros.get(i));
	        }
	        
	        try (final ResultSet resultado = sentenciaSqlPreparada.executeQuery()) {

	            while (resultado.next()) {
	                LoginEntity login = LoginEntity.build();
	           
	                login.setUsuario(resultado.getString("usuario"));
	                login.setPassword(resultado.getInt("password"));
	                login.setEstado(resultado.getBoolean("estado"));
	                logins.add(login);
	            }
	        }
	        
		}  catch (final SQLException excepcion) {
            var mensajeUsuario = ""; //Msg24
            var mensajeTecnico = ""; //Msg25
            throw new DataCMDBException(mensajeUsuario, mensajeTecnico, excepcion);

        } catch (final Exception excepcion) {
            var mensajeUsuario = ""; //Msg24
            var mensajeTecnico = ""; //Msg26
            throw new DataCMDBException(mensajeUsuario, mensajeTecnico, excepcion);
        }
		
		return logins;

		
	}

	

}
