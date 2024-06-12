package co.com.cmdb.business.facade.impl.proveedor;

import java.util.List;

import co.com.cmdb.business.assembler.dto.impl.ProveedorAssemblerDTO;
import co.com.cmdb.business.facade.FacadeWithReturn;
import co.com.cmdb.business.usecase.impl.proveedor.ConsultarProveedor;
import co.com.cmdb.crosscutting.exceptions.CMDBExceptions;
import co.com.cmdb.crosscutting.exceptions.custom.BusinessCMDBException;
import co.com.cmdb.crosscutting.exceptions.mesagecatalog.MessageCatalogStrategy;
import co.com.cmdb.crosscutting.exceptions.mesagecatalog.data.CodigoMensaje;
import co.com.cmdb.data.dao.factory.DAOFactory;
import co.com.cmdb.dto.ProveedorDTO;

public class ConsultarProveedoresFacade implements FacadeWithReturn<ProveedorDTO, List<ProveedorDTO>>{
	
	private DAOFactory daoFactory;

	public ConsultarProveedoresFacade() {
		daoFactory = DAOFactory.getFactory();
	}

@Override
public List<ProveedorDTO> execute(final ProveedorDTO dto) {
	daoFactory.iniciarTransaccion();
	try {
		
		var useCase = new ConsultarProveedor(daoFactory);
		var proveedorDomain = ProveedorAssemblerDTO.getInstance().toDomain(dto);
		var resultadosDomain = useCase.execute(proveedorDomain);
		
		return ProveedorAssemblerDTO.getInstance().toDTOCollection(resultadosDomain);
		
	}catch(final CMDBExceptions excepcion) {
		
		daoFactory.cancelarTransaccion();
		throw excepcion;
		
	}catch(final Exception excepcion) {
		
		daoFactory.cancelarTransaccion();
		var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00032);
		var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00033);
		throw new BusinessCMDBException(mensajeTecnico, mensajeUsuario, excepcion);
	
	} finally {
		
		daoFactory.cerrarConexion();
		
	}

}


}
