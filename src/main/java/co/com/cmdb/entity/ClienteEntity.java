package co.com.cmdb.entity;

import co.com.cmdb.crosscutting.helpers.BooleanHelper;
import co.com.cmdb.crosscutting.helpers.LongHelper;
import co.com.cmdb.crosscutting.helpers.ObjectHelper;
import co.com.cmdb.crosscutting.helpers.TextHelper;



public final class ClienteEntity {
	
	private String identificador;
	private TipoDocumentoEntity tipoDocumento;
	private String nombre;
	private String apellidos;
	private String correo;
	private long telefono;
	private boolean estado;
	
	public ClienteEntity() {
		super();
		setIdentificador (TextHelper.EMPTY);
		setTipoDocumento (TipoDocumentoEntity.build());
		setNombre (TextHelper.EMPTY);
		setApellidos (TextHelper.EMPTY);
		setCorreo (TextHelper.EMPTY);
		setTelefono (LongHelper.DEFAULT_LONG);
		setEstado(BooleanHelper.DEFAULT_BOOLEAN );
	}


	public ClienteEntity( final String identificador, final TipoDocumentoEntity tipoDocumento, final String nombre, final String apellidos,
			final String correo, final long telefono,final  boolean estado) {
		

		setIdentificador(identificador);
		setTipoDocumento(tipoDocumento);
		setNombre(nombre);
		setApellidos(apellidos);
		setCorreo(correo);
		setTelefono(telefono);
		setEstado(estado);
		
	}	
	
	public static final ClienteEntity build() {
		return new ClienteEntity();
	}
	
	public static final ClienteEntity build( final String identificador, final TipoDocumentoEntity tipoDocumento, final String nombre, final String apellidoss, final String correo, final long telefono, final boolean estado) {
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

	public final ClienteEntity setApellidos(final String apellidos) {
		this.apellidos = TextHelper.applyTrim(apellidos);
		return this;
	}

	public final ClienteEntity setCorreo(final String correo) {
		this.correo = TextHelper.applyTrim(correo);
		return this;
	}

	public final ClienteEntity setTelefono(final long telefono) {
		this.telefono = telefono;
		return this;
	}

	public ClienteEntity setEstado(final boolean estado) {
		this.estado = BooleanHelper.getDefaultValue(estado);
		return this;
	}


}

