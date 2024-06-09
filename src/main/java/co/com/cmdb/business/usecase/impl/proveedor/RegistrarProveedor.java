package co.com.cmdb.business.usecase.impl.proveedor;

import java.util.UUID;

import co.com.cmdb.business.assembler.entity.impl.TipoDocumentoAssemblerEntity;
import co.com.cmdb.business.domain.ProveedorDomain;
import co.com.cmdb.business.usecase.UseCaseWithoutReturn;
import co.com.cmdb.crosscutting.exceptions.custom.BusinessCMDBException;
import co.com.cmdb.crosscutting.exceptions.mesagecatalog.MessageCatalogStrategy;
import co.com.cmdb.crosscutting.exceptions.mesagecatalog.data.CodigoMensaje;
import co.com.cmdb.crosscutting.helpers.ObjectHelper;
import co.com.cmdb.crosscutting.helpers.UUIDHelper;
import co.com.cmdb.data.dao.factory.DAOFactory;
import co.com.cmdb.entity.ProveedorEntity;

public class RegistrarProveedor implements UseCaseWithoutReturn<ProveedorDomain> {

	private final DAOFactory factory;
	
	private static final long MIN_PHONE_NUMBER = 3000000000L;
    private static final long MAX_PHONE_NUMBER = 3999999999L;
	
    public RegistrarProveedor(final DAOFactory factory) {
    	
    	if(ObjectHelper.getObjectHelper().isNull(factory)) {
    		
    		var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00040);
    		var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00041);
    		
    		throw new BusinessCMDBException(mensajeTecnico, mensajeUsuario);
    		
    	}
    	
    	this.factory = factory;
    	
    }
	
	@Override
	public void execute(final ProveedorDomain data) {
		
		//1.
		try {
			validarDatosProveedor(data);
		} catch(Exception e) {
			
			throw e;
		}
		
		//2.
		
		try {
			validarProveedorMismoNumeroDocumentoMismoNombre(data.getNombre(), data.getNumeroDocumento());
		}catch(Exception e) {
			
			
		}
		
		//3. 
		
		var proveedorEntity = ProveedorEntity.build().setIdentificador(generarIdentificador()).setNumeroDocumento(data.getNumeroDocumento())
				.setTipoDocumento(TipoDocumentoAssemblerEntity.getInstance().toEntity(data.getTipoDocumento())).setNombre(data.getNombre())
				.setTelefono(data.getTelefono()).setEstado(data.isEstado());
		
		//4.
		
		try {
			factory.getProveedorDAO().crear(proveedorEntity);
		}catch(Exception e) {
			throw e;
		}
		
	}
	
	private final void validarProveedorMismoNumeroDocumentoMismoNombre(final String nombreProveedor, final String numeroDocumento) {
		
		var proveedorEntity = ProveedorEntity.build().setNombre(nombreProveedor).setNumeroDocumento(numeroDocumento);
		var resultados = factory.getProveedorDAO().consultar(proveedorEntity);
		
		if(!resultados.isEmpty()) {
			
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00034); 
			throw new BusinessCMDBException(mensajeUsuario);
			
		}
		
	}
	
    private void validarDatosProveedor(final ProveedorDomain data) {
    	
    	if(ObjectHelper.getObjectHelper().isNull(data.getNumeroDocumento()) || data.getNumeroDocumento().trim().isEmpty()) {
    		var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00035);
    		throw new BusinessCMDBException(mensajeUsuario);
    	}
        if (ObjectHelper.getObjectHelper().isNull(data.getNombre()) || data.getNombre().trim().isEmpty()) {
        	var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00036);
            throw new BusinessCMDBException(mensajeUsuario);
        }
       /* if (!NOMBRE_PATTERN.matcher(data.getNombre()).matches()) {
            throw new BusinessCMDBException("El nombre del cliente contiene caracteres inválidos.", "El nombre del cliente solo puede contener letras y espacios.");
        }
        */
		
       /* if (!NOMBRE_PATTERN.matcher(data.getApellidos()).matches()) {
            throw new BusinessCMDBException("El/Los  apellidos del cliente contienen caracteres inválidos.", "El/Los apellidos del cliente solo pueden contener letras y espacios.");
        }*/
        
        if (ObjectHelper.getObjectHelper().isNull(data.getTipoDocumento()) || ObjectHelper.getObjectHelper().isNull(data.getTipoDocumento().getIdentificador())) {
        	var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00037);
            throw new BusinessCMDBException(mensajeUsuario);
        }

        if (ObjectHelper.getObjectHelper().isNull(data.getTelefono())) {
        	var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00038);
            throw new BusinessCMDBException(mensajeUsuario);
        }
        if (data.getTelefono() < MIN_PHONE_NUMBER || data.getTelefono() > MAX_PHONE_NUMBER) {
        	var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00039);
            throw new BusinessCMDBException(mensajeUsuario);
        }
    }
    
    private final UUID generarIdentificador() {
    	UUID id = UUIDHelper.generate();
    	
    	boolean existedId = true;
    	while(existedId) {
    		id = UUIDHelper.generate();
    		
    		var proveedorEntity = ProveedorEntity.build().setIdentificador(id);
    		var resultados = factory.getProveedorDAO().consultar(proveedorEntity);
    		existedId =!resultados.isEmpty();
    	}
    	return id;
    }
	
	

}
