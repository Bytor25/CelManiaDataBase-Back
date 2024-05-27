package co.com.cmdb.business.assembler.dto;

import co.com.cmdb.business.assembler.Assembler;

public interface AssemblerDTO <D, K> extends Assembler <D, K> {
	
	K toDTO(D domain);

}
