import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static int[] arr;
    static long[][] dp;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        dp = new long[N][21];

        String[] input = br.readLine().split(" ");
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(input[i]);
        }

        dp[0][arr[0]] = 1;
        for(int i=1; i<arr.length-1; i++){
            int curr = arr[i];
            for(int j=0; j<=20; j++){
                if(dp[i-1][j]==0) continue;
                if(j+curr <= 20) dp[i][j+curr] += dp[i-1][j];
                if(j-curr >= 0) dp[i][j-curr] += dp[i-1][j];
            }
        }
        System.out.println(dp[N-2][arr[N-1]]);
    }
}
