package co.com.cmdb.data.dao.entity;

import java.util.List;

interface RetrieveDAO<E> {
	
	List<E> consultar(E data);

}
