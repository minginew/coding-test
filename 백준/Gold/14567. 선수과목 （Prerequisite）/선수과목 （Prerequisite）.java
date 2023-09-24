import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N,M;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] in = br.readLine().split(" ");
        N = Integer.parseInt(in[0]);
        M = Integer.parseInt(in[1]);

        int[] degree = new int[N+1];
        List<Integer>[] adjList = new ArrayList[N+1];
        for(int i=0; i<adjList.length; i++) adjList[i] = new ArrayList<>();

        for(int i=0; i<M; i++){
            String[] edge = br.readLine().split(" ");
            int st = Integer.parseInt(edge[0]);
            int ed = Integer.parseInt(edge[1]);
            adjList[st].add(ed);
            degree[ed]++;
        }

        Queue<Integer> q = new LinkedList<>();
        for(int i=1; i<degree.length; i++){
            if(degree[i]==0) q.offer(i);
        }
        int size = q.size();
        int count = 1;
        int[] out = new int[N+1];
        while (!q.isEmpty()){
            for(int i=0; i<size; i++){
                int st = q.poll();
                out[st] = count;
                for(int j=0; j<adjList[st].size(); j++){
                    int ed = adjList[st].get(j);
                    degree[ed]--;
                    if(degree[ed]==0) q.offer(ed);
                }
            }
            size = q.size();
            count++;
        }

        for(int i=1; i<out.length; i++) System.out.print(out[i]+" ");
    }
}