package com.lxz.org.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.lxz.org.pojo.FileTable;
import com.lxz.org.pojo.Message;
import com.lxz.org.service.FileService;

@Controller
public class FileController {
	
	@Autowired
	private FileService fileService=null;
	
	public Message uploadFile(@RequestParam("title") String title,HttpServletRequest req,ModelMap model) {
	MultipartHttpServletRequest multipartHttpServletRequest=(MultipartHttpServletRequest)req;
	MultipartFile imgFile=multipartHttpServletRequest.getFile("imageFile");
	FileTable file=new FileTable();
	file.setFileId(Integer.parseInt(title));
	Message msg=new Message();
	if(fileService.insertFile(imgFile,file)) {
		msg.setSuccess(true);
		msg.setInfo("插入成功！");
		}else {
			msg.setSuccess(false);
			msg.setInfo("插入失败！");
		}
	}
	
}
