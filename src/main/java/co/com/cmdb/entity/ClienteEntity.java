package co.com.cmdb.entity;

import java.util.UUID;

import co.com.cmdb.crosscutting.helpers.ObjectHelper;
import co.com.cmdb.crosscutting.helpers.TextHelper;
import co.com.cmdb.crosscutting.helpers.UUIDHelper;



public final class ClienteEntity {
	
	private UUID id;
	private int identificador;
	private TipoDocumentoEntity tipoDocumento;
	private String nombre;
	private String apellido;
	private String correo;
	private int telefono;
	private boolean estado;
	
	public ClienteEntity() {
		setId(UUIDHelper.getDefault());
		setNombre(TextHelper.EMPTY);
		setTipoDocumento(TipoDocumentoEntity.build());
	}


	
	
	public ClienteEntity(final UUID id, final int identificador, final TipoDocumentoEntity tipoDocumento, final String nombre, final String apellido,
			String correo, int telefono, boolean estado) {
		
		setId(id);
		setIdentificador(identificador);
		setTipoDocumento(tipoDocumento);
		setNombre(nombre);
		setApellido(apellido);
		setCorreo(correo);
		setTelefono(telefono);
		setEstado(estado);
		
	}




	public static final ClienteEntity build() {
		return new ClienteEntity();
	}
	
	//Getters

	public final UUID getId() {
		return id;
	}
	
	public final int getIdentificador() {
		return identificador;
	}
	
	public final TipoDocumentoEntity getTipoDocumento() {
		return tipoDocumento;
	}
	
	public final String getNombre() {
		return nombre;
	}
	
	public final String getApellido() {
		return apellido;
	}
	
	public final String getCorreo() {
		return correo;
	}

	public final int getTelefono() {
		return telefono;
	}
	
	public boolean isEstado() {
		return estado;
	}


	//Setters
	
	public final ClienteEntity setId(final UUID id) {
		this.id = id;
		return this;
	}

	public final ClienteEntity setIdentificador(final int identificador) {
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

	public final ClienteEntity setApellido(String apellido) {
		this.apellido = apellido;
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

