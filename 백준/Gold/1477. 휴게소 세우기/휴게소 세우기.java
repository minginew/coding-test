import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N,M,L;
    static List<Integer> list;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new  BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        L = Integer.parseInt(input[2]);
        list = new ArrayList<>();

        list.add(0);
        list.add(L);
        if(N > 0) {
            String[] input2 = br.readLine().split(" ");
            for(int i=0; i<N; i++){
                int n = Integer.parseInt(input2[i]);
                list.add(n);
            }
        }
        Collections.sort(list);
        System.out.println(BinarySearch(0,L));
    }
    public static int BinarySearch(int st, int ed){
        while (st+1<ed){
            int mid = (st+ed)/2;
            int count = 0;
            for(int i=1; i<list.size(); i++){
                int interval = list.get(i) - list.get(i-1);
                if(interval >= mid) {
                    if(interval%mid == 0){
                        count += interval/mid-1;
                    }else {
                        count += interval/mid;
                    }
                }
            }
            if(count <= M){
                ed = mid;
            }else {
                st = mid;
            }
        }
        return ed;
    }
}