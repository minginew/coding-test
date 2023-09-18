import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
	static int N;
	static int M;
	static boolean[] visit;
	static int[][] matrix;
	static Queue<Integer> q;
	static int max;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			String[] in = br.readLine().split(" ");
			N = Integer.parseInt(in[0]);
			M = Integer.parseInt(in[1]);
			max = -1;
			matrix = new int[N+1][N+1];
			
			for(int i=0; i<M; i++) {
				String[] eg = br.readLine().split(" ");
				int a = Integer.parseInt(eg[0]);
				int b = Integer.parseInt(eg[1]);
				matrix[a][b] = 1;
				matrix[b][a] = 1;
			}
			
			for(int i=1; i<=N; i++) {
				visit = new boolean[N+1];
				visit[i] = true;
				dfs(i,1);
			}
			sb.append("#").append(tc).append(" ").append(max).append("\n");
		}
		System.out.println(sb);
	}
	
	public static void dfs(int n, int len) {
		for(int i=0; i<=N; i++) {
			if(matrix[n][i]==1 && !visit[i]) {
				visit[i] = true;
				dfs(i, len+1);
				visit[i] = false;
			}
		}
		if(max<len) max = len;
	}		
}