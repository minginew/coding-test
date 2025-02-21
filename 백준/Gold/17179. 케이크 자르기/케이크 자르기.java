import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N,M,L,C;
    static int[] list;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new  BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        L = Integer.parseInt(input[2]);
        list = new int[M+2];
        list[0] = 0;
        list[M+1] = L;
        for(int i=1; i<=M; i++) list[i] = Integer.parseInt(br.readLine());

        for(int i=0; i<N; i++) {
            C = Integer.parseInt(br.readLine());
            System.out.println(BinarySearch(0, L + 1));
        }
    }
    public static int BinarySearch(int st, int ed){
        while (st+1 < ed){
            int mid = (st+ed)/2;

            int interval = 0;
            int count = 0;
            boolean isMatch = false;
            for(int i=1; i<list.length; i++){
                interval += list[i] - list[i-1];
                if(interval > mid){
                    interval = 0;
                    count++;
                }else if(interval == mid){
                    isMatch = true;
                }
            }

            if(!isMatch && count == C){
                ed = mid;
                continue;
            }

            if(count <= C){
                ed = mid;
            }else{
                st = mid;
            }
        }
        return ed;
    }
}