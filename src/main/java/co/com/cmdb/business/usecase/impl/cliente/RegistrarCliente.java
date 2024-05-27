package co.com.cmdb.business.usecase.impl.cliente;

import java.util.UUID;
import co.com.cmdb.business.assembler.entity
import co.com.cmdb.business.domain.ClienteDomain;
import co.com.cmdb.business.usecase.UseCaseWithoutReturn;
import co.com.cmdb.crosscutting.exceptions.custom.BusinessCMDBException;
import co.com.cmdb.crosscutting.helpers.ObjectHelper;
import co.com.cmdb.crosscutting.helpers.UUIDHelper;
import co.com.cmdb.data.dao.factory.DAOFactory;
import co.com.cmdb.entity.ClienteEntity;
import co.com.cmdb.entity.TipoDocumentoEntity;

public final class RegistrarCliente implements UseCaseWithoutReturn<ClienteDomain> {
	
	private DAOFactory factory;
	
	public RegistrarCliente(final DAOFactory factory) {
		
		if(ObjectHelper.getObjectHelper().isNull(factory)) {
			
			var mensajeUsuario = "Se ha producido un problema tratando de llevar a cabo el registro del cliente";
			var mensajeTecnico = "El dao factory para registrar el cliente llegó nulo";
			
			throw new BusinessCMDBException(mensajeTecnico, mensajeUsuario);
		}
		
		this.factory = factory;
	}

	@Override
	public void execute(final ClienteDomain data) {
		 
		//1. 
		
		//2. 
		
		validarCiudadMismoNombreMismoTipoDocumento(data.getNombre(), data.getTipoDocumento().getId(), data.getId());
		
		//3
		
		var clienteEntity = ClienteEntity.build().setId(generarIdentificadorCliente());
		
		//4 Guardar
		
		factory.getClienteDAO().crear(clienteEntity);
		
	}
	
	private final UUID generarIdentificadorCliente() {
		
		UUID id = UUIDHelper.generate();
		boolean existeId = true;
		
		while(existeId) {
			
			id = UUIDHelper.generate();
			
			var clienteEntity = ClienteEntity.build().setId(id);
			var resultados = factory.getClienteDAO().consultar(clienteEntity);
			
			existeId = !resultados.isEmpty();
			
		}
		
		return id;
	}
	
	private final void validarCiudadMismoNombreMismoTipoDocumento(final String nombreCliente, final UUID idCliente, final UUID idTipoDocumento) {
		
		var clienteEntity = ClienteEntity.build().setNombre(nombreCliente)
				.setTipoDocumento(TipoDocumentoEntity.build().setId(idTipoDocumento));
		
		var resultados = factory.getClienteDAO().consultar(clienteEntity);
		
		if(!resultados.isEmpty()) {
			
			var mensajeUsuario = "Ya existe un cliente con el nombre ... asociada con el tipo de documento";
			throw new BusinessCMDBException(mensajeUsuario);
			
		}
		
	}
}
