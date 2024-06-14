package co.com.cmdb.business.facade.impl.proveedor;

import co.com.cmdb.business.assembler.dto.impl.ProveedorAssemblerDTO;
import co.com.cmdb.business.facade.FacadeWithoutReturn;
import co.com.cmdb.business.usecase.impl.proveedor.ActualizarProveedor;
import co.com.cmdb.crosscutting.exceptions.CMDBExceptions;
import co.com.cmdb.crosscutting.exceptions.custom.BusinessCMDBException;
import co.com.cmdb.crosscutting.exceptions.mesagecatalog.MessageCatalogStrategy;
import co.com.cmdb.crosscutting.exceptions.mesagecatalog.data.CodigoMensaje;
import co.com.cmdb.data.dao.factory.DAOFactory;
import co.com.cmdb.dto.ProveedorDTO;

public class ActualizarProveedorFacade implements FacadeWithoutReturn<ProveedorDTO> {
	
	public DAOFactory daoFactory;
	
	public ActualizarProveedorFacade() {
		daoFactory = DAOFactory.getFactory();
	}

	@Override
	public void execute(ProveedorDTO dato) {
		daoFactory.iniciarTransaccion();
		
		try {
            var useCase = new ActualizarProveedor(daoFactory);
			var proveedorDomain = ProveedorAssemblerDTO.getInstance().toDomain(dato);
			
			useCase.execute(proveedorDomain);
			
			daoFactory.confirmarTransaccion();
			
		}catch(final CMDBExceptions excepcion) {
			daoFactory.cancelarTransaccion();
			throw excepcion;
			
		}catch(final Exception excepcion) {
			daoFactory.cancelarTransaccion();
			
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00171);
			var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00172);
			
			throw new BusinessCMDBException(mensajeTecnico,mensajeUsuario,excepcion);
			
		} finally {
			daoFactory.cerrarConexion();
		}
	}
}
