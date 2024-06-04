package co.com.cmdb.crosscutting.helpers;

public class BooleanHelper {

    private BooleanHelper() {
        super();
    }

    public static final boolean DEFAULT_BOOLEAN = false;


    public static final boolean isNull(final Boolean valor) {
        return ObjectHelper.getObjectHelper().isNull(valor);
    }

    public static final boolean getDefaultValue(final Boolean valor) {
        return isNull(valor) || valor.equals(DEFAULT_BOOLEAN);
    }

    public static final boolean parseBooleanOrDefault(final String str, final boolean defaultValue) {
        if (str == null) {
            return defaultValue;
        }
        return Boolean.parseBoolean(str);
    }

    public static final boolean isTrue(final Boolean valor) {
        return Boolean.TRUE.equals(valor);
    }

    public static final boolean isFalse(final Boolean valor) {
        return Boolean.FALSE.equals(valor);
    }
}