package co.com.cmdb.business.usecase.impl.proveedor;

import java.util.List;

import co.com.cmdb.business.assembler.entity.impl.ProveedorAssemblerEntity;
import co.com.cmdb.business.domain.ProveedorDomain;
import co.com.cmdb.business.usecase.UseCaseWithReturn;
import co.com.cmdb.crosscutting.exceptions.custom.BusinessCMDBException;
import co.com.cmdb.crosscutting.exceptions.mesagecatalog.MessageCatalogStrategy;
import co.com.cmdb.crosscutting.exceptions.mesagecatalog.data.CodigoMensaje;
import co.com.cmdb.crosscutting.helpers.TextHelper;
import co.com.cmdb.data.dao.factory.DAOFactory;

public class ConsultarPorIdProveedor implements UseCaseWithReturn<ProveedorDomain, List<ProveedorDomain>> {
	
	private DAOFactory factory;

	public ConsultarPorIdProveedor(final DAOFactory factory) {
		if (factory == null) {
			
			var mensajeUsuario = "";
			var mensajeTecnico = "";
			
			throw new BusinessCMDBException(mensajeTecnico, mensajeUsuario);
		}
		this.factory = factory;
	}

	@Override
    public List<ProveedorDomain> execute(final ProveedorDomain data) {
	
        if (TextHelper.isNullOrEmpty(data.getNumeroDocumento())) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00189);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00190);
            throw new BusinessCMDBException(mensajeTecnico, mensajeUsuario);
        }

        var proveedorEntityFilter = ProveedorAssemblerEntity.getInstance().toEntity(data);
        
        var resultadoEntity = factory.getProveedorDAO().consultarPorNumeroDocumento(proveedorEntityFilter.getNumeroDocumento());

        if (resultadoEntity == null) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00191);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00192)+ proveedorEntityFilter.getNumeroDocumento();
            throw new BusinessCMDBException(mensajeTecnico, mensajeUsuario);
        }

        return ProveedorAssemblerEntity.getInstance().toDomainCollection(resultadoEntity);
    }

}
