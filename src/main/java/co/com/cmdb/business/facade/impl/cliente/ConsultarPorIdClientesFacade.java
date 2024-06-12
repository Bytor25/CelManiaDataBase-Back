package co.com.cmdb.business.facade.impl.cliente;

import co.com.cmdb.business.assembler.dto.impl.ClienteAssemblerDTO;
import co.com.cmdb.business.facade.FacadeWithReturn;
import co.com.cmdb.business.usecase.impl.cliente.ConsultarPorIdCliente;
import co.com.cmdb.crosscutting.exceptions.CMDBExceptions;
import co.com.cmdb.crosscutting.exceptions.custom.BusinessCMDBException;
import co.com.cmdb.data.dao.factory.DAOFactory;
import co.com.cmdb.dto.ClienteDTO;

public class ConsultarPorIdClientesFacade  implements FacadeWithReturn<ClienteDTO, ClienteDTO>{
	
	private DAOFactory daoFactory;
	
	public ConsultarPorIdClientesFacade() {
		daoFactory = DAOFactory.getFactory();
	}
    @Override
    public ClienteDTO execute(ClienteDTO dto) {
        daoFactory.iniciarTransaccion();
        try {
            var useCase = new ConsultarPorIdCliente(daoFactory);
            var clienteDomain = ClienteAssemblerDTO.getInstance().toDomain(dto);
            var resultadoDomain = useCase.execute(clienteDomain);
            return ClienteAssemblerDTO.getInstance().toDTO(resultadoDomain);

        } catch (final CMDBExceptions excepcion) {
            daoFactory.cancelarTransaccion();
            throw excepcion;

        } catch (final Exception excepcion) {
            daoFactory.cancelarTransaccion();
            var mensajeUsuario = "Error al consultar el cliente por ID.";
            var mensajeTecnico = "Error técnico al ejecutar el caso de uso ConsultarPorIdCliente.";
            throw new BusinessCMDBException(mensajeTecnico, mensajeUsuario, excepcion);

        } finally {
            daoFactory.cerrarConexion();
        }
    }

}
