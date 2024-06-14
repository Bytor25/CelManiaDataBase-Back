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
		
		validarNumeroDocumento(data.getNumeroDocumento());
			
		validarProveedorMismoNumeroDocumentoMismoTipoDocumento(data.getNumeroDocumento(),data.getTipoDocumento().getIdentificador(), data.getIdentificador());
		
		validarNombre(data.getNombre());
		
		validarTelefono(data.getTelefono());
		
		validarProveedorMismoNumeroTelefono(data.getNumeroDocumento(), data.getTelefono());
		
		
	var proveedorEntity = ProveedorEntity.build().setIdentificador(generarIdentificador()).setNumeroDocumento(data.getNumeroDocumento())
				.setTipoDocumento(TipoDocumentoAssemblerEntity.getInstance().toEntity(data.getTipoDocumento())).setNombre(data.getNombre())
				.setTelefono(data.getTelefono()).setEstado(data.isEstado());
		
		
			factory.getProveedorDAO().crear(proveedorEntity);

		
	}
	
	private final void validarProveedorMismoNumeroDocumentoMismoTipoDocumento(final String numeroDocumento, int identificadorDocumento, UUID identificador) {
		
		ProveedorEntity proveedorExiste = factory.getProveedorDAO().consultarPorNumeroDocumentoTipoDocumento(numeroDocumento, identificadorDocumento,identificador);
		
		if(proveedorExiste != null) {
			
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00148);
			var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00149);
			
			throw new BusinessCMDBException(mensajeUsuario, mensajeTecnico);
			
		}
		
	}
	
	private void validarNumeroDocumento( final String valor) {
    	
        if (ObjectHelper.getObjectHelper().isNull(valor) || valor.trim().isEmpty()) {
        	var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00150);
        	var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00151);
            throw new BusinessCMDBException(mensajeUsuario, mensajeTecnico);
        }
        
        if (!TextHelper.validarSoloNumeros(valor)) {
        	var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00152);
        	var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00153);
        	throw new BusinessCMDBException(mensajeUsuario, mensajeTecnico);
        }
        
        if (!validarLongitud(valor,1,15)) {
        	var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00154);
        	var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00155);
        	throw new BusinessCMDBException(mensajeUsuario, mensajeTecnico);
        }
    	
    }
	
	private final void validarProveedorMismoNumeroTelefono(final String numeroDocumento, final long valor) {
		
		if(factory.getProveedorDAO().existeTelefono(valor, numeroDocumento)) {
			
        	var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00156);
        	var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00157);
        	throw new BusinessCMDBException(mensajeUsuario, mensajeTecnico);
		}
	}
	
	private void validarNombre(final String valor) {
    	
        if (ObjectHelper.getObjectHelper().isNull(valor) || valor.trim().isEmpty()) {
        	var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00158);
        	var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00159);
        	throw new BusinessCMDBException(mensajeUsuario, mensajeTecnico);
        }
        
        if (!validarLongitud(valor, 1, 60)) {
        	var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00160);
        	var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00161);
        	throw new BusinessCMDBException(mensajeUsuario, mensajeTecnico);
        }
        
    }
	
	private void validarTelefono(final long valor) {
    	
    	String numeroTelefono = String.valueOf(valor);
    	
        if (valor == 0) {
        	var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00162);
        	var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00163);
        	throw new BusinessCMDBException(mensajeUsuario,mensajeTecnico);
        }
        if (!validarLongitud(numeroTelefono,1,10)) {
        	var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00164);
        	var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00165);
        	throw new BusinessCMDBException(mensajeUsuario, mensajeTecnico);
        }
        if(!TextHelper.validarSoloNumeros(numeroTelefono)) {
        	var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00166);
        	var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00167);
        	throw new BusinessCMDBException(mensajeUsuario, mensajeTecnico);
        }
        if(!LongHelper.validarRango(valor)) {
        	var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00168);
        	var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00169);
        	throw new BusinessCMDBException(mensajeUsuario, mensajeTecnico);
        }
    	
    }
	
	 private boolean validarLongitud(final String valor, final int longitudMinima, final int LongitudMaxima) {
	    	
	    	return TextHelper.longitudMinima(valor, longitudMinima) && TextHelper.longitudMaxima(valor, LongitudMaxima);
	    	
	    }
	
	private final void validarExisteTipoDocumento(final int TipoDocumento) {
		
		var tipoDocumentoEntity = TipoDocumentoEntity.build().setIdentificador(TipoDocumento);
		var tipoDocumentoResultado = factory.getTipoDocumentoDAO().consultar(tipoDocumentoEntity);
		
		if(tipoDocumentoResultado.isEmpty() || TipoDocumento == 0) {
			
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00132);
			var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00170);
			
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
