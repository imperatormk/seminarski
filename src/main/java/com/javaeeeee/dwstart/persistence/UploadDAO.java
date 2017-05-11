package com.javaeeeee.dwstart.persistence;

import java.util.List;
import java.util.Optional;

import com.javaeeeee.dwstart.model.Upload;

public interface UploadDAO {
	public Upload save(Upload s);

	public void delete(Upload s);

	public Optional<Upload> getById(int id);

	public List<Upload> getAll();
}