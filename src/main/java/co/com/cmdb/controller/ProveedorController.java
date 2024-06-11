package co.com.cmdb.controller;

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
import co.com.cmdb.business.facade.impl.proveedor.ConsultarProveedoresFacade;
import co.com.cmdb.business.facade.impl.proveedor.RegistrarProveedoresFacade;
import co.com.cmdb.controller.response.ProveedorResponse;
import co.com.cmdb.crosscutting.exceptions.CMDBExceptions;
import co.com.cmdb.crosscutting.exceptions.mesagecatalog.MessageCatalogStrategy;
import co.com.cmdb.crosscutting.exceptions.mesagecatalog.data.CodigoMensaje;
import co.com.cmdb.dto.ProveedorDTO;

@RestController
@RequestMapping("/api/v1/proveedores")
@CrossOrigin("http://localhost:4200")

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
			proveedorResponse.getMensajes().add(MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00075));

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
			proveedorResponse.getMensajes().add(MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00076));

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
			proveedorResponse.getMensajes().add(MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00077));

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
	
	
		
		
}
	

		
