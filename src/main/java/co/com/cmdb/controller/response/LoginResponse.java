package co.com.cmdb.controller.response;

import java.util.ArrayList;

import co.com.cmdb.dto.LoginDTO;

public class LoginResponse extends Response<LoginDTO> {
	
	public LoginResponse() {
		setMensajes( new ArrayList<>());
		setDatos(new ArrayList<>());
	}

}
