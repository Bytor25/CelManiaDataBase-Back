package co.com.cmdb.business.usecase;

public interface UseCaseWithoutReturn <T> {
	
	void execute (T data);

}
