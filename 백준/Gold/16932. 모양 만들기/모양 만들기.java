import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    static int N,M;
    static int group = 2;
    static int maxCount = Integer.MIN_VALUE;
    static int[][] map;
    static Map<Integer, Integer> maps;
    static Queue<int[]> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        map = new int[N][M];
        maps = new HashMap<>();

        for(int r=0; r<N; r++){
            String[] input2 = br.readLine().split(" ");
            for(int c=0; c<M; c++){
                map[r][c] = Integer.parseInt(input2[c]);
            }
        }

        for(int r=0; r<N; r++){
            for(int c=0; c<M; c++){
                if(map[r][c] != 1) continue;
                q.offer(new int[] {r,c});
                map[r][c] = group;
                int count = bfs(group);
                maps.put(group++, count);
            }
        }

        for(int r=0; r<N; r++){
            for(int c=0; c<M; c++){
                if(map[r][c] != 0) continue;
                int linkCount = 1;
                Set<Integer> set = new HashSet<>();
                for(int d=0; d<4; d++){
                    int nr = r+dr[d];
                    int nc = c+dc[d];
                    if(nr<0 || nc<0 || nr>=N || nc>=M || map[nr][nc] == 0) continue;
                    set.add(map[nr][nc]);
                }
                for(int n : set){
                    linkCount += maps.get(n);
                }
                if(linkCount > maxCount){
                    maxCount = linkCount;
                }
            }
        }
        System.out.println(maxCount);
    }
    public static int bfs(int group){
        int count = 0;
        while (!q.isEmpty()){
            int r = q.peek()[0];
            int c = q.poll()[1];
            count++;
            for(int d=0; d<4; d++){
                int nr = r+dr[d];
                int nc = c+dc[d];
                if(nr<0 || nc<0 || nr>=N || nc>=M || map[nr][nc] != 1) continue;
                q.offer(new int[] {nr,nc});
                map[nr][nc] = group;
            }
        }
        return count;
    }
}
