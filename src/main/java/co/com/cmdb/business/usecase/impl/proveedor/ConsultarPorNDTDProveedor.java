package co.com.cmdb.business.usecase.impl.proveedor;

import co.com.cmdb.business.assembler.entity.impl.ProveedorAssemblerEntity;
import co.com.cmdb.business.domain.ProveedorDomain;
import co.com.cmdb.business.usecase.UseCaseWithReturn;
import co.com.cmdb.crosscutting.exceptions.custom.BusinessCMDBException;
import co.com.cmdb.crosscutting.exceptions.mesagecatalog.MessageCatalogStrategy;
import co.com.cmdb.crosscutting.exceptions.mesagecatalog.data.CodigoMensaje;
import co.com.cmdb.crosscutting.helpers.TextHelper;
import co.com.cmdb.data.dao.factory.DAOFactory;

public class ConsultarPorNDTDProveedor implements UseCaseWithReturn<ProveedorDomain, ProveedorDomain>{
	
	private DAOFactory factory;

	public ConsultarPorNDTDProveedor(final DAOFactory factory) {
		if (factory == null) {
			
			var mensajeUsuario = "";
			var mensajeTecnico = "";
			
			throw new BusinessCMDBException(mensajeTecnico, mensajeUsuario);
		}
		this.factory= factory;
	}

	@Override
	public ProveedorDomain execute(ProveedorDomain data) {
		
        if (TextHelper.isNullOrEmpty(data.getNumeroDocumento())) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00193);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00194);
            throw new BusinessCMDBException(mensajeTecnico, mensajeUsuario);
        }
        
        if(data.getTipoDocumento().getIdentificador() == 0) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00195);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00196) ;
            throw new BusinessCMDBException(mensajeTecnico, mensajeUsuario);
        }

        var ProveedorEntityFilter = ProveedorAssemblerEntity.getInstance().toEntity(data);
        
        var resultadoEntity = factory.getProveedorDAO().consultarPorNumeroDocumentoTipoDocumento(data.getNumeroDocumento(), data.getTipoDocumento().getIdentificador());

        if (resultadoEntity == null) {
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00198);
            var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00199)+ ProveedorEntityFilter.getNumeroDocumento();
            throw new BusinessCMDBException(mensajeTecnico, mensajeUsuario);
        }

        return ProveedorAssemblerEntity.getInstance().toDomain(resultadoEntity);
	}
}

