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
			proveedorResponse.getMensajes().add("Proveedores consultados exitosamente");

		} catch (final CMDBExceptions excepcion) {
			httpStatusCode = HttpStatus.BAD_REQUEST;
			proveedorResponse.getMensajes().add(excepcion.getMensajeUsuario());
			excepcion.printStackTrace();
			
		} catch (final Exception excepcion) {
			httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;

			var mensajeUsuario = "";//Msg6

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
			proveedorResponse.getMensajes().add("Proveedor registrado exitosamente");

		} catch (final CMDBExceptions excepcion) {
			httpStatusCode = HttpStatus.BAD_REQUEST;
			proveedorResponse.getMensajes().add(excepcion.getMensajeUsuario());
			excepcion.printStackTrace();
			
		} catch (final Exception excepcion) {
			
			httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;

			var mensajeUsuario = ""; //msg7
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
			// var facade = new RegistrarCiudadesFacade();

			// facade.execute(id);
			proveedorResponse.getMensajes().add("Proveedor actualizado exitosamente");

		} catch (final CMDBExceptions excepcion) {
			httpStatusCode = HttpStatus.BAD_REQUEST;
			proveedorResponse.getMensajes().add(excepcion.getMensajeUsuario());
		} catch (final Exception excepcion) {
			httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;

			var mensajeUsuario = ""; //Msg14
			proveedorResponse.getMensajes().add(mensajeUsuario);

			excepcion.printStackTrace();
		}

		return new ResponseEntity<>(proveedorResponse, httpStatusCode);
	}
	
	
		
		
}
	

		
