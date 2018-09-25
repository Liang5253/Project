package com.lxz;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.acl.Acl;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.ZooDefs.Perms;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.data.Stat;
import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class ZKConns implements Watcher{
	private final static Logger LOG=LoggerFactory.getLogger(ZKConns.class);
	private ZooKeeper zk;
	private long sessionId;
	private byte[] sessionPasswd;
	private static final String ipPath="127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183";
	private  final int timeout=5000;
	Stat stat=new Stat();
	CountDownLatch countDown =new CountDownLatch(1);
	/**
	 * 客户端和zk服务器端连接是一个异步过程
	 * 当连接成功之，客户端会收到一个watch通知
	 * 参数：
	 * 	connectString : 连接服务器的Ip字符串，
	 * 			比如： “192.168.1.1:2181,192.168.1.2:2181,192.168.1.3:2181”
	 * 			也可以是一个Ip
	 * 			也可以在ip后加路径。
	 * 
	 * sessionTimeout ： 超时时间，心跳收不到了就会超时。
	 * watcher: 通知事件，如果有对应的事件触发，则会收到一个通知，如果不需要，那就设置为Null
	 * canBeReadOnly : 可读，当这个物理机节点断开后，还是可以读到数据的，只是不能写。
	 * 						此时数据被读取到的可能是旧数据，此处建议设置为false,不推荐使用。
	 * sessionId 会话的ID
	 * sessionPasswd  :会话密码，当会话丢失后，可以依据sessionId和sessionPasswd重新获取会话。
	 */
	public ZKConns() {
		try {
			this.zk=new ZooKeeper(ipPath, timeout, this ,false);
			this.sessionId=zk.getSessionId();
			this.sessionPasswd=zk.getSessionPasswd();
			
			
			LOG.warn("客户端开始连接zookeeper服务器");
			LOG.warn("连接状态：{}",zk.getState());
			new Thread().sleep(2000);
			LOG.warn("连接状态:{}",zk.getState());
		} catch (Exception e) {
			e.printStackTrace();
			if(zk!=null) {
				try {
					zk.close();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		} 
	}
	
	
	
	/**
	 * 会话重连
	 * @param zkServerPath
	 */
	public void reConn() {
		try {
			if(zk!=null) zk.close();
			
			LOG.warn("会话重新连接.....");
			
			 this.zk=new ZooKeeper(this.ipPath, 
									  this.timeout,
									  this,
									  this.sessionId,
									  this.sessionPasswd,
									  false);
			
			LOG.warn("重新连接状态：{}",zk.getState());
			new Thread().sleep(2000);
			LOG.warn("重新连接状态：{}",zk.getState());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 异步创建节点
	 * @param path	创建的路径
	 * @param data	路径存储的数据的byte[]
	 * @param acls	控制权限策略
	 * @return
	 * @throws KeeperException
	 *  创建节点有两种：一是同步创建，二是异步创建
		 * 参数：
		 *  acl : 控制权限策略
		 *  		Ids.OPEN_ACL_UNSAFE  --> world:anyone:cdrwa
		 *  		CREATOR_ALL_ACL   -->auth:user:password:cdrwa
		 *  CreateMode: 节点类型，是一个枚举
		 *  		PERSISTENT : 持久节点
		 *  		PERSISTENT_SEQUENTIAL :　持久顺序节点
		 *  		EPHEMERAL: 临时节点
		 *  		EPHEMERAL:临时顺序节点
	 */
	public void callBackCreateNode(String path,byte[] data,List<ACL> acls){
		try {
			String ctx= "{'create':'success'}";
			zk.create(path, data, acls, CreateMode.PERSISTENT, new CreateCallBack(), ctx);
			System.out.println("创建节点:"+path+"成功！");
			new Thread().sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 同步创建节点
	 * @param path  创建的路径
	 * @param data	 路径存储的数据的byte[]
	 * @param acls	控制权限策略
	 * @return
	 * 	
		 * 创建节点有两种：一是同步创建，二是异步创建
		 * 参数：
		 *  acl : 
		 *  		Ids.OPEN_ACL_UNSAFE  --> world:anyone:cdrwa
		 *  		CREATOR_ALL_ACL   -->auth:user:password:cdrwa
		 *  createMode: 节点类型，是一个枚举
		 *  		PERSISTENT : 持久节点
		 *  		PERSISTENT_SEQUENTIAL :　持久顺序节点
		 *  		EPHEMERAL: 临时节点
		 *  		EPHEMERAL:临时顺序节点
		 
	 */
	public String synCreateNode(String path,byte[] data,List<ACL> acls) {
		String result=null;

		try {
			 result=zk.create(path, data, acls, CreateMode.PERSISTENT);
			System.out.println("创建节点成功！");
		} catch (KeeperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	
	/**
	 * 异步修改
	 * @param path
	 * @param data
	 * @param version
	 */
	public void nsynSetData(String path,byte[] data,int version){
		String ctx="{'set':'success'}";
		zk.setData(path, data, version,new SetNodeData(), ctx);
		try {
			new Thread().sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	/**
	 * 使用计数器来查询指定Path路径中的内容。
	 */
	public void getNode() {
		
		try {
//			byte[] pathNodes=zk.getData("/Liangxiangze", true, stat);  //同步查询方法一
//			byte[] pathNodes=zk.getData("/Liangxiangze", this, stat);  //同步查询方法二
			String ctx="{'get':'success'}";
//			zk.getData("/Liangxiangze", true,new GetDataCallback(), ctx); //异步查询节点方法一
			zk.getData("/Liangxiangze", this, new GetDataCallback(), ctx);//异步查询节点方法二
//			System.out.println("节点的内容为："+new String(pathNodes));
			this.countDown.await();//等待zookeeper服务器中返回事件反应。
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 多种查询子节点方法
	 */
	public void getChildList() {
		try {
//			List<String> list=zk.getChildren("/", true);//同步查询子节点方法一
//			List<String> list=zk.getChildren("/", this);//同步查询子节点方法二
//			List<String> list=zk.getChildren("/", true, stat); //同步查询子节点方法三
			List<String> list=zk.getChildren("/", this, stat); //同步查询子节点方法四
//			for (String string : list) {
//				System.out.println("子节点："+string);
//			}
			String ctx="{'getChildren':'success'}";
//			zk.getChildren("/", true, new GetChildren2Callback(), ctx);//异步查询子节点方法一
//			zk.getChildren("/", this, new GetChildren2Callback(), ctx);//异步查询子节点方法二
			zk.getChildren("/", true, new GetChildren2Callback(), ctx);//异步查询子节点方法三
			Thread.sleep(2000);  //主要用来等待zookeeper服务器返回消息。
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 判断节点是否存在。
	 */
	public void isExist() {
		try {
			Stat b=zk.exists("/Liangxiangze", true);
			System.out.println("数据版本："+b.getVersion());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 使用同步方式自定义权限创建节点
	 */
	public void  aclCreateNode() {
		try {
			List<ACL> list =new ArrayList<ACL>();
			Id id1;
			//新建一个Id 。
			id1 = new Id("digest",DigestAuthenticationProvider.generateDigest("liangxiangze:5253"));
			//把权限和id添加进list<ACL>集合里面
			list.add(new ACL(Perms.ALL,id1));
			//由于/Test节点需要用户权限，先设置登录权限
			zk.addAuthInfo("digest", "liangxiangze:5253".getBytes());
			//再添加节点，同时设定权限。
			String str =zk.create("/Test/test1", "testacl".getBytes(), list,CreateMode.PERSISTENT);
			System.out.println(str);
			countDown.await();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 使用ip权限限制创建节点
	 */
	public void ipAclCreateNode() {
		try {
		List<ACL> acls=new ArrayList<ACL>();
		Id ip1=new Id("ip","192.168.1.100");
		acls.add(new ACL(Perms.ALL,ip1));
		zk.addAuthInfo("digest", "liangxiangze:5253".getBytes());
		String ctx="{'create':'success'}";
		zk.create("/Test/iptest", "192.168.1.100".getBytes(), acls, CreateMode.PERSISTENT, new CreateCallBack(), ctx);
	
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	/**
	 * 实现接口抽象监控方法。
	 */
	public void process(WatchedEvent event) {
		LOG.warn("接收到watch通知：{}",event);
		//根据事件类型划分业务。
		if(event.getType()==EventType.NodeDataChanged) {
			try {
				byte[] pathNode=zk.getData(event.getPath(), false, stat);
				System.out.println("节点："+event.getPath()+"修改后的节点内容为："+new String(pathNode));
				this.countDown.countDown();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(event.getType()==EventType.NodeDeleted) {
			System.out.println("删除节点成功！");
			this.countDown.countDown();
		}else if(event.getType()==EventType.NodeCreated) {
			System.out.println("创建节点成功!");
			this.countDown.countDown();
		}else if(event.getType()==EventType.NodeChildrenChanged) {
			System.out.println("操作子节点成功！");
			this.countDown.countDown();
		}
		
	}
	
	

}
