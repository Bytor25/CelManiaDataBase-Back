package co.com.cmdb.business.assembler.entity.impl;

import static co.com.cmdb.crosscutting.helpers.ObjectHelper.getObjectHelper;

import java.util.ArrayList;
import java.util.List;

import co.com.cmdb.business.assembler.entity.AssemblerEntity;
import co.com.cmdb.business.domain.ProveedorDomain;
import co.com.cmdb.business.domain.TipoDocumentoDomain;
import co.com.cmdb.crosscutting.helpers.ObjectHelper;
import co.com.cmdb.entity.ProveedorEntity;
import co.com.cmdb.entity.TipoDocumentoEntity;

public class ProveedorAssemblerEntity implements AssemblerEntity<ProveedorDomain, ProveedorEntity> {

	private static final AssemblerEntity<TipoDocumentoDomain, TipoDocumentoEntity> tipoDocumentoAssembler = TipoDocumentoAssemblerEntity.getInstance();
	private static final AssemblerEntity<ProveedorDomain, ProveedorEntity> instance = new ProveedorAssemblerEntity();
	
	private ProveedorAssemblerEntity() {
		super();
	}
	
	public static final AssemblerEntity<ProveedorDomain, ProveedorEntity> getInstance(){
		return instance;
	}
	@Override
	public ProveedorDomain toDomain(final ProveedorEntity data) {
		
		var proveedorEntityTmp = getObjectHelper().getDefaultValue(data, ProveedorEntity.build());
		var tipoDocumentoDomain = tipoDocumentoAssembler.toDomain(proveedorEntityTmp.getTipoDocumento());
		
		return ProveedorDomain.build(proveedorEntityTmp.getIdentificador(),proveedorEntityTmp.getNumeroDocumento(),tipoDocumentoDomain,
				proveedorEntityTmp.getNombre(),proveedorEntityTmp.getTelefono(),
				proveedorEntityTmp.isEstado());
		
	}
	
	@Override
	public ProveedorEntity toEntity(ProveedorDomain domain) {
		var proveedorDomainTmp = getObjectHelper().getDefaultValue(domain, ProveedorDomain.build());
		var tipoDocumentoEntity = tipoDocumentoAssembler.toEntity(proveedorDomainTmp.getTipoDocumento());
		
		return ProveedorEntity.build().setIdentificador(proveedorDomainTmp.getIdentificador()).setNombre(proveedorDomainTmp.getNombre())
				.setTipoDocumento(tipoDocumentoEntity);
	}

	@Override
	public List<ProveedorDomain> toDomainCollection(final List<ProveedorEntity> entityCollection) {
		
		var entityCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(entityCollection, new ArrayList<ProveedorEntity>());

        var resultadosDomain = new ArrayList<ProveedorDomain>();

        for (ProveedorEntity proveedorEntity : entityCollectionTmp) {
            var proveedorDomainTmp = toDomain(proveedorEntity);
            resultadosDomain.add(proveedorDomainTmp);
        }
        
        return resultadosDomain;
	}

	@Override
	public List<ProveedorEntity> toEntityCollection(List<ProveedorDomain> domainCollection) {
		var domainCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(domainCollection, new ArrayList<ProveedorDomain>());
		return domainCollectionTmp.stream().map(this::toEntity).toList();
	}

}
