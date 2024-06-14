package co.com.cmdb.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.cmdb.business.facade.impl.proveedor.ActualizarProveedorFacade;
import co.com.cmdb.business.facade.impl.proveedor.ConsultarPorIdProveedoresFacade;
import co.com.cmdb.business.facade.impl.proveedor.ConsultarPorNDTDProveedoresFacade;
import co.com.cmdb.business.facade.impl.proveedor.ConsultarProveedoresFacade;
import co.com.cmdb.business.facade.impl.proveedor.RegistrarProveedoresFacade;
import co.com.cmdb.controller.response.ProveedorResponse;
import co.com.cmdb.crosscutting.exceptions.CMDBExceptions;
import co.com.cmdb.crosscutting.exceptions.mesagecatalog.MessageCatalogStrategy;
import co.com.cmdb.crosscutting.exceptions.mesagecatalog.data.CodigoMensaje;
import co.com.cmdb.dto.ProveedorDTO;
import co.com.cmdb.dto.TipoDocumentoDTO;

@RestController
@RequestMapping("/api/v1/proveedores")
@CrossOrigin(origins="http://localhost:4200")

public class ProveedorController {
	
	@GetMapping("/dummy")
	public ProveedorDTO dummy() {
		
		return ProveedorDTO.build();
	}
	
	@GetMapping
	public ResponseEntity<ProveedorResponse> consultar() {
		var httpStatusCode = HttpStatus.ACCEPTED;
		var proveedorResponse = new ProveedorResponse();
		
		try {
			var proveedorDto = ProveedorDTO.build();
			var facade = new ConsultarProveedoresFacade();
			
			proveedorResponse.setDatos(facade.execute(proveedorDto));
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00075);
			proveedorResponse.getMensajes().add(mensajeUsuario);

		} catch (final CMDBExceptions excepcion) {
			httpStatusCode = HttpStatus.BAD_REQUEST;
			proveedorResponse.getMensajes().add(excepcion.getMensajeUsuario());
			excepcion.printStackTrace();
			
		} catch (final Exception excepcion) {
			httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;

			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00042);

			proveedorResponse.getMensajes().add(mensajeUsuario);

			excepcion.printStackTrace();
		}

		return new ResponseEntity<>(proveedorResponse, httpStatusCode);
	}
	
	@PostMapping
	public ResponseEntity<ProveedorResponse> crear(@RequestBody ProveedorDTO proveedor) {
		var httpStatusCode = HttpStatus.ACCEPTED;
		var proveedorResponse = new ProveedorResponse();
		try {
			
			var facade = new RegistrarProveedoresFacade();
			facade.execute(proveedor);
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00076);
			proveedorResponse.getMensajes().add(mensajeUsuario);

		} catch (final CMDBExceptions excepcion) {
			httpStatusCode = HttpStatus.BAD_REQUEST;
			proveedorResponse.getMensajes().add(excepcion.getMensajeUsuario());
			excepcion.printStackTrace();
			
		} catch (final Exception excepcion) {
			
			httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;

			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00043);
			proveedorResponse.getMensajes().add(mensajeUsuario);

			excepcion.printStackTrace();
		}

		return new ResponseEntity<>(proveedorResponse, httpStatusCode);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ProveedorResponse> actualizar(@PathVariable String id, @RequestBody ProveedorDTO proveedorDto) {
		var httpStatusCode = HttpStatus.ACCEPTED;
		var proveedorResponse = new ProveedorResponse();
		try {
			proveedorDto.setNumeroDocumento(id);
			var facade = new ActualizarProveedorFacade();
			
			facade.execute(proveedorDto);
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00077);
			proveedorResponse.getMensajes().add(mensajeUsuario);

		} catch (final CMDBExceptions excepcion) {
			httpStatusCode = HttpStatus.BAD_REQUEST;
			proveedorResponse.getMensajes().add(excepcion.getMensajeUsuario());
		} catch (final Exception excepcion) {
			httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;

			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00043);
			proveedorResponse.getMensajes().add(mensajeUsuario);

			excepcion.printStackTrace();
		}

		return new ResponseEntity<>(proveedorResponse, httpStatusCode);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<ProveedorResponse> consultarPorId(@PathVariable String id) {
	    var httpStatusCode = HttpStatus.ACCEPTED;
	    var proveedorResponse = new ProveedorResponse();

	    try {
	        var proveedorDto = ProveedorDTO.build();
	        proveedorDto.setNumeroDocumento(id);
	        var facade = new ConsultarPorIdProveedoresFacade();

	        var resultado = facade.execute(proveedorDto);
	        proveedorResponse.setDatos(resultado); 
	        var mensajeUsuario = "";
	        proveedorResponse.getMensajes().add(mensajeUsuario);

	    } catch (final CMDBExceptions excepcion) {
	        httpStatusCode = HttpStatus.BAD_REQUEST;
	        proveedorResponse.getMensajes().add(excepcion.getMensajeUsuario());
	        excepcion.printStackTrace();

	    } catch (final Exception excepcion) {
	        httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
	        var mensajeUsuario = "";
	        proveedorResponse.getMensajes().add(mensajeUsuario);
	        excepcion.printStackTrace();
	    }

	    return new ResponseEntity<>(proveedorResponse, httpStatusCode);
	}
	
	@GetMapping("/{id}/{tipoDocumento}")
	public ResponseEntity<ProveedorResponse> consultarPorNumeroDocumentoTipoDocumento(@PathVariable String id, @PathVariable int tipoDocumento){
		
	    var httpStatusCode = HttpStatus.ACCEPTED;
	    var proveedorResponse = new ProveedorResponse();

	    try {
	        var proveedorDto = ProveedorDTO.build();
	        var tipoDocumentoDto = TipoDocumentoDTO.build();
	        
	        tipoDocumentoDto.setIdentificador(tipoDocumento);
	        proveedorDto.setNumeroDocumento(id).setTipoDocumento(tipoDocumentoDto);
	        
	        var facade = new ConsultarPorNDTDProveedoresFacade();

	        var resultado = facade.execute(proveedorDto);
	        proveedorResponse.setDatos(List.of(resultado)); 
	        
	        var mensajeUsuario = "";
	        proveedorResponse.getMensajes().add(mensajeUsuario);

	    } catch (final CMDBExceptions excepcion) {
	        httpStatusCode = HttpStatus.BAD_REQUEST;
	        proveedorResponse.getMensajes().add(excepcion.getMensajeUsuario());
	        excepcion.printStackTrace();

	    } catch (final Exception excepcion) {
	        httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
	        var mensajeUsuario = "";
	        proveedorResponse.getMensajes().add(mensajeUsuario);
	        excepcion.printStackTrace();
	    }

	    return new ResponseEntity<>(proveedorResponse, httpStatusCode);
	
	}
}
		
		
	

		
