import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        List<Integer>[] adj = new ArrayList[N+1];
        for(int i=0; i< adj.length; i++) adj[i] = new ArrayList<>();

        for(int i=0; i<M; i++){
            String[] input2 = br.readLine().split(" ");
            int st = Integer.parseInt(input2[0]);
            int ed = Integer.parseInt(input2[1]);
            adj[st].add(ed);
            adj[ed].add(st);
        }

        Stack<int[]> stack = new Stack<>();
        boolean[] visit = new boolean[N+1];
        int treeNum = 0;
        int cycleNum = 0;
        for(int i=1; i<=N; i++){
            if(visit[i]) continue;

            //[현재노드, 부모노드]
            stack.push(new int[] {i,0});
            visit[i] = true;

            while (!stack.isEmpty()){
                int nodes[] = stack.pop();
                int curr = nodes[0];
                int parent = nodes[1];

                for(int j=0; j<adj[curr].size(); j++){
                    int next = adj[curr].get(j);
                    if(visit[next]){
                        // 사이클 발생시키는 간선을 카운트할 때 무방향 그래프에선 두번씩 계산된다.
                        if(next != parent) cycleNum++;
                        continue;
                    }
                    stack.push(new int[] {next, curr});
                    visit[next] = true;
                }
            }
            treeNum++;
        }
        System.out.println(treeNum-1+(cycleNum/2));
    }
}