package com.lxz.countDown;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CheckStartUp {
	private static List<DangerCenter> stationList;
	private static CountDownLatch countDown;
	
	public CheckStartUp() {
		
	}
	
	public static boolean checkAllStations() throws Exception{
		//初始化三个调度站
		countDown=new CountDownLatch(3);
		stationList=new ArrayList<DangerCenter>();
		//把所有的站点添加进list
		stationList.add(new BeiJing(countDown));
		stationList.add(new JiangSu(countDown));
		stationList.add(new ShanDong(countDown));
		
		//使用线程池
		Executor executor=Executors.newFixedThreadPool(stationList.size());
		
		for (DangerCenter dangerCenter : stationList) {
			executor.execute(dangerCenter);
		}
		
		//等待线程执行完毕
		countDown.await();
		
		
		for (DangerCenter dangerCenter : stationList) {
			if(!dangerCenter.isOk()) {
				System.out.println("站点"+dangerCenter.getStation()+"调度失败，检查不通过、!");
				return false;
			}
			System.out.println("站点"+dangerCenter.getStation()+"调度成功，检查通过、!");
		}
		return true;

	
	}
	
	
	public static void main(String[] args) {
		try {
			CheckStartUp.checkAllStations();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
