package co.com.cmdb.data.dao.entity;

import co.com.cmdb.entity.ProveedorEntity;

public interface ProveedorDAO extends CreateDAO<ProveedorEntity>, RetrieveDAO<ProveedorEntity>, UpdateDAO<ProveedorEntity>{
	
	boolean existeTelefono(long telefono, String numeroDocumento);
	
}
