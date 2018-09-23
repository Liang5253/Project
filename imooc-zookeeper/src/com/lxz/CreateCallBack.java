package com.lxz;

import org.apache.zookeeper.AsyncCallback.StringCallback;


/**
 * 回调函数类
 * @author liang
 *
 */
public class CreateCallBack implements StringCallback  {

	@Override
	public void processResult(int rc, String path, Object ctx, String name) {
		System.out.println("创建节点："+path);
		System.out.println((String)ctx);
	}

}
