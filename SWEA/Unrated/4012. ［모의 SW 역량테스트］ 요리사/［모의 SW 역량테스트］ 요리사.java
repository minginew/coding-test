import java.io.BufferedReader;
import java.io.InputStreamReader;
public class Solution {
	static int N;
	static int min;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			min = Integer.MAX_VALUE; //최소
			N = Integer.parseInt(br.readLine());
			int[][] S = new int[N][N];
			boolean[] visit = new boolean[N]; //방문배열
			for(int r=0; r<N; r++) {//시너지 배열
				String[] row = br.readLine().split(" ");
				for(int c=0; c<N; c++) {
					S[r][c] = Integer.parseInt(row[c]);
				}
			}
			rec(0, visit, S);
			sb.append("#").append(tc).append(" ").append(min).append("\n");
		}
		System.out.println(sb.toString());
	}
	static void rec(int n, boolean[] visit, int[][] S) {
		if(n==N) {
			int sumA = 0;
			int sumB = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(i==j) continue;
					if(visit[i]&&visit[j]) sumA += S[i][j];
					else if(!visit[i]&&!visit[j]) sumB += S[i][j];
				}
			}
			int diff = Math.abs(sumA-sumB);
			if(min>diff) min=diff;
			return;
		}
		
		visit[n] = true;
		rec(n+1, visit, S);
		visit[n] = false;
		rec(n+1, visit, S);
	}
}