import javax.imageio.IIOException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    // 전체층, 강호위치, 스타트링크위치, 위층버튼, 아래층버튼
    static int F,S,G,move;
    // [0]: 위층버튼, [1]: 아래층버튼
    static int[] dx = new int[2];
    static int[] map;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        F = Integer.parseInt(input[0]);
        S = Integer.parseInt(input[1]);
        G = Integer.parseInt(input[2]);
        dx[0] = Integer.parseInt(input[3]); // U
        dx[1] = -(Integer.parseInt(input[4])); // D

        map = new int[F+1];
        visit = new boolean[F+1];

        bfs(S,G);
        if(visit[G]){
            System.out.println(move);
        }else {
            System.out.println("use the stairs");
        }
    }

    static void bfs(int S, int G){
        // 0층은 안씀, 강호 위치 true
        visit[0] = visit[S] = true;
        // 스타트링크 위치 표시
        map[G] = 1;
        Queue<Integer> q = new LinkedList<>();
        q.offer(S);

        int count = 0;
        int size = q.size();
        while (!q.isEmpty()){
            for(int i=0; i<size; i++){
                int x = q.poll();
                if(map[x] == 1){
                    move = count;
                    return;
                }
                for(int d=0; d<2; d++){
                    int nx = x + dx[d];
                    if(nx<=0 || nx>F || visit[nx]) continue;
                    visit[nx] = true;
                    q.offer(nx);
                }
            }
            size = q.size();
            count++;
        }
    }
}