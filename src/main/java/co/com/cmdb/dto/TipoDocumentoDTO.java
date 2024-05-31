package co.com.cmdb.dto;



public final class TipoDocumentoDTO {
	
	private int identificador;
	private String nombre;
	
	private TipoDocumentoDTO() {
		super();
	}

	private TipoDocumentoDTO(final int identificador, final String nombre) {
		setIdentificador(identificador);
		setNombre (nombre);
	}
	
	public static final TipoDocumentoDTO build() {
		return new TipoDocumentoDTO();
	}

	//Getters 
	
	public final int getIdentificador() {
		return identificador;
	}
	
	public final String getNombre() {
		return nombre;
	}


	//Setters
	
	public final TipoDocumentoDTO setIdentificador(int identificador) {
		this.identificador = identificador;
		return this;
	}

	public final TipoDocumentoDTO setNombre(String nombre) {
		this.nombre = nombre;
		return this;
	}
	
	
	
	
	

}
