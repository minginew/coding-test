import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int vipNum = Integer.parseInt(br.readLine());

        int[] dp = new int[N+1];

        for(int i=0; i<=N; i++){
            if(i<=1){
                dp[i] = 1;
                continue;
            }
            dp[i] = dp[i-1] + dp[i-2];
        }

        int sum = 1;
        int temp = 0;
        for(int i=0; i<vipNum; i++){
            int num = Integer.parseInt(br.readLine());
            sum *= dp[num-temp-1];
            temp = num;
        }
        sum *= dp[N-temp];

        System.out.println(sum);

    }
}