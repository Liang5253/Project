package contest.c01Lxz;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentranlockTest {
	
	public  int inc=0;
	Lock lock=new ReentrantLock();
	public  void increase() {
		lock.lock();
		inc++;
		lock.unlock();
	}
	public static void main(String[] args) {
		ReentranlockTest test=new ReentranlockTest();
		for(int i=0;i<10;i++) {
			new Thread() {
				public void run() {
					for(int j=0;j<1000;j++) {
						test.increase();
					}
				};
			}.start();
		}
		
		while(Thread.activeCount()>1)
			Thread.yield();
		System.out.println(test.inc);
	}	
}
