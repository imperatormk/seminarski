package com.fikt.seminarski.service;

import java.util.List;
import java.util.Optional;

import com.fikt.seminarski.model.Upload;

public interface UploadService {
	public Upload save(Upload s);

	public void delete(Upload s);

	public Optional<Upload> getById(int id);

	public List<Upload> getAll();
}
