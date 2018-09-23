package com.lxz;

import java.io.IOException;

import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZooKeeperConn {
	private long sessionId;
	private byte[] sessionPasswd;
	private final static Logger LOG=LoggerFactory.getLogger(ZooKeeperConn.class);
	private ZooKeeper zk;
	private String ipPath;
	private int timeout;
	
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
	public ZooKeeper getZooKeeper() {
			
			return getZk();
	}
	
	public long getSessionId() {
		return sessionId;
	}
	public void setSessionId(long sessionId) {
		this.sessionId = sessionId;
	}
	public byte[] getSessionPasswd() {
		return sessionPasswd;
	}
	public void setSessionPasswd(byte[] sessionPasswd) {
		this.sessionPasswd = sessionPasswd;
	}
	public ZooKeeper getZk() {
		return zk;
	}
	public ZooKeeperConn(String ipPath,int timeout,Watcher watcher) {
		try {
			this.zk=new ZooKeeper(ipPath, timeout, watcher,false);
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
	public void reConn(Watcher watcher) {
		try {
			if(zk!=null) zk.close();
			
			LOG.warn("会话重新连接.....");
			
			 this.zk=new ZooKeeper(this.ipPath, 
									  this.timeout,
									  watcher,
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
	
	
	public String getIpPath() {
		return ipPath;
	}
	public void setIpPath(String ipPath) {
		this.ipPath = ipPath;
	}
	public int getTimeout() {
		return timeout;
	}
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
	public static Logger getLog() {
		return LOG;
	}
	public void setZk(ZooKeeper zk) {
		this.zk = zk;
	}
	
	
	
	


}
