package co.com.cmdb.business.usecase.impl.cliente;

import co.com.cmdb.business.assembler.entity.impl.ClienteAssemblerEntity;
import co.com.cmdb.business.domain.ClienteDomain;
import co.com.cmdb.business.usecase.UseCaseWithReturn;
import co.com.cmdb.crosscutting.exceptions.custom.BusinessCMDBException;
import co.com.cmdb.crosscutting.helpers.TextHelper;
import co.com.cmdb.data.dao.factory.DAOFactory;

public class ConsultarPorIdTipoDocumentoCliente implements UseCaseWithReturn<ClienteDomain, ClienteDomain> {
	
	private DAOFactory factory;

	public ConsultarPorIdTipoDocumentoCliente(final DAOFactory factory) {
		if (factory == null) {
			
			var mensajeUsuario = "";
			var mensajeTecnico = "";
			
			throw new BusinessCMDBException(mensajeTecnico, mensajeUsuario);
		}
		this.factory= factory;
	}

	@Override
	public ClienteDomain execute(ClienteDomain data) {
		
        if (TextHelper.isNullOrEmpty(data.getNumeroDocumento())) {
            var mensajeUsuario = "El número de documento no puede ser nulo.";
            var mensajeTecnico = "El número de documento es nulo o vacío en el método execute de ConsultarPorIdCliente.";
            throw new BusinessCMDBException(mensajeTecnico, mensajeUsuario);
        }
        
        if(data.getTipoDocumento().getIdentificador() == 0) {
            var mensajeUsuario = "El tipo de documento no puede ser nulo.";
            var mensajeTecnico = "El tipo de documento es nulo o vacío en el método execute de ConsultarPorIdTipoDocumentoCliente.";
            throw new BusinessCMDBException(mensajeTecnico, mensajeUsuario);
        }

        var clienteEntityFilter = ClienteAssemblerEntity.getInstance().toEntity(data);
        
        var resultadoEntity = factory.getClienteDAO().consultarPoridTipoDocumento(data.getNumeroDocumento(), data.getTipoDocumento().getIdentificador());

        if (resultadoEntity == null) {
            var mensajeUsuario = "No se encontró el cliente con el numero de documento proporcionado.";
            var mensajeTecnico = "No se encontró ninguna entidad Cliente con el numero de documento: " + clienteEntityFilter.getNumeroDocumento();
            throw new BusinessCMDBException(mensajeTecnico, mensajeUsuario);
        }

        return ClienteAssemblerEntity.getInstance().toDomain(resultadoEntity);
	}

}