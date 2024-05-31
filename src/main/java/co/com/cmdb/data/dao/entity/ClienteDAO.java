package co.com.cmdb.data.dao.entity;

import java.util.Optional;
import java.util.UUID;

import co.com.cmdb.entity.ClienteEntity;

public interface ClienteDAO extends CreateDAO<ClienteEntity>, RetrieveDAO<ClienteEntity>, UpdateDAO<ClienteEntity>, DeleteDAO<UUID> {
	
		Optional<ClienteEntity> consultarPorId(UUID id);
}
