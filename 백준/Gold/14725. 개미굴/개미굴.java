import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int maxfloor;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        PriorityQueue<String[]> pq = new PriorityQueue<>(new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                int len = Math.min(o1.length, o2.length);
                for(int i=0; i<len; i++){
                    String s1 = o1[i];
                    String s2 = o2[i];
                    if(s1.equals(s2)) continue;
                    return s1.compareTo(s2);
                }
                return Integer.compare(o2.length, o1.length);
            }
        });

        for(int i=0; i<N; i++) {
            String[] input = br.readLine().split(" ");
            String[] stArr = new String[Integer.parseInt(input[0])];
            for(int j=0; j<stArr.length; j++){
                stArr[j] = input[j+1];
            }
            if(maxfloor < stArr.length){
                maxfloor = stArr.length;
            }
            pq.offer(stArr);
        }

        String[] preArr = null;
        String[] currArr;
        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()){
            if(preArr == null){
                preArr = pq.peek();
                currArr = pq.poll();
                String floor = "";
                for(int i=0; i<currArr.length; i++){
                    sb.append(floor).append(currArr[i]).append('\n');
                    floor += "--";
                }
                continue;
            }

            currArr = pq.poll();
            String floor = "";
            boolean equalPath = true;
            for(int i=0; i<currArr.length; i++){
                if(equalPath && i<preArr.length && preArr[i].equals(currArr[i])) {
                    floor += "--";
                    continue;
                }
                equalPath = false;
                sb.append(floor).append(currArr[i]).append('\n');
                floor += "--";
            }
            preArr = currArr;
        }
        System.out.println(sb);
    }
}