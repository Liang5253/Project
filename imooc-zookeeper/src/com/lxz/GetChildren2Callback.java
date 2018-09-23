package com.lxz;

import java.util.List;

import org.apache.zookeeper.AsyncCallback.Children2Callback;
import org.apache.zookeeper.data.Stat;

public class GetChildren2Callback  implements Children2Callback {
	@Override
	public void processResult(int rc, String path, Object ctx, List<String> children, Stat stat) {
		System.out.println("当前目录为："+path);
		for (String string : children) {
			System.out.println("子目录："+string);
			System.out.println();
		}
	}
	
}
