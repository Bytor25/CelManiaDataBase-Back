package co.com.cmdb.data.dao.entity;

import java.util.List;
import java.util.UUID;

import co.com.cmdb.entity.ProveedorEntity;

public interface ProveedorDAO extends CreateDAO<ProveedorEntity>, RetrieveDAO<ProveedorEntity>, UpdateDAO<ProveedorEntity>{
	
	boolean existeTelefono(long telefono, String numeroDocumento);
	
	List<ProveedorEntity> consultarPorNumeroDocumento(String numeroDocumento);
	
	ProveedorEntity consultarPorNumeroDocumentoTipoDocumento(String numeroDocumento, int identificadorDocumento, UUID identificador);
	
	
}
