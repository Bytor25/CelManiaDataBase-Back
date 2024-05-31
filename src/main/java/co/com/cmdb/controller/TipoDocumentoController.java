package co.com.cmdb.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.cmdb.controller.response.TipoDocumentoResponse;
import co.com.cmdb.crosscutting.exceptions.CMDBExceptions;
import co.com.cmdb.crosscutting.exceptions.mesagecatalog.MessageCatalogStrategy;
import co.com.cmdb.crosscutting.exceptions.mesagecatalog.data.CodigoMensaje;
import co.com.cmdb.dto.TipoDocumentoDTO;

public class TipoDocumentoController {
	
	@RestController
	@RequestMapping("/api/v1/TipoDocumento")
	public class TipoDocumentosController {
		
		@GetMapping
		public TipoDocumentoDTO dummy() {
			return TipoDocumentoDTO.build();
		}
		
		@DeleteMapping("/{id}")
		public ResponseEntity<TipoDocumentoResponse> eliminar(@PathVariable UUID id){
			var httpStatusCode = HttpStatus.ACCEPTED;
			var tipoDocumentoResponse = new TipoDocumentoResponse();
			try {
				
				tipoDocumentoResponse.getMensajes().add("TipoDocumento eliminado exitosamente");
				
			}catch (final CMDBExceptions excepcion) {
				httpStatusCode = HttpStatus.BAD_REQUEST;
				tipoDocumentoResponse.getMensajes().add(excepcion.getMensajeUsuario());			
			}catch ( final Exception excepcion) {
				httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
				
				var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00015);
				tipoDocumentoResponse.getMensajes().add(mensajeUsuario);
				
				excepcion.printStackTrace();
			}
			
			return new ResponseEntity<>(tipoDocumentoResponse, httpStatusCode);
		}
		
	}

}
