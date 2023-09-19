import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int start;
	static int target;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int t=0; t<T; t++) {
			String[] in = br.readLine().split(" ");
			start = Integer.parseInt(in[0]);
			target = Integer.parseInt(in[1]);
			
			Queue<Integer> q = new LinkedList<>();
			boolean[] visit = new boolean[10000];
			String[] path = new String[10000];
			
			q.offer(start);
			visit[start] = true;
			path[start] = "";
			
			bfs(q, visit, path);
		}
		System.out.println(sb);
	}
	static void bfs(Queue<Integer> q, boolean[] visit, String[] path) {
		while(!q.isEmpty()) {
			int num = q.poll();
			
			int nD = (2*num)%10000;
			
			int nS = (num==0)?9999:num-1;
			
			int n1 = num/1000;
			int nL = (num-n1*1000)*10+n1;
			
			int n2 = num/10;
			int nR = (num-n2*10)*1000+n2;
			
		
			
			if(!visit[nD]) {
				path[nD] = path[num] + "D";
				q.offer(nD);
				visit[nD] = true;
			}

			if(!visit[nS]) {
				path[nS] = path[num] + "S";
				q.offer(nS);
				visit[nS] = true;
			}
			if(!visit[nL]) {
				path[nL] = path[num] + "L";
				q.offer(nL);
				visit[nL] = true;
				
			}
			if(!visit[nR]) {
				path[nR] = path[num] + "R";
				q.offer(nR);
				visit[nR] = true;
			}
			
			
			if(nD==target || nS==target || nL==target || nR==target) {
				sb.append(path[target]).append("\n");
				return;
			}
			
		}
	}
}