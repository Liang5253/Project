package com.lxz.countDown;

import java.util.concurrent.CountDownLatch;

public class BeiJing extends DangerCenter {

	public BeiJing(CountDownLatch countDown) {
		super(countDown, "北京调度站！");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void check() {
		System.out.println("正在检查【"+ this.getStation() + "】...");
		try {
			Thread.sleep(2000);
		}catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("检查【"+this.getStation()+"】完毕，可以发车。");
	}

}
