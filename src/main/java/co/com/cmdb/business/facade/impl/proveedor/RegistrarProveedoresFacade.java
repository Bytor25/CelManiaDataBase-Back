package co.com.cmdb.business.facade.impl.proveedor;

import co.com.cmdb.business.assembler.dto.impl.ProveedorAssemblerDTO;
import co.com.cmdb.business.facade.FacadeWithoutReturn;
import co.com.cmdb.business.usecase.impl.proveedor.RegistrarProveedor;
import co.com.cmdb.crosscutting.exceptions.CMDBExceptions;
import co.com.cmdb.crosscutting.exceptions.custom.BusinessCMDBException;
import co.com.cmdb.crosscutting.exceptions.mesagecatalog.MessageCatalogStrategy;
import co.com.cmdb.crosscutting.exceptions.mesagecatalog.data.CodigoMensaje;
import co.com.cmdb.data.dao.factory.DAOFactory;
import co.com.cmdb.dto.ProveedorDTO;

public class RegistrarProveedoresFacade implements FacadeWithoutReturn<ProveedorDTO> {

	public DAOFactory daoFactory;
	
	public RegistrarProveedoresFacade() {
		
		daoFactory = DAOFactory.getFactory();
		
	}
	
	@Override
	public void execute(final ProveedorDTO dato) {
		daoFactory.iniciarTransaccion();
		
		try {
			
			var useCase = new RegistrarProveedor(daoFactory);
			var proveedorDomain = ProveedorAssemblerDTO.getInstance().toDomain(dato);
			System.out.println(proveedorDomain);
			useCase.execute(proveedorDomain);
			
			daoFactory.confirmarTransaccion();
		
		}catch(final CMDBExceptions excepcion) {
			
			daoFactory.cancelarTransaccion();
			
		}catch(final Exception excepcion) {
			
			daoFactory.cancelarTransaccion();
			
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00054);
			var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00055);
			
			throw new BusinessCMDBException(mensajeUsuario, mensajeTecnico, excepcion);
			
		} finally {
			
			daoFactory.cerrarConexion();
			
		}
		
	}

}
