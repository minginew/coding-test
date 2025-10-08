import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N;
    static int[] kids;
    static boolean[] visit;
    static int[][] order;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        kids = new int[N+1];
        visit = new boolean[N+1];
        order = new int[N+1][2];
        for(int i=0; i<N; i++){
            kids[i+1] = Integer.parseInt(br.readLine());
        }

        for(int i=1; i<N; i++){
            int k1 = kids[i];
            for(int j=i+1; j<=N; j++){
                int k2 = kids[j];
                if(k2 < k1){
                    order[k1][1]++;
                    order[k2][0]++;
                }
            }
        }
        int answer = 0;
        while(true){
            int maxOrder = maxValue();
            if(maxOrder == -1) break;
            answer++;
            order[maxOrder][0] = 0;
            order[maxOrder][1] = 0;

            int maxIndex = 0;
            for(int i=1; i<=N; i++){
                if(kids[i] == maxOrder){
                    maxIndex = i;
                    break;
                }
            }
            for(int i=1; i<=N; i++){
                if(i == maxIndex) continue;
                int k = kids[i];
                if((i < maxIndex) && (k > maxOrder)){
                    if(order[k][1] > 0) order[k][1]--;
                }

                if((i > maxIndex) && (k < maxOrder)){
                    if(order[k][0] > 0) order[k][0]--;
                }
            }
        }
        System.out.println(answer);
    }

    public static int maxValue(){
        int max = 0;
        int maxOrder = 0;
        for(int i=1; i<=N; i++){
            for(int j=0; j<2; j++){
                if(order[i][j] > max) {
                    max = order[i][j];
                    maxOrder = i;
                }
            }
        }
        if(max == 0) return -1;
        return maxOrder;
    }
}
