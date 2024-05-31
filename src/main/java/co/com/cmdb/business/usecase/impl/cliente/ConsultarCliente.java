package co.com.cmdb.business.usecase.impl.cliente;

import java.util.List;

import co.com.cmdb.business.assembler.entity.impl.ClienteAssemblerEntity;
import co.com.cmdb.business.domain.ClienteDomain;
import co.com.cmdb.business.usecase.UseCaseWithReturn;
import co.com.cmdb.crosscutting.exceptions.custom.BusinessCMDBException;
import co.com.cmdb.crosscutting.exceptions.mesagecatalog.MessageCatalogStrategy;
import co.com.cmdb.crosscutting.exceptions.mesagecatalog.data.CodigoMensaje;
import co.com.cmdb.crosscutting.helpers.ObjectHelper;
import co.com.cmdb.data.dao.factory.DAOFactory;



public class ConsultarCliente implements UseCaseWithReturn<ClienteDomain, List<ClienteDomain>>{
	
	
	private DAOFactory factory;

	public ConsultarCliente(final DAOFactory factory) {
		if (ObjectHelper.getObjectHelper().isNull(factory)) {
			
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00006);
			var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00009);
			
			throw new BusinessCMDBException(mensajeTecnico, mensajeUsuario);
		}
		this.factory= factory;
	}

	@Override
	public List<ClienteDomain> execute(final ClienteDomain data) {
		var clienteEntityFilter = ClienteAssemblerEntity.getInstance().toEntity(data);
		var resultadosEntity = factory.getClienteDAO().consultar(clienteEntityFilter);
		
		return ClienteAssemblerEntity.getInstance().toDomainCollection(resultadosEntity);
		
	}
    
	
	
	

}
