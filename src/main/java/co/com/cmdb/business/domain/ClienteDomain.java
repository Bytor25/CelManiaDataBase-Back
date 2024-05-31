package co.com.cmdb.business.domain;

import java.util.UUID;


import co.com.cmdb.crosscutting.helpers.TextHelper;
import co.com.cmdb.crosscutting.helpers.UUIDHelper;

public class ClienteDomain {
	
	private String identificador;
	private TipoDocumentoDomain tipoDocumento;
	private String nombre;
	private String apellido;
	private String correo;
	private int telefono;


private ClienteDomain(final String iddentificador, final TipoDocumentoDomain tipoDocumento, 
										final String nombre, final String apellido, final String correo,
										final int telefono) {
	
	setIdentificador(identificador);
	setTipoDocumento(tipoDocumento);
	setNombre(nombre);
	setApellido(apellido);
	setCorreo(correo);
	setTelefono(telefono);

}


public ClienteDomain(String identificador, String empty, TipoDocumentoDomain tipoDocumentoDomain) { //Eliminar
	// TODO Auto-generated constructor stub
}


public static final ClienteDomain build(final String identificador, final TipoDocumentoDomain tipoDocumento, 
										final String nombre, final String apellido, final String correo,
										final int telefono) {
	
	return new ClienteDomain(identificador, tipoDocumento, nombre, apellido, correo, telefono);
	
}

public static final ClienteDomain build(final String identificador, final String nombre, TipoDocumentoDomain tipoDocumento) {
	
	return new ClienteDomain(identificador, TextHelper.EMPTY, TipoDocumentoDomain.build());
	
}

public static final ClienteDomain build() {
	return new ClienteDomain(TextHelper.EMPTY, TextHelper.EMPTY, TipoDocumentoDomain.build());
	
}

	public String getIdentificador() {
		return identificador;
	}
	
	public TipoDocumentoDomain getTipoDocumento() {
		return tipoDocumento;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getApellido() {
		return apellido;
	}
	
	public String getCorreo() {
		return correo;
	}
	
	public int getTelefono() {
		return telefono;
	}
	
	private void setIdentificador(String identificador) {
		this.identificador = identificador;
	}
	
	private void setTipoDocumento(TipoDocumentoDomain tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	
	private void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	private void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	private void setCorreo(String correo) {
		this.correo = correo;
	}
	
	private void setTelefono(int telefono) {
		this.telefono = telefono;
	}

}



