package co.com.cmdb.crosscutting.helpers;

public final class TextHelper {
	
	public static final String EMPTY = "";
	public static final String UNDERLINE = "_";
	private static final String EMAIL_PATTERN = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
	private static final String DOCUMENT_NUMBER_PATTERN = "^[0-9]+$";
	private static final String ESPACIOS_LETRAS_DIGITOS = "^[0-9A-Za-záéíóúÁÉÍÓÚ]+$";

	private TextHelper() {
		super();
	}
	
	public static final boolean isNull(final String string) {
		return ObjectHelper.getObjectHelper().isNull(string);
	}
	
	public static final boolean isNullOrEmpty(final String string) {
		return isNull(string) || EMPTY.equals(string.trim());
	}
	
	public static final String getDefaultValue(final String string, final String defaultValue) {
		return ObjectHelper.getObjectHelper().getDefaultValue(string, defaultValue);
	}
	
	public static final String getDefaultValue(final String string) {
		return getDefaultValue(string,EMPTY);
	}
	
	public static final String applyTrim(final String string) {
		return getDefaultValue(string).trim();
	}
	
	public static final String concatenate(String...strings) {
		final StringBuilder sb = new StringBuilder(EMPTY);
		if(!ObjectHelper.getObjectHelper().isNull(strings)) {
			for(final var string : strings) {
				sb.append(applyTrim(string));
			}
		}
		return sb.toString();
	}
	
    public static String reemplazarParametro(String mensaje, String... parametros) {
        String mensajeReemplazado = mensaje;
        for(int i = 0; i< parametros.length; i++) {
            String marcador = "$[" + (i+1) + "}";
            mensajeReemplazado = mensajeReemplazado.replace(marcador, parametros[i]);
        }
        return mensajeReemplazado;
    }
    
    public static final boolean validarFormatoCorreo(final String valor) {
    	return getDefaultValue(valor).matches(EMAIL_PATTERN);
    }
    
    public static final boolean validarSoloNumeros(final String valor) {
    	return getDefaultValue(valor).matches(DOCUMENT_NUMBER_PATTERN);
    }
    
    public static final boolean longitudMinima ( final String valor, final int longitudMinima) {
    	return applyTrim(valor).length()>=longitudMinima;
    }
    
    public static final boolean longitudMaxima ( final String valor, final int longitudMaxima) {
    	return applyTrim(valor).length()<=longitudMaxima;
    }
    
    public static final boolean validarEspaciosLetrasDigitos(final String valor) {
    	return getDefaultValue(valor).matches(ESPACIOS_LETRAS_DIGITOS);
    }
	
}
