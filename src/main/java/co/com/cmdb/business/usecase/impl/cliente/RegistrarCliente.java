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
		validarDatosCiudad(data);
		//2. 
		
		validarClienteMismoNumeroDocumentoMismoNombre(data.getNombre(), data.getIdentificador());
		
		//3
		
	
		var clienteEntity = ClienteEntity.build().setIdentificador(data.getIdentificador()).setNombre(data.getNombre())
				.setTipoDocumento(TipoDocumentoAssemblerEntity.getInstance().toEntity(data.getTipoDocumento()));
		
		//4 Guardar
		
		factory.getClienteDAO().crear(clienteEntity);
		
	}
	
	
	private final void validarClienteMismoNumeroDocumentoMismoNombre(final String nombreCliente, final String idCliente) {
		
		var clienteEntity = ClienteEntity.build().setNombre(nombreCliente);
		var resultados = factory.getClienteDAO().consultar(clienteEntity);
		
		if(!resultados.isEmpty()) {
			
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00012);
			throw new BusinessCMDBException(mensajeUsuario);
			
		}
		
	}
	
    private void validarDatosCiudad(final ClienteDomain data) {

        if (ObjectHelper.getObjectHelper().isNull(data.getNombre()) || data.getNombre().trim().isEmpty()) {
            throw new BusinessCMDBException("El nombre de la ciudad está vacío.", "Debe proporcionar un nombre válido para la ciudad.");
        }
        if (!NOMBRE_PATTERN.matcher(data.getNombre()).matches()) {
            throw new BusinessCMDBException("El nombre de la ciudad contiene caracteres inválidos.", "El nombre de la ciudad solo puede contener letras y espacios.");
        }
        if (ObjectHelper.getObjectHelper().isNull(data.getTipoDocumento()) || ObjectHelper.getObjectHelper().isNull(data.getTipoDocumento().getIdentificador())) {
            throw new BusinessCMDBException("El departamento de la ciudad es nulo.", "Debe proporcionar un departamento válido para la ciudad.");
        }
    }

}
