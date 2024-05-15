package co.com.cmdb.dto;

import java.util.UUID;

public final class TipoDocumentoDTO {
	
	private UUID id;
	private String nombre;
	
	private TipoDocumentoDTO() {
		super();
	}

	private TipoDocumentoDTO(final UUID id, final String nombre) {
		setId (id);
		setNombre (nombre);
	}
	
	public static final TipoDocumentoDTO build() {
		return new TipoDocumentoDTO();
	}

	//Getters 
	
	public final UUID getId() {
		return id;
	}
	
	public final String getNombre() {
		return nombre;
	}


	//Setters
	
	public final void setId(UUID id) {
		this.id = id;
	}

	public final void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	
	
	

}
