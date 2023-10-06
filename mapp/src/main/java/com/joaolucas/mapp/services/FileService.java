package com.joaolucas.mapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joaolucas.mapp.model.File;
import com.joaolucas.mapp.repositories.FileRepository;
import com.joaolucas.mapp.services.exceptions.ResourceNotFoundException;

@Service
public class FileService {

	@Autowired
    private FileRepository fileRepository;

    public File saveFile(File file) {
        return fileRepository.save(file);
    }

    public void deleteFile(String id) {
        fileRepository.deleteById(id);
    }

    public File getFileById(String id) {
    	Optional<File> obj = fileRepository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }
    
    public List<File> listAllFilesByIdObra(String idObra) {
        return fileRepository.findByIdObra(idObra);
    }
    
    
}
