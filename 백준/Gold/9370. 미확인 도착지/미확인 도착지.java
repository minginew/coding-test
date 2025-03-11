import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node {
    int v;
    int w;
    public Node(int v, int w){
        this.v = v;
        this.w = w;
    }
}
public class Main {
    static int T,n,m,t,s,g,h;
    static int[] distS, distG, distH, targets;
    static boolean[] visit;
    static List<Node>[] adjArr;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());
        for(int tc=0; tc<T; tc++){
            String[] input = br.readLine().split(" ");
            n = Integer.parseInt(input[0]);
            m = Integer.parseInt(input[1]);
            t = Integer.parseInt(input[2]);

            String[] input2 = br.readLine().split(" ");
            s = Integer.parseInt(input2[0]);
            g = Integer.parseInt(input2[1]);
            h = Integer.parseInt(input2[2]);

            distS = new int[n+1];
            distG = new int[n+1];
            distH = new int[n+1];
            targets = new int[t];
            visit = new boolean[n+1];
            adjArr = new ArrayList[n+1];
            for(int i=0; i<=n; i++){
                adjArr[i] = new ArrayList<>();
                distS[i] = 1000000000;
                distG[i] = 1000000000;
                distH[i] = 1000000000;
            }

            for(int i=0; i<m; i++){
                String[]input3 = br.readLine().split(" ");
                int a = Integer.parseInt(input3[0]);
                int b = Integer.parseInt(input3[1]);
                int d = Integer.parseInt(input3[2]);
                adjArr[a].add(new Node(b,d));
                adjArr[b].add(new Node(a,d));
            }

            for(int i=0; i<t; i++) targets[i] = Integer.parseInt(br.readLine());
            
            distS = Djikstra(s);
            visit = new boolean[n+1];
            distG = Djikstra(g);
            visit = new boolean[n+1];
            distH = Djikstra(h);

            List<Integer> answer = new ArrayList<>();
            for(int i=0; i<targets.length; i++){
                int target = targets[i];
                if(distS[target] == (distG[s] + distG[h] + distH[target])) answer.add(target);
                else if(distS[target] == (distG[target]+ distH[g] + distH[s])) answer.add(target);
            }
            answer.sort(Comparator.naturalOrder());
            for(int i=0; i<answer.size(); i++) sb.append(answer.get(i)).append(" ");
            sb.append('\n');
        }
        System.out.println(sb);
    }
    public static int[] Djikstra(int node){
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return Integer.compare(o1.w, o2.w);
            }
        });
        pq.offer(new Node(node, 0));

        int[] dist = new int[n+1];
        for(int i=0; i<=n; i++){
            dist[i] = Integer.MAX_VALUE;
        }
        dist[node] = 0;
        while (!pq.isEmpty()){
            Node curr = pq.poll();
            if(visit[curr.v]) continue;
            visit[curr.v] = true;

            for(int i=0; i<adjArr[curr.v].size(); i++){
                Node next = adjArr[curr.v].get(i);
                if(!visit[next.v] && dist[next.v] > dist[curr.v] + next.w){
                    dist[next.v] = dist[curr.v] + next.w;
                    pq.offer(new Node(next.v, dist[next.v]));
                }
            }
        }
        return dist;
    }
}