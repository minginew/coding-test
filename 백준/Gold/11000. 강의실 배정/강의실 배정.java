import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new  BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        
        PriorityQueue<int[]> classes = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] c1, int[] c2) {
                if(c1[0] == c2[0]) return Integer.compare(c1[1]- c1[0], c2[1]-c2[0]);
                return Integer.compare(c1[0], c2[0]);
            }
        });

        PriorityQueue<Integer> room = new PriorityQueue<>(Comparator.naturalOrder());

        for(int i=0; i<N; i++){
            String[] input = br.readLine().split(" ");
            int s = Integer.parseInt(input[0]);
            int t = Integer.parseInt(input[1]);
            classes.offer(new int[]{s,t});
        }
        
        while (!classes.isEmpty()){
            int[] curr = classes.poll();
            int s = curr[0];
            int t = curr[1];

            if(room.isEmpty()){
                room.offer(t);
                continue;
            }
            if(room.peek() <= s){
                room.poll();
                room.offer(t);
            }else{
                room.offer(t);
            }
        }
        System.out.println(room.size());
    }
}