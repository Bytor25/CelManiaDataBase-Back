package co.com.cmdb.business.assembler.entity.impl;

import static co.com.cmdb.crosscutting.helpers.ObjectHelper.getObjectHelper;

import java.util.ArrayList;
import java.util.List;

import co.com.cmdb.business.assembler.entity.AssemblerEntity;
import co.com.cmdb.business.domain.ClienteDomain;
import co.com.cmdb.business.domain.TipoDocumentoDomain;
import co.com.cmdb.entity.ClienteEntity;
import co.com.cmdb.entity.TipoDocumentoEntity;
import co.com.cmdb.crosscutting.helpers.ObjectHelper;



public class ClienteAssemblerEntity implements AssemblerEntity<ClienteDomain, ClienteEntity> {

	private static final AssemblerEntity<TipoDocumentoDomain, TipoDocumentoEntity> tipoDocumentoAssembler = TipoDocumentoAssemblerEntity.getInstance();
	private static final AssemblerEntity<ClienteDomain, ClienteEntity> instance = new ClienteAssemblerEntity();
	
	private ClienteAssemblerEntity() {
		super();
	}

	public static final AssemblerEntity<ClienteDomain, ClienteEntity> getInstance(){
		return instance;
	}
	@Override
	public ClienteDomain toDomain(ClienteEntity data) {
		var clienteEntityTmp = getObjectHelper().getDefaultValue(data, ClienteEntity.build());
		var tipoDocumentoDomain = tipoDocumentoAssembler.toDomain(clienteEntityTmp.getTipoDocumento());
		return ClienteDomain.build(clienteEntityTmp.getIdentificador(),clienteEntityTmp.getNombre(),tipoDocumentoDomain);
	}

	@Override
	public ClienteEntity toEntity(ClienteDomain domain) {
		var clienteDomainTmp = getObjectHelper().getDefaultValue(domain, ClienteDomain.build());
		var tipoDocumentoEntity = tipoDocumentoAssembler.toEntity(clienteDomainTmp.getTipoDocumento());
		return ClienteEntity.build().setIdentificador(clienteDomainTmp.getIdentificador()).setNombre(clienteDomainTmp.getNombre()).setTipoDocumento(tipoDocumentoEntity);
	}

	@Override
	public final List<ClienteDomain> toDomainCollection( final List<ClienteEntity> entityCollection) {
		var entityCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(entityCollection, new ArrayList<ClienteEntity>());
		return entityCollectionTmp.stream().map(this::toDomain).toList();
	}


}
