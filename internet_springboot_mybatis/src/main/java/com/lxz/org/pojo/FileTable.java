package com.lxz.org.pojo;

import java.io.Serializable;

public class FileTable implements Serializable{
		
	private int fileId;
	private byte[] fileFile;
	public int getFileId() {
		return fileId;
	}
	public void setFileId(int fileId) {
		this.fileId = fileId;
	}
	public byte[] getFileFile() {
		return fileFile;
	}
	public void setFileFile(byte[] fileFile) {
		this.fileFile = fileFile;
	}
	
	
}	
