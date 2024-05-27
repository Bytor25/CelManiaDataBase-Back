package co.com.cmdb.business.usecase;

public interface UseCaseWithReturn <T,R>{
	
	R execute (T data);

}
