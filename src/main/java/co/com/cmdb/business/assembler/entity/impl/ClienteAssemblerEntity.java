package co.com.cmdb.business.assembler.entity.impl;

import co.com.cmdb.business.assembler.entity.AssemblerEntity;
import co.com.cmdb.business.domain.ClienteDomain;
import co.com.cmdb.business.domain.TipoDocumentoDomain;
import co.com.cmdb.entity.ClienteEntity;
import co.com.cmdb.entity.TipoDocumentoEntity;


public class ClienteAssemblerEntity implements AssemblerEntity<ClienteDomain, ClienteEntity> {

	private static final AssemblerEntity<ClienteDomain, ClienteEntity> instance = new ClienteAssemblerEntity();
	
	private static final AssemblerEntity<TipoDocumentoDomain,TipoDocumentoEntity> TipoDocumentoAssembler = TipoDocumentoAssemblerEntity.getInstance();
	
	
	protected ClienteAssemblerEntity() {
		
		super();
		
	}
	
	public static final AssemblerEntity <ClienteDomain, ClienteEntity> getInstance() {
		
		return instance;
		
	}
	@Override
	public ClienteDomain toDomain(ClienteEntity data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClienteEntity toEntity(ClienteDomain domain) {
		// TODO Auto-generated method stub
		return null;
	}


}
