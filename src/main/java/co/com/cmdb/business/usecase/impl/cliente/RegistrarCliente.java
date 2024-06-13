package co.com.cmdb.business.usecase.impl.cliente;

import java.util.UUID;

import co.com.cmdb.business.assembler.entity.impl.TipoDocumentoAssemblerEntity;
import co.com.cmdb.business.domain.ClienteDomain;
import co.com.cmdb.business.usecase.UseCaseWithoutReturn;
import co.com.cmdb.crosscutting.exceptions.custom.BusinessCMDBException;
import co.com.cmdb.crosscutting.exceptions.mesagecatalog.MessageCatalogStrategy;
import co.com.cmdb.crosscutting.exceptions.mesagecatalog.data.CodigoMensaje;
import co.com.cmdb.crosscutting.helpers.LongHelper;
import co.com.cmdb.crosscutting.helpers.ObjectHelper;
import co.com.cmdb.crosscutting.helpers.TextHelper;
import co.com.cmdb.crosscutting.helpers.UUIDHelper;
import co.com.cmdb.data.dao.factory.DAOFactory;
import co.com.cmdb.entity.ClienteEntity;
import co.com.cmdb.entity.TipoDocumentoEntity;



public final class RegistrarCliente implements UseCaseWithoutReturn<ClienteDomain> {
	
	private final DAOFactory factory;
	
	public RegistrarCliente(final DAOFactory factory) {
		
		if(ObjectHelper.getObjectHelper().isNull(factory)) {
			
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00010);
			var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00011);
			
			throw new BusinessCMDBException(mensajeTecnico, mensajeUsuario);
		}
		
		this.factory = factory;
	}

	@Override
	public void execute(final ClienteDomain data) {
			
			validarExisteTipoDocumento(data.getTipoDocumento().getIdentificador());
			
			validarNumeroDocumento(data.getNumeroDocumento());
			
			validarClienteMismoNumeroDocumentoMismoTipoDocumento(data.getNumeroDocumento(), data.getTipoDocumento().getIdentificador());
			
			validarNombre(data.getNombre());
			
			validarApellido(data.getApellidos());
			
			validarCorreo(data.getCorreo());
			
			validarTelefono(data.getTelefono());
			 
			validarClienteMismoCorreo(data.getNumeroDocumento(),data.getCorreo());
			
			validarClienteMismoNumeroTelefono(data.getNumeroDocumento(),data.getTelefono());

		//3.
		
		var clienteEntity = ClienteEntity.build().setIdentificador(generarIdentificador()).setNumeroDocumento(data.getNumeroDocumento())
				.setTipoDocumento(TipoDocumentoAssemblerEntity.getInstance().toEntity(data.getTipoDocumento())).setNombre(data.getNombre())
				.setApellidos(data.getApellidos()).setCorreo(data.getCorreo()).setTelefono(data.getTelefono()).setEstado(data.isEstado());
		//4.

			factory.getClienteDAO().crear(clienteEntity);
	
		
	}
	
	
	private final void validarClienteMismoNumeroDocumentoMismoTipoDocumento(final String valor, final int identificadorDocumento) {
		
		ClienteEntity clienteExiste = factory.getClienteDAO().consultarPoridTipoDocumento(valor, identificadorDocumento);
		
		if(clienteExiste != null) {
			
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00012);
			var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00107);
			throw new BusinessCMDBException(mensajeUsuario, mensajeTecnico);
		}
	}
	
	private final void validarClienteMismoNumeroTelefono(final String numeroDocumento, final long valor) {
		
		if(factory.getClienteDAO().existeTelefono(valor, numeroDocumento)) {
			
        	var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00108);
        	var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00109);
        	throw new BusinessCMDBException(mensajeUsuario, mensajeTecnico);
		}
	}
	
	private final void validarClienteMismoCorreo(final String numeroDocumento, final String valor) {
		
		if(factory.getClienteDAO().existeCorreo(valor, numeroDocumento)) {
			
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00110); 
			var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00111);
        	throw new BusinessCMDBException(mensajeUsuario, mensajeTecnico);
		}
		
	}
	
	
    
    private void validarNumeroDocumento( final String valor) {
    	
        if (ObjectHelper.getObjectHelper().isNull(valor) || valor.trim().isEmpty()) {
        	var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00045);
        	var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00112);
            throw new BusinessCMDBException(mensajeUsuario, mensajeTecnico);
        }
        
        if (!TextHelper.validarSoloNumeros(valor)) {
        	var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00046);
        	var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00113);
        	throw new BusinessCMDBException(mensajeUsuario, mensajeTecnico);
        }
        
        if (!validarLongitud(valor,1,15)) {
        	var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00114);
        	var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00115);
        	throw new BusinessCMDBException(mensajeUsuario, mensajeTecnico);
        }
    	
    }
    
    private void validarNombre(final String valor) {
    	
        if (ObjectHelper.getObjectHelper().isNull(valor) || valor.trim().isEmpty()) {
        	var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00047);
        	var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00116);
        	throw new BusinessCMDBException(mensajeUsuario, mensajeTecnico);
        }
        
        if (!validarLongitud(valor, 1, 60)) {
        	var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00117);
        	var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00118);
        	throw new BusinessCMDBException(mensajeUsuario, mensajeTecnico);
        }
        
    }
    
    private void validarApellido(final String valor) {
    	
        if (ObjectHelper.getObjectHelper().isNull(valor) || valor.trim().isEmpty()) {
        	var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00048);
        	var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00119);
        	throw new BusinessCMDBException(mensajeUsuario, mensajeTecnico);
        }
        
        if (!validarLongitud(valor, 1, 100)) {
        	var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00120);
        	var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00121);
        	throw new BusinessCMDBException(mensajeUsuario, mensajeTecnico);
        }
        
    	
    }
    
    private void validarCorreo(final String valor) {
        if (ObjectHelper.getObjectHelper().isNull(valor) || valor.trim().isEmpty()) {
        	var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00050);
        	var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00122);
        	throw new BusinessCMDBException(mensajeUsuario, mensajeTecnico);
        }
        if (!TextHelper.validarFormatoCorreo(valor)) {
        	var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00051);
        	var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00123);
        	throw new BusinessCMDBException(mensajeUsuario, mensajeTecnico);
        }
        if (!validarLongitud(valor,6,255)) {
        	var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00124);
        	var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00125);
        	throw new BusinessCMDBException(mensajeUsuario, mensajeTecnico);
        }
    }
     
    private void validarTelefono(final long valor) {
    	
    	String numeroTelefono = String.valueOf(valor);
    	
        if (valor == 0) {
        	var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00052);
        	var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00126);
        	throw new BusinessCMDBException(mensajeUsuario,mensajeTecnico);
        }
        if (!validarLongitud(numeroTelefono,1,10)) {
        	var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00053);
        	var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00127);
        	throw new BusinessCMDBException(mensajeUsuario, mensajeTecnico);
        }
        if(!TextHelper.validarSoloNumeros(numeroTelefono)) {
        	var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00128);
        	var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00129);
        	throw new BusinessCMDBException(mensajeUsuario, mensajeTecnico);
        }
        if(!LongHelper.validarRango(valor)) {
        	var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00130);
        	var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00131);
        	throw new BusinessCMDBException(mensajeUsuario, mensajeTecnico);
        }
    	
    }
    
    private final UUID generarIdentificador() {
    	UUID id = UUIDHelper.generate();
    	
    	boolean existedId = true;
    	while(existedId) {
    		id = UUIDHelper.generate();
    		
    		var clienteEntity = ClienteEntity.build().setIdentificador(id);
    		var resultados = factory.getClienteDAO().consultar(clienteEntity);
    		existedId = !resultados.isEmpty();
    	}
    	return id;
    }
    
    private void validarExisteTipoDocumento( final int TipoDocumento) {
    	
    	var tipoDocumentoEntity = TipoDocumentoEntity.build().setIdentificador(TipoDocumento);
    	
    	var tipoDocumentoResultado = factory.getTipoDocumentoDAO().consultar(tipoDocumentoEntity);
    	
    	if (tipoDocumentoResultado.isEmpty() || TipoDocumento == 0) {
    		
    		var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00132);
    		var mensajeTecnico = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00133);
    		
    		throw new BusinessCMDBException(mensajeUsuario, mensajeTecnico);
    	}
    	
    }
    
    private boolean validarLongitud(final String valor, final int longitudMinima, final int LongitudMaxima) {
    	
    	return TextHelper.longitudMinima(valor, longitudMinima) && TextHelper.longitudMaxima(valor, LongitudMaxima);
    	
    }

}
