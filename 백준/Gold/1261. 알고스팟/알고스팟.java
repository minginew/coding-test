import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    static int N,M;
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    static int[][] map;
    static boolean[][] visit;
    static int[][] dist;
    static class Node{
        int[] next;
        int cost;

        public Node(int[] next, int cost){
            this.next = next;
            this.cost = cost;
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[1]);
        M = Integer.parseInt(input[0]);
        map = new int[N][M];
        visit = new boolean[N][M];
        dist = new int[N][M];

        for(int r=0; r<N; r++){
            String[] row = br.readLine().split("");
            for(int c=0; c<M; c++){
                map[r][c] = Integer.parseInt(row[c]);
                dist[r][c] = Integer.MAX_VALUE;
            }
        }

        Dijkstra(new int[] {0,0});
        System.out.println(dist[N-1][M-1]);
    }

    static void Dijkstra(int[] start){
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return Integer.compare(o1.cost,o2.cost);
            }
        });

        pq.offer(new Node(start, 0));
        dist[0][0] = 0;
        while (!pq.isEmpty()){
            Node curr = pq.poll();
            int r = curr.next[0];
            int c = curr.next[1];
            int cost = curr.cost;

            visit[r][c] = true;

            for(int d=0; d<4; d++){
                int nr = r+dr[d];
                int nc = c+dc[d];

                if(nr<0 || nr>=N || nc<0 || nc>=M || visit[nr][nc]) continue;
                if(dist[nr][nc] > cost + map[nr][nc]){
                    dist[nr][nc] = cost + map[nr][nc];
                    pq.offer(new Node(new int[] {nr,nc},dist[nr][nc]));
                }
            }
        }
    }
}