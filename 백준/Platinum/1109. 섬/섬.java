import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] dr = {-1,1,0,0,-1,-1,1,1};
    static int[] dc = {0,0,-1,1,-1,1,-1,1};
    static int N,M,maxDepth;
    static int[] height, count;
    static int[][] map;
    static boolean[] root,dfsVisit;
    static boolean[][] visit;
    static List<Queue<int[]>> island;
    static List<Integer>[] adjArr;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new  BufferedReader(new InputStreamReader(System.in));
        //1. 섬을 구분한다.
        //2. 섬을 bfs해서 경계에 닿을 수 있으면 높이가 0이다.
        //3. 닿을 수 없다면 만날 수있는 섬의 개수를 기록한다.
        //4. 가장 내부의 섬은 만날 수 있는 섬이 1개이다.
        //5. 이과정이 끝나면 dfs로 높이를 매긴다.
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        map = new int[N][M];
        visit = new boolean[N][M];
        island = new ArrayList<>();
        island.add(new LinkedList<>());

        for(int r=0; r<N; r++){
            String[] col = br.readLine().split("");
            for(int c=0; c<M; c++){
                if(col[c].charAt(0) == 'x'){
                    map[r][c] = 1;
                }else {
                    map[r][c] = 0;
                }
            }
        }

        Queue<int[]> q = new LinkedList<>();
        int islandNum = 0;
        for(int r=0; r<N; r++){
            for(int c=0; c<M; c++){
                if(visit[r][c] || map[r][c] == 0) continue;
                island.add(new LinkedList<>());
                islandNum++;
                q.offer(new int[]{r,c});
                visit[r][c] = true;
                map[r][c] = islandNum;
                bfs1(q,islandNum);

            }
        }

        if(islandNum == 0){
            System.out.println(-1);
            return;
        }

        root = new boolean[islandNum+1];
        adjArr = new ArrayList[islandNum+1];
        for(int i=0; i<adjArr.length; i++) adjArr[i] = new ArrayList<>();

        for(int i=1; i<island.size(); i++){
            q = island.get(i);
            visit = new boolean[N][M];
            boolean[] adjVisit = new boolean[islandNum+1];
            bfs2(q, i, adjVisit);

        }

        height = new int[islandNum+1];
        count = new int[M+1];
        dfsVisit = new boolean[islandNum+1];
        for(int i=1; i<=islandNum; i++){
            if(root[i]){
                maxDepth = Integer.MIN_VALUE;
                dfsVisit[i] = true;
                dfs(i, 0,0);
            }
        }
        for(int i=1; i<height.length; i++) count[height[i]]++;

        StringBuilder sb  = new StringBuilder();
        for(int i=0; i<count.length; i++){
            if(count[i] == 0) break;
            sb.append(count[i]).append(" ");
        }
        System.out.println(sb);
    }

    public static void bfs1(Queue<int[]> q, int islandNum){
        while(!q.isEmpty()){
            int r = q.peek()[0];
            int c = q.poll()[1];
            island.get(islandNum).offer(new int[]{r,c});
            for(int d=0; d<8; d++){
                int nr = r + dr[d];
                int nc = c + dc[d];

                if(nr<0 || nc<0 || nr>=N || nc>=M || visit[nr][nc] || map[nr][nc] == 0) continue;
                q.offer(new int[]{nr,nc});
                visit[nr][nc] = true;
                map[nr][nc] = islandNum;
            }
        }
    }

    public static void bfs2(Queue<int[]> q, int islandNum, boolean[] adjVisit){
        while(!q.isEmpty()){
            int r = q.peek()[0];
            int c = q.poll()[1];

            for(int d=0; d<4; d++){
                int nr = r + dr[d];
                int nc = c + dc[d];

                if(nr<0 || nc<0 || nr>=N || nc>=M) {
                    root[islandNum] = true;
                }else if(map[nr][nc] != 0 && map[nr][nc]!=islandNum && !adjVisit[map[nr][nc]]){
                    adjVisit[map[nr][nc]] = true;
                    adjArr[islandNum].add(map[nr][nc]);
                }else if(!visit[nr][nc] && map[nr][nc] == 0){
                    q.offer(new int[]{nr,nc});
                    visit[nr][nc] = true;
                }
            }
        }
    }

    public static int dfs(int node, int depth, int parent){
        int maxDep = depth;
        for(int i=0; i<adjArr[node].size(); i++){
            int next = adjArr[node].get(i);
            if(dfsVisit[next] || adjArr[parent].contains(next) || root[next]) continue;
            dfsVisit[next] = true;
            int dep =  dfs(next, depth+1, node);
            maxDep = Math.max(maxDep, dep);
        }
        height[node] = maxDep-depth;
        return maxDep;
    }
}