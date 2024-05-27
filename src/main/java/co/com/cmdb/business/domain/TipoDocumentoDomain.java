package co.com.cmdb.business.domain;

import java.util.UUID;

import co.com.cmdb.crosscutting.helpers.TextHelper;
import co.com.cmdb.crosscutting.helpers.UUIDHelper;

public final class TipoDocumentoDomain {
	
	private UUID id;
	private String nombre;
	
	private TipoDocumentoDomain(final UUID id, final String nombre) {
		
		setId(id);
		setNombre(nombre);
		
	}
	
	public static TipoDocumentoDomain build(final UUID id, final String nombre) {
		
		return new TipoDocumentoDomain(id, nombre);
		
	}
	
	public static TipoDocumentoDomain build(final UUID id) {
		
		return new TipoDocumentoDomain(id, TextHelper.EMPTY);
		
	}
	
	public static TipoDocumentoDomain build() {
		
		return new TipoDocumentoDomain(UUIDHelper.getDefault(), TextHelper.EMPTY);
		
	}
	
	//Getters

	public UUID getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}
	
	//Setters

	private void setId(UUID id) {
		this.id = id;
	}

	private void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}