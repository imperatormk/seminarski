package com.javaeeeee.dwstart.service;

import java.util.List;
import java.util.Optional;

import com.javaeeeee.dwstart.model.Work;

public interface WorkService {
	public Work save(Work s);

	public void delete(Work s);

	public Optional<Work> getById(int id);

	public List<Work> getAll();
}
