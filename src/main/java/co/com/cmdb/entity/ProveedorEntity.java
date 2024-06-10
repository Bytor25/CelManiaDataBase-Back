package co.com.cmdb.entity;

import java.util.UUID;

import co.com.cmdb.crosscutting.helpers.BooleanHelper;
import co.com.cmdb.crosscutting.helpers.LongHelper;
import co.com.cmdb.crosscutting.helpers.ObjectHelper;
import co.com.cmdb.crosscutting.helpers.TextHelper;
import co.com.cmdb.crosscutting.helpers.UUIDHelper;
import co.com.cmdb.entity.ProveedorEntity;

public class ProveedorEntity {
	
	private UUID identificador;
	private String numeroDocumento;
	private TipoDocumentoEntity tipoDocumento;
	private String nombre;
	private long telefono;
	private boolean estado;
	
	
	public ProveedorEntity() {
		
		super();
		setIdentificador(UUIDHelper.getDefault());
		setNumeroDocumento(TextHelper.EMPTY);
		setTipoDocumento(TipoDocumentoEntity.build());
		setNombre(TextHelper.EMPTY);
		setTelefono(LongHelper.DEFAULT_LONG);
		setEstado(BooleanHelper.DEFAULT_BOOLEAN);
		
	}
	
	public ProveedorEntity(final UUID identificador,  final String numeroDocumento,  final TipoDocumentoEntity tipoDocumento,  final String nombre,
			 final long telefono, final boolean estado) {
		
		setIdentificador(identificador);
		setNumeroDocumento(numeroDocumento);
		setTipoDocumento(tipoDocumento);
		setNombre(nombre);
		setTelefono(telefono);
		setEstado(estado);

	}
	
	public static final ProveedorEntity build() {
		
		return new ProveedorEntity();
		
	}
	
	//Getters

	public final UUID getIdentificador() {
		return identificador;
	}

	public final String getNumeroDocumento() {
		return numeroDocumento;
	}

	public final TipoDocumentoEntity getTipoDocumento() {
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

	public final ProveedorEntity setIdentificador(UUID identificador) {
		this.identificador = identificador;
		return this;
	}

	public final ProveedorEntity setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = TextHelper.applyTrim(numeroDocumento);
		return this;
	}

	public final ProveedorEntity setTipoDocumento(TipoDocumentoEntity tipoDocumento) {
		this.tipoDocumento = ObjectHelper.getObjectHelper().getDefaultValue(tipoDocumento, new TipoDocumentoEntity());
		return this;
	}

	public final ProveedorEntity setNombre(String nombre) {
		this.nombre = TextHelper.applyTrim(nombre);
		return this;
	}

	public final ProveedorEntity setTelefono(long telefono) {
		this.telefono = telefono;
		return this;
	}

	public ProveedorEntity setEstado(final boolean estado) {
		this.estado = BooleanHelper.getDefaultValue(estado);
		return this;
	}
}
