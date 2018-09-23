package contest.c01Lxz;

public class C012 {

	public static void main(String[] args) {
	//创建一个数组，限定长度为300
	int s[]=new int[300];
	for(int i=0;i<s.length;i++) {
		float f=(float)Math.random();
		s[i]=(int)(f*900+100);//随机取出一个0~1的小数*900+100，赋值给s数组第i个元素
		System.out.print(s[i]+"\t");//输出s[i]+一个空格到控制台。
		if((i+1)%10==0) {
			System.out.println(); //如果i+1%10=0就输出空行
		}
	}
	
	System.out.println("\n");
		for(int i=0;i<s.length;i++) {
			int a1=s[i]/100;		//a1=数组第i个元素/100
			int a2=(s[i]%100)/10;	//a2=数组第i个元素%100/10   
			int a3=(s[i]%100)%10;	//a2=数组第i个元素%100%10 
			if(a1*a1*a1+a2*a2*a2+a3*a3*a3==s[i]) {
				System.out.println(a1);
				System.out.println(a2);
				System.out.println(a3);
				System.out.print(s[i]+"\t"); //如果a1的立方+a2的立方+a3的立方==数组s的第i个元素的话就输出数组元素。
			}
		}
	}

}
