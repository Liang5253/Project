package contest.c01Lxz;

public class VolatileTest {
	
	public volatile int inc=10;
	public void increase() {
		inc++;
	}
	public static void main(String[] args) {
		VolatileTest test=new VolatileTest();
		for(int i=0;i<=10;i++) {
			new Thread() {
				public void run() {
					for(int j=0;j<=1000;j++) {
						test.increase();
					}
				}
			}.start();
		}
		
		while(Thread.activeCount()>1)
			Thread.yield();
		System.out.println(test.inc);
	}	
}
