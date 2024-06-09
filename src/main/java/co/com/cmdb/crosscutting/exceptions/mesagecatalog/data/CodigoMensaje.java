package co.com.cmdb.crosscutting.exceptions.mesagecatalog.data;

import static co.com.cmdb.crosscutting.helpers.TextHelper.concatenate;
import static co.com.cmdb.crosscutting.helpers.TextHelper.UNDERLINE;

public enum CodigoMensaje {
	
	M00001(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00001", true),
	M00002(TipoMensaje.USUARIO, CategoriaMensaje.ERROR, "00002", true),
	M00003(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00003", true),
	M00004(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00004", true),
	M00005(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00005", true),
	M00006(TipoMensaje.USUARIO, CategoriaMensaje.ERROR, "00006", true),
	M00007(TipoMensaje.USUARIO, CategoriaMensaje.ERROR, "00007", true),
	M00008(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00008", true),
	M00009(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00009", true),
	M00010(TipoMensaje.USUARIO, CategoriaMensaje.ERROR, "00010", true),
	M00011(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00011", true),
	M00012(TipoMensaje.USUARIO, CategoriaMensaje.ERROR, "00012", true),
	M00013(TipoMensaje.USUARIO, CategoriaMensaje.ERROR, "00013", true),
	M00014(TipoMensaje.USUARIO, CategoriaMensaje.ERROR, "00014", true),
	M00015(TipoMensaje.USUARIO, CategoriaMensaje.ERROR, "00015", true),
	M00016(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00016", true),
	M00017(TipoMensaje.USUARIO, CategoriaMensaje.ERROR, "00017", true),
	M00018(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00018", true),
	M00019(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00019", true),
	M00020(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00020", true),
	M00021(TipoMensaje.USUARIO, CategoriaMensaje.ERROR, "00021", true),
	M00022(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00022", true),
	M00023(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00023", true),
	M00024(TipoMensaje.USUARIO, CategoriaMensaje.ERROR, "00024", true),
	M00025(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00025", true),
	M00026(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00026", true),
	M00027(TipoMensaje.USUARIO, CategoriaMensaje.ERROR, "00027", true),
	M00028(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00028", true),
	M00029(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00029", true),
	M00030(TipoMensaje.USUARIO, CategoriaMensaje.ERROR, "00030", true),
	M00031(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00031", true),
	M00032(TipoMensaje.USUARIO, CategoriaMensaje.ERROR, "00032", true),
	M00033(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00033", true),
	M00034(TipoMensaje.USUARIO, CategoriaMensaje.ERROR, "00034", true),
	M00035(TipoMensaje.USUARIO, CategoriaMensaje.ERROR, "00035", true),
	M00036(TipoMensaje.USUARIO, CategoriaMensaje.ERROR, "00036", true),
	M00037(TipoMensaje.USUARIO, CategoriaMensaje.ERROR, "00037", true),
	M00038(TipoMensaje.USUARIO, CategoriaMensaje.ERROR, "00038", true),
	M00039(TipoMensaje.USUARIO, CategoriaMensaje.ERROR, "00039", true),
	M00040(TipoMensaje.USUARIO, CategoriaMensaje.ERROR, "00040", true),
	M00041(TipoMensaje.TECNICO, CategoriaMensaje.ERROR, "00041", true);
	
	
	private TipoMensaje tipo;
	private CategoriaMensaje categoria;
	private String codigo;
	private boolean base;
	
	private CodigoMensaje(final TipoMensaje tipo, final CategoriaMensaje categoria, final String codigo, final boolean base) {
		setTipo(tipo);
		setCategoria(categoria);
		setCodigo(codigo);
		setBase(base);
	}

	//Getters
	
	public final TipoMensaje getTipo() {
		return tipo;
	}

	public final CategoriaMensaje getCategoria() {
		return categoria;
	}

	public final String getCodigo() {
		return codigo;
	}
	
	public final boolean isBase() {
		return base;
	}
	
	public String getIdentificador() {
		return concatenate(getTipo().name(), UNDERLINE, getCategoria().toString(), UNDERLINE, getCodigo());
	}
	//Setters
	

	private final void setTipo(TipoMensaje tipo) {
		this.tipo = tipo;
	}

	private final void setCategoria(CategoriaMensaje categoria) {
		this.categoria = categoria;
	}

	private final void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	private final void setBase(boolean base) {
		this.base = base;
	}
	
}
