package co.com.cmdb.business.assembler.dto.impl;

import static co.com.cmdb.crosscutting.helpers.ObjectHelper.getObjectHelper;

import java.util.ArrayList;
import java.util.List;

import co.com.cmdb.business.assembler.dto.AssemblerDTO;
import co.com.cmdb.business.domain.LoginDomain;
import co.com.cmdb.crosscutting.helpers.ObjectHelper;
import co.com.cmdb.dto.LoginDTO;

public class LoginAssemblerDTO implements AssemblerDTO<LoginDomain, LoginDTO>{
	
	private static final AssemblerDTO <LoginDomain, LoginDTO> instance = new LoginAssemblerDTO();
	
	private LoginAssemblerDTO() {
		
		super();
		
	}
	
	public static final AssemblerDTO <LoginDomain, LoginDTO> getInstance() {
		
		return instance;
		  
	}

	@Override
	public final LoginDomain toDomain(final LoginDTO data) {
		var loginDtoTmp = getObjectHelper().getDefaultValue(data, LoginDTO.build());
		return LoginDomain.build(loginDtoTmp.getUsuario(),loginDtoTmp.getPassword(),loginDtoTmp.isEstado());
	}
	
	@Override
	public final LoginDTO toDTO(final LoginDomain domain) {
		var loginDomainTmp = getObjectHelper().getDefaultValue(domain, LoginDomain.build());
		return LoginDTO.build().setUsuario(loginDomainTmp.getUsuario()).setPassword(loginDomainTmp.getPassword()).setEstado(loginDomainTmp.isEstado());
	}

	@Override
	public final List<LoginDomain> toDomainCollection(final List<LoginDTO> dtoCollection) {
		var dtoCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(dtoCollection, new ArrayList<LoginDTO>());
		var resultadosDomain = new ArrayList<LoginDomain>();
		
		for (LoginDTO loginDTO : dtoCollectionTmp) {
			var loginDomainTmp = toDomain(loginDTO);
			resultadosDomain.add(loginDomainTmp);
		}
		
		return resultadosDomain;
	}

	@Override
	public final List<LoginDTO> toDTOCollection(List<LoginDomain> domainCollection) {
		var domainCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(domainCollection, new ArrayList<LoginDomain>());
		
		return domainCollectionTmp.stream().map(this::toDTO).toList();
	}
}


