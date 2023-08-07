import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = Integer.parseInt(sc.nextLine());
		for(int i=0; i<N; i++) {
			String[] str = sc.nextLine().split(" ");
			String[] s1 = str[0].split("");
			String[] s2 = str[1].split("");
			
			Arrays.sort(s1);
			Arrays.sort(s2);

			for(int j=0; j<s1.length; j++) {
				if(!s1[j].equals(s2[j])) {
					System.out.println("Impossible");
					break;
				}
				if(j==s1.length-1) System.out.println("Possible");
			}
		}
	}
}