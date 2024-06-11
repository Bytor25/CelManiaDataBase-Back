package co.com.cmdb.controller.response;

import java.util.ArrayList;

import co.com.cmdb.dto.TipoDocumentoDTO;

public class TipoDocumentoResponse extends Response<TipoDocumentoDTO> {
	
	public TipoDocumentoResponse() {
		setMensajes( new ArrayList<String>());
		setDatos(new ArrayList<>());
		
	}

}
