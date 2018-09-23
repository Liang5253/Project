package com.lxz.org.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lxz.org.pojo.FileTable;

@Service("fileService")
public interface FileService {
	
	public boolean insertFile(MultipartFile file,FileTable fileTable);
}
