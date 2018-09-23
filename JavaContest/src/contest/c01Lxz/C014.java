package contest.c01Lxz;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class C014 {
	
	
	/**
	 * 	假如有一对兔子，出生后第三个月起每个月都生一对兔子，假如都不死，每个月的兔子总数为多少
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int f,f1=1,f2=1,m=24;
		System.out.println("第1个月的兔子对数为："+f2);
		System.out.println("第2个月的兔子对数为："+f2);
		for(int i=3;i<m;i++) {
			f=f2;
			f2=f1+f2;
			f1=f;
			System.out.println("第"+i+"个月的兔子对数为："+f2);
		}
		
		List list=new ArrayList();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		
	Iterator<String> iterator=list.iterator();
	while(iterator.hasNext()){
		System.out.println(iterator.next());
	}
	}
	
}
