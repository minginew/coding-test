import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        dp = new int[N];

        String[] input2 = br.readLine().split(" ");
        for(int i=0; i<N; i++){
            int n = Integer.parseInt(input2[i]);
            dp[i] = n;
        }

        for(int i=1; i<N; i++){
            dp[i] = Math.max(dp[i], dp[i]+dp[i-1]);
        }

        int max = Integer.MIN_VALUE;
        for(int i=0; i<N; i++){
            if(max < dp[i]) max = dp[i];
        }
        
        System.out.println(max);
    }
}
