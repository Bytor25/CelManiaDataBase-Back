package co.com.cmdb.business.assembler;

import java.util.List;

public interface Assembler <D, K> {
	
	D toDomain(K data);
	
	List <D> toDomainCollection(List<K> entityCollection);

}