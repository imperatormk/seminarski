package com.javaeeeee.dwstart.persistence;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.javaeeeee.dwstart.model.Work;

public interface WorkDAO {
	public Work save(Work s);

	public void delete(Work s);

	public Optional<Work> getById(int id);

	public List<Work> getAll();
}
