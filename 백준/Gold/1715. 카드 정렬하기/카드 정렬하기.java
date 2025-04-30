import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new  BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        });

        for(int i=0; i<N; i++){
            pq.offer(Integer.parseInt(br.readLine()));
        }

        int answer = 0;
        int temp = 0;
        while (!pq.isEmpty()){
            if(temp == 0){
                temp = pq.poll();
                continue;
            }else if(temp <= pq.peek()){
                temp += pq.poll();
                answer += temp;
            }else{
                pq.offer(temp);
                temp = pq.poll();
            }
        }
        System.out.println(answer);
    }
}
