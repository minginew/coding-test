import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    // 행성수
    static int N;
    static long totalMin;
    // 방문배열
    static boolean[] visit;
    // 큐에 들어왔던 간선정보
    static int[] queueInfo;

    // 행성 리스트
    static List<int[]> planets;
    // 간선 정보
    static List<List<Edge>> edgeList;
    static class Edge {
        int st;
        int ed;
        int w;
        Edge(int st, int ed, int w){
            this.st = st;
            this.ed = ed;
            this.w = w;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        visit = new boolean[N];
        queueInfo = new int[N];
        planets = new ArrayList<>();
        edgeList = new ArrayList<>();

        for(int i=0; i<N; i++){
            queueInfo[i] = Integer.MAX_VALUE;
            edgeList.add(new ArrayList<>());
        }

        for(int i=0; i<N; i++){
            String[] input = br.readLine().split(" ");
            int x = Integer.parseInt(input[0]);
            int y = Integer.parseInt(input[1]);
            int z = Integer.parseInt(input[2]);
            planets.add(new int[] {i,x,y,z});
        }

        if(N==1){
            System.out.println(0);
            return;
        }


        for(int i=1; i<=3; i++){
            int idx = i;
            Collections.sort(planets, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return Integer.compare(o1[idx],o2[idx]);
                }
            });

            for(int j=1; j<N; j++){
                int[] p1 = planets.get(j-1);
                int[] p2 = planets.get(j);
                edgeList.get(p1[0]).add(new Edge(p1[0],p2[0],Math.abs(p1[i]-p2[i])));
                edgeList.get(p2[0]).add(new Edge(p2[0],p1[0],Math.abs(p1[i]-p2[i])));
            }
        }
        prim(0);
        System.out.println(totalMin);
    }

    static void prim(int start){
        PriorityQueue<Edge> pq = new PriorityQueue<>(new Comparator<Edge>() {
            @Override
            public int compare(Edge e1, Edge e2) {
                return Integer.compare(e1.w, e2.w);
            }
        });

        visit[start] = true;
        for(Edge e : edgeList.get(0)){
            queueInfo[e.ed] = e.w;
            pq.offer(e);
        }

        while (!pq.isEmpty()){
            Edge curr = pq.poll();
            if(visit[curr.ed]) continue;
            visit[curr.ed] = true;
            totalMin += curr.w;

            int newSt = curr.ed;
            for(int i=0; i<edgeList.get(newSt).size(); i++) {
                Edge next = edgeList.get(newSt).get(i);
                // 방문한 간선정보를 기록하는게 메모리에 유의미한 변화는 주지 못했다.
                if (!visit[next.ed] && queueInfo[next.ed] >= next.w){
                    queueInfo[next.ed] = next.w;
                    pq.offer(next);
                }
            }
        }
    }
}