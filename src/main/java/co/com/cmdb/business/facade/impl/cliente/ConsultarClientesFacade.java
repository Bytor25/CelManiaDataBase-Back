package co.com.cmdb.business.facade.impl.cliente;

import java.util.List;

import co.com.cmdb.business.assembler.dto.impl.ClienteAssemblerDTO;
import co.com.cmdb.business.facade.FacadeWithReturn;
import co.com.cmdb.business.usecase.impl.cliente.ConsultarCliente;
import co.com.cmdb.crosscutting.exceptions.CMDBExceptions;
import co.com.cmdb.crosscutting.exceptions.custom.BusinessCMDBException;
import co.com.cmdb.data.dao.factory.DAOFactory;
import co.com.cmdb.dto.ClienteDTO;



public class ConsultarClientesFacade implements FacadeWithReturn<ClienteDTO, List<ClienteDTO>> {
	
	private DAOFactory daoFactory;
	public ConsultarClientesFacade() {
		daoFactory = DAOFactory.getFactory();
	}

	@Override
	public List<ClienteDTO> excute(ClienteDTO dto) {
		// TODO Auto-generated method stub
		try {
            
			var useCase = new ConsultarCliente(daoFactory);
			var clienteDomain = ClienteAssemblerDTO.getInstance().toDomain(dto);
			var resultadosDomain = useCase.execute(clienteDomain);
			return ClienteAssemblerDTO.getInstance().toDTOCollection(resultadosDomain);
		
		}catch(final CMDBExceptions excepcion) {
			
			throw excepcion;
			
		}catch(final Exception excepcion) {
			
		
			var mensajeUsuario = "Sea precentado un problema tratando de consultar la informacion de la ciudad...";
			var mensajeTecnico = "Sea precentado un problema INESPERADO tratando de registrar la ciudad..";
			throw new BusinessCMDBException(mensajeTecnico,mensajeUsuario,excepcion);
			
		} finally {
			daoFactory.cerrarConexion();
		}
	}
	}
	


