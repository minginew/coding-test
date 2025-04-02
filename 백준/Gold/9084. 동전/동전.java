import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int T,N,target;
    static int[] coins,dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());
        for(int t=0; t<T; t++){
            N = Integer.parseInt(br.readLine());
            coins = new int[N];
            String[] input = br.readLine().split(" ");
            target = Integer.parseInt(br.readLine());
            dp = new int[target+1];

            for(int i=0; i<N; i++){
                coins[i] = Integer.parseInt(input[i]);
            }

            dp[0] = 1;
            for(int i=0; i<coins.length; i++){
                int coin = coins[i];
                for(int j=1; j<=target; j++){
                    if(j-coin >= 0) dp[j] += dp[j-coin];
                }
            }
            sb.append(dp[target]).append("\n");
        }
        System.out.println(sb);
    }
}
