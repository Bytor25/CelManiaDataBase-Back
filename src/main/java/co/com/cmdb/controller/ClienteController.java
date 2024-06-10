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

import co.com.cmdb.business.facade.impl.cliente.ConsultarClientesFacade;
import co.com.cmdb.business.facade.impl.cliente.RegistrarClientesFacade;
import co.com.cmdb.controller.response.ClienteResponse;
import co.com.cmdb.crosscutting.exceptions.CMDBExceptions;
import co.com.cmdb.crosscutting.exceptions.mesagecatalog.MessageCatalogStrategy;
import co.com.cmdb.crosscutting.exceptions.mesagecatalog.data.CodigoMensaje;
import co.com.cmdb.dto.ClienteDTO;

@RestController
@RequestMapping("/api/v1/clientes")
@CrossOrigin("http://localhost:4200")
public class ClienteController {

	@GetMapping("/dummy")
	public ClienteDTO dummy() {	
		return ClienteDTO.build();
	}

	@GetMapping
	public ResponseEntity<ClienteResponse> consultar() {
		var httpStatusCode = HttpStatus.ACCEPTED;
		var clienteResponse = new ClienteResponse();
		
		try {
			var clienteDto = ClienteDTO.build();
			var facade = new ConsultarClientesFacade();
			
			clienteResponse.setDatos(facade.execute(clienteDto));
			clienteResponse.getMensajes().add("Clientes consultados exitosamente");

		} catch (final CMDBExceptions excepcion) {
			httpStatusCode = HttpStatus.BAD_REQUEST;
			clienteResponse.getMensajes().add(excepcion.getMensajeUsuario());
			excepcion.printStackTrace();
			
		} catch (final Exception excepcion) {
			httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;

			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00006);

			clienteResponse.getMensajes().add(mensajeUsuario);

			excepcion.printStackTrace();
		}

		return new ResponseEntity<>(clienteResponse, httpStatusCode);
	}

	@PostMapping
	public ResponseEntity<ClienteResponse> crear(@RequestBody ClienteDTO cliente) {
		var httpStatusCode = HttpStatus.ACCEPTED;
		var clienteResponse = new ClienteResponse();
		try {
			
			var facade = new RegistrarClientesFacade();
			
			facade.execute(cliente);
			clienteResponse.getMensajes().add("Cliente registrado exitosamente");

		} catch (final CMDBExceptions excepcion) {
			httpStatusCode = HttpStatus.BAD_REQUEST;
			clienteResponse.getMensajes().add(excepcion.getMensajeUsuario());
			excepcion.printStackTrace();
			
		} catch (final Exception excepcion) {
			
			httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;

			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00007);
			clienteResponse.getMensajes().add(mensajeUsuario);

			excepcion.printStackTrace();
		}

		return new ResponseEntity<>(clienteResponse, httpStatusCode);
	}

	

	@PutMapping("/{id}")
	public ResponseEntity<ClienteResponse> actualizar(@PathVariable String id, @RequestBody ClienteDTO clienteDto) {
		var httpStatusCode = HttpStatus.ACCEPTED;
		var clienteResponse = new ClienteResponse();
		try {
			clienteDto.setNumeroDocumento(id);
			
			clienteResponse.getMensajes().add("Cliente actualizado exitosamente");

		} catch (final CMDBExceptions excepcion) {
			httpStatusCode = HttpStatus.BAD_REQUEST;
			clienteResponse.getMensajes().add(excepcion.getMensajeUsuario());
		} catch (final Exception excepcion) {
			httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;

			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00014);
			clienteResponse.getMensajes().add(mensajeUsuario);

			excepcion.printStackTrace();
		}

		return new ResponseEntity<>(clienteResponse, httpStatusCode);
	}
}
