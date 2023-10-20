import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int t=0; t<T; t++) {
			int N = Integer.parseInt(br.readLine());
			int[][] dp = new int[3][N]; //행: 뽑지않는다. 1행 스티커를 뽑는다. 2행 스티커를 뽑는다.
			
			for(int r=1; r<=2; r++) {
				String[] row = br.readLine().split(" ");
				for(int c=0; c<N; c++) {
					dp[r][c] = Integer.parseInt(row[c]);
				}
			}

			for(int i=1; i<N; i++) {
				dp[0][i] += Math.max(dp[1][i-1], dp[2][i-1]);
				dp[1][i] += Math.max(dp[0][i-1], dp[2][i-1]);
				dp[2][i] += Math.max(dp[0][i-1], dp[1][i-1]);
			}
			int max = -1;
			for(int i=0; i<3; i++) {
				if(max<dp[i][N-1]) max = dp[i][N-1];
			}
			sb.append(max).append("\n");
		}
		System.out.println(sb);
	}
}