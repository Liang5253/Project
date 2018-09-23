package com.lxz;

public class ZKConnect  {
	
	public static final String zkServerPath="139.9.1.251:2181,139.9.1.251:2182,139.9.1.251:2183";
	
	public static void main(String[] args) {
		ZKConns zks=new ZKConns();
		try {
			//同步创建zk节点。
//			String result=zks.synCreateNode("/Liangxiangze", "liangxiangze".getBytes(), Ids.OPEN_ACL_UNSAFE);
//			System.out.println(result);
			//异步创建zk节点：
//			zks.callBackCreateNode("/Liangxiangze/tomorrow", "hello".getBytes(), Ids.OPEN_ACL_UNSAFE);
			//同步修改节点内容。		
//			Stat statu=zks.getZk().setData("/Liangxiangze/tomorrow","hi".getBytes() , 0);
//			System.out.println(statu.getVersion());
			//异步修改节点内容。
//			zks.nsynSetData("/Liangxiangze/tomorrow","ycflm?".getBytes(),3);
			//同步删除节点内容
//			zks.getZk().delete("/Liangxiangze/tomorrow", 4);
			//异步删除节点
//			String ctx ="{'delete':'success'}";
//			zks.getZk().delete("/Liangxiangze/tomorrow", 0,new DeleteCallbacks(), ctx);
//			new Thread().sleep(2000);
			//查询节点内容：
//			zks.getNode();
			//查询子节点：
//			zks.getChildList();
			//查询节点是否存在。
//			zks.isExist();
			//使用acl自定义权限创建
//			zks.aclCreateNode();
			//使用aclIp权限创建节点：
			zks.ipAclCreateNode();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	

}
