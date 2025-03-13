import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static int[] arr, count;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        count = new int[N];

        String[] input = br.readLine().split(" ");
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(input[i]);
            count[i] = Integer.MAX_VALUE;
        }

        count[0] = 0;
        for(int i=0; i<N; i++){
            int jump = arr[i];
            if(count[i] == Integer.MAX_VALUE) continue;
            for(int j=1; j<=jump; j++){
                if(i+j >= N) break;
                count[i+j] = Math.min(count[i+j], count[i]+1);
            }
        }
        if(count[N-1] == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(count[N-1]);
    }
}
