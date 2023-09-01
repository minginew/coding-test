import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
	static int N,M,K;
	static int min;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			String[] in = br.readLine().split(" ");
			min = Integer.MAX_VALUE;
			N = Integer.parseInt(in[0]);
			M = Integer.parseInt(in[1]);
			K = Integer.parseInt(in[2]);
			int[][] map = new int[N][M];
			int[] inj = new int[N]; //0:A를 넣는다. 1:B를 넣는다. -1:넣지 않는다.
			
			for(int i=0; i<N; i++) {
				String[] row = br.readLine().split(" ");
				for(int j=0; j<M; j++) {
					map[i][j] = Integer.parseInt(row[j]);
				}
			}
			com(map, inj, 0, 0);
			sb.append("#").append(t).append(" ").append(min).append("\n");
		}
		System.out.println(sb.toString());
	}
	static boolean check(int[][] map, int[] inj) {
	outter:for(int c=0; c<M; c++) {
			int temp = map[0][c];
			int count = 0;
			if(inj[0]==0) {
				temp = 0;
			}else if (inj[0]==1) {
				temp = 1;
			}
			for(int r=0; r<N; r++) {
				if(inj[r]==0) {
					if(inj[r]==temp) count++;
					else count=1;
					temp = 0;
				}else if (inj[r]==1) {
					if(inj[r]==temp) count++;
					else count=1;
					temp = 1;
				}else {
					if(map[r][c]==temp) count++;
					else count=1;
					temp = map[r][c];
				}
				if(count >= K) continue outter;
			}
			return false;
		}
		return true;
	}
	
	static void com(int[][] map, int[] inj, int idx, int n) {
		if(idx == N) {
			if(n>min) return;
			if(check(map, inj)) if(min>n) min = n;
			return;
		}
		inj[idx]=0;
		com(map, inj, idx+1, n+1);
		inj[idx]=1;
		com(map, inj, idx+1, n+1);
		inj[idx]=-1;
		com(map, inj, idx+1, n);
	}
}