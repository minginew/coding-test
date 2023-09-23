import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int[] dx = {1,2,3,4,5,6};
    static int[] map;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        int ladderSize = Integer.parseInt(in[0]);
        int snakeSize = Integer.parseInt(in[1]);
        map = new int[101];
        map[0] = map[1] = -1; // -1로 방문 처리할 예정

        for(int i=0; i<ladderSize; i++){
            String[] inL = br.readLine().split(" ");
            int st = Integer.parseInt(inL[0]);
            int ed = Integer.parseInt(inL[1]);
            map[st] = ed; // st에 들어오면 ed로 이동
        }
        for(int i=0; i<snakeSize; i++){
            String[] inS = br.readLine().split(" ");
            int st = Integer.parseInt(inS[0]);
            int ed = Integer.parseInt(inS[1]);
            map[st] = ed; // st에 들어오면 ed로 이동
        }
        System.out.println(bfs());

    }
    public static int bfs(){
        Queue<Integer> q = new LinkedList<>();
        q.offer(1);
        int size = q.size();
        int count = 0;
        while (!q.isEmpty()){
            for(int i=0; i<size; i++){
                int x = q.poll();
                if (x==100){
                    return count;
                }
                for (int d=0; d<6; d++){
                    int nx = x + dx[d];
                    if(nx>100 || map[nx] == -1) continue;
                    if(map[nx] != 0){
                        q.offer(map[nx]); //사다리 출구에 있는 위치를 큐에 담는다.
                        map[map[nx]] = -1; // 사다리 출구
                        map[nx] = -1; //사다리 입구
                    }else {
                        q.offer(nx);
                        map[nx] = -1;
                    }
                }
            }
            size = q.size();
            count++;
        }
        return 0;
    }
}