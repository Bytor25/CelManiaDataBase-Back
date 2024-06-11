package co.com.cmdb.business.facade.impl.login;

import co.com.cmdb.business.assembler.dto.impl.LoginAssemblerDTO;
import co.com.cmdb.business.facade.FacadeWithReturn;
import co.com.cmdb.business.usecase.impl.login.ValidarLogin;
import co.com.cmdb.crosscutting.exceptions.CMDBExceptions;
import co.com.cmdb.crosscutting.exceptions.custom.BusinessCMDBException;
import co.com.cmdb.data.dao.factory.DAOFactory;
import co.com.cmdb.dto.LoginDTO;

public class ValidarLoginFacade implements FacadeWithReturn<LoginDTO, Boolean> {
	
	private DAOFactory daoFactory;
	
	public ValidarLoginFacade() {
		
		daoFactory = DAOFactory.getFactory();
		
	}

	@Override
	public final Boolean execute(final LoginDTO dto) {
		daoFactory.iniciarTransaccion();
		try {
			
			var loginDomain = LoginAssemblerDTO.getInstance().toDomain(dto);
			
			final ValidarLogin useCase = new ValidarLogin(daoFactory);
			return useCase.execute(loginDomain);
			
		} catch (CMDBExceptions exception) {
			
			daoFactory.cancelarTransaccion();
			throw exception;
			
		} catch (Exception exception) {
			
			daoFactory.cancelarTransaccion();
			
			var mensajeUsuario = "Se presentó un problema tratando de validar el usuario";
			var mensajeTecnico = "Se presentó un problema INESPERADO tratando de autenticar el usuario";
			
			throw new BusinessCMDBException(mensajeUsuario, mensajeTecnico);
			
		} finally {
			
			daoFactory.cerrarConexion();
			
		}
	}

	




	
	

}
