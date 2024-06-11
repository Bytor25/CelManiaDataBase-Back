package co.com.cmdb.crosscutting.helpers;

public class LongHelper {

	private LongHelper() {
		super();
	}
	
	public static final long DEFAULT_LONG = 0000000000L;
    private static final long MIN_PHONE_NUMBER = 3000000000L;
    private static final long MAX_PHONE_NUMBER = 3999999999L;

	public static final boolean isNull(final Long valor) {
		return ObjectHelper.getObjectHelper().isNull(valor);
	}
	
	public static final Long getDefaultValue(final Long valor) {
		return isNull(valor) ? DEFAULT_LONG : valor ;
	}
	
    public static final boolean validarRango(final Long valor) {
        if (isNull(valor)) {
            return false;
        }
        return valor >= MIN_PHONE_NUMBER && valor <= MAX_PHONE_NUMBER;
    }
}
