package co.com.cmdb.business.assembler.dto.impl;

import static co.com.cmdb.crosscutting.helpers.ObjectHelper.getObjectHelper;

import java.util.ArrayList;
import java.util.List;

import co.com.cmdb.business.assembler.dto.AssemblerDTO;
import co.com.cmdb.business.domain.ClienteDomain;
import co.com.cmdb.business.domain.TipoDocumentoDomain;
import co.com.cmdb.crosscutting.helpers.ObjectHelper;
import co.com.cmdb.dto.ClienteDTO;
import co.com.cmdb.dto.TipoDocumentoDTO;

public final class ClienteAssemblerDTO implements AssemblerDTO<ClienteDomain, ClienteDTO> {
	
	private static final AssemblerDTO <ClienteDomain, ClienteDTO> instance = new ClienteAssemblerDTO();
	private static final AssemblerDTO<TipoDocumentoDomain, TipoDocumentoDTO> tipoDocumentoAssembler = TipoDocumentoAssemblerDTO.getInstance();

	
	private ClienteAssemblerDTO() {
		super();
	}
	
	public static final AssemblerDTO <ClienteDomain, ClienteDTO> getInstance() {
		
		return instance;
		
	}
	
	@Override
	public final ClienteDomain toDomain(final ClienteDTO data) {
		var clienteDtoTmp = getObjectHelper().getDefaultValue(data, ClienteDTO.build());
		var tipoDocumentoDomain = tipoDocumentoAssembler.toDomain(clienteDtoTmp.getTipoDocumento());
		return ClienteDomain.build(clienteDtoTmp.getIdentificador(),tipoDocumentoDomain,clienteDtoTmp.getNombre(),clienteDtoTmp.getApellido(),clienteDtoTmp.getCorreo(),clienteDtoTmp.getTelefono(),clienteDtoTmp.isEstado());
	}

	@Override
	public ClienteDTO toDTO(final ClienteDomain domain) {
		var clienteDomainTmp = getObjectHelper().getDefaultValue(domain, ClienteDomain.build());
		var tipoDocumentoDTO = tipoDocumentoAssembler.toDTO(clienteDomainTmp.getTipoDocumento());
		return ClienteDTO.build().setIdentificador(clienteDomainTmp.getIdentificador()).setTipoDocumento(tipoDocumentoDTO).setNombre(clienteDomainTmp.getNombre()).setApellido(clienteDomainTmp.getApellido()).setCorreo(clienteDomainTmp.getCorreo()).setTelefono(clienteDomainTmp.getTelefono());
	}
	
	@Override
	public List<ClienteDomain> toDomainCollection(List<ClienteDTO> dtoCollection) {
		var dtoCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(dtoCollection, new ArrayList<ClienteDTO>());
		var resultadosDomain = new ArrayList<ClienteDomain>();
		
		for (ClienteDTO clienteDTO : dtoCollectionTmp) {
			var clienteDomainTmp = toDomain(clienteDTO);
			resultadosDomain.add(clienteDomainTmp);
		}
		
		return resultadosDomain;
	}

	@Override
	public final List<ClienteDTO> toDTOCollection(final List<ClienteDomain>  domainCollection) {
		var domainCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(domainCollection, new ArrayList<ClienteDomain>());
		
		return domainCollectionTmp.stream().map(this::toDTO).toList();
	}


	
	}
