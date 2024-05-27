package co.com.cmdb.business.assembler.dto.impl;

import co.com.cmdb.business.assembler.dto.AssemblerDTO;
import co.com.cmdb.business.domain.TipoDocumentoDomain;
import co.com.cmdb.crosscutting.helpers.ObjectHelper;
import static co.com.cmdb.crosscutting.helpers.ObjectHelper.getObjectHelper;
import java.util.ArrayList;
import java.util.List;
import co.com.cmdb.dto.TipoDocumentoDTO;


public class TipoDocumentoAssemblerDTO implements AssemblerDTO <TipoDocumentoDomain, TipoDocumentoDTO> {

	private static final AssemblerDTO <TipoDocumentoDomain, TipoDocumentoDTO> instance = new TipoDocumentoAssemblerDTO();
	
	private TipoDocumentoAssemblerDTO() {
		
		super();
		
	}
	
	public static final AssemblerDTO<TipoDocumentoDomain, TipoDocumentoDTO> getInstance(){
		
		return instance;
		
		
	}
	@Override
	public TipoDocumentoDomain toDomain(TipoDocumentoDTO data) {
		var tipoDocumentoDtoTmp = getObjectHelper().getDefaultValue(data, TipoDocumentoDTO.build());
		return TipoDocumentoDomain.build(tipoDocumentoDtoTmp.getId());
	}

	@Override
	public TipoDocumentoDTO toDTO(TipoDocumentoDomain domain) {
		var tipoDocumentoDomainTmp = getObjectHelper().getDefaultValue(domain, TipoDocumentoDomain.build());
		// TODO Auto-generated method stub
		return TipoDocumentoDTO.build().setId(tipoDocumentoDomainTmp.getId()).setNombre(tipoDocumentoDomainTmp.getNombre());
	}

	@Override
	public final List<TipoDocumentoDTO> toDTOCollection(final List<TipoDocumentoDomain>  domainCollection) {
		var domainCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(domainCollection, new ArrayList<TipoDocumentoDomain>());
		
		return domainCollectionTmp.stream().map(this::toDTO).toList();
	}
	
	

}
