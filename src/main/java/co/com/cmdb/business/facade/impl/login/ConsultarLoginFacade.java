package co.com.cmdb.business.facade.impl.login;

import java.util.List;

import co.com.cmdb.business.assembler.dto.impl.LoginAssemblerDTO;
import co.com.cmdb.business.facade.FacadeWithReturn;
import co.com.cmdb.business.usecase.impl.login.ConsultarLogin;
import co.com.cmdb.crosscutting.exceptions.CMDBExceptions;
import co.com.cmdb.crosscutting.exceptions.custom.BusinessCMDBException;
import co.com.cmdb.crosscutting.exceptions.mesagecatalog.MessageCatalogStrategy;
import co.com.cmdb.crosscutting.exceptions.mesagecatalog.data.CodigoMensaje;
import co.com.cmdb.data.dao.factory.DAOFactory;
import co.com.cmdb.dto.LoginDTO;

public class ConsultarLoginFacade implements FacadeWithReturn<LoginDTO, List<LoginDTO>> {
	
	private DAOFactory daoFactory;
	
	public ConsultarLoginFacade() {
		daoFactory = DAOFactory.getFactory();
	}

	@Override
	public List<LoginDTO> execute(LoginDTO dto) {
		daoFactory.iniciarTransaccion();
		try {
            
			var useCase = new ConsultarLogin(daoFactory);
			var loginDomain = LoginAssemblerDTO.getInstance().toDomain(dto);
			var resultadosDomain = useCase.execute(loginDomain);
			return LoginAssemblerDTO.getInstance().toDTOCollection(resultadosDomain);
		
		}catch(final CMDBExceptions excepcion) {
			daoFactory.cancelarTransaccion();
			throw excepcion;
			
		}catch(final Exception excepcion) {
			
			daoFactory.cancelarTransaccion();
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00056);
			var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00057);
			throw new BusinessCMDBException(mensajeTecnico,mensajeUsuario,excepcion);
			
		} finally {
			
			daoFactory.cerrarConexion();
			
		}
	}

}
