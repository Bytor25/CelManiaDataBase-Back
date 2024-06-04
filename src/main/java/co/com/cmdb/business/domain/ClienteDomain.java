package co.com.cmdb.business.domain;

import co.com.cmdb.crosscutting.helpers.BooleanHelper;
import co.com.cmdb.crosscutting.helpers.ObjectHelper;
import co.com.cmdb.crosscutting.helpers.TextHelper;



public class ClienteDomain {
	
	private String identificador;
	private TipoDocumentoDomain tipoDocumento;
	private String nombre;
	private String apellido;
	private String correo;
	private long telefono;
	private boolean estado;


	private ClienteDomain(final String identificador, final TipoDocumentoDomain tipoDocumento, 
						  final String nombre, final String apellido, final String correo,
						  final long telefono, final boolean estado) {
		
		setIdentificador(identificador);
		setTipoDocumento(tipoDocumento);
		setNombre(nombre);
		setApellido(apellido);
		setCorreo(correo);
		setTelefono(telefono);
		setEstado(estado);
	
	}

public static final ClienteDomain build(final String identificador, final TipoDocumentoDomain tipoDocumento, 
										final String nombre, final String apellido, final String correo,
										final long telefono, final boolean estado) {
	
	return new ClienteDomain(identificador, tipoDocumento, nombre, apellido, correo, telefono, estado);
	
}

public static final ClienteDomain build(final String identificador) {
	
	return new ClienteDomain(identificador, TipoDocumentoDomain.build(),TextHelper.EMPTY,TextHelper.EMPTY,TextHelper.EMPTY, 0, false);
	
}

public static final ClienteDomain build() {
	return new ClienteDomain(TextHelper.EMPTY, TipoDocumentoDomain.build(),TextHelper.EMPTY,TextHelper.EMPTY,TextHelper.EMPTY, 0, false);
	
}

// Getters

	public String getIdentificador() {
		return identificador;
	}
	
	public TipoDocumentoDomain getTipoDocumento() {
		return tipoDocumento;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getApellido() {
		return apellido;
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
	
	private void setIdentificador(String identificador) {
		this.identificador = TextHelper.applyTrim(identificador);
	}
	
	private void setTipoDocumento(TipoDocumentoDomain tipoDocumento) {
		this.tipoDocumento = ObjectHelper.getObjectHelper().getDefaultValue(tipoDocumento, TipoDocumentoDomain.build());
	}
	
	private void setNombre(String nombre) {
		this.nombre = TextHelper.applyTrim(nombre);
	}
	
	private void setApellido(String apellido) {
		this.apellido = TextHelper.applyTrim(apellido);
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



