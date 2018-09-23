package com.lxz;

import org.apache.zookeeper.AsyncCallback.DataCallback;
import org.apache.zookeeper.data.Stat;

public class GetDataCallback implements DataCallback {

	@Override
	public void processResult(int rc, String path, Object ctx, byte[] data, Stat stat) {
		System.out.println("当前的节点为：" + path);
		System.out.println("节点的内容为："+new String(data));
		System.out.println("节点的版本为："+stat.getVersion());
	}

}
