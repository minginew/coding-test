import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws  Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N+1];

        Arrays.fill(dp,N);
        dp[0] = 0;
        dp[3] = 1;
        for(int i=5; i<=N; i++){
            dp[i] = Math.min(dp[i-3]+1,dp[i]);
            dp[i] = Math.min(dp[i-5]+1,dp[i]);
        }
        if(dp[N] == N){
            System.out.println(-1);
            return;
        }
        System.out.println(dp[N]);
    }
}