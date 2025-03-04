import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Edge {
    int v1,v2,w;
    public Edge(int v1, int v2, int w){
        this.v1 = v1;
        this.v2 = v2;
        this.w = w;
    }
}
public class Main {
    static int[] yr = {-1,1};
    static int[] yc = {0,0};
    static int[] xr = {0,0};
    static int[] xc = {-1,1};
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    static int N,M;
    static int[][] map;
    static boolean[][] visitBfs;
    static int[] parent,rank;
    static Queue<int[]> q;
    static PriorityQueue<Edge> pq;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new  BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        map = new int[N][M];

        for(int r=0; r<N; r++){
            String[] col = br.readLine().split(" ");
            for(int c=0; c<M; c++){
                map[r][c] = Integer.parseInt(col[c]);
            }
        }

        q = new LinkedList<>();
        visitBfs = new boolean[N][M];
        int group = 1;
        for(int r=0; r<N; r++){
            for(int c=0; c<M; c++){
                if(map[r][c]==0 || visitBfs[r][c]) continue;
                map[r][c] = group;
                q.offer(new int[] {r,c});
                visitBfs[r][c] = true;
                groupSearch(group);
                group++;
            }
        }

        pq = new PriorityQueue<>(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return Integer.compare(o1.w, o2.w);
            }
        });

        for(int i=1; i<group; i++){
            q = new LinkedList<>();
            visitBfs = new boolean[N][M];
            for(int r=0; r<N; r++){
                for(int c=0; c<M; c++){
                    if(map[r][c] == i){
                        q.offer(new int[] {r,c});
                        visitBfs[r][c] = true;
                    }
                }
            }
            bfs(i,'y');
        }

        for(int i=1; i<group; i++){
            q = new LinkedList<>();
            visitBfs = new boolean[N][M];
            for(int r=0; r<N; r++){
                for(int c=0; c<M; c++){
                    if(map[r][c] == i){
                        q.offer(new int[] {r,c});
                        visitBfs[r][c] = true;
                    }
                }
            }
            bfs(i,'x');
        }

        int answer = 0;
        parent = new int[group];
        rank = new int[group];
        makeup();
        while (!pq.isEmpty()){
            Edge edge = pq.poll();
            int f1 = find(edge.v1);
            int f2 = find(edge.v2);
            int w = edge.w;

            if(f1 == f2) continue;
            union(f1,f2);
            answer += w;
        }

        int rootNum = 0;
        for(int i=1; i<parent.length; i++){
            if(parent[i] == i) rootNum++;
        }

        if(rootNum == 1) System.out.println(answer);
        else System.out.println(-1);
    }

    public static void makeup (){
        for(int i=0; i<parent.length; i++) parent[i] = i;
    }

    public static int find (int node){
        if(parent[node] == node) return node;
        else return parent[node] = find(parent[node]);
    }

    public static void union(int f1, int f2){
        if(f1 == f2) return;
        if(rank[f1] < rank[f2]){
            parent[f1] = f2;
        }else {
            parent[f2] = f1;
            if(rank[f1] == rank[f2]) rank[f1]++;
        }
    }

    public static void groupSearch(int group){
        while (!q.isEmpty()){
            int r = q.peek()[0];
            int c = q.poll()[1];

            for(int d=0; d<4; d++){
                int nr = r+dr[d];
                int nc = c+dc[d];
                if(nr<0 || nc<0 || nr>=N || nc>=M || visitBfs[nr][nc] || map[nr][nc] == 0) continue;
                q.offer(new int[] {nr,nc});
                visitBfs[nr][nc] = true;
                map[nr][nc] = group;
            }
        }
    }
    public static void bfs(int myGroup, char type){
        int size = q.size();
        int move = 0;
        while (!q.isEmpty()){
            for(int i=0; i<size; i++){
                int r = q.peek()[0];
                int c = q.poll()[1];

                for(int d=0; d<2; d++){
                    int nr = r;
                    int nc = r;
                    if(type == 'y'){
                        nr = r+yr[d];
                        nc = c+yc[d];
                    }else if(type == 'x'){
                        nr = r+xr[d];
                        nc = c+xc[d];
                    }
                    if(nr<0 || nc<0 || nr>=N || nc>=M || visitBfs[nr][nc]) continue;
                    if(map[nr][nc] == 0){
                        q.offer(new int[] {nr,nc});
                        visitBfs[nr][nc] = true;
                    }else {
                        int otherGroup = map[nr][nc];
                        visitBfs[nr][nc] = true;
                        if(move == 1) continue;
                        pq.offer(new Edge(myGroup,otherGroup,move));
                    }
                }
            }
            size = q.size();
            move++;
        }
    }
}