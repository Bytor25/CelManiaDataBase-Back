package co.com.cmdb.entity;

import java.util.UUID;

import co.com.cmdb.crosscutting.helpers.ObjectHelper;
import co.com.cmdb.crosscutting.helpers.TextHelper;


public final class ClienteEntity {
	
	private String identificador;
	private TipoDocumentoEntity tipoDocumento;
	private String nombre;
	private String apellidos;
	private String correo;
	private int telefono;
	private boolean estado;
	
	public ClienteEntity() {
		
		setNombre(TextHelper.EMPTY);
		setTipoDocumento(TipoDocumentoEntity.build());
	}


	public ClienteEntity( final String identificador, final TipoDocumentoEntity tipoDocumento, final String nombre, final String apellidos,
			String correo, int telefono, boolean estado) {
		

		setIdentificador(identificador);
		setTipoDocumento(tipoDocumento);
		setNombre(nombre);
		setapellidos(apellidos);
		setCorreo(correo);
		setTelefono(telefono);
		setEstado(estado);
		
	}	
	
	public static final ClienteEntity build() {
		return new ClienteEntity();
	}
	
	public static final ClienteEntity build( final String identificador, final TipoDocumentoEntity tipoDocumento, final String nombre, final String apellidoss, final String correo, final int telefono, final boolean estado) {
		return new ClienteEntity( identificador, tipoDocumento, nombre, apellidoss,correo,telefono, estado);
	}

	
	//Getters


	
	public final String getIdentificador() {
		return identificador;
	}
	
	public final TipoDocumentoEntity getTipoDocumento() {
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
	


	public final ClienteEntity setIdentificador(final String identificador) {
		this.identificador = identificador;
		return this;
	}

	public final ClienteEntity setTipoDocumento(final TipoDocumentoEntity tipoDocumento) {
		this.tipoDocumento = ObjectHelper.getObjectHelper().getDefaultValue(tipoDocumento, new TipoDocumentoEntity());
		return this;
		
	
	}

	public final ClienteEntity setNombre(final String nombre) {
		this.nombre = TextHelper.applyTrim(nombre);
		return this;
	}

	public final ClienteEntity setapellidos(String apellidos) {
		this.apellidos = apellidos;
		return this;
	}

	public final ClienteEntity setCorreo(String correo) {
		this.correo = correo;
		return this;
	}

	public final ClienteEntity setTelefono(int telefono) {
		this.telefono = telefono;
		return this;
	}

	public ClienteEntity setEstado(final boolean estado) {
		this.estado = estado;
		return this;
	}


}

