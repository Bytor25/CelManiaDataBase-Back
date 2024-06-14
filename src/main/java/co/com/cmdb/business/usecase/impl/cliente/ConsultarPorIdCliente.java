package co.com.cmdb.business.usecase.impl.cliente;

import java.util.List;

import co.com.cmdb.business.assembler.entity.impl.ClienteAssemblerEntity;
import co.com.cmdb.business.domain.ClienteDomain;
import co.com.cmdb.business.usecase.UseCaseWithReturn;
import co.com.cmdb.crosscutting.exceptions.custom.BusinessCMDBException;
import co.com.cmdb.crosscutting.exceptions.mesagecatalog.MessageCatalogStrategy;
import co.com.cmdb.crosscutting.exceptions.mesagecatalog.data.CodigoMensaje;
import co.com.cmdb.crosscutting.helpers.TextHelper;
import co.com.cmdb.data.dao.factory.DAOFactory;

public class ConsultarPorIdCliente implements UseCaseWithReturn<ClienteDomain, List<ClienteDomain>>{
	
	private DAOFactory factory;

	public ConsultarPorIdCliente(final DAOFactory factory) {
		if (factory == null) {
			
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00006);
			var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00204);
			
			throw new BusinessCMDBException(mensajeTecnico, mensajeUsuario);
		}
		this.factory= factory;
	}

	@Override
    public List<ClienteDomain> execute(final ClienteDomain data) {
	
        if (TextHelper.isNullOrEmpty(data.getNumeroDocumento())) {
        	var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00045);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00200);
            throw new BusinessCMDBException(mensajeTecnico, mensajeUsuario);
        }

        var clienteEntityFilter = ClienteAssemblerEntity.getInstance().toEntity(data);
        
        var resultadoEntity = factory.getClienteDAO().consultarPorid(clienteEntityFilter.getNumeroDocumento());

        if (resultadoEntity == null) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00202);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00203) + clienteEntityFilter.getNumeroDocumento();
            throw new BusinessCMDBException(mensajeTecnico, mensajeUsuario);
        }

        return ClienteAssemblerEntity.getInstance().toDomainCollection(resultadoEntity);
    }

}
