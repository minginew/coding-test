import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N,M;
    static List<Integer> books = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        String[] input2 = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        PriorityQueue<Integer> positive = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> negative = new PriorityQueue<>(Collections.reverseOrder());

        for(int i=0; i<N; i++){
            int len = Integer.parseInt(input2[i]);
            if(len >= 0) positive.add(len);
            else negative.add(Math.abs(len));
        }
        
        int endBook = 0;
        if(positive.isEmpty()) endBook = negative.peek();
        else if(negative.isEmpty()) endBook = positive.peek();
        else{
            endBook = Math.max(negative.peek(), positive.peek());
        }

        int answer = 0;
        while(!positive.isEmpty()){
            answer += positive.peek()*2;
            for(int i=0; i<M; i++) positive.poll();
        }

        while(!negative.isEmpty()){
            answer += negative.peek()*2;
            for(int i=0; i<M; i++) negative.poll();
        }

        answer -= endBook;
        System.out.println(answer);
    }
}
