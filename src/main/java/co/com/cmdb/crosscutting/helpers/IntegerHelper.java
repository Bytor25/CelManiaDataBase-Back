package co.com.cmdb.crosscutting.helpers;

public class IntegerHelper {

    private IntegerHelper() {
        super();
    }

    public static final int DEFAULT_INT = 0;

    public static final boolean isNull(final Integer valor) {
        return ObjectHelper.getObjectHelper().isNull(valor);
    }

    public static final Integer getDefaultValue(final Integer valor) {
        return isNull(valor) ? DEFAULT_INT : valor;
    }
}
