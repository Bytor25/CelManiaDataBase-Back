package co.com.cmdb.business.assembler.dto.impl;

import static co.com.cmdb.crosscutting.helpers.ObjectHelper.getObjectHelper;

import java.util.ArrayList;
import java.util.List;

import co.com.cmdb.business.assembler.dto.AssemblerDTO;
import co.com.cmdb.business.domain.ProveedorDomain;
import co.com.cmdb.business.domain.TipoDocumentoDomain;
import co.com.cmdb.crosscutting.helpers.ObjectHelper;
import co.com.cmdb.dto.ProveedorDTO;
import co.com.cmdb.dto.TipoDocumentoDTO;

public final class ProveedorAssemblerDTO implements AssemblerDTO<ProveedorDomain, ProveedorDTO> {
	
	private static final AssemblerDTO <ProveedorDomain, ProveedorDTO> instance = new ProveedorAssemblerDTO();
	private static final AssemblerDTO<TipoDocumentoDomain, TipoDocumentoDTO> tipoDocumentoAssembler = TipoDocumentoAssemblerDTO.getInstance();

	private ProveedorAssemblerDTO() {
		super();
	}
	
	public static final AssemblerDTO <ProveedorDomain, ProveedorDTO> getInstance() {
		
		return instance;
		  
	}
	@Override
	public ProveedorDomain toDomain(final ProveedorDTO data) {
		
		var proveedorDtoTmp = getObjectHelper().getDefaultValue(data, ProveedorDTO.build());
		var tipoDocumentoDomain = tipoDocumentoAssembler.toDomain(proveedorDtoTmp.getTipoDocumento());
		return ProveedorDomain.build(proveedorDtoTmp.getIdentificador(),proveedorDtoTmp.getNumeroDocumento(),
				tipoDocumentoDomain,proveedorDtoTmp.getNombre(),proveedorDtoTmp.getTelefono(),proveedorDtoTmp.isEstado());
		
	}
	
	@Override
	public ProveedorDTO toDTO(final ProveedorDomain domain) {
		var proveedorDomainTmp = getObjectHelper().getDefaultValue(domain, ProveedorDomain.build());
		var tipoDocumentoDTO = tipoDocumentoAssembler.toDTO(proveedorDomainTmp.getTipoDocumento());
		return ProveedorDTO.build().setIdentificador(proveedorDomainTmp.getIdentificador()).setNumeroDocumento(proveedorDomainTmp.getNumeroDocumento()).setTipoDocumento(tipoDocumentoDTO).setNombre(proveedorDomainTmp.getNombre()).setTelefono(proveedorDomainTmp.getTelefono()).setEstado(proveedorDomainTmp.isEstado());

	}

	@Override
	public List<ProveedorDomain> toDomainCollection(final List<ProveedorDTO> dtoCollection) {
		
		var dtoCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(dtoCollection, new ArrayList<ProveedorDTO>());
		var resultadosDomain = new ArrayList<ProveedorDomain>();
		
		for (ProveedorDTO proveedorDTO : dtoCollectionTmp) {
			var proveedorDomainTmp = toDomain(proveedorDTO);
			resultadosDomain.add(proveedorDomainTmp);
		}
		
		return resultadosDomain;
	}	
	

	@Override
	public final List<ProveedorDTO> toDTOCollection(final List<ProveedorDomain>  domainCollection) {
		var domainCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(domainCollection, new ArrayList<ProveedorDomain>());
		
		return domainCollectionTmp.stream().map(this::toDTO).toList();
	}

}
