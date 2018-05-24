package it.eng.cepmiddleware;

import java.util.Collection;

public interface CRUDService<T, ID> {

	public void Create(T entity);
	public T Read(ID entityId);
	public Collection<T> Read();
	public T Update(T entity);
	public void Delete(ID entityId);

}
