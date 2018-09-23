package contest.c01Lxz;

public class C011 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i,j,k;
		for (i = 1;  i<=10; i++) {
			j=i-1;
			System.out.print("");
			for(k=1;k<=i+j;k++) {
				System.out.print(j);
			}
			System.out.println();
		}
	}

}
