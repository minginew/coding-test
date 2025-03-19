import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Computer {
    int end, seatNum;
    public Computer(int end, int seatNum){
        this.end = end;
        this.seatNum = seatNum;
    }
}

public class Main {
    static int N;
    static int[] count;
    static boolean[] visit;
    static List<int[]> users;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        count = new int[100001];
        visit = new boolean[100001];
        users = new ArrayList<>();

        for(int i=0; i<N; i++){
            String[] input = br.readLine().split(" ");
            int st = Integer.parseInt(input[0]);
            int ed = Integer.parseInt(input[1]);
            users.add(new int[]{st,ed});
        }

        users.sort(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[0], o2[0]);
            }
        });

        PriorityQueue<Computer> emptySeat = new PriorityQueue<>(new Comparator<Computer>() {
            @Override
            public int compare(Computer o1, Computer o2) {
                return Integer.compare(o1.seatNum, o2.seatNum);
            }
        });

        PriorityQueue<Computer> usingSeat = new PriorityQueue<>(new Comparator<Computer>() {
            @Override
            public int compare(Computer o1, Computer o2) {
                return Integer.compare(o1.end, o2.end);
            }
        });

        int seatIdx = 0;
        for(int i=0; i<users.size(); i++){
            int[] user = users.get(i);

            while (!usingSeat.isEmpty()){
                if(usingSeat.peek().end > user[0]) break;
                emptySeat.offer(usingSeat.poll());
            }

            if(emptySeat.isEmpty()){
                count[seatIdx]++;
                usingSeat.offer(new Computer(user[1], seatIdx++));
            }else {
                int seatNum = emptySeat.poll().seatNum;
                count[seatNum]++;
                usingSeat.offer(new Computer(user[1],seatNum));
            }
        }

        for(int i=0; i<count.length; i++){
            if(count[i] == 0) break;
            sb.append(count[i]).append(" ");
        }
        System.out.println(seatIdx);
        System.out.println(sb);
    }
}
