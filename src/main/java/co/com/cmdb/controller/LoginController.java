package co.com.cmdb.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import co.com.cmdb.business.facade.impl.login.ConsultarLoginFacade;
import co.com.cmdb.controller.response.LoginResponse;
import co.com.cmdb.crosscutting.exceptions.CMDBExceptions;
import co.com.cmdb.crosscutting.exceptions.mesagecatalog.MessageCatalogStrategy;
import co.com.cmdb.crosscutting.exceptions.mesagecatalog.data.CodigoMensaje;
import co.com.cmdb.dto.LoginDTO;

@RestController
@RequestMapping("/api/v1/logins")
@CrossOrigin("http://localhost:4200")

public class LoginController {
	
	@GetMapping("/dummy")
	public LoginDTO dummy() {
		
		return LoginDTO.build();
		
	}
	
	@GetMapping
	public ResponseEntity<LoginResponse> consultar() {
		var httpStatusCode = HttpStatus.ACCEPTED;
		var loginResponse = new LoginResponse();
		
		try {
			var loginDto = LoginDTO.build();
			var facade = new ConsultarLoginFacade();
			
			loginResponse.setDatos(facade.execute(loginDto));
			loginResponse.getMensajes().add("Usuarios consultados exitosamente");

		} catch (final CMDBExceptions excepcion) {
			httpStatusCode = HttpStatus.BAD_REQUEST;
			loginResponse.getMensajes().add(excepcion.getMensajeUsuario());
			excepcion.printStackTrace();
			
		} catch (final Exception excepcion) {
			httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;

			var mensajeUsuario = ""; //Msg6

			loginResponse.getMensajes().add(mensajeUsuario);

			excepcion.printStackTrace();
		}

		return new ResponseEntity<>(loginResponse, httpStatusCode);
	}

}
