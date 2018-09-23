package contest.c01Lxz;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ThreadExecutro   {
	public static void main(String[] args) {
		ExecutorService executor=Executors.newFixedThreadPool(10);
		for (int i = 0; i < 20; i++) {
//			executor.execute(new ThreadRunnable());
		Future<String> future=executor.submit(new ThreadRunnable());
			try {
				System.out.println(future.get());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
class ThreadRunnable implements Callable<String>{
	public String call() {
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName());
		return "this a future case";
	}
	
}
