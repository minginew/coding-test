import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int[] dy = {-1,-2,-2,-1,1,2,2,1};
    static int[] dx = {-2,-1,1,2,-2,-1,1,2};
    static int N;
    static int count;
    static int[][] map;
    static boolean[][] visit;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int t=0; t<T; t++){
            N = Integer.parseInt(br.readLine());
            map = new int[N][N]; //맵
            visit = new boolean[N][N]; //방문배열
            count = 0; //target까지 이동횟수
            String[] input1 = br.readLine().split(" ");
            String[] input2 = br.readLine().split(" ");
            int[] knight = {Integer.parseInt(input1[0]),Integer.parseInt(input1[1])}; //체스말의 시작위치
            int[] target = {Integer.parseInt(input2[0]),Integer.parseInt(input2[1])}; //체스말의 도착위치
            if (target[0]==knight[0]&&target[1]==knight[1]){ //시작위치와 도착위치가 같게 주어지면 0 출력 후 종료
                System.out.println(0);
                continue;
            }
            Queue<int[]> q = new LinkedList<>(); //말의 좌표를 담을 자취 Queue
            System.out.println(bfs(q,knight,target));
        }
    }
    static int bfs(Queue<int[]> q, int[] knight, int[] target){
        q.offer(new int[] {knight[0],knight[1]});
        visit[knight[0]][knight[1]] = true;

        while (!q.isEmpty()){
            int size = q. size();
            count++;
            for(int i=0; i<size; i++){
                int r = q.peek()[0];
                int c = q.poll()[1];
                for(int d=0; d<8; d++){
                    int nr = r+dy[d];
                    int nc = c+dx[d];
                    if(nr<0 || nr>=N || nc<0 || nc>=N) continue;
                    if(visit[nr][nc]) continue;
                    q.offer(new int[] {nr,nc});
                    visit[nr][nc] = true;
                }
                if(visit[target[0]][target[1]]) return count; //타겟 만나면 종료
            }
        }
        return 0;
    }
}