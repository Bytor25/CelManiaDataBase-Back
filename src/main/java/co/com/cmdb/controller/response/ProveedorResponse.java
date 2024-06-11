package co.com.cmdb.controller.response;

import java.util.ArrayList;

import co.com.cmdb.dto.ProveedorDTO;

public class ProveedorResponse extends Response<ProveedorDTO> {
	
	public ProveedorResponse() {
		setMensajes( new ArrayList<String>());
		setDatos(new ArrayList<>());
	}

}
