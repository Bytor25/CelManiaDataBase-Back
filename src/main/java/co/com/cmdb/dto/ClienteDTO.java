package co.com.cmdb.dto;

import java.util.UUID;

import co.com.cmdb.crosscutting.helpers.TextHelper;
import co.com.cmdb.crosscutting.helpers.UUIDHelper;


public final class ClienteDTO {
	

	private String identificador;
	private TipoDocumentoDTO tipoDocumento;
	private String nombre;
	private String apellidos;
	private String correo;
	private int telefono;
	private boolean estado;
	
	public ClienteDTO() {
		super();
	}

	public ClienteDTO( final String identificador, final TipoDocumentoDTO tipoDocumento, final String nombre, final String apellidos, final String correo,
			final int telefono, final boolean estado) {
		
	
		setIdentificador (identificador);
		setTipoDocumento (tipoDocumento);
		setNombre (nombre);
		setApellido (apellidos);
		setCorreo (correo);
		setTelefono (telefono);
		setEstado(estado);
	}
	
	public static final ClienteDTO build() {
		return new ClienteDTO();
	}
	
	public static final ClienteDTO build( final String identificador, final TipoDocumentoDTO tipoDocumento, final String nombre, final String apellidos, final String correo, final int telefono, final boolean estado) {
		return new ClienteDTO( identificador, tipoDocumento, nombre, apellidos, correo, telefono , estado);
	}
	
	//Getters


	
	public final String getIdentificador() {
		return identificador;
	}
	
	public final TipoDocumentoDTO getTipoDocumento() {
		return tipoDocumento;
	}
	
	public final String getNombre() {
		return nombre;
	}
	
	public final String getApellido() {
		return apellidos;
	}
	
	public final String getCorreo() {
		return correo;
	}

	public final int getTelefono() {
		return telefono;
	}
	
	public boolean isEstado() {
		return estado;
	}


	//Setters
	


	public final ClienteDTO setIdentificador(String identificador) {
		this.identificador = identificador;
		return this;
	}

	public final ClienteDTO setTipoDocumento(final TipoDocumentoDTO tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
		return this;
	}

	public final ClienteDTO setNombre(final String nombre) {
		this.nombre = TextHelper.applyTrim(nombre);
		return this;
	}

	public final ClienteDTO setApellido(String apellidos) {
		this.apellidos = TextHelper.applyTrim(apellidos);
		return this;
	}

	public final ClienteDTO setCorreo(String correo) {
		this.correo = correo;
		return this;
	}

	public final ClienteDTO setTelefono(int telefono) {
		this.telefono = telefono;
		return this;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
}
