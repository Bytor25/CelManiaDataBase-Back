package co.com.cmdb.dto;

import java.util.UUID;

import co.com.cmdb.crosscutting.helpers.BooleanHelper;
import co.com.cmdb.crosscutting.helpers.LongHelper;
import co.com.cmdb.crosscutting.helpers.ObjectHelper;
import co.com.cmdb.crosscutting.helpers.TextHelper;
import co.com.cmdb.crosscutting.helpers.UUIDHelper;


public final class ClienteDTO {

	private UUID identificador;
	private String numeroDocumento;
	private TipoDocumentoDTO tipoDocumento;
	private String nombre;
	private String apellidos;
	private String correo;
	private long telefono;
	private boolean estado;
	
	public ClienteDTO() {
		super();
		setIdentificador (UUIDHelper.getDefault());
		setNumeroDocumento(TextHelper.EMPTY);
		setTipoDocumento (TipoDocumentoDTO.build());
		setNombre (TextHelper.EMPTY);
		setApellidos(TextHelper.EMPTY);
		setCorreo (TextHelper.EMPTY);
		setTelefono (LongHelper.DEFAULT_LONG);
		setEstado(BooleanHelper.DEFAULT_BOOLEAN);
	}


	
	public ClienteDTO(final UUID identificador, final String numeroDocumento, final TipoDocumentoDTO tipoDocumento, final String nombre,
			final String apellidos, final String correo, final long telefono, final boolean estado) {
		
		setIdentificador (identificador);
		setNumeroDocumento(numeroDocumento);
		setTipoDocumento (tipoDocumento);
		setNombre (nombre);
		setApellidos(apellidos);
		setCorreo (correo);
		setTelefono (telefono);
		setEstado(estado);
	}


	public static final ClienteDTO build() {
		return new ClienteDTO();
	}
	
	//Getters
	
	public final UUID getIdentificador() {
		return identificador;
	}
	
	public final String getNumeroDocumento() {
		return numeroDocumento;
	}

	public final TipoDocumentoDTO getTipoDocumento() {
		return tipoDocumento;
	}
	
	public final String getNombre() {
		return nombre;
	}
	
	public final String getApellidos() {
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

	public final ClienteDTO setIdentificador(UUID identificador) {
		this.identificador = UUIDHelper.getDefault(identificador, UUIDHelper.getDefault());
		return this;
	}

	public final ClienteDTO setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = TextHelper.applyTrim(numeroDocumento);
		return this;
	}

	public final ClienteDTO setTipoDocumento(final TipoDocumentoDTO tipoDocumento) {
		this.tipoDocumento = ObjectHelper.getObjectHelper().getDefaultValue(tipoDocumento, new TipoDocumentoDTO());
		return this;
	}

	public final ClienteDTO setNombre(String nombre) {
		this.nombre = TextHelper.applyTrim(nombre);
		return this;
	}

	public final ClienteDTO setApellidos(String apellidos) {
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

	public final ClienteDTO setEstado(boolean estado) {
		this.estado = BooleanHelper.getDefaultValue(estado);
		return this;
	}
}
