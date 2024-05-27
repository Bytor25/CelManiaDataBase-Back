package co.com.cmdb.business.facade;

public interface FacadeWithReturn<P, H> {
	
	H excute(P dto);

}
