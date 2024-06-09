package co.com.cmdb.business.domain;

import java.util.UUID;

import co.com.cmdb.crosscutting.helpers.BooleanHelper;
import co.com.cmdb.crosscutting.helpers.ObjectHelper;
import co.com.cmdb.crosscutting.helpers.TextHelper;
import co.com.cmdb.crosscutting.helpers.UUIDHelper;



public class ClienteDomain {
	
	private UUID identificador;
	private String numeroDocumento;
	private TipoDocumentoDomain tipoDocumento;
	private String nombre;
	private String apellidos;
	private String correo;
	private long telefono;
	private boolean estado;


	private ClienteDomain(final UUID identificador,final String numeroDocumento, final TipoDocumentoDomain tipoDocumento, 
						  final String nombre, final String apellidos, final String correo,
						  final long telefono, final boolean estado) {
		
		setIdentificador(identificador);
		setNumeroDocumento(numeroDocumento);
		setTipoDocumento(tipoDocumento);
		setNombre(nombre);
		setApellidos(apellidos);
		setCorreo(correo);
		setTelefono(telefono);
		setEstado(estado);
	
	}

public static final ClienteDomain build(final UUID identificador,final String numeroDocumento, final TipoDocumentoDomain tipoDocumento, 
										final String nombre, final String apellidos, final String correo,
										final long telefono, final boolean estado) {
	
	return new ClienteDomain(identificador,numeroDocumento, tipoDocumento, nombre, apellidos, correo, telefono, estado);
	
}

public static final ClienteDomain build(final UUID identificador, final String numeroDocumento) {
	
	return new ClienteDomain(identificador,numeroDocumento, TipoDocumentoDomain.build(),TextHelper.EMPTY,TextHelper.EMPTY,TextHelper.EMPTY, 0, BooleanHelper.DEFAULT_BOOLEAN);
	
}

public static final ClienteDomain build() {
	return new ClienteDomain(UUIDHelper.getDefault(),TextHelper.EMPTY, TipoDocumentoDomain.build(),TextHelper.EMPTY,TextHelper.EMPTY,TextHelper.EMPTY, 0, BooleanHelper.DEFAULT_BOOLEAN);
	
}

// Getters

	public UUID getIdentificador() {
		return identificador;
	}
	
	public final String getNumeroDocumento() {
		return numeroDocumento;
	}

	public TipoDocumentoDomain getTipoDocumento() {
		return tipoDocumento;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getApellidos() {
		return apellidos;
	}
	
	public String getCorreo() {
		return correo;
	}
	
	public long getTelefono() {
		return telefono;
	}
	public final boolean isEstado() {
		return estado;
	}
	
//Setters
	
	private void setIdentificador(UUID identificador) {
		this.identificador = identificador;
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
	
	private void setApellidos(String apellidos) {
		this.apellidos = TextHelper.applyTrim(apellidos);
	}
	
	private void setCorreo(String correo) {
		this.correo = TextHelper.applyTrim(correo);
	}
	
	private void setTelefono(long telefono) {
		this.telefono = telefono;
	}
	private void setEstado(boolean estado) {
		this.estado = BooleanHelper.getDefaultValue(estado);
	}

}



