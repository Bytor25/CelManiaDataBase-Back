package co.com.cmdb.business.facade.impl.proveedor;

import co.com.cmdb.business.assembler.dto.impl.ProveedorAssemblerDTO;
import co.com.cmdb.business.facade.FacadeWithReturn;
import co.com.cmdb.business.usecase.impl.proveedor.ConsultarPorNDTDProveedor;
import co.com.cmdb.crosscutting.exceptions.CMDBExceptions;
import co.com.cmdb.crosscutting.exceptions.custom.BusinessCMDBException;
import co.com.cmdb.data.dao.factory.DAOFactory;
import co.com.cmdb.dto.ProveedorDTO;

public class ConsultarPorNDTDProveedoresFacade implements FacadeWithReturn<ProveedorDTO, ProveedorDTO>{
	
	private DAOFactory daoFactory;
	
	public ConsultarPorNDTDProveedoresFacade(){
		
		daoFactory = DAOFactory.getFactory();
		
	}

	@Override
	public ProveedorDTO execute(ProveedorDTO dto) {
        daoFactory.iniciarTransaccion();
        try {
            var useCase = new ConsultarPorNDTDProveedor(daoFactory);
            var proveedorDomain = ProveedorAssemblerDTO.getInstance().toDomain(dto);
            var resultadoDomain = useCase.execute(proveedorDomain);
            return ProveedorAssemblerDTO.getInstance().toDTO(resultadoDomain);

        } catch (final CMDBExceptions excepcion) {
        	
            daoFactory.cancelarTransaccion();
            
            throw excepcion;

        } catch (final Exception excepcion) {
            daoFactory.cancelarTransaccion();
            var mensajeUsuario = "";
            var mensajeTecnico = "";
            throw new BusinessCMDBException(mensajeTecnico, mensajeUsuario, excepcion);

        } finally {
            daoFactory.cerrarConexion();
        }
	}

}
