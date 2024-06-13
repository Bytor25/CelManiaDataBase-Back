package co.com.cmdb.business.facade.impl.cliente;

import co.com.cmdb.business.assembler.dto.impl.ClienteAssemblerDTO;
import co.com.cmdb.business.facade.FacadeWithoutReturn;
import co.com.cmdb.business.usecase.impl.cliente.ActualizarCliente;
import co.com.cmdb.crosscutting.exceptions.CMDBExceptions;
import co.com.cmdb.crosscutting.exceptions.custom.BusinessCMDBException;
import co.com.cmdb.crosscutting.exceptions.mesagecatalog.MessageCatalogStrategy;
import co.com.cmdb.crosscutting.exceptions.mesagecatalog.data.CodigoMensaje;
import co.com.cmdb.data.dao.factory.DAOFactory;
import co.com.cmdb.dto.ClienteDTO;

public class ActualizarClientesFacade implements FacadeWithoutReturn<ClienteDTO> {
	
	public DAOFactory daoFactory;
	
	public ActualizarClientesFacade() {
		daoFactory = DAOFactory.getFactory();
		}

	@Override
	public void execute(ClienteDTO dato) {
		daoFactory.iniciarTransaccion();
		
		try {
            var useCase = new ActualizarCliente(daoFactory);
			var clienteDomain = ClienteAssemblerDTO.getInstance().toDomain(dato);
			
			useCase.execute(clienteDomain);
			
			daoFactory.confirmarTransaccion();
			
		}catch(final CMDBExceptions excepcion) {
			daoFactory.cancelarTransaccion();
			
			throw excepcion;
			
		}catch(final Exception excepcion) {
			daoFactory.cancelarTransaccion();
			
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00103);
			var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00104);
			
			throw new BusinessCMDBException(mensajeTecnico,mensajeUsuario,excepcion);
			
		} finally {
			daoFactory.cerrarConexion();
		}
		
	}

}
