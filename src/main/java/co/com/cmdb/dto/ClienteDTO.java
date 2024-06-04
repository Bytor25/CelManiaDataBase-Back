package co.com.cmdb.dto;

import co.com.cmdb.crosscutting.helpers.BooleanHelper;
import co.com.cmdb.crosscutting.helpers.LongHelper;
import co.com.cmdb.crosscutting.helpers.ObjectHelper;
import co.com.cmdb.crosscutting.helpers.TextHelper;


public final class ClienteDTO {

	private String identificador;
	private TipoDocumentoDTO tipoDocumento;
	private String nombre;
	private String apellidos;
	private String correo;
	private long telefono;
	private boolean estado;
	
	public ClienteDTO() {
		super();
		setIdentificador (TextHelper.EMPTY);
		setTipoDocumento (TipoDocumentoDTO.build());
		setNombre (TextHelper.EMPTY);
		setApellido (TextHelper.EMPTY);
		setCorreo (TextHelper.EMPTY);
		setTelefono (LongHelper.DEFAULT_LONG);
		setEstado(BooleanHelper.DEFAULT_BOOLEAN);
	}

	public ClienteDTO( final String identificador, final TipoDocumentoDTO tipoDocumento, final String nombre, final String apellidos, final String correo,
			final long telefono, final boolean estado) {
		
	
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

	public final long getTelefono() {
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
		this.tipoDocumento = ObjectHelper.getObjectHelper().getDefaultValue(tipoDocumento, new TipoDocumentoDTO());
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
		this.correo = TextHelper.applyTrim(correo);
		return this;
	}

	public final ClienteDTO setTelefono(long telefono) {
		this.telefono = telefono;
		return this;
	}

	public void setEstado(boolean estado) {
		this.estado = BooleanHelper.getDefaultValue(estado);
	}
}
