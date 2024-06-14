package co.com.cmdb.business.usecase.impl.proveedor;

import co.com.cmdb.business.assembler.entity.impl.ClienteAssemblerEntity;
import co.com.cmdb.business.assembler.entity.impl.ProveedorAssemblerEntity;
import co.com.cmdb.business.domain.ProveedorDomain;
import co.com.cmdb.business.usecase.UseCaseWithReturn;
import co.com.cmdb.crosscutting.exceptions.custom.BusinessCMDBException;
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
            var mensajeUsuario = "El número de documento no puede ser nulo.";
            var mensajeTecnico = "El número de documento es nulo o vacío en el método execute de ConsultarPorIdCliente.";
            throw new BusinessCMDBException(mensajeTecnico, mensajeUsuario);
        }
        
        if(data.getTipoDocumento().getIdentificador() == 0) {
            var mensajeUsuario = "El tipo de documento no puede ser nulo.";
            var mensajeTecnico = "El tipo de documento es nulo o vacío en el método execute de ConsultarPorIdTipoDocumentoCliente.";
            throw new BusinessCMDBException(mensajeTecnico, mensajeUsuario);
        }

        var ProveedorEntityFilter = ProveedorAssemblerEntity.getInstance().toEntity(data);
        
        var resultadoEntity = factory.getProveedorDAO().consultarPorNumeroDocumentoTipoDocumento(data.getNumeroDocumento(), data.getTipoDocumento().getIdentificador());

        if (resultadoEntity == null) {
            var mensajeUsuario = "No se encontró el proveedor con el numero de documento proporcionado.";
            var mensajeTecnico = "No se encontró ninguna entidad Proveedor con el numero de documento: " + ProveedorEntityFilter.getNumeroDocumento();
            throw new BusinessCMDBException(mensajeTecnico, mensajeUsuario);
        }

        return ProveedorAssemblerEntity.getInstance().toDomain(resultadoEntity);
	}
}

