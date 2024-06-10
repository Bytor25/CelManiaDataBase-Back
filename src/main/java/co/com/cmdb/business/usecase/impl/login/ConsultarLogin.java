package co.com.cmdb.business.usecase.impl.login;

import java.util.List;

import co.com.cmdb.business.assembler.entity.impl.LoginAssemblerEntity;
import co.com.cmdb.business.domain.LoginDomain;
import co.com.cmdb.business.usecase.UseCaseWithReturn;
import co.com.cmdb.crosscutting.exceptions.custom.BusinessCMDBException;
import co.com.cmdb.crosscutting.helpers.ObjectHelper;
import co.com.cmdb.data.dao.factory.DAOFactory;

public class ConsultarLogin implements UseCaseWithReturn<LoginDomain, List<LoginDomain>>{
	
	private DAOFactory factory;
	
	public ConsultarLogin(final DAOFactory factory) {
		
		if(ObjectHelper.getObjectHelper().isNull(factory)) {
			
			var mensajeUsuario = ""; //Msg6
			var mensajeTecnico = ""; //Msg9
			
			throw new BusinessCMDBException(mensajeUsuario, mensajeTecnico);
			
		}
		
		this.factory = factory;
		
	}
	
	

	@Override
	public List<LoginDomain> execute(LoginDomain data) {
		
		var loginEntityFilter = LoginAssemblerEntity.getInstance().toEntity(data);
		var resultadosEntity = factory.getLoginDAO().consultar(loginEntityFilter);
		
		return LoginAssemblerEntity.getInstance().toDomainCollection(resultadosEntity);
	
	}

}
