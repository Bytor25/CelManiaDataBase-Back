package co.com.cmdb.business.assembler.entity.impl;

import co.com.cmdb.business.assembler.entity.AssemblerEntity;
import co.com.cmdb.business.domain.TipoDocumentoDomain;
import co.com.cmdb.entity.TipoDocumentoEntity;

public final class TipoDocumentoAssemblerEntity implements AssemblerEntity<TipoDocumentoDomain, TipoDocumentoEntity> {

	private static final AssemblerEntity <TipoDocumentoDomain, TipoDocumentoEntity> instance = new TipoDocumentoAssemblerEntity();
	
	public TipoDocumentoAssemblerEntity() {

		super();
		
	}
	
	public static final AssemblerEntity <TipoDocumentoDomain, TipoDocumentoEntity > getInstance() {
		
		return instance;

	}
	@Override
	public final TipoDocumentoDomain toDomain(TipoDocumentoEntity data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TipoDocumentoEntity toEntity(TipoDocumentoDomain domain) {
		// TODO Auto-generated method stub
		return null;
	}

	
	

	
	

}
