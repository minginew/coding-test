import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long[][] dp = new long[3][N]; //0행: 사자를 가두지 않는다. 1행: 1행에 사자를 가둔다. 2행: 2행에 사자를 가둔다.
		dp[0][0] = dp[1][0] = dp[2][0] = 1;
		
		for(int i=1; i<N; i++) {
			//i(현재) 사자를 가두지 않는다면. 이전(i-1) 모든 경우를 더해줌 (사자가 어디에 있든 상관 무)
			dp[0][i] = (dp[0][i-1] + dp[1][i-1] + dp[2][i-1])%9901;
			//이전 우리에 사자를 넣지 않은 경우 + 사자를 2번째에 넣는 경우
			dp[1][i] = (dp[0][i-1] + dp[2][i-1])%9901;
			//이전 우리에 사자를 넣지 않은 경우 + 사자를 1번째에 넣는 경우
			dp[2][i] = (dp[0][i-1] + dp[1][i-1])%9901;
		}
		long count = (dp[0][N-1] + dp[1][N-1] + dp[2][N-1])%9901;
		System.out.println(count);
	}
}