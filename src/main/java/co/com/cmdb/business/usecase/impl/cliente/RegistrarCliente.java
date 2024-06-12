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
			
			validarClienteMismoNumeroDocumento(data.getNumeroDocumento());
			
			validarNumeroDocumento(data.getNumeroDocumento());
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
	
	
	private final void validarClienteMismoNumeroDocumento(final String valor) {
		
		var clienteEntity = ClienteEntity.build().setNumeroDocumento(valor);
		System.out.println(clienteEntity);
		var resultados = factory.getClienteDAO().consultar(clienteEntity);
		
		System.out.println(resultados);
		
		if(resultados.isEmpty()) {
			
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00012);
			var mensajeTecnico = "validarClienteMismoNumeroDocumento";
			throw new BusinessCMDBException(mensajeUsuario, mensajeTecnico);
		}
	}
	
	private final void validarClienteMismoNumeroTelefono(final String numeroDocumento, final long valor) {
		
		if(factory.getClienteDAO().existeTelefono(valor, numeroDocumento)) {
			
        	var mensajeUsuario = "validarClienteMismoNumeroTelefono holaaaaaaaaa";
        	var mensajeTecnico = "validarClienteMismoNumeroTelefono";
        	throw new BusinessCMDBException(mensajeUsuario, mensajeTecnico);
		}
	}
	
	private final void validarClienteMismoCorreo(final String numeroDocumento, final String valor) {
		
		if(factory.getClienteDAO().existeCorreo(valor, numeroDocumento)) {
			
        	var mensajeUsuario = "validarClienteMismoCorreo";
        	var mensajeTecnico = "validarClienteMismoCorreo";
        	throw new BusinessCMDBException(mensajeUsuario, mensajeTecnico);
		}
		
	}
	
	
    
    private void validarNumeroDocumento( final String valor) {
    	
        if (ObjectHelper.getObjectHelper().isNull(valor) || valor.trim().isEmpty()) {
        	var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00045);
        	var mensajeTecnico = "validarNumeroDocumento";
            throw new BusinessCMDBException(mensajeUsuario, mensajeTecnico);
        }
        
        if (!TextHelper.validarSoloNumeros(valor)) {
        	var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00046);
        	var mensajeTecnico = "validarNumeroDocumento";
        	throw new BusinessCMDBException(mensajeUsuario, mensajeTecnico);
        }
        
        if (!validarLongitud(valor,1,15)) {
        	var mensajeUsuario = "validarNumeroDocumento";
        	var mensajeTecnico = "validarNumeroDocumento";
        	throw new BusinessCMDBException(mensajeUsuario, mensajeTecnico);
        }
    	
    }
    
    private void validarNombre(final String valor) {
    	
        if (ObjectHelper.getObjectHelper().isNull(valor) || valor.trim().isEmpty()) {
        	var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00047);
        	var mensajeTecnico = "";
        	throw new BusinessCMDBException(mensajeUsuario, mensajeTecnico);
        }
        
        if (!validarLongitud(valor, 1, 60)) {
        	var mensajeUsuario = "validarNombre";
        	var mensajeTecnico = "validarNombre";
        	throw new BusinessCMDBException(mensajeUsuario, mensajeTecnico);
        }
        
    }
    
    private void validarApellido(final String valor) {
    	
        if (ObjectHelper.getObjectHelper().isNull(valor) || valor.trim().isEmpty()) {
        	var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00047);
        	var mensajeTecnico = "validarApellido";
        	throw new BusinessCMDBException(mensajeUsuario, mensajeTecnico);
        }
        
        if (!validarLongitud(valor, 1, 100)) {
        	var mensajeUsuario = "validarApellido";
        	var mensajeTecnico = "validarApellido";
        	throw new BusinessCMDBException(mensajeUsuario, mensajeTecnico);
        }
        
    	
    }
    
    private void validarCorreo(final String valor) {
        if (ObjectHelper.getObjectHelper().isNull(valor) || valor.trim().isEmpty()) {
        	var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00050);
        	var mensajeTecnico = "validarCorreo";
        	throw new BusinessCMDBException(mensajeUsuario, mensajeTecnico);
        }
        if (!TextHelper.validarFormatoCorreo(valor)) {
        	var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00051);
        	var mensajeTecnico = "validarCorreo";
        	throw new BusinessCMDBException(mensajeUsuario, mensajeTecnico);
        }
        if (!validarLongitud(valor,6,255)) {
        	var mensajeUsuario = "validarCorreo";
        	var mensajeTecnico = "validarCorreo";
        	throw new BusinessCMDBException(mensajeUsuario, mensajeTecnico);
        }
    }
     
    private void validarTelefono(final long valor) {
    	
    	String numeroTelefono = String.valueOf(valor);
    	
        if (ObjectHelper.getObjectHelper().isNull(numeroTelefono) || numeroTelefono.trim().isEmpty()) {
        	var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00052);
        	var mensajeTecnico = "validarTelefono";
        	throw new BusinessCMDBException(mensajeUsuario,mensajeTecnico);
        }
        if (!validarLongitud(numeroTelefono,1,10)) {
        	var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00053);
        	var mensajeTecnico = "validarTelefono";
        	throw new BusinessCMDBException(mensajeUsuario, mensajeTecnico);
        }
        if(!TextHelper.validarSoloNumeros(numeroTelefono)) {
        	var mensajeUsuario = "validarTelefono";
        	var mensajeTecnico = "validarTelefono";
        	throw new BusinessCMDBException(mensajeUsuario, mensajeTecnico);
        }
        if(!LongHelper.validarRango(valor)) {
        	var mensajeUsuario = "validarTelefono";
        	var mensajeTecnico = "validarTelefono";
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
    	
    	if (tipoDocumentoResultado.isEmpty()) {
    		var mensajeUsuario = "validarExisteTipoDocumento Usuario";
    		var mensajeTecnico = "validarExisteTipoDocumento";
    		
    		throw new BusinessCMDBException(mensajeUsuario, mensajeTecnico);
    	}
    	
    }
    
    private boolean validarLongitud(final String valor, final int longitudMinima, final int LongitudMaxima) {
    	
    	return TextHelper.longitudMinima(valor, longitudMinima) && TextHelper.longitudMaxima(valor, LongitudMaxima);
    	
    }

}
