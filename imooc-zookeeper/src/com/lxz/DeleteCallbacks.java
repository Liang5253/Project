package com.lxz;

import org.apache.zookeeper.AsyncCallback.VoidCallback;

public class DeleteCallbacks implements VoidCallback {

	@Override
	public void processResult(int rc, String path, Object ctx) {
		System.out.println("删除"+path + "成功");
		System.out.println((String)ctx);
	}

}
