import java.io.BufferedReader;
import java.io.InputStreamReader;import java.security.DigestInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
	static int N,M,X;
	static int[] dist;
	static boolean[] visit;
	static int[][] map;
	static List<List<Node>> adj = new ArrayList<>();
	static class Node{
		int v=0;
		int w=0;
		
		public Node(int v, int w) {
			super();
			this.v = v;
			this.w = w;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] in = br.readLine().split(" ");
		N = Integer.parseInt(in[0]);
		M = Integer.parseInt(in[1]);
		X = Integer.parseInt(in[2]);
		dist = new int[N+1];
		visit = new boolean[N+1];
		map = new int[N+1][N+1];
		for(int i=0; i<=N; i++) adj.add(new ArrayList<>());
		for(int i=0; i<M; i++) {
			String[] no = br.readLine().split(" ");
			int s = Integer.parseInt(no[0]);
			int v = Integer.parseInt(no[1]);
			int w = Integer.parseInt(no[2]);
			adj.get(s).add(new Node(v, w));
		}
		
		
		
		PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return Integer.compare(o1.w, o2.w);
			}
		});
		
		for(int i=1; i<=N; i++) {
			dijstra(i, pq);
		}
		int max = -1;
		for(int v=1; v<=N; v++) {
			int len = map[X][v] + map[v][X];
			if(max<len) max = len;
		}
		System.out.println(max);
		
	}
	static void dijstra(int n, PriorityQueue<Node> pq) {
		Arrays.fill(dist, 123456789);
		visit = new boolean[N+1];
		dist[n] = 0;
		pq.add(new Node(n, 0));
		
		while(!pq.isEmpty()) {
			Node curr = pq.poll();
			visit[curr.v] = true;
			
			for(Node next : adj.get(curr.v)) {
				if(!visit[next.v] && dist[next.v] > dist[curr.v] + next.w) {
					dist[next.v] = dist[curr.v] + next.w;
					pq.add(new Node(next.v, dist[next.v]));
				}
			}
		}
		for(int i=1; i<=N; i++){
			map[n][i] = dist[i];
		}
	}
}