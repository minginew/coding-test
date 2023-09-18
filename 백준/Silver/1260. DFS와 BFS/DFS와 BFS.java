import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String[] in = br.readLine().split(" ");
		int N = Integer.parseInt(in[0]); //정점의 개수
		int M = Integer.parseInt(in[1]); //간선의 개수
		int S = Integer.parseInt(in[2]); //시작 정점
		int[][] matrix = new int[N+1][N+1]; // 인접행렬
		boolean[] visit = new boolean[N+1];
		for(int i=0; i<M; i++) {
			String[] e = br.readLine().split(" ");
			int r = Integer.parseInt(e[0]);
			int c = Integer.parseInt(e[1]);

			matrix[r][c] = 1;
			matrix[c][r] = 1;
		}
		
		Stack<Integer> stack = new Stack<>();
		stack.push(S);
		
		//dfs
		while(!stack.isEmpty()) {
			int n = stack.pop();
			if(!visit[n])sb.append(n).append(" ");
			visit[n] = true;
			for(int i=N; i>=0; i--) {
				if(matrix[n][i]==1 && !visit[i]) {
					stack.push(i);
				}
			}
		}
		sb.append("\n");
		
		Queue<Integer> q = new LinkedList<>();
		q.offer(S);
		visit = new boolean[N+1];
		visit[S] = true;
		
		//bfs
		while(!q.isEmpty()) {
			int n = q.poll();
			sb.append(n).append(" ");
			for(int i=1; i<N+1; i++) {
				if(matrix[n][i]==1 && !visit[i]) {
					q.offer(i);
					visit[i] = true;
				}
			}
		}
		System.out.println(sb.toString().trim());
	}

}