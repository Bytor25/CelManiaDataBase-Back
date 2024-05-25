package co.com.cmdb.entity;

import java.util.UUID;

import co.com.cmdb.crosscutting.helpers.ObjectHelper;
import co.com.cmdb.crosscutting.helpers.TextHelper;

public final class ClienteEntity {
	
	private UUID id;
	private int identificador;
	private TipoDocumentoEntity tipoDocumento;
	private String nombre;
	private String apellido;
	private String correo;
	private int telefono;
	private boolean estado;
	
	private ClienteEntity() {
		super();
	}

	private ClienteEntity(final UUID id, final int identificador, final TipoDocumentoEntity tipoDocumento, final String nombre, final String apellido, final String correo,
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
	
	public static final ClienteEntity build() {
		return new ClienteEntity();
	}
	
	//Getters

	public final UUID getId() {
		return id;
	}
	
	public final int getIdentificador() {
		return identificador;
	}
	
	public final TipoDocumentoEntity getTipoDocumento() {
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
	
	public final void setId(UUID id) {
		this.id = id;
	}

	public final void setIdentificador(int identificador) {
		this.identificador = identificador;
	}

	public final void setTipoDocumento(final TipoDocumentoEntity tipoDocumento) {
		this.tipoDocumento = ObjectHelper.getObjectHelper().getDefaultValue(tipoDocumento, new TipoDocumentoEntity());
	}

	public final void setNombre(String nombre) {
		this.nombre = TextHelper.applyTrim(nombre);
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

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
}

