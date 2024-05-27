package co.com.cmdb.controller.response;

import java.util.ArrayList;

import co.com.cmdb.dto.ClienteDTO;

public class ClienteResponse extends Response<ClienteDTO> {
	
	public ClienteResponse() {
		setMensajes( new ArrayList<>());
		setDatos(new ArrayList<>());
	}

	

}
