package com.lxz;

import org.apache.zookeeper.AsyncCallback.StatCallback;
import org.apache.zookeeper.data.Stat;

public class SetNodeData implements StatCallback{

	@Override
	public void processResult(int rc, String path, Object ctx, Stat stat) {
		System.out.println("修改后的数据版本为："+stat.getVersion());
	}

	
}
