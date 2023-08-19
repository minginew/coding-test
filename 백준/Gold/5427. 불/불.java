import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int[] dy = {-1,1,0,0};
    static int[] dx = {0,0,-1,1};
    static int N;
    static int M;
    static int time;
    static char[][] map;
    static boolean[][] visit;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int t=0; t<T; t++){
            String[] input = br.readLine().split(" ");
            N = Integer.parseInt(input[1]); //백준은 왜 열을 먼저 주는걸 좋아할까?
            M = Integer.parseInt(input[0]);
            map = new char[N][M]; //맵
            visit = new boolean[N][M]; //방문배열
            time = 0; //탈출에 걸린시간
            Queue<int[]> sang = new LinkedList<>(); //상근쓰의 이동 좌표를 담은 큐
            Queue<int[]> fire = new LinkedList<>(); //불의 이동 좌표를 담는 큐
            for(int r=0; r<N; r++){
                char[] row = br.readLine().toCharArray();
                for(int c=0; c<M; c++){
                    map[r][c]=row[c];
                    if(map[r][c]=='#') visit[r][c] = true; //벽은 못가게 방문배열을 true
                    else if(map[r][c]=='@'){
                        visit[r][c] = true;
                        sang.offer(new int[] {r,c}); //상근이 초기 위치를 큐에 담음
                    }
                    else if(map[r][c]=='*'){
                        visit[r][c] = true;
                        fire.offer(new int[] {r,c}); //불의 초기 위치를 큐에 담음
                    }
                }
            }
            if(bfs(sang,fire)>0){
                sb.append(time).append("\n");
                continue;
            }
            sb.append("IMPOSSIBLE").append("\n");
        }
        System.out.println(sb.toString());
    }
    static int bfs(Queue<int[]> sang, Queue<int[]> fire){
        int sizeF = fire.size();
        int sizeS = sang.size();
        while (!sang.isEmpty()){
            time++;
            for (int i=0; i<sizeF; i++){
                int r = fire.peek()[0];
                int c = fire.poll()[1];
                for(int d=0; d<4; d++){
                    int nr = r+dy[d];
                    int nc = c+dx[d];
                    if(nr<0 || nr>=N || nc<0 || nc>=M) continue;
                    if(visit[nr][nc]) continue;
                    fire.offer(new int[] {nr,nc});
                    visit[nr][nc] = true;
                }
            }
            for (int i=0; i<sizeS; i++){
                int r = sang.peek()[0];
                int c = sang.poll()[1];
                if(r==0 || r==N-1 || c==0 || c==M-1){
                    return time;
                }
                for(int d=0; d<4; d++){
                    int nr = r+dy[d];
                    int nc = c+dx[d];
                    if(nr<0 || nr>=N || nc<0 || nc>=M) continue;
                    if(visit[nr][nc]) continue;
                    sang.offer(new int[] {nr,nc});
                    visit[nr][nc] = true;
                }
            }
            sizeF = fire.size();
            sizeS = sang.size();
        }
        return 0;
    }
}