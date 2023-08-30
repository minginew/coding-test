import java.util.Arrays;
import java.util.Scanner;

public class Solution {
	static int N;
	static int min;
	public static void main(String[] args)  {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int T = sc.nextInt();
		for(int t=1; t<=T; t++) {
			min = Integer.MAX_VALUE;
			N = sc.nextInt();
			int[] company = {Integer.parseInt(sc.next()),Integer.parseInt(sc.next())};
			int[] home = {Integer.parseInt(sc.next()),Integer.parseInt(sc.next())};
			int[][] customer = new int[N][2];
			int[] arr = new int[N];
			boolean[] visit = new boolean[N];
			Arrays.fill(arr, -1);
			
			for(int i=0; i<N; i++) {
				customer[i] = new int[] {Integer.parseInt(sc.next()),Integer.parseInt(sc.next())};
			}
			rec(0, arr, visit, company, home, customer);
			sb.append("#").append(t).append(" ").append(min).append("\n");
		}
		System.out.println(sb.toString());

	}
	static void rec(int n, int[] arr, boolean[] visit, int[] company, int[] home, int[][] customer) {
		if(n==N) {
			int distance = 0;
			distance += Math.abs(customer[arr[0]][0]-company[0])+Math.abs(customer[arr[0]][1]-company[1]);
			for(int i=0; i<N-1; i++) {
				distance += Math.abs(customer[arr[i]][0]-customer[arr[i+1]][0])+Math.abs(customer[arr[i]][1]-customer[arr[i+1]][1]);
			}
			distance += Math.abs(customer[arr[N-1]][0]-home[0])+Math.abs(customer[arr[N-1]][1]-home[1]);
			if(min>distance) min = distance;
			return;
		}
		for(int i=0; i<N; i++) {
			if(!visit[i]) {
				visit[i] = true;
				arr[n] = i;
				rec(n+1, arr, visit, company, home, customer);
				visit[i] = false;
			}
		}
	}
}