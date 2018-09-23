package contest.c01Lxz;

import java.util.Scanner;

public class C013 {

	public static void main(String[] args) {
	
	int year =0;
	int month=0;
	int days=0;
	String str="Y";
	Scanner input=new Scanner(System.in);
	while(str.equals("Y")||str.equals("y")) {
	try {
		System.out.print("请输入年份：");
		year=input.nextInt();
		System.out.print("请输入月份：");
		month=input.nextInt();
			if(year>0&&month<12&&month>0) {
				switch( month){
					case 1:
					case 3:
					case 5:
					case 7:
					case 10:
					case 8:
					case 12:
						days=31;
						break;
					case 2:
						if(year%400==0||(year%100==0&&year%4==0)) {
							days=29;
						}else {
							days=28;
						}break;
					default:
						days=30;
						break;
				}
			}else {
				System.out.println("您输入的年份或月份有错误，请重新输入！");
				continue;
			}
		
		System.out.println("您询问的"+ year + "年" + month +"月的天数为"+days+"天！");
		
	} catch (Exception e) {
		System.err.println("请输入整数类型！");
		continue;
	}
	System.out.println("是否继续？（Y表示继续,N表示结束");
	str=input.next();
	}
	}
}
