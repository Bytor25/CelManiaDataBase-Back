package co.com.cmdb.business.usecase.impl.proveedor;

import java.util.List;

import co.com.cmdb.business.assembler.entity.impl.ProveedorAssemblerEntity;
import co.com.cmdb.business.domain.ProveedorDomain;
import co.com.cmdb.business.usecase.UseCaseWithReturn;
import co.com.cmdb.crosscutting.exceptions.custom.BusinessCMDBException;
import co.com.cmdb.crosscutting.exceptions.mesagecatalog.MessageCatalogStrategy;
import co.com.cmdb.crosscutting.exceptions.mesagecatalog.data.CodigoMensaje;
import co.com.cmdb.crosscutting.helpers.ObjectHelper;
import co.com.cmdb.data.dao.factory.DAOFactory;

public class ConsultarProveedor implements UseCaseWithReturn<ProveedorDomain, List<ProveedorDomain>> {

	private DAOFactory factory;

	public ConsultarProveedor(final DAOFactory factory) {
		if (ObjectHelper.getObjectHelper().isNull(factory)) {
			
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00030);
			var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00031);
			
			throw new BusinessCMDBException(mensajeTecnico, mensajeUsuario);
		}
		
		this.factory= factory;
		
	}

	@Override
	public List<ProveedorDomain> execute(final ProveedorDomain data) {
		var proveedorEntityFilter = ProveedorAssemblerEntity.getInstance().toEntity(data);
		var resultadosEntity = factory.getProveedorDAO().consultar(proveedorEntityFilter);
		
		return ProveedorAssemblerEntity.getInstance().toDomainCollection(resultadosEntity);
		
	}

}
