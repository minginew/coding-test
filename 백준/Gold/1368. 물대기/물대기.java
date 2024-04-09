import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    //논의수
    static int N;
    //최소비용
    static int minTotal = Integer.MAX_VALUE;
    //우선순위 큐에 방문했던 간선의 비용정보
    static int[] queueInfo;
    //논을 파는데 들어가는 비용
    static int[] digCost;
    //방문노드 배열
    static boolean[] visit;
    //인접배열 (노드간 비용정보)
    static int[][] adjArr;
    
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
                // index 번째 노드로 들어오는 간선의 비용을 기록해둔다.
                queueInfo[j] = Math.min(adjArr[i][j], digCost[j]);
                pq.add(new Edge(i, j, queueInfo[j]));
            }

            while (!pq.isEmpty()){
                Edge curr = pq.poll();
                if(visit[curr.ed]) continue;
                visit[curr.ed] = true;
                // 물을 끌어올지, 아니면 새로 논의 우물을 팔지 결정한다.
                totalCost += Math.min(adjArr[curr.st][curr.ed], digCost[curr.ed]);

                int newSt = curr.ed;
                for(int newEd=0; newEd<N; newEd++){
                    // newEd 노드로 들어오는 간선기록을 확인후 불필요한 간선을 걸러준다.
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