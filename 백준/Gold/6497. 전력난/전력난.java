import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N,M;
    static int[] root,rank;

    static class Edge{
        int st;
        int ed;
        int cost;
        public Edge(int st, int ed, int cost){
            this.st = st;
            this.ed = ed;
            this.cost = cost;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


       while (true){
           String[] in = br.readLine().split(" ");
           N = Integer.parseInt(in[0]);
           M = Integer.parseInt(in[1]);
           if(N==0 && M==0) break;

           root = new int[20001];
           rank = new int[20001];
           List<Edge> list = new ArrayList<>();

           for(int i=0; i<=20000; i++){
               make(i);
               rank[i] = 0;
           }

           int totalCost = 0;
           for(int i=0; i<M; i++) {
               String[] edge = br.readLine().split(" ");
               int st = Integer.parseInt(edge[0]);
               int ed = Integer.parseInt(edge[1]);
               int cost= Integer.parseInt(edge[2]);
               totalCost+=cost;
               list.add(new Edge(st, ed, cost));
           }


           Collections.sort(list, new Comparator<Edge>() {
               @Override
               public int compare(Edge o1, Edge o2) {
                   return Integer.compare(o1.cost, o2.cost);
               }
           });

           int cost = 0;
           int count = 0;
           for(Edge e : list) {
               int findX = find(e.st);
               int findY = find(e.ed);

               if(findX==findY) continue;

               union(findX, findY);
               cost += e.cost;
               count++;
               if (count == N-1) break;
           }
           System.out.println(totalCost - cost);
       }
    }

    static void make(int i){
        root[i]=i;
    }
    static int find(int x){
        if(root[x]==x) return x;
        return root[x] = find(root[x]);
    }

    static void union(int x, int y){
        if(rank[x] < rank[y]) {
            root[x] = y;
        } else {
            root[y] = x;

            if(rank[x] == rank[y])
                rank[x]++;
        }
    }
}