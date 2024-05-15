package co.com.cmdb.crosscutting.exceptions.mesagecatalog.data;

public final class Mensaje {
	
	private CodigoMensaje codigo;
	private String contenido;
	
	
	public Mensaje(CodigoMensaje codigo, String contenido) {
		setCodigo(codigo);
		setContenido(contenido);
	}

	//Setters
	
	private final void setCodigo(CodigoMensaje codigo) {
		this.codigo = codigo;
	}


	private final void setContenido(String contenido) {
		this.contenido = contenido;
	}

	
	//Getters
	
	public final CodigoMensaje getCodigo() {
		return codigo;
	}

	public final String getContenido() {
		return contenido;
	}
	
	public final TipoMensaje getTipo() {
		return getCodigo().getTipo();
	}
	
	public final boolean esBase() {
		return getCodigo().isBase();
	}
	public final String getIdentificador() {
		return getCodigo().getIdentificador();
	}

}
