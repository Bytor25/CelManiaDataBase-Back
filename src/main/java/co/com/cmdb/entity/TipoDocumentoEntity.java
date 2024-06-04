package co.com.cmdb.entity;


import co.com.cmdb.crosscutting.helpers.TextHelper;



public final class TipoDocumentoEntity {

	private int identificador;
	private String nombre;
	
	public TipoDocumentoEntity() {
		super();
		setIdentificador(identificador = 0);
		setNombre(TextHelper.EMPTY);
	}
	
	

	public TipoDocumentoEntity(final int identificador,final String nombre) {
		setIdentificador(identificador);
		setNombre(nombre);
	
	}
	
	public static final TipoDocumentoEntity build() {
		return new TipoDocumentoEntity();
	}
	
	public static final TipoDocumentoEntity build( final int identificador, final String nombre) {
		return new TipoDocumentoEntity( identificador, nombre);
	}

	//Getters
	
	public int getIdentificador() {
		return identificador;
	}

	
	public final String getNombre() {
		return nombre;
	}


	//Setters
	
	public final TipoDocumentoEntity setNombre(final String nombre) {
		this.nombre = TextHelper.applyTrim(nombre);
		return this;
	}
	
	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}
	
}
