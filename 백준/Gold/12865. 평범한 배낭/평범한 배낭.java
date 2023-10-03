import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        int N = Integer.parseInt(in[0]);
        int K = Integer.parseInt(in[1]);
        int[][] dp = new int[N][K+1];
        int[][] obj = new int[N][2];

        for (int i=0; i<N; i++){
            String[] ob = br.readLine().split(" ");
            obj[i][0] = Integer.parseInt(ob[0]);
            obj[i][1] = Integer.parseInt(ob[1]);
        }

        for(int r=0; r<N; r++){
            for(int c=1; c<=K; c++){
                int m = obj[r][0]; //물건의 무게
                int k = obj[r][1]; //물건의 가치

                if(r==0){
                    if (m<=c) dp[r][c] = k;
                    continue;
                }
                if(c-m<0) dp[r][c] = dp[r-1][c];
                else{
                    dp[r][c] = Math.max(dp[r-1][c], dp[r-1][c-m] + k);
                }
            }
        }
        System.out.println(dp[N-1][K]);
    }
}