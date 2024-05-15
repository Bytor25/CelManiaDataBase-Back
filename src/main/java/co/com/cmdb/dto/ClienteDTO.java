package co.com.cmdb.dto;

import java.util.UUID;

public final class ClienteDTO {
	
	private UUID id;
	private int identificador;
	private String tipoDocumento;
	private String nombre;
	private String apellido;
	private String correo;
	private int telefono;
	
	private ClienteDTO() {
		super();
	}

	private ClienteDTO(final UUID id, final int identificador, final String tipoDocumento, final String nombre, final String apellido, final String correo,
			final int telefono) {
		
		setId (id);
		setIdentificador (identificador);
		setTipoDocumento (tipoDocumento);
		setNombre (nombre);
		setApellido (apellido);
		setCorreo (correo);
		setTelefono (telefono);
	}
	
	public static final ClienteDTO build() {
		return new ClienteDTO();
	}
	
	//Getters

	public final UUID getId() {
		return id;
	}
	
	public final int getIdentificador() {
		return identificador;
	}
	
	public final String getTipoDocumento() {
		return tipoDocumento;
	}
	
	public final String getNombre() {
		return nombre;
	}
	
	public final String getApellido() {
		return apellido;
	}
	
	public final String getCorreo() {
		return correo;
	}

	public final int getTelefono() {
		return telefono;
	}


	//Setters
	
	public final void setId(UUID id) {
		this.id = id;
	}

	public final void setIdentificador(int identificador) {
		this.identificador = identificador;
	}

	public final void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public final void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public final void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public final void setCorreo(String correo) {
		this.correo = correo;
	}

	public final void setTelefono(int telefono) {
		this.telefono = telefono;
	}

}
