package co.com.cmdb.business.assembler.entity;

import java.util.List;

import co.com.cmdb.business.assembler.Assembler;

public interface AssemblerEntity <D, K> extends Assembler <D, K> {
	
	K toEntity(D domain);
	
	List<K> toEntityCollection(List<D> domainCollection);
}
