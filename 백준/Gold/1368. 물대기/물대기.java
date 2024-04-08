import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int minTotal = Integer.MAX_VALUE;
    static int[] queueInfo;
    static int[] digCost;
    static boolean[] visit;
    static int[][] adjArr;
    static List<Integer> startList = new LinkedList<>();
    static class Edge {
        int st;
        int ed;
        int w;

        Edge(int curr, int next, int cost){
            this.st = curr;
            this.ed = next;
            this.w = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        queueInfo = new int[N];
        digCost = new int[N];
        adjArr = new int[N][N];

        for(int i=0; i<N; i++){
            digCost[i] = Integer.parseInt(br.readLine());
        }

        for(int r=0; r<N; r++){
            String[] row = br.readLine().split(" ");
            for(int c=0; c<N; c++){
                if(r==c) continue;
                int cost = Integer.parseInt(row[c]);
                adjArr[r][c] = cost;
            }
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return Integer.compare(o1.w, o2.w);
            }
        });

        for(int i=0; i<N; i++){
            int totalCost = digCost[i];
            Arrays.fill(queueInfo, Integer.MAX_VALUE);
            visit = new boolean[N];
            visit[i] = true;
            pq.clear();

            for(int j=0; j<N; j++){
                if(i == j) continue;
                queueInfo[j] = Math.min(adjArr[i][j], digCost[j]);
                pq.add(new Edge(i, j, queueInfo[j]));
            }

            while (!pq.isEmpty()){
                Edge curr = pq.poll();
                if(visit[curr.ed]) continue;
                visit[curr.ed] = true;
                totalCost += Math.min(adjArr[curr.st][curr.ed], digCost[curr.ed]);

                int newSt = curr.ed;
                for(int newEd=0; newEd<N; newEd++){
                    if(!visit[newEd] && adjArr[newSt][newEd] <= queueInfo[newEd]){
                        pq.add(new Edge(newSt, newEd, adjArr[newSt][newEd]));
                        queueInfo[newEd] = adjArr[newSt][newEd];
                    }
                }
            }
            if(totalCost < minTotal){
                minTotal = totalCost;
            }
        }
        System.out.println(minTotal);
    }
}