package co.com.cmdb.business.facade.impl.cliente;

import co.com.cmdb.business.assembler.dto.impl.ClienteAssemblerDTO;
import co.com.cmdb.business.facade.FacadeWithoutReturn;
import co.com.cmdb.business.usecase.impl.cliente.RegistrarCliente;
import co.com.cmdb.data.dao.factory.DAOFactory;
import co.com.cmdb.dto.ClienteDTO;
import co.edu.uco.pch.crosscutting.exceptions.PCHException;
import co.edu.uco.pch.crosscutting.exceptions.customs.BusinessPCHException;



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
			
		}catch(final PCHException excepcion) {
			daoFactory.cancelarTransaccion();
			
		}catch(final Exception excepcion) {
			daoFactory.cancelarTransaccion();
			
			var mensajeUsuario = "Sea precentado un problema tratando de registra la informacion del cliente...";
			var mensajeTecnico = "Sea precentado un problema INESPERADO tratando de registrar el cliente..";
			throw new BusinessPCHException(mensajeTecnico,mensajeUsuario,excepcion);
			
		} finally {
			daoFactory.cerrarConexion();
		}
	}
	}
	

