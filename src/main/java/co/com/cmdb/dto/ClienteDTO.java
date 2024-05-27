package co.com.cmdb.dto;

import java.util.UUID;

public final class ClienteDTO {
	
	private UUID id;
	private int identificador;
	private TipoDocumentoDTO tipoDocumento;
	private String nombre;
	private String apellido;
	private String correo;
	private int telefono;
	private boolean estado;
	
	private ClienteDTO() {
		super();
	}

	private ClienteDTO(final UUID id, final int identificador, final TipoDocumentoDTO tipoDocumento, final String nombre, final String apellido, final String correo,
			final int telefono) {
		
		setId (id);
		setIdentificador (identificador);
		setTipoDocumento (tipoDocumento);
		setNombre (nombre);
		setApellido (apellido);
		setCorreo (correo);
		setTelefono (telefono);
		setEstado(estado);
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
	
	public final TipoDocumentoDTO getTipoDocumento() {
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
	
	public boolean isEstado() {
		return estado;
	}


	//Setters
	
	public final ClienteDTO setId(UUID id) {
		this.id = id;
		return this;
	}

	public final ClienteDTO setIdentificador(int identificador) {
		this.identificador = identificador;
		return this;
	}

	public final ClienteDTO setTipoDocumento(final TipoDocumentoDTO tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
		return this;
	}

	public final ClienteDTO setNombre(final String nombre) {
		this.nombre = nombre;
		return this;
	}

	public final ClienteDTO setApellido(String apellido) {
		this.apellido = apellido;
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
