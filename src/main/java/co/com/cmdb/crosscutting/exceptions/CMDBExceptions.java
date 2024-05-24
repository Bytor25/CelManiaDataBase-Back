package co.com.cmdb.crosscutting.exceptions;

import co.com.cmdb.crosscutting.exceptions.enums.Lugar;
import co.com.cmdb.crosscutting.helpers.ObjectHelper;
import co.com.cmdb.crosscutting.helpers.TextHelper;

public class CMDBExceptions extends RuntimeException{

	private static final long serialVersionUID = -3678185012202698958L;
	protected String mensajeUsuario;
	protected Lugar lugar;
	
	public CMDBExceptions(String mensajeTecnico, String mensajeUsuario, Lugar lugar, Throwable excepcionRaiz) {
		super(mensajeTecnico, excepcionRaiz);
		setMensajeUsuario(mensajeUsuario);
		setLugar(lugar);
	}
	
	public CMDBExceptions(String mensajeUsuario, Lugar lugar) {
		super(mensajeUsuario);
		setMensajeUsuario(mensajeUsuario);
		setLugar(lugar);
	}
	
	public CMDBExceptions(String mensajeTecnico, String mensajeUsuario, Lugar lugar) {
		super(mensajeUsuario);
		setMensajeUsuario(mensajeUsuario);
		setLugar(lugar);
	}
	private void setMensajeUsuario(String mensajeUsuario) {
		this.mensajeUsuario = TextHelper.applyTrim(mensajeUsuario);
	}
	
	private void setLugar(Lugar lugar) {
		this.lugar = ObjectHelper.getObjectHelper().getDefaultValue(lugar, Lugar.DEFAULT);
	}
	
	public static  long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public final String getMensajeUsuario() {
		return mensajeUsuario;
	}
	
	public final Lugar getLugar() {
		return lugar;
	}
	
}
	

 