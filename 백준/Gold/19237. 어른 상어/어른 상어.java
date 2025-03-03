import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] dr = {0,-1,1,0,0};
    static int[] dc = {0,0,0,-1,1};
    static int N,M,k;
    static int[][] map, kMap;
    static List<Integer>[][] dirMap;
    static Map<Integer,int[]> sharks;
    static Map<Integer, Integer> dir;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new  BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        k = Integer.parseInt(input[2]);
        map = new int[N][N];
        kMap = new int[N][N];
        dirMap = new ArrayList[M+1][5];
        sharks = new HashMap<>();
        dir = new HashMap<>();

        for(int r=0; r<N; r++){
            String[] col = br.readLine().split(" ");
            for(int c=0; c<N; c++){
                map[r][c] = Integer.parseInt(col[c]);
                if(map[r][c] != 0){
                    sharks.put(map[r][c], new int[] {r,c});
                }
            }
        }

        String[] input2 = br.readLine().split(" ");
        for(int i=1; i<=input2.length; i++){
            int d = Integer.parseInt(input2[i-1]);
            dir.put(i,d);
        }

        for(int i=1; i<=M; i++){
            for(int j=1; j<=4; j++){
                String[] input3 = br.readLine().split(" ");
                dirMap[i][j] = new ArrayList<>();
                for(int h=0; h<4; h++) {
                    int d = Integer.parseInt(input3[h]);
                    dirMap[i][j].add(d);
                }
            }
        }

        int time = 0;
        while (sharks.size() > 1){
            if(time>=1000){
                time = -1;
                break;
            }
            // 채취 -1
            for(int r=0; r<N; r++){
                for(int c=0; c<N; c++){
                    if(kMap[r][c] > 0){
                        kMap[r][c]--;
                    }
                }
            }
            // 새로 이동한 자리에 채취 k
            for(int n : sharks.keySet()){
                int[] shark = sharks.get(n);
                kMap[shark[0]][shark[1]] = k;
            }
            // 채취가 0인 자리 map에서 지우기
            for(int r=0; r<N; r++){
                for(int c=0; c<N; c++){
                    if(kMap[r][c] == 0){
                        map[r][c] = 0;
                    }
                }
            }

            // 상어 이동
            int[][] visit = new int[N][N];
            List<Integer> removeList = new ArrayList<>();
            for(int n : sharks.keySet()){
                int[] shark = sharks.get(n);
                int r = shark[0];
                int c = shark[1];
                int currDir = dir.get(n);

                int backDir = -1;
                int[] back = new int[] {-1,-1};
                boolean isMove = false;
                for(int i=0; i<dirMap[n][currDir].size(); i++){
                    int d = dirMap[n][currDir].get(i);
                    int nr = r+dr[d];
                    int nc = c+dc[d];
                    if(nr<0 || nc<0 || nr>=N || nc>=N) continue;

                    if(map[nr][nc] == n){
                        if(backDir != -1) continue;
                        back = new int[] {nr,nc};
                        backDir = d;
                    }else if(map[nr][nc] != n){
                        if(kMap[nr][nc] > 0) continue;
                        isMove = true;
                        if(visit[nr][nc] != 0){
                            int strong = Math.min(n, visit[nr][nc]);
                            int weak = Math.max(n, visit[nr][nc]);
                            removeList.add(weak);
                            if(strong == n) {
                                sharks.put(n, new int[] {nr,nc});
                                visit[nr][nc] = n;
                                dir.put(n, d);
                            }
                        }else if(visit[nr][nc] == 0){
                            sharks.put(n, new int[] {nr,nc});
                            visit[nr][nc] = n;
                            dir.put(n, d);
                        }
                        break;
                    }
                }
                if(!isMove){
                    int rb = back[0];
                    int cb = back[1];
                    sharks.put(n, new int[] {rb,cb});
                    visit[rb][cb] = n;
                    dir.put(n, backDir);
                }
            }

            // 쫒겨난 상어 Map에서 제거
            for(int i=0; i<removeList.size(); i++) {
                int n = removeList.get(i);
                sharks.remove(n);
            }

            // 상어 이동후 배열에 적용
            for(int n : sharks.keySet()){
                int[] shark = sharks.get(n);
                int r = shark[0];
                int c = shark[1];
                map[r][c] = n;
            }
            time++;
        }
        System.out.println(time);
    }
}