package org.nxf.frame.service;

import java.util.List;

public interface IService {

	public abstract void save(Object o);

	public abstract List<?> getAll();

	public abstract List<?> select(String condition);

	public abstract void update(Object o);

	public abstract int delete(Integer id);

	public abstract List<?> getById(Long id);

	public abstract Object getOne(Integer id);

	public abstract Object getOneByName(String name);

	public abstract Object getByLimit(Integer p, Integer size);

	public abstract int getRecordTotal();

	public abstract List<?> getPage(int p);

};