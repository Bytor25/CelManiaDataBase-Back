package co.com.cmdb.business.usecase.impl.cliente;

import co.com.cmdb.business.assembler.entity.impl.TipoDocumentoAssemblerEntity;
import co.com.cmdb.business.domain.ClienteDomain;
import co.com.cmdb.business.usecase.UseCaseWithoutReturn;
import co.com.cmdb.crosscutting.exceptions.custom.BusinessCMDBException;
import co.com.cmdb.crosscutting.exceptions.mesagecatalog.MessageCatalogStrategy;
import co.com.cmdb.crosscutting.exceptions.mesagecatalog.data.CodigoMensaje;
import co.com.cmdb.crosscutting.helpers.LongHelper;
import co.com.cmdb.crosscutting.helpers.ObjectHelper;
import co.com.cmdb.crosscutting.helpers.TextHelper;
import co.com.cmdb.data.dao.factory.DAOFactory;
import co.com.cmdb.entity.ClienteEntity;
import co.com.cmdb.entity.TipoDocumentoEntity;

public class ActualizarCliente implements UseCaseWithoutReturn<ClienteDomain>{
	
	private DAOFactory factory;

	public ActualizarCliente(final DAOFactory factory) {
		if (ObjectHelper.getObjectHelper().isNull(factory)) {
			
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00105);
			var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00106);
			
			throw new BusinessCMDBException(mensajeTecnico, mensajeUsuario);
		}
		this.factory= factory;
	}

	@Override
	public void execute(ClienteDomain data) {
		
		validarExisteTipoDocumento(data.getTipoDocumento().getIdentificador());
		
		validarNombre(data.getNombre());
		
		validarApellido(data.getApellidos());
		
		validarCorreo(data.getCorreo());
		
		validarTelefono(data.getTelefono());
		
		validarClienteMismoCorreo(data.getNumeroDocumento(),data.getCorreo());
		
		validarClienteMismoNumeroTelefono(data.getNumeroDocumento(),data.getTelefono());
		
		
		var clienteEntity = ClienteEntity.build().setNumeroDocumento(data.getNumeroDocumento())
				.setTipoDocumento(TipoDocumentoAssemblerEntity.getInstance().toEntity(data.getTipoDocumento())).setNombre(data.getNombre())
				.setApellidos(data.getApellidos()).setCorreo(data.getCorreo()).setTelefono(data.getTelefono()).setEstado(data.isEstado());
		
		factory.getClienteDAO().mofidicar(clienteEntity);
		
	}
	
	
	private final void validarClienteMismoNumeroTelefono(final String numeroDocumento, final long valor) {

		if(factory.getClienteDAO().existeTelefono(valor, numeroDocumento)) {
			
        	var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00108);
        	var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00134);
        	throw new BusinessCMDBException(mensajeUsuario, mensajeTecnico);
		}
	}
	
	private final void validarClienteMismoCorreo(final String numeroDocumento, final String valor) {
		
		if(factory.getClienteDAO().existeCorreo(valor, numeroDocumento)) {
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00110); 
        	var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00135);
        	throw new BusinessCMDBException(mensajeUsuario, mensajeTecnico);
		}
		
	}
		    
	    private void validarNombre(final String valor) {
	    	
	        if (ObjectHelper.getObjectHelper().isNull(valor) || valor.trim().isEmpty()) {
	        	var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00047);
	        	var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00136);
	        	throw new BusinessCMDBException(mensajeUsuario, mensajeTecnico);
	        }
	        
	        if (!validarLongitud(valor, 1, 60)) {
	        	var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00117);
	        	var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00137);
	        	throw new BusinessCMDBException(mensajeUsuario, mensajeTecnico);
	        }
	        
	    }
	    
	    private void validarApellido(final String valor) {
	    	
	        if (ObjectHelper.getObjectHelper().isNull(valor) || valor.trim().isEmpty()) {
	        	var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00048);
	        	var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00138);
	        	throw new BusinessCMDBException(mensajeUsuario, mensajeTecnico);
	        }
	        
	        if (!validarLongitud(valor, 1, 100)) {
	        	var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00120);
	        	var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00139);
	        	throw new BusinessCMDBException(mensajeUsuario, mensajeTecnico);
	        }
	        
	    	
	    }
	    
	    private void validarCorreo(final String valor) {
	        if (ObjectHelper.getObjectHelper().isNull(valor) || valor.trim().isEmpty()) {
	        	var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00050);
	        	var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00140);
	        	throw new BusinessCMDBException(mensajeUsuario, mensajeTecnico);
	        }
	        if (!TextHelper.validarFormatoCorreo(valor)) {
	        	var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00051);
	        	var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00141);
	        	throw new BusinessCMDBException(mensajeUsuario, mensajeTecnico);
	        }
	        if (!validarLongitud(valor,6,255)) {
	        	var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00124);
	        	var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00142);
	        	throw new BusinessCMDBException(mensajeUsuario, mensajeTecnico);
	        }
	    }
	     
	    private void validarTelefono(final long valor) {
	    	
	    	String numeroTelefono = String.valueOf(valor);
	    	
	        if (ObjectHelper.getObjectHelper().isNull(numeroTelefono) || numeroTelefono.trim().isEmpty()) {
	        	var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00052);
	        	var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00143);
	        	throw new BusinessCMDBException(mensajeUsuario, mensajeTecnico);
	        }
	        if (!validarLongitud(numeroTelefono,1,10)) {
	        	var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00053);
	        	var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00144);
	        	throw new BusinessCMDBException(mensajeUsuario, mensajeTecnico);
	        }
	        if(!TextHelper.validarSoloNumeros(numeroTelefono)) {
	        	var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00128); 
	        	var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00145);
	        	throw new BusinessCMDBException(mensajeUsuario, mensajeTecnico);
	        }
	        if(!LongHelper.validarRango(valor)) {
	        	var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00130);
	        	var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00146);
	        	throw new BusinessCMDBException(mensajeUsuario, mensajeTecnico);
	        }
	    	
	    }
	    
	    
	    private boolean validarLongitud(final String valor, final int longitudMinima, final int LongitudMaxima) {
	    	
	    	return TextHelper.longitudMinima(valor, longitudMinima) && TextHelper.longitudMaxima(valor, LongitudMaxima);
	    	
	    }
	    
	    private void validarExisteTipoDocumento( final int TipoDocumento) {
	    	
	    	var tipoDocumentoEntity = TipoDocumentoEntity.build().setIdentificador(TipoDocumento);
	    	
	    	var tipoDocumentoResultado = factory.getTipoDocumentoDAO().consultar(tipoDocumentoEntity);
	    	
	    	if (tipoDocumentoResultado.isEmpty() || TipoDocumento == 0) {
	    		
	    		var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00132);
	    		var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00147);
	    		
	    		throw new BusinessCMDBException(mensajeUsuario, mensajeTecnico);
	    	}
	    	
	    }


}
