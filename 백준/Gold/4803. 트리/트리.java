import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

public class Main {
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringBuilder sb = new StringBuilder();
            int T = 0;
            int N;
            int M;
            int count = 0;
            boolean[] visit ;
            ArrayList<Integer>[] adj;
            Stack<int[]> stack = new Stack<>();

            while (true){
                String[] input = br.readLine().split(" ");
                N = Integer.parseInt(input[0]);
                M = Integer.parseInt(input[1]);
                T++;
                //종료조건
                if(N==0 && M==0) break;

                count = 0;
                adj = new ArrayList[N+1];
                //Arrays.fill로 참조 채우지말 것 tlqkf
                for(int i=1; i<adj.length; i++) adj[i] = new ArrayList<>();
                visit = new boolean[N+1];
                visit[0] = true;

                for(int i=0; i<M; i++){
                    String[] edge = br.readLine().split(" ");
                    int st = Integer.parseInt(edge[0]);
                    int ed = Integer.parseInt(edge[1]);

                    adj[st].add(ed);
                    adj[ed].add(st);
                }

                for(int i=1; i<=N; i++){
                    if(visit[i]) continue;
                    // [현재노드, 부모노드]
                    stack.push(new int[] {i,0});
                    visit[i] = true;

                    boolean isCycle = false;
                    while(!stack.isEmpty()){
                        int nodes[] = stack.pop();
                        int curr = nodes[0];
                        int parent = nodes[1];
                        for(int j=0; j<adj[curr].size(); j++){
                            int next = adj[curr].get(j);
                            if(visit[next]) {
                                // 방문한 노드가 부모가 아닌 다른 인접노드일 경우 == 싸이클 형성
                                if(next != parent) isCycle = true;
                                continue;
                            }

                            visit[next] = true;
                            stack.push(new int[] {next, curr});
                        }
                    }
                    if(!isCycle) count++;
                }
                switch (count){
                    case 0: sb.append("Case "+T+": ").append("No trees.").append("\n");
                        break;
                    case 1: sb.append("Case "+T+": ").append("There is one tree.").append("\n");
                        break;
                    default: sb.append("Case "+T+": ").append("A forest of "+count+" trees.").append("\n");
                }
            }
            System.out.println(sb);
        }
}