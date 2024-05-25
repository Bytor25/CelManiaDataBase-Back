package co.com.cmdb.data.dao.entity.concrete.azuresql;

import java.sql.Connection;
import java.util.List;

import co.com.cmdb.data.dao.entity.TipoDocumentoDAO;
import co.com.cmdb.data.dao.entity.concrete.SqlConnection;
import co.com.cmdb.entity.ClienteEntity;
import co.com.cmdb.entity.TipoDocumentoEntity;

public class TipoDocumentoAzureSqlDAO extends SqlConnection implements TipoDocumentoDAO{
	
	public TipoDocumentoAzureSqlDAO(final Connection conexion) {
		
		super(conexion);

	}
	
	public List<TipoDocumentoEntity> consultar(TipoDocumentoEntity data){
		
		return null;
		
	}

	@Override
	public List<ClienteEntity> consultar(ClienteEntity data) {
		// TODO Auto-generated method stub
		return null;
	}

}
