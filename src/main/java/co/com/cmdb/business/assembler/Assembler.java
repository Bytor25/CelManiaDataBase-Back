package co.com.cmdb.business.assembler;

public interface Assembler <D, K> {
	
	D toDomain(K data);

}
