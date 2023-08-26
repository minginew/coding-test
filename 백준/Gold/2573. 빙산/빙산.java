import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int[] dr = {-1,1,0,0}; //상 하 좌 우
    static int[] dc = {0,0,-1,1};
    static int N;
    static int M;
    static int iceNum;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        int[][] map = new int[N][M];
        boolean[][] visit = new boolean[N][M];
        Queue<int[]> q = new LinkedList<>();

        for(int r=0; r<N; r++){
            String[] row = br.readLine().split(" ");
            for(int c=0; c<M; c++){
                map[r][c] = Integer.parseInt(row[c]);
            }
        }
        int time=0;
        int a =0;
        while (true){
            time++;
            melting(map); //빙하가 녹는다.
            iceNum=0;  //녹은 다음에 빙하게 몇덩이인지 담기위한 변수
            for(int r=0; r<N; r++){
                for(int c=0; c<M; c++){
                    if(map[r][c]!=0 && !visit[r][c]){
                        q.offer(new int[] {r,c});
                        visit[r][c] = true;
                        bfs(map,visit,q); //bfs를 통해 빙하가 몇개의 덩어리인지 파악한다.
                        if(iceNum>1){//두개 이상으로 분리된다면 걸린 시간을 출력하고 종료한다.
                            System.out.println(time);
                            return;
                        }
                    }
                }
            }
            visit = new boolean[N][M]; //빙하 방문배열 초기화
            if (iceNum==0){//한 덩리인 채로 그냥 다 녹아버린다면 0을 출력 후 종료한다.
                System.out.println(0);
                return;
            }
        }


    }
    static void melting(int[][] map){
        Queue<int[]> position = new LinkedList<>(); //빙하가 있는 곳의 좌표를 모두 담는다.
        Queue<Integer> meltNum = new LinkedList<>(); //빙하의 주변에 몇개의 방향에 바닷물이 있는지 기록한다.
        for(int r=0; r<N; r++){ //좌표와 해당 좌표에 인접한 바닷물 방향 수를 큐에 담는과정
            for(int c=0; c<M; c++){
                if(map[r][c]!=0){
                    position.offer(new int[] {r,c});
                    int count=0;
                    for(int d=0; d<4; d++){
                        int nr = r+dr[d];
                        int nc = c+dc[d];
                        if(nr<0 || nr>=N || nc<0 || nc>=M) continue;
                        if (map[nr][nc]==0) count++;
                    }
                    meltNum.offer(count);
                }
            }
        }

        while (!position.isEmpty()){ // 해당 빙하에 바닷물이 접해있는 방향 수 만큼 빼준다.
            int r=position.peek()[0];
            int c=position.poll()[1];
            map[r][c]-=meltNum.poll();
            if(map[r][c]<0) map[r][c]=0; // 다녹으면 0으로 처리
        }

    }

    static void bfs(int[][] map, boolean[][] visit, Queue<int[]> q){ //빙하 덩어리가 몇개인지 확인하는 bfs
           int size = q.size();
           while (!q.isEmpty()){
               for (int i=0; i<size; i++){
                   int r = q.peek()[0];
                   int c = q.poll()[1];
                   for (int d=0; d<4; d++){
                       int nr = r+dr[d];
                       int nc = c+dc[d];
                       if(nr<0 || nr>=N || nc<0 || nc>=M || map[nr][nc]==0 || visit[nr][nc]) continue;

                       visit[nr][nc]=true;
                       q.offer(new int[] {nr,nc});
                   }
               }
               size = q.size();
           }
           iceNum++;
    }
}