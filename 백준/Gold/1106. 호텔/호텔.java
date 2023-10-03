import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        int C = Integer.parseInt(in[0]);
        int N = Integer.parseInt(in[1]);
        int[][] city = new int[N][2]; //n개 도시 {비용, 고객수}
        int[] dp = new int[10001]; //index: 고객수, value: 비용 -> c보다 더많은 고객을 더 싼 비용으로 홍보할 가능성 때문에 10001로 둠

        for(int i=0; i<N; i++){
            String[] c = br.readLine().split(" ");
            city[i][0] = Integer.parseInt(c[0]);
            city[i][1] = Integer.parseInt(c[1]);
        }

        for(int i=1; i<=10000; i++){//dp순회, i==고객수
            for(int j=0; j<N; j++){//도시배열 순회
                int cost = city[j][0]; //비용
                int cli = city[j][1]; //고객

                if(i>=cli){
                    if(dp[i]==0) dp[i] = dp[i-cli] + cost; //dp[i]==0 처음값을 넣을 때
                    else dp[i] = Math.min(dp[i],dp[i-cli] + cost);
                }else {
                    if(dp[i]==0) dp[i] = cost; //이거 때문에 개고생.. 더 많은 고객을 적은 비용으로 홍보할 수도 있음.
                }
            }
        }
        int minCost = Integer.MAX_VALUE;
        for(int i=C; i<=10000; i++){
            if(minCost>dp[i]) minCost = dp[i];
        }
        System.out.println(minCost);
    }
}