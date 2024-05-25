package co.com.cmdb.data.dao.entity;

import java.util.UUID;

import co.com.cmdb.entity.ClienteEntity;

public interface ClienteDAO extends CreateDAO<ClienteEntity>, RetrieveDAO<ClienteEntity>, UpdateDAO<ClienteEntity>, DeleteDAO<UUID> {

}
