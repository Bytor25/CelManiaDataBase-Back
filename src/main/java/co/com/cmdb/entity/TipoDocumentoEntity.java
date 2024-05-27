package co.com.cmdb.entity;

import java.util.UUID;

import co.com.cmdb.crosscutting.helpers.TextHelper;
import co.com.cmdb.crosscutting.helpers.UUIDHelper;


public final class TipoDocumentoEntity {
	
	private UUID id;
	private String nombre;
	
	public TipoDocumentoEntity() {
		setId(UUIDHelper.getDefault());
		setNombre(TextHelper.EMPTY);
	}
	
	

	public TipoDocumentoEntity(final UUID id, final String nombre) {
		setId(id);
		setNombre(nombre);
	
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

	public final void setNombre(String nombre) {
		this.nombre = TextHelper.applyTrim(nombre);
	}
	
	
	
	
	

}
