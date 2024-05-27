package co.com.cmdb.business.assembler.dto.impl;

import co.com.cmdb.business.assembler.dto.AssemblerDTO;
import co.com.cmdb.business.domain.TipoDocumentoDomain;
import static co.com.cmdb.crosscutting.helpers.ObjectHelper.getObjectHelper;
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
	public final TipoDocumentoDomain toDomain(final TipoDocumentoDTO data) {
		var tipoDocumentoDtoTmp = getObjectHelper().getDefaultValue(data, TipoDocumentoDTO.build());
		return TipoDocumentoDomain.build(tipoDocumentoDtoTmp.getId(), tipoDocumentoDtoTmp.getNombre());
	}

	@Override
	public final TipoDocumentoDTO toDTO(final TipoDocumentoDomain domain) {
		//var tipoDocumentoDomainTmp = getObjectHelper().getDefaultValue(domain, TipoDocumentoDomain.build());
		//return TipoDocumentoDTO.build().setId(tipoDocumentoDomainTmp.getId());
		
		return null;
	}
	
	

}
