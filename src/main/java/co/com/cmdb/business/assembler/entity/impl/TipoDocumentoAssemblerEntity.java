package co.com.cmdb.business.assembler.entity.impl;

import static co.com.cmdb.crosscutting.helpers.ObjectHelper.getObjectHelper;

import java.util.ArrayList;
import java.util.List;

import co.com.cmdb.business.assembler.entity.AssemblerEntity;
import co.com.cmdb.business.domain.TipoDocumentoDomain;
import co.com.cmdb.crosscutting.helpers.ObjectHelper;
import co.com.cmdb.entity.TipoDocumentoEntity;

public final class TipoDocumentoAssemblerEntity implements AssemblerEntity<TipoDocumentoDomain, TipoDocumentoEntity> {

	private static final  AssemblerEntity<TipoDocumentoDomain, TipoDocumentoEntity> instance = new TipoDocumentoAssemblerEntity();
	
	private TipoDocumentoAssemblerEntity() {
		super();
	}
	
	public static final AssemblerEntity<TipoDocumentoDomain, TipoDocumentoEntity> getInstance(){
		return instance;
	}
	@Override
	public TipoDocumentoDomain toDomain(final TipoDocumentoEntity data) {
		var tipoDocumentoEntityTmp = getObjectHelper().getDefaultValue(data, TipoDocumentoEntity.build());
		return TipoDocumentoDomain.build(data.getIdentificador(), tipoDocumentoEntityTmp.getNombre());
	}

	@Override
	public TipoDocumentoEntity toEntity(final TipoDocumentoDomain domain) {
		var tipoDocumentoDomainTmp = getObjectHelper().getDefaultValue(domain, TipoDocumentoDomain.build());
		return  TipoDocumentoEntity.build(tipoDocumentoDomainTmp.getIdentificador(), tipoDocumentoDomainTmp.getNombre());
	}

	@Override
	public List<TipoDocumentoDomain> toDomainCollection(List<TipoDocumentoEntity> entityCollection) {
        var entityCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(entityCollection, new ArrayList<TipoDocumentoEntity>());

        var resultadosDomain = new ArrayList<TipoDocumentoDomain>();

        for (TipoDocumentoEntity tipoDocumentoEntity : entityCollectionTmp) {
            var tipoDocumentoDomainTmp = toDomain(tipoDocumentoEntity);
            resultadosDomain.add(tipoDocumentoDomainTmp);
        }
        return resultadosDomain;
	}

	@Override
	public List<TipoDocumentoEntity> toEntityCollection(List<TipoDocumentoDomain> domainCollection) {
        var domainCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(domainCollection, new ArrayList<TipoDocumentoDomain>());
        return domainCollectionTmp.stream().map(this::toEntity).toList();
	}

	
	

	
	

}
