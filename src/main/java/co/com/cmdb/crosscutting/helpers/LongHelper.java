package co.com.cmdb.crosscutting.helpers;

public class LongHelper {

	private LongHelper() {
		super();
	}
	
	public static final long DEFAULT_LONG = 0000000000L;

	public static final boolean isNull(final Long valor) {
		return ObjectHelper.getObjectHelper().isNull(valor);
	}
	
	public static final boolean getDefaultValue(final Long valor) {
		return isNull(valor) || valor.equals(valor);
	}
}
