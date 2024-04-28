import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.*;

public class Main {
    // 지역수, 수색범위, 간선수
    static int N,M,R,maxItem;
    static int[] item;
    static boolean[] visit;
    static int[][] dist;
    static List<List<Node>> adjList;
    static class Node {
        int next;
        int cost;

        public Node(int next, int cost){
            this.next = next;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        R = Integer.parseInt(input[2]);
        maxItem = Integer.MIN_VALUE;
        item = new int[N];
        visit = new boolean[N];
        dist = new int[N][N];
        adjList = new LinkedList<>();

        for(int r=0; r<N; r++){
            for(int c=0; c<N; c++){
                dist[r][c] = Integer.MAX_VALUE;
            }
        }

        String[] inItem = br.readLine().split(" ");
        for(int i=0; i<N; i++){
            item[i] = Integer.parseInt(inItem[i]);
            adjList.add(new LinkedList<Node>());
        }

        for(int i=0; i<R; i++){
            String[] edge = br.readLine().split(" ");
            int node1 = Integer.parseInt(edge[0])-1;
            int node2 = Integer.parseInt(edge[1])-1;
            int cost = Integer.parseInt(edge[2]);

            adjList.get(node1).add(new Node(node2,cost));
            adjList.get(node2).add(new Node(node1,cost));

        }

        for(int st=0; st<N; st++){
            Dijkstra(st);
        }

        for(int i=0; i<N; i++){
            int itemNum = 0;
            for(int j=0; j<N; j++){
                if (dist[i][j] <= M)  itemNum+=item[j];
            }
            if(itemNum > maxItem) maxItem = itemNum;
        }
        System.out.println(maxItem);
    }

    static void Dijkstra(int start){
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return Integer.compare(o1.cost, o2.cost);
            }
        });

        visit = new boolean[N];
        pq.offer(new Node(start, 0));
        dist[start][start] = 0;

        while (!pq.isEmpty()){
            Node curr = pq.poll();
            visit[curr.next] = true;

            for(int i=0; i<adjList.get(curr.next).size(); i++){
                Node next = adjList.get(curr.next).get(i);
                if(!visit[next.next] && dist[start][next.next] > curr.cost + next.cost){
                    dist[start][next.next] = curr.cost + next.cost;
                    pq.offer(new Node(next.next, dist[start][next.next]));
                }
            }
        }
    }
}