package co.com.cmdb.business.facade.impl.cliente;

import co.com.cmdb.business.assembler.dto.impl.ClienteAssemblerDTO;
import co.com.cmdb.business.facade.FacadeWithoutReturn;
import co.com.cmdb.business.usecase.impl.cliente.RegistrarCliente;
import co.com.cmdb.data.dao.factory.DAOFactory;
import co.com.cmdb.dto.ClienteDTO;
import co.com.cmdb.crosscutting.exceptions.CMDBExceptions;
import co.com.cmdb.crosscutting.exceptions.custom.BusinessCMDBException;
import co.com.cmdb.crosscutting.exceptions.mesagecatalog.MessageCatalogStrategy;
import co.com.cmdb.crosscutting.exceptions.mesagecatalog.data.CodigoMensaje;




public class RegistrarClientesFacade implements FacadeWithoutReturn<ClienteDTO> {
	
	public DAOFactory daoFactory;
	public RegistrarClientesFacade() {
		daoFactory = DAOFactory.getFactory();
		}

	@Override
	public void execute(final ClienteDTO dto) {
		// TODO Auto-generated method stub
		daoFactory.iniciarTransaccion();
		
		try {
            var useCase = new RegistrarCliente(daoFactory);
			var clienteDomain = ClienteAssemblerDTO.getInstance().toDomain(dto);
			
			useCase.execute(clienteDomain);
			
			daoFactory.confirmarTransaccion();
			
		}catch(final CMDBExceptions excepcion) {
			daoFactory.cancelarTransaccion();
			
		}catch(final Exception excepcion) {
			daoFactory.cancelarTransaccion();
			
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00007);
			var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00008);
			
			throw new BusinessCMDBException(mensajeTecnico,mensajeUsuario,excepcion);
			
		} finally {
			daoFactory.cerrarConexion();
		}
	}
	}
	

