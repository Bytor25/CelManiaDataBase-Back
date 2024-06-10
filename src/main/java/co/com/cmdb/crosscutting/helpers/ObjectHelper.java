package co.com.cmdb.crosscutting.helpers;

public class ObjectHelper {
	
	private static final ObjectHelper INSTANCE = new ObjectHelper();

	private ObjectHelper() {
		super();
	}
	
	public static final ObjectHelper getObjectHelper() {
		return INSTANCE;
	}
	
	public <any> boolean isNull(final any object) {
		return object == null;
	}
	
	public <any> any getDefaultValue(final any object, final any defaultValue) {
		return isNull(object) ? defaultValue : object;
	}

	
}
