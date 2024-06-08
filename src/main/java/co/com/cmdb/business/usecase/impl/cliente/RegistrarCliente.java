package co.com.cmdb.business.usecase.impl.cliente;

import java.util.regex.Pattern;

import co.com.cmdb.business.assembler.entity.impl.TipoDocumentoAssemblerEntity;
import co.com.cmdb.business.domain.ClienteDomain;
import co.com.cmdb.business.usecase.UseCaseWithoutReturn;
import co.com.cmdb.crosscutting.exceptions.custom.BusinessCMDBException;
import co.com.cmdb.crosscutting.exceptions.mesagecatalog.MessageCatalogStrategy;
import co.com.cmdb.crosscutting.exceptions.mesagecatalog.data.CodigoMensaje;
import co.com.cmdb.crosscutting.helpers.ObjectHelper;

import co.com.cmdb.data.dao.factory.DAOFactory;
import co.com.cmdb.entity.ClienteEntity;



public final class RegistrarCliente implements UseCaseWithoutReturn<ClienteDomain> {
	
	private final DAOFactory factory;
	private static final Pattern NOMBRE_PATTERN = Pattern.compile("^[a-zA-Z\\\\s]+$");
	private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
	
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
		var clienteEntity = ClienteEntity.build().setIdentificador(data.getIdentificador()).setNumeroDocumento(data.getNumeroDocumento())
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
    	
    	if(ObjectHelper.getObjectHelper().isNull(data.getNumeroDocumento()) || data.getNumeroDocumento().trim().isEmpty()) {
    		throw new BusinessCMDBException("El numero de identificacion del clientes está vacío.","Debe propocriconar un numero de identificacion válido para el cliente.");
    	}
        if (ObjectHelper.getObjectHelper().isNull(data.getNombre()) || data.getNombre().trim().isEmpty()) {
            throw new BusinessCMDBException("El nombre del cliente está vacío.", "Debe proporcionar un nombre válido para el cliente.");
        }
        if (!NOMBRE_PATTERN.matcher(data.getNombre()).matches()) {
            throw new BusinessCMDBException("El nombre del cliente contiene caracteres inválidos.", "El nombre del cliente solo puede contener letras y espacios.");
        }
        
		if(ObjectHelper.getObjectHelper().isNull(data.getApellidos()) || data.getApellidos().trim().isEmpty()) {
			throw new BusinessCMDBException("El/Los apellidos del cliente están vacíos.","Debe de proporcionar apellido/s válidos para el cliente");
		}
		
        if (!NOMBRE_PATTERN.matcher(data.getApellidos()).matches()) {
            throw new BusinessCMDBException("El/Los  apellidos del cliente contienen caracteres inválidos.", "El/Los apellidos del cliente solo pueden contener letras y espacios.");
        }
        
        if (ObjectHelper.getObjectHelper().isNull(data.getTipoDocumento()) || ObjectHelper.getObjectHelper().isNull(data.getTipoDocumento().getIdentificador())) {
            throw new BusinessCMDBException("El tipo de documento del cliente es nulo.", "Debe proporcionar un tipo de documento válido para el cliente.");
        }
        
        if (ObjectHelper.getObjectHelper().isNull(data.getCorreo()) || data.getCorreo().trim().isEmpty()) {
            throw new BusinessCMDBException("El correo electrónico del cliente está vacío.", "Debe proporcionar un correo electrónico válido para el cliente.");
        }
        if (!EMAIL_PATTERN.matcher(data.getCorreo()).matches()) {
            throw new BusinessCMDBException("El correo electrónico del cliente es inválido.", "Debe proporcionar un correo electrónico válido para el cliente.");
        }

        if (ObjectHelper.getObjectHelper().isNull(data.getTelefono())) {
            throw new BusinessCMDBException("El número de teléfono del cliente es nulo.", "Debe proporcionar un número de teléfono válido para el cliente.");
        }
        if (data.getTelefono() < MIN_PHONE_NUMBER || data.getTelefono() > MAX_PHONE_NUMBER) {
            throw new BusinessCMDBException("El número de teléfono del cliente es inválido.", "Debe proporcionar un número de teléfono válido de 10 dígitos para el cliente.");
        }
    }

}
