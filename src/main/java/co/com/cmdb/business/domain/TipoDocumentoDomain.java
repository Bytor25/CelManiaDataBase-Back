package co.com.cmdb.business.domain;


import co.com.cmdb.crosscutting.helpers.IntegerHelper;
import co.com.cmdb.crosscutting.helpers.TextHelper;


public final class TipoDocumentoDomain {
	
	private int identificador;
	private String nombre;
	
	
	private TipoDocumentoDomain() {
		super();
		setIdentificador(IntegerHelper.DEFAULT_INT);
		setNombre(TextHelper.EMPTY);
	}
	
	private TipoDocumentoDomain(final int identificador, final String nombre) {
		setIdentificador(identificador);
		setNombre(nombre);
		
	}
	
	public static TipoDocumentoDomain build(final int identificador, final String nombre) {
		
		return new TipoDocumentoDomain(identificador, nombre);
		
	}
	
	public static TipoDocumentoDomain build(final int identificador) {
		
		return new TipoDocumentoDomain(identificador, TextHelper.EMPTY);
		
	}
	
	public static TipoDocumentoDomain build() {
		
		return new TipoDocumentoDomain(IntegerHelper.DEFAULT_INT,TextHelper.EMPTY);
	}
	
	//Getters

	public int getIdentificador() {
		return identificador;
	}

	public String getNombre() {
		return nombre;
	}
	
	//Setters

	private void setIdentificador(int identificador) {
		this.identificador = IntegerHelper.getDefaultValue(identificador);
	}

	private void setNombre(String nombre) {
		this.nombre = TextHelper.applyTrim(nombre);
	}
}