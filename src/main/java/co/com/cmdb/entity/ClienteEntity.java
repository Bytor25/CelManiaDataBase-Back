package co.com.cmdb.entity;

import java.util.UUID;

import co.com.cmdb.crosscutting.helpers.BooleanHelper;
import co.com.cmdb.crosscutting.helpers.LongHelper;
import co.com.cmdb.crosscutting.helpers.ObjectHelper;
import co.com.cmdb.crosscutting.helpers.TextHelper;
import co.com.cmdb.crosscutting.helpers.UUIDHelper;

public final class ClienteEntity {
	
	private UUID identificador;
	private String numeroDocumento;
	private TipoDocumentoEntity tipoDocumento;
	private String nombre;
	private String apellidos;
	private String correo;
	private long telefono;
	private boolean estado;
	
	public ClienteEntity() {
		setIdentificador (UUIDHelper.generate());
		setNumeroDocumento (TextHelper.EMPTY);
		setTipoDocumento (TipoDocumentoEntity.build());
		setNombre (TextHelper.EMPTY);
		setApellidos (TextHelper.EMPTY);
		setCorreo (TextHelper.EMPTY);
		setTelefono (LongHelper.DEFAULT_LONG);
		setEstado(BooleanHelper.DEFAULT_BOOLEAN);
	}

	public ClienteEntity(final UUID identificador, final String numeroDocumento, final TipoDocumentoEntity tipoDocumento, final String nombre,
			final String apellidos, final String correo, final long telefono, final boolean estado) {
		
		setIdentificador(identificador);
		setNumeroDocumento(numeroDocumento);
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
	
	public static final ClienteEntity build( final UUID identificador,final String numeroDocumento, final TipoDocumentoEntity tipoDocumento, 
			final String nombre, final String apellidos, final String correo, final long telefono, final boolean estado) {
		
		return new ClienteEntity( identificador, numeroDocumento, tipoDocumento, nombre, apellidos, correo, telefono, estado);
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
	


	public final ClienteEntity setIdentificador(UUID identificador) {
		this.identificador = UUIDHelper.getDefault(identificador, UUIDHelper.getDefault());
		return this;
	}

	public final ClienteEntity setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = TextHelper.applyTrim(numeroDocumento);
		return this;
	}

	public final ClienteEntity setTipoDocumento(TipoDocumentoEntity tipoDocumento) {
		this.tipoDocumento = ObjectHelper.getObjectHelper().getDefaultValue(tipoDocumento, new TipoDocumentoEntity());
		return this;
	}

	public final ClienteEntity setNombre(String nombre) {
		this.nombre = TextHelper.applyTrim(nombre);
		return this;
	}

	public final ClienteEntity setApellidos(String apellidos) {
		this.apellidos = TextHelper.applyTrim(apellidos);
		return this;
	}

	public final ClienteEntity setCorreo(String correo) {
		this.correo = TextHelper.applyTrim(correo);
		return this;
	}

	public final ClienteEntity setTelefono(long telefono) {
		this.telefono = telefono;
		return this;
	}

	public ClienteEntity setEstado(final boolean estado) {
		this.estado = BooleanHelper.getDefaultValue(estado);
		return this;
	}


}

