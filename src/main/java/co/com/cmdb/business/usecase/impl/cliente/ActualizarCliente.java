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
			
        	var mensajeUsuario = "El número de teléfono ingresado ya está asociado a otro cliente";
        	var mensajeTecnico = "No fue posible la actualización del cliente ya que el número de teléfono ya está en uso";
        	throw new BusinessCMDBException(mensajeUsuario, mensajeTecnico);
		}
	}
	
	private final void validarClienteMismoCorreo(final String numeroDocumento, final String valor) {
		
		if(factory.getClienteDAO().existeCorreo(valor, numeroDocumento)) {
        	var mensajeUsuario = "El correo ingresado ya está asociado a otro cliente";
        	var mensajeTecnico = "No fue posible la actualización del cliente ya que el número de teléfono ya está en uso";
        	throw new BusinessCMDBException(mensajeUsuario, mensajeTecnico);
		}
		
	}
		    
	    private void validarNombre(final String valor) {
	    	
	        if (ObjectHelper.getObjectHelper().isNull(valor) || valor.trim().isEmpty()) {
	        	var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00047);
	        	var mensajeTecnico = "No fue posible la actualización del cliente debido a que el campo de nombre llegó vacío";
	        	throw new BusinessCMDBException(mensajeUsuario, mensajeTecnico);
	        }
	        
	        if (!validarLongitud(valor, 1, 60)) {
	        	var mensajeUsuario = "El nombre ingresado no se encuentra en el rango permitido. Revise los datos nuevamente";
	        	var mensajeTecnico = "No fue posible la actualización del cliente debido a que el nombre ingresado no se encuentra en el rango permitido";
	        	throw new BusinessCMDBException(mensajeUsuario, mensajeTecnico);
	        }
	        
	    }
	    
	    private void validarApellido(final String valor) {
	    	
	        if (ObjectHelper.getObjectHelper().isNull(valor) || valor.trim().isEmpty()) {
	        	var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00048);
	        	var mensajeTecnico = "No fue posible la actualización del cliente debido a que el campo de apellido llegó vacío";
	        	throw new BusinessCMDBException(mensajeUsuario, mensajeTecnico);
	        }
	        
	        if (!validarLongitud(valor, 1, 100)) {
	        	var mensajeUsuario = "El apellido ingresado no se encuentra en el rango permitido. Revise los datos nuevamente";
	        	var mensajeTecnico = "No fue posible la actualización del cliente debido a que el apellido ingresado no se encuentra en el rango permitido";
	        	throw new BusinessCMDBException(mensajeUsuario, mensajeTecnico);
	        }
	        
	    	
	    }
	    
	    private void validarCorreo(final String valor) {
	        if (ObjectHelper.getObjectHelper().isNull(valor) || valor.trim().isEmpty()) {
	        	var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00050);
	        	var mensajeTecnico = "No fue posible la actualización del cliente debido a que el campo de correo llegó vacío";
	        	throw new BusinessCMDBException(mensajeUsuario, mensajeTecnico);
	        }
	        if (!TextHelper.validarFormatoCorreo(valor)) {
	        	var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00051);
	        	var mensajeTecnico = "No fue posible la actualización del cliente debido a que el correo ingresado no se encuentra bajo el formato permitido";
	        	throw new BusinessCMDBException(mensajeUsuario, mensajeTecnico);
	        }
	        if (!validarLongitud(valor,6,255)) {
	        	var mensajeUsuario = "El correo ingresado no se encuentra en el rango permitido. Revise los datos nuevamente";
	        	var mensajeTecnico = "No fue posible el registro del cliente debido a que el correo ingresado no se encuentra en el rango permitido";
	        	throw new BusinessCMDBException(mensajeUsuario, mensajeTecnico);
	        }
	    }
	     
	    private void validarTelefono(final long valor) {
	    	
	    	String numeroTelefono = String.valueOf(valor);
	    	
	        if (ObjectHelper.getObjectHelper().isNull(numeroTelefono) || numeroTelefono.trim().isEmpty()) {
	        	var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00052);
	        	var mensajeTecnico = "No fue posible la actualización del cliente debido a que el campo de teléfono llegó vacío";
	        	throw new BusinessCMDBException(mensajeUsuario, mensajeTecnico);
	        }
	        if (!validarLongitud(numeroTelefono,1,10)) {
	        	var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00053);
	        	var mensajeTecnico = "No fue posbile la actualización del cliente debido a que el teléfono ingresado no se encuentra en el rango permitido";
	        	throw new BusinessCMDBException(mensajeUsuario, mensajeTecnico);
	        }
	        if(!TextHelper.validarSoloNumeros(numeroTelefono)) {
	        	var mensajeUsuario = "El teléfono ingresado no se encuentra en el formato correcto. Revise los datos nuevamente";
	        	var mensajeTecnico = "No fue posible la actualización del cliente debido a que el teléfono ingresado no se encuentra en el formato permitido";
	        	throw new BusinessCMDBException(mensajeUsuario, mensajeTecnico);
	        }
	        if(!LongHelper.validarRango(valor)) {
	        	var mensajeUsuario = "El teléfono ingresado no se encuentra en el rango definido. Revise los datos nuevamente";
	        	var mensajeTecnico = "No fue posible la actualización del cliente debido a que el teléfono ingresado no se encuentra dentro del rango definido";
	        	throw new BusinessCMDBException(mensajeUsuario, mensajeTecnico);
	        }
	    	
	    }
	    
	    
	    private boolean validarLongitud(final String valor, final int longitudMinima, final int LongitudMaxima) {
	    	
	    	return TextHelper.longitudMinima(valor, longitudMinima) && TextHelper.longitudMaxima(valor, LongitudMaxima);
	    	
	    }
	    
	    private void validarExisteTipoDocumento( final int TipoDocumento) {
	    	
	    	var tipoDocumentoEntity = TipoDocumentoEntity.build().setIdentificador(TipoDocumento);
	    	
	    	var tipoDocumentoResultado = factory.getTipoDocumentoDAO().consultar(tipoDocumentoEntity);
	    	
	    	if (tipoDocumentoResultado.isEmpty()) {
	    		var mensajeUsuario = "El tipo de documento ingresado no existe. Por favor intente con otro";
	    		var mensajeTecnico = "No fue posible la actualización del cliente debido a que el Tipo de documento seleccionado no se encuentra registrado en la base de datos POSTGRE SQL";
	    		
	    		throw new BusinessCMDBException(mensajeUsuario, mensajeTecnico);
	    	}
	    	
	    }


}
