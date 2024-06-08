
package co.com.cmdb.data.dao.entity.concrete.postgresSql;

import java.sql.Connection;

import java.util.List;


import co.com.cmdb.data.dao.entity.TipoDocumentoDAO;
import co.com.cmdb.data.dao.entity.concrete.SqlConnection;
import co.com.cmdb.entity.TipoDocumentoEntity;

public class TipoDocumentoPostgresSqlDAO extends SqlConnection implements TipoDocumentoDAO{
	
	public TipoDocumentoPostgresSqlDAO(final Connection conexion) {
		
		super(conexion);

	}

	public List<TipoDocumentoEntity> consultar(TipoDocumentoEntity data){
		
		return null;
		
	}

}
