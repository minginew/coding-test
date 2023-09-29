import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] dp = new int[N][];

        for(int i=0; i<N; i++){
            String[] in = br.readLine().split(" ");
            dp[i] = new int[i+1];

            for(int j=0; j<dp[i].length; j++) {
                dp[i][j] = Integer.parseInt(in[j]);
            }
        }

        for(int r=1; r<N; r++){
            for(int c=0; c<dp[r].length; c++){
                if(c==0){
                    dp[r][c] = dp[r-1][c] + dp[r][c];
                }else if(r==c){
                    dp[r][c] = dp[r-1][c-1] + dp[r][c];
                }else {
                    dp[r][c] = Math.max(dp[r-1][c-1],dp[r-1][c]) + dp[r][c];
                }
            }
        }

        int maxValue = 0;
        for(int i=0; i<N; i++){
            if(maxValue < dp[N-1][i]) maxValue = dp[N-1][i];
        }
        System.out.println(maxValue);
    }
}