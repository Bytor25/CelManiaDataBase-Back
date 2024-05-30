package co.com.cmdb.entity;

import java.util.UUID;

import co.com.cmdb.crosscutting.helpers.TextHelper;
import co.com.cmdb.crosscutting.helpers.UUIDHelper;


public final class TipoDocumentoEntity {
	
	private UUID id;
	private int identificador;
	private String nombre;
	
	public TipoDocumentoEntity() {
		setId(UUIDHelper.getDefault());
		setNombre(TextHelper.EMPTY);
	}
	
	

	public TipoDocumentoEntity(final UUID id, final String nombre) {
		
		setId(id);
		setNombre(nombre);
	
	}
	
	public int getIdentificador() {
		return identificador;
	}

	public static final TipoDocumentoEntity build() {
		return new TipoDocumentoEntity();
	}

	//Getters 
	
	public final UUID getId() {
		return id;
	}
	
	public final String getNombre() {
		return nombre;
	}


	//Setters
	
	public final TipoDocumentoEntity setId(UUID id) {
		this.id = id;
		return this;
	}

	public final TipoDocumentoEntity setNombre(final String nombre) {
		this.nombre = TextHelper.applyTrim(nombre);
		return this;
	}
	
	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}
	
}
