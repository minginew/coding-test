import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
	static int N;
	static int K;
	static int max;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			String[] in = br.readLine().split(" ");
			N = Integer.parseInt(in[0]);
			K = Integer.parseInt(in[1]);
			max = Integer.MIN_VALUE;
			boolean[] visit = new boolean[N];
			int[][] burger = new int[N][2];
			for(int i=0; i<N; i++) {
				String[] row = br.readLine().split(" ");
				for(int j=0; j<2; j++) {
					burger[i][j] = Integer.parseInt(row[j]);
				}
			}
			rec(burger, visit, 0, 0);
			sb.append("#").append(t).append(" ").append(max).append("\n");
		}
		System.out.println(sb.toString());
	}
	static void rec(int[][] burger, boolean[] visit, int n, int cal) {
		if(cal>K) return;
		if(n==N) {
			int score = 0;
			for(int i=0; i<N; i++) if(visit[i]) score += burger[i][0];
			if(max<score) max=score;
			return;
		}
		visit[n]=true;
		rec(burger, visit, n+1, cal+burger[n][1]);
		visit[n]=false;
		rec(burger, visit, n+1, cal);
	}
}