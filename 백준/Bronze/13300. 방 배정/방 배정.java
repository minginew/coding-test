import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		int[] men = new int[6];
		int[] girl = new int[6];
		for(int i=0; i<N; i++) {
			int sex = sc.nextInt();
			int grade = sc.nextInt();
			
			if(sex==0) {
				girl[grade-1]+=1;
			}else if (sex==1) {
				men[grade-1]+=1;
			}
		}

		int roomNum = 0;
		for(int i=0; i<men.length; i++) {
			roomNum += (int)Math.ceil((double)men[i]/K);
			roomNum += (int)Math.ceil((double)girl[i]/K);
		}
		System.out.println(roomNum);
	}
}