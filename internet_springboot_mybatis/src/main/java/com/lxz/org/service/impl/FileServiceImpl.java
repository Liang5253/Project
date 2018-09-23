package com.lxz.org.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.lxz.org.dao.FileTableDao;
import com.lxz.org.pojo.FileTable;
import com.lxz.org.service.FileService;

public class FileServiceImpl implements FileService {
	
	@Autowired
	private FileTableDao fileServiceDao;
	
	@Override
	@Transactional(isolation=Isolation.READ_COMMITTED,propagation=Propagation.REQUIRED)
	public boolean insertFile(MultipartFile file,FileTable fileTable) {
		String filePath="F:/com"+new Date().getTime()+file.getOriginalFilename();
		fileTable.setFileFile(filePath);
		return ;
	}

}
