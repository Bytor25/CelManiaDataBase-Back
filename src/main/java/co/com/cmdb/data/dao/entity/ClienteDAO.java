package co.com.cmdb.data.dao.entity;

import co.com.cmdb.entity.ClienteEntity;

public interface ClienteDAO extends CreateDAO<ClienteEntity>, RetrieveDAO<ClienteEntity>, UpdateDAO<ClienteEntity> {
	
	ClienteEntity consultarPorid(String numeroDocumento);
	
	boolean existeCorreo(String correo, String numeroDocumento);
	
	boolean existeTelefono(long telefono, String numeroDocumento);
	
	
}
