package com.fikt.seminarski.persistence;

import java.util.List;
import java.util.Optional;

import com.fikt.seminarski.model.Work;

public interface WorkDAO {
	public Work save(Work s);

	public void delete(Work s);

	public Optional<Work> getById(int id);

	public List<Work> getAll();
}
