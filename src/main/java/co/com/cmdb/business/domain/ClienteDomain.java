package co.com.cmdb.business.domain;

import java.util.UUID;

import co.com.cmdb.crosscutting.helpers.ObjectHelper;
import co.com.cmdb.crosscutting.helpers.TextHelper;
import co.com.cmdb.crosscutting.helpers.UUIDHelper;

public class ClienteDomain {
	
	private UUID id;
	private TipoDocumentoDomain tipoDocumento;
	private String nombre;
	private String apellido;
	private String correo;
	private int telefono;


private ClienteDomain(final UUID id, final TipoDocumentoDomain tipoDocumento, 
										final String nombre, final String apellido, final String correo,
										final int telefono) {
	
	setId(id);
	setTipoDocumento(tipoDocumento);
	setNombre(nombre);
	setApellido(apellido);
	setCorreo(correo);
	setTelefono(telefono);

}


public ClienteDomain(UUID id2, String empty, TipoDocumentoDomain tipoDocumentoDomain) { //Eliminar
	// TODO Auto-generated constructor stub
}


public static final ClienteDomain build(final UUID id, final TipoDocumentoDomain tipoDocumento, 
										final String nombre, final String apellido, final String correo,
										final int telefono) {
	
	return new ClienteDomain(id, tipoDocumento, nombre, apellido, correo, telefono);
	
}

public static final ClienteDomain build(final UUID id) {
	
	return new ClienteDomain(id, TextHelper.EMPTY, TipoDocumentoDomain.build());
	
}

public static final ClienteDomain build() {
	
	return new ClienteDomain(UUIDHelper.getDefault(), TextHelper.EMPTY, TipoDocumentoDomain.build());
	
}

	public UUID getId() {
		return id;
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
	
	private void setId(UUID id) {
		this.id = id;
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



