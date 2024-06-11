package co.com.cmdb.data.dao.entity;

import co.com.cmdb.entity.LoginEntity;

public interface LoginDAO extends RetrieveDAO<LoginEntity>{
	
	boolean validarUsuario(String usuario, int password);

}
