package co.com.cmdb.business.assembler.dto;

import java.util.List;

import co.com.cmdb.business.assembler.Assembler;

public interface AssemblerDTO <D, K> extends Assembler <D, K> {
	
	K toDTO(D domain);
	
	List<K> toDTOCollection(List<D> domainCollection);

}
