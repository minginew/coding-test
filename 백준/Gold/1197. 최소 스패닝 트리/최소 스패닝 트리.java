import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static int N,M;
    static int[] root,rank;
    static class Edge{
        int i;
        int j;
        int cost;

        public Edge(int i, int j, int cost){
            this.i = i;
            this.j = j;
            this.cost = cost;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        N = Integer.parseInt(in[0]);
        M = Integer.parseInt(in[1]);
        root = new int[N+1];
        rank = new int[N+1];
        List<Edge> list = new ArrayList<>(10001);

        for(int i=0; i<=N; i++) make(i);
        for(int i=0; i<M; i++){
            String[] e = br.readLine().split(" ");
            int x = Integer.parseInt(e[0]);
            int y = Integer.parseInt(e[1]);
            int c = Integer.parseInt(e[2]);
            list.add(new Edge(x,y,c));
        }

        Collections.sort(list, new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return Integer.compare(o1.cost,o2.cost);
            }
        });

        int count = 0;
        int costSum = 0;
        for(int i=0; i<M; i++){
            int x = find(list.get(i).i);
            int y = find(list.get(i).j);
            if(root[x]==root[y]) continue;
            int findX = find(list.get(i).i);
            int findY = find(list.get(i).j);

            if(findX == findY) continue;
            union(findX,findY);
            count++;
            costSum+=list.get(i).cost;
            if (count == N-1) break;
        }
        System.out.println(costSum);
    }
    public static void make(int i){
        root[i] = i;
    }
    public static int find(int x){
        if(root[x] == x) return x;
        else return root[x] = find(root[x]);
    }
    public static void union(int x, int y){
        if(rank[x]>=rank[y]) root[y] = x;
        else root[x] = y;
        if(rank[x]==rank[y]) rank[x]++;
    }

}