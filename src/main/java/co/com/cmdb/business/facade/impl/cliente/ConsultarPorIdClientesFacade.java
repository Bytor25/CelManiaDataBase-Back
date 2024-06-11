package co.com.cmdb.business.facade.impl.cliente;

import java.util.List;

import co.com.cmdb.business.assembler.dto.impl.ClienteAssemblerDTO;
import co.com.cmdb.business.facade.FacadeWithReturn;
import co.com.cmdb.business.usecase.impl.cliente.ConsultarPorIdCliente;
import co.com.cmdb.crosscutting.exceptions.CMDBExceptions;
import co.com.cmdb.crosscutting.exceptions.custom.BusinessCMDBException;
import co.com.cmdb.data.dao.factory.DAOFactory;
import co.com.cmdb.dto.ClienteDTO;

public class ConsultarPorIdClientesFacade  implements FacadeWithReturn<ClienteDTO, List<ClienteDTO>>{
	
	private DAOFactory daoFactory;
	
	public ConsultarPorIdClientesFacade() {
		daoFactory = DAOFactory.getFactory();
	}
	@Override
	public List<ClienteDTO> execute(ClienteDTO dto) {
		daoFactory.iniciarTransaccion();
		try {
            
			var useCase = new ConsultarPorIdCliente(daoFactory);
			var clienteDomain = ClienteAssemblerDTO.getInstance().toDomain(dto);
			var resultadosDomain = useCase.execute(clienteDomain);
			return ClienteAssemblerDTO.getInstance().toDTOCollection(resultadosDomain);
		
		}catch(final CMDBExceptions excepcion) {
			daoFactory.cancelarTransaccion();
			throw excepcion;
			
		}catch(final Exception excepcion) {
			
			daoFactory.cancelarTransaccion();
			var mensajeUsuario = "";
			var mensajeTecnico = "";
			throw new BusinessCMDBException(mensajeTecnico,mensajeUsuario,excepcion);
			
		} finally {
			daoFactory.cerrarConexion();
		}
	}

}
