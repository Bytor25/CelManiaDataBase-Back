package co.com.cmdb.business.domain;

import java.util.UUID;

import co.com.cmdb.crosscutting.helpers.BooleanHelper;
import co.com.cmdb.crosscutting.helpers.LongHelper;
import co.com.cmdb.crosscutting.helpers.ObjectHelper;
import co.com.cmdb.crosscutting.helpers.TextHelper;
import co.com.cmdb.crosscutting.helpers.UUIDHelper;

public class ProveedorDomain {
	
	private UUID identificador;
	private String numeroDocumento;
	private TipoDocumentoDomain tipoDocumento;
	private String nombre;
	private long telefono;
	private boolean estado;
	
	private ProveedorDomain(final UUID identificador,final String numeroDocumento, final TipoDocumentoDomain tipoDocumento, 
			  final String nombre, final long telefono, final boolean estado) {

		setIdentificador(identificador);
		setNumeroDocumento(numeroDocumento);
		setTipoDocumento(tipoDocumento);
		setNombre(nombre);
		setTelefono(telefono);
		setEstado(estado);

}
	
	public static final ProveedorDomain build(final UUID identificador,final String numeroDocumento, final TipoDocumentoDomain tipoDocumento, 
			final String nombre, final long telefono, final boolean estado) {

		return new ProveedorDomain(identificador,numeroDocumento, tipoDocumento, nombre, telefono, estado);
		
	}
		
	
	public static final ProveedorDomain build(final UUID identificador) {
			
		return new ProveedorDomain(identificador, TextHelper.EMPTY, TipoDocumentoDomain.build(),TextHelper.EMPTY, LongHelper.DEFAULT_LONG, BooleanHelper.DEFAULT_BOOLEAN);
			
		}
	
	public static final ProveedorDomain build() {
		return new ProveedorDomain(UUIDHelper.getDefault(),TextHelper.EMPTY, TipoDocumentoDomain.build(),TextHelper.EMPTY, LongHelper.DEFAULT_LONG, BooleanHelper.DEFAULT_BOOLEAN);
		
	}

	//Getters
	
	public final UUID getIdentificador() {
		return identificador;
	}

	public final String getNumeroDocumento() {
		return numeroDocumento;
	}

	public final TipoDocumentoDomain getTipoDocumento() {
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

	private void setIdentificador(UUID identificador) {
		this.identificador = UUIDHelper.getDefault(identificador, UUIDHelper.getDefault());
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = TextHelper.applyTrim(numeroDocumento);
	}

	private void setTipoDocumento(TipoDocumentoDomain tipoDocumento) {
		this.tipoDocumento = ObjectHelper.getObjectHelper().getDefaultValue(tipoDocumento, TipoDocumentoDomain.build());
	}

	private void setNombre(String nombre) {
		this.nombre = TextHelper.applyTrim(nombre);
	}

	private void setTelefono(long telefono) {
		this.telefono = telefono;
	}

	private void setEstado(boolean estado) {
		this.estado = BooleanHelper.getDefaultValue(estado);
	}
	
	
	
	
	
	
	

}
