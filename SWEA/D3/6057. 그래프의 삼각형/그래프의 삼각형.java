import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
	static int N,M,count;
	static int[][] matrix;
	static boolean[] visit;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			String[] in = br.readLine().split(" ");
			N = Integer.parseInt(in[0]);
			M = Integer.parseInt(in[1]);
			matrix = new int[N+1][N+1];
			visit = new boolean[N+1];
			count = 0;
			
			for(int i=0; i<M; i++) {
				String[] eg = br.readLine().split(" ");
				int a = Integer.parseInt(eg[0]);
				int b = Integer.parseInt(eg[1]);
				matrix[a][b] = matrix[b][a] = 1;
			}
			comb(1, 0);
			sb.append("#").append(tc).append(" ").append(count).append("\n");
		}
		System.out.println(sb);
	}
	
	public static void comb(int n, int r) {//n 1부터
		if(r==3) {
			boolean check = true;
			for(int i=0; i<visit.length; i++) {
				if(!visit[i]) continue;
				for(int j=i+1; j<visit.length; j++) {
					if(!visit[j]) continue;
					if(matrix[i][j]==0) {
						check = false;
						break;
					}
				}
			}
			if(check) count++;
			return;
		}
		if(n==N+1) return;
		visit[n] = true;
		comb(n+1, r+1);
		visit[n] = false;
		comb(n+1, r);
	}
}