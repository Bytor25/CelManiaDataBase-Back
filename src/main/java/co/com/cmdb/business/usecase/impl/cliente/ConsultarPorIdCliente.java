package co.com.cmdb.business.usecase.impl.cliente;

import co.com.cmdb.business.assembler.entity.impl.ClienteAssemblerEntity;
import co.com.cmdb.business.domain.ClienteDomain;
import co.com.cmdb.business.usecase.UseCaseWithReturn;
import co.com.cmdb.crosscutting.exceptions.custom.BusinessCMDBException;
import co.com.cmdb.crosscutting.helpers.TextHelper;
import co.com.cmdb.data.dao.factory.DAOFactory;

public class ConsultarPorIdCliente implements UseCaseWithReturn<ClienteDomain, ClienteDomain>{
	
	private DAOFactory factory;

	public ConsultarPorIdCliente(final DAOFactory factory) {
		if (factory == null) {
			
			var mensajeUsuario = "";
			var mensajeTecnico = "";
			
			throw new BusinessCMDBException(mensajeTecnico, mensajeUsuario);
		}
		this.factory= factory;
	}

	@Override
    public ClienteDomain execute(ClienteDomain data) {
		
		System.out.println(data.getNumeroDocumento());
        if (TextHelper.isNullOrEmpty(data.getNumeroDocumento())) {
            var mensajeUsuario = "El número de documento no puede ser nulo.";
            var mensajeTecnico = "El número de documento es nulo o vacío en el método execute de ConsultarPorIdCliente.";
            throw new BusinessCMDBException(mensajeTecnico, mensajeUsuario);
        }

        var clienteEntityFilter = ClienteAssemblerEntity.getInstance().toEntity(data);
        
        System.out.println(clienteEntityFilter.getNumeroDocumento());
        
        var resultadoEntity = factory.getClienteDAO().consultarPorid(clienteEntityFilter.getNumeroDocumento());

        if (resultadoEntity == null) {
            var mensajeUsuario = "No se encontró el cliente con el ID proporcionado.";
            var mensajeTecnico = "No se encontró ninguna entidad Cliente con el ID: " + clienteEntityFilter.getNumeroDocumento();
            throw new BusinessCMDBException(mensajeTecnico, mensajeUsuario);
        }

        return ClienteAssemblerEntity.getInstance().toDomain(resultadoEntity);
    }

}
