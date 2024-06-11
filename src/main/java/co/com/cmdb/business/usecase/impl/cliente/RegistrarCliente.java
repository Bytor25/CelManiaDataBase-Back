package co.com.cmdb.business.usecase.impl.cliente;

import java.util.UUID;
import java.util.regex.Pattern;

import co.com.cmdb.business.assembler.entity.impl.TipoDocumentoAssemblerEntity;
import co.com.cmdb.business.domain.ClienteDomain;
import co.com.cmdb.business.usecase.UseCaseWithoutReturn;
import co.com.cmdb.crosscutting.exceptions.custom.BusinessCMDBException;
import co.com.cmdb.crosscutting.exceptions.mesagecatalog.MessageCatalogStrategy;
import co.com.cmdb.crosscutting.exceptions.mesagecatalog.data.CodigoMensaje;
import co.com.cmdb.crosscutting.helpers.ObjectHelper;
import co.com.cmdb.crosscutting.helpers.UUIDHelper;
import co.com.cmdb.data.dao.factory.DAOFactory;
import co.com.cmdb.entity.ClienteEntity;



public final class RegistrarCliente implements UseCaseWithoutReturn<ClienteDomain> {
	
	private final DAOFactory factory;
	
	private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
	private static final Pattern DOCUMENT_NUMBER_PATTERN = Pattern.compile("^[0-9]+$");
	
    private static final long MIN_PHONE_NUMBER = 3000000000L;
    private static final long MAX_PHONE_NUMBER = 3999999999L;
	
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
		 
		//1. 

			validarDatosCliente(data);
	
		//2. 
		

			validarClienteMismoNumeroDocumentoMismoNombre(data.getNombre(), data.getNumeroDocumento());

		//3.
			
			/*validarTipoDocumentoExista(data.getTipoDocumento().getIdentificador());*/
		
		var clienteEntity = ClienteEntity.build().setIdentificador(generarIdentificador()).setNumeroDocumento(data.getNumeroDocumento())
				.setTipoDocumento(TipoDocumentoAssemblerEntity.getInstance().toEntity(data.getTipoDocumento())).setNombre(data.getNombre())
				.setApellidos(data.getApellidos()).setCorreo(data.getCorreo()).setTelefono(data.getTelefono()).setEstado(data.isEstado());
		//4.

		
			factory.getClienteDAO().crear(clienteEntity);
	
		
	}
	
	
	private final void validarClienteMismoNumeroDocumentoMismoNombre(final String nombreCliente, final String numeroDocumento) {
		
		var clienteEntity = ClienteEntity.build().setNombre(nombreCliente).setNumeroDocumento(numeroDocumento);
		var resultados = factory.getClienteDAO().consultar(clienteEntity);
		
		if(!resultados.isEmpty()) {
			
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00012);
			throw new BusinessCMDBException(mensajeUsuario);
			
		}
		
	}
	
    private void validarDatosCliente(final ClienteDomain data) {
    	
        if (ObjectHelper.getObjectHelper().isNull(data.getNumeroDocumento()) || data.getNumeroDocumento().trim().isEmpty()) {
        	var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00045);
            throw new BusinessCMDBException(mensajeUsuario);
        }
        
        if (!DOCUMENT_NUMBER_PATTERN.matcher(data.getNumeroDocumento()).matches()) {
        	var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00046);
        	throw new BusinessCMDBException(mensajeUsuario);
        }
        
        if (ObjectHelper.getObjectHelper().isNull(data.getNombre()) || data.getNombre().trim().isEmpty()) {
        	var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00047);
        	throw new BusinessCMDBException(mensajeUsuario);
        }

		if (ObjectHelper.getObjectHelper().isNull(data.getApellidos()) || data.getApellidos().trim().isEmpty()) {
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00048);
			throw new BusinessCMDBException(mensajeUsuario);
		}
        
        if (ObjectHelper.getObjectHelper().isNull(data.getTipoDocumento()) || ObjectHelper.getObjectHelper().isNull(data.getTipoDocumento().getIdentificador())) {
        	var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00049);
        	throw new BusinessCMDBException(mensajeUsuario);
        }
        
        if (ObjectHelper.getObjectHelper().isNull(data.getCorreo()) || data.getCorreo().trim().isEmpty()) {
        	var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00050);
        	throw new BusinessCMDBException(mensajeUsuario);
        }
        if (!EMAIL_PATTERN.matcher(data.getCorreo()).matches()) {
        	var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00051);
        	throw new BusinessCMDBException(mensajeUsuario);
        }

        if (ObjectHelper.getObjectHelper().isNull(data.getTelefono())) {
        	var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00052);
        	throw new BusinessCMDBException(mensajeUsuario);
        }
        if (data.getTelefono() < MIN_PHONE_NUMBER || data.getTelefono() > MAX_PHONE_NUMBER) {
        	var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00053);
        	throw new BusinessCMDBException(mensajeUsuario);
        }
    }
    
    private final UUID generarIdentificador() {
    	UUID id = UUIDHelper.generate();
    	
    	boolean existedId = true;
    	while(existedId) {
    		id = UUIDHelper.generate();
    		
    		var clienteEntity = ClienteEntity.build().setIdentificador(id);
    		var resultados = factory.getClienteDAO().consultar(clienteEntity);
    		existedId =!resultados.isEmpty();
    	}
    	return id;
    }

}
