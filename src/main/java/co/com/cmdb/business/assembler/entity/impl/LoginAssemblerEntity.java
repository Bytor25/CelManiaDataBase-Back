package co.com.cmdb.business.assembler.entity.impl;

import static co.com.cmdb.crosscutting.helpers.ObjectHelper.getObjectHelper;

import java.util.ArrayList;
import java.util.List;

import co.com.cmdb.business.assembler.entity.AssemblerEntity;
import co.com.cmdb.business.domain.LoginDomain;
import co.com.cmdb.crosscutting.helpers.ObjectHelper;
import co.com.cmdb.entity.LoginEntity;

public class LoginAssemblerEntity implements AssemblerEntity<LoginDomain, LoginEntity> {

	private static final AssemblerEntity<LoginDomain, LoginEntity> instance = new LoginAssemblerEntity();
	
	private LoginAssemblerEntity() {
		super();
	}

	public static final AssemblerEntity<LoginDomain, LoginEntity> getInstance(){
		return instance;
	}

	@Override
	public LoginDomain toDomain(final LoginEntity data) {
		var loginEntityTmp = getObjectHelper().getDefaultValue(data, LoginEntity.build());
		
		return LoginDomain.build(loginEntityTmp.getUsuario(), loginEntityTmp.getPassword(), loginEntityTmp.getEstado());
	}
	
	@Override
	public final LoginEntity toEntity(final LoginDomain domain) {
		var loginDomainTmp = getObjectHelper().getDefaultValue(domain, LoginDomain.build());
		return LoginEntity.build().setUsuario(loginDomainTmp.getUsuario()).setPassword(loginDomainTmp.getPassword()).setEstado(loginDomainTmp.isEstado());
	}

	@Override
	public final List<LoginDomain> toDomainCollection(List<LoginEntity> entityCollection) {
		var entityCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(entityCollection, new ArrayList<LoginEntity>());

        var resultadosDomain = new ArrayList<LoginDomain>();

        for (LoginEntity loginEntity : entityCollectionTmp) {
            var loginDomainTmp = toDomain(loginEntity);
            resultadosDomain.add(loginDomainTmp);
        }
        return resultadosDomain;
	}

	@Override
	public List<LoginEntity> toEntityCollection(List<LoginDomain> domainCollection) {
		var domainCollectionTmp = ObjectHelper.getObjectHelper().getDefaultValue(domainCollection, new ArrayList<LoginDomain>());
		return domainCollectionTmp.stream().map(this::toEntity).toList();
	}
	
 

}
