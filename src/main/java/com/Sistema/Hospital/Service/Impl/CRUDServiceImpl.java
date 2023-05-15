package com.Sistema.Hospital.Service.Impl;

import java.util.List;

import com.Sistema.Hospital.Repository.IGENERICRepository;
import com.Sistema.Hospital.Service.ICRUDService;

public abstract class CRUDServiceImpl<T, ID> implements ICRUDService<T, ID> {

	protected abstract IGENERICRepository<T, ID> getRepo();
	
	@Override
	public T create(T t) throws Exception{
		return getRepo().save(t);
	}

	@Override
	public List<T> getAll() throws Exception{
		return getRepo().findAll();
	}

	@Override
	public T getById(ID id) throws Exception{
		return getRepo().findById(id).orElse(null);
	}

	@Override
	public T update(T t) throws Exception{
		return getRepo().save(t);
	}

	@Override
	public void deleteById(ID id) throws Exception{
		getRepo().deleteById(id);
		
	}

}
	