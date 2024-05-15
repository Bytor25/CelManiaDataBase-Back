package co.com.cmdb.crosscutting.helpers;

public final class ObjectHelper {
	
	private static final ObjectHelper INSTANCE = new ObjectHelper();

	private ObjectHelper() {
		super();
	}
	
	public static final ObjectHelper getObjectHelper() {
		return INSTANCE;
	}
	
	public <any> boolean isNull(any object) {
		return object == null;
	}
	
	public <any> any getDefaultValue(any object, any defaultValue) {
		return this.isNull(object) ? defaultValue : object;
	}
	
}
