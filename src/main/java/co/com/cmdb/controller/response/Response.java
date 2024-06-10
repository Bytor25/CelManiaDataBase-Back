package co.com.cmdb.controller.response;

import java.util.ArrayList;
import java.util.List;

import co.com.cmdb.crosscutting.helpers.ObjectHelper;

public class Response<T> {
	
	private List<String> mensajes;
	private List<T> datos;
	
	public Response() {
		setMensajes(new ArrayList<>());
		setDatos(new ArrayList<>());
		
		}
	
	//Getters
	public final List<String> getMensajes() {
		return mensajes;
	}
	
	public final List<T> getDatos() {
		return datos;
	}
	
	
	//Setters
	public final void setMensajes(final List<String> mensajes) {
		this.mensajes = ObjectHelper.getObjectHelper().getDefaultValue(mensajes, new ArrayList<>());
	}
	
	public final void setDatos(List<T> datos) {
		this.datos = ObjectHelper.getObjectHelper().getDefaultValue(datos, new ArrayList<>());;
	}
	

}
