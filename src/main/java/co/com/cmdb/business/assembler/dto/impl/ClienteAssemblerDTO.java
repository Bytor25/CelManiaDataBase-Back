package co.com.cmdb.business.assembler.dto.impl;

import co.com.cmdb.business.assembler.dto.AssemblerDTO;
import co.com.cmdb.business.domain.ClienteDomain;
import co.com.cmdb.dto.ClienteDTO;

public final class ClienteAssemblerDTO implements AssemblerDTO<ClienteDomain, ClienteDTO> {
	
	private static final AssemblerDTO <ClienteDomain, ClienteDTO> instance = new ClienteAssemblerDTO();

	
	private ClienteAssemblerDTO() {
		
		super();
		
	}
	
	public static final AssemblerDTO <ClienteDomain, ClienteDTO> getInstance() {
		
		return instance;
		
	}
	@Override
	public ClienteDomain toDomain(ClienteDTO data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClienteDTO toDTO(ClienteDomain domain) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
