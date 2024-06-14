package co.com.cmdb.business.usecase.impl.proveedor;

import co.com.cmdb.business.assembler.entity.impl.TipoDocumentoAssemblerEntity;
import co.com.cmdb.business.domain.ProveedorDomain;
import co.com.cmdb.business.usecase.UseCaseWithoutReturn;
import co.com.cmdb.crosscutting.exceptions.custom.BusinessCMDBException;
import co.com.cmdb.crosscutting.exceptions.mesagecatalog.MessageCatalogStrategy;
import co.com.cmdb.crosscutting.exceptions.mesagecatalog.data.CodigoMensaje;
import co.com.cmdb.crosscutting.helpers.LongHelper;
import co.com.cmdb.crosscutting.helpers.ObjectHelper;
import co.com.cmdb.crosscutting.helpers.TextHelper;
import co.com.cmdb.data.dao.factory.DAOFactory;
import co.com.cmdb.entity.ProveedorEntity;
import co.com.cmdb.entity.TipoDocumentoEntity;

public final class ActualizarProveedor implements UseCaseWithoutReturn<ProveedorDomain> {

	private final DAOFactory factory;
	
    public ActualizarProveedor(final DAOFactory factory) {
    	
    	if(ObjectHelper.getObjectHelper().isNull(factory)) {
    		
    		var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00177);
    		var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00178);
    		
    		throw new BusinessCMDBException(mensajeTecnico, mensajeUsuario);
    		
    	}
    	
    	this.factory = factory;
    	
    }
	
	@Override
	public void execute(final ProveedorDomain data) {
		
		validarExisteTipoDocumento(data.getTipoDocumento().getIdentificador());
		
			
		validarProveedorMismoNumeroDocumentoMismoTipoDocumento(data.getNumeroDocumento(),data.getTipoDocumento().getIdentificador());
		
		validarNombre(data.getNombre());
		
		validarTelefono(data.getTelefono());
		
		validarProveedorMismoNumeroTelefono(data.getNumeroDocumento(), data.getTelefono());
		
		
	var proveedorEntity = ProveedorEntity.build().setNumeroDocumento(data.getNumeroDocumento())
				.setTipoDocumento(TipoDocumentoAssemblerEntity.getInstance().toEntity(data.getTipoDocumento())).setNombre(data.getNombre())
				.setTelefono(data.getTelefono());
		
		
			factory.getProveedorDAO().mofidicar(proveedorEntity);

		
	}
	
	private final void validarProveedorMismoNumeroDocumentoMismoTipoDocumento(final String numeroDocumento, int identificadorDocumento) {
		
		ProveedorEntity proveedorExiste = factory.getProveedorDAO().consultarPorNumeroDocumentoTipoDocumento(numeroDocumento, identificadorDocumento);
		
		if(proveedorExiste != null) {
			
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00179);
			var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00180);
			
			throw new BusinessCMDBException(mensajeUsuario, mensajeTecnico);
			
		}
		
	}
	
	
	private final void validarProveedorMismoNumeroTelefono(final String numeroDocumento, final long valor) {
		
		if(factory.getProveedorDAO().existeTelefono(valor, numeroDocumento)) {
			
        	var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00156);
        	var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00181);
        	throw new BusinessCMDBException(mensajeUsuario, mensajeTecnico);
		}
	}
	
	private void validarNombre(final String valor) {
    	
        if (ObjectHelper.getObjectHelper().isNull(valor) || valor.trim().isEmpty()) {
        	var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00158);
        	var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00182);
        	throw new BusinessCMDBException(mensajeUsuario, mensajeTecnico);
        }
        
        if (!validarLongitud(valor, 1, 60)) {
        	var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00160);
        	var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00183);
        	throw new BusinessCMDBException(mensajeUsuario, mensajeTecnico);
        }
        
    }
	
	private void validarTelefono(final long valor) {
    	
    	String numeroTelefono = String.valueOf(valor);
    	
        if (valor == 0) {
        	var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00162);
        	var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00184);
        	throw new BusinessCMDBException(mensajeUsuario,mensajeTecnico);
        }
        if (!validarLongitud(numeroTelefono,1,10)) {
        	var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00164);
        	var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00185);
        	throw new BusinessCMDBException(mensajeUsuario, mensajeTecnico);
        }
        if(!TextHelper.validarSoloNumeros(numeroTelefono)) {
        	var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00166);
        	var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00186);
        	throw new BusinessCMDBException(mensajeUsuario, mensajeTecnico);
        }
        if(!LongHelper.validarRango(valor)) {
        	var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00168);
        	var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00187);
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
			var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00188);
			
			throw new BusinessCMDBException(mensajeUsuario, mensajeTecnico);
			
			
		}
		
		
	}
	

}
