package co.com.cmdb.dto;

import co.com.cmdb.crosscutting.helpers.IntegerHelper;
import co.com.cmdb.crosscutting.helpers.TextHelper;

public final class TipoDocumentoDTO {
	
	private int identificador;
	private String nombre;
	
	public TipoDocumentoDTO() {
		super();
		setIdentificador(IntegerHelper.DEFAULT_INT);
		setNombre (TextHelper.EMPTY);
	}

	public TipoDocumentoDTO(final int identificador, final String nombre) {
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
	
	public final TipoDocumentoDTO setIdentificador(final int identificador) {
		this.identificador = identificador;
		return this;
	}

	public final TipoDocumentoDTO setNombre(final String nombre) {
		this.nombre = TextHelper.applyTrim(nombre);
		return this;
	}
	
	
	
	
	

}
