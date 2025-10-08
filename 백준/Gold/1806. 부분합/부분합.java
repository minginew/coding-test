import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static int N,S;
    static int[] arr;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input1 = br.readLine().split(" ");
        String[] input2 = br.readLine().split(" ");

        N = Integer.parseInt(input1[0]);
        S = Integer.parseInt(input1[1]);
        arr = new int[N];
        for(int i=0; i<N; i++) arr[i] = Integer.parseInt(input2[i]);

        if(arr[0] == S) {
            System.out.println(1);
            return;
        }

        int left = 0;
        int right = 1;
        int sum = arr[0] + arr[1];
        int minLen = Integer.MAX_VALUE;

        while (true){
            if(right >= arr.length) break;
            if(sum >= S){
                if(minLen > right-left+1) minLen = right-left+1;
                sum -= arr[left++];
                if(left > right){
                    if(++right >= arr.length) break;
                    sum += arr[right];
                }
            }else if (sum < S){
                if(++right >= arr.length) break;
                sum += arr[right];
            }
        }
        if(minLen == Integer.MAX_VALUE) System.out.println(0);
        else System.out.println(minLen);
    }
}
