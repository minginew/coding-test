import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String [] in = br.readLine().split(" ");
        int[] arr = new int[N];
        int[] continueArr = new int[N];
        for (int i=0; i<N; i++) arr[i] = Integer.parseInt(in[i]);

        int maxLen = 0;
        for (int i=0; i<N; i++){
            if(i==0){
                continueArr[i] = 1;
                continue;
            }
            if(arr[i-1]<=arr[i]) continueArr[i] = continueArr[i-1]+1;
            else  continueArr[i] = 1;
        }
        for(int i=0; i<N; i++) if(maxLen<continueArr[i]) maxLen = continueArr[i];

        for (int i=N-1; i>=0; i--){
            if(i==N-1){
                continueArr[i] = 1;
                continue;
            }
            if(arr[i+1]<=arr[i]) continueArr[i] = continueArr[i+1]+1;
            else  continueArr[i] = 1;
        }
        for(int i=0; i<N; i++) if(maxLen<continueArr[i]) maxLen = continueArr[i];

        System.out.println(maxLen);
    }
}