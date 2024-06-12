package co.com.cmdb.business.usecase.impl.proveedor;

import java.util.UUID;

import co.com.cmdb.business.assembler.entity.impl.TipoDocumentoAssemblerEntity;
import co.com.cmdb.business.domain.ProveedorDomain;
import co.com.cmdb.business.usecase.UseCaseWithoutReturn;
import co.com.cmdb.crosscutting.exceptions.custom.BusinessCMDBException;
import co.com.cmdb.crosscutting.exceptions.mesagecatalog.MessageCatalogStrategy;
import co.com.cmdb.crosscutting.exceptions.mesagecatalog.data.CodigoMensaje;
import co.com.cmdb.crosscutting.helpers.LongHelper;
import co.com.cmdb.crosscutting.helpers.ObjectHelper;
import co.com.cmdb.crosscutting.helpers.TextHelper;
import co.com.cmdb.crosscutting.helpers.UUIDHelper;
import co.com.cmdb.data.dao.factory.DAOFactory;
import co.com.cmdb.entity.ProveedorEntity;
import co.com.cmdb.entity.TipoDocumentoEntity;

public final class RegistrarProveedor implements UseCaseWithoutReturn<ProveedorDomain> {

	private final DAOFactory factory;
	
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
		
		validarExisteTipoDocumento(data.getTipoDocumento().getIdentificador());
			
		validarProveedorMismoNumeroDocumento(data.getNumeroDocumento());
		
	var proveedorEntity = ProveedorEntity.build().setIdentificador(generarIdentificador()).setNumeroDocumento(data.getNumeroDocumento())
				.setTipoDocumento(TipoDocumentoAssemblerEntity.getInstance().toEntity(data.getTipoDocumento())).setNombre(data.getNombre())
				.setTelefono(data.getTelefono()).setEstado(data.isEstado());
		
		
			factory.getProveedorDAO().crear(proveedorEntity);

		
	}
	
	private final void validarProveedorMismoNumeroDocumento(final String numeroDocumento) {
		
		
		var proveedorEntity = ProveedorEntity.build().setNumeroDocumento(numeroDocumento);
		var resultados = factory.getProveedorDAO().consultar(proveedorEntity);
		
		System.out.println(resultados);
		
		if(!resultados.isEmpty()) {
			
			var mensajeUsuario = "puta madre";
			var mensajeTecnico = "loco";
			
			throw new BusinessCMDBException(mensajeUsuario, mensajeTecnico);
			
		}
		
	}
	
	private final void validarExisteTipoDocumento(final int TipoDocumento) {
		
		var tipoDocumentoEntity = TipoDocumentoEntity.build().setIdentificador(TipoDocumento);
		var tipoDocumentoResultado = factory.getTipoDocumentoDAO().consultar(tipoDocumentoEntity);
		
		if(tipoDocumentoResultado.isEmpty()) {
			
			var mensajeUsuario = "El tipo de documento que se seleccionó no existe";
			var mensajeTecnico = "El tipo de documento que se seleccionó no existe en la base de datos";
			
			throw new BusinessCMDBException(mensajeUsuario, mensajeTecnico);
			
			
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
