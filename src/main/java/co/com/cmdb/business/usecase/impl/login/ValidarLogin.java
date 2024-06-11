package co.com.cmdb.business.usecase.impl.login;

import co.com.cmdb.business.domain.LoginDomain;
import co.com.cmdb.business.usecase.UseCaseWithReturn;
import co.com.cmdb.crosscutting.exceptions.custom.BusinessCMDBException;
import co.com.cmdb.crosscutting.exceptions.mesagecatalog.MessageCatalogStrategy;
import co.com.cmdb.crosscutting.exceptions.mesagecatalog.data.CodigoMensaje;
import co.com.cmdb.data.dao.factory.DAOFactory;

public class ValidarLogin implements UseCaseWithReturn<LoginDomain, Boolean>{
	
	private final DAOFactory factory;
	
	public ValidarLogin(final DAOFactory factory) {
		
		this.factory = factory;
		
	}

	@Override
	public Boolean execute(final LoginDomain login) {
		
		if(login.getUsuario().isEmpty()) {
			
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00059);
			throw new BusinessCMDBException(mensajeUsuario);
			
		}
		
		var loginDAO = factory.getLoginDAO();
		boolean isValidated = loginDAO.validarUsuario(login.getUsuario(), login.getPassword());
	
		if(!isValidated) {
			
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00060);
			throw new BusinessCMDBException(mensajeUsuario);
			
		}
		
		return true;
	}

}
