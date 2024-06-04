package co.com.cmdb.business.assembler.entity.impl;

import static co.com.cmdb.crosscutting.helpers.ObjectHelper.getObjectHelper;

import java.util.ArrayList;
import java.util.List;

import co.com.cmdb.business.assembler.entity.AssemblerEntity;
import co.com.cmdb.business.domain.TipoDocumentoDomain;
import co.com.cmdb.crosscutting.helpers.ObjectHelper;
import co.com.cmdb.entity.TipoDocumentoEntity;

public final class TipoDocumentoAssemblerEntity implements AssemblerEntity<TipoDocumentoDomain, TipoDocumentoEntity> {

private final static AssemblerEntity<TipoDocumentoDomain, TipoDocumentoEntity> instance = new TipoDocumentoAssemblerEntity();
	
	private TipoDocumentoAssemblerEntity() {
		super();
	}
	
	public static final AssemblerEntity<TipoDocumentoDomain, TipoDocumentoEntity> getInstance(){
		return instance;
	}
	@Override
	public TipoDocumentoDomain toDomain(TipoDocumentoEntity data) {
		var tipoDocumentoEntityTmp = getObjectHelper().getDefaultValue(data, TipoDocumentoEntity.build());
		return TipoDocumentoDomain.build(data.getIdentificador(), tipoDocumentoEntityTmp.getNombre());
	}

	@Override
	public TipoDocumentoEntity toEntity(TipoDocumentoDomain domain) {
		var tipoDocumentoDomainTmp = getObjectHelper().getDefaultValue(domain, TipoDocumentoDomain.build());
		return  TipoDocumentoEntity.build(tipoDocumentoDomainTmp.getIdentificador(), tipoDocumentoDomainTmp.getNombre());
	}

	@Override
	public List<TipoDocumentoDomain> toDomainCollection(List<TipoDocumentoEntity> entityCollection) {
		// TODO Auto-generated method stub
		var entityCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(entityCollection, new ArrayList<TipoDocumentoEntity>());
		return entityCollectionTmp.stream().map(this::toDomain).toList();
	}

	
	

	
	

}
