package co.com.cmdb.dto;

import java.util.UUID;

import co.com.cmdb.crosscutting.helpers.BooleanHelper;
import co.com.cmdb.crosscutting.helpers.LongHelper;
import co.com.cmdb.crosscutting.helpers.ObjectHelper;
import co.com.cmdb.crosscutting.helpers.TextHelper;
import co.com.cmdb.crosscutting.helpers.UUIDHelper;

public final class ProveedorDTO {

	private UUID identificador;
	private String numeroDocumento;
	private TipoDocumentoDTO tipoDocumento;
	private String nombre;
	private long telefono;
	private boolean estado;
	
	
	public ProveedorDTO() {
		
		super();
		setIdentificador(UUIDHelper.getDefault());
		setNumeroDocumento(TextHelper.EMPTY);
		setTipoDocumento(TipoDocumentoDTO.build());
		setNombre(TextHelper.EMPTY);
		setTelefono(LongHelper.DEFAULT_LONG);
		setEstado(BooleanHelper.DEFAULT_BOOLEAN);
		
	}
	
	public ProveedorDTO(final UUID identificador,  final String numeroDocumento,  final TipoDocumentoDTO tipoDocumento,  final String nombre,
			 final long telefono, final boolean estado) {
		
		setIdentificador(identificador);
		setNumeroDocumento(numeroDocumento);
		setTipoDocumento(tipoDocumento);
		setNombre(nombre);
		setTelefono(telefono);
		setEstado(estado);

	}
	
	public static final ProveedorDTO build() {
		
		return new ProveedorDTO();
		
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

	public final long getTelefono() {
		return telefono;
	}

	public final boolean isEstado() {
		return estado;
	}
	
	//Setters

	public final ProveedorDTO setIdentificador(UUID identificador) {
		this.identificador = identificador;
		return this;
	}

	public final ProveedorDTO setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = TextHelper.applyTrim(numeroDocumento);
		return this;
	}

	public final ProveedorDTO setTipoDocumento(TipoDocumentoDTO tipoDocumento) {
		this.tipoDocumento = ObjectHelper.getObjectHelper().getDefaultValue(tipoDocumento, new TipoDocumentoDTO());
		return this;
	}

	public final ProveedorDTO setNombre(String nombre) {
		this.nombre = TextHelper.applyTrim(nombre);
		return this;
	}

	public final ProveedorDTO setTelefono(long telefono) {
		this.telefono = telefono;
		return this;
	}

	public final ProveedorDTO setEstado(boolean estado) {
		this.estado = BooleanHelper.getDefaultValue(estado);
		return this;
	}
	
	

}
