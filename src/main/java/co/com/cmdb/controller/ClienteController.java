package co.com.cmdb.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import co.com.cmdb.dto.ClienteDTO;


public class ClienteController {
	
	@RestController
	@RequestMapping("/api/v1/clientes")
	public class ClientesController {
		
		@GetMapping("/dummy")
		public ClienteDTO dummy() {
			return ClienteDTO.build();
		}
		
		
		
		@GetMapping
		public ResponseEntity<ClienteResponse> consultar(){
			var httpStatusCode = HttpStatus.ACCEPTED;
			var clienteResponse = new ClienteResponse();
			try {
				var clienteDto = ClienteDTO.build();			
				var facade = new ConsultarClientesFacade();
				
				clienteResponse.setDatos(facade.excute(clienteDto));
				clienteResponse.getMensajes().add("Clientes consultados exitosamente");
				
			}catch (final CMDBExceptions excepcion) {
				httpStatusCode = HttpStatus.BAD_REQUEST;
				clienteResponse.getMensajes().add(excepcion.getMensajeUsuario());			
			}catch ( final Exception excepcion) {
				httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
				
				var mensajeUsuario = "Se ha presentado un problema tratando de consultar la informacion del cliente...";
				clienteResponse.getMensajes().add(mensajeUsuario);
				
				excepcion.printStackTrace();
			}
			
			return new ResponseEntity<>(clienteResponse, httpStatusCode);
		}
		
		@PostMapping
		public ResponseEntity<ClienteResponse> registrar(@RequestBody ClienteDTO cliente){
			var httpStatusCode = HttpStatus.ACCEPTED;
			var clienteResponse = new ClienteResponse();
			try {
				
				var facade = new RegistrarClientesFacade();
				
				facade.execute(cliente);
				clienteResponse.getMensajes().add("Cliente registrado exitosamente");
				
			
			}catch (final CMDBExceptions excepcion) {
				httpStatusCode = HttpStatus.BAD_REQUEST;
				clienteResponse.getMensajes().add(excepcion.getMensajeUsuario());			
			}catch ( final Exception excepcion) {
				httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
				
				var mensajeUsuario = "Se ha presentado un problema tratando de registrar la informacion de la ciudades...";
				clienteResponse.getMensajes().add(mensajeUsuario);
				
				excepcion.printStackTrace();
			}
			
			return new ResponseEntity<>(clienteResponse, httpStatusCode);
		}
		
		/*@DeleteMapping("/{id}")
		public ResponseEntity<ClienteResponse> eliminar(@PathVariable UUID id){
			var httpStatusCode = HttpStatus.ACCEPTED;
			var clienteResponse = new ClienteResponse();
			try {
				
				
				
				//var facade = new RegistrarCiudadesFacade();
				
				//facade.execute(id);
				clienteResponse.getMensajes().add("Cliente eliminada exitosamente");
				
			}catch (final CMDBExceptions excepcion) {
				httpStatusCode = HttpStatus.BAD_REQUEST;
				clienteResponse.getMensajes().add(excepcion.getMensajeUsuario());			
			}catch ( final Exception excepcion) {
				httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
				
				var mensajeUsuario = "Se ha presentado un problema tratando de eliminar la informacion del cliente...";
				clienteResponse.getMensajes().add(mensajeUsuario);
				
				excepcion.printStackTrace();
			}
			
			return new ResponseEntity<>(clienteResponse, httpStatusCode);
		}*/
		
		@PutMapping("/{id}")
		public ResponseEntity<ClienteResponse> actualizar(@PathVariable UUID id, @RequestBody ClienteDTO clienteDto){
			var httpStatusCode = HttpStatus.ACCEPTED;
			var clienteResponse = new ClienteResponse();
			try {
				clienteDto.setId(id);
				//var facade = new RegistrarCiudadesFacade();
				
				//facade.execute(id);
				clienteResponse.getMensajes().add("Cliente actualizado exitosamente");
				
			}catch (final CMDBExceptions excepcion) {
				httpStatusCode = HttpStatus.BAD_REQUEST;
				clienteResponse.getMensajes().add(excepcion.getMensajeUsuario());			
			}catch ( final Exception excepcion) {
				httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
				
				var mensajeUsuario = "Se ha presentado un problema tratando de actulizar la informacion de la ciudades...";
				clienteResponse.getMensajes().add(mensajeUsuario);
				
				excepcion.printStackTrace();
			}
			
			return new ResponseEntity<>(clienteResponse, httpStatusCode);
		}
	}
}

