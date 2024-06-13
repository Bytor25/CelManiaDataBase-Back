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

import co.com.cmdb.business.facade.impl.cliente.ActualizarClientesFacade;
import co.com.cmdb.business.facade.impl.cliente.ConsultarClientesFacade;
import co.com.cmdb.business.facade.impl.cliente.ConsultarPorIdClientesFacade;
import co.com.cmdb.business.facade.impl.cliente.RegistrarClientesFacade;
import co.com.cmdb.controller.response.ClienteResponse;
import co.com.cmdb.crosscutting.exceptions.CMDBExceptions;
import co.com.cmdb.crosscutting.exceptions.mesagecatalog.MessageCatalogStrategy;
import co.com.cmdb.crosscutting.exceptions.mesagecatalog.data.CodigoMensaje;
import co.com.cmdb.dto.ClienteDTO;

@RestController
@RequestMapping("/api/v1/clientes")
@CrossOrigin(origins="http://localhost:4200")
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
			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00067);
			clienteResponse.getMensajes().add(mensajeUsuario);

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
			var mensajeUsuario= MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00068);
			clienteResponse.getMensajes().add(mensajeUsuario);

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
            var facade = new ActualizarClientesFacade();
            facade.execute(clienteDto);
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00069);
            clienteResponse.getMensajes().add(mensajeUsuario);

        } catch (final CMDBExceptions excepcion) {
            httpStatusCode = HttpStatus.BAD_REQUEST;
            clienteResponse.getMensajes().add(excepcion.getMensajeUsuario());
            excepcion.printStackTrace();
        } catch (final Exception excepcion) {
            httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00014);
            clienteResponse.getMensajes().add(mensajeUsuario);
            excepcion.printStackTrace();
        }

        return new ResponseEntity<>(clienteResponse, httpStatusCode);
    }
	
    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponse> consultarPorId(@PathVariable String id) {
        var httpStatusCode = HttpStatus.ACCEPTED;
        var clienteResponse = new ClienteResponse();

        try {
            var clienteDto = ClienteDTO.build();
            clienteDto.setNumeroDocumento(id);
            var facade = new ConsultarPorIdClientesFacade();

            var resultado = facade.execute(clienteDto);
            clienteResponse.setDatos(List.of(resultado)); 
            var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00079);
            clienteResponse.getMensajes().add(mensajeUsuario);

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
	
}
