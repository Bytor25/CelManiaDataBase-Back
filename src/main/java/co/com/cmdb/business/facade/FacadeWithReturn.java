package co.com.cmdb.business.facade;

public interface FacadeWithReturn<P, H> {
	
	H execute(P dto);

}
